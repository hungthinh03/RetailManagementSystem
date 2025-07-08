package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import connectDB.ConnectDB;
import dao.DangNhap_DAO;
import entity.TaiKhoan;

public class DangNhap extends JFrame implements ActionListener {
	public static String tenNhanVien;
	public static String vaiTro;
	private JTextField txtTaiKhoan;
	private JLabel lblTaiKhoan, lblMatKhau,lblTitle, lb;
	private JPasswordField matKhau;
	private JButton btnDangNhap, btnHuy;
	private JCheckBox chkHienMK;
	private DangNhap_DAO dn;

	public DangNhap() {
		super("Đăng nhập");
		setResizable(false);
		
		ConnectDB.getInstance().connect();
		dn = new DangNhap_DAO();
		JPanel pNorth = new JPanel();
		pNorth.add(lblTitle = new JLabel("ĐĂNG NHẬP HỆ THỐNG"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 35));
		lblTitle.setForeground(Color.WHITE);
		pNorth.setBackground(Color.RED);

		Box b = Box.createVerticalBox();
		Box b1, b2, b3,b4;
		b.add(Box.createVerticalStrut(50));
		b.add(b1 = Box.createHorizontalBox());
		
		b1.add(lblTaiKhoan = new JLabel("Tài khoản:"));
		lblTaiKhoan.setFont(new Font("Tahoma",Font.BOLD,15));
		b1.add(Box.createHorizontalStrut(10));
		b1.add(txtTaiKhoan = new JTextField());
		b1.add(Box.createHorizontalStrut(30));
		txtTaiKhoan.setMaximumSize(new Dimension(300, 30));


		b.add(b2 = Box.createHorizontalBox());
		b2.add(lblMatKhau = new JLabel("Mật khẩu:"));
		lblMatKhau.setFont(new Font("Tahoma",Font.BOLD,15));
		b2.add(Box.createHorizontalStrut(10));
		b2.add(matKhau = new JPasswordField());
		b2.add(Box.createHorizontalStrut(30));
		matKhau.setMaximumSize(new Dimension(300, 30));


		b.add(b3 = Box.createHorizontalBox());
		b3.add(chkHienMK = new JCheckBox("Hiển thị mật khẩu"));
		
		b.add(b4 = Box.createHorizontalBox());  
		btnDangNhap = new JButton("Đăng nhập");
		btnHuy = new JButton("Thoát");
		b4.add(btnDangNhap);
		b4.add(Box.createHorizontalStrut(20));
		b4.add(btnHuy);
		
		JLabel lblImage = new JLabel();
		lblImage.setIcon(new ImageIcon("icon/login_icon.png"));
		lblImage.setBorder(new EmptyBorder(0, 10, 0, 0));

		
		add(pNorth, BorderLayout.NORTH);
		
		add(b, BorderLayout.CENTER);
		add(lblImage, BorderLayout.WEST);
		
		lblMatKhau.setPreferredSize(lblTaiKhoan.getPreferredSize());
		
		setSize(600, 350);
		setVisible(true);
		setLocationRelativeTo(null);
		//
		btnHuy.addActionListener(this);
		btnDangNhap.addActionListener(this);
		chkHienMK.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnHuy) {
			System.exit(1);
		}
		if (e.getSource() == btnDangNhap) {
			dangNhap();
			
		}
		if (chkHienMK.isSelected()) {
	        matKhau.setEchoChar((char) 0);  
	    } else {
	        matKhau.setEchoChar('*');  
	    }
		
	}

	private void dangNhap() {
		String tenTaiKhoan = txtTaiKhoan.getText();
		 String matKhauNhap = new String(matKhau.getPassword());
		 
		 TaiKhoan tkNhap = dn.ktDangNhap(tenTaiKhoan,matKhauNhap);
			 
		 if (tkNhap != null) {
			 tenNhanVien = tkNhap.getNhanVien().getTenNV();
			 vaiTro = tkNhap.getNhanVien().getVaiTro();
			 JOptionPane.showMessageDialog(this,"Đăng nhập thành công!");
			 new Start();
			 setVisible(false);
			 return;
		 }else {
			 JOptionPane.showMessageDialog(this, "Tên tài khoản hoặc mật khẩu không đúng");
			 txtTaiKhoan.requestFocus();
			 return;
		 }
		 
	}
}
