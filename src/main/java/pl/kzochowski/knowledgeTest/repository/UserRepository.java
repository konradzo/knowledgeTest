package pl.kzochowski.knowledgeTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kzochowski.knowledgeTest.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
