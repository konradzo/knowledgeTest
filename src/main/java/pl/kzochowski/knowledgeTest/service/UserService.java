package pl.kzochowski.knowledgeTest.service;

import pl.kzochowski.knowledgeTest.model.User;

public interface UserService {

    User createUser(User user);

    public class IncorrectEmailException extends IllegalArgumentException {
        public IncorrectEmailException(User user) {
            super(String.format("Incorrect email %s for user %s %s", user.getEmail(), user.getName(), user.getSurname()));
        }
    }

}
