package repository;


import model.Transaction; // Transaction model sınıfınızı import edin
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Belirli bir kullanıcıya ait tüm işlemleri getirme
    List<Transaction> findByUserId(Long userId);

    // Belirli bir kullanıcıya ait ve belirli bir kategoriye ait tüm işlemleri getirme
    List<Transaction> findByUserIdAndCategoryId(Long userId, Long categoryId);

    // Belirli bir kullanıcıya ait ve belirli bir tarih aralığındaki işlemleri getirme
    // (Aylık raporlama için faydalı olabilir)
    List<Transaction> findByUserIdAndTransactionDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    // Belirli bir kullanıcıya ait ve belirli bir işlem tipindeki (INCOME/EXPENSE) tüm işlemleri getirme
    List<Transaction> findByUserIdAndType(Long userId, String type);

    // Diğer özel sorgular, projenizin gereksinimlerine göre buraya eklenebilir.
    // Örneğin: findByUserIdOrderByTransactionDateDesc(), findByUserIdAndAmountGreaterThan(Long userId, BigDecimal amount)
}
