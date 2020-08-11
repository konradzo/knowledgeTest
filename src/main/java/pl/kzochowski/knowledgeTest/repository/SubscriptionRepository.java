package pl.kzochowski.knowledgeTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kzochowski.knowledgeTest.model.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    List<Subscription> findAll();
}
