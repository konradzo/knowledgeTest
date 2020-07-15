package pl.kzochowski.knowledgeTest.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kzochowski.knowledgeTest.model.Subscription;
import pl.kzochowski.knowledgeTest.model.User;
import pl.kzochowski.knowledgeTest.repository.UserRepository;
import pl.kzochowski.knowledgeTest.service.UserService;

import java.time.LocalDateTime;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //todo dao??
    @Override
    public User createUser(User user) {
        Subscription subscription = Subscription.builder()
                .user(user)
                .activeUntil(LocalDateTime.now().plusYears(1))
                .build();
        user.setSubscription(subscription);

        userRepository.save(user);
        log.info("New user created. Email: {}, creation date {}", user.getEmail(), user.getCreateAt());
        return user;
    }
}
