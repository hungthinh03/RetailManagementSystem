package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.LoaiSanPham_DAO;
import dao.QuanLyKhachHang_DAO;
import dao.QuanLyNhanVien_DAO;
import dao.SanPham_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;

public class QuanLyBanHang extends JPanel implements ActionListener, ItemListener, TableModelListener{
	private DefaultTableModel modelSP;
	private JTable tableSP;
	private JLabel lblGiamGia;
	private JTextField txtGiamGia;
	private JLabel lblTong;
	private JTextField txtTong;
	private JButton btnThanhToan;
	private JLabel lblHoaDon;
	private JLabel lblMa;
	private JTextField txtMa;
	private JLabel lblNV;
	private JTextField txtNV;
	private JLabel lblNgay;
	private JDateChooser txtNgay;
	private JLabel lblGio;
	private JTextField txtGio;
	private JLabel lblKH;
	private JTextField txtKH;
	private JLabel lblDT;
	private JTextField txtDT;
	private SanPham_DAO dao;
	private ChiTietHoaDon_DAO cthd_dao;
	private QuanLyNhanVien_DAO nv_dao;
	private QuanLyKhachHang_DAO kh_dao;
	private HoaDon_DAO hd_dao;
	public QuanLyBanHang() {
		setSize(1200, 700);
		setVisible(true);
		setLayout(new BorderLayout(0, 0));
		
		//
		dao= new SanPham_DAO();
		cthd_dao = new ChiTietHoaDon_DAO();
		nv_dao= new QuanLyNhanVien_DAO();
		kh_dao= new QuanLyKhachHang_DAO();
		hd_dao= new HoaDon_DAO();
		ArrayList<SanPham> dssp= dao.getalltbSanPham();
		
		//pnNorth
		JPanel pNorth = new JPanel();
		pNorth.setBackground(Color.red);
		add(pNorth, BorderLayout.NORTH);
		//
		JLabel lblTieuDe = new JLabel("QUẢN LÝ BÁN HÀNG ");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 22));
		lblTieuDe.setForeground(Color.white);
		lblTieuDe.setVerticalAlignment(SwingConstants.BOTTOM);
		pNorth.add(lblTieuDe);
		
		//		
		int soSP = dssp.size();
		int cols = 3;
		int rows = (int) Math.ceil(soSP / (double) cols);
		JPanel pCenter = new JPanel(new GridLayout(rows, cols, 0, 0));
		for (SanPham sp : dssp) {
		    JToggleButton btn = new JToggleButton (
		    	    "<html><center>"
		    	    + "<b>" + sp.getTenSP() 
		    	    + "</b><br>Giá: " 
		    	    + sp.tinhGiaBan() 
		    	    + "đ<br>" 
		    	    + "</center></html>"
		    );
		    btn.setPreferredSize(new Dimension(40, 100)); 
		    btn.setHorizontalAlignment(SwingConstants.CENTER);
		    btn.setVerticalTextPosition(SwingConstants.CENTER);
		    btn.setFocusPainted(false);
		    //
		    btn.putClientProperty("product", sp); //Lưu trữ sp vào btn
		    btn.addItemListener(this);
		    //
		    pCenter.add(btn);
		}
		//JScrollPane
		JScrollPane scrollPane = new JScrollPane(pCenter);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		//
		JTabbedPane tabbedCenter = new JTabbedPane();
		tabbedCenter.addTab("Tất cả sản phẩm", scrollPane);
		add(tabbedCenter);

		
		//
		JPanel pEast= new JPanel();
		pEast.setLayout(new BorderLayout());
		pEast.setPreferredSize(new Dimension(550,1000));
		add(pEast, BorderLayout.EAST);
		//
		JPanel pTop= new JPanel();
		pTop.setLayout(new BorderLayout());
		pEast.add(pTop, BorderLayout.NORTH);
		Box bTop, bt1, bt2, bt3, bt4;
		pTop.add(bTop= Box.createVerticalBox());
		bTop.add(bt1= Box.createHorizontalBox());
		bt1.add(Box.createVerticalStrut(50));
		bt1.add(lblHoaDon= new JLabel("HÓA ĐƠN THANH TOÁN"));
		bt1.add(Box.createVerticalStrut(30));
		lblHoaDon.setForeground(Color.RED);
		lblHoaDon.setFont(new Font("Arial", Font.BOLD, 18));
		//
		bTop.add(bt2= Box.createHorizontalBox());
		bt2.add(Box.createHorizontalStrut(25));
		bt2.add(lblMa= new JLabel("Mã HĐ: "));
		bt2.add(Box.createHorizontalStrut(10));
		bt2.add(txtMa= new JTextField(20));
		bt2.add(Box.createHorizontalStrut(20));
		bt2.add(lblNV= new JLabel("Nhân viên: "));
		bt2.add(Box.createHorizontalStrut(10));
		bt2.add(txtNV= new JTextField(20));
		bt2.add(Box.createHorizontalStrut(25));
		bTop.add(Box.createVerticalStrut(3));
		//
		bTop.add(bt3= Box.createHorizontalBox());
		bt3.add(Box.createHorizontalStrut(25));
		bt3.add(lblNgay= new JLabel("Ngày: "));
		bt3.add(Box.createHorizontalStrut(12));
		bt3.add(txtNgay= new JDateChooser());
		txtNgay.setLocale(new Locale("vi", "VN")); //Định dạng tiếng việt
		bt3.add(Box.createHorizontalStrut(40));
		bt3.add(lblGio= new JLabel("Giờ vào: "));
		bt3.add(Box.createHorizontalStrut(10));
		bt3.add(txtGio= new JTextField(20));
		bt3.add(Box.createHorizontalStrut(25));
		bTop.add(Box.createVerticalStrut(3));
		//
		bTop.add(bt4= Box.createHorizontalBox());
		bt4.add(Box.createHorizontalStrut(25));
		//bt4.add(lblKH= new JLabel("Khách hàng: "));
		//bt4.add(Box.createHorizontalStrut(0));
		//bt4.add(txtKH= new JTextField(20));
		//bt4.add(Box.createHorizontalStrut(20));
		bt4.add(lblDT= new JLabel("SDT Khách: "));
		bt4.add(Box.createHorizontalStrut(2));
		bt4.add(txtDT= new JTextField(20));
		bt4.add(Box.createHorizontalStrut(283));
		bTop.add(Box.createVerticalStrut(15));
		lblMa.setPreferredSize(lblDT.getPreferredSize());
		lblNgay.setPreferredSize(lblDT.getPreferredSize());
		lblGio.setPreferredSize(lblDT.getPreferredSize());
		lblNV.setPreferredSize(lblDT.getPreferredSize());
		//lblDT.setPreferredSize(lblDT.getPreferredSize());
		//
		JPanel pMid= new JPanel();
		pMid.setBorder(BorderFactory.createEmptyBorder(0, 25, 15, 25)); // top, left, bottom, right
		pEast.add(pMid, BorderLayout.CENTER);
		String[] header = {"STT","Tên SP","SL","Đơn giá","Thành tiền"};
		modelSP = new DefaultTableModel(header, 0) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return column == 2; // SL editable
		    }
		};
		tableSP = new JTable(modelSP);
		tableSP.setBackground(Color.WHITE);
		pMid.setLayout(new BorderLayout()); // To allow full expansion
		pMid.add(new JScrollPane(tableSP), BorderLayout.CENTER);
		tableSP.getColumnModel().getColumn(0).setPreferredWidth(10);  // STT
		tableSP.getColumnModel().getColumn(2).setPreferredWidth(10);  // SL
		tableSP.getColumnModel().getColumn(1).setPreferredWidth(200); // Tên SP
		tableSP.getColumnModel().getColumn(3).setPreferredWidth(80); // Đơn giá
		tableSP.getColumnModel().getColumn(4).setPreferredWidth(80); // Thành tiền
		//
		JPanel pBot= new JPanel();
		pBot.setLayout(new BorderLayout());
		pEast.add(pBot, BorderLayout.SOUTH);
		pEast.setBorder(BorderFactory.createEmptyBorder(5, 5, 30, 5)); // top, left, bottom, right
		Box b2;
		pBot.add(b2= Box.createHorizontalBox());
		b2.add(Box.createHorizontalStrut(25));
		b2.add(lblGiamGia= new JLabel("Giảm giá (%): "));
		b2.add(Box.createHorizontalStrut(10));
		b2.add(txtGiamGia= new JTextField(20));
		b2.add(Box.createHorizontalStrut(20));
		b2.add(lblTong= new JLabel("Tổng tiền: "));
		b2.add(Box.createHorizontalStrut(10));
		b2.add(txtTong= new JTextField(20));
		b2.add(Box.createHorizontalStrut(20));
		b2.add(btnThanhToan= new JButton("Thanh toán"));
		b2.add(Box.createHorizontalStrut(25));
		
		//
		btnThanhToan.addActionListener(this);
		modelSP.addTableModelListener(this);
		setChiTietHoaDon();

		
	}
	public void setChiTietHoaDon() {
		String tenNV = Start.getTenDangNhap();
		LocalTime now = LocalTime.now();
		String nowTime = now.format(DateTimeFormatter.ofPattern("HH:mm"));
		//
		int newMaHD= hd_dao.getSoHoaDon() +1;
		//
		txtMa.setText(String.format("HD%03d", newMaHD)); // Tự tạo maHD mới
		txtNV.setText(tenNV);
		txtGio.setText(nowTime);
		txtGiamGia.setText("0");
		txtTong.setText("0");
		//
		txtMa.setEditable(false);
		txtNV.setEditable(false);
		txtTong.setEditable(false);
	}
	
	public void setTongTienHD() {
	    int tongTien = 0;
	    for (int i = 0; i < modelSP.getRowCount(); i++) {
	        Object value = modelSP.getValueAt(i, 4); // Thành Tiền
	        if (value != null) {
	            tongTien += Integer.parseInt(value.toString());
	        }
	    }
	    //
		int giamGia = 0;
		try {
		    giamGia = Integer.parseInt(txtGiamGia.getText().trim());
		    if (giamGia < 0 || giamGia > 100) {
		        throw new NumberFormatException();
		    }
		} 
		catch (NumberFormatException ex) {
		    JOptionPane.showMessageDialog(null, "Giảm giá phải là một số từ 0-100");
		    txtGiamGia.setText("0");
		    giamGia = 0;
		}
		txtTong.setText(String.valueOf(tongTien));
	}
	
	
	public HoaDon revertHoaDonFromField() 
	{
		String maHd, tenNv, sdt;
		LocalDate ngayLap;
		//
		maHd = txtMa.getText().trim();
		//
		NhanVien nv = new NhanVien();
		tenNv= txtNV.getText();
		nv= nv_dao.searchTen(tenNv);
		//
		KhachHang kh = new KhachHang();
		sdt= txtDT.getText();
		kh= kh_dao.searchSDT(sdt);
		//
		ngayLap= txtNgay.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		//
		return new HoaDon(maHd,ngayLap,nv,kh);
	}
	
	
	public void themHoaDonBanHang() {
		if (tableSP.getRowCount() == 0) 
		{	
			JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm");
			return;
		}
		if (validData())
		{
			HoaDon hd = revertHoaDonFromField();
			if (!kh_dao.kiemtraKhachHangTonTai(hd.getKhachHang().getMaKH())) {
				JOptionPane.showMessageDialog(this, "Khách hàng chưa tồn tại trong hệ thống, vui lòng thêm khách hàng trước!");
				txtDT.requestFocus();
				return;
			}
			
			//
			String[] options = {"Xác nhận", "Quay lại"};
			int confirm = JOptionPane.showOptionDialog(
			    this,
			    "Xác nhận thanh toán và lưu lại hóa đơn?",
			    "Xác nhận",
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    options,
			    options[0]
			);
			if (confirm != 0) //"Quay lại"
			{ 
				return;
			}

			//
			for (int i=0; i< tableSP.getRowCount();i++) 
			{
				String tenSP = tableSP.getValueAt(i, 1).toString();
				SanPham sp = dao.layMaSpTheoTen(tenSP);
				int soLuong = Integer.parseInt(tableSP.getValueAt(i, 2).toString());
				int giaBan = Integer.parseInt(tableSP.getValueAt(i,3).toString());
				ChiTietHoaDon cthd = new ChiTietHoaDon(hd, sp, giaBan, soLuong);
				hd_dao.create(hd);
				cthd_dao.create(cthd);
			}
			//
			JOptionPane.showMessageDialog(this, "Thanh toán thành công, đã lưu hóa đơn vào hệ thống!");
			modelSP.setRowCount(0);
			txtMa.setText("");
			LocalDate localDate = LocalDate.now();
			Date date = java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			txtNgay.setDate(date);
			txtDT.setText("");
			txtTong.setText("0");
			txtGiamGia.setText("0");
			//
			setChiTietHoaDon();
			
			restart();
		}
	}
	
	private boolean validData() {
		String maHD = txtMa.getText().trim();
		//
		Date dateNgayLap = txtNgay.getDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String ngayLap = sdf.format(dateNgayLap);
		//
		String sdt = txtDT.getText().trim();
		if (maHD.isEmpty()) {
			JOptionPane.showMessageDialog(this,"Mã hóa đơn không được để trống");
			txtMa.requestFocus();
			return false;
		}
		if (!maHD.matches("^(HD)\\d{3}")) {
			JOptionPane.showMessageDialog(this, "Mã hóa đơn bắt đầu bằng HD và theo sau là 3 kí số");
			txtMa.requestFocus();
			return false;
		}
		
		if (ngayLap.isEmpty()) {
			JOptionPane.showMessageDialog(this,"Ngày lập hóa đơn không được để trống");
			txtNgay.requestFocus();
			return false;
		}
		
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate ngaylap = LocalDate.parse(ngayLap,dtf);
			if (ngaylap.isAfter(LocalDate.now())) {
				JOptionPane.showMessageDialog(this, "Ngày lập hóa đơn không được sau ngày hiện tại");
				return false;
			}
		} catch (DateTimeException e) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng ngày (yyyy-MM-dd)");
			txtNgay.requestFocus();
			return false;
		}
		
		if (sdt.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Số điện thoại khách hàng không được trống");
			txtDT.requestFocus();
			return false;
		}
		
		if (!sdt.matches("^0\\d{9}") || sdt.length()!=10) {
			JOptionPane.showMessageDialog(this, "Số điện thoại phải gồm 10 kí số và bắt đầu bằng số 0");
			txtDT.requestFocus();
			return false;
		}
		return true;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o= e.getSource();
		if (o.equals(btnThanhToan)) {
			themHoaDonBanHang();
		}
	}
	
	
	public void itemStateChanged(ItemEvent i)
    {
		JToggleButton btn= (JToggleButton) i.getSource();	//Button chọn SP
		SanPham selectedSP = (SanPham) btn.getClientProperty("product");
		//
        if (btn.isSelected()) 
        {
        	String tenSP = selectedSP.getTenSP();
            int giaBan = selectedSP.tinhGiaBan();
            int soLuong = 1;
            int thanhTien = giaBan * soLuong;
            //
            Object[] row = {modelSP.getRowCount() + 1, tenSP, soLuong, giaBan, thanhTien};
            modelSP.addRow(row);
            // Focus ô SL
            int newRow = modelSP.getRowCount() - 1;
            tableSP.requestFocus();
            tableSP.changeSelection(newRow, 2, false, false);
            
            //
        	btn.setForeground(Color.WHITE);
        	btn.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        	btn.setBorder(new LineBorder(Color.WHITE, 2, true));
        	setTongTienHD();
	    } 
        else 
        {
            // Xóa SP từ bảng nếu không còn chọn
            String tenSP = selectedSP.getTenSP();
            for (int j= 0; j< modelSP.getRowCount(); j++) 
            {
                if (modelSP.getValueAt(j, 1).equals(tenSP)) 
                {
                    modelSP.removeRow(j);
                    break;
                }
            }
            // Đặt lại STT
            for (int k = 0; k < modelSP.getRowCount(); k++) {
                modelSP.setValueAt(k + 1, k, 0);
            }
            //
        	btn.setForeground(Color.BLACK);
        	btn.setBorder(UIManager.getBorder("Button.border"));
        	btn.setBackground(UIManager.getColor("Button.background")); // default
        	setTongTienHD();
	    }
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
    }
	@Override
	public void tableChanged(TableModelEvent e) {
	    if (e.getType() == TableModelEvent.UPDATE) 
	    {
	        int row = e.getFirstRow();
	        int column = e.getColumn();
	        
	        // Kiểm tra nếu chỉnh sửa cột SL (index 2)
	        if (column == 2) 
	        {
	            Object value = modelSP.getValueAt(row, column);
	            try {
	                int soLuong = Integer.parseInt(value.toString());
	                if (soLuong <= 0) {
	                    throw new NumberFormatException(); // Không cho <= 0
	                }
	                // Cập nhật Thành Tiền
	                int giaBan = (int) modelSP.getValueAt(row, 3);
	                modelSP.setValueAt(soLuong * giaBan, row, 4);
	                setTongTienHD();
	                
	            } 
	            catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(null, "Vui lòng nhập số nguyên dương cho Số lượng!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
	                modelSP.setValueAt(1, row, column); // Reset
	                // Cập nhật Thành Tiền
	                int giaBan = (int) modelSP.getValueAt(row, 3);
	                modelSP.setValueAt(giaBan, row, 4);
	                setTongTienHD();
	            }
	        }
	    }
	}
	
	public void restart()
	{
	    Window currentWindow = SwingUtilities.getWindowAncestor(this);
	    if (currentWindow != null) {
	        currentWindow.dispose(); 
	    }

	    new Start();
	}
}








