package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.DonDoiTraCTR;
import controller.KhachHangCTR;

import entity.DonDoiTra;
import entity.KhachHang;




public class HoaDonDoiTraUI extends JFrame {
	private JPanel contentPane;
	public DefaultTableModel modelTraHang;
	private DonDoiTraCTR doiTraCTR;
	private KhachHangCTR kh_ctr;
	private KhachHang kh;
	private DonDoiTra ddt;
	public JTable tableTraHangHoaDon;
	public DefaultTableModel modelDoiHang;
	private JTable tableDoiHangHoaDon;

	
	

	public  HoaDonDoiTraUI(DonDoiTra donDoiTra, Object[][] dataTraHang, Object[][] dataDoiHang, String tienTra, String phitrahang, String tienMua, String giamGia, String tongTien, String tienKhachDua, String tienTraKhach ) {
		

		
		doiTraCTR = new DonDoiTraCTR();
		
		kh_ctr = new KhachHangCTR();
		
		kh = kh_ctr.layThongTinKhachHangTheoMaDonDoiTra(donDoiTra.getMaDonDoiTra());
		
		ddt = doiTraCTR.layThongTinDonDoiTraTheoMa(donDoiTra.getMaDonDoiTra());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hóa Đơn Đổi Trả");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD , 20));
		lblNewLabel.setBounds(300, 7, 200, 32);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nhân Viên");
		lblNewLabel_1.setBounds(24, 49, 121, 13);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Tên Khách Hàng");
		lblNewLabel_2.setBounds(24, 72, 121, 13);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Số Điện Thoại");
		lblNewLabel_3.setBounds(24, 95, 121, 13);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Ngày Lập Hóa Đơn");
		lblNewLabel_4.setBounds(23, 118, 122, 13);
		panel.add(lblNewLabel_4);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(0, 142, 770, 5);
		panel.add(panel_1);
		
		JLabel textNguoiLap = new JLabel("");
		textNguoiLap.setBounds(155, 49, 137, 13);
		textNguoiLap.setText("Hứa Lập Quốc");
		panel.add(textNguoiLap);

		JLabel textTenKhachHang = new JLabel("");
		textTenKhachHang.setBounds(155, 72, 137, 13);
		textTenKhachHang.setText(kh.getHoTen());
		panel.add(textTenKhachHang);

		JLabel textSoDienThoai = new JLabel("");
		textSoDienThoai.setBounds(155, 95, 137, 13);
		textSoDienThoai.setText(kh.getSdt());
		panel.add(textSoDienThoai);

		JLabel textNgayLap = new JLabel("");
		textNgayLap.setBounds(155, 118, 137, 13);
		textNgayLap.setText(ddt.getNgayDoiTra()+"");
		panel.add(textNgayLap);
		
		
		JLabel textTraHang = new JLabel("");
		textTraHang.setBounds(300, 150, 137, 25);
		textTraHang.setFont(new Font("Tahoma", Font.BOLD , 20));
		textTraHang.setText("Trả Hàng");
		panel.add(textTraHang);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 180, 750, 150);
		panel.add(scrollPane_1);

		String headCollum[] = new String[]  {"Tên sản phẩm", "Giá bán", "Đơn vị tính", "số lượng","Tổng Tiền"};;
		modelTraHang = new DefaultTableModel(headCollum, 0);
		tableTraHangHoaDon = new JTable(modelTraHang);
		
		 for (Object[] row : dataTraHang) {
			 modelTraHang.addRow(row);
	        }
		scrollPane_1.setViewportView(tableTraHangHoaDon);
		
		JLabel textDoiHang = new JLabel("");
		textDoiHang.setBounds(300, 350, 137, 25);
		textDoiHang.setFont(new Font("Tahoma", Font.BOLD , 20));
		textDoiHang.setText("Đổi Hàng");
		panel.add(textDoiHang);
		
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 380, 750, 150);
		panel.add(scrollPane_2);

		String headCollum1[] = new String[]  {"Tên sản phẩm", "Giá bán", "Đơn vị tính", "số lượng","Tổng Tiền"};;
		modelDoiHang = new DefaultTableModel(headCollum1, 0);
		tableDoiHangHoaDon = new JTable(modelDoiHang);
		 for (Object[] row : dataDoiHang) {
			 modelDoiHang.addRow(row);
	        }
		scrollPane_2.setViewportView(tableDoiHangHoaDon);
		
		
		JLabel lblNewLabel_9 = new JLabel("Tổng tiền trả");
		lblNewLabel_9.setBounds(10, 550, 135, 31);
		panel.add(lblNewLabel_9);

		JLabel textTongTienTra = new JLabel("");
		textTongTienTra.setBounds(129, 550, 224, 31);
		textTongTienTra.setText(tienTra);
		panel.add(textTongTienTra);
		

		JLabel lblNewLabel_10 = new JLabel("Phí trả hàng");
		lblNewLabel_10.setBounds(10, 570, 135, 31);
		panel.add(lblNewLabel_10);

		JLabel textPhiTraHang = new JLabel("");
		textPhiTraHang.setBounds(129, 570, 224, 31);
		textPhiTraHang.setText(phitrahang);
		panel.add(textPhiTraHang);
		
		JLabel lblNewLabel_11 = new JLabel("Tổng tiền mua");
		lblNewLabel_11.setBounds(10, 590, 135, 31);
		panel.add(lblNewLabel_11);

		JLabel textTongTienMua = new JLabel("");
		textTongTienMua.setBounds(129, 590, 224, 31);
		textTongTienMua.setText(tienMua);
		panel.add(textTongTienMua);
		
		JLabel lblNewLabel_12 = new JLabel("Giảm giá");
		lblNewLabel_12.setBounds(10, 610, 135, 31);
		panel.add(lblNewLabel_12);

		JLabel textGiamGia = new JLabel("");
		textGiamGia.setBounds(129, 610, 224, 31);
		textGiamGia.setText(giamGia);
		panel.add(textGiamGia);
		
		JLabel lblNewLabel_13 = new JLabel("Tổng tiền");
		lblNewLabel_13.setBounds(10, 630, 135, 31);
		panel.add(lblNewLabel_13);

		JLabel textTongTien = new JLabel("");
		textTongTien.setBounds(129, 630, 224, 31);
		textTongTien.setText(tongTien);
		panel.add(textTongTien);
		
		JLabel lblNewLabel_14 = new JLabel("Tiền khách đưa");
		lblNewLabel_14.setBounds(10, 650, 135, 31);
		panel.add(lblNewLabel_14);

		JLabel textTienKhachDua = new JLabel("");
		textTienKhachDua.setBounds(129, 650, 224, 31);
		textTienKhachDua.setText(tienKhachDua);
		panel.add(textTienKhachDua);
		
		JLabel lblNewLabel_15 = new JLabel("Tiền trả khách");
		lblNewLabel_15.setBounds(10, 670, 135, 31);
		panel.add(lblNewLabel_15);

		JLabel textTienTraKhach = new JLabel("");
		textTienTraKhach.setBounds(129, 670, 224, 31);
		textTienTraKhach.setText(tienTraKhach);
		panel.add(textTienTraKhach);
		
	}
	



}
