package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
//import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.table.DefaultTableModel;


import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.BaseFont;


public class QuanLyCTHD extends JFrame implements ActionListener, MouseListener {
    private JTextField txtmahd, txtmanv, txttennv, txtmakh, txtngaylap, txttenkh, txttensp, txtongtien, txtmasp, txtsoluong;
    private JLabel lblmahd, lblmanv, lbltennv, lblmakh, lbltenkh, lbltensp, lblngaylap, lbltongtien, lblmasp, lblsoluong;
    private JButton btnxuathd;
    private DefaultTableModel tablemodel;
    private JTable table;
    private String maHD;
	private ChiTietHoaDon_DAO cthd_dao;
	private double tongtien=0;

    public QuanLyCTHD(String maHD) {
        this.maHD = maHD;
        cthd_dao = new ChiTietHoaDon_DAO();
        setVisible(true);
        setTitle("Giao diện chi tiết hóa đơn");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 300);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // pnNorth
        JPanel pnNorth = new JPanel();
        pnNorth.setBackground(Color.red);
        JLabel lblieude = new JLabel("Chi Tiết Hóa Đơn");
        lblieude.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 22)); 
        lblieude.setForeground(Color.WHITE);
        pnNorth.add(lblieude);
        add(pnNorth, BorderLayout.NORTH);

        // pnCenter
        Box b = Box.createVerticalBox();
        Box b1, b2, b3, b4, b5, b6, b7;

        Dimension txtSize = new Dimension(200, 25);

        b.add(Box.createVerticalStrut(10));
        b.add(b1 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b1.add(lblmahd = new JLabel("Mã hóa đơn:"));
        b1.add(Box.createHorizontalStrut(10));
        b1.add(txtmahd = new JTextField());
        txtmahd.setPreferredSize(txtSize);
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lbltongtien = new JLabel("Tổng tiền:"));
        b1.add(Box.createHorizontalStrut(10));
        b1.add(txtongtien = new JTextField());
        txtongtien.setPreferredSize(txtSize);

        b.add(b2 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b2.add(lblmanv = new JLabel("Mã nhân viên:"));
        b2.add(Box.createHorizontalStrut(10));
        b2.add(txtmanv = new JTextField());
        txtmanv.setPreferredSize(txtSize);
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lbltennv = new JLabel("Tên nhân viên:"));
        b2.add(Box.createHorizontalStrut(10));
        b2.add(txttennv = new JTextField());
        txttennv.setPreferredSize(txtSize);

        b.add(b3 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b3.add(lblmakh = new JLabel("Mã khách hàng:"));
        b3.add(Box.createHorizontalStrut(10));
        b3.add(txtmakh = new JTextField());
        txtmakh.setPreferredSize(txtSize);
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lbltenkh = new JLabel("Tên khách hàng:"));
        b3.add(Box.createHorizontalStrut(10));
        b3.add(txttenkh = new JTextField());
        txttenkh.setPreferredSize(txtSize);

        b.add(b4 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b4.add(lblmasp = new JLabel("Mã sản phẩm:"));
        b4.add(Box.createHorizontalStrut(10));
        b4.add(txtmasp = new JTextField());
        txtmasp.setPreferredSize(txtSize);
        b4.add(Box.createHorizontalStrut(20));
        b4.add(lbltensp = new JLabel("Tên sản phẩm:"));
        b4.add(Box.createHorizontalStrut(10));
        b4.add(txttensp = new JTextField());
        txttensp.setPreferredSize(txtSize);

        b.add(b5 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b5.add(lblsoluong = new JLabel("Số lượng:"));
        b5.add(Box.createHorizontalStrut(10));
        b5.add(txtsoluong = new JTextField());
        txtsoluong.setPreferredSize(txtSize);
        b5.add(Box.createHorizontalStrut(20));
        b5.add(lblngaylap = new JLabel("Ngày lập:"));
        b5.add(Box.createHorizontalStrut(10));
        b5.add(txtngaylap = new JTextField());
        txtngaylap.setPreferredSize(txtSize);

        b.add(b6 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        String[] headers = "Mã hóa đơn;Mã nhân viên;Tên nhân viên;Mã khách hàng;Tên khách hàng;Mã sản phẩm;Tên sản phẩm;Đơn giá;Số lượng;Ngày lập;Thành tiền".split(";");
        tablemodel = new DefaultTableModel(headers, 0);
        table = new JTable(tablemodel);
        JScrollPane scroll = new JScrollPane(table);
        table.setRowHeight(25);
        table.setAutoCreateRowSorter(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        b6.add(scroll);

        b.add(Box.createVerticalStrut(10));
        b.add(b7 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        b7.add(Box.createHorizontalStrut(20));
        btnxuathd = new JButton("Xuất chi tiết hóa đơn");
        b7.add(btnxuathd);

        Dimension lblSize = new Dimension(110, 25);
        lblmahd.setPreferredSize(lblSize);
        lbltongtien.setPreferredSize(lblSize);
        lblmanv.setPreferredSize(lblSize);
        lbltennv.setPreferredSize(lblSize);
        lblmakh.setPreferredSize(lblSize);
        lbltenkh.setPreferredSize(lblSize);
        lblmasp.setPreferredSize(lblSize);
        lbltensp.setPreferredSize(lblSize);
        lblsoluong.setPreferredSize(lblSize);
        lblngaylap.setPreferredSize(lblSize);

        add(b, BorderLayout.CENTER);
        b.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        DocDuLieuVaoTable(maHD);
        table.addMouseListener(this);
        
        btnxuathd.addActionListener(this);
    }

    private void DocDuLieuVaoTable(String maHD) {
        ArrayList<ChiTietHoaDon> list = cthd_dao.getallChiTietHoaDon();
        for (ChiTietHoaDon cthd : list) {
            HoaDon hd = cthd.getHoaDon();
            if (hd.getMaHD().equals(maHD)) {
                double thanhTien = cthd.getGiaBan() * cthd.getSoLuong();
                tongtien+= thanhTien;
                String[] rowData = {
                	cthd.getHoaDon().getMaHD(),
                	cthd.getHoaDon().getNhanVien().getMaNV(),
                	cthd.getHoaDon().getNhanVien().getTenNV(),
                	cthd.getHoaDon().getKhachHang().getMaKH(),
                	cthd.getHoaDon().getKhachHang().getTenKH(),
                    cthd.getSanPham().getMaSP(),
                    cthd.getSanPham().getTenSP(),
                    String.valueOf(cthd.getGiaBan()),
                    String.valueOf(cthd.getSoLuong()),
                    String.valueOf(cthd.getHoaDon().getNgayLap()),
                    String.valueOf(thanhTien)
                };
                tablemodel.addRow(rowData);
            }
        }
        table.setModel(tablemodel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnxuathd) {
            // Lấy dữ liệu từ bảng và tạo PDF
            try {
            	double tongTien = 0.0;
                // Tạo tài liệu PDF
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream("data/ChiTietHoaDon.pdf"));
                document.open();
                
                BaseFont baseFont = BaseFont.createFont("font/arialbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font pdfFont = new Font(baseFont, 12);

                // Thêm tiêu đề
                document.add(new Paragraph("Chi Tiết Hóa Đơn",pdfFont));
                document.add(new Paragraph(" "));
                String maHD = table.getValueAt(0, 0).toString();
                String maNV = table.getValueAt(0, 1).toString();
                String tenNV = table.getValueAt(0, 2).toString();
                String maKH = table.getValueAt(0, 3).toString(); 
                String tenKH = table.getValueAt(0, 4).toString(); 
                document.add(new Paragraph("Mã hóa đơn: " + maHD,pdfFont));
                document.add(new Paragraph("Mã nhân viên: " + maNV + " - " + tenNV,pdfFont));
                document.add(new Paragraph("Mã khách hàng: " + maKH + " - " + tenKH,pdfFont));
                document.add(new Paragraph("------------------------------------------------------------------------"));
                // Thêm thông tin hóa đơn vào PDF
                for (int i = 0; i < table.getRowCount(); i++) {
                    String maSP = table.getValueAt(i, 5).toString();
                    String tenSP = table.getValueAt(i, 6).toString();
                    String donGia = table.getValueAt(i, 7).toString();
                    String soLuong = table.getValueAt(i, 8).toString();
                    String ngayLap = table.getValueAt(i, 9).toString();
                    double thanhTien = Double.parseDouble( table.getValueAt(i, 10).toString());
                    tongTien +=thanhTien;
                   
                    document.add(new Paragraph("Mã sản phẩm: " + maSP + " - " + tenSP,pdfFont));
                    document.add(new Paragraph("Đơn giá: " + donGia,pdfFont));
                    document.add(new Paragraph("Số lượng: " + soLuong,pdfFont));
                    document.add(new Paragraph("Ngày lập: " + ngayLap,pdfFont));
                    document.add(new Paragraph("Thành tiền: " + thanhTien,pdfFont));
                    document.add(new Paragraph(" "));
                    document.add(new Paragraph("------------------------------------------------------------------------"));
                 
                }
                document.add(new Paragraph("**************************************************************"));
                document.add(new Paragraph("Tổng tiền: "+ tongTien,pdfFont));

                // Đóng tài liệu PDF
                document.close();
                JOptionPane.showMessageDialog(this, "Xuất PDF thành công!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi xuất PDF!");
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table.getSelectedRow();
        txtmahd.setText(tablemodel.getValueAt(row, 0).toString());
        txtmanv.setText(tablemodel.getValueAt(row, 1).toString());
        txttennv.setText(tablemodel.getValueAt(row, 2).toString());
        txtmakh.setText(tablemodel.getValueAt(row, 3).toString());
        txttenkh.setText(tablemodel.getValueAt(row, 4).toString());
        txtmasp.setText(tablemodel.getValueAt(row, 5).toString());
        txttensp.setText(tablemodel.getValueAt(row, 6).toString());
        txtsoluong.setText(tablemodel.getValueAt(row, 8).toString());
        txtngaylap.setText(tablemodel.getValueAt(row, 9).toString());
        txtongtien.setText(String.valueOf(tongtien));
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

    public static void main(String[] args) {
        new QuanLyCTHD("HD");
    }
}
