package com.example.tweb.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHomePage(HttpSession session) {
        // Kiểm tra gác cổng: Chưa đăng nhập thì đẩy ra trang login
        if (session.getAttribute("currentUser") == null) {
            return "redirect:/login";
        }
        return "index"; // Trả về giao diện Dashboard
    }
}