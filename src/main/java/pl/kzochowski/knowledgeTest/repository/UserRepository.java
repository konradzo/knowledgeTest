package pl.kzochowski.knowledgeTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kzochowski.knowledgeTest.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(Integer id);

    Optional<User> findByEmail(String email);

    List<User> findByEmailContainingIgnoreCase(String query);
}
