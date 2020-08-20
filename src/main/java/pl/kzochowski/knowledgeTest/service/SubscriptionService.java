package pl.kzochowski.knowledgeTest.service;

import pl.kzochowski.knowledgeTest.model.Subscription;
import pl.kzochowski.knowledgeTest.model.SubscriptionList;

import java.util.List;

public interface SubscriptionService {

    Subscription fetchSubscriptionById(Integer id);

    SubscriptionList fetchSubscriptionByEmail(String email);

    SubscriptionList listAllSubscriptions();

    class SubscriptionDoesNotExistException extends RuntimeException {
        public SubscriptionDoesNotExistException(Integer id) {
            super(String.format("Subscription with id %d does not exist", id));
        }

        public SubscriptionDoesNotExistException(String email) {
            super(String.format("Subscription with email %s does not exist", email));
        }
    }
}
