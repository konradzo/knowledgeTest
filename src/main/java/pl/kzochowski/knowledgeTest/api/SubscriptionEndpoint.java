package pl.kzochowski.knowledgeTest.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kzochowski.knowledgeTest.model.Subscription;
import pl.kzochowski.knowledgeTest.model.SubscriptionList;
import pl.kzochowski.knowledgeTest.service.SubscriptionService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionEndpoint {
    private final SubscriptionService subscriptionService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Subscription fetch(@PathVariable Integer id) {
        return subscriptionService.fetchSubscriptionById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    SubscriptionList listAll() {
        return subscriptionService.listAllSubscriptions();
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    SubscriptionList searchByEmail(@RequestParam String query){
        return subscriptionService.fetchSubscriptionByEmail(query);
    }
}
