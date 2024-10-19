package userInterfaces;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import component.CustomTable;
//import connectDB.ConnectDB;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;

public class NhaCungCapUI extends JPanel implements ActionListener, MouseListener {
	private JTextField txtTim;
	private JTextField txtMaKH;
	private JTextField txtTen;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JTable tableNCC;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnXoaTrang;
	private DefaultTableModel modelNCC;
	private JTextField txtEmail;

	public NhaCungCapUI() {
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
		
		// bảng
		JPanel panelBang = new JPanel();
		panelBang.setBorder(new LineBorder(Color.GRAY, 1, true));
		panelBang.setBounds(41, 103, 1203, 718);
		panelBang.setBorder(new LineBorder(Color.GRAY, 1, true));
		panelTong.add(panelBang);
		
		Object[][] data = {
	            {"1", "john@example.com", "Developer", "1", "john@example.com"},
	            {"2", "jane@example.com", "Designer", "1", "john@example.com"},
	            {"3", "mike@example.com", "Manager", "1", "john@example.com"},
	            {"John Doe", "john@example.com", "Developer", "1", "john@example.com"},
	            
	            
		};
		String[] header = {"Mã số", "Tên", "Số điện thoại", "Email","Địa chỉ"};
		modelNCC = new DefaultTableModel(header, 0);
		tableNCC = new JTable(modelNCC);
//        tableKH = new JTable(data, columnNames);
		
        // Create custom table
        CustomTable table = new CustomTable(data, header, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle, 20);
       
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1203, 713));  // thay đổi theo khung chứa
        panelBang.add(scrollPane);
        
        JScrollBar sb = scrollPane.getVerticalScrollBar();
		
		// biểu mẫu
        JPanel panelBieuMau = new JPanel() ;
		panelBieuMau.setBackground(Color.WHITE);
		panelBieuMau.setBounds(1265, 103, 495, 718);
		panelTong.add(panelBieuMau);
		panelBieuMau.setBorder(new LineBorder(Color.GRAY, 1, true));
		panelBieuMau.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Thông tin");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel_1.setBounds(157, 10, 174, 57);
		panelBieuMau.add(lblNewLabel_1);
		
		JLabel lblMaNCC = new JLabel("Mã số:");
		lblMaNCC.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblMaNCC.setBounds(35, 100, 180, 38);
		panelBieuMau.add(lblMaNCC);
		
		JLabel lblTen = new JLabel("Tên: ");
		lblTen.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTen.setBounds(35, 167, 180, 38);
		panelBieuMau.add(lblTen);
		
		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblSDT.setBounds(35, 242, 180, 38);
		panelBieuMau.add(lblSDT);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblDiaChi.setBounds(35, 394, 180, 38);
		panelBieuMau.add(lblDiaChi);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblEmail.setBounds(35, 315, 180, 38);
		panelBieuMau.add(lblEmail);
		
		txtMaKH = new JTextField();
		txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtMaKH.setBackground(Color.WHITE);
		txtMaKH.setEditable(false);
		txtMaKH.setBounds(225, 100, 225, 35);
		panelBieuMau.add(txtMaKH);
		txtMaKH.setColumns(10);
		txtMaKH.setBorder(new LineBorder(Color.BLACK, 1));
		
		txtTen = new JTextField();
		txtTen.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtTen.setColumns(10);
		txtTen.setBorder(new LineBorder(Color.BLACK, 1));
		txtTen.setBackground(Color.WHITE);
		txtTen.setBounds(225, 167, 225, 35);
		panelBieuMau.add(txtTen);
		
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
		txtDiaChi.setBounds(225, 396, 225, 35);
		panelBieuMau.add(txtDiaChi);
		
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
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtEmail.setColumns(10);
		txtEmail.setBorder(new LineBorder(Color.BLACK, 1));
		txtEmail.setBackground(Color.WHITE);
		txtEmail.setBounds(225, 317, 225, 35);
		panelBieuMau.add(txtEmail);
	
		
		// thanh công cụ
		JPanel panelThanhcongCu = new JPanel();
		panelThanhcongCu.setBackground(Color.WHITE);
		panelThanhcongCu.setBounds(41, 34, 1719, 46);
		panelTong.add(panelThanhcongCu);
		panelThanhcongCu.setLayout(null);
		panelThanhcongCu.setBackground(new Color(232, 234, 236));
		
		JLabel lblTieuDe = new JLabel("NHÀ CUNG CẤP");
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTieuDe.setBounds(47, 10, 203, 36);
		panelThanhcongCu.add(lblTieuDe);
		
		JPanel panelTim = new JPanel();
		panelTim.setBackground(Color.WHITE);
		panelTim.setBounds(322, 0, 438, 46);
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
		txtTen.addActionListener(this);
		txtMaKH.addActionListener(this);
		txtSDT.addActionListener(this);
		
		tableNCC.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object o = e.getSource();
		
		int donguocChon = tableNCC.getSelectedRow();
		
		
		if (o.equals(btnXoaTrang))
			xoaTrang();
		
	}
	
	private void xoaTrang() {
		txtMaKH.setText("");
		txtDiaChi.setText("");
		txtEmail.setText("");
		txtSDT.setText("");
		txtTen.setText("");
	}
	
 	private void xoaHetDuLieuTrenModel() {
		DefaultTableModel dm = (DefaultTableModel) tableNCC.getModel();
		dm.getDataVector().removeAllElements();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int dongDuocChon = tableNCC.getSelectedRow();
		System.out.println("dong duoc chon" + dongDuocChon);
		
		txtMaKH.setText(modelNCC.getValueAt(dongDuocChon, 0).toString());
		txtTen.setText(modelNCC.getValueAt(dongDuocChon, 1).toString());
		txtSDT.setText(modelNCC.getValueAt(dongDuocChon, 2).toString());
		txtDiaChi.setText(modelNCC.getValueAt(dongDuocChon, 3).toString());
		
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
