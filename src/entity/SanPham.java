package entity;

import java.time.LocalDate;

public class SanPham {
	private String maSP;
	private String tenSP;
	private int donGia;
	private String tinhTrang;
	private LocalDate ngaySX;
	private LoaiSanPham loaiSP;

	
	public SanPham() {
		this("", "", 0,  "",LocalDate.now(),null );
	}


	public SanPham(String maSP, String tenSP, int donGia, String tinhTrang, LocalDate ngaySX, LoaiSanPham loaiSP) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.donGia = donGia;
		this.tinhTrang = tinhTrang;
		this.ngaySX = ngaySX;
		this.loaiSP = loaiSP;
	}


	public String getMaSP() {
		return maSP;
	}


	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}


	public String getTenSP() {
		return tenSP;
	}


	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}


	public int getDonGia() {
		return donGia;
	}
	
	public int tinhGiaBan() {
		String[] loai= {"LSP01","LSP02","LSP03","LSP04","LSP05",};
		double[] loi= {0.5,0.6,0.5,0.4,0.5};
		double tangGia= 0;
		//
		for (int i = 0; i < loai.length; i++) {
		    if (loai[i].equals(this.getLoaiSP().getMaLoai())) {
		        tangGia = loi[i];
		        break;
		    }
		}
		int giaBan = (int) (Math.ceil((donGia+donGia*tangGia) / 1000.0) * 1000); //Làm tròn về 1000
		return giaBan;
	}


	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}


	public String getTinhTrang() {
		return tinhTrang;
	}


	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}


	public LocalDate getNgaySX() {
		return ngaySX;
	}


	public void setNgaySX(LocalDate ngaySX) {
		this.ngaySX = ngaySX;
	}


	public LoaiSanPham getLoaiSP() {
		return loaiSP;
	}


	public void setLoaiSP(LoaiSanPham loaiSP) {
		this.loaiSP = loaiSP;
	}


	@Override
	public String toString() {
		return "SanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", donGia=" + donGia + ", tinhTrang=" + tinhTrang
				+ ", ngaySX=" + ngaySX + ", loaiSP=" + loaiSP + "]";
	}


	
	

	

}
