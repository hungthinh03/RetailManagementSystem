package entity;

import java.time.LocalDate;

public class HoaDon {
	private String maHD;
    private LocalDate ngayLap;
    private NhanVien nhanVien;
    private KhachHang khachHang;
    
	public HoaDon() {
		
		this("",LocalDate.now(),null,null);
	}
    public HoaDon(String maHD) {
		super();
		this.maHD = maHD;
	}

	public HoaDon(String maHD, LocalDate ngayLap, NhanVien nhanVien, KhachHang khachHang) {
        this.maHD = maHD;
        this.ngayLap = ngayLap;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
        this.ngayLap = ngayLap;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }
	
	

}
