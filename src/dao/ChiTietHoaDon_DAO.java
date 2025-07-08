package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.LoaiSanPham;
import entity.SanPham;
import dao.HoaDon_DAO;

public class ChiTietHoaDon_DAO {
	private ArrayList<ChiTietHoaDon> listCt;
	private ArrayList<HoaDon> listHd;
	private ArrayList<SanPham> listSp;
	private HoaDon_DAO dao_hd;
	private SanPham_DAO dao_sp;
	
	public ChiTietHoaDon_DAO() {
		listCt = new ArrayList<ChiTietHoaDon>();
		dao_hd = new HoaDon_DAO();
		listHd = dao_hd.getalltbHoaDon();
		dao_sp = new SanPham_DAO();
		listSp = dao_sp.getalltbSanPham();
	}
	
	public ArrayList<ChiTietHoaDon> getallChiTietHoaDon() {
		try {
	        ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();
	        String sql = "SELECT * FROM CHITIETHOADON";
	        Statement statement = con.createStatement();
	        ResultSet rs = statement.executeQuery(sql);

	        while (rs.next()) {
	            String maHD = rs.getString("maHD").trim();
	            String maSP = rs.getString("maSP").trim();
	            int giaBan = rs.getInt("giaBan");
	            int soLuong = rs.getInt("soLuong");
	            //
	            ChiTietHoaDon cthd = new ChiTietHoaDon(dao_hd.searchHD(maHD,listHd), dao_sp.searchArray(maSP,listSp), giaBan, soLuong);
	            listCt.add(cthd);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listCt;
	}
	
	
	public boolean create(ChiTietHoaDon cthd) {
		Connection con =  ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt = con.prepareStatement("insert into CHITIETHOADON values (?,?,?,?)");
			stmt.setString(1, cthd.getHoaDon().getMaHD());
			stmt.setString(2, cthd.getSanPham().getMaSP());
			stmt.setInt(3, cthd.getGiaBan());
			stmt.setInt(4, cthd.getSoLuong());
			n = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}

}
		
