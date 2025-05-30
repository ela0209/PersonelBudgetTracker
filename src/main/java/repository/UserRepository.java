package repository;



import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static java.awt.PageAttributes.MediaType.C;


@Repository // Bu arayüzün bir Spring bileşeni olduğunu ve Repository katmanına ait olduğunu belirtir
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository, User entity'si için CRUD operasyonlarını (save, findById, findAll, delete vb.) sağlar.
    // İlk parametre: Yönetilecek Entity sınıfı (User)
    // İkinci parametre: Entity'nin ID tipidir (Long)

    // Kullanıcı adına göre kullanıcıyı bulmak için özel metot (Spring Data JPA adlandırma kuralı ile otomatik oluşur)
    Optional<User> findByUsername(String username);

    // Belirli bir kullanıcı adının zaten var olup olmadığını kontrol etmek için
    boolean existsByUsername(String username);
}
