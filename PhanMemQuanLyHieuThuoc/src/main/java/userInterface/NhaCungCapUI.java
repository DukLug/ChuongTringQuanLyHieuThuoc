package userInterface;

import java.awt.Color;
<<<<<<< HEAD
import java.awt.DefaultFocusTraversalPolicy;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
=======
import java.awt.Dimension;
import javax.swing.JLabel;
>>>>>>> 58d5bf1d6ab37b1050a9a9f2306bbd22633c2f17
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
<<<<<<< HEAD

import component.CustomButton;
import component.CustomTable;
import controller.NhaCungCapCTR;
import entity.NhaCungCap;
import component.CustomButton.CustomButtonIconSide;
=======
import component.CustomButton;
import component.CustomTable;
import component.CustomButton.CustomButtonIconSide;

>>>>>>> 58d5bf1d6ab37b1050a9a9f2306bbd22633c2f17
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
<<<<<<< HEAD
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JComponent;

public class NhaCungCapUI extends JPanel implements ActionListener, MouseListener {
	private JTextField txtTim;
	private JTextField txtMaNCC;
	private JTextField txtTen;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private CustomButton btnThem;
	private CustomButton btnCapNhat;
	private CustomButton btnXoaTrang;
	private JTextField txtEmail;
	private CustomTable customTable;
	private Object[][] data = new Object[0][0];
	private NhaCungCap ncc;
	private JComponent panelTong;
	private JComponent scrollPane;
	private JCheckBox chckbxHienDS;

	public NhaCungCapUI() {
		super();
//		data = NhaCungCapCTR.layData();
		taoHinh();
		NhaCungCapCTR.ketNoiData();
		
=======
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
	private CustomButton btnThem;
	private CustomButton btnCapNhat;
	private CustomButton btnXoaTrang;
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
		
		panelTong = new JPanel();
		JPanel panelTong = new JPanel();
		panelTong.setBackground(UIStyles.BackgroundColor);
		panelTong.setBounds(0, 0, UIStyles.ApplicationWidth, UIStyles.MainSectionHeight);
		add(panelTong);
		panelTong.setLayout(null);
		panelTong.revalidate(); 
		panelTong.repaint(); 
	    // Cuối cùng, đặt chính sách di chuyển tiêu điểm
	    panelTong.setFocusTraversalPolicy(new DefaultFocusTraversalPolicy());
	    panelTong.revalidate();
	    panelTong.repaint();
		
	
//		// biểu mẫu
        JPanel panelBieuMau = new JPanel() ;
		panelBieuMau.setBackground(Color.WHITE);
		panelBieuMau.setBounds(1265, 91, 547, 718);
		panelTong.add(panelBieuMau);
		panelBieuMau.setBorder(new LineBorder(Color.GRAY, 1, true));
		panelBieuMau.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Thông tin");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel_1.setBounds(107, 10, 359, 57);
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
		

		txtMaNCC = new JTextField();
		txtMaNCC.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMaNCC.setBackground(Color.WHITE);
		txtMaNCC.setEditable(false);
		txtMaNCC.setBounds(225, 100, 278, 35);
		panelBieuMau.add(txtMaNCC);
		txtMaNCC.setColumns(10);
		txtMaNCC.setBorder(new LineBorder(Color.BLACK, 1));
		
		txtTen = new JTextField();
		txtTen.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtTen.setColumns(10);
		txtTen.setBorder(new LineBorder(Color.BLACK, 1));
		txtTen.setBackground(Color.WHITE);
		txtTen.setBounds(225, 167, 278, 35);
		panelBieuMau.add(txtTen);
		
		txtSDT = new JTextField();

		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtSDT.setColumns(10);
		txtSDT.setBorder(new LineBorder(Color.BLACK, 1));
		txtSDT.setBackground(Color.WHITE);
		txtSDT.setBounds(225, 245, 278, 35);
		panelBieuMau.add(txtSDT);
		
		txtDiaChi = new JTextField();

		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtDiaChi.setColumns(10);
		txtDiaChi.setBorder(new LineBorder(Color.BLACK, 1));
		txtDiaChi.setBackground(Color.WHITE);
		txtDiaChi.setBounds(225, 396, 278, 35);
		panelBieuMau.add(txtDiaChi);
		

		btnThem = new CustomButton("Thêm", UIStyles.ThemButtonStyle, UIStyles.Add, CustomButtonIconSide.LEFT, () -> quayLai());
		btnThem.setBounds(71, 530, 180, 46);
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelBieuMau.add(btnThem);
		
		btnCapNhat = new CustomButton("Cập nhật", UIStyles.CapNhatButtonStyle, UIStyles.Update, CustomButtonIconSide.LEFT, () -> quayLai());
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnCapNhat.setBounds(300, 530, 180, 46);
		panelBieuMau.add(btnCapNhat);
		
		btnXoaTrang = new CustomButton("Xóa trắng", UIStyles.XoaTrangButtonStyle, UIStyles.Trash, CustomButtonIconSide.LEFT, () -> quayLai());
		btnXoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnXoaTrang.setBounds(184, 609, 180, 46);
		panelBieuMau.add(btnXoaTrang);
		
		txtEmail = new JTextField();

		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtEmail.setColumns(10);
		txtEmail.setBorder(new LineBorder(Color.BLACK, 1));
		txtEmail.setBackground(Color.WHITE);
		txtEmail.setBounds(225, 317, 278, 35);
		panelBieuMau.add(txtEmail);
	
		
		// thanh công cụ
		JPanel panelThanhcongCu = new JPanel();
		panelThanhcongCu.setBackground(Color.WHITE);
		panelThanhcongCu.setBounds(120, 24, 1692, 46);
		panelTong.add(panelThanhcongCu);
		panelThanhcongCu.setLayout(null);
		panelThanhcongCu.setBackground(UIStyles.BackgroundColor);
		
		JLabel lblTieuDe = new JLabel("NHÀ CUNG CẤP");
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTieuDe.setBounds(66, 5, 203, 36);
		
		panelThanhcongCu.add(lblTieuDe);
		
		JPanel panelTim = new JPanel();
		panelTim.setBackground(Color.WHITE);
		panelTim.setBounds(432, 0, 438, 46);
		panelThanhcongCu.add(panelTim);
		panelTim.setLayout(new BorderLayout(0, 0));
		panelTim.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		txtTim = new JTextField();
		txtTim.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelTim.add(txtTim, BorderLayout.CENTER);
		txtTim.setBackground(Color.WHITE);
		txtTim.setColumns(10);
		txtTim.setBorder(BorderFactory.createEmptyBorder()); // xóa viền
		UIStyles.setPlaceholder(txtTim, "Mã, tên nhà cung cấp");
		
		JLabel icon = new JLabel(UIStyles.Find);
		
		panelTim.add(icon, BorderLayout.WEST);
		icon.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // cách lề 5px
		

		chckbxHienDS = new JCheckBox("Hiện tất cả");

		chckbxHienDS.setBackground(UIStyles.BackgroundColor);
		chckbxHienDS.setFont(new Font("Tahoma", Font.PLAIN, 24));
		chckbxHienDS.setBounds(1014, 0, 146, 46);
		chckbxHienDS.setFocusPainted(false); 
		panelThanhcongCu.add(chckbxHienDS);

//		String[] columnNames = {"Mã", "Tên", "Số điện thoại", "Email", "Dịa chỉ"};
	        // Create custom table
//		customTable = new CustomTable(data, columnNames, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle, 20,
//				 new int[] {224, 224, 224, 224, 227},
//				 ()->ClickedMyTable());
		
		docuLieuVaotable();
//		taoScrollPanel(data);
		
//		scrollPane = new JScrollPane(customTable);
//        scrollPane.setPreferredSize(new Dimension(1123, 711)); // thay đổi theo khung chứa
//        scrollPane.setBorder(new LineBorder(Color.GRAY, 1, true));
//        scrollPane.setBounds(120, 90, 1123, 718); // Đặt kích thước và vị trí của scrollPane
//        panelTong.add(scrollPane);

        
		btnCapNhat.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		
		txtDiaChi.addActionListener(this);
		txtTen.addActionListener(this);
		txtMaNCC.addActionListener(this);
		txtSDT.addActionListener(this);
		txtTim.addActionListener(this);
		
		chckbxHienDS.addActionListener(this);

		customTable.addMouseListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object o = e.getSource();
		
		if (o.equals(btnXoaTrang))
			xoaTrang();
		
		else if (o.equals(btnThem)) {
			ncc = layThongTin();

			if (NhaCungCapCTR.themNCC(ncc)) {
				JOptionPane.showMessageDialog(this, "Thêm thành công");
				xoaHetDuLieuTrenTablel();
			}
			else
				JOptionPane.showMessageDialog(this, "Thêm không thành công");
		}
		
		else if (o.equals(btnCapNhat)) {
			ncc = layThongTin();
			if (NhaCungCapCTR.capNhatNCC(ncc)) {
				JOptionPane.showMessageDialog(this, "Cập nhật thành công");
				xoaHetDuLieuTrenTablel();
			}
			else
				JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
		}
		
		else if (o.equals(txtTim)) { 
	        String thongTin = txtTim.getText().trim();
	        data = NhaCungCapCTR.timKiem(thongTin);
	        panelTong.remove(scrollPane); // Xóa bảng cũ
	        taoScrollPanel(data);
	    }
		
		else if (chckbxHienDS.isSelected()) 
			xoaHetDuLieuTrenTablel();
			
		
	}
	
	private void xoaTrang() {
		txtMaNCC.setText("");
		txtDiaChi.setText("");
		txtEmail.setText("");
		txtSDT.setText("");
		txtTen.setText("");
	}
	
 	private NhaCungCap layThongTin() {
 		
 		String ma = txtMaNCC.getText();
 		if (ma == null || ma.isEmpty())
 			ma = NhaCungCapCTR.taoMa();

		String ten = txtTen.getText();
		String sdt = txtSDT.getText();
		String email = txtEmail.getText();
		String diaChi = txtDiaChi.getText();
		
		NhaCungCap ncc = new NhaCungCap(ma, ten, sdt, email, diaChi);
		
		return ncc;
	}
 	
 	private void docuLieuVaotable() {
 		data = NhaCungCapCTR.layData();
 		taoScrollPanel(data);
 	}
 	
 	private void xoaHetDuLieuTrenTablel() {
 		panelTong.remove(scrollPane); // Xóa bảng cũ
	    docuLieuVaotable();
 	}

 	private void taoScrollPanel(Object[][] data) {
 		String[] columnNames = {"Mã", "Tên", "Số điện thoại"};
 		
 		customTable = new CustomTable(data, columnNames, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle, 20,
				 new int[] {350, 350, 350},
				 ()->ClickedMyTable());
 		
 	    // Cập nhật bảng trong JScrollPane
 	    scrollPane = new JScrollPane(customTable);
 	    scrollPane.setPreferredSize(new Dimension(1123, 711)); 
 	    scrollPane.setBorder(new LineBorder(Color.GRAY, 1, true));
 	    scrollPane.setBounds(120, 90, 1123, 718); 
 
 	    panelTong.add(scrollPane); 
 	    panelTong.add(scrollPane);
 	    panelTong.revalidate(); 
	    panelTong.repaint(); 
	    
	    customTable.addMouseListener(this);;
 	}
 	
	@Override
	public void mouseClicked(MouseEvent e) {
	    int dongDuocChon = customTable.getSelectedRow();
//	    JOptionPane.showMessageDialog(this, dongDuocChon);

	    if (dongDuocChon >= 0) {
	        txtMaNCC.setText(customTable.getValueAt(dongDuocChon, 0).toString());
	        txtTen.setText(customTable.getValueAt(dongDuocChon, 1).toString());
	        txtSDT.setText(customTable.getValueAt(dongDuocChon, 2).toString());
//	        txtEmail.setText(customTable.getValueAt(dongDuocChon, 3).toString());
//	        txtDiaChi.setText(customTable.getValueAt(dongDuocChon, 4).toString());
	    }
	}
	@Override public void mousePressed(MouseEvent e) {}

	@Override public void mouseReleased(MouseEvent e) {}

	@Override public void mouseEntered(MouseEvent e) {}

	@Override public void mouseExited(MouseEvent e) {}
	
	private void ClickedMyTable() {}

	private void quayLai() {}


}
	
