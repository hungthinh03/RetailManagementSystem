package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.NhanVien;

public class QuanLyKhachHang_DAO {
	ArrayList<KhachHang> dskh; 
	KhachHang kh;
	
	public QuanLyKhachHang_DAO() {
		dskh = new ArrayList<KhachHang>()	;
		kh = new KhachHang();
	}
	
	public ArrayList<KhachHang> gettalltbKhachHang(){
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from KHACHHANG";
			Statement statement = con.createStatement();
			ResultSet  rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maKH  = rs.getString(1);
				String hoTen = rs.getString(2);
				String gioiTinh = rs.getString(3);
				String sdt = rs.getString(4);
				KhachHang kh = new KhachHang(maKH,hoTen,gioiTinh,sdt);
				dskh.add(kh);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return dskh;
	}
	
	public boolean create(KhachHang kh) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into KHACHHANG values (?,?,?,?)");
			stmt.setString(1,kh.getMaKH());
			stmt.setString(2,kh.getTenKH());
			stmt.setString(3, kh.getGioiTinh());
			stmt.setString(4, kh.getSoDT());
			n = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
	
	public boolean delete (String maKH) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from KHACHHANG where maKH = ?");
			stmt.setString(1,maKH);
			n = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
	
	public boolean update (KhachHang kh) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n  = 0;
		try {
			stmt = con.prepareStatement("update KHACHHANG set tenKH = ?, gioiTinh = ?, soDT = ? where maKH = ?");
			stmt.setString(1, kh.getTenKH());
			stmt.setString(2,kh.getGioiTinh());
			stmt.setString(3,kh.getSoDT());
			stmt.setString(4,kh.getMaKH());
			n = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
	
	public KhachHang search(String maNV) {
		KhachHang kh = null;
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("Select * from KHACHHANG where maKH = ?");
			stmt.setString(1, maNV);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String maKH = rs.getString(1);
				String tenKh = rs.getString(2);
				String gioiTinh = rs.getString(3);
				String soDt = rs.getString(4);
				kh = new KhachHang(maKH,tenKh,gioiTinh,soDt);
				return kh;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return kh;
	}
	
	
	public boolean kiemtraKhachHangTonTai(String makh) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
	
		try {
			stmt = con.prepareStatement("select count(*) from KHACHHANG where maKH = ?");
			stmt.setString(1, makh);
			ResultSet rs = stmt.executeQuery();
			if (rs.next() && rs.getInt(1) > 0) {
				return true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public KhachHang searchSDT(String sdt) {
		KhachHang kh = null;
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("Select * from KHACHHANG where soDT = ?");
			stmt.setString(1, sdt);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String maKH = rs.getString(1);
				String tenKh = rs.getString(2);
				String gioiTinh = rs.getString(3);
				String soDt = rs.getString(4);
				kh = new KhachHang(maKH,tenKh,gioiTinh,soDt);
				return kh;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return kh;
	}
	
}
