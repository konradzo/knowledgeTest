package pl.kzochowski.knowledgeTest.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;
import pl.kzochowski.knowledgeTest.model.Subscription;
import pl.kzochowski.knowledgeTest.model.User;
import pl.kzochowski.knowledgeTest.model.UserList;
import pl.kzochowski.knowledgeTest.repository.UserRepository;
import pl.kzochowski.knowledgeTest.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MailSenderService mailSenderService;
    private final EmailValidator emailValidator;

    @Override
    public User createUser(User user) {
        validProvidedEmail(user);

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
    public User fetchUserById(Integer id) {
        Optional<User> result = userRepository.findById(id);
        if (!result.isPresent())
            throw new UserDoesNotExistException(id);

        return result.get();
    }

    @Override
    public User removeUserById(Integer id) {
        Optional<User> result = userRepository.findById(id);
        if (!result.isPresent())
            throw new UserDoesNotExistException(id);

        userRepository.delete(result.get());
        log.info("User with id {} removed", id);
        return result.get();
    }

    @Override
    public UserList listAllUsers() {
        List<User> users = userRepository.findAll();
        log.info("Found users list size: {}", users.size());
        return new UserList(users.size(), users);
    }

    private boolean checkIfUserAlreadyExists(String email) {
        Optional<User> tempUser = userRepository.findByEmail(email);
        if (tempUser.isPresent()) {
            log.info("User with email {} already exists!", tempUser.get().getEmail());
            return true;
        }
        return false;
    }

    private void validProvidedEmail(User user) {
        boolean check = emailValidator.isValid(user.getEmail());
        if (!check)
            throw new IncorrectEmailException(user.getEmail());

        log.info("Provided email {} is correct", user.getEmail());
    }
}
