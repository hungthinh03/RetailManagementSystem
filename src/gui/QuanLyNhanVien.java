package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.QuanLyNhanVien_DAO;
import entity.NhanVien;

public class QuanLyNhanVien extends JPanel implements ActionListener, MouseListener{
	private JTextField txtMa, txtTen, txtDiaChi, txtSdt, txtNgaySinh, txtEmail,txtTimKiem;
	private JLabel lblMa, lblTen, lblDiaChi, lblSdt, lblNgaySinh, lblGioiTinh, lblEmail, lblTrangThai,lblThem, lblNhom;
	private JTable tblNV;
	private DefaultTableModel model;
	private JComboBox cmbGioiTinh, cmbTrangThai, cmbNhom;
	private JButton btnThem, btnSua, btnXoa, btnThoat,btnXoaRong,btnSearch;
	private JButton btnTrangChinh,btnBanHang,btnNhanVien,btnSanPham,btnHoaDon,btnThongKe,btnDangXuat, btnRefresh;
	private QuanLyNhanVien_DAO nv_dao;
	
	public QuanLyNhanVien() {
		setSize(1200, 800);
		ConnectDB.getInstance().connect();
		nv_dao = new QuanLyNhanVien_DAO();
		//
		setLayout(new BorderLayout(0, 0));
	    setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 15));
	        
		JTabbedPane tabbedPane = new JTabbedPane();

		Box mainBox = Box.createVerticalBox();
		mainBox.add(Box.createVerticalStrut(15)); 
		Box boxTren, boxDuoi;	
		mainBox.add(boxTren = Box.createHorizontalBox());

		Box boxTrenTrai, boxTrenPhai;
		boxTren.add(boxTrenTrai = Box.createVerticalBox());
		boxTren.add(Box.createHorizontalStrut(20)); 
		Box b0;
		boxTrenTrai.add(b0 = Box.createHorizontalBox());
		b0.add(lblThem = new JLabel("Thêm nhân viên"));
		lblThem.setForeground(Color.RED);
		lblThem.setFont(new Font("Arial",Font.BOLD,20));
		boxTrenTrai.add(Box.createVerticalStrut(30));
		boxTren.add(boxTrenPhai = Box.createVerticalBox());

		Box b1, b2, b3, b4, b5,b6;
	
		
		boxTrenTrai.add(b1 = Box.createHorizontalBox());
		b1.add(lblMa = new JLabel("Mã NV:"));
		b1.add(Box.createHorizontalStrut(20));
		b1.add(txtMa = new JTextField());
		
	
		b1.add(Box.createHorizontalStrut(80));
		b1.add(lblTen = new JLabel("Họ và tên:"));
		b1.add(Box.createHorizontalStrut(30));
		b1.add(txtTen = new JTextField());
		
	
		boxTrenTrai.add(Box.createVerticalStrut(10));

		boxTrenTrai.add(b2 = Box.createHorizontalBox());
		b2.add(lblNgaySinh = new  JLabel("Ngày sinh:"));
		b2.add(Box.createHorizontalStrut(20));
		b2.add(txtNgaySinh = new JTextField());
		
	
		b2.add(Box.createHorizontalStrut(80));
		b2.add(lblEmail = new JLabel("Email:"));
		b2.add(Box.createHorizontalStrut(30));
		b2.add(txtEmail = new JTextField());
	
	
		boxTrenTrai.add(Box.createVerticalStrut(10));	

		boxTrenTrai.add(b3 = Box.createHorizontalBox());
		b3.add(lblSdt = new JLabel("Số điện thoại:"));
		b3.add(Box.createHorizontalStrut(20));
		b3.add(txtSdt = new JTextField());
	
	
		b3.add(Box.createHorizontalStrut(90));
		b3.add(lblTrangThai = new JLabel("Trạng thái:"));
		b3.add(Box.createHorizontalStrut(60));
		String[] trangthai = { "Đang làm", "Đã thôi việc" };
		b3.add(cmbTrangThai = new JComboBox<>(trangthai));
		boxTrenTrai.add(Box.createVerticalStrut(10));

		boxTrenTrai.add(b4 = Box.createHorizontalBox());
		b4.add(lblGioiTinh = new JLabel("Giới tính:"));
		b4.add(Box.createHorizontalStrut(10));
		String[] item = { "Nam", "Nữ" };
		b4.add(Box.createHorizontalStrut(10));
		b4.add(cmbGioiTinh = new JComboBox<>(item));
		b4.add(Box.createHorizontalStrut(120));
		b4.add(lblNhom = new JLabel("Vai trò:"));
		b4.add(Box.createHorizontalStrut(70));
		String[] nhom = { "Quản lý", "Nhân viên" };
		b4.add(cmbNhom = new JComboBox<>(nhom));

		boxTrenTrai.add(Box.createVerticalStrut(10));

		boxTrenTrai.add(b5 = Box.createHorizontalBox());
		b5.add(lblDiaChi = new JLabel("Địa chỉ:"));
		b5.add(Box.createHorizontalStrut(30));
		b5.add(txtDiaChi = new JTextField());

		boxTrenTrai.add(Box.createVerticalStrut(20));
		boxTrenTrai.add(b6 = Box.createHorizontalBox());
		ImageIcon iconClear = new ImageIcon(("icon/xoarong.png"));
		Image imgClear = iconClear.getImage().getScaledInstance(20, 20,0);
		ImageIcon btnClearResize = new ImageIcon(imgClear);
		btnXoaRong = new JButton("Xóa rỗng",btnClearResize);
		
		ImageIcon iconRefersh = new ImageIcon("icon/refresh.png");
		Image imgRefresh = iconRefersh.getImage().getScaledInstance(20,20, 0);
		ImageIcon btnRefreshResize = new ImageIcon(imgRefresh);
		btnRefresh = new JButton("Làm mới",btnRefreshResize);
		
		b6.add(btnXoaRong);
		b6.add(Box.createHorizontalStrut(30));
		b6.add(btnRefresh);
		
		boxTrenTrai.setBorder(BorderFactory.createEmptyBorder(20, 20, 30, 20));
		boxTrenTrai.setOpaque(true);
		
		Box tren, boxTimKiem, tieudeTimKiem, duoi;
		boxTrenPhai.setBackground(new Color(237, 239, 242));
		boxTrenPhai.add(tren = Box.createVerticalBox());
		tren.add(tieudeTimKiem= Box.createHorizontalBox());
		JLabel lblTieuDe;
		tieudeTimKiem.add(lblTieuDe = new JLabel("Nhập mã nhân viên để tìm:"));
		tren.setOpaque(true);
		tren.setBorder(BorderFactory.createEmptyBorder(10,10,0,10));
		//
		tren.add(boxTimKiem = Box.createHorizontalBox());
		boxTimKiem.add(txtTimKiem = new JTextField());
		txtTimKiem.setMinimumSize(new Dimension(300, 30));
		txtTimKiem.setMaximumSize(new Dimension(300, 30));
		boxTimKiem.add(Box.createHorizontalStrut(2));
		
		ImageIcon iconSearch = new ImageIcon(("icon/find.png"));
		Image img = iconSearch.getImage().getScaledInstance(20, 12,0);
		ImageIcon resizedIcon = new ImageIcon(img);
		 btnSearch = new JButton("Tìm",resizedIcon);
		boxTimKiem.add(btnSearch);
		
		
		boxTrenPhai.add(duoi = Box.createVerticalBox());
		
		ImageIcon iconThem = new ImageIcon(("icon/add.png"));
		Image imgThem = iconThem.getImage().getScaledInstance(20, 20,0);
		ImageIcon btnThemResize = new ImageIcon(imgThem);
		btnThem = new JButton("Thêm",btnThemResize);
		

		ImageIcon iconEdit = new ImageIcon(("icon/edit-icon.png"));
		Image imgSua = iconEdit.getImage().getScaledInstance(20, 20,0);
		ImageIcon btnSuaResize = new ImageIcon(imgSua);
		btnSua = new JButton("Sửa",btnSuaResize);
		
		ImageIcon iconXoa = new ImageIcon(("icon/delete.png"));
		Image imgXoa = iconXoa.getImage().getScaledInstance(20, 20,0);
		ImageIcon btnXoaResize = new ImageIcon(imgXoa);
		btnXoa = new JButton("Xóa",btnXoaResize);
		
		ImageIcon iconThoat = new ImageIcon(("icon/logout.png"));
		Image imgThoat = iconThoat.getImage().getScaledInstance(20, 20,0);
		ImageIcon btnThoatResize = new ImageIcon(imgThoat);
		
		btnThoat = new JButton("Thoát",btnThoatResize);
		
		//duoi.setBackground(Color.white);
		duoi.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

		duoi.setOpaque(true);
		
		Box b8,b9;
		duoi.add(b8 = Box.createHorizontalBox());
		b8.add(btnThem);
		b8.add(Box.createHorizontalStrut(30));
		b8.add(btnSua);
		duoi.add(b9 = Box.createHorizontalBox());
		b9.add(btnXoa);
		b9.add(Box.createHorizontalStrut(30));
		b9.add(btnThoat);
		

		mainBox.add(Box.createVerticalStrut(15)); 
		mainBox.add(boxDuoi = Box.createVerticalBox());

		Box b7;
		boxDuoi.add(b7 = Box.createHorizontalBox());


		String[] cols = { "Mã NV", "Họ và tên","Địa chỉ" ,"Email", "Số điện thoại", "Trạng thái", "Giới tính","Vai trò","Ngày sinh" };
		model = new DefaultTableModel(cols, 0);
	
		JScrollPane pane = new JScrollPane(tblNV);
		pane.setViewportView(tblNV = new JTable(model));
		tblNV.setPreferredScrollableViewportSize(new Dimension(1000, 900));
		b7.add(pane);
		
		Dimension txtSize = new Dimension(100,30);
		txtMa.setPreferredSize(txtSize);
		txtSdt.setPreferredSize(txtSize);
		txtNgaySinh.setPreferredSize(txtSize);
		cmbGioiTinh.setPreferredSize(txtSize);
		cmbTrangThai.setPreferredSize(txtSize);
		cmbNhom.setPreferredSize(txtSize);
		txtEmail.setPreferredSize(txtSize);
		txtTen.setPreferredSize(txtSize);
		txtTimKiem.setMaximumSize(new Dimension(300,30));
		
		
		  Dimension lblSize = new Dimension(100, 25);
		lblMa.setPreferredSize(lblSize);
		lblGioiTinh.setPreferredSize(lblSize);
		lblSdt.setPreferredSize(lblSize);
		lblDiaChi.setPreferredSize(new Dimension(90,25));
		lblNgaySinh.setPreferredSize(lblSize);
		lblTen.setPreferredSize(lblSize);
		lblEmail.setPreferredSize(lblSize);
		lblTrangThai.setPreferredSize(new Dimension(130,25));
		lblNhom.setPreferredSize(lblSize);
		


		btnThem.addActionListener(this);
		tblNV.addMouseListener(this);
		btnXoaRong.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnSearch.addActionListener(this);
		btnRefresh.addActionListener(this);
		btnThoat.addActionListener(this);

		tabbedPane.add("Quản lý nhân viên", mainBox);
		add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.setBackground(new Color(237, 239, 242));
		//
		DocDuLieuDatabaseVaoTable();
		
	}
	private void DocDuLieuDatabaseVaoTable() {
		QuanLyNhanVien_DAO ds = new QuanLyNhanVien_DAO();
		List<NhanVien> list = ds.getalltbNhanVien();
		for (NhanVien s : list) {
			String[] rowData = {s.getMaNV(),s.getTenNV(),s.getDiaChi(),s.getEmail(),s.getSoDT(),s.getTrangThai(),s.getGioiTinh(),s.getVaiTro(),s.getNgaySinh().toString()};
			model.addRow(rowData);
		}
		tblNV.setModel(model);
	}
	String maNV, hoTen, email, sdt, trangthai, gioitinh, vaitro, diaChi;
	LocalDate ngaySinh;
	private NhanVien revertFromField() {
		maNV = txtMa.getText();
		hoTen = txtTen.getText();
		email = txtEmail.getText();
		sdt = txtSdt.getText();
		trangthai = cmbTrangThai.getSelectedItem().toString();
		gioitinh = cmbGioiTinh.getSelectedItem().toString();
		diaChi = txtDiaChi.getText();
		vaitro = cmbNhom.getSelectedItem().toString();
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    ngaySinh = LocalDate.parse(txtNgaySinh.getText().trim(), formatter);
		return new NhanVien(maNV, hoTen,diaChi, sdt, trangthai,ngaySinh, vaitro, email, gioitinh);
		
	}
	
	public boolean validData() {
		String maNv = txtMa.getText();
		String hoten = txtTen.getText();
		String Email = txtEmail.getText();
		String Sdt = txtSdt.getText();
		String ngaysinh = txtNgaySinh.getText();
		String diachi = txtDiaChi.getText();
		
		if (maNv.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this,"Mã nhân viên không được để trống");
			txtMa.requestFocus();
			return false;
		}
		if (!maNv.matches("^[A-Z]{2}\\d{3}")) {
			JOptionPane.showMessageDialog(this,"Mã nhân viên bắt đầu bằng 2 kí tự hoa và theo sau là 3 kí số");
			txtMa.requestFocus();
			return false;
		}
		
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    try {
		        LocalDate.parse(ngaysinh, formatter); 
		    } catch (DateTimeException e) {
		        JOptionPane.showMessageDialog(this,"Vui lòng nhập đúng định dạng ngày (yyyy-MM-dd)");
		        txtNgaySinh.requestFocus();
		        return false;
		    }		
		
		if (hoten.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this,"Họ tên nhân viên không được trống");
			txtTen.requestFocus();
			return false;
		}
		
		if (Email.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this,"Email không được để trống");
			txtEmail.requestFocus();
			return false;
		}
		if (!Email.matches("^[A-Za-z\\_\\-\\.\\d]+\\@[A-Za-z]+\\.[a-z]+$")) {
			JOptionPane.showMessageDialog(this,"Email sai định dạng");
			txtEmail.requestFocus();
			return false;
		}
		
		if (Sdt.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this,"Số điện thoại không được để trống");
			txtSdt.requestFocus();
			return false;
		}
		
		if(!Sdt.matches("^0\\d{9}")) {
			JOptionPane.showMessageDialog(this,"Số điện thoại phải bằng đầu bằng số 0");
			txtSdt.requestFocus();
			return false;
		}
		
		if (diachi.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this,"Địa chỉ không được để trống");
			txtDiaChi.requestFocus();
			return false;
		}
		
		
		return true;
	}
	
	
	public void timNhanVien() {
		String manv = txtTimKiem.getText().trim();
		NhanVien nv = nv_dao.search(manv);
		model.setRowCount(0);
		if (nv!=null) {
			String[] dataRow =  {nv.getMaNV(),nv.getTenNV(),nv.getDiaChi(),nv.getEmail(),nv.getSoDT(),nv.getTrangThai(),nv.getGioiTinh(),nv.getVaiTro(),nv.getNgaySinh().toString()};
			model.addRow(dataRow);
				
		}else {
			JOptionPane.showMessageDialog(this,"Không tìm thấy nhân viên có mã "+ manv);
			txtTimKiem.requestFocus();
			return;
		}
		
		}
	
	
	public void themNhanVien() {
		if (validData()) {
			NhanVien nv = revertFromField();
			if(nv_dao.create(nv)) {
				
				model.addRow(new Object[] {nv.getMaNV(), nv.getTenNV(),nv.getDiaChi(),nv.getEmail(),nv.getSoDT(),nv.getTrangThai(),nv.getGioiTinh(),nv.getVaiTro(),nv.getNgaySinh().toString()});
				JOptionPane.showMessageDialog(this,"Thêm nhân viên thành công");
			}else {
				JOptionPane.showMessageDialog(this,"Trùng mã nhân viên");
				txtMa.requestFocus();
			}
		}
	}
	
	public void xoaNhanVien() {
		int row = tblNV.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this,"Chưa chọn dòng để xóa");
		}else {
			String trangThai= tblNV.getValueAt(row, 5).toString();
			if(trangThai.equals("Đang làm"))
			{
				JOptionPane.showMessageDialog(this,"Không thể xóa nhân viên ở trạng thái 'Đang làm'");
				return;
			}
			String manv = tblNV.getValueAt(row, 0).toString();
			String[] options = {"Có", "Không"};
			int hoiNhac = JOptionPane.showOptionDialog(this,"Xác nhận xóa nhân viên?","Chú ý",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
			if (hoiNhac == JOptionPane.YES_OPTION) {
				if (nv_dao.delete(manv)) {
					model.removeRow(row);
					JOptionPane.showMessageDialog(this,"Xóa thành công!");
					xoaRong();
				}
			}
		}
	}
	  
	public boolean validDataSua() {
		String hoten = txtTen.getText();
		String Email = txtEmail.getText();
		String Sdt = txtSdt.getText();
		String ngaysinh = txtNgaySinh.getText();
		String diachi = txtDiaChi.getText();
		
		
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    try {
		        LocalDate.parse(ngaysinh, formatter); 
		    } catch (DateTimeException e) {
		        JOptionPane.showMessageDialog(this,"Vui lòng nhập đúng định dạng ngày (yyyy-MM-dd)");
		        txtNgaySinh.requestFocus();
		        return false;
		    }		
		
		if (hoten.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this,"Họ tên nhân viên không được trống");
			txtTen.requestFocus();
			return false;
		}
		
		if (Email.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this,"Email không được để trống");
			txtEmail.requestFocus();
			return false;
		}
		if (!Email.matches("^[A-Za-z\\_\\-\\.\\d]+\\@[A-Za-z]+\\.[a-z]+$")) {
			JOptionPane.showMessageDialog(this,"Email sai định dạng");
			txtEmail.requestFocus();
			return false;
		}
		
		if (Sdt.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this,"Số điện thoại không được để trống");
			txtSdt.requestFocus();
			return false;
		}
		
		if(!Sdt.matches("^0\\d{9}")) {
			JOptionPane.showMessageDialog(this,"Số điện thoại phải bằng đầu bằng số 0");
			txtSdt.requestFocus();
			return false;
		}
		
		if (diachi.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this,"Địa chỉ không được để trống");
			txtDiaChi.requestFocus();
			return false;
		}
		
		
		return true;
	}
	
	public void suaNhanVien() {
		int row = tblNV.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this,"Chưa chọn nhân viên để sửa");
		}
		String maTable = tblNV.getValueAt(row, 0).toString().trim();
		
		String ma = txtMa.getText();
		if (!ma.equals(maTable)) {
			JOptionPane.showMessageDialog(this,"Không thể sửa mã nhân viên");
			txtTen.requestFocus();
			return;
		}
		if (validDataSua()) {
			NhanVien nv = revertFromField();
			if (nv_dao.update(nv)) {
				model.setValueAt(nv.getTenNV(), row, 1);
				model.setValueAt(nv.getDiaChi(), row, 2);
				model.setValueAt(nv.getEmail(), row, 3);
				model.setValueAt(nv.getSoDT(), row, 4);
				model.setValueAt(nv.getTrangThai(), row, 5);
				model.setValueAt(nv.getGioiTinh(), row, 6);
				model.setValueAt(nv.getVaiTro(), row, 7);
				model.setValueAt(nv.getNgaySinh(), row, 8);
				JOptionPane.showMessageDialog(this,"Sửa thành công");
				return;
			}
		}
		
		

		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnThem) {
			themNhanVien();
		}else if (e.getSource() == btnXoaRong) {
			xoaRong();
		}else if (e.getSource() == btnXoa) {
			xoaNhanVien();
		}else if (e.getSource() == btnSua) {
			suaNhanVien();
		}else if (e.getSource() == btnSearch) {
			timNhanVien();
		}else if (e.getSource() == btnRefresh) {
			model.setRowCount(0);
			DocDuLieuDatabaseVaoTable();;
			txtMa.setText("");
			txtTen.setText("");
			txtNgaySinh.setText("");
			txtEmail.setText("");
			txtSdt.setText("");
			cmbTrangThai.setSelectedIndex(0);
			cmbGioiTinh.setSelectedIndex(0);
			cmbNhom.setSelectedIndex(0);
			txtDiaChi.setText("");
		}else if (e.getSource() == btnThoat) {
			System.exit(1);
		}
		
	}

	public void xoaRong() {
		txtMa.setText("");
		txtTen.setText("");
		txtNgaySinh.setText("");
		txtEmail.setText("");
		txtSdt.setText("");
		cmbTrangThai.setSelectedIndex(0);
		cmbGioiTinh.setSelectedIndex(0);
		cmbNhom.setSelectedIndex(0);
		txtDiaChi.setText("");
		txtMa.requestFocus();
		tblNV.clearSelection();

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tblNV.getSelectedRow();
		if (row != -1) {
			txtMa.setText(tblNV.getValueAt(row, 0).toString().trim());
			txtTen.setText(tblNV.getValueAt(row,1).toString().trim());
			txtDiaChi.setText(tblNV.getValueAt(row, 2).toString().trim());
			txtEmail.setText(tblNV.getValueAt(row, 3).toString().trim());
			txtSdt.setText(tblNV.getValueAt(row, 4).toString().trim());
			String trangthai = tblNV.getValueAt(row, 5).toString().trim();
			if (trangthai.equalsIgnoreCase("Đang làm")) {
				cmbTrangThai.setSelectedIndex(0);
			}else if (trangthai.equalsIgnoreCase("Đã thôi việc")) {
				cmbTrangThai.setSelectedIndex(1);
			}
			String gt = tblNV.getValueAt(row, 6).toString().trim();
			if (gt.equalsIgnoreCase("nam")) {
				cmbGioiTinh.setSelectedIndex(0);
			}else if (gt.equalsIgnoreCase("Nữ")) {
				cmbGioiTinh.setSelectedIndex(1);
			}
			String vaiTro = tblNV.getValueAt(row, 7).toString().trim();
			if (vaiTro.equalsIgnoreCase("Quản lý")) {
				cmbNhom.setSelectedIndex(0);
			}else if (vaiTro.equalsIgnoreCase("Nhân viên")) {
				cmbNhom.setSelectedIndex(1);
			}
			
			txtNgaySinh.setText(tblNV.getValueAt(row, 8).toString().trim());
			
		}else {
			txtMa.setText("");
			txtTen.setText("");
			txtNgaySinh.setText("");
			txtEmail.setText("");
			txtSdt.setText("");
			cmbTrangThai.setSelectedIndex(0);
			cmbGioiTinh.setSelectedIndex(0);
			cmbNhom.setSelectedIndex(0);
			txtDiaChi.setText("");
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
}
