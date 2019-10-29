package com.kk.springsecuritymybatis.controller;

import com.kk.springsecuritymybatis.pojo.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("user", getUsername());
        model.addAttribute("role", getAuthority());
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("user", getUsername());
        model.addAttribute("role", getAuthority());
        return "admin";
    }

    @RequestMapping("/dba")
    public String dbaPage(Model model) {
        model.addAttribute("user", getUsername());
        model.addAttribute("role", getAuthority());
        return "dba";
    }

    @RequestMapping("/accessDenied")
    public String den(Model model) {
        model.addAttribute("user", getUsername());
        model.addAttribute("role", getAuthority());
        return "accessDenied";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);

        }
        return "redirect:/login?logout";
    }




    private String getUsername() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(username);
        return username;
    }

    private String getAuthority () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority a: authentication.getAuthorities()) {
            roles.add(a.getAuthority());
        }
        System.out.println(roles);
        return roles.toString();
    }

}
