package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.LoaiSanPham_DAO;
import dao.SanPham_DAO;
import entity.LoaiSanPham;
import entity.SanPham;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class QuanLySanPham extends JPanel implements ActionListener ,MouseListener{
    private JComboBox comboLoai;
    private JComboBox combomaLoai;
    private JComboBox combotinhTrang;
    private DefaultTableModel tableModel;
    private JTable table;
    private LoaiSanPham_DAO loai_dao;
    private SanPham_DAO sp_dao;
  
    JLabel lbltensp, lblmasp, lblloai, lbldongia, lblngaysx,lbltinhtrang,lblmaloai;
    JTextField txttensp, txtmasp, txtdongia, txttimkiem,txtngaysx;
    JButton  btnXoa1Dong,btnLamMoi,btnXoaTrang,btnThem,btnSua,btnTimKiem;
	
    

    public QuanLySanPham() {
    	 sp_dao = new SanPham_DAO();
    	 loai_dao = new LoaiSanPham_DAO();
    	
        setSize(900, 600);
        setLayout(new BorderLayout(0, 0));
        
        //pnNorth
  		JPanel pNorth = new JPanel();
  		pNorth.setBackground(Color.red);
  		add(pNorth, BorderLayout.NORTH);
  		//
  		JLabel lblTieuDe = new JLabel("QUẢN LÝ SẢN PHẨM");
  		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 22));
  		lblTieuDe.setForeground(Color.white);
  		lblTieuDe.setVerticalAlignment(SwingConstants.BOTTOM);
  		pNorth.add(lblTieuDe);
        
  		
        Box b = Box.createVerticalBox();
        Box b1, b2, b3, b4, b5,b6;


        Dimension txtSize = new Dimension(200, 25); // Kích thước chuẩn cho TextField

        b.add(Box.createVerticalStrut(10));
        b.add(b1 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b1.add(lblmasp = new JLabel("Mã Sản Phẩm:"));
        b1.add(Box.createHorizontalStrut(10));
        b1.add(txtmasp = new JTextField());
        txtmasp.setPreferredSize(txtSize);

        b.add(b2 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b2.add(lbltensp = new JLabel("Tên Sản Phẩm:"));
        b2.add(Box.createHorizontalStrut(10));
        b2.add(txttensp = new JTextField());
        txttensp.setPreferredSize(txtSize);

        b.add(b3 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b3.add(lbldongia = new JLabel("Đơn giá:"));
        b3.add(Box.createHorizontalStrut(10));
        b3.add(txtdongia = new JTextField());
        txtdongia.setPreferredSize(txtSize);

        b.add(b4 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b4.add(lblngaysx = new JLabel("Ngày Sản Xuất:"));
        b4.add(Box.createHorizontalStrut(10));
        b4.add(txtngaysx =  new JTextField());
        txtngaysx.setPreferredSize(txtSize);
        // loai san pham
        
 
        
        
        b4.add(Box.createHorizontalStrut(20));
        b4.add(lblloai = new JLabel("Loại Sản Phẩm:"));
        b4.add(Box.createHorizontalStrut(10));
        
        //do du lieu vao combobox
      
        
       b4.add(comboLoai =new JComboBox<String>());
       comboLoai.setEditable(true);
 
       loai_dao = new LoaiSanPham_DAO(); 
       ArrayList<LoaiSanPham> listLoai = loai_dao.getallLoaiSanPham();
       for (LoaiSanPham l : listLoai ) {
    	   comboLoai.addItem(l.getTenLoai());
       }
       
       b4.add(Box.createHorizontalStrut(20));
       b4.add(lblmaloai = new JLabel("Mã Loại:"));
       b4.add(Box.createHorizontalStrut(10));
       
       b4.add(Box.createHorizontalStrut(10));
       b4.add(combomaLoai =new JComboBox<String>());
       combomaLoai.setEditable(true);
      
       loai_dao = new LoaiSanPham_DAO(); 
       ArrayList<LoaiSanPham> listmaLoai = loai_dao.getallLoaiSanPham();
       for (LoaiSanPham l : listmaLoai ) {
    	   combomaLoai.addItem(l.getMaLoai());
       }
       
        
        
        // tinh trang
        b4.add(Box.createHorizontalStrut(20));
        b4.add(lbltinhtrang = new JLabel("Tình trạng:"));
        b4.add(Box.createHorizontalStrut(10));
        String[] tinhtrang = {"Còn bán","Hết bán"};
        b4.add(combotinhTrang = new JComboBox(tinhtrang));

        // Thêm hàng nút b5
        b.add(b5 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(20));

        btnThem = new JButton("Thêm");
        btnXoaTrang = new JButton("Xóa trắng");
         btnXoa1Dong = new JButton("Xóa 1 dòng");
        btnLamMoi = new JButton("Làm mới");
        btnSua = new JButton("Sửa");
        btnTimKiem= new JButton("Tìm kiếm");
     

        // Thêm các nút vào hàng ngang và chèn khoảng cách giữa chúng
        b5.add(btnThem);
        b5.add(Box.createHorizontalStrut(20));
        b5.add(btnXoaTrang);
        b5.add(Box.createHorizontalStrut(20));
        b5.add(btnXoa1Dong);
        b5.add(Box.createHorizontalStrut(20));
        b5.add(btnLamMoi);
        b5.add(Box.createHorizontalStrut(20));
        b5.add(btnSua);
        b5.add(Box.createHorizontalStrut(20));
    
        b5.add(Box.createHorizontalStrut(20));
        b5.add(txttimkiem = new JTextField());
        b5.add(Box.createHorizontalStrut(20));
        b5.add(btnTimKiem);


        b.add(b6 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        String[] headers = "Mã Sản Phẩm;Tên Sản Phẩm;Đơn Giá;Mã Loại;Tình Trạng;Loại;Ngày Sản Xuất".split(";");
        tableModel = new DefaultTableModel(headers, 0);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setViewportView(table = new JTable(tableModel));
        table.setRowHeight(25);
        table.setAutoCreateRowSorter(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        b6.add(scroll);


        // Căn đều kích thước label
        Dimension lblSize = new Dimension(110, 25);
        lbltensp.setPreferredSize(lblSize);
        lblmasp.setPreferredSize(lblSize);
        lbldongia.setPreferredSize(lblSize);
        lblngaysx.setPreferredSize(lblSize);
        lblloai.setPreferredSize(lblSize);

        add(b, BorderLayout.CENTER);
        b.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        
        
		table.addMouseListener(this);
		btnXoa1Dong.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnTimKiem.addActionListener(this);
		
		DocDuLieuVaoTable();
	//	DocDuLieuTenLoaiVaoTable();

        
    }
    
  
	
    // doc du lieu vao table
    private void DocDuLieuVaoTable() {
    	SanPham_DAO ds = new SanPham_DAO();
    	List<SanPham> list = ds.getalltbSanPham();
    	for (SanPham s :list) {
    		String[] rowData= {s.getMaSP(),s.getTenSP(), String.valueOf(s.getDonGia()),s.getLoaiSP().getMaLoai(),s.getTinhTrang(),s.getLoaiSP().getTenLoai(),s.getNgaySX().toString()};
    		tableModel.addRow(rowData);
    	}
    	  table.setModel(tableModel);
    }
    // khai bao bien 
    String maSP,tenSP,tinhTrang,maLoai,tenLoai;
    LocalDate ngaySX;
    double donGia;
    

    private SanPham revertFromField() {
        maSP = txtmasp.getText();
        tenSP = txttensp.getText();
        donGia = Double.parseDouble(txtdongia.getText());
        tinhTrang = combotinhTrang.getSelectedItem().toString();
        
        // Lấy mã loại từ combobox mã loại
        maLoai = combomaLoai.getSelectedItem().toString(); 
        // Lấy tên loại từ combobox tên loại (nếu cần)
        tenLoai = comboLoai.getSelectedItem().toString(); 

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ngaySX = LocalDate.parse(txtngaysx.getText().trim(), formatter);

        LoaiSanPham loaiSP = new LoaiSanPham(maLoai, tenLoai);
        return new SanPham(maSP, tenSP, (int) donGia, tinhTrang, ngaySX, loaiSP);
    }
	
	
	public boolean validData() {
		String maSP =txtmasp.getText();
		String tenSP= txttensp.getText();
		String donGia= txtdongia.getText();
		String ngaySX=txtngaysx.getText();
		//masanpham
		if (maSP.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Mã Sản Phẩm không được để trống");
			txtmasp.requestFocus();
			return false;
		}
		
		if(!maSP.matches("^SP\\d+$")) {
			JOptionPane.showMessageDialog(this,"2 ký tự đầu phải là SP theo sau là 3 ký tự số");
			txtmasp.requestFocus();
			return false;
			
		}
		//tenSanPham
		if (tenSP.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống");
			txttensp.requestFocus();
			return false;
		}
		
		
		 //don gia
		if (donGia.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Đơn giá không được để trống");
			txtdongia.requestFocus();
			return false;
		}
		
		//ngaysx
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    try {
		        LocalDate.parse(ngaySX, formatter); 
		    } catch (DateTimeException e) {
		        JOptionPane.showMessageDialog(this,"Vui lòng nhập đúng định dạng ngày (yyyy-MM-dd)");
		        txtngaysx.requestFocus();
		        return false;
		    }
		    
		    if (combomaLoai.getSelectedItem() == null) {
		        JOptionPane.showMessageDialog(this, "Vui lòng chọn mã loại!");
		        combomaLoai.requestFocus();
		        return false;
		    }
		   
		    return true;	    
	}
	// them san pham
	
	public void themSanPham() {
	    if (validData()) {
	        SanPham sp = revertFromField();
	        if (sp_dao.create(sp)) {
	            // Sửa lại số lượng và thứ tự cột
	            tableModel.addRow(new Object[]{
	                sp.getMaSP(),
	                sp.getTenSP(),
	                sp.getDonGia(),
	                sp.getLoaiSP().getMaLoai(), // Lấy mã loại từ đối tượng LoaiSanPham
	                sp.getTinhTrang(),
	                sp.getLoaiSP().getTenLoai(),
	                sp.getNgaySX().toString()

	                
	            });
	            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
	        } else {
	            JOptionPane.showMessageDialog(this, "Trùng mã sản phẩm");
	            txtmasp.requestFocus();
	        }
	    }
	}
	
	//delete san pham
	public void xoaSanPham() {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this,"Chưa chọn dòng để xóa");
		}else {
			String masp = table.getValueAt(row, 0).toString();
			int hoiNhac = JOptionPane.showConfirmDialog(this,"Bạn chắc chăn muốn xóa chứ?","Chú ý",JOptionPane.YES_NO_OPTION);
			if (hoiNhac == JOptionPane.YES_OPTION) {
				if (sp_dao.delete(masp)) {
					tableModel.removeRow(row);
					JOptionPane.showMessageDialog(this,"Xóa thành công!");
					xoaTrang();
				}
			}
		}
	}
	
	//timkiem 
	public void timSanPham() {
	    String masp = txttimkiem.getText().trim();
	    SanPham sp = sp_dao.search(masp);
	    tableModel.setRowCount(0);
	    if (sp != null) {
	        tableModel.addRow(new Object[]{
	            sp.getMaSP(),
	            sp.getTenSP(),
	            sp.getDonGia(),
	            sp.getLoaiSP().getMaLoai(),
	            sp.getTinhTrang(),
	            sp.getLoaiSP().getTenLoai(),
	            sp.getNgaySX().toString()
	        }); // Thêm dấu ) và ;
	    } else {
	        JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm có mã " + masp); // Sửa "nhân viên" thành "sản phẩm"
	        txttimkiem.requestFocus();
	        return;
	    }
	}
	


	
	public void suaSanPham() {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this,"Chưa chọn sản phẩm để sửa");
			return;
		}
		String maTable = table.getValueAt(row, 0).toString().trim();
		txtmasp.setEditable(false); // Không cho sửa mã
		String ma = txtmasp.getText();
		if (!ma.equals(maTable)) {
			JOptionPane.showMessageDialog(this,"Không thể sửa mã sản phẩm");
			txttensp.requestFocus();
			return;
		}
		if (validData()) {
			SanPham sp = revertFromField();
			if(sp_dao.update(sp)) {
				// Cập nhật lại dữ liệu trong bảng
				tableModel.setValueAt(sp.getTenSP(), row, 1);
				tableModel.setValueAt(sp.getDonGia(), row, 2);
				tableModel.setValueAt(sp.getLoaiSP().getMaLoai(), row, 3);
				tableModel.setValueAt(sp.getTinhTrang(), row, 4);
				tableModel.setValueAt(sp.getLoaiSP().getTenLoai(), row, 5);
				tableModel.setValueAt(sp.getNgaySX().toString(), row, 6);
				
				JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công");
			} else {
				JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thất bại");
			}
		}
	}

		
		
		
	
	
	public void xoaTrang() {
		txtmasp.setText("");
		txttensp.setText("");
		txtdongia.setText("");
		txtngaysx.setText("");
		combomaLoai.setSelectedIndex(0);
		comboLoai.setSelectedIndex(0);
		combotinhTrang.setSelectedIndex(0);
	
	}
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource()==btnThem) {
    		themSanPham();
    	}
    	else if(e.getSource()==btnXoaTrang) {
    		xoaTrang();
    	}
    	else if(e.getSource()==btnXoa1Dong) {
    		xoaSanPham();
    	}
    	
    	else if(e.getSource()==btnTimKiem) {
    		timSanPham();
    	}
    	else if (e.getSource() == btnSua) {
			suaSanPham();}
    	else if(e.getSource()==btnLamMoi)
    	{
    		tableModel.setRowCount(0);
    		DocDuLieuVaoTable();
    		txtmasp.setText("");
    		txttensp.setText("");
    		txtdongia.setText("");
    		txtngaysx.setText("");
    		comboLoai.setSelectedIndex(0);
    		combomaLoai.setSelectedIndex(0);
    		combotinhTrang.setSelectedIndex(0);
    	}
    	
    }
    
    
    
    
    
    
    
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row= table.getSelectedRow();
		txtmasp.setText(tableModel.getValueAt(row, 0).toString());
		txttensp.setText(tableModel.getValueAt(row, 1).toString());
		txtdongia.setText(tableModel.getValueAt(row, 2).toString());
		combomaLoai.setSelectedItem(tableModel.getValueAt(row, 3).toString());
		
	    combotinhTrang.setSelectedItem(tableModel.getValueAt(row, 4).toString());
	    comboLoai.setSelectedItem(tableModel.getValueAt(row, 5).toString());
		txtngaysx.setText(tableModel.getValueAt(row, 6).toString());

		
		
		
		
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
  
}


