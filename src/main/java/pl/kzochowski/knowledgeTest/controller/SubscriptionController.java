package pl.kzochowski.knowledgeTest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kzochowski.knowledgeTest.model.Subscription;
import pl.kzochowski.knowledgeTest.model.SubscriptionList;
import pl.kzochowski.knowledgeTest.service.SubscriptionService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Subscription fetchSubscription(@PathVariable Integer id) {
        return subscriptionService.fetchSubscriptionById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    SubscriptionList listSubscriptions() {
        List<Subscription> subscriptions = subscriptionService.listAllSubscriptions();
        log.info("Listing subscriptions list, size: {}", subscriptions.size());
        return new SubscriptionList(subscriptions);
    }
}
