package entity;

public class KhachHang {
	private String maKh;
	private String tenKH;
	private String gioiTinh;
	private String soDT;
	
	public KhachHang() {
		this("","","","");
	}

	public KhachHang(String maKh, String tenKH, String gioiTinh, String soDT) {
		this.maKh = maKh;
		this.tenKH = tenKH;
		this.gioiTinh = gioiTinh;
		this.soDT = soDT;
	}

	public String getMaKH() {
		return maKh;
	}

	public void setMaKH(String maKh) {
		this.maKh = maKh;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getSoDT() {
		return soDT;
	}

	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}

	@Override
	public String toString() {
		return "KhachHang [maKh=" + maKh + ", tenKH=" + tenKH + ", gioiTinh=" + gioiTinh + ", soDT=" + soDT + "]";
	}
	
	
	
}
