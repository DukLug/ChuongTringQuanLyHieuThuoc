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




public class HoaDonTraUI extends JFrame {
	private JPanel contentPane;
	public DefaultTableModel modelTraHang;
	private DonDoiTraCTR doiTraCTR;
	private KhachHangCTR kh_ctr;
	private KhachHang kh;
	private DonDoiTra ddt;
	public JTable tableTraHangHoaDon;
	public DefaultTableModel modelDoiHang;

	
	

	public  HoaDonTraUI(DonDoiTra donDoiTra, Object[][] dataTraHang, String tienTra, String phitrahang,  String tongTien,String ghiChu ) {
		

		
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

		JLabel lblNewLabel = new JLabel("Hóa Đơn Trả Hàng");
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
		

		
		JLabel lblNewLabel_9 = new JLabel("Tổng tiền trả");
		lblNewLabel_9.setBounds(10, 350, 135, 31);
		panel.add(lblNewLabel_9);

		JLabel textTongTienTra = new JLabel("");
		textTongTienTra.setBounds(129, 350, 224, 31);
		textTongTienTra.setText(tienTra);
		panel.add(textTongTienTra);
		

		JLabel lblNewLabel_10 = new JLabel("Phí trả hàng");
		lblNewLabel_10.setBounds(10, 370, 135, 31);
		panel.add(lblNewLabel_10);

		JLabel textPhiTraHang = new JLabel("");
		textPhiTraHang.setBounds(129, 370, 224, 31);
		textPhiTraHang.setText(phitrahang);
		panel.add(textPhiTraHang);
		
		
		JLabel lblNewLabel_13 = new JLabel("Tiền trả khách");
		lblNewLabel_13.setBounds(10, 390, 135, 31);
		panel.add(lblNewLabel_13);

		JLabel textTongTien = new JLabel("");
		textTongTien.setBounds(129, 390, 224, 31);
		textTongTien.setText(tongTien);
		panel.add(textTongTien);
		
		JLabel lblNewLabel_14 = new JLabel("Ghi chú:");
		lblNewLabel_14.setBounds(10, 410, 135, 31);
		panel.add(lblNewLabel_14);

		JLabel textghiChu = new JLabel("");
		textghiChu.setBounds(129, 410, 224, 31);
		textghiChu.setText(ghiChu);
		panel.add(textghiChu);
		
		
	}
	



}
