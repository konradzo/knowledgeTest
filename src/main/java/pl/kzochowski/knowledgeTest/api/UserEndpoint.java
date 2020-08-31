package pl.kzochowski.knowledgeTest.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kzochowski.knowledgeTest.model.User;
import pl.kzochowski.knowledgeTest.model.UserList;
import pl.kzochowski.knowledgeTest.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserEndpoint {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User fetch(@PathVariable("id") Integer id) {
        return userService.fetchUserById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public User remove(@PathVariable("id") Integer id) {
        return userService.removeUserById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserList listAll() {
        return userService.listAllUsers();
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public UserList searchByEmail(@RequestParam String query) {
        return userService.searchUsersByEmail(query);
    }
}
