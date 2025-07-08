package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiSanPham;
import entity.SanPham;

public class LoaiSanPham_DAO {
	ArrayList<LoaiSanPham> listlsp;
	LoaiSanPham lsp;
	
    public ArrayList<LoaiSanPham> getallLoaiSanPham() {
        ArrayList<LoaiSanPham> dssp = new ArrayList<LoaiSanPham>();
        try {
        	ConnectDB.getInstance().connect(); 
            
            Connection con = ConnectDB.getConnection(); 
            
                 Statement statement = con.createStatement();
                 ResultSet rs = statement.executeQuery("SELECT * FROM LOAISANPHAM");

                while (rs.next()) {
                    String maLoai = rs.getString(1);
                    String tenLoai = rs.getString(2);
                    LoaiSanPham l = new LoaiSanPham(maLoai, tenLoai);
                    dssp.add(l);
                }
                
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dssp;
    }


}
