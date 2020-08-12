package pl.kzochowski.knowledgeTest.controller;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kzochowski.knowledgeTest.model.User;
import pl.kzochowski.knowledgeTest.service.UserService;
import pl.kzochowski.knowledgeTest.service.UserService.IncorrectEmailException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserEndpoint {
    //todo find user by email
    //todo deleting user by email, listing users
    private final UserService userService;
    private final EmailValidator emailValidator;

    public UserEndpoint(UserService userService) {
        this.userService = userService;
        this.emailValidator = EmailValidator.getInstance();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    User createUser(@Valid @RequestBody User newUser) {
        if (incorrectEmailAddress(newUser))
            throw new IncorrectEmailException(newUser);
        return userService.createUser(newUser);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    User fetchUser(@PathVariable("id") Integer id) {
        return userService.fetchUserById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    User removeUser(@PathVariable("id") Integer id) {
        return userService.removeUserById(id);
    }

    private boolean incorrectEmailAddress(User user) {
        return !emailValidator.isValid(user.getEmail());
    }
}
