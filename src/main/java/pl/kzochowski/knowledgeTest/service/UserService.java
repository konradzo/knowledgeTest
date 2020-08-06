package pl.kzochowski.knowledgeTest.service;

import pl.kzochowski.knowledgeTest.model.User;

public interface UserService {

    User createUser(User user);

    User fetchUserById(Integer id);

    User removeUserById(Integer id);

    class IncorrectEmailException extends IllegalArgumentException {
        public IncorrectEmailException(User user) {
            super(String.format("Incorrect email %s for user %s %s", user.getEmail(), user.getName(), user.getSurname()));
        }
    }

    // todo change extended exception?
    class UserAlreadyExistsException extends IllegalArgumentException {
        public UserAlreadyExistsException(User user) {
            super(String.format("User with email %s already exists!", user.getEmail()));
        }
    }

    class UserDoesNotExistException extends RuntimeException {
        public UserDoesNotExistException(Integer id) {
            super(String.format("User with id %d does not exist!", id));
        }
    }
}
