package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.QuanLyKhachHang_DAO;
import entity.KhachHang;


public class QuanLyKhachHang extends JPanel implements ActionListener,MouseListener{
	JLabel lblMa, lblSdt, lblTen,lblGioiTinh,lblTim;
	JTextField txtMa, txtSdt, txtTen, txtTim;
	JComboBox cboGioiTinh;
	JTable tblKh;
	DefaultTableModel model;
	JButton btnThem, btnXoa, btnSua, btnXoaRong, btnTim, btnLamMoi, btnThoat;
	QuanLyKhachHang_DAO kh_dao;
	public QuanLyKhachHang() {
		ConnectDB.getInstance().getConnection();
		kh_dao = new QuanLyKhachHang_DAO();
		setSize(1200,900);
		
		setLayout(new BorderLayout(0, 0));
//	    setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 15));
	    JTabbedPane tabbedPane = new JTabbedPane();
	    Box box = Box.createVerticalBox();
	    box.add(Box.createVerticalStrut(40));
	    Box b1,b2,b3,b4,b5,b6;
	    tabbedPane.add(box,"Quản lý khách hàng");
	    box.add(b1 = Box.createHorizontalBox());
	    b1.add(Box.createHorizontalStrut(20));
	    b1.add(lblMa = new  JLabel("Mã khách hàng: "));
	    b1.add(Box.createHorizontalStrut(20));
	    b1.add(txtMa = new JTextField());
	    b1.add(Box.createHorizontalStrut(40));
	    
	    box.add(Box.createVerticalStrut(20));
	    box.add(b2 = Box.createHorizontalBox());
	    b2.add(Box.createHorizontalStrut(20));
	    b2.add(lblTen = new JLabel("Họ tên khách hàng: "));
	    b2.add(Box.createHorizontalStrut(20));
	    b2.add(txtTen = new JTextField());
	    b2.add(Box.createHorizontalStrut(40));
	    
	    box.add(Box.createVerticalStrut(20));
	    box.add(b3 = Box.createHorizontalBox());
	    b3.add(Box.createHorizontalStrut(20));
	    b3.add(lblGioiTinh = new JLabel("Giới tính: "));
	    b3.add(Box.createHorizontalStrut(20));
	    String[] gioiTinh = {"Nam","Nữ"};
	    b3.add(cboGioiTinh = new JComboBox<String>(gioiTinh));
	    b3.add(Box.createHorizontalStrut(40));
	    
	    box.add(Box.createVerticalStrut(20));
	    box.add(b4 = Box.createHorizontalBox());
	    b4.add(Box.createHorizontalStrut(20));
	    b4.add(lblSdt = new JLabel("Số điện thoại: "));
	    b4.add(Box.createHorizontalStrut(20));
	    b4.add(txtSdt = new JTextField());
	    b4.add(Box.createHorizontalStrut(40));
	    
	  
	    ImageIcon iconThem = new ImageIcon(("icon/customer-2.png"));
	    Image imgThem = iconThem.getImage().getScaledInstance(20, 20, 0);
	    ImageIcon iconThemResize = new ImageIcon(imgThem);
	    btnThem = new JButton("Thêm", iconThemResize);
	    
	    ImageIcon iconXoa = new ImageIcon(("icon/delete.png"));
	    Image imgXoa = iconXoa.getImage().getScaledInstance(20, 20, 0);
	    ImageIcon iconXoaResize = new ImageIcon(imgXoa);
	    btnXoa = new JButton("Xóa",iconXoaResize);
	    
	    ImageIcon iconSua = new ImageIcon(("icon/edit-icon.png"));
	    Image imgSua = iconSua.getImage().getScaledInstance(20, 20, 0);
	    ImageIcon iconSuaResize = new ImageIcon(imgSua);
	    btnSua = new JButton("Cập nhật",iconSuaResize);
	    
	    ImageIcon iconXoaRong = new ImageIcon(("icon/xoarong.png"));
	    Image imgXoaRong = iconXoaRong.getImage().getScaledInstance(20, 20, 0);
	    ImageIcon iconXoaRongResize = new ImageIcon(imgXoaRong);
	    btnXoaRong = new JButton("Xóa rỗng",iconXoaRongResize);
	    
	    ImageIcon iconTim = new ImageIcon(("icon/find.png"));
	    Image imgTim = iconXoaRong.getImage().getScaledInstance(20, 20, 0);
	    ImageIcon iconTimResize = new ImageIcon(imgTim);
	    btnTim = new JButton("Tìm",iconTimResize);
	    
	    ImageIcon iconRefresh = new ImageIcon(("icon/refresh.png"));
	    Image imgRefresh = iconRefresh.getImage().getScaledInstance(20, 20, 0);
	    ImageIcon iconRefreshResize = new ImageIcon(imgRefresh);
	    btnLamMoi = new JButton("Làm mới",iconRefreshResize);
	    
	    ImageIcon iconThoat = new ImageIcon(("icon/logout.png"));
	    Image imgThoat = iconThoat.getImage().getScaledInstance(20, 20, 0);
	    ImageIcon iconThoatResize = new ImageIcon(imgThoat);
	    btnThoat = new JButton("Thoát",iconThoatResize);
	    
	    
	  
//	    b5.add(btnThem);
//	    b5.add(Box.createHorizontalStrut(20));
//	    b5.add(btnXoa);
//	    b5.add(Box.createHorizontalStrut(20));
//	    b5.add(btnSua);
//	    b5.add(Box.createHorizontalStrut(20));
//	    b5.add(btnXoaRong);
	    
	    JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	    split.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 40));
	    //split.setBackground(Color.white);
//	    JPanel pLeft = new JPanel();
//	    pLeft.add(btnThem);
//	    pLeft.add(btnXoa);
//	    pLeft.add(btnSua);
//	    pLeft.add(btnXoaRong);
//	    split.add(pLeft);
	    Box splitTrai = Box.createHorizontalBox();
	    Box b9;
	    splitTrai.add(b9 = Box.createHorizontalBox());
	    b9.add(btnThem);
	    b9.add(btnXoa);
	    b9.add(btnSua);
	    b9.add(btnXoaRong);
	    split.add(splitTrai);
	    
	    
	    box.add(Box.createVerticalStrut(20));
	    box.add(b5 = Box.createHorizontalBox());
	    
	    Box splitPhai = Box.createHorizontalBox();
	    Box b7,b8;
	    splitPhai.add(b7 = Box.createHorizontalBox());
	    b7.add(lblTim = new JLabel("     Nhập mã khách hàng:  "));
	    b7.add(txtTim = new JTextField());
	    splitPhai.add(b8 = Box.createHorizontalBox());
	    b8.add(btnTim);
	    b8.add(btnLamMoi);
	    b8.add(btnThoat);
	    split.add(splitPhai);	   
//	    pRight.add(txtTim = new JTextField(20));
//	    pRight.add(btnTim);
//	    pRight.add(btnLamMoi);
//	    pRight.add(btnThoat);
//	    split.add(pRight);
	    b5.add(split);
	    
	 

	    
	    
	    //box.add(Box.createVerticalStrut(20));
	    box.add(b6 = Box.createHorizontalBox());
	    String[] cols = {"Mã khách hàng","Họ tên","Giới tính","Số điện thoại"};
	    model = new DefaultTableModel(cols,0);
	    JScrollPane pane = new JScrollPane(tblKh);
	    pane.setViewportView(tblKh = new JTable(model));
	    tblKh.setPreferredScrollableViewportSize(new Dimension(1000, 800));
	    b6.add(pane);
	    
//	    split.setPreferredSize(new Dimension(200,200));
	    
	    Dimension txtSize = new Dimension(400,40);
	    txtMa.setPreferredSize(txtSize);
	    txtSdt.setPreferredSize(txtSize);
	    txtTen.setPreferredSize(txtSize);
	    cboGioiTinh.setPreferredSize(txtSize);
	    
	    lblMa.setPreferredSize(lblTen.getPreferredSize());
	    lblSdt.setPreferredSize(lblTen.getPreferredSize());
	    lblGioiTinh.setPreferredSize(lblTen.getPreferredSize());
	    
	    add(tabbedPane,BorderLayout.CENTER);
	    
	    docDuLieuTuDataBase();
	    btnThem.addActionListener(this);
	    btnSua.addActionListener(this);
	    btnLamMoi.addActionListener(this);
	 
	    btnThoat.addActionListener(this);
	    btnTim.addActionListener(this);
	    btnXoa.addActionListener(this);
	    btnXoaRong.addActionListener(this);
	    tblKh.addMouseListener(this);
	}
	
	
	private void docDuLieuTuDataBase() {
		QuanLyKhachHang_DAO ds = new QuanLyKhachHang_DAO();
		List<KhachHang> list = ds.gettalltbKhachHang();
		for (KhachHang s : list) {
			String[] rowData = {s.getMaKH(),s.getTenKH(),s.getGioiTinh(),s.getSoDT()};
			model.addRow(rowData);
		}
		tblKh.setModel(model);
	}
	
	
	String maKH, tenKH, gioiTinh, sdt;
	private KhachHang revertFromField() {
		maKH = txtMa.getText();
		tenKH = txtTen.getText();
		gioiTinh = cboGioiTinh.getSelectedItem().toString();
		sdt = txtSdt.getText();
		return new KhachHang(maKH,tenKH,gioiTinh,sdt);
	}
	
	private boolean validData(boolean themKH) {
		String tenKH = txtTen.getText();
		String maKH = txtMa.getText();
		String gioitinh = cboGioiTinh.getSelectedItem().toString();
		String sdt = txtSdt.getText();
		if (themKH) {
			if (maKH.trim().isEmpty()) {
				JOptionPane.showMessageDialog(this,"Mã khách hàng không được để trống");
				txtMa.requestFocus();
				return false;
			}
			if (!maKH.matches("^(KH)\\d{3}$")) {
				JOptionPane.showMessageDialog(this, "Mã khách hàng bắt đầu bằng KH và theo sau là 3 kí số");
				txtMa.requestFocus();
				return false;
			}
		}
		
		if (tenKH.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this,"Tên khách hàng không được để trống");
			txtTen.requestFocus();
			return false;
		}
		
		if (sdt.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this,"Số điện thoại không được để trống");
			txtSdt.requestFocus();
			return false;
		}
		
		if (!sdt.matches("^0\\d{9}") || sdt.length() < 10) {
			JOptionPane.showMessageDialog(this,"Số điện thoại phải bằng đầu bằng số 0 và gồm 10 số");
			txtSdt.requestFocus();
			return false;
		}
		return true;
	}
	
	private void themKhachHang() {
		if (validData(true)) {
			KhachHang kh = revertFromField();
				if (kh_dao.create(kh)) {
					model.addRow(new Object[] {kh.getMaKH(),kh.getTenKH(),kh.getGioiTinh(),kh.getSoDT()});
					JOptionPane.showMessageDialog(this,"Thêm thành công!");
				}else {
					JOptionPane.showMessageDialog(this,"Trùng mã khách hàng!");
				}
			}
		}
	private void xoaKhachHang() {
		int row = tblKh.getSelectedRow();
		if (row ==-1 ) {
			JOptionPane.showMessageDialog(this,"Bạn chưa chọn dòng để xóa!");
		}else {
			String makh = tblKh.getValueAt(row, 0).toString();
			int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa?","Chú ý",JOptionPane.YES_NO_OPTION);
			if (hoiNhac == JOptionPane.YES_OPTION) {
				if (kh_dao.delete(makh)) {
					model.removeRow(row);
					JOptionPane.showMessageDialog(this,"Xóa thành công");
					xoaRong();
				}
			}
		}
	}
	
	private void suaKhachHang() {
		int row = tblKh.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this,"Bạn chưa chọn dòng để sửa");
		}else {
			String maTable = tblKh.getValueAt(row, 0).toString().trim();
			String ma = txtMa.getText().trim();
			if (!ma.equals(maTable)) {
				JOptionPane.showMessageDialog(this,"Không thể sửa mã khách hàng");
				txtMa.setText(maTable);
				txtTen.requestFocus();
				return;
			}
			if (validData(false)) {
				KhachHang s = revertFromField();
				if (kh_dao.update(s)) {
					model.setValueAt(s.getTenKH(), row, 1);
					model.setValueAt(s.getGioiTinh(), row, 2);
					model.setValueAt(s.getSoDT(), row, 3);
					JOptionPane.showMessageDialog(this,"Sửa thành công");
					return;
				}
			}
		}
	}
	
	private void timKhachHang() {
		String maKH = txtTim.getText().trim();
		KhachHang kh = kh_dao.search(maKH);
		if (maKH.isEmpty()) {
			JOptionPane.showMessageDialog(this,"Chưa nhập mã khách hàng cần tìm!");
			txtTim.requestFocus();
			return;
		}
		model.setRowCount(0);
		if (kh!=null) {
			model.addRow(new Object[] {kh.getMaKH(),kh.getTenKH(),kh.getGioiTinh(),kh.getSoDT()});
			tblKh.setRowSelectionInterval(0, 0);
			int row = tblKh.getSelectedRow();
			txtMa.setText(tblKh.getValueAt(row, 0).toString().trim());
			txtTen.setText(tblKh.getValueAt(row, 1).toString().trim());
			cboGioiTinh.setSelectedItem(tblKh.getValueAt(row, 2).toString().trim());
			txtSdt.setText(tblKh.getValueAt(row, 3).toString().trim());
			
		}else {
			JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng có mã "+maKH);
			txtMa.requestFocus();
			return;
		}
		
	}
	
	private void lamMoi() {
		model.setRowCount(0);
		docDuLieuTuDataBase();
		txtMa.setText("");
		txtTen.setText("");
		cboGioiTinh.setSelectedIndex(0);
		txtSdt.setText("");
	}
	
	private void xoaRong() {
		txtMa.setText("");
		txtTen.setText("");
		cboGioiTinh.setSelectedIndex(0);
		txtSdt.setText("");
		txtMa.requestFocus();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tblKh.getSelectedRow();
		if (row == -1) {
			txtMa.setText("");
			txtTen.setText("");
			cboGioiTinh.setSelectedIndex(0);
			txtSdt.setText("");
		}else {
			txtMa.setText(tblKh.getValueAt(row, 0).toString().trim());
			txtTen.setText(tblKh.getValueAt(row, 1).toString().trim());
			String gioiTinh = tblKh.getValueAt(row, 2).toString().trim();
			if (gioiTinh.equalsIgnoreCase("Nam")) {
				cboGioiTinh.setSelectedIndex(0);
			}else if (gioiTinh.equalsIgnoreCase("Nữ")){
				cboGioiTinh.setSelectedIndex(1);
			}
			txtSdt.setText(tblKh.getValueAt(row, 3).toString().trim());
		}
		
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
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnThem) {
			themKhachHang();
		}
		if (e.getSource() == btnXoa) {
			xoaKhachHang();
		}
		
		if (e.getSource() == btnTim) {
			timKhachHang();
		}
		if (e.getSource() == btnLamMoi) {
			lamMoi();
		}
		if(e.getSource() == btnSua) {
			suaKhachHang();
		}
		if (e.getSource() == btnThoat) {
			System.exit(1);
		}
		
		if (e.getSource() == btnXoaRong) {
			xoaRong();
		}
		
	}
	
}
