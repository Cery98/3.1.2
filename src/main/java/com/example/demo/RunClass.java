package com.example.demo;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.RoleServiceImpl;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RunClass implements CommandLineRunner {
    private RoleServiceImpl roleServiceImp;
    private UserServiceImpl userServiceImp;

    @Autowired
    public RunClass(RoleServiceImpl roleServiceImp, UserServiceImpl userServiceImp) {
        this.roleServiceImp = roleServiceImp;
        this.userServiceImp = userServiceImp;
    }


    @Override
    public void run(String... args) {
        Role userTest = new Role(1, "ROLE_USER");
        Role adminTest = new Role(2, "ROLE_ADMIN");
        roleServiceImp.addRole(userTest);
        roleServiceImp.addRole(adminTest);
        Set<Role> userSet = Stream.of(userTest).collect(Collectors.toSet());
        Set<Role> adminSet = Stream.of(adminTest, userTest).collect(Collectors.toSet());

        User user = new User("user@mail.ru","12","User",userSet);
        User admin = new User("admin@mail.ru", "12", "admin",adminSet);
        userServiceImp.save(user);
        userServiceImp.save(admin);
    }
}