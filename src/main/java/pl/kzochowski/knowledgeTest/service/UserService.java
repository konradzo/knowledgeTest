package pl.kzochowski.knowledgeTest.service;

import pl.kzochowski.knowledgeTest.model.ExamApproachList;
import pl.kzochowski.knowledgeTest.model.User;
import pl.kzochowski.knowledgeTest.model.UserList;

public interface UserService {

    User createUser(User user);

    User fetchUserById(Integer id);

    User removeUserById(Integer id);

    UserList listAllUsers();

    UserList searchUsersByEmail(String query);

    ExamApproachList listExamApproaches(Integer id);

    class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException(User user) {
            super(String.format("User with email %s already exists!", user.getEmail()));
        }
    }

    class UserDoesNotExistException extends RuntimeException {
        public UserDoesNotExistException(Integer id) {
            super(String.format("User with id %d does not exist!", id));
        }
    }

    class IncorrectEmailException extends IllegalArgumentException {
        public IncorrectEmailException(String email) {
            super(String.format("Wrong email prodived: %s", email));
        }
    }
}
