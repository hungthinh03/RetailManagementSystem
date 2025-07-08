package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiSanPham;
import entity.NhanVien;
import entity.SanPham;


public class HoaDon_DAO {
	ArrayList<HoaDon> listhd;
	
	public HoaDon_DAO() {
		listhd = new ArrayList<HoaDon>();
	}
	public ArrayList<HoaDon> getalltbHoaDon(){
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql  =( "select * " +
		            "from KHACHHANG as kh " +
		            "inner join HoaDon as hd on kh.maKH=hd.maKH " +
		            "inner join NHANVIEN as nv on nv.maNV=hd.maNV " +
		            "inner join CHITIETHOADON as ct on hd.maHD=ct.maHD " +
		            "inner join SANPHAM as sp on ct.maSP=sp.maSP " 
		         
		        );
			Statement statement =  con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maHD = rs.getString("maHD");
				LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
				NhanVien nv = new NhanVien(rs.getString("maNV"),rs.getString("tenNV"),rs.getString("diaChi"),
						rs.getString("soDT"),rs.getString("trangThai"),rs.getDate("ngaySinh").toLocalDate()
						,rs.getString("vaiTro"),rs.getString("email"),rs.getString("gioiTinh"));
				KhachHang kh = new KhachHang(rs.getString("maKH"),
						rs.getString("tenKH"),rs.getString("gioiTinh"),rs.getString("soDT"));
			
				HoaDon h =new HoaDon(maHD,ngayLap,nv,kh);
				listhd.add(h);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return listhd;
	}

	
	public HoaDon searchHD(String mahd, ArrayList<HoaDon> list) {
	    for (HoaDon hd : list) {
	        if (hd.getMaHD().equals(mahd)) 
	        {
	            return hd;
	        }
	    }
	    return null;
	}

	
	public boolean create(HoaDon hd) {
		Connection con = ConnectDB.getInstance().getConnection();
		String sqlCheck = "SELECT COUNT(*) FROM HOADON WHERE MaHD = ?";
	    String sqlInsert = "INSERT INTO HOADON (MaHD, NgayLap, MaNV, MaKH) VALUES (?, ?, ?, ?)";
	    try {
	    	PreparedStatement stmtCheck = con.prepareStatement(sqlCheck);
	        PreparedStatement stmtInsert = con.prepareStatement(sqlInsert);
	        stmtCheck.setString(1, hd.getMaHD());
	        ResultSet rs = stmtCheck.executeQuery();
	        if (rs.next() && rs.getInt(1) > 0) {
	            return false;  // Return false if the record exists
	        }
	        stmtInsert.setString(1, hd.getMaHD());
	        stmtInsert.setDate(2, Date.valueOf(hd.getNgayLap()));
	        stmtInsert.setString(3, hd.getNhanVien().getMaNV());
	        stmtInsert.setString(4, hd.getKhachHang().getMaKH());
	        int n = stmtInsert.executeUpdate();
	        return n > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	
	
	public int getSoHoaDon(){
		int soHD= 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql  =("select * from HOADON");
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				soHD++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return soHD;
	}

}

		