package pl.kzochowski.knowledgeTest.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kzochowski.knowledgeTest.model.User;
import pl.kzochowski.knowledgeTest.service.UserService;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    //todo dao??
    @Override
    public User createUser(User user) {

        log.info("New user created. Email: {}, creation date {}", user.getEmail(), user.getCreateAt());
        return null;
    }
}
