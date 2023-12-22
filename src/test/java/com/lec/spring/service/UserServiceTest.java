package com.lec.spring.service;

import com.lec.spring.domain.Authority;
import com.lec.spring.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void test1(){
        User user = null;

        user = userService.findByUsername("user1");
        System.out.println(user);
        user = userService.findByUsername("user2");
        System.out.println(user);
        user = userService.findByUsername("admin1");
        System.out.println(user);
        user = userService.findByUsername("admin2");
        System.out.println(user);

        User newUser = User.builder()
                .name("회원3")
                .username("user3")
                .password("1234")
                .email("user3@mail.com")
                .build();

        System.out.println("가입전: " + newUser);
        userService.register(newUser);
        System.out.println("가입후: " + newUser);



    }

    @Test
    void test2(){
        List<Authority> authorityList = userService.selectAuthoritiesById(1L);
        System.out.println(authorityList);

        authorityList = userService.selectAuthoritiesById(2L);
        System.out.println(authorityList);

        authorityList = userService.selectAuthoritiesById(3L);
        System.out.println(authorityList);
    }

}







