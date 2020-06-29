/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twb.springboot.ldap.controller;

import com.twb.springboot.ldap.db.User;
import com.twb.springboot.ldap.db.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author addico
 */
@Controller
public class AppController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @RequestMapping(value = "/login")
    public String login() {
        return "signin";
    }
    
    @RequestMapping(value = "/home")
    public String home() {
        return "index";
    }
    
    @RequestMapping(value = "/")
    public String home1() {
        return "index";
    }

    @GetMapping("/user")
    public ModelAndView getForm(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user-form");
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping("/user")
    public String saveUser(@ModelAttribute("user") User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        ModelAndView mav = new ModelAndView();
        mav.addObject("success", "SUCCESS");
        return "index";
    }
}
