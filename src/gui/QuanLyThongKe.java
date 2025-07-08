package gui;

import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.ChiTietHoaDon_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.BaseFont;

public class QuanLyThongKe extends JPanel implements ActionListener {
	private JLabel lblBDau;
	private JLabel lblKThuc;
	private JLabel lblNV;
	private JDateChooser txtBDau;
	private JDateChooser txtKThuc;
	private JTextField txtNV;
	private DefaultTableModel modelTK;
	private JTable tableTK;
	private JLabel lblThongKe;
	private JButton btnLamMoi;
	private JButton btnDT;
	private JLabel lbThang;
	private JLabel lbNam;
	private JTextField txtThang;
	private JTextField txtNam;
	private JLabel lblSP;
	private JTextField txtSP;
	private JButton btnTK;
	private ChiTietHoaDon_DAO cthd_dao;

	public QuanLyThongKe() {
		setSize(900, 600);
		setVisible(true);
		setLayout(new BorderLayout(0, 0));
		
		//
		cthd_dao= new ChiTietHoaDon_DAO();
		
		//pnNorth
		JPanel pNorth = new JPanel();
		pNorth.setBackground(Color.red);
		add(pNorth, BorderLayout.NORTH);
		//
		JLabel lblTieuDe = new JLabel("THỐNG KÊ BÁN HÀNG ");
		//lblTieuDe.setFont(new Font("Arial", Font.BOLD, 22));
		lblTieuDe.setForeground(Color.white);
		lblTieuDe.setVerticalAlignment(SwingConstants.BOTTOM);
		//pNorth.add(lblTieuDe);
		
		//pCenter
		JPanel pCenter = new JPanel();
		add(pCenter, BorderLayout.CENTER);
		pCenter.setBorder(new LineBorder(new Color(0, 0, 0)));
		pCenter.setLayout(new BorderLayout(0, 0));
		//
		JPanel pTop = new JPanel();
		pCenter.add(pTop, BorderLayout.NORTH);
		Box bCenter= Box.createVerticalBox();
		pTop.add(bCenter);
		//Box b1 = Box.createHorizontalBox();
		Box b1, b2;
		bCenter.add(Box.createVerticalStrut(10));
		bCenter.add(b1= Box.createHorizontalBox());
		bCenter.add(Box.createVerticalStrut(5));
		bCenter.add(b2= Box.createHorizontalBox());
		//
		b1.add(lblThongKe = new JLabel("Thống kê theo thời gian: "));
		//
		b2.add(lblBDau= new JLabel("Từ ngày: "));
		b2.add(txtBDau = new JDateChooser());
		b2.add(Box.createHorizontalStrut(50));
		b2.add(lblKThuc= new JLabel("Đến ngày: "));
		b2.add(txtKThuc = new JDateChooser());
		b2.add(Box.createHorizontalStrut(20));
		txtBDau.setLocale(new Locale("vi", "VN")); //Định dạng tiếng việt
		txtKThuc.setLocale(new Locale("vi", "VN"));
		//lblThongKe.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblThongKe.setForeground(Color.red);
		//
		JPanel pTable= new JPanel();
		pCenter.add(pTable, BorderLayout.CENTER);
		//
		pTable.setBorder(BorderFactory.createTitledBorder(" Danh sách thống kê:  "));
		pTable.setLayout(new BorderLayout(0, 0));
		//
		String[] header = {"STT","Mã HĐ","Mã SP","Tên SP","Số lượng","Mã NV","Tên NV","Ngày lập","Thành tiền"};
		modelTK= new DefaultTableModel(header, 0);
		tableTK = new JTable(modelTK);
		tableTK.setBackground(Color.WHITE);
		pTable.add(tableTK);
		pTable.add(new JScrollPane(tableTK));
	    
		//pSouth
		JPanel pSouth = new JPanel();
		pSouth.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(pSouth, BorderLayout.SOUTH);
		pSouth.setLayout(new BorderLayout(0, 0));
		//
		pSouth.setBorder(BorderFactory.createTitledBorder(" Bộ lọc tìm kiếm:	 "));
		Box bSouth = Box.createVerticalBox();
		pSouth.add(bSouth);
		bSouth.add(Box.createVerticalStrut(5));
		Box bs = Box.createHorizontalBox();
		bSouth.add(bs);
		bSouth.add(Box.createVerticalStrut(50));
		//
		bs.add(Box.createHorizontalStrut(50));
		bs.add(lblSP= new JLabel("Theo mã sản phẩm: "));
		bs.add(txtSP= new JTextField(20));
		bs.add(Box.createHorizontalStrut(30));
		bs.add(lblNV= new JLabel("Theo mã nhân viên: "));
		bs.add(txtNV= new JTextField(20));
		bs.add(Box.createHorizontalStrut(50));
		bs.add(btnTK = new JButton("Tìm kiếm"));
		bs.add(Box.createHorizontalStrut(20));
		bs.add(btnDT = new JButton("Thống kê doanh thu"));
		bs.add(Box.createHorizontalStrut(50));
		
		//
		btnTK.addActionListener(this);
		btnDT.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o= e.getSource();
		if (o == btnTK) {
			//
			while(modelTK.getRowCount()> 0)
			{
				modelTK.removeRow(0);
			}
			//
		    ArrayList<ChiTietHoaDon> list = cthd_dao.getallChiTietHoaDon();
		    int stt = 1;

		    // Add rows that match the date range filter first
		    for (ChiTietHoaDon cthd : list) {
		        LocalDate ngayLap = cthd.getHoaDon().getNgayLap();
		        Date startDate = txtBDau.getDate(); 
		        Date endDate = txtKThuc.getDate();
		        LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		        LocalDate localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		        // Check if the date of the invoice is within the range
		        if (!ngayLap.isBefore(localStartDate) && !ngayLap.isAfter(localEndDate)) {
		            boolean rowExists = false;

		            // Check for duplicates in the table before adding the row
		            for (int i = 0; i < modelTK.getRowCount(); i++) {
		                String maHD = (String) modelTK.getValueAt(i, 1);
		                String maSP = (String) modelTK.getValueAt(i, 2);

		                if (maHD.equals(cthd.getHoaDon().getMaHD()) && maSP.equals(cthd.getSanPham().getMaSP())) {
		                    rowExists = true;
		                    break;
		                }
		            }

		            // If no duplicate, add the row
		            if (!rowExists) {
		                double thanhTien = cthd.getGiaBan() * cthd.getSoLuong();
		                String[] rowData = {
		                    String.valueOf(stt),
		                    cthd.getHoaDon().getMaHD(),
		                    cthd.getSanPham().getMaSP(),
		                    cthd.getSanPham().getTenSP(),
		                    String.valueOf(cthd.getSoLuong()),
		                    cthd.getHoaDon().getNhanVien().getMaNV(),
		                    cthd.getHoaDon().getNhanVien().getTenNV(),
		                    String.valueOf(cthd.getHoaDon().getNgayLap()),
		                    String.valueOf(thanhTien)
		                };
		                modelTK.addRow(rowData);
		                stt++;
		            }
		        }
		    }

		    // After all rows are added, now filter by NV and SP if needed
		    String locNV = txtNV.getText().trim();
		    String locSP = txtSP.getText().trim();

		    // Loop through the rows in the model and remove rows that don't match the filters
		    for (int i = modelTK.getRowCount() - 1; i >= 0; i--) {
		        boolean removeRow = false;

		        // If locNV is not empty and does not match NV value in the row
		        if (!locNV.isEmpty() && !modelTK.getValueAt(i, 5).toString().contains(locNV)) {
		            removeRow = true;
		        }

		        // If locSP is not empty and does not match SP value in the row
		        if (!locSP.isEmpty() && !modelTK.getValueAt(i, 2).toString().contains(locSP)) {
		            removeRow = true;
		        }

		        // Remove the row if it doesn't match the filters
		        if (removeRow) {
		            modelTK.removeRow(i);
		        }
		    }

		    // If no rows remain, show a message indicating no results found
		    if (modelTK.getRowCount() == 0) {
		        JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn đạt chỉ tiêu tìm kiếm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		    }

		    // Update the table model
		    tableTK.setModel(modelTK);
		}

		else if (o == btnDT) 
		{
		    try {
		        // Tạo tài liệu PDF
		        Document document = new Document();
		        PdfWriter.getInstance(document, new FileOutputStream("data/ThongKe.pdf"));
		        document.open();

		        BaseFont baseFont = BaseFont.createFont("font/arialbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		        Font pdfFont = new Font(baseFont, 12);

		        // Tính tổng doanh thu, SP bán nhiều nhất, NV bán nhiều HD nhất
		        double tongTien = 0.0;
		        Map<String, Integer> productSales = new HashMap<>();
		        Map<String, Integer> employeeSales = new HashMap<>();

		        // Duyệt qua bảng để tính toán
		        for (int i = 0; i < tableTK.getRowCount(); i++) {
		            // Adjusted the column index for "Thành tiền" to 8 (previously was 10)
		            double thanhTien = Double.parseDouble(tableTK.getValueAt(i, 8).toString()); // Column "Thành tiền"
		            String maSP = tableTK.getValueAt(i, 2).toString(); // Column "Mã SP"
		            String maNV = tableTK.getValueAt(i, 5).toString(); // Column "Mã NV"
		            tongTien += thanhTien;

		            // Tính số lượng bán của sản phẩm
		            productSales.put(maSP, productSales.getOrDefault(maSP, 0) + 1);
		            // Tính số lượng hóa đơn của nhân viên
		            employeeSales.put(maNV, employeeSales.getOrDefault(maNV, 0) + 1);
		        }

		        // Tìm sản phẩm bán nhiều nhất
		        String bestSellingProduct = productSales.entrySet().stream()
		            .max(Comparator.comparingInt(Map.Entry::getValue))
		            .map(Map.Entry::getKey)
		            .orElse("Không có sản phẩm bán nhiều nhất");

		        // Tìm nhân viên bán nhiều hóa đơn nhất
		        String bestSellingEmployee = employeeSales.entrySet().stream()
		            .max(Comparator.comparingInt(Map.Entry::getValue))
		            .map(Map.Entry::getKey)
		            .orElse("Không có nhân viên bán nhiều hóa đơn nhất");

		        // Thêm tiêu đề và thông tin tổng quan
		        document.add(new Paragraph("Thống Kê Hóa Đơn", pdfFont));
		        document.add(new Paragraph(" "));
		        document.add(new Paragraph("Tổng doanh thu: " + tongTien, pdfFont));
		        document.add(new Paragraph("Sản phẩm bán nhiều nhất: " + bestSellingProduct, pdfFont));
		        document.add(new Paragraph("Nhân viên bán nhiều hóa đơn nhất: " + bestSellingEmployee, pdfFont));
		        document.add(new Paragraph("------------------------------------------------------------------------"));

		        // Thêm thông tin chi tiết của hóa đơn
		        
		        for (int i = 0; i < tableTK.getRowCount(); i++) {
		            String maHD = tableTK.getValueAt(i, 1).toString(); // Column "Mã HĐ"
		            String maSP = tableTK.getValueAt(i, 2).toString(); // Column "Mã SP"
		            String tenSP = tableTK.getValueAt(i, 3).toString(); // Column "Tên SP"
		            String soLuong = tableTK.getValueAt(i, 4).toString(); // Column "Số lượng"
		            String maNV = tableTK.getValueAt(i, 5).toString(); // Column "Mã NV"
		            String tenNV = tableTK.getValueAt(i, 6).toString(); // Column "Tên NV"
		            String ngayLap = tableTK.getValueAt(i, 7).toString(); // Column "Ngày lập"
		            double thanhTien = Double.parseDouble(tableTK.getValueAt(i, 8).toString()); // Column "Thành tiền"

		            // Add each row's details to the document
		            document.add(new Paragraph("\n------------------------ "+"Mã HĐ: " + maHD + "\n Mã NV: " + maNV + "\n Tên NV: " + tenNV + "\n Mã SP: " + maSP + "\n Tên SP: " + tenSP + 
		                                      "\n Số lượng: " + soLuong + "\n Ngày lập: " + ngayLap + "\n Thành tiền: " + thanhTien, pdfFont));
		        }
		        

		        document.close();
		        JOptionPane.showMessageDialog(this, "Xuất PDF thành công!");
		    } catch (Exception ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(this, "Lỗi khi xuất PDF!");
		    }
		}

		
	}

}
