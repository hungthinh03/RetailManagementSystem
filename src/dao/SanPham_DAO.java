package dao;

import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiSanPham;
import entity.SanPham;

public class SanPham_DAO {
	ArrayList<SanPham> listsp;
	SanPham sp;
	public SanPham_DAO() {
		listsp = new ArrayList<SanPham>();
		sp = new SanPham();
	}
		public ArrayList<SanPham> getalltbSanPham(){
			try {
				ConnectDB.getInstance();
				Connection con = ConnectDB.getConnection();
				String sql  = "Select  s.maSP, s.tenSP, s.donGia, s.tinhTrang, s.ngaySX,l.maLoai, l.tenLoai from SANPHAM s inner join LOAISANPHAM l ON s.maLoai=l.maLoai ";
				Statement statement =  con.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				while (rs.next()) {
					String maSP = rs.getString("maSP");
					String tenSP = rs.getString("tenSP");
					int  donGia = rs.getInt("donGia");
					String tinhTrang= rs.getString("tinhTrang");
					LocalDate ngaySX = rs.getDate("ngaySX").toLocalDate();
					LoaiSanPham loaiSP = new LoaiSanPham(rs.getString("maLoai"),rs.getString("tenLoai"));
					SanPham s = new SanPham(maSP,tenSP,donGia,tinhTrang,ngaySX,loaiSP);
					listsp.add(s);
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return listsp;
		
		}
	
	public boolean create(SanPham sp) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n =0;
		try {	
			stmt = con.prepareStatement("SELECT COUNT(*) FROM SANPHAM WHERE MaSP = ?");
			stmt.setString(1,sp.getMaSP());
			  ResultSet rs = stmt.executeQuery();
			  if (rs.next() && rs.getInt(1) > 0) {
		            return false;
		        }
			

			    stmt = con.prepareStatement("INSERT INTO SANPHAM (maSP, tenSP, donGia, maLoai, tinhTrang, ngaySX) VALUES (?, ?, ?, ?, ?, ?)");
			    stmt.setString(1, sp.getMaSP());
			    stmt.setString(2, sp.getTenSP());
			    stmt.setInt(3, sp.getDonGia());
			    stmt.setString(4, sp.getLoaiSP().getMaLoai()); // Đảm bảo giá trị này không NULL
			    stmt.setString(5, sp.getTinhTrang());
			    stmt.setDate(6, Date.valueOf(sp.getNgaySX()));
			n = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
	
	public boolean delete(String masp) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("DELETE FROM SANPHAM WHERE maSP= ?");
			stmt.setString(1, masp);
			n = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
	
	public boolean update(SanPham sp) {
	    ConnectDB.getInstance();
	    Connection con = ConnectDB.getConnection();
	    PreparedStatement stmt = null; 
	    int n = 0;
	    try {
	        stmt = con.prepareStatement("UPDATE SANPHAM SET tenSP = ?, donGia = ?, maLoai = ?, tinhTrang = ?, ngaySX = ? WHERE maSP = ?");
	        stmt.setString(1, sp.getTenSP());                      
	        stmt.setDouble(2, sp.getDonGia());                      
	        stmt.setString(3, sp.getLoaiSP().getMaLoai());           
	        stmt.setString(4, sp.getTinhTrang());                   
	        stmt.setDate(5, Date.valueOf(sp.getNgaySX()));           
	        stmt.setString(6, sp.getMaSP());                        
	        n = stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return n > 0;
	}

	
	public SanPham search(String masp) {
		SanPham sp = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("SELECT * from SANPHAM s inner join LOAISANPHAM l ON s.maLoai=l.maLoai WHERE maSP = ?");
			stmt.setString(1, masp);
			ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	        	String maSP = rs.getString("maSP");
				String tenSP = rs.getString("tenSP");
				int  donGia = rs.getInt("donGia");
				String tinhTrang= rs.getString("tinhTrang");
				LocalDate ngaySX = rs.getDate("ngaySX").toLocalDate();
				LoaiSanPham loaiSP = new LoaiSanPham(rs.getString("maLoai"),rs.getString("tenLoai"));
				sp = new SanPham(maSP,tenSP,donGia,tinhTrang,ngaySX,loaiSP);
	        

		}
		}catch(SQLException e) {
		e.printStackTrace();
		}
		return sp;
	}
	
	public SanPham searchArray(String maSP, ArrayList<SanPham> list) {
	    for (SanPham sp : list) {
	        if (sp.getMaSP().equalsIgnoreCase(maSP)) {
	            return sp;
	        }
	    }
	    return null;
	}
	
	
	public SanPham layMaSpTheoTen(String tenSp) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("Select * from SANPHAM where tenSP = ?");
			stmt.setString(1, tenSp);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String  maSP = rs.getString("maSP");
				
				int dongia = rs.getInt("donGia");
				LoaiSanPham lsp = new LoaiSanPham();
				lsp.setMaLoai("LSP01");
				String tinhTrang = rs.getString("tinhTrang");
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate ngaysx = LocalDate.parse(rs.getString("ngaySX"),dtf);
				sp = new SanPham(maSP,tenSp,dongia,tinhTrang,ngaysx,lsp);
			
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return sp;
	
	}
}

