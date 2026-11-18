package com.example.tweb.controller;

import com.example.tweb.model.LopHoc;
import com.example.tweb.model.SinhVien;
import com.example.tweb.model.TaiKhoan;
import com.example.tweb.repository.LopHocRepository;
import com.example.tweb.repository.SinhVienRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/sinhvien")
public class SinhVienController {

    @Autowired
    private SinhVienRepository sinhVienRepository;
    
    @Autowired
    private LopHocRepository lopHocRepository;

    // Hàm phụ trợ: Kiểm tra xem người dùng có phải ADMIN không
    private boolean isAdmin(HttpSession session) {
        TaiKhoan currentUser = (TaiKhoan) session.getAttribute("currentUser");
        return currentUser != null && "ADMIN".equals(currentUser.getRole());
    }

    // 1. Xem danh sách sinh viên (Bắt buộc đăng nhập, nhưng ai cũng xem được)
    @GetMapping
    public String listSinhVien(Model model, HttpSession session) {
        if (session.getAttribute("currentUser") == null) return "redirect:/login";
        
        model.addAttribute("listSinhVien", sinhVienRepository.findAll());
        return "sinhvien/list"; 
    }

    // 2. Hiển thị form thêm mới (CHỈ ADMIN)
    @GetMapping("/add")
    public String showAddForm(Model model, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/sinhvien";

        SinhVien sv = new SinhVien();
        sv.setLopHoc(new LopHoc());
        
        model.addAttribute("sinhVien", sv);
        model.addAttribute("listLopHoc", lopHocRepository.findAll()); 
        return "sinhvien/add";
    }

    // 3. Xử lý lưu sinh viên mới (CHỈ ADMIN)
    @PostMapping("/save")
    public String saveSinhVien(@ModelAttribute("sinhVien") SinhVien sinhVien, RedirectAttributes redirectAttributes, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/sinhvien";

        if (sinhVienRepository.existsByMaSV(sinhVien.getMaSV())) {
            redirectAttributes.addFlashAttribute("error", "Mã sinh viên đã tồn tại!");
        } else {
            sinhVienRepository.save(sinhVien);
            redirectAttributes.addFlashAttribute("success", "Thêm sinh viên thành công!");
        }
        return "redirect:/sinhvien";
    }

    // 4. Hiển thị form sửa thông tin (CHỈ ADMIN)
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/sinhvien";

        SinhVien sinhVien = sinhVienRepository.findById(id).orElse(null);
        if (sinhVien != null) {
            model.addAttribute("sinhVien", sinhVien);
            model.addAttribute("listLopHoc", lopHocRepository.findAll());
            return "sinhvien/edit";
        }
        return "redirect:/sinhvien";
    }

    // 5. Xử lý cập nhật thông tin (CHỈ ADMIN)
    @PostMapping("/update/{id}")
    public String updateSinhVien(@PathVariable("id") Long id, @ModelAttribute("sinhVien") SinhVien sinhVienForm, RedirectAttributes redirectAttributes, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/sinhvien";

        SinhVien existingStudent = sinhVienRepository.findById(id).orElse(null);
        if (existingStudent != null) {
            existingStudent.setHoTen(sinhVienForm.getHoTen());
            existingStudent.setNgaySinh(sinhVienForm.getNgaySinh());
            existingStudent.setGioiTinh(sinhVienForm.getGioiTinh());
            existingStudent.setEmail(sinhVienForm.getEmail());
            existingStudent.setSoDienThoai(sinhVienForm.getSoDienThoai());
            existingStudent.setDiaChi(sinhVienForm.getDiaChi());
            existingStudent.setDiemTB(sinhVienForm.getDiemTB());
            existingStudent.setLopHoc(sinhVienForm.getLopHoc());
            
            sinhVienRepository.save(existingStudent);
            redirectAttributes.addFlashAttribute("success", "Cập nhật thành công!");
        }
        return "redirect:/sinhvien";
    }

    // 6. Xử lý xóa sinh viên (CHỈ ADMIN)
    @GetMapping("/delete/{id}")
    public String deleteSinhVien(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/sinhvien";

        if (!sinhVienRepository.existsById(id)) {
            redirectAttributes.addFlashAttribute("error", "Sinh viên không tồn tại!");
        } else {
            sinhVienRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Đã xóa sinh viên thành công!");
        }
        return "redirect:/sinhvien";
    }
}