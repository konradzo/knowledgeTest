package pl.kzochowski.knowledgeTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kzochowski.knowledgeTest.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByName(String categoryName);
}
