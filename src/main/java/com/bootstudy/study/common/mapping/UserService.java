package com.bootstudy.study.common.mapping;

import com.bootstudy.study.common.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();

    public User create(User user) {
        // db넣는 작업
        return user;
    }

    public List<User> findAll() {
        return users;
    }
}
