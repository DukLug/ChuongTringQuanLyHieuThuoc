package userInterfaces;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import component.CustomTable;
import component.ImageUtilities;

//import connectDB.ConnectDB;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;

public class KhachHangUI extends JPanel implements ActionListener, MouseListener {
	private JTextField txtTim;
	private JTextField txtMaKH;
	private JTextField txtHoTen;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JTextField txtDTL;
	private JTable tableKH;
	private JRadioButton rdbtn50;
	private JRadioButton rdbtn200;
	private JRadioButton rdbtn500;
	private JRadioButton rdbtn600;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnXoaTrang;
	private DefaultTableModel modelKH;
	

	public KhachHangUI() {
		super();
		taoHinh();
		
		 // Khởi tạo kết nối đến cơ sở dữ liệu khi một thể hiện của NhanVien_UI được tạo ra
//        try {
//            ConnectDB.getInstance().connect();
//            System.out.println("Connect!!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
	}
	
	public void taoHinh() {
		setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
		this.setBackground(Color.WHITE);
		
setLayout(null);
		
		JPanel panelTong = new JPanel();
		panelTong.setBackground(new Color(232, 234, 236));
		panelTong.setBounds(0, 0, 1800, 850);
		add(panelTong);
		panelTong.setLayout(null);
		
		JPanel panelLoc = new JPanel();
		panelLoc.setBackground(Color.WHITE);
		panelLoc.setBounds(41, 81, 281, 727);
		panelTong.add(panelLoc);
		panelLoc.setBackground(new Color(232, 234, 236));
		panelLoc.setLayout(null);

		JPanel panelKhung = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Graphics2D g2d = (Graphics2D) g.create();
		       		        
		        int shadowSize = 10; // Kích thước bóng
		        int size = Math.min(getWidth(), getHeight() - 10); // Kích thước panel lớn hơn
//		        int size = Math.min(getWidth(), getHeight()) / 2;
		        
		        RoundRectangle2D border = new RoundRectangle2D.Double(0, 0, size, size - 5, 0, 0);
		        ImageUtilities.applyQualityRenderingHints(g2d);
		        g2d.drawImage(ImageUtilities.applyShadow(border, 4, getBackground(), Color.DARK_GRAY, 0.25f), 2, 2, this);
		        g2d.setColor(Color.BLACK);
		        g2d.translate(5, 5);
		        g2d.draw(border);
		        g2d.dispose();
		    }
		};
		panelKhung.setBackground(new Color(232, 234, 236));
		panelKhung.setBounds(0, 10, 299, 264);
		panelLoc.add(panelKhung);
		panelKhung.setLayout(null);
		
		JPanel panelLocDTL = new JPanel();
		panelLocDTL.setBackground(Color.WHITE);
		panelLocDTL.setBounds(8, 10, 247, 240);
		panelLocDTL.setLayout(null);
		panelLocDTL.setBorder(new LineBorder(Color.GRAY, 1, true)); // Viền màu xám, độ dày 2, bo góc
		panelKhung.add(panelLocDTL);
		
		JLabel lblNewLabel = new JLabel("Điểm tích lũy");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(20, 10, 190, 29);
		panelLocDTL.add(lblNewLabel);
		
		ButtonGroup groupDTL = new ButtonGroup();
		
		rdbtn50 = new JRadioButton("< 50");
		rdbtn50.setBackground(Color.WHITE);
		rdbtn50.setFont(new Font("Tahoma", Font.PLAIN, 24));
		rdbtn50.setBounds(47, 55, 103, 21);
		rdbtn50.setFocusPainted(false); 
		panelLocDTL.add(rdbtn50);
		groupDTL.add(rdbtn50);
		
		rdbtn200 = new JRadioButton("50 - 200");
		rdbtn200.setBackground(Color.WHITE);
		rdbtn200.setFont(new Font("Tahoma", Font.PLAIN, 24));
		rdbtn200.setBounds(47, 99, 138, 21);
		rdbtn200.setFocusPainted(false); 
		panelLocDTL.add(rdbtn200);
		groupDTL.add(rdbtn200);
		
		rdbtn500 = new JRadioButton("200 - 500");
		rdbtn500.setBackground(Color.WHITE);
		rdbtn500.setFont(new Font("Tahoma", Font.PLAIN, 24));
		rdbtn500.setBounds(47, 151, 138, 21);
		rdbtn500.setFocusPainted(false); 
		panelLocDTL.add(rdbtn500);
		groupDTL.add(rdbtn500);
		
		rdbtn600 = new JRadioButton("> 500");
		rdbtn600.setBackground(Color.WHITE);
		rdbtn600.setFont(new Font("Tahoma", Font.PLAIN, 24));
		rdbtn600.setBounds(47, 199, 103, 21);
		rdbtn600.setFocusPainted(false); 
		panelLocDTL.add(rdbtn600);
		groupDTL.add(rdbtn600);
		
		// bảng
		JPanel panelBang = new JPanel();
		panelBang.setBorder(new LineBorder(Color.GRAY, 1, true));
		panelBang.setBounds(332, 90, 911, 718);
		panelTong.add(panelBang);
		
		Object[][] data = {
	            {"1", "john@example.com", "Developer", "1", "john@example.com"},
	            {"2", "jane@example.com", "Designer", "1", "john@example.com"},
	            {"3", "mike@example.com", "Manager", "1", "john@example.com"},
	            {"John Doe", "john@example.com", "Developer", "1", "john@example.com"},
	            
	            
		};
		String[] header = {"Mã số", "Họ tên", "Số điện thoại", "Địa chỉ", "Điểm tích lũy"};
		modelKH = new DefaultTableModel(header, 0);
		tableKH = new JTable(modelKH);
//        tableKH = new JTable(data, columnNames);
        // Create custom table
        CustomTable table = new CustomTable(data, header, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle, 20);
       
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(911, 711)); // thay đổi theo khung chứa
        scrollPane.setBorder(new LineBorder(Color.GRAY, 1, true));
        
        panelBang.add(scrollPane);
        
        JScrollBar sb = scrollPane.getVerticalScrollBar();
		
		// biểu mẫu
        JPanel panelBieuMau = new JPanel() ;
		panelBieuMau.setBackground(Color.WHITE);
		panelBieuMau.setBounds(1265, 90, 495, 718);
		panelTong.add(panelBieuMau);
		panelBieuMau.setBorder(new LineBorder(Color.GRAY, 1, true));
		panelBieuMau.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Thông tin");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel_1.setBounds(157, 10, 174, 57);
		panelBieuMau.add(lblNewLabel_1);
		
		JLabel lblMaKH = new JLabel("Mã khách hàng:");
		lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblMaKH.setBounds(35, 100, 180, 38);
		panelBieuMau.add(lblMaKH);
		
		JLabel lblHoTen = new JLabel("Họ và tên: ");
		lblHoTen.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblHoTen.setBounds(35, 167, 180, 38);
		panelBieuMau.add(lblHoTen);
		
		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblSDT.setBounds(35, 242, 180, 38);
		panelBieuMau.add(lblSDT);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblDiaChi.setBounds(35, 319, 180, 38);
		panelBieuMau.add(lblDiaChi);
		
		JLabel lblDTL = new JLabel("Điểm tích lũy:");
		lblDTL.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblDTL.setBounds(35, 399, 180, 38);
		panelBieuMau.add(lblDTL);
		
		txtMaKH = new JTextField();
		txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtMaKH.setBackground(Color.WHITE);
		txtMaKH.setEditable(false);
		txtMaKH.setBounds(225, 100, 225, 35);
		panelBieuMau.add(txtMaKH);
		txtMaKH.setColumns(10);
		txtMaKH.setBorder(new LineBorder(Color.BLACK, 1));
		
		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtHoTen.setColumns(10);
		txtHoTen.setBorder(new LineBorder(Color.BLACK, 1));
		txtHoTen.setBackground(Color.WHITE);
		txtHoTen.setBounds(225, 167, 225, 35);
		panelBieuMau.add(txtHoTen);
		
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtSDT.setColumns(10);
		txtSDT.setBorder(new LineBorder(Color.BLACK, 1));
		txtSDT.setBackground(Color.WHITE);
		txtSDT.setBounds(225, 245, 225, 35);
		panelBieuMau.add(txtSDT);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBorder(new LineBorder(Color.BLACK, 1));
		txtDiaChi.setBackground(Color.WHITE);
		txtDiaChi.setBounds(225, 319, 225, 35);
		panelBieuMau.add(txtDiaChi);
		
		txtDTL = new JTextField();
		txtDTL.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtDTL.setEditable(false);
		txtDTL.setColumns(10);
		txtDTL.setBorder(new LineBorder(Color.BLACK, 1));
		txtDTL.setBackground(Color.WHITE);
		txtDTL.setBounds(225, 399, 225, 35);
		panelBieuMau.add(txtDTL);
		
		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnThem.setBackground(Color.WHITE);
		btnThem.setBounds(49, 563, 180, 46);
		btnThem.setIcon(UIStyles.Add);
		btnThem.setFocusPainted(false);  // xóa khung khi thao tác
		panelBieuMau.add(btnThem);
		
		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnCapNhat.setBackground(Color.WHITE);
		btnCapNhat.setBounds(267, 563, 180, 46);
		btnCapNhat.setIcon(UIStyles.Update);
		btnCapNhat.setFocusPainted(false); 
		panelBieuMau.add(btnCapNhat);
		
		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setBackground(Color.WHITE);
		btnXoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnXoaTrang.setBounds(164, 634, 180, 46);
		btnXoaTrang.setFocusPainted(false); 
		panelBieuMau.add(btnXoaTrang);
	
		
		// thanh công cụ
		JPanel panelThanhcongCu = new JPanel();
		panelThanhcongCu.setBackground(Color.WHITE);
		panelThanhcongCu.setBounds(41, 25, 1719, 46);
		panelTong.add(panelThanhcongCu);
		panelThanhcongCu.setLayout(null);
		panelThanhcongCu.setBackground(new Color(232, 234, 236));
		
		JLabel lblTieuDe = new JLabel("KHÁCH HÀNG");
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTieuDe.setBounds(46, 10, 170, 26);
		panelThanhcongCu.add(lblTieuDe);
		
		JPanel panelTim = new JPanel();
		panelTim.setBackground(Color.WHITE);
		panelTim.setBounds(291, 0, 438, 46);
		panelThanhcongCu.add(panelTim);
		panelTim.setLayout(new BorderLayout(0, 0));
		panelTim.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		txtTim = new JTextField();
		txtTim.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelTim.add(txtTim, BorderLayout.CENTER);
		txtTim.setBackground(Color.WHITE);
		txtTim.setColumns(10);
		txtTim.setBorder(BorderFactory.createEmptyBorder()); // xóa viền
		
		JLabel icon = new JLabel(UIStyles.FInd);
		panelTim.add(icon, BorderLayout.WEST);
		icon.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // cách lề 5px
		
		JCheckBox chckbxHienDS = new JCheckBox("Hiện tất cả");
		chckbxHienDS.setBackground(new Color(232, 234, 236));
		chckbxHienDS.setFont(new Font("Tahoma", Font.PLAIN, 24));
		chckbxHienDS.setBounds(1057, 0, 146, 46);
		chckbxHienDS.setFocusPainted(false); 
		panelThanhcongCu.add(chckbxHienDS);
		
		btnCapNhat.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		
		txtDiaChi.addActionListener(this);
		txtDTL.addActionListener(this);
		txtHoTen.addActionListener(this);
		txtMaKH.addActionListener(this);
		txtSDT.addActionListener(this);
		
		tableKH.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object o = e.getSource();
		
		int donguocChon = tableKH.getSelectedRow();
		
		
		if (o.equals(btnXoaTrang))
			xoaTrang();
		
	}
	
	private void xoaTrang() {
		txtMaKH.setText("");
		txtDiaChi.setText("");
		txtDTL.setText("");
		txtSDT.setText("");
		txtHoTen.setText("");
	}
	
 	private void xoaHetDuLieuTrenModel() {
		DefaultTableModel dm = (DefaultTableModel) tableKH.getModel();
		dm.getDataVector().removeAllElements();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int dongDuocChon = tableKH.getSelectedRow();
		System.out.println("dong duoc chon" + dongDuocChon);
		
		txtMaKH.setText(modelKH.getValueAt(dongDuocChon, 0).toString());
		txtHoTen.setText(modelKH.getValueAt(dongDuocChon, 1).toString());
		txtSDT.setText(modelKH.getValueAt(dongDuocChon, 2).toString());
		txtDiaChi.setText(modelKH.getValueAt(dongDuocChon, 3).toString());
		txtDTL.setText(modelKH.getValueAt(dongDuocChon, 4).toString());
		
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
