package pl.kzochowski.knowledgeTest.model;

import lombok.Data;

import java.util.List;

@Data
public class UserList {
    private final long size;
    private final List<User> users;
}
