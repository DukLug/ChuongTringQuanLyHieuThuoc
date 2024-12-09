package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.synth.SynthScrollPaneUI;

import component.CustomButton;
import component.CustomItem;
import component.CustomItemList;
import component.RoundedBorder;
import controller.BanHangCTR;
import controller.SanPhamCTR;
import component.CustomButton.CustomButtonIconSide;
import customDataType.DonViTinh;
import customDataType.LoaiHoaDon;
import entity.KhachHang;
import entity.SanPhamYTe;
import testEntity.Thuoc;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;


public class BanHangUI extends JPanel implements ActionListener    { 
	private JTextField txtTimSDT;
	private JTextField txtTenKH;
	private JTextField txtDTL;
	private CustomButton btnThemKH;
	private static JTextField txtTongTienHD;
	private static JTextField txtGiamGia;
	private JTextField txtKhachDua;
	private JTextField txtTienThua;
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
	private JComboBox<LoaiHoaDon>  comboBoxLoaiHD;
	private JTextField txtSDT;
	private int count = 0;
	private JPanel panelBanHang;

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
		txtTenKH.setEnabled(false);
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
		txtCCCD.setEnabled(false);
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
		txtSDT.setEnabled(false);
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
		txtKhachDua.setEnabled(false);
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
		lblTenNV.setText("Hứa Lập Quốc"); // ***** Hoặc giá trị thực tế của nhân viên
		
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
				new int[]{30, 400, 150, 150, 150, 200, 100}, 
				Color.LIGHT_GRAY,  50, 
				new String[]{"","Tên sản phẩm", "Đơn vị tính", "Số lượng", "Giá Bán", "Tổng tiền", ""}, 
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
        
        btnGiamGia.addActionListener(this);
        btnLamMoi.addActionListener(this);
        btnQuetMa.addActionListener(this);
        btnTaoHD.addActionListener(this);
        btnThemKH.addActionListener(this);
        btnThemSP.addActionListener(this);
        
        comboBoxChietKhau.addActionListener(this);
        comboBoxLoaiHD.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		KhachHang kh; 
		SanPhamYTe sp;
		String thongTinCanTim = "";
		String sdt;
		
		// tìm khách hàng
		if (o.equals(txtTimSDT)) {
			thongTinCanTim = txtTimSDT.getText().trim();
			kh = bh_ctr.timKHTheoSDT(thongTinCanTim);
			
			if (kh == null) {
				thongBaoLoi(txtTimSDT, "Khách hàng không tồn tại");
				return;
			}
			else {
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
				for (CustomItem item : banHangList.getItemList()) {
			        if (item instanceof BanHangRow) {
			            BanHangRow row = (BanHangRow) item;
			            Thuoc sanPham = row.getSanPhamYTe(); // Lấy đối tượng Thuoc từ BanHangRow

			            if (sanPham.maThuoc.equalsIgnoreCase(sp.getMaSanPham())) {
			                sanPham.soLuong += 1; // Cập nhật số lượng

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
	            banHangList.addItem(new BanHangRow(stt, new Thuoc(sp.getMaSanPham(), sp.getTenSanPham(), sp.getDonViTinh1(), 1, sp.getGiaBanDonViTinh1()))); // 1: số lượng mặc định khi chọn
				
				tinhChietKhau(sdt);
			}
			
			txtTimSP.setText("");
		}
		

		// thay đổi lựa chọn chiết khấu
		if (o.equals(comboBoxChietKhau)) {
			tinhCacLoaiTienCuaHD();
		}
		
		// làm mới
		if (o.equals(btnLamMoi))
			lamMoi();
	}
	
	private void tinhChietKhau(String sdt) {
		ArrayList<Integer> dsCK = bh_ctr.tinhTienChietKhau(sdt);
		
		if (banHangList.getItemList().size() == 0) {
			comboBoxChietKhau.addItem(0);
		}
		else {
			comboBoxChietKhau.removeAllItems();
			for (Integer ck : dsCK)
				comboBoxChietKhau.addItem(ck);
		}
		
		tinhCacLoaiTienCuaHD();

	}
	
	public static void tinhCacLoaiTienCuaHD() {
		BigDecimal tienChietKhau = BigDecimal.ZERO;
		
		BigDecimal tongTienHD = bh_ctr.tinhTongTienHoaDon(banHangList);
		txtTongTienHD.setText(formatDecimal(tongTienHD));
		
		BigDecimal tienKhuyenMai = bh_ctr.tinhKhuyenMai(tongTienHD);
		txtGiamGia.setText(formatDecimal(tienKhuyenMai));
		
		Object selectedValue = comboBoxChietKhau.getSelectedItem();
		if (selectedValue != null) {
			tienChietKhau = new BigDecimal(selectedValue.toString());
		}
		
		BigDecimal tienKhachPhaiTra = bh_ctr.tinhTienKhachPhaiTra(tongTienHD, tienKhuyenMai, tienChietKhau);
		txtKhachPhaiTra.setText(formatDecimal(tienKhachPhaiTra));
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
	
//	public void kiemTraCapNhat(CustomItemList list) {
//		ArrayList<CustomItem> newItems = new ArrayList<CustomItem>();
//		Random rand = new Random();
//		int n = rand.nextInt();
//		for(int i = 0; i < n%20; i++) {
//			newItems.add(new BanHangRow(i,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop, 12)));
//		}
//		list.updateList(newItems);
//	}
	
	private void xoaTrangTTKH() {
		txtTenKH.setText("");
		txtSDT.setText("");
		txtDTL.setText("");
		txtCCCD.setText("");
		comboBoxChietKhau.removeAllItems();
		chckbxKhachLe.setSelected(false);
	}
	
	private void lamMoi() {
		xoaTrangTTKH();
		txtTongTienHD.setText("");
		txtGiamGia.setText("");
		txtKhachPhaiTra.setText("");
		txtKhachDua.setText("");
		txtTienThua.setText("");
		comboBoxLoaiHD.setSelectedIndex(0);
		banHangList.getItemList().clear();
	}
	

	public static class BanHangRow extends CustomItem implements ActionListener{
		private static int prefWidth = 1250;
		private static int prefHeight = 100;
		private static Font font = UIStyles.DefaultFont;
		private static Color backgroundColor = Color.red;
		private static Border border = BorderFactory.createEmptyBorder();
		private BanHangCTR bh_ctr = new BanHangCTR();
	
		private static int[] cellsWidth = new int[] {30, 400, 150, 150, 150, 200, 100};

		private JComponent[] cells;

		public Thuoc thuoc;	
		private CustomButton btnTang;
		private JTextField txtSLBan;
		private CustomButton btnGiam;
		private JLabel lblDVT;
		private JLabel lblTenSP;
		private JLabel lblGiaBan;
		private JLabel lblTongTienSP;
		private CustomButton btnXoa;
		private JLabel lblSTT;

		
		public BanHangRow(int stt, Thuoc thuoc) {
			super(prefWidth, prefHeight, backgroundColor, border, cellsWidth);

			this.thuoc = thuoc;
			
		//Thiết kế các cell
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
		    cell3.setLayout(new BorderLayout());
		    
		    lblDVT = new JLabel(thuoc.donViTinh.toString());
		    lblDVT.setFont(new Font(lblDVT.getFont().getName(), Font.PLAIN, 20));
		    lblDVT.setHorizontalAlignment(SwingConstants.CENTER); // canh theo chiều ngang
		    cell3.add(lblDVT, BorderLayout.CENTER); // thêm vào giữa
		    
		    // cột 4: số lượng
		    JPanel cell4 = new JPanel();
		    cell4.setBackground(Color.WHITE);
		    cell4.setLayout(new BoxLayout(cell4, BoxLayout.X_AXIS));

		    btnGiam = new CustomButton("-", UIStyles.LabelBarButtonStyle, null, CustomButtonIconSide.LEFT, ()->quayLai());
		    btnGiam.setPreferredSize(new Dimension(30, btnGiam.getPreferredSize().height));
		   
		    txtSLBan = new JTextField();
		    txtSLBan.setText(thuoc.soLuong + "");
		    txtSLBan.setFont(new Font("Arial", Font.PLAIN, 20));
		    
		    btnTang = new CustomButton("+", UIStyles.LabelBarButtonStyle, null, CustomButtonIconSide.LEFT, ()->quayLai());
		    btnTang.setPreferredSize(new Dimension(30, btnTang.getPreferredSize().height));
		  
		    cell4.add(btnGiam);
		    cell4.add(txtSLBan);
		    cell4.add(btnTang);
		    
		    // cột 5: giá bán
		    JPanel cell5 = new JPanel();
		    cell5.setBackground(Color.WHITE);
		    cell5.setLayout(new BorderLayout());
		    
		    BigDecimal giaBan = new BigDecimal(thuoc.giaBan.toString());
		    lblGiaBan = new JLabel(formatDecimal(giaBan));
		    lblGiaBan.setFont(new Font(lblGiaBan.getFont().getName(), Font.PLAIN, 20));
		    lblGiaBan.setHorizontalAlignment(SwingConstants.CENTER); 
		    cell5.add(lblGiaBan, BorderLayout.CENTER);
		    
		    // cột 6: tổng tiền
		    JPanel cell6 = new JPanel();
		    cell6.setBackground(Color.WHITE);
		    cell6.setLayout(new BorderLayout());
		    
		    BigDecimal tongTien = bh_ctr.tinhTongTienTungSP(giaBan, txtSLBan.getText().trim());
		    lblTongTienSP = new JLabel(formatDecimal(tongTien));
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
		    
		    btnGiam.addActionListener(this);
		    btnTang.addActionListener(this);
		    btnXoa.addActionListener(this);
		}
		
		public void capNhatSoLuong(int thayDoi) {
			thuoc.soLuong += thayDoi;
			txtSLBan.setText("" + thuoc.soLuong);
		}
		public void layThongTin() {
			System.out.println(this.thuoc);
		}
		
		public void setSanPhamYTe(Thuoc sanPham) {
	        this.thuoc = sanPham;  // Cập nhật đối tượng Thuoc
	        updateRowUI();
	    }
		
		public Thuoc getSanPhamYTe() { 
	        return thuoc;
	    }
		
		private void updateRowUI() {
	        txtSLBan.setText(String.valueOf(thuoc.soLuong));
	        BigDecimal tongTien = bh_ctr.tinhTongTienTungSP(thuoc.giaBan, String.valueOf(thuoc.soLuong));
		    lblTongTienSP.setText(formatDecimal(tongTien));
	    }
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			
			int thayDoi = 0;
			
			if (o.equals(btnTang)) {
		        thayDoi = 1; 
		    } else if (o.equals(btnGiam)) {
		        thayDoi = -1; 
		    }

		    if (thayDoi != 0) {
		        capNhatSoLuong(thayDoi); 
		        BigDecimal giaBan = new BigDecimal(thuoc.giaBan.toString());
		        BigDecimal tongTien = bh_ctr.tinhTongTienTungSP(giaBan, String.valueOf(thuoc.soLuong));
		        lblTongTienSP.setText(formatDecimal(tongTien));
		        tinhCacLoaiTienCuaHD();
		    }
		    
		    if (o.equals(btnXoa)) {
		    	
		    }
		    
		}
	}
	
	public static String formatDecimal(BigDecimal number) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator('.'); // Dấu phân cách hàng nghìn
		symbols.setDecimalSeparator(',');   // Dấu thập phân

		// Sử dụng mẫu định dạng với 'đ' ở cuối
		DecimalFormat decimalFormat = new DecimalFormat("#,###.### 'đ'", symbols);
	        return decimalFormat.format(number);
	}
	
	private static void quayLai() {}
}


