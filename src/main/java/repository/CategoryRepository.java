package repository;



import model.Category; // Category model sınıfınızı import edin
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Kategori adına göre kategoriyi bulmak için özel metot
    Optional<Category> findByName(String name);
}
