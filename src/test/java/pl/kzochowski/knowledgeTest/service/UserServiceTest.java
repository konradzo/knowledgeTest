package pl.kzochowski.knowledgeTest.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import pl.kzochowski.knowledgeTest.KnowledgeTestApplication;
import pl.kzochowski.knowledgeTest.model.Subscription;
import pl.kzochowski.knowledgeTest.model.User;
import pl.kzochowski.knowledgeTest.model.UserList;

import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest(classes = KnowledgeTestApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void shouldCreateAndReturnUserById() {
        // given
        User user = aUser();

        // when
        userService.createUser(user);
        User result = userService.fetchUserById(user.getId());

        // then
        assertThat(result, equalTo(user));
    }

    @Test
    public void shouldThrowExceptionWhenUserAlreadyExists() {
        // given
        User user = aUser();
        userService.createUser(user);

        // then
        assertThrows(UserService.UserAlreadyExistsException.class, () -> userService.createUser(user));
    }

    @Test
    public void shouldThrowExceptionWhenUserDoesNotFoundById() {
        // then
        assertThrows(UserService.UserDoesNotExistException.class, () -> userService.fetchUserById(1));
    }

    @Test
    public void shouldReturnAllUsers() {
        // given
        User firstUser = aUser();
        User secondUser = bUser();
        userService.createUser(firstUser);
        userService.createUser(secondUser);

        // when
        UserList userList = userService.listAllUsers();

        // then
        assertThat(userList.getUsers(), hasSize(2));
        assertThat(userList.getUsers(), containsInAnyOrder(firstUser, secondUser));
    }

    @Test
    public void shouldReturnUsersByEmailQuery(){
        // given
        User firstUser = aUser();
        User secondUser = bUser();
        User thirdUser = cUser();
        userService.createUser(firstUser);
        userService.createUser(secondUser);
        userService.createUser(thirdUser);

        // when
        UserList userList = userService.searchUsersByEmail("snow");

        // then
        assertThat(userList.getUsers(), hasSize(2));
        assertThat(userList.getUsers(), containsInAnyOrder(secondUser, thirdUser));
    }

    private User aUser() {
        return User.builder()
                .name("John")
                .surname("Doe")
                .email("sample.email@gmail.com")
                .build();
    }

    private User bUser() {
        return User.builder()
                .name("Jack")
                .surname("Snow")
                .email("jack.snow@gmail.com")
                .build();
    }

    private User cUser(){
        return User.builder()
                .name("Alicja")
                .surname("Snow")
                .email("alicja.snow@gmail.com")
                .build();
    }
}
