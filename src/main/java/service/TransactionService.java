package service;

import exception.InvalidTransactionException;
import exception.ResourceNotFoundException;
import model.Category;
import model.Transaction;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              UserService userService,
                              CategoryService categoryService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    public Transaction addTransaction(Long userId, String categoryName, BigDecimal amount, String description, LocalDate date, boolean isIncome) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransactionException("Tutar pozitif olmalıdır.");
        }

        User user = userService.getUserById(userId);
        Category category = categoryService.getCategoryByName(categoryName);

        String type = isIncome ? "INCOME" : "EXPENSE";

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setCategory(category);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setTransactionDate(date);
        transaction.setType(type);

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getMonthlyTransactions(Long userId, int month) {
        User user = userService.getUserById(userId);
        List<Transaction> all = transactionRepository.findAll(); // veya custom query varsa onu kullan
        return all.stream()
                .filter(t -> t.getUser().getId().equals(userId) &&
                        t.getTransactionDate().getMonthValue() == month)
                .collect(Collectors.toList());
    }

    public Map<String, Object> getMonthlySummary(Long userId, int month) {
        List<Transaction> transactions = getMonthlyTransactions(userId, month);

        BigDecimal income = transactions.stream()
                .filter(t -> "INCOME".equals(t.getType()))
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal expense = transactions.stream()
                .filter(t -> "EXPENSE".equals(t.getType()))
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        String topCategory = transactions.stream()
                .filter(t -> "EXPENSE".equals(t.getType()))
                .collect(Collectors.groupingBy(
                        t -> t.getCategory().getName(),
                        Collectors.reducing(BigDecimal.ZERO, Transaction::getAmount, BigDecimal::add)
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Yok");

        Map<String, Object> summary = new HashMap<>();
        summary.put("income", income);
        summary.put("expense", expense);
        summary.put("savings", income.subtract(expense));
        summary.put("topSpendingCategory", topCategory);

        return summary;
    }
    public Transaction updateTransaction(Long id, String categoryName, BigDecimal amount, String description, LocalDate date, boolean isIncome) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("İşlem bulunamadı. ID: " + id));

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransactionException("Tutar pozitif olmalıdır.");
        }

        Category category = categoryService.getCategoryByName(categoryName);

        transaction.setCategory(category);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setTransactionDate(date);
        transaction.setType(isIncome ? "INCOME" : "EXPENSE");

        return transactionRepository.save(transaction);
    }
    public void deleteTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("İşlem bulunamadı. ID: " + id));
        transactionRepository.delete(transaction);
    }


}
