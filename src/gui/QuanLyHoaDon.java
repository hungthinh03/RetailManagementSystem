package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.SanPham_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.SanPham;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.chrono.JapaneseDate;
import java.util.ArrayList;
import java.util.List;

public class QuanLyHoaDon extends JPanel implements ActionListener, MouseListener {
    private DefaultTableModel tableQuanlyHoaDon;
    private JTable tablehoadon;
    private JLabel lblmahd, lblmanv, lblmakh, lblngay;
    private JTextField txtmahd, txtmanv, txtmakh, txtngay, txttimkiemhd;
    private JButton btntimkiem, btnxemcthd, btnXoaRong, btnLamMoi;
    private ChiTietHoaDon_DAO cthd_dao;
    private HoaDon_DAO hd_dao;

    public QuanLyHoaDon() {
        setSize(900, 600);
        setLayout(new BorderLayout(0, 0));
        cthd_dao = new ChiTietHoaDon_DAO();
        hd_dao = new HoaDon_DAO();

        // pnNorth
        JPanel pnNorth = new JPanel();
        pnNorth.setBackground(Color.red);
        JLabel lblieude = new JLabel("QUẢN LÝ HÓA ĐƠN");
        pnNorth.add(lblieude);
        lblieude.setFont(new Font("Arial", Font.BOLD, 22));
        lblieude.setForeground(Color.WHITE);
        add(pnNorth, BorderLayout.NORTH);

        // pnWest
        JPanel pnWest = new JPanel(new BorderLayout());
        pnWest.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 20));

        // Panel tìm kiếm
        JPanel searchPanel = new JPanel();
        searchPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Tìm Kiếm", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 14)));
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));

        Box bWest = Box.createVerticalBox();
        bWest.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
        Box bma, bngay, bmanv, bmakh, btimkiem;

        bWest.add(Box.createVerticalStrut(20));

        bWest.add(bma = Box.createHorizontalBox());
        bma.add(lblmahd = new JLabel("Mã Hóa Đơn:"));
        bma.add(Box.createHorizontalStrut(10));
        bma.add(txtmahd = new JTextField(20));
        bWest.add(Box.createVerticalStrut(10));

        bWest.add(bngay = Box.createHorizontalBox());
        bngay.add(lblngay = new JLabel("Ngày lập:"));
        bngay.add(Box.createHorizontalStrut(10));
        bngay.add(txtngay = new JTextField(20));
        bWest.add(Box.createVerticalStrut(10));

        bWest.add(bmanv = Box.createHorizontalBox());
        bmanv.add(lblmanv = new JLabel("Mã nhân viên:"));
        bmanv.add(Box.createHorizontalStrut(10));
        bmanv.add(txtmanv = new JTextField(20));
        bWest.add(Box.createVerticalStrut(10));

        bWest.add(bmakh = Box.createHorizontalBox());
        bmakh.add(lblmakh = new JLabel("Mã khách hàng:"));
        bmakh.add(Box.createHorizontalStrut(10));
        bmakh.add(txtmakh = new JTextField(20));
        bWest.add(Box.createVerticalStrut(10));

        bWest.add(btimkiem = Box.createHorizontalBox());
        btimkiem.add(txttimkiemhd = new JTextField(15));
        btimkiem.add(Box.createHorizontalStrut(10));
        btimkiem.add(btntimkiem = new JButton("Tìm kiếm"));
        bWest.add(Box.createVerticalStrut(10));

        Dimension lblSize = new Dimension(110, 25);
        lblmahd.setPreferredSize(lblSize);
        lblngay.setPreferredSize(lblSize);
        lblmanv.setPreferredSize(lblSize);
        lblmakh.setPreferredSize(lblSize);

        searchPanel.add(bWest);

        pnWest.add(searchPanel, BorderLayout.NORTH);

        // Panel chức năng khác
        JPanel functionPanel = new JPanel();
        functionPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Chức năng khác", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 14)));
        functionPanel.setLayout(new BoxLayout(functionPanel, BoxLayout.Y_AXIS));

        // Button Xuất hóa đơn
        btnxemcthd = new JButton("Xem Chi Tiết Hóa Đơn");
        btnxemcthd.setAlignmentX(Component.CENTER_ALIGNMENT);
        functionPanel.add(btnxemcthd);
        functionPanel.add(Box.createVerticalStrut(10)); // Khoảng cách 5px

        // Button Làm mới
        btnLamMoi = new JButton("Làm mới");
        btnLamMoi.setAlignmentX(Component.CENTER_ALIGNMENT);
        functionPanel.add(btnLamMoi);
        functionPanel.add(Box.createVerticalStrut(10)); // Khoảng cách 5px

        // Button Xóa rỗng
        btnXoaRong = new JButton("Xóa rỗng");
        btnXoaRong.setAlignmentX(Component.CENTER_ALIGNMENT);
        functionPanel.add(btnXoaRong);

        pnWest.add(functionPanel, BorderLayout.CENTER);
        add(pnWest, BorderLayout.WEST);

        // Table
        Box bhd = Box.createHorizontalBox();
        String[] headers = "Mã hóa đơn;Ngày lập;Mã nhân viên;Mã khách hàng".split(";");
        tableQuanlyHoaDon = new DefaultTableModel(headers, 0);
        JScrollPane scroll = new JScrollPane(tablehoadon = new JTable(tableQuanlyHoaDon));
        tablehoadon.setRowHeight(25);
        tablehoadon.setAutoCreateRowSorter(true);
        tablehoadon.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        bhd.add(scroll);
        bhd.setBorder(BorderFactory.createEmptyBorder(18, 10, 12, 10));
        add(bhd, BorderLayout.CENTER);

        DocDuLieuVaoTableHoaDon();

        tablehoadon.addMouseListener(this);
        btntimkiem.addActionListener(this);
        btnXoaRong.addActionListener(this);
        btnLamMoi.addActionListener(this);
        btnxemcthd.addActionListener(this);
    }

    public void DocDuLieuVaoTableHoaDon() {
        ArrayList<ChiTietHoaDon> list = cthd_dao.getallChiTietHoaDon();
        tableQuanlyHoaDon.setRowCount(0); // Xóa sạch bảng trước khi load
        ArrayList<String> themMaHD = new ArrayList<>();
        for (ChiTietHoaDon h : list) 
        {
        	String[] rowData = {h.getHoaDon().getMaHD(), h.getHoaDon().getNgayLap().toString(), h.getHoaDon().getNhanVien().getMaNV(), h.getHoaDon().getKhachHang().getMaKH()};
        	if (!themMaHD.contains(h.getHoaDon().getMaHD()))  // Tránh trùng lặp dòng
        	{
        		tableQuanlyHoaDon.addRow(rowData);
        		themMaHD.add(h.getHoaDon().getMaHD());
        	}
        }
        tablehoadon.setModel(tableQuanlyHoaDon);
    }

    public void timHoaDon() {
        String mahd = txttimkiemhd.getText().trim();
        ArrayList<HoaDon> listHd= new ArrayList<HoaDon>();
        listHd= hd_dao.getalltbHoaDon();
        HoaDon hd = hd_dao.searchHD(mahd,listHd);
        tableQuanlyHoaDon.setRowCount(0);
        if (hd != null) {
            tableQuanlyHoaDon.addRow(new Object[]{
                    hd.getMaHD(),
                    hd.getNgayLap().toString(),
                    hd.getNhanVien().getMaNV(),
                    hd.getKhachHang().getMaKH()
            });
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn có mã " + mahd);
            txtmahd.requestFocus();
            DocDuLieuVaoTableHoaDon();
        }
    }

    private void xoaRong() {
        txtmahd.setText("");
        txtngay.setText("");
        txtmanv.setText("");
        txtmakh.setText("");
        txttimkiemhd.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == btntimkiem) {
            timHoaDon();
        } 
        else if (o == btnXoaRong) {
            xoaRong();
        } 
        else if (o == btnLamMoi) {
            DocDuLieuVaoTableHoaDon();
        }
        else if (o == btnxemcthd) 
        {  	// Xử lý nút xem chi tiết hóa đơn
            int row = tablehoadon.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn cần xem chi tiết");
            } 
            else 
            {
                String maHD = tableQuanlyHoaDon.getValueAt(row, 0).toString();
                new QuanLyCTHD(maHD).setVisible(true);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = tablehoadon.getSelectedRow();
        if (row != -1) {
            txtmahd.setText(tableQuanlyHoaDon.getValueAt(row, 0).toString());
            txtngay.setText(tableQuanlyHoaDon.getValueAt(row, 1).toString());
            txtmanv.setText(tableQuanlyHoaDon.getValueAt(row, 2).toString());
            txtmakh.setText(tableQuanlyHoaDon.getValueAt(row, 3).toString());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}