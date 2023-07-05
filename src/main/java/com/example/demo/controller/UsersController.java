package com.example.demo.controller;

import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UsersController {


    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UsersController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping()
    public String showThisUser(ModelMap model, Principal principal) {
        model.addAttribute("users", userServiceImpl.findByEmail(principal.getName()));
        return "singleUser";
    }


}