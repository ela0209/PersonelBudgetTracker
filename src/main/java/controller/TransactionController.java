package controller;



import model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/add")
    public Transaction addTransaction(@RequestParam Long userId,
                                      @RequestParam String category,
                                      @RequestParam BigDecimal amount,
                                      @RequestParam String description,
                                      @RequestParam String date, // format: yyyy-MM-dd
                                      @RequestParam boolean income) {

        return transactionService.addTransaction(
                userId,
                category,
                amount,
                description,
                LocalDate.parse(date),
                income
        );
    }

    @GetMapping("/monthly")
    public List<Transaction> getMonthly(@RequestParam Long userId, @RequestParam int month) {
        return transactionService.getMonthlyTransactions(userId, month);
    }

    @GetMapping("/summary")
    public Map<String, Object> getSummary(@RequestParam Long userId, @RequestParam int month) {
        return transactionService.getMonthlySummary(userId, month);
    }
    // PUT /transactions/update
    @PutMapping("/update")
    public Transaction updateTransaction(@RequestParam Long id,
                                         @RequestParam String category,
                                         @RequestParam BigDecimal amount,
                                         @RequestParam String description,
                                         @RequestParam String date,
                                         @RequestParam boolean income) {
        return transactionService.updateTransaction(
                id,
                category,
                amount,
                description,
                LocalDate.parse(date),
                income
        );
    }

    // DELETE /transactions/delete/{id}
    @DeleteMapping("/delete/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }

}
