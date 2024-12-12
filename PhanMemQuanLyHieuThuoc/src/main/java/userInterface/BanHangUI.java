package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SortOrder;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.Date;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

import application.PhanMemQuanLyHieuThuoc;
import component.CustomButton;
import component.CustomItem;
import component.CustomItemList;
import component.CustomTable;
import component.RoundedBorder;
import controller.BanHangCTR;
import controller.KhachHangCTR;
import controller.SanPhamCTR;
import component.CustomButton.CustomButtonIconSide;
import customDataType.LoaiHoaDon;
import dao.LoHangDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.LoHang;
import entity.NhanVien;
import entity.SanPhamYTe;
import testEntity.Thuoc;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JCheckBox;


public class BanHangUI extends JPanel implements ActionListener, MouseListener    { 
	private JTextField txtTimSDT;
	private JTextField txtTenKH;
	private JTextField txtDTL;
	private CustomButton btnThemKH;
	private static JTextField txtTongTienHD;
	private static JTextField txtGiamGia;
	private static JTextField txtKhachDua;
	private static JTextField txtTienThua;
	private JButton btnGiamGia;
	private static JTextField txtKhachPhaiTra;
	private CustomButton btnTaoHD;
	private CustomButton btnLamMoi;
	private JTextField txtTimSP;
	private CustomButton btnThemSP;
	private JButton btnQuetMa;
	private JTextField txtGhiChu;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	private JLabel lblNgayLap;
	private JLabel lblTenNV;
	private JTextField txtCCCD;
	private static BanHangCTR bh_ctr = new BanHangCTR();
	private static CustomItemList banHangList;
	private static JComboBox comboBoxChietKhau;
	private JCheckBox chckbxKhachLe;
	private static JComboBox<LoaiHoaDon> comboBoxLoaiHD;
	private JTextField txtSDT;
	private static float phanPhanTramKM = 0;
	private JPanel panelBanHang;
	private JFrame frameKhuyenMai;
	private Object[][] data = new Object[0][0];
	private CustomTable tableKM;
	private CustomButton btnChon;
	private CustomButton btnThoat;
	private static BigDecimal tienChietKhau = BigDecimal.ZERO;
	private static BigDecimal tienKhuyenMai = BigDecimal.ZERO;
	private static BigDecimal tongTienHD = BigDecimal.ZERO;
	private static BigDecimal tienKhachPhaiTra = BigDecimal.ZERO;

	private static KhachHang kh; 
	private static SanPhamYTe sp;
	private static String maKM;

	private KhachHangCTR kh_ctr = new KhachHangCTR();
	public BanHangUI() {
		super();
		taoHinh();
		xoaTrangTTKH();
		lamMoi();
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
		
		
		// PHẦN THÔNG TIN
		JPanel panelThongTin = new JPanel();
		panelThongTin.setBackground(Color.WHITE);
		panelThongTin.setBounds(1318, 0, 602, 850);
		panelTong.add(panelThongTin);
		panelThongTin.setLayout(null);
		
		// phần khách hàng
		JPanel panelKhachHang = new JPanel();
		panelKhachHang.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), 
			    "Thông tin khách hàng", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 21), Color.BLACK));
		panelKhachHang.setBackground(Color.WHITE);
		panelKhachHang.setBounds(32, 48, 539, 331);
		panelThongTin.add(panelKhachHang);
		panelKhachHang.setLayout(null);
		
		JPanel panelTimKH = new JPanel();
		panelTimKH.setBounds(25, 61, 306, 35);
		panelKhachHang.add(panelTimKH);
		panelTimKH.setLayout(null);
		panelTimKH.setBorder(new LineBorder(Color.BLACK, 1)); 
		
		JLabel lblIconTim = new JLabel(UIStyles.Find);
		lblIconTim.setBounds(269, 1, 37, 35);
		panelTimKH.add(lblIconTim);
		
		txtTimSDT = new JTextField();
		txtTimSDT.setBackground(Color.WHITE);
		txtTimSDT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTimSDT.setBounds(1, 1, 262, 33);
		panelTimKH.add(txtTimSDT);
		txtTimSDT.setColumns(10);
		txtTimSDT.setBorder(null); 
		UIStyles.setPlaceholder(txtTimSDT, "Số điện thoại");
		
		btnThemKH = new CustomButton("Thêm KH", UIStyles.NavBarButtonStyle, null, CustomButtonIconSide.LEFT, () -> quayLai());
		btnThemKH.setBounds(358, 61, 160, 35);
		btnThemKH.setFont(UIStyles.BoldFont);
		panelKhachHang.add(btnThemKH);
		
		JLabel lblNewLabel = new JLabel("Tên KH:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(42, 117, 86, 25);
		panelKhachHang.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Điểm:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(42, 201, 86, 25);
		panelKhachHang.add(lblNewLabel_1);
		
		txtTenKH = new JTextField();
		txtTenKH.setDisabledTextColor(Color.BLACK);
		txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTenKH.setBounds(177, 115, 298, 30);
		panelKhachHang.add(txtTenKH);
		txtTenKH.setColumns(10);
		txtTenKH.setBorder(new LineBorder(Color.BLACK, 1)); 
		
		txtDTL = new JTextField();
		txtDTL.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDTL.setDisabledTextColor(Color.BLACK);
		txtDTL.setEnabled(false);
		txtDTL.setColumns(10);
		txtDTL.setBounds(177, 199, 298, 30);
		txtDTL.setBorder(new LineBorder(Color.BLACK, 1)); 
		panelKhachHang.add(txtDTL);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Chiết khấu:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(42, 288, 116, 25);
		panelKhachHang.add(lblNewLabel_1_1_1);
		
		comboBoxChietKhau = new JComboBox();
		comboBoxChietKhau.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBoxChietKhau.setBackground(Color.WHITE);
		comboBoxChietKhau.setBounds(177, 285, 298, 30);
		comboBoxChietKhau.setFocusable(false);
		panelKhachHang.add(comboBoxChietKhau);
		
		chckbxKhachLe = new JCheckBox("Khách lẻ");
		chckbxKhachLe.setHorizontalAlignment(SwingConstants.RIGHT);
		chckbxKhachLe.setBackground(Color.WHITE);
		chckbxKhachLe.setFont(new Font("Tahoma", Font.PLAIN, 24));
		chckbxKhachLe.setBounds(358, 25, 160, 21);
		chckbxKhachLe.setBorder(new LineBorder(Color.BLACK, 1)); 
		chckbxKhachLe.setFocusable(false);
		panelKhachHang.add(chckbxKhachLe);
		
		txtCCCD = new JTextField();
		txtCCCD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCCCD.setColumns(10);
		txtCCCD.setBorder(new LineBorder(Color.BLACK, 1));
		txtCCCD.setBounds(177, 242, 298, 30);
		txtCCCD.setDisabledTextColor(Color.BLACK);
		panelKhachHang.add(txtCCCD);
		
		JLabel lblNewLabel_1_1 = new JLabel("CCCD:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(42, 244, 86, 25);
		panelKhachHang.add(lblNewLabel_1_1);
		
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSDT.setDisabledTextColor(Color.BLACK);
		txtSDT.setColumns(10);
		txtSDT.setBorder(new LineBorder(Color.BLACK, 1));
		txtSDT.setBounds(177, 157, 298, 30);
		panelKhachHang.add(txtSDT);
		
		JLabel lblNewLabel_1_2 = new JLabel("SĐT:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(42, 159, 86, 25);
		panelKhachHang.add(lblNewLabel_1_2);
		
		// phần hóa đơn
		JPanel panelHoaDon = new JPanel();
		panelHoaDon.setBackground(Color.WHITE);
		panelHoaDon.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), 
			    "Thông tin hóa đơn", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 21), Color.BLACK));
		panelHoaDon.setBounds(32, 390, 539, 437);
		panelThongTin.add(panelHoaDon);
		panelHoaDon.setLayout(null);
		
		txtTongTienHD = new JTextField();
		txtTongTienHD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTongTienHD.setDisabledTextColor(Color.BLACK);
		txtTongTienHD.setEnabled(false);
		txtTongTienHD.setColumns(10);
		txtTongTienHD.setBounds(187, 36, 288, 30);
		txtTongTienHD.setBorder(new LineBorder(Color.BLACK, 1)); 
		panelHoaDon.add(txtTongTienHD);
		
		JLabel lblTinK = new JLabel("Khách đưa:");
		lblTinK.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTinK.setBounds(42, 167, 111, 25);
		panelHoaDon.add(lblTinK);
		
		JLabel lblTunTha = new JLabel("Tiền thừa:");
		lblTunTha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTunTha.setBounds(42, 210, 111, 25);
		panelHoaDon.add(lblTunTha);
		
		JLabel lblTongTienHD = new JLabel("Tổng giá:");
		lblTongTienHD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTongTienHD.setBounds(42, 38, 111, 25);
		panelHoaDon.add(lblTongTienHD);
		
		txtKhachDua = new JTextField();
		txtKhachDua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtKhachDua.setDisabledTextColor(Color.BLACK);
		txtKhachDua.setColumns(10);
		txtKhachDua.setBounds(187, 165, 288, 30);
		txtKhachDua.setBorder(new LineBorder(Color.BLACK, 1)); 
		panelHoaDon.add(txtKhachDua);
		
		txtTienThua = new JTextField();
		txtTienThua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTienThua.setDisabledTextColor(Color.BLACK);
		txtTienThua.setEnabled(false);
		txtTienThua.setColumns(10);
		txtTienThua.setBounds(187, 208, 288, 30);
		txtTienThua.setBorder(new LineBorder(Color.BLACK, 1)); 
		panelHoaDon.add(txtTienThua);
		
		JLabel lblGimGi = new JLabel("Giảm giá:");
		lblGimGi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGimGi.setBounds(42, 80, 111, 25);
		panelHoaDon.add(lblGimGi);
		
		JPanel panelGiamGia = new JPanel();
		panelGiamGia.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelGiamGia.setBounds(187, 79, 288, 30);
		panelHoaDon.add(panelGiamGia);
		panelGiamGia.setLayout(null);
		
		txtGiamGia = new JTextField();
		txtGiamGia.setBounds(1, 1, 254, 28);
		txtGiamGia.setDisabledTextColor(Color.BLACK);
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
		lblKhchPhiTr.setBounds(42, 124, 136, 25);
		panelHoaDon.add(lblKhchPhiTr);
		
		txtKhachPhaiTra = new JTextField();
		txtKhachPhaiTra.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtKhachPhaiTra.setDisabledTextColor(Color.decode("#E8C047"));
		txtKhachPhaiTra.setEnabled(false);
		txtKhachPhaiTra.setColumns(10);
		txtKhachPhaiTra.setBorder(new LineBorder(Color.BLACK, 1));
		txtKhachPhaiTra.setBounds(187, 122, 288, 30);
		panelHoaDon.add(txtKhachPhaiTra);
		
		btnTaoHD = new CustomButton("Tạo hóa đơn", UIStyles.ThemButtonStyle, null, CustomButtonIconSide.LEFT, () -> quayLai());
		btnTaoHD.setText("Thanh toán");
		btnTaoHD.setForeground(Color.WHITE);
		btnTaoHD.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnTaoHD.setBounds(10, 384, 257, 40);
		panelHoaDon.add(btnTaoHD);
		
		btnLamMoi = new CustomButton("Làm mới", UIStyles.NavBarButtonStyle, null, CustomButtonIconSide.LEFT, () -> quayLai());
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLamMoi.setBounds(271, 384, 257, 40);
		panelHoaDon.add(btnLamMoi);

		lblTenNV = new JLabel();
		lblTenNV.setForeground(Color.RED);
		lblTenNV.setBounds(32, 10, 225, 30);
		panelThongTin.add(lblTenNV);
		lblTenNV.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTenNV.setText(PhanMemQuanLyHieuThuoc.nhanVienHienTai.getHoTen()); // ***** Hoặc giá trị thực tế của nhân viên
		
		lblNgayLap = new JLabel();
		lblNgayLap.setForeground(Color.BLACK);
		lblNgayLap.setFont(new Font("Tahoma", Font.PLAIN, 20)); 
		lblNgayLap.setBounds(310, 10, 261, 30); 
		panelThongTin.add(lblNgayLap);
		lblNgayLap.setHorizontalAlignment(SwingConstants.RIGHT);
		
		// PHẦN BÁN HÀNG
		panelBanHang = new JPanel();
		panelBanHang.setBounds(10, 13, 1299, 827);
		panelTong.add(panelBanHang);
		panelBanHang.setLayout(null);
		panelBanHang.setBackground(UIStyles.BackgroundColor);
		
		// phần tìm kiếm
		JPanel panelTimKiem = new JPanel();
		panelTimKiem.setBounds(23, 13, 955, 45);
		panelTimKiem.setBorder(new LineBorder(Color.BLACK, 1, true));
		panelBanHang.add(panelTimKiem);
		panelTimKiem.setLayout(null);
		
		btnQuetMa = new JButton(UIStyles.BarCode);
		btnQuetMa.setBounds(1, 1, 56, 40);
		btnQuetMa.setBorder(null);
		btnQuetMa.setBackground(Color.WHITE);
		btnQuetMa.setFocusable(false);
		panelTimKiem.add(btnQuetMa);
		
		txtTimSP = new JTextField();
		txtTimSP.setBackground(Color.WHITE);
		txtTimSP.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtTimSP.setBounds(59, 2, 787, 40);
		txtTimSP.setBorder(new MatteBorder(0, 2, 0, 2, Color.BLACK));
		UIStyles.setPlaceholder(txtTimSP, "Mã sản phẩm");
		panelTimKiem.add(txtTimSP);
		txtTimSP.setBorder(null);
		txtTimSP.setColumns(10);
		
		btnThemSP = new CustomButton("Thêm", UIStyles.NavBarButtonStyle, null, CustomButtonIconSide.LEFT, () -> quayLai());
		btnThemSP.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnThemSP.setBounds(848, 3, 105, 40);
		btnThemSP.setFocusable(false);
		panelTimKiem.add(btnThemSP);
		
		comboBoxLoaiHD = new JComboBox<>();
		comboBoxLoaiHD.setBounds(990, 13, 288, 45);
		panelBanHang.add(comboBoxLoaiHD);
		comboBoxLoaiHD.setBackground(Color.WHITE);
		comboBoxLoaiHD.setFocusable(false);
		comboBoxLoaiHD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		// Thêm giá trị của enum LoaiHoaDon vào JComboBox
		for (LoaiHoaDon loaiHD : LoaiHoaDon.values()) 
			comboBoxLoaiHD.addItem(loaiHD);
		
		// Đặt giá trị mặc định là phần tử đầu tiên
		comboBoxLoaiHD.setSelectedIndex(0);
		
		// phần bảng
		banHangList = new CustomItemList(
				1255, 549,  10, 50, Color.white, 
				new int[]{30, 200, 240, 240, 240, 200, 100}, 
				Color.LIGHT_GRAY,  50, 
				new String[]{"","Tên sản phẩm", "ĐVT1", "ĐVT2", "ĐVT3", "Tổng tiền", ""}, 
				new Font("Arial", Font.BOLD, 20), 
				new ArrayList<CustomItem>()
		);
		
		banHangList.setBounds(23, 83, 1255, 654);
		panelBanHang.add(banHangList);
        
        txtGhiChu = new JTextField();
        txtGhiChu.setBounds(23, 750, 1255, 56);
        panelBanHang.add(txtGhiChu);
        txtGhiChu.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtGhiChu.setColumns(10);
        UIStyles.setPlaceholder(txtGhiChu, "Ghi chú đơn thuốc");
        
        // ĐĂNG KÝ SỰ KIỆN
        txtTimSDT.addActionListener(this);
        txtTimSP.addActionListener(this);
        txtGiamGia.addActionListener(this);
        txtKhachDua.addActionListener(this);
        txtKhachPhaiTra.addActionListener(this);
        txtTienThua.addActionListener(this);
        txtTongTienHD.addActionListener(this);
        
        btnGiamGia.addActionListener(this);
        btnLamMoi.addActionListener(this);
        btnQuetMa.addActionListener(this);
        btnTaoHD.addActionListener(this);
        btnThemKH.addActionListener(this);
        btnThemSP.addActionListener(this);
     
        comboBoxChietKhau.addActionListener(this);
        comboBoxLoaiHD.addActionListener(this);
        
        chckbxKhachLe.addActionListener(this);
        
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		String thongTinCanTim = "";
		String sdt;
	
		// tìm khách hàng
		if (o.equals(txtTimSDT)){
			thongTinCanTim = txtTimSDT.getText().trim();
			kh = bh_ctr.timKHTheoSDT(thongTinCanTim);
			
			if (kh == null) {
				thongBaoLoi(txtTimSDT, "Khách hàng không tồn tại");
				return;
			}
			else {
				chckbxKhachLe.setSelected(false);
				txtTenKH.setText(kh.getHoTen());
				txtCCCD.setText(kh.getCccd());
				txtDTL.setText(Integer.toString(kh.getDiemTichLuy()));
				txtSDT.setText(kh.getSdt());
				
				sdt = txtSDT.getText();
				tinhChietKhau(sdt);
			}
			
			txtTimSDT.setText("");
		}
		
		// tìm sản phẩm bằng mã vạch
		if (o.equals(txtTimSP) || o.equals(btnThemSP) ) {
			
			thongTinCanTim = txtTimSP.getText().trim();
			sdt = txtSDT.getText();
			
			sp = SanPhamCTR.timSanPhamTheoMaVach(thongTinCanTim);
			
			if (sp == null) {
				thongBaoLoi(txtTimSP, "Sản phẩm không tồn tại");
				return;
			}
			else {
				phanPhanTramKM = 0;
				
				for (CustomItem item : banHangList.getItemList()) {
			        if (item instanceof BanHangRow) {
			            BanHangRow row = (BanHangRow) item;
			            Thuoc sanPham = row.getSanPhamYTe(); // Lấy đối tượng Thuoc từ BanHangRow

			            if (sanPham.maThuoc.equalsIgnoreCase(sp.getMaSanPham())) {
			                sanPham.soLuongDVT1 += 1; // Cập nhật số lượng

			                // Cập nhật lại BanHangRow trong banHangList
			                row.setSanPhamYTe(sanPham); // Đảm bảo BanHangRow được cập nhật với thuốc mới

			                tinhCacLoaiTienCuaHD();
			                txtTimSP.setText("");
			                return;
			            }
			        }
				}
				
				// Nếu không tìm thấy, thêm mới vào danh sách
	            int stt = banHangList.getItemList().size() + 1;
	            banHangList.addItem(new BanHangRow(stt, new Thuoc(sp.getMaSanPham(), sp.getTenSanPham(), sp.getDonViTinh1(), sp.getDonViTinh2(), sp.getDonViTinh3(), 1, 0, 0, sp.getGiaBanDonViTinh1(), sp.getGiaBanDonViTinh2(), sp.getGiaBanDonViTinh3(), sp.getGiaTriQuyDoi2(), sp.getGiaTriQuyDoi3())));
	            
	            tinhChietKhau(sdt);
			}
			
			txtTimSP.setText("");
		}
		

		// thay đổi lựa chọn chiết khấu
		if (o.equals(comboBoxChietKhau)) {
			tinhCacLoaiTienCuaHD();
		}
		
		// làm mới
		if (o.equals(btnLamMoi)) {
			xoaTrangTTKH();
			lamMoi();
		}
		
		// chọn mã khuyến mãi
		if (o.equals(btnGiamGia))
			formThongTinKhuyenMai();
		
		// chọn loại khuyến mãi
		if (o.equals(btnChon)) {
			frameKhuyenMai.dispose();
			tinhCacLoaiTienCuaHD();
		}
		
		// chọn nút thoát
		if (o.equals(btnThoat))
			frameKhuyenMai.dispose();
		
		// chọn khách kẻ
		if (chckbxKhachLe.isSelected()) {
			kh = bh_ctr.timKHTheoMaKH("KH000000");
			
			txtTenKH.setText(kh.getHoTen());
			txtCCCD.setText(kh.getCccd());
			txtSDT.setText(kh.getSdt());
		}
		
		// thêm khách hàng
		if (o.equals(btnThemKH)) {
				
			String tenKh = txtTenKH.getText().trim();
			String sdtKH = txtSDT.getText().trim();
			
			kh = bh_ctr.timKHTheoSDT(sdtKH);
			
			if (kh == null) {	
				int diemTicLuy = 0;
				String ma = kh_ctr.taoMa();

				KhachHang khachHangMoi = new KhachHang(ma, tenKh, sdtKH, diemTicLuy);
				
				if (bh_ctr.themKH(khachHangMoi)) {
					chckbxKhachLe.setSelected(false);
					txtTenKH.setText(khachHangMoi.getHoTen());
					txtCCCD.setText(khachHangMoi.getCccd());
					txtDTL.setText(Integer.toString(khachHangMoi.getDiemTichLuy()));
					txtSDT.setText(khachHangMoi.getSdt());
		
					tinhChietKhau(sdtKH);
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Khách hàng đã tồn tại");
				return;
			}
			

		}
		
		if (o.equals(txtKhachDua)) {
			if (o.equals(txtKhachDua)) {
			    BigDecimal tienKhachPhaiTra = bh_ctr.removeFormatting(txtKhachPhaiTra.getText().trim());
			    BigDecimal tienKhachDua = new BigDecimal(txtKhachDua.getText().trim());

			    BigDecimal tienThua = bh_ctr.tinhTienTraKhach(tienKhachDua, tienKhachPhaiTra);
			    
			    txtTienThua.setText(bh_ctr.formatDecimal(tienThua));
			}

		}
		
		// thêm hóa đơn
		if (o.equals(btnTaoHD)) {
			
			if (kiemTraSLTon(banHangList)) {
				
				if ( txtSDT.getText().trim() == null) {
					JOptionPane.showMessageDialog(this, "Phải nhập thông tin khách hàng");
					return;
				}
				
				if (txtSDT.getText().trim() == null && !chckbxKhachLe.isSelected() ) {
					JOptionPane.showMessageDialog(this, "Phải nhập thông tin khách hàng");
					return;
				}
				
				if (banHangList.getItemList().size() == 0) {
					JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm");
					return;
				}
				
				String maHD = bh_ctr.taoMaHoaDon();
				LocalDate localDate = LocalDate.now(); // Lấy ngày hiện tại
				Date ngayTao = Date.valueOf(localDate);
				int dtl = bh_ctr.tinhSoDTLDaDung(tienChietKhau);
				BigDecimal thanhTien = tienKhachPhaiTra;
				NhanVien nhanVien = PhanMemQuanLyHieuThuoc.nhanVienHienTai;
				KhachHang khachHang = new KhachHang(kh.getMaKhachHang());
				KhuyenMai khuyenMai = new KhuyenMai(maKM);
				int diemDaDung = bh_ctr.tinhSoDTLDaDung(tienChietKhau);
				LoaiHoaDon loaiHD = (LoaiHoaDon) comboBoxLoaiHD.getSelectedItem();
				
				HoaDon hoaDon = new HoaDon(maHD, ngayTao, dtl, thanhTien, nhanVien, khuyenMai, khachHang, loaiHD);
				
				if (bh_ctr.themHD(hoaDon)) {
		
					bh_ctr.capNhatDTL(khachHang, diemDaDung, thanhTien);
					
					for (CustomItem item : banHangList.getItemList()) {
				        if (item instanceof BanHangRow) {
				            BanHangRow row = (BanHangRow) item;
				            Thuoc sanPham = row.getSanPhamYTe(); // Lấy đối tượng Thuoc từ BanHangRow

				            String maCTHD = bh_ctr.taoMaChiTietHoaDon(hoaDon.getMaHoaDon());
							BigDecimal tongTien = bh_ctr.tinhTongTienTungSP(sanPham.giaBanDonViTinh1, sanPham.giaBanDonViTinh2, sanPham.giaBanDonViTinh3, 
									sanPham.soLuongDVT1 + "", sanPham.soLuongDVT2 + "", sanPham.soLuongDVT3 + "");
							HoaDon hd = new HoaDon(maHD);
							String maSP = sanPham.maThuoc;
							SanPhamYTe sanPhamYTe = new SanPhamYTe(maSP);
							
							String maLo = bh_ctr.timMaLoTheoMaSP(maSP);
							LoHang LoHang = new LoHang(maLo);
							
							String maLoThayThe = null;
							LoHang LohayThe = new LoHang(maLoThayThe);

							ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(maCTHD, sanPham.soLuongDVT1, sanPham.soLuongDVT2, sanPham.soLuongDVT3,
									tongTien, hd, sanPhamYTe, LoHang, LohayThe);
							
							if (bh_ctr.themChiTietHoaDon(chiTietHoaDon) && bh_ctr.capNhatSoLuongSP(sanPham.maThuoc, sanPham.soLuongDVT1, sanPham.soLuongDVT2, sanPham.soLuongDVT3)
									&& bh_ctr.capNhatSoLuongKM(khuyenMai.getMaKhuyenMai())) 
								continue;
				        }
					}
					
					JOptionPane.showMessageDialog(this, "Thêm hóa đơn thành công");
				}
					
				else
					JOptionPane.showMessageDialog(this, "Thêm HD không thành công");

				xoaTrangTTKH();
				lamMoi();
			}
			
		}
		
	}
	
	private void tinhChietKhau(String sdt) {
		ArrayList<Integer> dsCK = bh_ctr.tinhTienChietKhau(sdt);
		comboBoxChietKhau.removeAllItems();

		
		for (Integer ck : dsCK)
			comboBoxChietKhau.addItem(bh_ctr.formatDecimal(new BigDecimal(ck)));
		
		tinhCacLoaiTienCuaHD();
		
	}
	
	public static void tinhCacLoaiTienCuaHD() {
		if (banHangList.getItemList().size() > 0) {
			tongTienHD = bh_ctr.tinhTongTienHoaDon(banHangList);
			txtTongTienHD.setText(bh_ctr.formatDecimal(tongTienHD));
			
			tienKhuyenMai = bh_ctr.tinhKhuyenMai(tongTienHD, phanPhanTramKM);
			txtGiamGia.setText(bh_ctr.formatDecimal(tienKhuyenMai));
			
			if (phanPhanTramKM == 0) {
				Object[][] dsKM = bh_ctr.timDSKhuyenMai(tongTienHD);
//				maKM = (String) dsKM[0][0];
			}
				
			Object selectedValue = comboBoxChietKhau.getSelectedItem();
			if (selectedValue != null) {
			    tienChietKhau = bh_ctr.removeFormatting(selectedValue.toString());
			} 
			
			tienKhachPhaiTra = bh_ctr.tinhTienKhachPhaiTra(tongTienHD, tienKhuyenMai, tienChietKhau);
			txtKhachPhaiTra.setText(bh_ctr.formatDecimal(tienKhachPhaiTra));
			
			txtKhachDua.setText(bh_ctr.formatDecimal(tienKhachPhaiTra));
			txtTienThua.setText(bh_ctr.formatDecimal(new BigDecimal(0)));
		}
		else {
			lamMoi();
		}
		
	}
	
	private JFrame formThongTinKhuyenMai() {
	    // Khởi tạo frameKhuyenMai trước
	    frameKhuyenMai = new JFrame();
	    frameKhuyenMai.setSize(950, 630); // Đặt kích thước cửa sổ
	    frameKhuyenMai.setResizable(false);
	    frameKhuyenMai.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
	    frameKhuyenMai.setLocationRelativeTo(null); // Đặt cửa sổ ở giữa màn hình
	    frameKhuyenMai.setVisible(true); // Hiển thị cửa sổ

	    JPanel panelTong = new JPanel();
	    panelTong.setBounds(0, 0, 950, 630);
	    panelTong.setLayout(null);
	    frameKhuyenMai.getContentPane().add(panelTong);
	    
	    JLabel lblTieuDe = new JLabel("Danh sách các khuyến mãi có thể áp dụng");
	    lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 28));
	    lblTieuDe.setBounds(165, 16, 591, 53);
	    panelTong.add(lblTieuDe);
	    
	    // Tạo dữ liệu bảng và tiêu đề
        String[] header = {"Mã khuyến mãi", "Điều kiện", "Chiết khấu", "Số tiền giảm"};
        data = bh_ctr.timDSKhuyenMai(bh_ctr.tinhTongTienHoaDon(banHangList));
        tableKM = new CustomTable(data, header, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle, 20);

        // Thêm bảng vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(tableKM);
        scrollPane.setPreferredSize(new Dimension(910, 400));
        scrollPane.setBorder(new LineBorder(Color.GRAY, 1, true));
        scrollPane.setBounds(10, 90, 910, 400); // Đặt kích thước và vị trí của scrollPane
        panelTong.add(scrollPane);
        
        btnChon = new CustomButton("Chọn", UIStyles.ThemButtonStyle, null, CustomButtonIconSide.LEFT, () -> quayLai());
	    btnChon.setFont(new Font("Tahoma", Font.PLAIN, 24));
	    btnChon.setBounds(351, 520, 123, 45);
	    panelTong.add(btnChon);
	   
	    btnThoat = new CustomButton("Thoát", UIStyles.ThemButtonStyle, null, CustomButtonIconSide.LEFT, () -> quayLai());
	    btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 24));
	    btnThoat.setBounds(484, 520, 123, 45);
	    panelTong.add(btnThoat);
	    
	    btnChon.addActionListener(this);
        btnThoat.addActionListener(this);
        
        tableKM.addMouseListener(this);

	    return frameKhuyenMai;
	}

	public static class BanHangRow extends CustomItem implements ActionListener{
		private static int prefWidth = 1250;
		private static int prefHeight = 100;
		private static Font font = UIStyles.DefaultFont;
		private static Color backgroundColor = Color.WHITE;
		private static Border border = BorderFactory.createEmptyBorder();
		private BanHangCTR bh_ctr = new BanHangCTR();
	
		private static int[] cellsWidth = new int[] {30, 220, 220, 240, 240, 200, 100};

		private JComponent[] cells;

		public Thuoc thuoc;	
		private JLabel lblTenSP;
		private JLabel lblTongTienSP;
		private CustomButton btnXoa;
		private JLabel lblSTT;
		private int STT;
		private CustomButton btnGiam1;
		private static JTextField txtSLBan1;
		private CustomButton btnTang1;
		private JLabel lblDVT1;
		private CustomButton btnGiam2;
		private static JTextField txtSLBan2;
		private CustomButton btnTang2;
		private JLabel lblDVT2;
		private CustomButton btnGiam3;
		private static JTextField txtSLBan3;
		private CustomButton btnTang3;
		private JLabel lblDVT3;
		private int thayDoi1_1;
		
		private int thayDoi1 = 0;
		private int thayDoi2 = 0;
		private int thayDoi3 = 0;
		private int thayDoi2_2;
		private int thayDoi3_3;
			
		
		public BanHangRow(int stt, Thuoc thuoc) {
			super(prefWidth, prefHeight, backgroundColor, border, cellsWidth);

			this.thuoc = thuoc;

		//Thiết kế các cell
			this.STT = stt;
			lblSTT = new JLabel(stt+"");
			lblSTT.setFont(new Font(lblSTT.getFont().getName(), Font.PLAIN, 20));
		    
			// cột 2: tên sản phẩm
		    JPanel cell2 = new JPanel();
		    cell2.setBackground(Color.WHITE);
		    cell2.setLayout(new BorderLayout());
		    
		    lblTenSP = new JLabel(thuoc.tenThuoc);
		    lblTenSP.setFont(new Font(lblTenSP.getFont().getName(), Font.PLAIN, 20));
		    lblTenSP.setHorizontalAlignment(SwingConstants.LEFT); 
		    cell2.add(lblTenSP, BorderLayout.CENTER);
		    
		    // cột 3: đơn vị tính
		    JPanel cell3 = new JPanel();
		    cell3.setBackground(Color.WHITE);
		    cell3.setLayout(null); // Bố cục tự do
		    cell3.setPreferredSize(new Dimension(240, 80)); // Chiều rộng 240px, chiều cao 80px để có đủ không gian
		    cell3.setBounds(10, 30, 240, 80);
		    // Nút giảm
		    btnGiam1 = new CustomButton("-", UIStyles.LabelBarButtonStyle, null, CustomButtonIconSide.LEFT, () -> quayLai());
		    btnGiam1.setBounds(5, 15, 40, 30); // Đặt nút giảm rộng hơn

		    // TextField số lượng
		    txtSLBan1 = new JTextField();
		    txtSLBan1.setText(thuoc.soLuongDVT1 + "");
		    txtSLBan1.setFont(new Font("Arial", Font.PLAIN, 16));
		    txtSLBan1.setHorizontalAlignment(JTextField.CENTER);
		    txtSLBan1.setBounds(50, 15, 80, 30); // Đặt TextField rộng hơn

		    // Nút tăng
		    btnTang1 = new CustomButton("+", UIStyles.LabelBarButtonStyle, null, CustomButtonIconSide.LEFT, () -> quayLai());
		    btnTang1.setBounds(135, 15, 40, 30); // Đặt nút tăng rộng hơn

		    // Nhãn đơn vị tính
		    lblDVT1 = new JLabel(thuoc.donViTinh1.toString() + '/' + bh_ctr.formatDecimal(thuoc.giaBanDonViTinh1));
		    lblDVT1.setFont(new Font(lblDVT1.getFont().getName(), Font.PLAIN, 18)); // Tăng kích thước font và chữ đậm
		    lblDVT1.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa nhãn
		    lblDVT1.setBounds(40, 45, 160, 30); // Đặt vị trí xuống dưới và căn giữa

		    // Thêm các thành phần vào panel
		    cell3.add(btnGiam1);
		    cell3.add(txtSLBan1);
		    cell3.add(btnTang1);
		    cell3.add(lblDVT1);
		    
		    // cột 4: 
		    JPanel cell4 = new JPanel();
		    cell4.setBackground(Color.WHITE);
		    cell4.setLayout(null); // Bố cục tự do
		    cell4.setPreferredSize(new Dimension(240, 80)); // Chiều rộng 240px, chiều cao 80px để có đủ không gian

		    // Nút giảm
		    btnGiam2 = new CustomButton("-", UIStyles.LabelBarButtonStyle, null, CustomButtonIconSide.LEFT, () -> quayLai());
		    btnGiam2.setBounds(5, 15, 40, 30); // Đặt nút giảm rộng hơn

		    // TextField số lượng
		    txtSLBan2 = new JTextField();
		    txtSLBan2.setText(thuoc.soLuongDVT2 + "");
		    txtSLBan2.setFont(new Font("Arial", Font.PLAIN, 16));
		    txtSLBan2.setHorizontalAlignment(JTextField.CENTER);
		    txtSLBan2.setBounds(50, 15, 80, 30); // Đặt TextField rộng hơn

		    // Nút tăng
		    btnTang2 = new CustomButton("+", UIStyles.LabelBarButtonStyle, null, CustomButtonIconSide.LEFT, () -> quayLai());
		    btnTang2.setBounds(135, 15, 40, 30); // Đặt nút tăng rộng hơn

		    // Nhãn đơn vị tính
		    lblDVT2 = new JLabel(thuoc.donViTinh2.toString() + '/' + bh_ctr.formatDecimal(thuoc.giaBanDonViTinh2));
		    lblDVT2.setFont(new Font(lblDVT2.getFont().getName(), Font.PLAIN, 18)); // Tăng kích thước font và chữ đậm
		    lblDVT2.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa nhãn
		    lblDVT2.setBounds(40, 45, 160, 30); // Đặt vị trí xuống dưới và căn giữa

		    // Thêm các thành phần vào panel
		    cell4.add(btnGiam2);
		    cell4.add(txtSLBan2);
		    cell4.add(btnTang2);
		    cell4.add(lblDVT2);

		    // cột 5: 
		    JPanel cell5 = new JPanel();
		    cell5.setBackground(Color.WHITE);
		    cell5.setLayout(null); // Bố cục tự do
		    cell5.setPreferredSize(new Dimension(240, 80)); // Chiều rộng 240px, chiều cao 80px để có đủ không gian

		    // Nút giảm
		    btnGiam3 = new CustomButton("-", UIStyles.LabelBarButtonStyle, null, CustomButtonIconSide.LEFT, () -> quayLai());
		    btnGiam3.setBounds(5, 15, 40, 30); // Đặt nút giảm rộng hơn

		    // TextField số lượng
		    txtSLBan3 = new JTextField();
		    txtSLBan3.setText(thuoc.soLuongDVT3+ "");
		    txtSLBan3.setFont(new Font("Arial", Font.PLAIN, 16));
		    txtSLBan3.setHorizontalAlignment(JTextField.CENTER);
		    txtSLBan3.setBounds(50, 15, 80, 30); // Đặt TextField rộng hơn

		    // Nút tăng
		    btnTang3 = new CustomButton("+", UIStyles.LabelBarButtonStyle, null, CustomButtonIconSide.LEFT, () -> quayLai());
		    btnTang3.setBounds(135, 15, 40, 30); // Đặt nút tăng rộng hơn

		    // Nhãn đơn vị tính
		    lblDVT3 = new JLabel(thuoc.donViTinh3.toString() + '/' + bh_ctr.formatDecimal(thuoc.giaBanDonViTinh3));
		    lblDVT3.setFont(new Font(lblDVT3.getFont().getName(), Font.PLAIN, 18)); // Tăng kích thước font và chữ đậm
		    lblDVT3.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa nhãn
		    lblDVT3.setBounds(40, 45, 160, 30); // Đặt vị trí xuống dưới và căn giữa

		    // Thêm các thành phần vào panel
		    cell5.add(btnGiam3);
		    cell5.add(txtSLBan3);
		    cell5.add(btnTang3);
		    cell5.add(lblDVT3);

		    // cột 6: tổng tiền
		    JPanel cell6 = new JPanel();
		    cell6.setBackground(Color.WHITE);
		    cell6.setLayout(new BorderLayout());
		    
		    BigDecimal tongTien = bh_ctr.tinhTongTienTungSP(thuoc.giaBanDonViTinh1, thuoc.giaBanDonViTinh2, thuoc.giaBanDonViTinh3, 
		    		txtSLBan1.getText().trim(), txtSLBan2.getText().trim(), txtSLBan3.getText().trim());
		    lblTongTienSP = new JLabel(bh_ctr.formatDecimal(tongTien));
		    lblTongTienSP.setFont(new Font(lblTongTienSP.getFont().getName(), Font.PLAIN, 20));
		    lblTongTienSP.setHorizontalAlignment(SwingConstants.CENTER); 
		    cell6.add(lblTongTienSP, BorderLayout.CENTER);
		    
		    // cột 7: nút xóa
		    JPanel cell7 = new JPanel();
		    cell7.setBackground(Color.WHITE);
		    cell7.setLayout(new BorderLayout());
		    
		    btnXoa = new CustomButton("", UIStyles.XoaSanPhamButtonStyle, UIStyles.Trash, CustomButtonIconSide.LEFT, ()->quayLai());
		    btnXoa.setPreferredSize(new Dimension(30, 30));
		    btnXoa.setFocusable(false);
		    btnXoa.setBackground(Color.WHITE);
		    btnXoa.setHorizontalAlignment(SwingConstants.CENTER); 
		    cell7.add(btnXoa, BorderLayout.CENTER);
		    
		    cells = new JComponent[] {lblSTT, cell2, cell3, cell4, cell5, cell6, cell7};
		    
		    super.addCells(cells);
		    
		    btnGiam1.addActionListener(this);
		    btnGiam2.addActionListener(this);
		    btnGiam3.addActionListener(this);
		    
		    btnTang1.addActionListener(this);
		    btnTang2.addActionListener(this);
		    btnTang3.addActionListener(this);
		    
		    btnXoa.addActionListener(this);
		    
//		    txtSLBan1.addActionListener(this);
//		    txtSLBan2.addActionListener(this);
//		    txtSLBan3.addActionListener(this);
		    
		    txtSLBan1.addActionListener(e -> {
		        String soLuongText = txtSLBan1.getText().trim();
		        
		        if(kiemTraSLNhapVao(soLuongText)) {
//		        	thayDoi1_1 = 0;  
//		            thayDoi2_2 = 0;
//		            thayDoi3_3 = 0;
//		            
		        	int soLuong = Integer.parseInt(soLuongText);
//		            thayDoi1_1 = soLuong - thuoc.soLuongDVT1;  
//		            thayDoi2_2 = 0;
//		            thayDoi3_3 = 0;
//		            System.out.println(thayDoi1);
//		            System.out.println(thayDoi1_1 + ", " + thayDoi2_2 + ", " + thayDoi3_3);
//		            capNhatSL(thayDoi1_1, thayDoi2_2, thayDoi3_3);
		        	thuoc.soLuongDVT1 = soLuong;
		        	txtSLBan1.setText(soLuongText);
		        	tinhCacLoaiTienCuaHD();
		        	BigDecimal tongTien1 = bh_ctr.tinhTongTienTungSP(thuoc.giaBanDonViTinh1, thuoc.giaBanDonViTinh2, thuoc.giaBanDonViTinh3, 
				    		txtSLBan1.getText().trim(), txtSLBan2.getText().trim(), txtSLBan3.getText().trim());
			        lblTongTienSP.setText(bh_ctr.formatDecimal(tongTien1));
		        } else {
		        	JOptionPane.showMessageDialog(this, "Chỉ nhập số");
		        	txtSLBan1.setText(String.valueOf(thuoc.soLuongDVT1));
		        }
	            
		        
		    });
		    
		    txtSLBan2.addActionListener(e -> {
		        String soLuongText = txtSLBan2.getText().trim();
		        
		        if(kiemTraSLNhapVao(soLuongText)) {
		        	int soLuong = Integer.parseInt(soLuongText);
		        	thuoc.soLuongDVT2 = soLuong;
		        	tinhCacLoaiTienCuaHD();
		        	BigDecimal tongTien1 = bh_ctr.tinhTongTienTungSP(thuoc.giaBanDonViTinh1, thuoc.giaBanDonViTinh2, thuoc.giaBanDonViTinh3, 
				    		txtSLBan1.getText().trim(), txtSLBan2.getText().trim(), txtSLBan3.getText().trim());
			        lblTongTienSP.setText(bh_ctr.formatDecimal(tongTien1));
		        } else {
		        	JOptionPane.showMessageDialog(this, "Chỉ nhập số");
		        	txtSLBan2.setText(String.valueOf(thuoc.soLuongDVT2));
		        }
	            
		        
		    });
		    
		    txtSLBan3.addActionListener(e -> {
		        String soLuongText = txtSLBan3.getText().trim();
		        
		        if(kiemTraSLNhapVao(soLuongText)) {
		        	int soLuong = Integer.parseInt(soLuongText);
		        	thuoc.soLuongDVT3 = soLuong;
		        	tinhCacLoaiTienCuaHD();
		        	BigDecimal tongTien1 = bh_ctr.tinhTongTienTungSP(thuoc.giaBanDonViTinh1, thuoc.giaBanDonViTinh2, thuoc.giaBanDonViTinh3, 
				    		txtSLBan1.getText().trim(), txtSLBan2.getText().trim(), txtSLBan3.getText().trim());
			        lblTongTienSP.setText(bh_ctr.formatDecimal(tongTien1));
		        } else {
		        	JOptionPane.showMessageDialog(this, "Chỉ nhập số");
		        	txtSLBan3.setText(String.valueOf(thuoc.soLuongDVT3));
		        }
	            
		        
		    });
		}
		
		public void capNhatSoLuong(int thayDoi1, int thayDoi2, int thayDoi3) {
			thuoc.soLuongDVT1 += thayDoi1;
			thuoc.soLuongDVT2 += thayDoi2;
			thuoc.soLuongDVT3 += thayDoi3;
			
			txtSLBan1.setText("" + thuoc.soLuongDVT1);
			txtSLBan2.setText("" + thuoc.soLuongDVT2);
			txtSLBan3.setText("" + thuoc.soLuongDVT3);
		}
		
		public void layThongTin() {
			System.out.println(this.thuoc);
		}
		
		public void setSanPhamYTe(Thuoc sanPham) {
	        this.thuoc = sanPham;  // Cập nhật đối tượng Thuoc
	        capNhatRowUI();
	    }
		
		public Thuoc getSanPhamYTe() { 
	        return thuoc;
	    }
		
		private void capNhatRowUI() {
	        txtSLBan1.setText(String.valueOf(thuoc.soLuongDVT1));
	        txtSLBan2.setText(String.valueOf(thuoc.soLuongDVT2));
	        txtSLBan3.setText(String.valueOf(thuoc.soLuongDVT3));
	        BigDecimal tongTien = bh_ctr.tinhTongTienTungSP(thuoc.giaBanDonViTinh1, thuoc.giaBanDonViTinh2, thuoc.giaBanDonViTinh3, 
		    		txtSLBan1.getText().trim(), txtSLBan2.getText().trim(), txtSLBan3.getText().trim());
		    lblTongTienSP.setText(bh_ctr.formatDecimal(tongTien));
	    }
		
		 public void setSTT(int stt) {
		        this.STT = stt;  // Cập nhật STT
		        lblSTT.setText(String.valueOf(stt));  // Cập nhật lại giá trị STT trên giao diện
		    }

		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			
			
			
			int SLSP1 = Integer.parseInt(txtSLBan1.getText());
			int SLSP2 = Integer.parseInt(txtSLBan2.getText());
			int SLSP3 = Integer.parseInt(txtSLBan3.getText());
			
			if (banHangList.getItemList().size() >= 1) {
				thayDoi1 = 0;
				thayDoi2 = 0;
				thayDoi3 = 0;
				
				if (o.equals(btnTang1)) {
			        thayDoi1 = 1; 
			    }
				
				if (o.equals(btnTang2)) {
			        thayDoi2 = 1; 
			    }
				
				if (o.equals(btnTang3)) {
			        thayDoi3 = 1; 
			    }
				
				if (o.equals(btnGiam1)) {
			    	if (txtSLBan1.getText().equals("1") && txtSLBan2.getText().trim().equals("0")  && txtSLBan3.getText().trim().equals("0")) {
			    		banHangList.removeItem(this);
				    	capNhatBang(banHangList);
				    	tinhCacLoaiTienCuaHD();
			    	}
			    	if (txtSLBan1.getText().equals("0") && (!txtSLBan2.getText().trim().equals("0")  || !txtSLBan3.getText().trim().equals("0"))) {
			    		thayDoi1 = 0; 
			    	}
			    	else 
			    		thayDoi1 = -1; 
			    }
				
				if (o.equals(btnGiam2)) {
			    	if (txtSLBan2.getText().equals("1") && txtSLBan1.getText().trim().equals("0")  && txtSLBan3.getText().trim().equals("0")) {
			    		banHangList.removeItem(this);
				    	capNhatBang(banHangList);
				    	tinhCacLoaiTienCuaHD();
			    	}
			    	if (txtSLBan2.getText().equals("0") && (!txtSLBan1.getText().trim().equals("0")  || !txtSLBan3.getText().trim().equals("0"))) {
			    		thayDoi2 = 0;
			    	}
			    	else 
			    		thayDoi2 = -1; 
			    }
				
				if (o.equals(btnGiam3)) {
			    	if (txtSLBan3.getText().equals("1") && txtSLBan2.getText().trim().equals("0")  && txtSLBan1.getText().trim().equals("0")) {
			    		banHangList.removeItem(this);
				    	capNhatBang(banHangList);
				    	tinhCacLoaiTienCuaHD();
			    	}
			    	if (txtSLBan3.getText().equals("0") && (!txtSLBan2.getText().trim().equals("0")  || !txtSLBan1.getText().trim().equals("0"))) {
			    		thayDoi3 = 0;
			    	}
			    	else 
			    		thayDoi3 = -1; 
			    }
				
			    if (thayDoi1 != 0 || thayDoi2 != 0 || thayDoi3 != 0) {
			        capNhatSoLuong(thayDoi1, thayDoi2, thayDoi3); 
			                      
			        BigDecimal tongTien = bh_ctr.tinhTongTienTungSP(thuoc.giaBanDonViTinh1, thuoc.giaBanDonViTinh2, thuoc.giaBanDonViTinh3, 
				    		txtSLBan1.getText().trim(), txtSLBan2.getText().trim(), txtSLBan3.getText().trim());
			        lblTongTienSP.setText(bh_ctr.formatDecimal(tongTien));
			        SLSP1 = thuoc.soLuongDVT1;
			        SLSP2 = thuoc.soLuongDVT2;
			        SLSP3 = thuoc.soLuongDVT3;
			        tinhCacLoaiTienCuaHD();
			    }
			    
			    if (o.equals(btnXoa) ) {
			    	banHangList.removeItem(this);
			    	capNhatBang(banHangList);
			    	tinhCacLoaiTienCuaHD();
			    }
			    
			} 
		}
		
//		private void capNhatSL(int thayDoi1_1, int thayDoi2_2, int thayDoi3_3) {
//			 	capNhatSoLuong(thayDoi1_1, thayDoi2_2, thayDoi3_3); 
//             
////				int SLSP1 = Integer.parseInt(txtSLBan1.getText());
////				int SLSP2 = Integer.parseInt(txtSLBan2.getText());
////				int SLSP3 = Integer.parseInt(txtSLBan3.getText());
//				
////		        BigDecimal tongTien = bh_ctr.tinhTongTienTungSP(thuoc.giaBanDonViTinh1, thuoc.giaBanDonViTinh2, thuoc.giaBanDonViTinh3, 
////			    		txtSLBan1.getText().trim(), txtSLBan2.getText().trim(), txtSLBan3.getText().trim());
////		        lblTongTienSP.setText(bh_ctr.formatDecimal(tongTien));
//		        
////		        SLSP1 = thuoc.soLuongDVT1;
////		        SLSP2 = thuoc.soLuongDVT2;
////		        SLSP3 = thuoc.soLuongDVT3;
//		        
//		        tinhCacLoaiTienCuaHD();
//		}
		
		private boolean kiemTraSLNhapVao(String soLuong) {
		    return soLuong.matches("\\d+");  // Kiểm tra chuỗi có chứa chỉ các chữ số
		}

		private void capNhatBang(CustomItemList list) {
		    ArrayList<CustomItem> bang = new ArrayList<>();
		    
		    int stt = 1; 
		    for (CustomItem item : list.getItemList()) { 
		        if (item instanceof BanHangRow) {
		           
		            BanHangRow banHangRow = (BanHangRow) item;
		            banHangRow.setSTT(stt); 
		            stt++; 
		        }
		        bang.add(item); 
		    }
		    
		    banHangList.updateList(bang);
		}
	}
	
	private void xoaTrangTTKH() {
		txtTenKH.setText("");
		txtSDT.setText("");
		txtDTL.setText("");
		txtCCCD.setText("");
		comboBoxChietKhau.removeAllItems();
		chckbxKhachLe.setSelected(false);
		kh = null;
	}
	
	public static void lamMoi() {
		phanPhanTramKM = 0;
		tienChietKhau = BigDecimal.ZERO;
		tienKhuyenMai = BigDecimal.ZERO;
		tongTienHD = BigDecimal.ZERO;
		tienKhachPhaiTra = BigDecimal.ZERO;
		txtTongTienHD.setText("");
		txtGiamGia.setText("");
		txtKhachPhaiTra.setText("");
		txtKhachDua.setText("");
		txtTienThua.setText("");
		comboBoxLoaiHD.setSelectedIndex(0);
		comboBoxChietKhau.removeAllItems();
		
//		if (banHangList.getItemList().size() == 1)
//			banHangList.removeAllItems();
//		else
		banHangList.updateList(new ArrayList<>());
		banHangList.repaint();

	}
	
	private boolean kiemTraSLTon(CustomItemList banHangList) {
		for (CustomItem item : banHangList.getItemList()) {
	        if (item instanceof BanHangRow) {
	            BanHangRow row = (BanHangRow) item;
	            Thuoc sanPham = row.getSanPhamYTe(); // Lấy đối tượng Thuoc từ BanHangRow

	            LoHang loHang = bh_ctr.timLoHang(sanPham.maThuoc);
	            
	            if (loHang.getSoLuongDonViTinh1() < sanPham.soLuongDVT1) {
	                JOptionPane.showMessageDialog(this, "Sản phẩm " + sanPham.tenThuoc + "(ĐVT1) chi còn " + loHang.getSoLuongDonViTinh1());
//	                sanPham.soLuongDVT1 = loHang.getSoLuongDonViTinh1();
	                return false;
	            }
	            if (loHang.getSoLuongDonViTinh2() < sanPham.soLuongDVT2) {
	            	 JOptionPane.showMessageDialog(this, "Sản phẩm " + sanPham.tenThuoc + "(ĐVT2) chi còn " + loHang.getSoLuongDonViTinh2());
//	            	 sanPham.soLuongDVT2 = loHang.getSoLuongDonViTinh2();
	                return false;
	            }
	            if ((loHang.getSoLuongDonViTinh3() < sanPham.soLuongDVT3) ) {
	            	 JOptionPane.showMessageDialog(this, "Sản phẩm " + sanPham.tenThuoc + "(ĐVT3) chi còn " + loHang.getSoLuongDonViTinh3());
//	            	 sanPham.soLuongDVT3 = loHang.getSoLuongDonViTinh3();
	                return false;
	            }
	        }
		}
		
		return true;
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

	private void hienThiTGHienTai() {
	    LocalDateTime tgHienTai = LocalDateTime.now();
	    lblNgayLap.setText(tgHienTai.format(formatter));
	}
	
	private void thongBaoLoi(JTextField txt, String loi) {
 		txt.requestFocus();
		JOptionPane.showMessageDialog(this, loi);
 	}
	
	private static void quayLai() {}

	@Override
	public void mouseClicked(MouseEvent e) {
		int dongDuocChon = tableKM.getSelectedRow();
		phanPhanTramKM = Float.parseFloat(tableKM.getValueAt(dongDuocChon, 2).toString());
		maKM = (tableKM.getValueAt(dongDuocChon, 0).toString());
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}

