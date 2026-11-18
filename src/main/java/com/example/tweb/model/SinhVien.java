package com.example.tweb.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sinh_vien")
public class SinhVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //[cite: 14]

    @Column(name = "ma_sv", unique = true, nullable = false, length = 20)
    private String maSV; //[cite: 14]

    @Column(name = "ho_ten", nullable = false, length = 100)
    private String hoTen; //[cite: 14]

    @Column(name = "ngay_sinh", nullable = false)
    private LocalDate ngaySinh; //[cite: 14]

    @Column(name = "gioi_tinh", nullable = false, length = 10)
    private String gioiTinh; //[cite: 14]

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMaSV() {
		return maSV;
	}

	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public Double getDiemTB() {
		return diemTB;
	}

	public void setDiemTB(Double diemTB) {
		this.diemTB = diemTB;
	}

	public LopHoc getLopHoc() {
		return lopHoc;
	}

	public void setLopHoc(LopHoc lopHoc) {
		this.lopHoc = lopHoc;
	}

	@Column(nullable = false, length = 100)
    private String email; //[cite: 14]

    @Column(name = "so_dien_thoai", length = 15)
    private String soDienThoai; //[cite: 14]

    @Column(name = "dia_chi", length = 255)
    private String diaChi; //[cite: 14]

    @Column(name = "diem_tb")
    private Double diemTB = 0.0; //[cite: 14]

    @ManyToOne
    @JoinColumn(name = "lop_id")
    private LopHoc lopHoc; // Quan hệ 1-N với bảng Lớp học[cite: 13, 14]

    // (Bạn tự Generate Getters và Setters cho tất cả các biến ở đây)
}