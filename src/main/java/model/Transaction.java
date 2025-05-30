package model;


import jakarta.persistence.*;

import java.math.BigDecimal; // Finansal tutarlar için BigDecimal kullanılması tavsiye edilir
import java.time.LocalDate; // Tarih için LocalDate kullanılması tavsiye edilir

@Entity // Bu sınıfın bir JPA varlığı olduğunu belirtir
@Table(name = "transactions") // Veritabanındaki tablo adını belirtir
public class Transaction {

    @Id // Birincil anahtarı işaretler
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Kimlik sütununun veritabanı tarafından otomatik olarak artırılacağını belirtir
    private Long id;

    // Bir işlemin bir kullanıcıya ait olduğunu belirtir (Çoktan Bire ilişki)
    // FetchType.LAZY: Kullanıcı nesnesini sadece ihtiyaç duyulduğunda yükle
    // JoinColumn: user_id sütununun foreign key olduğunu belirtir
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Bir işlemin bir kategoriye ait olduğunu belirtir (Çoktan Bire ilişki)
    // FetchType.LAZY: Kategori nesnesini sadece ihtiyaç duyulduğunda yükle
    // JoinColumn: category_id sütununun foreign key olduğunu belirtir
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false) // Tutarın null olamayacağını belirtir
    private BigDecimal amount; // Finansal tutarlar için BigDecimal

    @Column(nullable = false) // Açıklamanın null olamayacağını belirtir
    private String description;

    @Column(nullable = false) // İşlem tarihinin null olamayacağını belirtir
    private LocalDate transactionDate; // Tarih için LocalDate

    @Column(nullable = false) // İşlem tipinin (gelir/gider) null olamayacağını belirtir
    private String type; // "INCOME" veya "EXPENSE" gibi String değerler tutulacak

    // JPA için gerekli olan varsayılan constructor
    public Transaction() {
    }

    // İşlem oluşturmak için constructor
    public Transaction(User user, Category category, BigDecimal amount, String description, LocalDate transactionDate, String type) {
        this.user = user;
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.transactionDate = transactionDate;
        this.type = type;
    }

    // Getter ve Setter metotları
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", transactionDate=" + transactionDate +
                ", type='" + type + '\'' +
                '}';
    }
}
