package userInterface;

import java.awt.Color;
import java.awt.DefaultFocusTraversalPolicy;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import component.CustomButton;
import component.CustomTable;
import controller.NhaCungCapCTR;
import entity.NhaCungCap;
import component.CustomButton.CustomButtonIconSide;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
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
	private Object[][] data = new Object[0][0];
	private NhaCungCap ncc;
	private JComponent panelTong;
	private JComponent scrollPane;
	private JCheckBox chckbxHienDS;
	private CustomTable tableNCC;

	public NhaCungCapUI() {
		super();
		taoHinh();
		NhaCungCapCTR.ketNoiData();
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
		txtTen.addActionListener(e -> SwingUtilities.invokeLater(() -> txtSDT.requestFocus()));
		
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSDT.setColumns(10);
		txtSDT.setBorder(new LineBorder(Color.BLACK, 1));
		txtSDT.setBackground(Color.WHITE);
		txtSDT.setBounds(225, 245, 278, 35);
		panelBieuMau.add(txtSDT);
		txtSDT.addActionListener(e -> SwingUtilities.invokeLater(() -> txtEmail.requestFocus()));
		
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
		txtEmail.addActionListener(e -> SwingUtilities.invokeLater(() -> txtDiaChi.requestFocus()));
		
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

		String[] header = {"Mã", "Tên", "Số điện thoại"};
		data = NhaCungCapCTR.layData();
		tableNCC = new CustomTable(data, header, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle, 20);

		scrollPane = new JScrollPane(tableNCC);
        scrollPane.setPreferredSize(new Dimension(1123, 711)); // thay đổi theo khung chứa
        scrollPane.setBorder(new LineBorder(Color.GRAY, 1, true));
        scrollPane.setBounds(120, 90, 1123, 718); // Đặt kích thước và vị trí của scrollPane
        panelTong.add(scrollPane);

        
		btnCapNhat.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		
		txtDiaChi.addActionListener(this);
		txtTen.addActionListener(this);
		txtMaNCC.addActionListener(this);
		txtSDT.addActionListener(this);
		txtTim.addActionListener(this);
		
		chckbxHienDS.addActionListener(this);

		tableNCC.addMouseListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object o = e.getSource();
		
		if (o.equals(btnXoaTrang))
			xoaTrang();
			
		else if (o.equals(btnThem)) {
			if (kiemTraDuLieu()) {
				ncc = layThongTin();

				if (NhaCungCapCTR.kiemTraTrung(ncc.getMaNhaCungCap())) {
					JOptionPane.showMessageDialog(this, "Mã nhà cung cấp bị trùng");
					return;
				}
				
				if (NhaCungCapCTR.themNCC(ncc)) {
					JOptionPane.showMessageDialog(this, "Thêm thành công");
					data = NhaCungCapCTR.layData();
					tableNCC.capNhatDuLieu(data);
				}
				else
					JOptionPane.showMessageDialog(this, "Thêm không thành công");
			}
		}
		
		else if (o.equals(btnCapNhat)) {
			if (kiemTraDuLieu()) {
				ncc = layThongTin();
				if (NhaCungCapCTR.capNhatNCC(ncc)) {
					JOptionPane.showMessageDialog(this, "Cập nhật thành công");
					data = NhaCungCapCTR.layData();
					tableNCC.capNhatDuLieu(data);
				}
				else
					JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
			}
		}
		
		else if (o.equals(txtTim)) { 
	        String thongTin = txtTim.getText().trim();
	        data = NhaCungCapCTR.timKiem(thongTin);
	        tableNCC.capNhatDuLieu(data);;
	    }
		
		else if (chckbxHienDS.isSelected()) {
			data = NhaCungCapCTR.layData();
			tableNCC.capNhatDuLieu(data);
		}
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
 	
 	private boolean kiemTraDuLieu() {
 		String ten = txtTen.getText().trim();
		String sdt = txtSDT.getText().trim();
		String email = txtEmail.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		
		if (!(ten.length() > 0)) {
			thongBaoLoi(txtTen, "Tên nhà cung cấp không được để trống");
			return false;
		}
		
		if (!(sdt.length() > 0 && sdt.matches("[0-9]{10,14}"))) {
			thongBaoLoi(txtSDT, "Số điện thoại phải từ 10 đến 14 ký tự số");
			return false;
		}
		
		if (!(email.length() > 0)) {
			thongBaoLoi(txtEmail, "Email không được để trống");
			return false;
		} 
		else if (!(email.matches("^.+@.+\\..+$"))) {
			thongBaoLoi(txtEmail, "Email phải có dạng xxx@xxx.xxx (trong đó x là ký tự bất kỳ)");
			return false;
		}
		
		if (!(diaChi.length() > 0)) {
			thongBaoLoi(txtDiaChi, "Địa chỉ không được để trống");
			return false;
		}
		
		return true;
 	}
 	
 	private void thongBaoLoi(JTextField txt, String loi) {
 		txt.requestFocus();
		JOptionPane.showMessageDialog(this, loi);
 	}

	@Override
	public void mouseClicked(MouseEvent e) {
	    int dongDuocChon = tableNCC.getSelectedRow();
	    
	    String ma = tableNCC.getValueAt(dongDuocChon, 0).toString();
	    NhaCungCap ncc = NhaCungCapCTR.timKiemTheoMa(ma);
	    
	    txtMaNCC.setText(ncc.getMaNhaCungCap());
        txtTen.setText(ncc.getTenNhaCungCap());
        txtSDT.setText(ncc.getSdt());
        txtEmail.setText(ncc.getEmail());
        txtDiaChi.setText(ncc.getDiaChi());
	    
	}
	
	@Override public void mousePressed(MouseEvent e) {}

	@Override public void mouseReleased(MouseEvent e) {}

	@Override public void mouseEntered(MouseEvent e) {}

	@Override public void mouseExited(MouseEvent e) {}

	private void quayLai() {}
}
	
