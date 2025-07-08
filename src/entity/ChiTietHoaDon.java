package entity;

public class ChiTietHoaDon {
    private HoaDon hoaDon; 
    private SanPham sanPham;
    private int giaBan;
    private int soLuong;

    public ChiTietHoaDon(HoaDon hoaDon, SanPham sanPham, int giaBan, int soLuong) {
        this.hoaDon = hoaDon;
        this.sanPham = sanPham;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    // Getter and Setter for giaBan
    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    // Getter and Setter for soLuong
    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}

