package com.app.InventorySystem.controller;

import com.app.InventorySystem.model.User;
import com.app.InventorySystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String homepage() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/register")
    public String getUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String postUserForm(@Valid User user, BindingResult bindingResult) {
        if (userService.getUserByEmail(user.getEmail()).isPresent()) {
            bindingResult.rejectValue("email", null, "There is already an mentee account registered with that email");
        }
        if (userService.getUserByUsername(user.getUsername()).isPresent()) {
            bindingResult.rejectValue("username", null, "There is already an user account registered with that username");
        }
        user.setRole("ROLE_USER");

        if (bindingResult.hasErrors()) {
            return "register";
        } else {
            userService.saveUser(user);
            return "redirect:/login";
        }
    }
}