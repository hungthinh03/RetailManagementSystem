package dao;

import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;

public class QuanLyNhanVien_DAO {
	ArrayList<NhanVien> dsnv;
	NhanVien nv;
	public QuanLyNhanVien_DAO() {
		dsnv = new ArrayList<NhanVien>();
		nv = new NhanVien();
	}
	public ArrayList<NhanVien> getalltbNhanVien(){
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql  = "Select * from NHANVIEN";
			Statement statement =  con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maNV = rs.getString(1);
				String tenNV = rs.getString(2);
				String diaChi = rs.getString(3);
				String soDT = rs.getString(4);
				String trangThai = rs.getString(5);
				LocalDate ngaySinh = rs.getDate(6).toLocalDate();
				String vaiTro = rs.getString(7);
				String email = rs.getString(8);
				String gioiTinh = rs.getString(9);
				NhanVien s = new NhanVien(maNV,tenNV,diaChi,soDT,trangThai,ngaySinh,vaiTro,email,gioiTinh);
				dsnv.add(s);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return dsnv;
	
	}
	
	public boolean create(NhanVien nv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n =0;
		try {	
			stmt = con.prepareStatement("SELECT COUNT(*) FROM NHANVIEN WHERE MaNV = ?");
			stmt.setString(1,nv.getMaNV());
			  ResultSet rs = stmt.executeQuery();
			  if (rs.next() && rs.getInt(1) > 0) {
		            return false;
		        }
			
			
			stmt = con.prepareStatement("insert into"+" NHANVIEN values (?,?,?,?,?,?,?,?,?)");
			stmt.setString(1,nv.getMaNV());
			stmt.setString(2,nv.getTenNV());
			stmt.setString(3,nv.getDiaChi());
			stmt.setString(4,nv.getSoDT());
			stmt.setString(5, nv.getTrangThai());
			stmt.setDate(6, Date.valueOf(nv.getNgaySinh()));
			stmt.setString(7, nv.getVaiTro());
			stmt.setString(8, nv.getEmail());
			stmt.setString(9,nv.getGioiTinh());
			n = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
	
	public boolean delete(String manv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("DELETE FROM NHANVIEN WHERE maNV = ?");
			stmt.setString(1, manv);
			n = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
	
	public boolean update(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null; 
		int n=0;
		try {
			stmt = con.prepareStatement("UPDATE NHANVIEN set tenNV = ?, diaChi = ?, soDT = ?, trangThai = ?, ngaySinh =?, vaiTro = ?, email = ?, gioiTinh = ? where maNV =?");
			stmt.setString(1,nv.getTenNV());
			stmt.setString(2,nv.getDiaChi());
			stmt.setString(3,nv.getSoDT());
			stmt.setString(4,nv.getTrangThai());
			stmt.setString(5,nv.getNgaySinh().toString());
			stmt.setString(6,nv.getVaiTro());
			stmt.setString(7, nv.getEmail());
			stmt.setString(8, nv.getGioiTinh());
			stmt.setString(9, nv.getMaNV());
			n = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
	
	public NhanVien search(String maNV) {
		NhanVien nv = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("SELECT * from NHANVIEN WHERE maNV = ?");
			stmt.setString(1, maNV);
			ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	        	String ma = rs.getString(1);
				String tenNV = rs.getString(2);
				String diaChi = rs.getString(3);
				String soDT = rs.getString(4);
				String trangThai = rs.getString(5);
				LocalDate ngaySinh = rs.getDate(6).toLocalDate();
				String vaiTro = rs.getString(7);
				String email = rs.getString(8);
				String gioiTinh = rs.getString(9);
				nv = new NhanVien(ma,tenNV,diaChi,soDT,trangThai,ngaySinh,vaiTro,email,gioiTinh);
				return nv;
	        

		}
		}catch(SQLException e) {
		e.printStackTrace();
		}
		return nv;
	}
	
	public NhanVien searchTen(String ten) {
		NhanVien nv = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("SELECT * from NHANVIEN WHERE tenNV = ?");
			stmt.setString(1, ten);
			ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	        	String ma = rs.getString(1);
				String tenNV = rs.getString(2);
				String diaChi = rs.getString(3);
				String soDT = rs.getString(4);
				String trangThai = rs.getString(5);
				LocalDate ngaySinh = rs.getDate(6).toLocalDate();
				String vaiTro = rs.getString(7);
				String email = rs.getString(8);
				String gioiTinh = rs.getString(9);
				nv = new NhanVien(ma,tenNV,diaChi,soDT,trangThai,ngaySinh,vaiTro,email,gioiTinh);
				return nv;
		}
		}catch(SQLException e) {
		e.printStackTrace();
		}
		return nv;
	}
}
