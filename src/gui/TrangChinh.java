package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class TrangChinh extends JPanel {

	public TrangChinh() {
		ImageIcon iconCuaHang = new ImageIcon("icon/main2.jpg");
		Image imgCuaHang = iconCuaHang.getImage();
		Image resizedImg = imgCuaHang.getScaledInstance(1100, 800, Image.SCALE_SMOOTH);
		ImageIcon resizedIconCuaHang = new ImageIcon(resizedImg);
		JLabel lblImage = new JLabel(resizedIconCuaHang);
		lblImage.setHorizontalAlignment(JLabel.CENTER);

		
		Box b = Box.createVerticalBox();
		b.add(Box.createVerticalStrut(50));

		/*
		JLabel title = new JLabel("CỬA HÀNG TIỆN LỢI MESSIMART");
		title.setFont(new Font("Arial", Font.BOLD, 26));
		title.setForeground(Color.MAGENTA);
		title.setAlignmentX(CENTER_ALIGNMENT);
		b.add(title);

		*/
		b.add(Box.createVerticalStrut(50));
		
		/*
		JLabel lblName = new JLabel("CHỦ CỬA HÀNG: NGUYỄN GIA PHÚ");
		lblName.setFont(new Font("Arial", Font.BOLD, 19));
		lblName.setAlignmentX(CENTER_ALIGNMENT);
		b.add(lblName);

		ImageIcon iconAvt = new ImageIcon(("icon/owner.png"));
		Image imgAvt = iconAvt.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); 
		ImageIcon resizedIconAvt = new ImageIcon(imgAvt);
		JLabel lblOwner = new JLabel(resizedIconAvt);
		lblOwner.setAlignmentX(CENTER_ALIGNMENT);
		b.add(lblOwner);
		*/
		b.setBackground(new Color(255, 255, 204)); 
		b.setOpaque(true);
		
		
		add(lblImage, BorderLayout.CENTER);
		add(b,BorderLayout.EAST);
		setSize(800, 650);
	}
}
