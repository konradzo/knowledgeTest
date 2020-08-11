package pl.kzochowski.knowledgeTest.service;

import pl.kzochowski.knowledgeTest.model.Subscription;

import java.util.List;

public interface SubscriptionService {

    Subscription fetchSubscriptionById(Integer id);

    Subscription fetchSubscriptionByEmail(String email);

    List<Subscription> listAllSubscriptions();

    class SubscriptionDoesNotExist extends RuntimeException {
        public SubscriptionDoesNotExist(Integer id) {
            super(String.format("Subscription with id %d does not exist", id));
        }

        public SubscriptionDoesNotExist(String email) {
            super(String.format("Subscription with email %s does not exist", email));
        }
    }
}
