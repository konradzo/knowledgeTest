package pl.kzochowski.knowledgeTest.controller;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kzochowski.knowledgeTest.model.User;
import pl.kzochowski.knowledgeTest.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    User createUser(@Valid @RequestBody User newUser) {
        if (incorrectEmailAddress(newUser))
            throw new UserService.IncorrectEmailException(newUser);
        return userService.createUser(newUser);
    }

    private boolean incorrectEmailAddress(User user) {
        //todo email validation
        return false;
    }
}
