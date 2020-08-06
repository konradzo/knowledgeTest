package pl.kzochowski.knowledgeTest.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kzochowski.knowledgeTest.model.Subscription;
import pl.kzochowski.knowledgeTest.model.User;
import pl.kzochowski.knowledgeTest.repository.UserRepository;
import pl.kzochowski.knowledgeTest.service.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MailSenderService mailSenderService;

    public UserServiceImpl(UserRepository userRepository, MailSenderService mailSenderService) {
        this.userRepository = userRepository;
        this.mailSenderService = mailSenderService;
    }

    @Override
    public User createUser(User user) {
        if (checkIfUserAlreadyExists(user.getEmail()))
            throw new UserAlreadyExistsException(user);

        Subscription subscription = Subscription.builder()
                .user(user)
                .activeUntil(LocalDateTime.now().plusYears(1))
                .build();
        user.setSubscription(subscription);

        userRepository.save(user);
        mailSenderService.sendNewAccountEmail(user.getEmail());
        log.info("New user created. Email: {}, creation date {}", user.getEmail(), user.getCreateAt());
        return user;
    }

    @Override
    public User fetchUserByEmail(String email) {
        Optional<User> result = userRepository.findByEmail(email);
        if (!result.isPresent())
            throw new UserDoesNotExistException(email);

        return result.get();
    }

    @Override
    public User removeUser(String email) {
        Optional<User> result = userRepository.findByEmail(email);
        if (!result.isPresent())
            throw new UserDoesNotExistException(email);

        userRepository.deleteByEmail(email);
        log.info("User {} removed", email);
        return result.get();
    }

    private boolean checkIfUserAlreadyExists(String email) {
        Optional<User> tempUser = userRepository.findByEmail(email);
        if (tempUser.isPresent()) {
            log.info("User with email {} already exists!", tempUser.get().getEmail());
            return true;
        }
        return false;
    }
}
