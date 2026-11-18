package com.example.tweb.controller;

import com.example.tweb.model.TaiKhoan;
import com.example.tweb.repository.TaiKhoanRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    // Hiển thị trang đăng nhập
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Nằm trực tiếp trong thư mục templates
    }

    // Xử lý logic đăng nhập
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session, Model model) {
        
        TaiKhoan tk = taiKhoanRepository.findByUsernameAndPassword(username, password);
        
        if (tk != null) {
            // Đăng nhập thành công -> Lưu thông tin người dùng vào Session
            session.setAttribute("currentUser", tk);
            return "redirect:/sinhvien"; 
        } else {
            // Đăng nhập thất bại -> Báo lỗi
            model.addAttribute("error", "Sai tên đăng nhập hoặc mật khẩu!");
            return "login";
        }
    }

    // Xử lý Đăng xuất
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("currentUser"); // Xóa session
        return "redirect:/login";
    }
}