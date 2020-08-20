package pl.kzochowski.knowledgeTest.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kzochowski.knowledgeTest.model.Subscription;
import pl.kzochowski.knowledgeTest.model.SubscriptionList;
import pl.kzochowski.knowledgeTest.repository.SubscriptionRepository;
import pl.kzochowski.knowledgeTest.service.SubscriptionService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription fetchSubscriptionById(Integer id) {
        Optional<Subscription> result = subscriptionRepository.findById(id);
        if (!result.isPresent())
            throw new SubscriptionDoesNotExistException(id);
        log.info("Fetched subscription: {}", result.get().getUser().getEmail());
        return result.get();
    }

    @Override
    public SubscriptionList fetchSubscriptionByEmail(String email) {
        log.info(String.format("Searching subscriptions by user email, query: \"%s\"", email));
        List<Subscription> subscriptions = subscriptionRepository.findByUser_EmailContainingIgnoreCase(email);
        log.info("Found subscriptions list size: {}", subscriptions.size());
        return new SubscriptionList(subscriptions.size(), subscriptions);
    }

    @Override
    public SubscriptionList listAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        log.info("Found subscriptions list size: {}", subscriptions.size());
        return new SubscriptionList(subscriptions.size(), subscriptions);
    }
}
