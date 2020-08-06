package pl.kzochowski.knowledgeTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kzochowski.knowledgeTest.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    void deleteByEmail(String email);
}
