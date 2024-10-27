package userInterfaces;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import component.CustomButton;
import component.CustomTable;
import component.CustomButton.CustomButtonIconSide;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;


public class BanHangUI extends JPanel implements ActionListener { 
	private JTextField txtTimSDT;
	private JTextField txtTenKH;
	private JTextField txtSDT;
	private CustomButton btnThemKH;
	private JTextField txtTenNV;
	private JTextField txtNgayLap;
	private JTextField txtTongGia;
	private JTextField txtGiamGia;
	private JTextField txtKhachDua;
	private JTextField txtTienThua;
	private JButton btnGiamGia;
	private JTextField txtKhachPhaiTra;
	private CustomButton btnTaoHD;
	private CustomButton btnLamMoi;
	private JTextField txtTimThuoc;
	private CustomButton btnThemThuoc;
	private JButton btnQuetMa;
	private JTextField txtGhiChu;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	private JLabel lblNgayLap;
	private JLabel lblTenNV;

	public BanHangUI() {
		super();
		taoHinh();
		layThoiGianHienTai();
	}
	
	public void taoHinh() {

		setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
		setLayout(null);
		setBackground(Color.WHITE);
		
		JPanel panelTong = new JPanel();
		panelTong.setBackground(UIStyles.backgroundColor);
		panelTong.setBounds(0, 0, UIStyles.ApplicationWidth, UIStyles.MainSectionHeight);
		add(panelTong);
		panelTong.setLayout(null);
		
		
		// phần thông tin
		JPanel panelThongTin = new JPanel();
		panelThongTin.setBackground(Color.WHITE);
		panelThongTin.setBounds(1318, 0, 602, 850);
		panelTong.add(panelThongTin);
		panelThongTin.setLayout(null);
		
		// phần khách hàng
		JPanel panelKhachHang = new JPanel();
		panelKhachHang.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), 
			    "Thông tin khách hàng", TitledBorder.LEFT, TitledBorder.TOP, UIStyles.FONT, Color.BLACK));
		panelKhachHang.setBackground(Color.WHITE);
		panelKhachHang.setBounds(32, 50, 539, 259);
		panelThongTin.add(panelKhachHang);
		panelKhachHang.setLayout(null);
		
		JPanel panelTimKH = new JPanel();
		panelTimKH.setBounds(25, 61, 306, 35);
		panelKhachHang.add(panelTimKH);
		panelTimKH.setLayout(null);
		panelTimKH.setBorder(new LineBorder(Color.BLACK, 1)); 
		
		JLabel lblIconTim = new JLabel(UIStyles.FInd);
		lblIconTim.setBounds(269, 1, 37, 35);
		panelTimKH.add(lblIconTim);
		
		txtTimSDT = new JTextField();
		txtTimSDT.setBackground(Color.WHITE);
		txtTimSDT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTimSDT.setBounds(1, 1, 269, 33);
		panelTimKH.add(txtTimSDT);
		txtTimSDT.setColumns(10);
		txtTimSDT.setBorder(null); 
		UIStyles.setPlaceholder(txtTimSDT, "Số điện thoại");
		
		btnThemKH = new CustomButton("Thêm KH", UIStyles.NavBarButtonStyle, null, CustomButtonIconSide.LEFT, () -> quayLai());
		btnThemKH.setBounds(358, 61, 160, 35);
		btnThemKH.setFont(UIStyles.FONT_BLOD);
		panelKhachHang.add(btnThemKH);
		
		JLabel lblNewLabel = new JLabel("Tên KH:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(42, 117, 86, 25);
		panelKhachHang.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("SĐT KH:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(42, 162, 86, 25);
		panelKhachHang.add(lblNewLabel_1);
		
		txtTenKH = new JTextField();
		txtTenKH.setEnabled(false);
		txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTenKH.setBounds(177, 115, 298, 30);
		panelKhachHang.add(txtTenKH);
		txtTenKH.setColumns(10);
		txtTenKH.setBorder(new LineBorder(Color.BLACK, 1)); 
		
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSDT.setEnabled(false);
		txtSDT.setColumns(10);
		txtSDT.setBounds(177, 160, 298, 30);
		txtSDT.setBorder(new LineBorder(Color.BLACK, 1)); 
		panelKhachHang.add(txtSDT);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Chiết khấu:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(42, 206, 116, 25);
		panelKhachHang.add(lblNewLabel_1_1_1);
		
		JComboBox comboBoxChietKhau = new JComboBox();
		comboBoxChietKhau.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBoxChietKhau.setBackground(Color.WHITE);
		comboBoxChietKhau.setBounds(177, 203, 298, 30);
		panelKhachHang.add(comboBoxChietKhau);
		
		JCheckBox chckbxKL = new JCheckBox("Khách lẻ");
		chckbxKL.setHorizontalAlignment(SwingConstants.RIGHT);
		chckbxKL.setBackground(Color.WHITE);
		chckbxKL.setFont(new Font("Tahoma", Font.PLAIN, 24));
		chckbxKL.setBounds(358, 23, 160, 21);
		panelKhachHang.add(chckbxKL);
		chckbxKL.setFocusable(false);
//		chckbxKL.setIconTextGap(5); // tạo khaonrg cách giữa ô và chữ
//		chckbxKL.setPreferredSize(new Dimension(40, 40));
//		chckbxKL.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		// phần hóa đơn
		JPanel panelHoaDon = new JPanel();
		panelHoaDon.setBackground(Color.WHITE);
		panelHoaDon.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), 
			    "Thông tin hóa đơn", TitledBorder.LEFT, TitledBorder.TOP, UIStyles.FONT, Color.BLACK));
		panelHoaDon.setBounds(32, 319, 539, 508);
		panelThongTin.add(panelHoaDon);
		panelHoaDon.setLayout(null);
		
		txtTongGia = new JTextField();
		txtTongGia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTongGia.setEnabled(false);
		txtTongGia.setColumns(10);
		txtTongGia.setBounds(187, 85, 288, 30);
		txtTongGia.setBorder(new LineBorder(Color.BLACK, 1)); 
		panelHoaDon.add(txtTongGia);
		
		JLabel lblTinK = new JLabel("Khách đưa:");
		lblTinK.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTinK.setBounds(42, 231, 111, 25);
		panelHoaDon.add(lblTinK);
		
		JLabel lblTunTha = new JLabel("Tiền thừa:");
		lblTunTha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTunTha.setBounds(42, 282, 111, 25);
		panelHoaDon.add(lblTunTha);
		
		JLabel lblTngGi = new JLabel("Tổng giá:");
		lblTngGi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTngGi.setBounds(42, 87, 111, 25);
		panelHoaDon.add(lblTngGi);
		
		txtKhachDua = new JTextField();
		txtKhachDua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtKhachDua.setEnabled(false);
		txtKhachDua.setColumns(10);
		txtKhachDua.setBounds(187, 229, 288, 30);
		txtKhachDua.setBorder(new LineBorder(Color.BLACK, 1)); 
		panelHoaDon.add(txtKhachDua);
		
		txtTienThua = new JTextField();
		txtTienThua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTienThua.setEnabled(false);
		txtTienThua.setColumns(10);
		txtTienThua.setBounds(187, 280, 288, 30);
		txtTienThua.setBorder(new LineBorder(Color.BLACK, 1)); 
		panelHoaDon.add(txtTienThua);
		
		JComboBox comboBoxLoaiHD = new JComboBox();
		comboBoxLoaiHD.setBounds(187, 38, 288, 30);
		panelHoaDon.add(comboBoxLoaiHD);
		comboBoxLoaiHD.setBackground(Color.WHITE);
		comboBoxLoaiHD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblLoaihd = new JLabel("Loại hóa đơn:");
		lblLoaihd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLoaihd.setBounds(42, 41, 125, 25);
		panelHoaDon.add(lblLoaihd);
		
		JLabel lblGimGi = new JLabel("Giảm giá:");
		lblGimGi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGimGi.setBounds(42, 132, 111, 25);
		panelHoaDon.add(lblGimGi);
		
		JPanel panelGiamGia = new JPanel();
		panelGiamGia.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelGiamGia.setBounds(187, 132, 288, 30);
		panelHoaDon.add(panelGiamGia);
		panelGiamGia.setLayout(null);
		
		txtGiamGia = new JTextField();
		txtGiamGia.setBounds(1, 1, 254, 28);
		panelGiamGia.add(txtGiamGia);
		txtGiamGia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtGiamGia.setEnabled(false);
		txtGiamGia.setBorder(null);
		txtGiamGia.setColumns(10);
		
		btnGiamGia = new JButton(UIStyles.More);
		btnGiamGia.setBackground(new Color(225,225,225));
		btnGiamGia.setForeground(Color.WHITE);
		btnGiamGia.setBounds(253, 0, 35, 30);
		btnGiamGia.setFocusable(false);
		panelGiamGia.add(btnGiamGia);
		
		JLabel lblKhchPhiTr = new JLabel("Khách phải trả:");
		lblKhchPhiTr.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblKhchPhiTr.setBounds(42, 182, 136, 25);
		panelHoaDon.add(lblKhchPhiTr);
		
		txtKhachPhaiTra = new JTextField();
		txtKhachPhaiTra.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtKhachPhaiTra.setEnabled(false);
		txtKhachPhaiTra.setColumns(10);
		txtKhachPhaiTra.setBorder(new LineBorder(Color.BLACK, 1));
		txtKhachPhaiTra.setBounds(187, 180, 288, 30);
		panelHoaDon.add(txtKhachPhaiTra);
		
		btnTaoHD = new CustomButton("Tạo hóa đơn", UIStyles.ThemButtonStyle, null, CustomButtonIconSide.LEFT, () -> quayLai());
		btnTaoHD.setForeground(Color.WHITE);
		btnTaoHD.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnTaoHD.setBounds(90, 453, 170, 40);
		panelHoaDon.add(btnTaoHD);
		
		btnLamMoi = new CustomButton("Làm mới", UIStyles.NavBarButtonStyle, null, CustomButtonIconSide.LEFT, () -> quayLai());
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLamMoi.setBounds(291, 453, 170, 40);
		panelHoaDon.add(btnLamMoi);
		
//		txtTenNV = new JTextField();
//		txtTenNV.setBounds(10, 17, 225, 30);
//		panelThongTin.add(txtTenNV);
//		txtTenNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		txtTenNV.setEnabled(false);
//		txtTenNV.setColumns(10);
//		txtTenNV.setBorder(new LineBorder(Color.BLACK, 1));
		
		lblTenNV = new JLabel();
		lblTenNV.setForeground(Color.RED);
		lblTenNV.setBounds(32, 10, 225, 30);
		panelThongTin.add(lblTenNV);
		lblTenNV.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTenNV.setText("Hứa Lập Quốc"); // Hoặc giá trị thực tế của nhân viên
		
		lblNgayLap = new JLabel();
		lblNgayLap.setForeground(Color.BLACK);
		lblNgayLap.setFont(new Font("Tahoma", Font.PLAIN, 20)); 
		lblNgayLap.setBounds(310, 10, 261, 30); 
		panelThongTin.add(lblNgayLap);
		lblNgayLap.setHorizontalAlignment(SwingConstants.RIGHT);
		 
		JLabel lblNewLabel_2 = new JLabel("Bán hàng");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_2.setBounds(572, 20, 164, 49);
		panelTong.add(lblNewLabel_2);
		
		// phần bán hàng
		JPanel panelBanHang = new JPanel();
		panelBanHang.setBounds(10, 90, 1299, 750);
		panelTong.add(panelBanHang);
		panelBanHang.setLayout(null);
		panelBanHang.setBackground(UIStyles.backgroundColor);
		
		// phần tìm kiếm
		JPanel panelTimKiem = new JPanel();
		panelTimKiem.setBounds(157, 10, 1035, 42);
		panelTimKiem.setBorder(new LineBorder(Color.BLACK, 1, true));
		panelBanHang.add(panelTimKiem);
		panelTimKiem.setLayout(null);
		
		btnQuetMa = new JButton(UIStyles.barCode);
		btnQuetMa.setBounds(1, 1, 56, 38);
		btnQuetMa.setBorder(null);
		btnQuetMa.setBackground(Color.WHITE);
		btnQuetMa.setFocusable(false);
		panelTimKiem.add(btnQuetMa);
		
		txtTimThuoc = new JTextField();
		txtTimThuoc.setBackground(Color.WHITE);
		txtTimThuoc.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtTimThuoc.setBounds(59, 2, 869, 37);
		txtTimThuoc.setBorder(new MatteBorder(0, 2, 0, 2, Color.BLACK));
		UIStyles.setPlaceholder(txtTimThuoc, "Mã sản phẩm");
		panelTimKiem.add(txtTimThuoc);
		txtTimThuoc.setBorder(null);
		txtTimThuoc.setColumns(10);
		
		btnThemThuoc = new CustomButton("Thêm", UIStyles.NavBarButtonStyle, null, CustomButtonIconSide.LEFT, () -> quayLai());
		btnThemThuoc.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnThemThuoc.setBounds(929, 2, 105, 38);
		btnThemThuoc.setFocusable(false);
		panelTimKiem.add(btnThemThuoc);
		
		// phần bảng thông tin
		Object[][] data = {
	            {"1", "john@example.com", "Developer", "1", "john@example.com"},
	            {"2", "jane@example.com", "Designer", "1", "john@example.com"},
	            {"3", "mike@example.com", "Manager", "1", "john@example.com"},
	            {"John Doe", "john@example.com", "Developer", "1", "john@example.com"},
	            
	            
		};
		String[] columnNames = {"Mã số", "Họ tên", "Số điện thoại", "Email", "Địa chỉ"};
		JTable a = new JTable(data, columnNames);
//        tableKH = new JTable(data, columnNames);
        // Create custom table
        CustomTable table = new CustomTable(data, columnNames, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle, 20);
       
        JScrollPane scrollPaneBang = new JScrollPane(table);
        scrollPaneBang.setPreferredSize(new Dimension(1123, 711)); // thay đổi theo khung chứa
        scrollPaneBang.setBorder(new LineBorder(Color.GRAY, 1, true));
        scrollPaneBang.setBounds(23, 72, 1255, 585); // Đặt kích thước và vị trí của scrollPane
        panelBanHang.add(scrollPaneBang);
        
        JPanel panel = new JPanel();
        panel.setBounds(23, 678, 1255, 47);
        panelBanHang.add(panel);
        panel.setLayout(null);
        
        txtGhiChu = new JTextField();
        txtGhiChu.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtGhiChu.setBounds(0, 0, 1255, 47);
        panel.add(txtGhiChu);
        txtGhiChu.setColumns(10);
        UIStyles.setPlaceholder(txtGhiChu, "Ghi chú đơn thuốc");
		
  
		
	}

	private Object quayLai() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
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
}


