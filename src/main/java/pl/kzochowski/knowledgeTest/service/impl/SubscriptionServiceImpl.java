package pl.kzochowski.knowledgeTest.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kzochowski.knowledgeTest.model.Subscription;
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
            throw new SubscriptionDoesNotExist(id);
        return result.get();
    }

    @Override
    public Subscription fetchSubscriptionByEmail(String email) {
        //todo implement
        return null;
    }

    @Override
    public List<Subscription> listAllSubscriptions() {
        return subscriptionRepository.findAll();
    }
}
