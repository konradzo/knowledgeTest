package pl.kzochowski.knowledgeTest.model;

import lombok.*;

import java.util.List;

@Data
public class SubscriptionList {
    private final long size;
    private final List<Subscription> subscriptions;
}
