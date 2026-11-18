package com.example.tweb.repository;
import com.example.tweb.model.LopHoc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LopHocRepository extends JpaRepository<LopHoc, Long> {
    boolean existsByMaLop(String maLop); // Dùng cho Sequence thêm lớp học[cite: 22]
}
