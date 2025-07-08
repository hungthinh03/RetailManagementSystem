package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Start extends JFrame implements ActionListener {
	private JLabel lblNV;
	private JButton btnTrangChinh;
	private JButton btnBanHang;
	private JButton btnSanPham;
	private JButton btnHoaDon;
	private JButton btnNhanVien;
	private JButton btnThongKe;
	private JButton btnThoat;
	private JButton btnKhachHang;
	public Start() {
		setVisible(true);
		setTitle("Quản lý cửa hàng tiện lợi");
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setSize(1200,600);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);	// Full screen :P
		
		//
		JPanel pWest= new JPanel();
		pWest.setLayout(new BorderLayout());
		pWest.setPreferredSize(new Dimension(200,1000));
		pWest.setBackground(Color.white);
		pWest.setBackground(new Color(255, 56, 56));
		//pWest.setBorder(BorderFactory.createLineBorder(Color.RED, 2)); // 2 = thickness
		//
		Box bWest = Box.createVerticalBox();
		pWest.add(bWest);
		bWest.add(Box.createVerticalStrut(20));
		//
		ImageIcon logoIcon = new ImageIcon("icon/logo.png");
		Image logoImg = logoIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); // adjust size as needed
		JLabel lblLogo = new JLabel(new ImageIcon(logoImg));
		lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT); // center in Box layout
		bWest.add(lblLogo);
		bWest.add(Box.createVerticalStrut(20));
		//
		Box b1;
		bWest.add(b1= Box.createHorizontalBox());
		b1.add(lblNV= new JLabel("Xin chào, "+ DangNhap.tenNhanVien));
		lblNV.setFont(new Font("Arial", Font.BOLD, 15));
		lblNV.setForeground(Color.WHITE);
		bWest.add(Box.createVerticalStrut(10));
		bWest.add(createWhiteLine(4));
		//
		Font btnFont = new Font("Arial", Font.BOLD, 15);
		Color btnColor = pWest.getBackground();
		//
		 btnTrangChinh = new JButton("Trang chính");
		 btnBanHang = new JButton("Bán hàng");
		 btnSanPham = new JButton("Sản phẩm");
		 btnHoaDon = new JButton("Hóa đơn");
		 btnKhachHang = new JButton("Khách hàng");
		 btnNhanVien = new JButton("Nhân viên");
		 btnThongKe = new JButton("Thống kê");
		 btnThoat = new JButton("Đăng xuất");
		//
		btnTrangChinh.setIcon(resizeIcon("icon/home.png", 30, 30));
		btnBanHang.setIcon(resizeIcon("icon/shopping.png", 30, 30));
		btnSanPham.setIcon(resizeIcon("icon/store.png", 30, 30));
		btnHoaDon.setIcon(resizeIcon("icon/bill.png", 30, 30));
		btnNhanVien.setIcon(resizeIcon("icon/person.png", 30, 30));
		btnThongKe.setIcon(resizeIcon("icon/money.png", 30, 30));
		btnKhachHang.setIcon(resizeIcon("icon/customer.png", 30, 30));

		//
		JButton[] buttons = {btnTrangChinh, btnBanHang, btnSanPham, btnHoaDon, btnNhanVien, btnThongKe, btnKhachHang};
		for (JButton btn : buttons) {
		    btn.setBackground(btnColor);
		    btn.setForeground(Color.WHITE);
		    btn.setFont(btnFont);
		    btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		    btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55)); // full width
		    btn.setBorderPainted(false);
		    btn.setFocusPainted(false);
		    btn.setIconTextGap(5);
		}
		btnTrangChinh.setFont(new Font("Arial", Font.BOLD, 17)); // fit
		btnThoat.setBackground(Color.white);
		btnThoat.setForeground(Color.black);
		btnThoat.setFont(btnFont);
		btnThoat.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnThoat.setBorderPainted(false);
		btnThoat.setFocusPainted(false);
	    //
		bWest.add(btnTrangChinh);
		bWest.add(Box.createVerticalStrut(2));
		bWest.add(btnBanHang);
		bWest.add(Box.createVerticalStrut(2));
		bWest.add(btnSanPham);
		bWest.add(Box.createVerticalStrut(2));
		bWest.add(btnHoaDon);
		bWest.add(Box.createVerticalStrut(2));
		bWest.add(btnKhachHang);
		bWest.add(Box.createVerticalStrut(2));
		bWest.add(btnNhanVien);
		bWest.add(Box.createVerticalStrut(2));
		bWest.add(btnThongKe);
		bWest.add(createWhiteLine(4));
		bWest.add(Box.createVerticalStrut(8));
		bWest.add(btnThoat);
		add(pWest, BorderLayout.WEST); 

		//
		JPanel pCenter = new JPanel(new CardLayout());
		add(pCenter);
		//
		JPanel trangChinhPanel = new TrangChinh();
		JPanel banHangPanel = new QuanLyBanHang();
		JPanel sanPhamPanel = new QuanLySanPham();
		JPanel hoaDonPanel = new QuanLyHoaDon();
		JPanel nhanVienPanel = new QuanLyNhanVien();
		JPanel thongKePanel = new QuanLyThongKe();
		JPanel khachHangPanel = new QuanLyKhachHang();
		//
		pCenter.add(trangChinhPanel, "TrangChinh");
		pCenter.add(banHangPanel, "BanHang");
		pCenter.add(sanPhamPanel, "SanPham");
		pCenter.add(hoaDonPanel, "HoaDon");
		pCenter.add(khachHangPanel, "KhachHang");
		pCenter.add(nhanVienPanel, "NhanVien");
		pCenter.add(thongKePanel, "ThongKe");
		
		//
		CardLayout cardLayout = (CardLayout) pCenter.getLayout();
		btnTrangChinh.addActionListener(e -> cardLayout.show(pCenter, "TrangChinh"));
		btnBanHang.addActionListener(e -> cardLayout.show(pCenter, "BanHang"));
		btnSanPham.addActionListener(e -> cardLayout.show(pCenter, "SanPham"));
		btnHoaDon.addActionListener(e -> cardLayout.show(pCenter, "HoaDon"));
		btnNhanVien.addActionListener(e -> cardLayout.show(pCenter, "NhanVien"));
		btnThongKe.addActionListener(e -> cardLayout.show(pCenter, "ThongKe"));
		btnKhachHang.addActionListener(e-> cardLayout.show(pCenter,"KhachHang"));
		btnThoat.addActionListener(this);
		hienThi();
		
	}

	public static void main(String[] args) {
		new DangNhap().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnThoat) {
			new DangNhap();
			setVisible(false);
		}
		
	}
	public static ImageIcon resizeIcon(String path, int width, int height) {
	    ImageIcon icon = new ImageIcon(path);
	    Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    return new ImageIcon(img);
	}
	public JPanel createWhiteLine(int thickness) {
	    JPanel line = new JPanel();
	    line.setMaximumSize(new Dimension(Integer.MAX_VALUE, thickness));
	    line.setPreferredSize(new Dimension(Integer.MAX_VALUE, thickness));
	    line.setBackground(Color.WHITE);
	    return line;
	}
	
	public void hienThi() {
		if (DangNhap.vaiTro.equalsIgnoreCase("nhân viên")) {
			btnNhanVien.setEnabled(false);
			btnNhanVien.setBackground(new Color(209, 52, 52));
			btnNhanVien.setToolTipText("Chức năng chỉ dành cho Quản Lý");
		} else {
			btnNhanVien.setEnabled(true);
		}
	}
	
	public static String getTenDangNhap() {
		return DangNhap.tenNhanVien;
	}


}
