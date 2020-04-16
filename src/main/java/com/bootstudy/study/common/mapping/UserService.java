package com.bootstudy.study.common.mapping;

import com.bootstudy.study.common.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User create(User user) {
        // db넣는 작업
        return user;
    }
}
