package com.icolak.controller;

import com.icolak.dto.UserDTO;
import com.icolak.service.RoleService;
import com.icolak.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final RoleService roleService;
    private final UserService userService;

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createUser(Model model) {

        model.addAttribute("user", new UserDTO());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleService.findAll());

        return "/user/create";
    }

    @PostMapping("/create")
    public String insertUser(@ModelAttribute UserDTO user) {

        userService.save(user);

        return "redirect:/user/create";
    }
}
