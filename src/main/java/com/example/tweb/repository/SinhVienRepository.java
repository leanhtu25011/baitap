package com.example.tweb.repository;
import com.example.tweb.model.SinhVien;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SinhVienRepository extends JpaRepository<SinhVien, Long> {
    boolean existsByMaSV(String maSV); // Dùng cho Sequence kiểm tra trùng mã SV[cite: 18]
}