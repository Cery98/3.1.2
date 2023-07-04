package com.example.demo.controller;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {


    private final UserServiceImpl userServiceImpl;

    private final RoleRepository roleRepository;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, RoleRepository roleRepository) {
        this.userServiceImpl = userServiceImpl;
        this.roleRepository = roleRepository;
    }

    @GetMapping()
    public String allUsers(Model model, Principal principal) {

        principal.getName();
        model.addAttribute("users", userServiceImpl.allUsers());
        return "allUsers";
    }

    @GetMapping("/{id}")
    public String singleUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userServiceImpl.findOne(id));
        return "singleUser";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute User user, Model model) {

        List<Role> list = roleRepository.findAll();
        model.addAttribute("allRoles", list);
        return "newUser";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        System.out.println(user);
        if (bindingResult.hasErrors()) {
            System.out.println("ERORRRRRRRRRRRRRRRRRRRRRRRRRRRRRRREEEEEEEEERRRRRRRRRRRRR");
            return "/newUser";
        }

        List<Role> list = roleRepository.findAll();
        model.addAttribute("allRoles", list);

        userServiceImpl.save(user);
        System.out.println("SAAAAAAAAAAAAAAAAAVVVVVVVVVVVVVVVVVVVVVVEEEEEEEEEEEEEEEEEEEEEE");
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userServiceImpl.findOne(id));
        return "updateUser";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                             @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "updateUser";
        }
        System.out.println(user);
        userServiceImpl.update(user, id);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userServiceImpl.delete(id);
        return "redirect:/admin";
    }
}
