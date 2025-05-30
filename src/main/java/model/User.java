package model; // Paket adınızı doğru şekilde güncelleyin


import jakarta.persistence.*;
import jakarta.transaction.Transaction;

import java.util.HashSet;
import java.util.Set;

@Entity // Bu sınıfın bir JPA varlığı olduğunu belirtir
@Table(name = "users") // Veritabanındaki tablo adını belirtir
public class User {

    @Id // Birincil anahtarı işaretler
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Kimlik sütununun veritabanı tarafından otomatik olarak artırılacağını belirtir
    private Long id;

    @Column(nullable = false, unique = true) // Sütunun null olamayacağını ve benzersiz olması gerektiğini belirtir
    private String username;

    @Column(nullable = false) // Sütunun null olamayacağını belirtir
    private String password;

    // Bir kullanıcının birden çok işlemi olabilir. mappedBy "user" alanının Transaction sınıfında olduğunu belirtir.
    // CascadeType.ALL: User ile ilgili tüm işlemler (kaydetme, güncelleme, silme) Transaction'lara da yansır.
    // orphanRemoval = true: Bir transaction'ın user ilişkisi kaldırılırsa, transaction'ın kendisi de silinir.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Transaction> transactions = new HashSet<>();

    // JPA için gerekli olan varsayılan constructor
    public User() {
    }

    // Kullanıcı oluşturmak için constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter ve Setter metotları
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }


}
