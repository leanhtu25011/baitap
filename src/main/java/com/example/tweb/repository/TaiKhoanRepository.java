package com.example.tweb.repository;

import com.example.tweb.model.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Long> {
    // Hàm này tương đương: SELECT * FROM tai_khoan WHERE username = ? AND password = ?
    TaiKhoan findByUsernameAndPassword(String username, String password);
}