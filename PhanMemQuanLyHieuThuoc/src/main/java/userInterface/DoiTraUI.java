package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import component.CustomButton;
import component.CustomPanel;
import component.CustomTable;
import component.CustomButton.CustomButtonIconSide;

public class DoiTraUI extends JPanel{
	
	
	


	private JTextField txtTenKH;
	private JTextField txtSDT;
	private CustomButton btnLamMoi;
	private JLabel lblTenNV;
	private JLabel lblNgayLap;
	private JTextField txtChietKhau;
	private JTextField txtTongGiaGoc;
	private JTextField txtmaHoaDon;
	private JTextField txtTongGiaTra;
	private JTextField txtPhiTraHang;
	private JTextField txtTienTraKhach;
	private CustomButton btnTraHang;
	private JButton btnQuetMa;
	private CustomButton btnThemThuoc;
	private JTextField txtTimTheoMaHoaDon;
	private JTextField txtGhiChu;
	private JButton btnQuetMaSP;
	private JTextField txtTimTheoMaSP;
	private CustomButton btnTimHD;
	private JComboBox cbGiamGia;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	public DoiTraUI() {
		super();
		taoHinh();
		layThoiGianHienTai();
	}
	
	public void taoHinh() {
		setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
		setLayout(null);
		setBackground(Color.WHITE);
		
		JPanel panelTong = new JPanel();
		panelTong.setBackground(UIStyles.BackgroundColor);
		panelTong.setBounds(0, 0, UIStyles.ApplicationWidth, UIStyles.MainSectionHeight);
		add(panelTong);
		panelTong.setLayout(null);
		
		// tiêu đề
		JLabel lblNewLabel_2 = new JLabel("Đổi Trả");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_2.setBounds(572, 10, 164, 49);
		panelTong.add(lblNewLabel_2);
		
		// phần thông tin
				CustomPanel panelThongTin = new CustomPanel(20, 0);
				panelThongTin.setBackground(Color.WHITE);
				panelThongTin.setBounds(1318, 10, 590, 810);
				panelTong.add(panelThongTin);
				panelThongTin.setLayout(null);
				
				
				
		// thông tin nhân viên
				lblTenNV = new JLabel();
				lblTenNV.setForeground(Color.RED);
				lblTenNV.setBounds(32, 10, 225, 30);
				panelThongTin.add(lblTenNV);
				lblTenNV.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblTenNV.setText("Hứa Lập Quốc");
				
				lblNgayLap = new JLabel();
				lblNgayLap.setForeground(Color.BLACK);
				lblNgayLap.setFont(new Font("Tahoma", Font.PLAIN, 20)); 
				lblNgayLap.setBounds(310, 10, 261, 30); 
				panelThongTin.add(lblNgayLap);
				lblNgayLap.setHorizontalAlignment(SwingConstants.RIGHT);
				
				

				
		// phần khách hàng
				JPanel panelKhachHang = new JPanel();
				panelKhachHang.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), 
					    "Thông tin khách hàng", TitledBorder.LEFT, TitledBorder.TOP, UIStyles.DefaultFont, Color.BLACK));
				panelKhachHang.setBackground(Color.WHITE);
				panelKhachHang.setBounds(32, 50, 539, 200);
				panelThongTin.add(panelKhachHang);
				panelKhachHang.setLayout(null);
				
				
				JLabel lblNewLabel = new JLabel("Tên KH:");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblNewLabel.setBounds(42, 50, 86, 25);
				panelKhachHang.add(lblNewLabel);
				
				txtTenKH = new JTextField();
				txtTenKH.setEnabled(false);
				txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtTenKH.setBounds(177, 45, 298, 30);
				panelKhachHang.add(txtTenKH);
				txtTenKH.setColumns(10);
				txtTenKH.setBorder(new LineBorder(Color.BLACK, 1)); 
				
				
				JLabel lblNewLabel_1 = new JLabel("SĐT KH:");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblNewLabel_1.setBounds(42, 100, 86, 25);
				panelKhachHang.add(lblNewLabel_1);
			
				
				txtSDT = new JTextField();
				txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtSDT.setEnabled(false);
				txtSDT.setColumns(10);
				txtSDT.setBounds(177, 95, 298, 30);
				txtSDT.setBorder(new LineBorder(Color.BLACK, 1)); 
				panelKhachHang.add(txtSDT);
				
				JLabel lblNewLabel_1_1_1 = new JLabel("Chiết khấu:");
				lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblNewLabel_1_1_1.setBounds(42, 150, 116, 25);
				panelKhachHang.add(lblNewLabel_1_1_1);
				
				txtChietKhau = new JTextField();
				txtChietKhau.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtChietKhau.setEnabled(false);
				txtChietKhau.setColumns(10);
				txtChietKhau.setBorder(new LineBorder(Color.BLACK, 1)); 
				txtChietKhau.setBounds(177, 145, 298, 30);
				panelKhachHang.add(txtChietKhau);
				
				
				
		// phần hóa đơn
				JPanel panelHoaDon = new JPanel();
				panelHoaDon.setBackground(Color.WHITE);
				panelHoaDon.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), 
					    "Thông tin hóa đơn", TitledBorder.LEFT, TitledBorder.TOP, UIStyles.DefaultFont, Color.BLACK));
				panelHoaDon.setBounds(32, 280, 539, 508);
				panelThongTin.add(panelHoaDon);
				panelHoaDon.setLayout(null);
				
				
				// mã hóa đơn
				JLabel lblmaHD = new JLabel("Mã hóa đơn:");
				lblmaHD.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblmaHD.setBounds(42, 40, 125, 25);
				panelHoaDon.add(lblmaHD);
				
				txtmaHoaDon = new JTextField();
				txtmaHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtmaHoaDon.setEnabled(false);
				txtmaHoaDon.setColumns(10);
				txtmaHoaDon.setBounds(187, 35, 288, 30);
				txtmaHoaDon.setBorder(new LineBorder(Color.BLACK, 1)); 
				panelHoaDon.add(txtmaHoaDon);
				
				
				// tổng tiền gốc
				
				JLabel lblTngGi = new JLabel("Tổng giá gốc:");
				lblTngGi.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblTngGi.setBounds(42, 90, 140, 25);
				panelHoaDon.add(lblTngGi);
				
				txtTongGiaGoc = new JTextField();
				txtTongGiaGoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtTongGiaGoc.setEnabled(false);
				txtTongGiaGoc.setColumns(10);
				txtTongGiaGoc.setBounds(187, 85, 288, 30);
				txtTongGiaGoc.setBorder(new LineBorder(Color.BLACK, 1)); 
				panelHoaDon.add(txtTongGiaGoc);
				
				// giá trả
				JLabel lblGiaTra = new JLabel("Tổng giá trả:");
				lblGiaTra.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblGiaTra.setBounds(42, 140, 140, 25);
				panelHoaDon.add(lblGiaTra);
				
				
				txtTongGiaTra = new JTextField();
				txtTongGiaTra.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtTongGiaTra.setEnabled(false);
				txtTongGiaTra.setColumns(10);
				txtTongGiaTra.setBounds(187, 135, 288, 30);
				txtTongGiaTra.setBorder(new LineBorder(Color.BLACK, 1)); 
				panelHoaDon.add(txtTongGiaTra);
				
				//giảm giá
				JLabel lblGiamGia = new JLabel("Giảm giá:");
				lblGiamGia.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblGiamGia.setBounds(42, 190, 136, 25);
				panelHoaDon.add(lblGiamGia);
				
				cbGiamGia = new JComboBox<>();
				cbGiamGia.setBackground(Color.WHITE);
				cbGiamGia.setFont(new Font("Tahoma", Font.PLAIN, 20));
				cbGiamGia.setBounds(187, 185, 288, 30);
				panelHoaDon.add(cbGiamGia);
				
				//phí trả hàng
				JLabel lblPhiTraHang = new JLabel("Phí trả hàng:");
				lblPhiTraHang.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblPhiTraHang.setBounds(42, 240, 140, 25);
				panelHoaDon.add(lblPhiTraHang);
				
				txtPhiTraHang = new JTextField();
				txtPhiTraHang.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtPhiTraHang.setEnabled(false);
				txtPhiTraHang.setColumns(10);
				txtPhiTraHang.setBounds(187, 235, 288, 30);
				txtPhiTraHang.setBorder(new LineBorder(Color.BLACK, 1)); 
				panelHoaDon.add(txtPhiTraHang);
				
				
				
				//cần trả khách 
				JLabel lblTienTraKhach = new JLabel("Tiền trả khách:");
				lblTienTraKhach.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblTienTraKhach.setBounds(42, 290, 140, 25);
				panelHoaDon.add(lblTienTraKhach);
				
				txtTienTraKhach = new JTextField();
				txtTienTraKhach.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtTienTraKhach.setEnabled(false);
				txtTienTraKhach.setColumns(10);
				txtTienTraKhach.setBounds(187, 285, 288, 30);
				txtTienTraKhach.setBorder(new LineBorder(Color.BLACK, 1)); 
				panelHoaDon.add(txtTienTraKhach);
				
				//
				
				
				btnTraHang = new CustomButton("Thanh Toán", UIStyles.ThemButtonStyle, null, CustomButtonIconSide.LEFT, () -> quayLai());
				btnTraHang.setForeground(Color.WHITE);
				btnTraHang.setFont(new Font("Tahoma", Font.BOLD, 20));
				btnTraHang.setBounds(90, 453, 170, 40);
				panelHoaDon.add(btnTraHang);
				
				btnLamMoi = new CustomButton("Làm mới", UIStyles.NavBarButtonStyle, null, CustomButtonIconSide.LEFT, () -> quayLai());
				btnLamMoi.setForeground(Color.WHITE);
				btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 20));
				btnLamMoi.setBounds(291, 453, 170, 40);
				panelHoaDon.add(btnLamMoi);
				
		// phần trả hàng
				JPanel panelTraHang = new JPanel();
				panelTraHang.setBounds(10, 50, 1299, 350);
				panelTong.add(panelTraHang);
				panelTraHang.setLayout(null);
				panelTraHang.setBackground(UIStyles.BackgroundColor);
				
				// phần tìm kiếm
				JPanel panelTimKiem = new JPanel();
				panelTimKiem.setBounds(10, 10, 1035, 42);
				panelTimKiem.setBorder(new LineBorder(Color.BLACK, 1, true));
				panelTraHang.add(panelTimKiem);
				panelTimKiem.setLayout(null);
				
				btnQuetMa = new JButton(UIStyles.BarCode);
				btnQuetMa.setBounds(1, 1, 56, 38);
				btnQuetMa.setBorder(null);
				btnQuetMa.setBackground(Color.WHITE);
				btnQuetMa.setFocusable(false);
				panelTimKiem.add(btnQuetMa);
				
				txtTimTheoMaHoaDon = new JTextField();
				txtTimTheoMaHoaDon.setBackground(Color.WHITE);
				txtTimTheoMaHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 23));
				txtTimTheoMaHoaDon.setBounds(59, 2, 869, 37);
				txtTimTheoMaHoaDon.setBorder(new MatteBorder(0, 2, 0, 2, Color.BLACK));
				UIStyles.setPlaceholder(txtTimTheoMaHoaDon, "Mã hóa đơn cần trả");
				panelTimKiem.add(txtTimTheoMaHoaDon);
				txtTimTheoMaHoaDon.setBorder(null);
				txtTimTheoMaHoaDon.setColumns(10);
				
				btnTimHD = new CustomButton("Tìm", UIStyles.NavBarButtonStyle, null, CustomButtonIconSide.LEFT, () -> quayLai());
				btnTimHD.setFont(new Font("Tahoma", Font.BOLD, 20));
				btnTimHD.setBounds(929, 2, 105, 38);
				btnTimHD.setFocusable(false);
				panelTimKiem.add(btnTimHD);
				// phần bảng thông tin
				Object[][] data = {
			            {"1", "john@example.com", "Developer", "1", "john@example.com"},
			            {"2", "jane@example.com", "Designer", "1", "john@example.com"},
			            {"3", "mike@example.com", "Manager", "1", "john@example.com"},
			            {"John Doe", "john@example.com", "Developer", "1", "john@example.com"},
			            
			            
				};
				String[] columnNames = {"Mã số", "Họ tên", "Số điện thoại", "Email", "Địa chỉ"};
				
				CustomTable table = new CustomTable(data, columnNames, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle, 20);
		        JScrollPane scrollPaneBang = new JScrollPane(table);
		        scrollPaneBang.setPreferredSize(new Dimension(1123, 711)); // thay đổi theo khung chứa
		        scrollPaneBang.setBorder(new LineBorder(Color.GRAY, 1, true));
		        scrollPaneBang.setBounds(10, 72, 1255, 270); // Đặt kích thước và vị trí của scrollPane
		        panelTraHang.add(scrollPaneBang);
		     
		        
		   // panel đổi hàng
		        JPanel panelDoiHang = new JPanel();
		        panelDoiHang.setBounds(10, 400, 1299, 350);
				panelTong.add(panelDoiHang);
				panelDoiHang.setLayout(null);
				panelDoiHang.setBackground(UIStyles.BackgroundColor);
				
				// phần tìm kiếm
				JPanel panelTimKiemSP = new JPanel();
				panelTimKiemSP.setBounds(10, 10, 1035, 42);
				panelTimKiemSP.setBorder(new LineBorder(Color.BLACK, 1, true));
				panelDoiHang.add(panelTimKiemSP);
				panelTimKiemSP.setLayout(null);
				
				btnQuetMaSP = new JButton(UIStyles.BarCode);
				btnQuetMaSP.setBounds(1, 1, 56, 38);
				btnQuetMaSP.setBorder(null);
				btnQuetMaSP.setBackground(Color.WHITE);
				btnQuetMaSP.setFocusable(false);
				panelTimKiemSP.add(btnQuetMaSP);
				
				txtTimTheoMaSP = new JTextField();
				txtTimTheoMaSP.setBackground(Color.WHITE);
				txtTimTheoMaSP.setFont(new Font("Tahoma", Font.PLAIN, 23));
				txtTimTheoMaSP.setBounds(59, 2, 869, 37);
				txtTimTheoMaSP.setBorder(new MatteBorder(0, 2, 0, 2, Color.BLACK));
				UIStyles.setPlaceholder(txtTimTheoMaSP, "Mã sản phẩm cần đổi");
				panelTimKiemSP.add(txtTimTheoMaSP);
				txtTimTheoMaSP.setBorder(null);
				txtTimTheoMaSP.setColumns(10);
				

				btnThemThuoc = new CustomButton("Thêm", UIStyles.NavBarButtonStyle, null, CustomButtonIconSide.LEFT, () -> quayLai());
				btnThemThuoc.setFont(new Font("Tahoma", Font.BOLD, 20));
				btnThemThuoc.setBounds(929, 2, 105, 38);
				btnThemThuoc.setFocusable(false);
				panelTimKiemSP.add(btnThemThuoc);
				
				// phần bảng thông tin đổi hàng
				Object[][] dataDoiHang = {
			            {"1", "john@example.com", "Developer", "1", "john@example.com"},
			            {"2", "jane@example.com", "Designer", "1", "john@example.com"},
			            {"3", "mike@example.com", "Manager", "1", "john@example.com"},
			            {"John Doe", "john@example.com", "Developer", "1", "john@example.com"},
			            
			            
				};
				String[] headers = {"Mã số", "Họ tên", "Số điện thoại", "Email", "Địa chỉ"};
				
				CustomTable tableDoiHang = new CustomTable(dataDoiHang, headers, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle, 20);
		        JScrollPane scrollPaneBangDoi = new JScrollPane(tableDoiHang);
		        scrollPaneBangDoi.setPreferredSize(new Dimension(1123, 711)); // thay đổi theo khung chứa
		        scrollPaneBangDoi.setBorder(new LineBorder(Color.GRAY, 1, true));
		        scrollPaneBangDoi.setBounds(10, 72, 1255, 270); // Đặt kích thước và vị trí của scrollPane
		        panelDoiHang.add(scrollPaneBangDoi);
		    
		        // ghi chú
		        JPanel panelghiChu = new JPanel();
		        panelghiChu.setBounds(20, 760, 1255, 47);
		        panelTong.add(panelghiChu);
		        panelghiChu.setLayout(null);
		        
		        txtGhiChu = new JTextField();
		        txtGhiChu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		        txtGhiChu.setBounds(0, 0, 1255, 47);
		        panelghiChu.add(txtGhiChu);
		        txtGhiChu.setColumns(10);
		        UIStyles.setPlaceholder(txtGhiChu, "Ghi chú đơn thuốc");
			
				 
				
	    
	}
	private void layThoiGianHienTai() {
	    // Hiển thị thời gian ngay lập tức khi khởi tạo
		hienThiTGHienTai();
	    
	    // Sau đó mới khởi tạo Timer để update mỗi giây
	    Timer timer = new Timer(1000, new ActionListener() {
	        @Override 
	        public void actionPerformed(ActionEvent e) {
	        	hienThiTGHienTai();
	        }
	    });
	    timer.start();
	}

	// Tách logic hiển thị thời gian ra method riêng
	private void hienThiTGHienTai() {
	    LocalDateTime tgHienTai = LocalDateTime.now();
	    lblNgayLap.setText(tgHienTai.format(formatter));

	}
	
	private void quayLai() {
		
	}
	
	
}
