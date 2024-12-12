package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import component.CustomButton;
import component.CustomTable;
import component.ImageUtilities;
import controller.KhachHangCTR;
import controller.NhaCungCapCTR;
import dao.KhachHangDAO;
import entity.KhachHang;
import entity.NhaCungCap;
import component.CustomButton.CustomButtonIconSide;
import component.CustomPanel;

import javax.swing.JTextField;
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
import javax.swing.JComponent;

public class KhachHangUI extends JPanel implements ActionListener, MouseListener {
	private JTextField txtTim;
	private JTextField txtMaKH;
	private JTextField txtHoTen;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JTextField txtDTL;
	private CustomTable tableKH;
	private JRadioButton rdbtn200;
	private JRadioButton rdbtn500;
	private CustomButton btnThem;
	private CustomButton btnCapNhat;
	private CustomButton btnXoaTrang;
	private JTextField txtCCCD;
	private Object[][] data = new Object[0][0];
	private JCheckBox chckbxHienDS;
	private KhachHang kh;
	private JRadioButton rdbtn900;
	private JRadioButton rdbtn1000;

	public KhachHangUI() {
		super();
		taoHinh();
		KhachHangCTR.ketNoiData();
	}
	
	public void taoHinh() {
		setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
		this.setBackground(Color.WHITE);
		
setLayout(null);
		
		JPanel panelTong = new JPanel();
		panelTong.setBackground(UIStyles.BackgroundColor);
		panelTong.setBounds(0, 0, UIStyles.ApplicationWidth, UIStyles.MainSectionHeight);

		add(panelTong);
		panelTong.setLayout(null);
		
		JPanel panelLoc = new JPanel();
		panelLoc.setBackground(Color.WHITE);
		panelLoc.setBounds(41, 81, 281, 727);
		panelTong.add(panelLoc);
		panelLoc.setBackground(UIStyles.BackgroundColor);
		panelLoc.setLayout(null);

		CustomPanel panelLocDTL = new CustomPanel(20, 5);
		panelLocDTL.setBackground(Color.WHITE);
		panelLocDTL.setBounds(8, 10, 247, 240);
		panelLocDTL.setLayout(null);
		panelLoc.add(panelLocDTL);
		
		JLabel lblNewLabel = new JLabel("Điểm tích lũy");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(20, 10, 190, 29);
		panelLocDTL.add(lblNewLabel);
		
		ButtonGroup groupDTL = new ButtonGroup();
		
		rdbtn200 = new JRadioButton("< 200");
		rdbtn200.setBackground(Color.WHITE);
		rdbtn200.setFont(new Font("Tahoma", Font.PLAIN, 24));
		rdbtn200.setBounds(47, 55, 103, 21);
		rdbtn200.setFocusPainted(false); 
		panelLocDTL.add(rdbtn200);
		groupDTL.add(rdbtn200);
		
		rdbtn500 = new JRadioButton("200 - 500");
		rdbtn500.setBackground(Color.WHITE);
		rdbtn500.setFont(new Font("Tahoma", Font.PLAIN, 24));
		rdbtn500.setBounds(47, 99, 138, 21);
		rdbtn500.setFocusPainted(false); 
		panelLocDTL.add(rdbtn500);
		groupDTL.add(rdbtn500);
		
		rdbtn900 = new JRadioButton("500 - 900");
		rdbtn900.setBackground(Color.WHITE);
		rdbtn900.setFont(new Font("Tahoma", Font.PLAIN, 24));
		rdbtn900.setBounds(47, 151, 138, 21);
		rdbtn900.setFocusPainted(false); 
		panelLocDTL.add(rdbtn900);
		groupDTL.add(rdbtn900);
		
		rdbtn1000 = new JRadioButton("> 900");
		rdbtn1000.setBackground(Color.WHITE);
		rdbtn1000.setFont(new Font("Tahoma", Font.PLAIN, 24));
		rdbtn1000.setBounds(47, 199, 103, 21);
		rdbtn1000.setFocusPainted(false); 
		panelLocDTL.add(rdbtn1000);
		groupDTL.add(rdbtn1000);
		
		String[] header = {"Mã số", "Họ tên", "Số điện thoại", "Điểm tích lũy"};
		data = KhachHangCTR.layData();
		tableKH = new CustomTable(data, header, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle, 20);
	       
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(tableKH);
        scrollPane.setPreferredSize(new Dimension(911, 711));
        scrollPane.setBorder(new LineBorder(Color.GRAY, 1, true));
        scrollPane.setBounds(332, 90, 911, 718); // Đặt kích thước và vị trí của scrollPane
        panelTong.add(scrollPane);
		
		// biểu mẫu
        JPanel panelBieuMau = new JPanel() ;
		panelBieuMau.setBackground(Color.WHITE);
		panelBieuMau.setBounds(1265, 90, 606, 718);
		panelTong.add(panelBieuMau);
		panelBieuMau.setBorder(new LineBorder(Color.GRAY, 1, true));
		panelBieuMau.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Thông tin");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel_1.setBounds(229, 10, 174, 57);
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
		lblDTL.setBounds(35, 476, 180, 38);
		panelBieuMau.add(lblDTL);
		
		txtMaKH = new JTextField();
		txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMaKH.setBackground(Color.WHITE);
		txtMaKH.setEditable(false);
		txtMaKH.setBounds(244, 102, 308, 35);
		panelBieuMau.add(txtMaKH);
		txtMaKH.setColumns(10);
		txtMaKH.setBorder(new LineBorder(Color.BLACK, 1));
		
		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtHoTen.setColumns(10);
		txtHoTen.setBorder(new LineBorder(Color.BLACK, 1));
		txtHoTen.setBackground(Color.WHITE);
		txtHoTen.setBounds(244, 169, 308, 35);
		panelBieuMau.add(txtHoTen);
		txtHoTen.addActionListener(e -> SwingUtilities.invokeLater(() -> txtSDT.requestFocus()));
		
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSDT.setColumns(10);
		txtSDT.setBorder(new LineBorder(Color.BLACK, 1));
		txtSDT.setBackground(Color.WHITE);
		txtSDT.setBounds(244, 244, 308, 35);
		panelBieuMau.add(txtSDT);
		txtSDT.addActionListener(e -> SwingUtilities.invokeLater(() -> txtDiaChi.requestFocus()));
		
		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBorder(new LineBorder(Color.BLACK, 1));
		txtDiaChi.setBackground(Color.WHITE);
		txtDiaChi.setBounds(244, 321, 308, 35);
		panelBieuMau.add(txtDiaChi);
		txtDiaChi.addActionListener(e -> SwingUtilities.invokeLater(() -> txtCCCD.requestFocus()));
		
		txtDTL = new JTextField();
		txtDTL.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDTL.setEditable(false);
		txtDTL.setColumns(10);
		txtDTL.setBorder(new LineBorder(Color.BLACK, 1));
		txtDTL.setBackground(Color.WHITE);
		txtDTL.setBounds(244, 476, 308, 35);
		panelBieuMau.add(txtDTL);

		btnThem = new CustomButton("Thêm", UIStyles.ThemButtonStyle, UIStyles.Add, CustomButtonIconSide.LEFT, () -> quayLai());
		btnThem.setBounds(102, 563, 180, 46);
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelBieuMau.add(btnThem);
		
		
		btnCapNhat = new CustomButton("Cập nhật", UIStyles.CapNhatButtonStyle, UIStyles.Update, CustomButtonIconSide.LEFT, () -> quayLai());
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnCapNhat.setBounds(339, 563, 180, 46);
		panelBieuMau.add(btnCapNhat);
		
		btnXoaTrang = new CustomButton("Xóa trắng", UIStyles.XoaTrangButtonStyle, UIStyles.Trash, CustomButtonIconSide.LEFT, () -> quayLai());
		btnXoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnXoaTrang.setBounds(223, 636, 180, 46);
		panelBieuMau.add(btnXoaTrang);
		
		JLabel lblCccd = new JLabel("CCCD:");
		lblCccd.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblCccd.setBounds(35, 399, 180, 38);
		panelBieuMau.add(lblCccd);
		
		txtCCCD = new JTextField();
		txtCCCD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCCCD.setColumns(10);
		txtCCCD.setBorder(new LineBorder(Color.BLACK, 1));
		txtCCCD.setBackground(Color.WHITE);
		txtCCCD.setBounds(244, 399, 308, 35);
		panelBieuMau.add(txtCCCD);
		
		// thanh công cụ
		JPanel panelThanhcongCu = new JPanel();
		panelThanhcongCu.setBackground(Color.WHITE);
		panelThanhcongCu.setBounds(41, 25, 1719, 46);
		panelTong.add(panelThanhcongCu);
		panelThanhcongCu.setLayout(null);
		panelThanhcongCu.setBackground(UIStyles.BackgroundColor);
		
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
		UIStyles.setPlaceholder(txtTim, "Mã, số điện thoại khách hàng");
		
		JLabel icon = new JLabel(UIStyles.Find);
		panelTim.add(icon, BorderLayout.WEST);
		icon.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // cách lề 5px
		
		chckbxHienDS = new JCheckBox("Hiện tất cả");
		chckbxHienDS.setBackground(UIStyles.BackgroundColor);
		
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
		txtTim.addActionListener(this);
		txtCCCD.addActionListener(this);
		
		rdbtn1000.addActionListener(this);
		rdbtn200.addActionListener(this);
		rdbtn500.addActionListener(this);
		rdbtn900.addActionListener(this);
		
		chckbxHienDS.addActionListener(this);
		
		tableKH.addMouseListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object o = e.getSource();
		if (o.equals(btnXoaTrang))
			xoaTrang();
		
		if (chckbxHienDS.isSelected()) {
			data = KhachHangCTR.layData();
			tableKH.capNhatDuLieu(data);
		}
		
		if (o.equals(btnThem)) {
			if (kiemTraDuLieu()) {
				kh = layThongTin();
				
				if (KhachHangCTR.kiemTraTrung(kh.getMaKhachHang())) {
					JOptionPane.showMessageDialog(this, "Mã khách hàng bị trùng");
					return;
				}
				
				if (KhachHangCTR.themKH(kh)) {
					JOptionPane.showMessageDialog(this, "Thêm thành công");
					data = KhachHangCTR.layData();
					tableKH.capNhatDuLieu(data);
				}
				else
					JOptionPane.showMessageDialog(this, "Thêm không thành công");
			}
		}
		
		if (o.equals(btnCapNhat)) {
			if (kiemTraDuLieu()) {
				kh = layThongTin();
				
				if (KhachHangCTR.capNhatKH(kh)) {
					JOptionPane.showMessageDialog(this, "Cập nhật thành công");
					data = KhachHangCTR.layData();
					tableKH.capNhatDuLieu(data);
				} else
					JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
			}
		}
		
		if (o.equals(txtTim)) { 
	        String thongTin = txtTim.getText().trim();
	        data = KhachHangCTR.timKiem(thongTin);
	        tableKH.capNhatDuLieu(data);
	    }
		
		if (o.equals(rdbtn200)) {
			data = KhachHangCTR.locDTL(0, 199);
			tableKH.capNhatDuLieu(data);
		} else if (o.equals(rdbtn500)) {
			data = KhachHangCTR.locDTL(200, 500);
			tableKH.capNhatDuLieu(data);
		} else if (o.equals(rdbtn900)) {
			data = KhachHangCTR.locDTL(501, 900);
			tableKH.capNhatDuLieu(data);
		} else if (o.equals(rdbtn1000)) {
			data = KhachHangCTR.locDTL(901, Integer.MAX_VALUE);
			tableKH.capNhatDuLieu(data);
		}

	}
	
	private void xoaTrang() {
		txtMaKH.setText("");
		txtDiaChi.setText("");
		txtDTL.setText("");
		txtSDT.setText("");
		txtHoTen.setText("");
		txtCCCD.setText("");
	}
	
	private KhachHang layThongTin() {
 		
 		String ma = txtMaKH.getText();
 		if (ma == null || ma.isEmpty())
 			ma = KhachHangCTR.taoMa();

		String ten = txtHoTen.getText();
		String sdt = txtSDT.getText();
		String cccd = txtCCCD.getText();
		String diaChi = txtDiaChi.getText();
		int DTL;
		
		if (txtDTL.getText().isEmpty() || txtDTL.getText() == null)
			DTL = 0;
		else
			DTL = Integer.parseInt(txtDTL.getText());

		KhachHang kh = new KhachHang(ma, ten, sdt, cccd, diaChi, DTL);
		return kh;
	}

	private boolean kiemTraDuLieu() {
		String ten = txtHoTen.getText().trim();
		String sdt = txtSDT.getText().trim();
		String cccd = txtCCCD.getText().trim();
		
		if (ten.isEmpty() || ten == null) {
			thongBaoLoi(txtHoTen, "Tên khách hàng không được để trống");
			return false;
		}
		
		if (!(sdt.length() > 0 && sdt.matches("[0-9]{10,14}"))) {
			thongBaoLoi(txtSDT, "Số điện thoại phải từ 10 đến 14 ký tự số");
			return false;
		}
		
		if (cccd.length() > 0) {
			if (!(cccd.matches("[0-9]{12}"))) {
				thongBaoLoi(txtCCCD, "Căn cước công dân phải đủ 12 ký tự số");
				return false;
			}
		}
		
		return true;
	}
	
	private void thongBaoLoi(JTextField txt, String loi) {
 		txt.requestFocus();
		JOptionPane.showMessageDialog(this, loi);
 	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int dongDuocChon = tableKH.getSelectedRow();
		
		String ma = tableKH.getValueAt(dongDuocChon, 0).toString();
		KhachHang kh = KhachHangCTR.timKiemTheoMa(ma);
		
		txtMaKH.setText(kh.getMaKhachHang());
		txtHoTen.setText(kh.getHoTen());
		txtSDT.setText(kh.getSdt());
		txtCCCD.setText(kh.getCccd());
		txtDiaChi.setText(kh.getDiaChi());
		txtDTL.setText(Integer.toString(kh.getDiemTichLuy()));		
	}

	@Override public void mousePressed(MouseEvent e) {}

	@Override public void mouseReleased(MouseEvent e) {}

	@Override public void mouseEntered(MouseEvent e) {}

	@Override public void mouseExited(MouseEvent e) {}
	
	private void quayLai() {}
}
	
