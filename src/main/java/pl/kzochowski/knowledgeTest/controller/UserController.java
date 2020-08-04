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
public class UserController {
    private final UserService userService;
    private final EmailValidator emailValidator;

    public UserController(UserService userService) {
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

    //todo deleting user, listing users

    private boolean incorrectEmailAddress(User user) {
        return !emailValidator.isValid(user.getEmail());
    }
}
