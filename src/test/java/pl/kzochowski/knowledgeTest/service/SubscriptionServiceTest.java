package pl.kzochowski.knowledgeTest.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import pl.kzochowski.knowledgeTest.KnowledgeTestApplication;
import pl.kzochowski.knowledgeTest.model.Subscription;
import pl.kzochowski.knowledgeTest.model.SubscriptionList;
import pl.kzochowski.knowledgeTest.model.User;

import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest(classes = KnowledgeTestApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SubscriptionServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Test
    public void shouldCreateAndReturnSubscriptionById() {
        // given
        User user = aUser();
        userService.createUser(user);

        // when
        Subscription subscription = subscriptionService.fetchSubscriptionById(1);

        // then
        assertThat(subscription, equalTo(user.getSubscription()));
    }

    @Test
    public void shouldThrowExceptionWhenSubscriptionDoesNotExistById() {
        // then
        assertThrows(SubscriptionService.SubscriptionDoesNotExistException.class, () -> subscriptionService.fetchSubscriptionById(1));
    }

    @Test
    public void shouldReturnSubscriptionByUserEmail() {
        // given
        User firstUser = aUser();
        User secondUser = bUser();
        User thirdUser = cUser();
        userService.createUser(firstUser);
        userService.createUser(secondUser);
        userService.createUser(thirdUser);

        // when
        SubscriptionList subscriptionList = subscriptionService.fetchSubscriptionByEmail("snow");

        // then
        assertThat(subscriptionList.getSubscriptions(), hasSize(2));
        assertThat(subscriptionList.getSubscriptions(), containsInAnyOrder(secondUser.getSubscription(), thirdUser.getSubscription()));
    }

    @Test
    public void shouldReturnEmptySubscriptionListByUserEmailQuery() {
        // given
        User firstUser = aUser();
        User secondUser = bUser();
        User thirdUser = cUser();
        userService.createUser(firstUser);
        userService.createUser(secondUser);
        userService.createUser(thirdUser);

        // when
        SubscriptionList subscriptionList = subscriptionService.fetchSubscriptionByEmail("black");

        // then
        assertThat(subscriptionList.getSubscriptions(), hasSize(0));
        assertThat(subscriptionList.getSubscriptions(), not(containsInAnyOrder(secondUser.getSubscription(), thirdUser.getSubscription())));
    }

    @Test
    public void shouldReturnAllSubscriptions() {
        // given
        User firstUser = aUser();
        User secondUser = bUser();
        User thirdUser = cUser();
        userService.createUser(firstUser);
        userService.createUser(secondUser);
        userService.createUser(thirdUser);

        // when
        SubscriptionList subscriptionList = subscriptionService.listAllSubscriptions();

        // then
        assertThat(subscriptionList.getSubscriptions(), hasSize(3));
    }

    private User aUser() {
        return User.builder()
                .name("John")
                .surname("Doe")
                .email("sample.email@gmail.com")
                .build();
    }

    private User bUser() {
        return User.builder()
                .name("Jack")
                .surname("Snow")
                .email("jack.snow@gmail.com")
                .build();
    }

    private User cUser() {
        return User.builder()
                .name("Alicja")
                .surname("Snow")
                .email("alicja.snow@gmail.com")
                .build();
    }
}
