package com.example.tweb.model;
import jakarta.persistence.*;

@Entity
@Table(name = "lop_hoc")
public class LopHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //[cite: 15]

    @Column(name = "ma_lop", unique = true, nullable = false, length = 20)
    private String maLop; //[cite: 15]

    @Column(name = "ten_lop", nullable = false, length = 100)
    private String tenLop; //[cite: 15]

    @Column(nullable = false, length = 100)
    private String khoa; //[cite: 15]

    @Column(name = "khoa_hoc", length = 20)
    private String khoaHoc = "2022-2026"; //[cite: 15]

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMaLop() {
		return maLop;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	public String getTenLop() {
		return tenLop;
	}

	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}

	public String getKhoa() {
		return khoa;
	}

	public void setKhoa(String khoa) {
		this.khoa = khoa;
	}

	public String getKhoaHoc() {
		return khoaHoc;
	}

	public void setKhoaHoc(String khoaHoc) {
		this.khoaHoc = khoaHoc;
	}

    // (Bạn tự Generate Getters và Setters)
}