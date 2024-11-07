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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import component.CustomButton;
import component.CustomPanel;
import component.CustomTable;
import controller.ChiTietHoaDonCTR;
import controller.HoaDonCTR;
import controller.SanPhamCTR;
import customDataType.DonViTinh;
import dao.ChiTietDonDoiTraDAO;
import dao.DonDoiTraDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.KhuyenMaiDAO;
import dao.LoHangDAO;
import dao.SanPhamYTeDAO;
import entity.ChiTietDonDoiTra;
import entity.ChiTietHoaDon;
import entity.DonDoiTra;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.LoHang;
import entity.NhanVien;
import entity.SanPhamYTe;
import component.CustomButton.CustomButtonIconSide;
import connectDB.ConnectDB;

public class DoiTraUI extends JPanel{
	
	
	


	private JTextField txtTenKH;
	private JTextField txtMaNhanVien;
	private JTextField txtSDT;
	private CustomButton btnLamMoi;
	private JLabel lblTenNV;
	private JLabel lblNgayLap;
	private JTextField txtTongGiaGoc;
	private JTextField txtmaHoaDon;
	public JTextField txtPhiTraHang;
	private CustomButton btnTraHang;
	private JButton btnQuetMa;
	private CustomButton btnThemThuoc;
	private JTextField txtTimTheoMaHoaDon;
	private JTextField txtGhiChu;
	private JButton btnQuetMaSP;
	private JTextField txtTimTheoMaSP;
	private CustomButton btnTimHD;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	private CustomTable tableTraHang;
	private ChiTietHoaDonCTR chiTietHoaDonCTR;
	private CustomTable tableDoiHang;
	private DonDoiTraDAO donDoiTraDAO;
	private BigDecimal tienTraKhach;
	
	private String lastDate = "";
	private Map<String, Integer> soThuTuMap = new HashMap<>();
	private LoHangDAO LoHangDAO;
	private ChiTietDonDoiTraDAO chiTietDoiTraDAO;
	private CustomTable tableHoaDon;
	private HoaDonCTR hoaDonCTR;
	private JTextField txtTienTraHang;
	private JTextField txtTongTienHang;
	private JTextField txtTongTienMua;
	private JTextField txtTongHoaDon;
	private JTextField txtKhachTra;
	private JTextField txtGiamGia;
	private JLabel lblTienKhachTra;
	private JLabel lblTienTraKhach;
	private JPanel panelTong;

	

	public DoiTraUI() {
		super();
		try {
		 	ConnectDB.getInstance().connect();
		 	System.out.println("Thành công");
		 }catch (Exception e) {
		 	e.printStackTrace();
		 }	
		donDoiTraDAO = new DonDoiTraDAO();
		chiTietHoaDonCTR = new ChiTietHoaDonCTR();
		LoHangDAO = new LoHangDAO();
		chiTietDoiTraDAO = new ChiTietDonDoiTraDAO();
		hoaDonCTR = new HoaDonCTR();
		taoHinh();
		layThoiGianHienTai();
	}
	
	public void taoHinh() {
		setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
		setLayout(null);
		setBackground(Color.WHITE);
		
		panelTong = new JPanel();
		panelTong.setBackground(UIStyles.BackgroundColor);
		panelTong.setBounds(0, 0, UIStyles.ApplicationWidth, UIStyles.MainSectionHeight);
		add(panelTong);
		panelTong.setLayout(null);
		
		// tiêu đề
		JLabel lblNewLabel_2 = new JLabel("Đổi Trả");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_2.setBounds(700, 10, 164, 49);
		panelTong.add(lblNewLabel_2);
		
		// phần thông tin
				CustomPanel panelThongTin = new CustomPanel(20, 0);
				panelThongTin.setBackground(Color.WHITE);
				panelThongTin.setBounds(1408, 10, 500, 820);
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
				lblNgayLap.setBounds(230, 10, 261, 30); 
				panelThongTin.add(lblNgayLap);
				lblNgayLap.setHorizontalAlignment(SwingConstants.RIGHT);
				
				

				
		// phần khách hàng
				JPanel panelKhachHang = new JPanel();
				panelKhachHang.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), 
					    "Thông tin khách hàng", TitledBorder.LEFT, TitledBorder.TOP, UIStyles.DefaultFont, Color.BLACK));
				panelKhachHang.setBackground(Color.WHITE);
				panelKhachHang.setBounds(32, 50, 450, 150);
				panelThongTin.add(panelKhachHang);
				panelKhachHang.setLayout(null);
				
				
				JLabel lblNewLabel = new JLabel("Tên KH:");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblNewLabel.setBounds(42, 50, 86, 25);
				panelKhachHang.add(lblNewLabel);
				
				txtTenKH = new JTextField();
				txtTenKH.setEnabled(false);
				txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtTenKH.setBounds(177, 45, 250, 30);
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
				txtSDT.setBounds(177, 95, 250, 30);
				txtSDT.setBorder(new LineBorder(Color.BLACK, 1)); 
				panelKhachHang.add(txtSDT);
	
				
				
				
		// phần hóa đơn
				JPanel panelHoaDon = new JPanel();
				panelHoaDon.setBackground(Color.WHITE);
				panelHoaDon.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), 
					    "Thông tin hóa đơn", TitledBorder.LEFT, TitledBorder.TOP, UIStyles.DefaultFont, Color.BLACK));
				panelHoaDon.setBounds(32, 210, 450, 600);
				panelThongTin.add(panelHoaDon);
				panelHoaDon.setLayout(null);
				
				// trả hàng
				JLabel lblTraHang = new JLabel("Trả Hàng");
				lblTraHang.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblTraHang.setForeground(Color.GREEN); 
				lblTraHang.setBounds(200, 20, 125, 25);
				panelHoaDon.add(lblTraHang);
				// mã hóa đơn
				JLabel lblmaHD = new JLabel("Mã hóa đơn:");
				lblmaHD.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblmaHD.setBounds(42, 70, 125, 25);
				panelHoaDon.add(lblmaHD);
				
				txtmaHoaDon = new JTextField();
				txtmaHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtmaHoaDon.setEnabled(false);
				txtmaHoaDon.setColumns(10);
				txtmaHoaDon.setBounds(187, 65, 250, 30);
				txtmaHoaDon.setBorder(new LineBorder(Color.BLACK, 1)); 
				panelHoaDon.add(txtmaHoaDon);
				
				
				
				// tổng giá gốc
				
				JLabel lblTngGi = new JLabel("Tổng giá gốc:");
				lblTngGi.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblTngGi.setBounds(42, 120, 140, 25);
				panelHoaDon.add(lblTngGi);
				
				txtTongGiaGoc = new JTextField();
				txtTongGiaGoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtTongGiaGoc.setEnabled(false);
				txtTongGiaGoc.setColumns(10);
				txtTongGiaGoc.setBounds(187, 115, 250, 30);
				txtTongGiaGoc.setBorder(new LineBorder(Color.BLACK, 1)); 
				panelHoaDon.add(txtTongGiaGoc);
				
				
				//phí trả hàng
				JLabel lblPhiTraHang = new JLabel("Phí trả hàng:");
				lblPhiTraHang.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblPhiTraHang.setBounds(42, 170, 140, 25);
				panelHoaDon.add(lblPhiTraHang);
				
				txtPhiTraHang = new JTextField();
				txtPhiTraHang.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtPhiTraHang.setEnabled(false);
				txtPhiTraHang.setColumns(10);
				txtPhiTraHang.setBounds(187, 165, 250, 30);
				txtPhiTraHang.setBorder(new LineBorder(Color.BLACK, 1)); 
				panelHoaDon.add(txtPhiTraHang);
				
				// tổng tiền trả
				JLabel lblGiaTra = new JLabel("Tổng tiền trả:");
				lblGiaTra.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblGiaTra.setBounds(42, 220, 136, 25);
				panelHoaDon.add(lblGiaTra);
				
				
				txtTienTraHang = new JTextField();
				txtTienTraHang.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtTienTraHang.setEnabled(false);
				txtTienTraHang.setColumns(10);
				txtTienTraHang.setBounds(187, 215, 250, 30);
				txtTienTraHang.setBorder(new LineBorder(Color.BLACK, 1)); 
				panelHoaDon.add(txtTienTraHang);
				
				
				// Mua hàng
				JLabel lblMuaHang = new JLabel("Mua Hàng");
				lblMuaHang.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblMuaHang.setForeground(Color.GREEN); 
				lblMuaHang.setBounds(200, 250, 125, 25);
				panelHoaDon.add(lblMuaHang);
							
				//Tổng tiền hàng
				JLabel lblTienHang = new JLabel("Tổng tiền hàng:");
				lblTienHang.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblTienHang.setBounds(42, 290, 160, 25);
				panelHoaDon.add(lblTienHang);
				
				txtTongTienHang = new JTextField();
				txtTongTienHang.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtTongTienHang.setEnabled(false);
				txtTongTienHang.setColumns(10);
				txtTongTienHang.setBounds(187, 285, 250, 30);
				txtTongTienHang.setBorder(new LineBorder(Color.BLACK, 1)); 
				panelHoaDon.add(txtTongTienHang);
				
				//giảm giá
				JLabel lblGiamGia = new JLabel("Giảm giá:");
				lblGiamGia.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblGiamGia.setBounds(42, 340, 136, 25);
				panelHoaDon.add(lblGiamGia);
				
				txtGiamGia = new JTextField();
				txtGiamGia.setBackground(Color.WHITE);
				txtGiamGia.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtGiamGia.setEnabled(false);
				txtGiamGia.setColumns(10);
				txtGiamGia.setBounds(187, 335, 250, 30);
				panelHoaDon.add(txtGiamGia);
				
				// tổng tiền mua
				
				JLabel lblTienMua = new JLabel("Tổng tiền mua:");
				lblTienMua.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblTienMua.setBounds(42, 390, 140, 25);
				panelHoaDon.add(lblTienMua);
				
				txtTongTienMua = new JTextField();
				txtTongTienMua.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtTongTienMua.setEnabled(false);
				txtTongTienMua.setColumns(10);
				txtTongTienMua.setBounds(187, 385, 250, 30);
				txtTongTienMua.setBorder(new LineBorder(Color.BLACK, 1)); 
				panelHoaDon.add(txtTongTienMua);
				
				// tiền trả khách
				lblTienTraKhach = new JLabel("Tiền trả khách:");
				lblTienTraKhach.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblTienTraKhach.setBounds(42, 440, 140, 25);
				panelHoaDon.add(lblTienTraKhach);
				
				txtTongHoaDon = new JTextField();
				txtTongHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtTongHoaDon.setEnabled(false);
				txtTongHoaDon.setColumns(10);
				txtTongHoaDon.setBounds(187, 435, 250, 30);
				txtTongHoaDon.setBorder(new LineBorder(Color.BLACK, 1)); 
				panelHoaDon.add(txtTongHoaDon);
				
				// khách trả
				
				lblTienKhachTra = new JLabel("Tiền khách trả:");
				lblTienKhachTra.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblTienKhachTra.setBounds(42, 490, 140, 25);
				panelHoaDon.add(lblTienKhachTra);
				
				txtKhachTra = new JTextField();
				txtKhachTra.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtKhachTra.setEnabled(false);
				txtKhachTra.setColumns(10);
				txtKhachTra.setBounds(187, 485, 250, 30);
				txtKhachTra.setBorder(new LineBorder(Color.BLACK, 1)); 
				panelHoaDon.add(txtKhachTra);
				
				btnTraHang = new CustomButton("Thanh Toán", UIStyles.ThemButtonStyle, null, CustomButtonIconSide.LEFT, () -> thanhToan());
				btnTraHang.setForeground(Color.WHITE);
				btnTraHang.setFont(new Font("Tahoma", Font.BOLD, 20));
				btnTraHang.setBounds(50, 540, 170, 40);
				panelHoaDon.add(btnTraHang);
				
			
				
				btnLamMoi = new CustomButton("Làm mới", UIStyles.NavBarButtonStyle, null, CustomButtonIconSide.LEFT, () -> LamMoi());
				btnLamMoi.setForeground(Color.WHITE);
				btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 20));
				btnLamMoi.setBounds(250, 540, 170, 40);
				panelHoaDon.add(btnLamMoi);
				
		// phần trả hàng
				JPanel panelTraHang = new JPanel();
				panelTraHang.setBounds(350, 50, 1299, 350);
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
				
				btnTimHD = new CustomButton("Tìm", UIStyles.NavBarButtonStyle, null, CustomButtonIconSide.LEFT, () -> timChiTietHoaDonTheoMa(txtTimTheoMaHoaDon.getText()));
				btnTimHD.setFont(new Font("Tahoma", Font.BOLD, 20));
				btnTimHD.setBounds(929, 2, 105, 38);
				btnTimHD.setFocusable(false);
				panelTimKiem.add(btnTimHD);
				// phần bảng thông tin trả hàng
				String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Giá bán", "Đơn vị tính", "số lượng","Tổng Tiền"};
				Object[][] data = new Object[0][columnNames.length];

				
				tableTraHang = new CustomTable(data, columnNames, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle, 20);
		        JScrollPane scrollPaneBang = new JScrollPane(tableTraHang);
		        scrollPaneBang.setPreferredSize(new Dimension(1123, 711)); // thay đổi theo khung chứa
		        scrollPaneBang.setBorder(new LineBorder(Color.GRAY, 1, true));
		        scrollPaneBang.setBounds(10, 72, 1030, 270); // Đặt kích thước và vị trí của scrollPane
		        panelTraHang.add(scrollPaneBang);
		        
		        tableTraHang.addMouseListener(new MouseAdapter() {
		            @Override
		            public void mouseClicked(MouseEvent e) {
		                int row = tableTraHang.rowAtPoint(e.getPoint());
		                int col = tableTraHang.columnAtPoint(e.getPoint());
		                
		                if (col == 0) {
		                	DefaultTableModel model = (DefaultTableModel) tableTraHang.getModel();
		                	 int confirm = JOptionPane.showConfirmDialog(null, 
		                             "Bạn có chắc chắn muốn xóa sản phẩm này?", 
		                             "Xác nhận xóa", 
		                             JOptionPane.YES_NO_OPTION);
		                         
		                         
		                         if (confirm == JOptionPane.YES_OPTION) {
		                             model.removeRow(row); 
		                             capNhatTongHangTra();
		                             capNhatTongGiaTra();
		                             tinhTongHoaDon();
		                             
		                         }
		                	
		                }

		                if (col == 4) {  
		                    DefaultTableModel model = (DefaultTableModel) tableTraHang.getModel();
		                    
		                    int currentQuantity = (int) model.getValueAt(row, 4);
		                    
		                    String inputQuantity = JOptionPane.showInputDialog("Nhập số lượng mới:", currentQuantity);
		                    if (inputQuantity == null || inputQuantity.isEmpty()) {
		                        return; 
		                    }
		                    
		                    try {
		                        int newQuantity = Integer.parseInt(inputQuantity);
		                        String maHoaDon = (String) model.getValueAt(row, 0);
		                        
		                       
		                        ArrayList<ChiTietHoaDon> dsChiTietHoaDon = chiTietHoaDonCTR.timChiTietHoaDonTheoMaHoaDon(txtmaHoaDon.getText());
		                        
		                        
		                        if (dsChiTietHoaDon == null || dsChiTietHoaDon.isEmpty()) {
		                            JOptionPane.showMessageDialog(null, "Không tìm thấy chi tiết hóa đơn cho mã: " + maHoaDon);
		                            return; 
		                        }
//		                        
		                        ChiTietHoaDon chiTietHoaDon = dsChiTietHoaDon.get(0); 
		                        int maxQuantity = chiTietHoaDon.getSoLuong(); 
		                        
		                        if (newQuantity > maxQuantity) {
		                           
		                            newQuantity = maxQuantity; 
		                        } else if (newQuantity <= 0) {
		                        	newQuantity = 0;
		                            return; 
		                        }

		                        model.setValueAt(newQuantity, row, 4); 
		                        BigDecimal giaBan = (BigDecimal) model.getValueAt(row, 2);
		                        BigDecimal newTongTien = giaBan.multiply(BigDecimal.valueOf(newQuantity));
		                        model.setValueAt(newTongTien, row, 5);
		                        capNhatTongGiaTra();
		                        capNhatTongHangTra();
		                        tinhTongHoaDon();

		                    } catch (NumberFormatException ex) {
		                        
		                    }
		                }
		            }
		        });




		     
		        
		   // panel đổi hàng
		        JPanel panelDoiHang = new JPanel();
		        panelDoiHang.setBounds(350, 400, 1299, 350);
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
			
				btnThemThuoc = new CustomButton("Thêm", UIStyles.NavBarButtonStyle, null, CustomButtonIconSide.LEFT, () -> themSanPhamVaoChiTietDoiTra(txtTimTheoMaSP.getText()));
				btnThemThuoc.setFont(new Font("Tahoma", Font.BOLD, 20));
				btnThemThuoc.setBounds(929, 2, 105, 38);
				btnThemThuoc.setFocusable(false);
				panelTimKiemSP.add(btnThemThuoc);
				
				// phần bảng thông tin đổi hàng
				String[] headers = {"Mã sản phẩm", "Tên sản phẩm", "Giá bán", "Đơn vị tính", "số lượng","Tổng Tiền"};
				Object[][] dataDoiHang = new Object[0][columnNames.length];
				
				tableDoiHang = new CustomTable(dataDoiHang, headers, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle, 20);
		        JScrollPane scrollPaneBangDoi = new JScrollPane(tableDoiHang);
		        scrollPaneBangDoi.setPreferredSize(new Dimension(1123, 711)); 
		        scrollPaneBangDoi.setBorder(new LineBorder(Color.GRAY, 1, true));
		        scrollPaneBangDoi.setBounds(10, 72, 1030, 270); 
		        panelDoiHang.add(scrollPaneBangDoi);
		        
		        tableDoiHang.addMouseListener(new MouseAdapter() {
		            @Override
		            public void mouseClicked(MouseEvent e) {
		                int row = tableDoiHang.rowAtPoint(e.getPoint());
		                int column = tableDoiHang.columnAtPoint(e.getPoint());

		                
		                if (column == 4) { 
		                    String input = JOptionPane.showInputDialog("Nhập số lượng mới:");
		                    if (input != null) {
		                        try {
		                            int newQuantity = Integer.parseInt(input); 

		                            DefaultTableModel model = (DefaultTableModel) tableDoiHang.getModel();
		                            //String maSanPham = (String) model.getValueAt(row, 0); 

		                           if(newQuantity <= 0) {
		                        	 model.removeRow(row);
		                        	   
		                           }else {
		                        	   
		                           
		                            
		                            model.setValueAt(newQuantity, row, column); 

		                            
		                            BigDecimal giaBan = (BigDecimal) model.getValueAt(row, 2);
		                            BigDecimal tongTien = giaBan.multiply(BigDecimal.valueOf(newQuantity)); 
		                            model.setValueAt(tongTien, row, 5); 
		                           
		                            }
		                           capNhatTongTienMuaHang();
		                           tinhTongHoaDon();

		                        } catch (NumberFormatException ex) {
		                            JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ, vui lòng nhập lại.");
		                        }
		                    }
		                }
		            }
		        });


		    
		        // ghi chú
		        JPanel panelghiChu = new JPanel();
		        panelghiChu.setBounds(20, 760, 1290, 47);
		        panelTong.add(panelghiChu);
		        panelghiChu.setLayout(null);
		        
		        txtGhiChu = new JTextField();
		        txtGhiChu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		        txtGhiChu.setBounds(0, 0, 1290, 47);
		        panelghiChu.add(txtGhiChu);
		        txtGhiChu.setColumns(10);
		        UIStyles.setPlaceholder(txtGhiChu, "Ghi chú đơn thuốc");
			
		        // panel danh sách hóa đơn
		        JPanel panelDSHoaDon = new JPanel();
		        panelDSHoaDon.setBounds(0, 0, 470, 800);
				panelTong.add(panelDSHoaDon);
				panelDSHoaDon.setLayout(null);
				panelDSHoaDon.setBackground(UIStyles.BackgroundColor);
				
		
				JLabel dsHoaDon = new JLabel("Danh sách hóa đơn");
				 dsHoaDon.setFont(new Font("Tahoma", Font.BOLD, 20));
				 dsHoaDon.setBounds(50, 40, 200, 49);
				 panelDSHoaDon.add(dsHoaDon);
				 
				 // table danh sách hóa đon
				 
				 String[] header = {"Mã HĐ", "Thành tiền"};
					Object[][] dataHoaDon = new Object[0][columnNames.length];
					
					tableHoaDon = new CustomTable(dataHoaDon, header, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle, 20);
			        JScrollPane scrollPaneHoaDon = new JScrollPane(tableHoaDon);
			        scrollPaneHoaDon.setPreferredSize(new Dimension(1123, 711)); // thay đổi theo khung chứa
			        scrollPaneHoaDon.setBorder(new LineBorder(Color.GRAY, 1, true));
			        scrollPaneHoaDon.setBounds(5, 100, 340, 640); // Đặt kích thước và vị trí của scrollPane
			        panelDSHoaDon.add(scrollPaneHoaDon);
			        layDanhSachHoaDon();
			        
			        tableHoaDon.addMouseListener(new MouseAdapter() {
			            @Override
			            public void mouseClicked(MouseEvent e) {
			            	
			                int row = tableHoaDon.rowAtPoint(e.getPoint());
			                if (row >= 0) { 
			                    String maHoaDon = (String) tableHoaDon.getValueAt(row, 0); 
			                   
			                    	 timChiTietHoaDonTheoMa(maHoaDon);
			                    
			                   
			                }
			            }
			        });
	    
	}
	
	// lấy danh sách hóa đơn
	public void layDanhSachHoaDon() {
		ArrayList<HoaDon> dsHoaDon = hoaDonCTR.layDanhSachHoaDon();
		Object[][] data = new Object[dsHoaDon.size()][2]; 
		for (int i = 0; i < dsHoaDon.size(); i++) {
		    HoaDon hd = dsHoaDon.get(i);
		    data[i][0] = hd.getMaHoaDon();
		    data[i][1] = hd.getThanhTien();
		}
		
		tableHoaDon.capNhatDuLieu(data);
		
		
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

	
	// tìm hóa đơn cần đổi trả
	private void timChiTietHoaDonTheoMa(String maHD) {
	    ArrayList<ChiTietHoaDon> dsChiTietHoaDon = chiTietHoaDonCTR.timChiTietHoaDonTheoMaHoaDon(maHD);
	    String mahoaDon = txtmaHoaDon.getText();
	    if(mahoaDon != null && !mahoaDon.isEmpty()) {
	    	JOptionPane.showMessageDialog(this,"đã có hóa đơn đang thực hiện đổi trả");
	    	return;
	    }
	    
	    if (dsChiTietHoaDon != null && !dsChiTietHoaDon.isEmpty()) {
	        LayThongTinKhachHangTheoHoadon(maHD);
	        
	        Object[][] currentData = tableTraHang.getData();
	        for (ChiTietHoaDon chiTietHoaDon : dsChiTietHoaDon) {
	            SanPhamYTe spYTe = chiTietHoaDon.getSanPhamYTe();
	            boolean exists = false;
	            int maxQuantity = chiTietHoaDon.getSoLuong(); 

	            for (int i = 0; i < currentData.length; i++) {
	                if (currentData[i][0].equals(spYTe.getMaSanPham())) {
	                    int currentQuantity = (int) currentData[i][4]; 
	                    
	                   
	                    if (currentQuantity + 1 > maxQuantity) {
	                        currentData[i][4] = maxQuantity;  
	                    } else {
	                        currentData[i][4] = currentQuantity + 1;  
	                    }
	                    
	               
	                    currentData[i][5] = tinhGiaBan(spYTe, (int) currentData[i][4]);
	                    exists = true;
	                    break;
	                }
	            }

	            if (!exists) {
	                
	                Object[][] newData = new Object[currentData.length + 1][6];
	                System.arraycopy(currentData, 0, newData, 0, currentData.length);

	                newData[currentData.length][0] = spYTe.getMaSanPham();
	                newData[currentData.length][1] = spYTe.getTenSanPham();
	                newData[currentData.length][2] = spYTe.getGiaBan();
	                newData[currentData.length][3] = spYTe.getDonViTinh();
	                newData[currentData.length][4] = Math.min(1, maxQuantity);  
	                newData[currentData.length][5] = tinhGiaBan(spYTe, (int) newData[currentData.length][4]);

	                currentData = newData; 
	            }
	        }

	        tableTraHang.setData(currentData);
	        capNhatTongGiaTra();
	        capNhatTongHangTra();
	        tinhTongHoaDon();
	        txtTimTheoMaHoaDon.setText(null);
	        JOptionPane.showMessageDialog(this, "Thông tin chi tiết hóa đơn đã được tải thành công.");
	    } else {
	        JOptionPane.showMessageDialog(this, "Không tìm thấy chi tiết hóa đơn cho mã: " + maHD);
	    }
	}
	
	// phí trả hàng
	private BigDecimal tinhPhiTraHang(BigDecimal tongTienHangTra) {
	    BigDecimal tyLePhi = new BigDecimal("0.05"); // 5% phí
	    return tongTienHangTra.multiply(tyLePhi);
	}

	// tính tiền trả khách
	private void capNhatTongHangTra() {
	    DefaultTableModel model = (DefaultTableModel) tableTraHang.getModel();
	    BigDecimal tongTienHangTra = BigDecimal.ZERO;

	    for (int i = 0; i < model.getRowCount(); i++) {
	        BigDecimal tongTien = (BigDecimal) model.getValueAt(i, 5);
	        tongTienHangTra = tongTienHangTra.add(tongTien);
	    }

	   
	    BigDecimal phiTraHang = tinhPhiTraHang(tongTienHangTra);
	    
	    txtPhiTraHang.setText(phiTraHang.toString());
	    
	    BigDecimal tongHangTraSauPhi = tongTienHangTra.subtract(phiTraHang);

	    
	    txtTienTraHang.setText(tongHangTraSauPhi.toString());
	}
	
	
	private void capNhatTongGiaTra() {
	    DefaultTableModel model = (DefaultTableModel) tableTraHang.getModel();
	    BigDecimal tongTienHangTra = BigDecimal.ZERO;

	    for (int i = 0; i < model.getRowCount(); i++) {
	        BigDecimal tongTien = (BigDecimal) model.getValueAt(i, 5);
	        tongTienHangTra = tongTienHangTra.add(tongTien);
	    }



	    
	    txtTongGiaGoc.setText(tongTienHangTra.toString());
	}

	
	// thêm sản phẩm mới vào bảng đổi trả
	
	private void themSanPhamVaoChiTietDoiTra(String maSP) {
	    SanPhamYTeDAO spDao = new SanPhamYTeDAO();
	    ArrayList<SanPhamYTe> dsSP = new ArrayList<SanPhamYTe>();
	    dsSP.add(SanPhamCTR.timSanPhamTheoMaSanPham(maSP));
	    
	    if (dsSP != null && !dsSP.isEmpty()) {
	        SanPhamYTe spYTe = dsSP.get(0); 
	        boolean exists = false;

	        Object[][] currentData = tableDoiHang.getData();
	        for (int i = 0; i < currentData.length; i++) {
	            if (currentData[i][0].equals(spYTe.getMaSanPham())) {
	                
	                int currentQuantity = (int) currentData[i][4]; 
	                currentData[i][4] = currentQuantity + 1; 
	                currentData[i][5] = tinhGiaBan(spYTe, currentQuantity + 1); 
	                exists = true;
	                break;
	            }
	        }

	        if (!exists) {
	           
	            Object[][] newData = new Object[currentData.length + 1][6];
	            System.arraycopy(currentData, 0, newData, 0, currentData.length);

	            newData[currentData.length][0] = spYTe.getMaSanPham();
	            newData[currentData.length][1] = spYTe.getTenSanPham();
	            newData[currentData.length][2] = spYTe.getGiaBan();
	            newData[currentData.length][3] = spYTe.getDonViTinh();
	            newData[currentData.length][4] = 1;
	            newData[currentData.length][5] = tinhGiaBan(spYTe, 1); 

	            tableDoiHang.setData(newData);
	        } else {
	            tableDoiHang.setData(currentData); 
	        }
	        capNhatTongTienMuaHang();
	        tinhTongHoaDon();

	        txtTimTheoMaSP.setText(null);
	       
	        
	        JOptionPane.showMessageDialog(this, "Sản phẩm đã được tìm thấy và thêm vào danh sách chi tiết đổi trả.");
	    } else {
	        JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm với mã: " + maSP);
	    }
	}


	// tính tiền tổng tiền
	private BigDecimal tinhGiaBan(SanPhamYTe spYTe, int soLuong) {
	    
	    return spYTe.getGiaBan().multiply(new BigDecimal(soLuong));
	}
	
	
	// phát sinh mã chi tiết đơn đổi trả
	public String phatSinhMaChiTietDoiTra(String maDonDoiTra) {
	    
	    String ddMMYYYYXXXX = maDonDoiTra.substring(2, 14);
	    String prefix = "CTDDT" + ddMMYYYYXXXX;

	    if (!ddMMYYYYXXXX.equals(lastDate)) {
	        soThuTuMap.clear();
	        soThuTuMap.put(ddMMYYYYXXXX, 0);
	        lastDate = ddMMYYYYXXXX;
	    }

	  
	    int stt = soThuTuMap.get(ddMMYYYYXXXX) + 1;
	    soThuTuMap.put(ddMMYYYYXXXX, stt);

	 
	    String maChiTietDoiTra = prefix + String.format("%02d", stt);

	   
	    while (checkIfChiTietKeyExists(maChiTietDoiTra)) {
	        stt++;
	        soThuTuMap.put(ddMMYYYYXXXX, stt);
	        maChiTietDoiTra = prefix + String.format("%02d", stt);
	    }

	    return maChiTietDoiTra;
	}

	
	private boolean checkIfChiTietKeyExists(String maChiTietDoiTra) {
	    ChiTietDonDoiTraDAO chiTietDonDoiTraDAO = new ChiTietDonDoiTraDAO();
	    return chiTietDonDoiTraDAO.checkMaChiTietDoiTraExists(maChiTietDoiTra);
	}

	 
	// phát sinh mã đơn đổi trả
	 public String phatSinhMaDonDoiTra() {
		    Date currentDate = new Date(System.currentTimeMillis());
		    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		    String formattedDate = sdf.format(currentDate);

		    if (!formattedDate.equals(lastDate)) {
		        soThuTuMap.clear();
		        soThuTuMap.put(formattedDate, 0);
		        lastDate = formattedDate;
		    }

		    int stt = soThuTuMap.get(formattedDate) + 1;
		    soThuTuMap.put(formattedDate, stt);

		    String maDonDoiTra = "DDT" + formattedDate + String.format("%04d", stt);
		    
		    
		    while (checkIfKeyExists(maDonDoiTra)) {
		        stt++; 
		        soThuTuMap.put(formattedDate, stt);
		        maDonDoiTra = "DDT" + formattedDate + String.format("%04d", stt);
		    }

		    return maDonDoiTra;
		}

	// Phương thức kiểm tra xem mã đã tồn tại trong cơ sở dữ liệu chưa
		private boolean checkIfKeyExists(String maDonDoiTra) {
		    
		    DonDoiTraDAO donDoiTraDAO = new DonDoiTraDAO();
		    return donDoiTraDAO.checkMaDonDoiTraExists(maDonDoiTra);
		}

	
	// lấy thông tin khách hàng
	private void LayThongTinKhachHangTheoHoadon(String maHD) {
		 HoaDonDAO hdDao = new HoaDonDAO();
		 HoaDon hd = hdDao.layThongTinKhachHangTheoMaHoaDon(maHD);
		 if (hd != null) {
			 txtTenKH.setText(hd.getKhachHang().getHoTen());
			 txtSDT.setText(hd.getKhachHang().getSdt());
			 txtmaHoaDon.setText(hd.getMaHoaDon());
			 txtTienTraHang.setText(String.valueOf(hd.getThanhTien()));
		 } else {
        
        JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin khách hàng cho mã hóa đơn: " + maHD);
    }
		 
	}
	
	
	
	// tổng tiền hàng
	private void capNhatTongTienMuaHang() {
	    BigDecimal tongTienDoiHang = BigDecimal.ZERO;
	    Object[][] currentData = tableDoiHang.getData();

	    for (Object[] row : currentData) {
	        BigDecimal tongTienRow = (BigDecimal) row[5]; 
	        tongTienDoiHang = tongTienDoiHang.add(tongTienRow);
	    }

	   

	    
	   tienTraKhach = tongTienDoiHang;
	   txtTongTienHang.setText(tienTraKhach.toString());
	   
	   // Tìm khuyến mãi và áp dụng chiết khấu
	    KhuyenMai khuyenMaiApDung = layMaKhuyenMai(tienTraKhach);

	    if (khuyenMaiApDung != null) {
	        BigDecimal chietKhau = BigDecimal.valueOf(khuyenMaiApDung.getChietKhau());
	        BigDecimal tongTienSauChietKhau = tienTraKhach.subtract(tienTraKhach.multiply(chietKhau));

	        txtTongTienMua.setText(tongTienSauChietKhau.toString());
	       
	    } else {
	    	 txtTongTienMua.setText(tienTraKhach.toString());
	        
	    }
	   
	}
	
	// lấy khuyến mãi
	private KhuyenMai layMaKhuyenMai(BigDecimal tongTienHang) {
	    KhuyenMaiDAO kmDao = new KhuyenMaiDAO();
	    ArrayList<KhuyenMai> dsKhuyenMai = kmDao.timKhuyenMaiTheoDieuKien1(tongTienHang.toString());

	    if (tongTienHang.compareTo(BigDecimal.ZERO) <= 0) {
	        txtGiamGia.setText(""); 
	        return null; }
	    if (dsKhuyenMai != null && !dsKhuyenMai.isEmpty()) {
	        KhuyenMai khuyenMaiLonNhat = null;
	        for (KhuyenMai km : dsKhuyenMai) {
	            if (khuyenMaiLonNhat == null || km.getChietKhau() > khuyenMaiLonNhat.getChietKhau()) {
	                khuyenMaiLonNhat = km;
	            }
	        }

	        if (khuyenMaiLonNhat != null) {
	            txtGiamGia.setText(khuyenMaiLonNhat.getMaKhuyenMai());
	        }
	        return khuyenMaiLonNhat;
	    } else {
	        return null;
	    }
	}
	
	// 

	
	private void LamMoi() {
	  
	    DefaultTableModel modelDoiHang = (DefaultTableModel) tableDoiHang.getModel();
	    modelDoiHang.setRowCount(0); 
	    DefaultTableModel modelTraHang = (DefaultTableModel) tableTraHang.getModel();
	    modelTraHang.setRowCount(0); 
	   txtTenKH.setText("");
	   txtSDT.setText("");
	   txtmaHoaDon.setText("");
	   txtTongGiaGoc.setText("");;
	   txtPhiTraHang.setText("");

	   txtTongTienHang.setText("");
	   txtGiamGia.setText("");
	   txtTongTienMua.setText("");	  
	   txtTongHoaDon.setText("");
	   txtKhachTra.setText("");
	   txtGhiChu.setText("");
	   txtTienTraHang.setText("");
	    JOptionPane.showMessageDialog(null, "Đã làm mới dữ liệu.");
		
	}

	

	
	
	// tạo đơn đổi trả

		private DonDoiTra taoDonDoiTra() {
		    String maDonDoiTra = phatSinhMaDonDoiTra(); 
		    Date ngayDoiTra = new Date(System.currentTimeMillis()); 
		    BigDecimal tienHoan = new BigDecimal(txtTongHoaDon.getText()); 
		    
		    NhanVien nv = getSelectedNhanVien(); 
		    KhuyenMai km = getSelectedKhuyenMai();
		    KhachHang kh = getSelectedKhachHang(txtmaHoaDon.getText()); 
		    HoaDon hd = getSelectedHoaDon(); 

		    
		    if (nv == null || kh == null || hd == null) {
		        JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên, khách hàng, và hóa đơn hợp lệ.");
		         
		    }

		  
		    DonDoiTra donDoiTra = new DonDoiTra(maDonDoiTra, ngayDoiTra, tienHoan, nv, km, kh, hd);

		   return donDoiTra;
		}


		private NhanVien getSelectedNhanVien() {
			
		    return new NhanVien("NV000001");
		}

		private KhuyenMai getSelectedKhuyenMai() {
		    
		    return new KhuyenMai(txtGiamGia.getText()); 
		}

		private KhachHang getSelectedKhachHang(String maHD) {
			 HoaDonDAO hdDao = new HoaDonDAO();
			 String makh = hdDao.getMaKhachHangByMaHoaDon(maHD);
			 KhachHang kh = new KhachHang(makh);
			 
		    return kh;
		}

		private HoaDon getSelectedHoaDon() {
		    return new HoaDon(txtmaHoaDon.getText()); 
		}

	

		
		private void ThemChiTietDonDoiTra(String maDonDoiTra) {
			System.out.println("Bắt đầu thêm chi tiết đơn đổi trả...");
			
		    Object[][] dataDoiHang = tableDoiHang.getData();
		    Object[][] dataTraHang = tableTraHang.getData();
		    
		    System.out.println("dữ liệu Đổi hàng" + dataDoiHang.length);
		    System.out.println("dữ liệu Trả hàng" + dataTraHang.length);

		    // Thêm chi tiết cho bảng đổi hàng
		    for (Object[] row : dataDoiHang) {
		        if (row == null || row.length == 0) {
		            System.out.println("Dữ liệu dòng đổi hàng rỗng hoặc null.");
		            continue;
		        }
		        
		        String maChitietDoiTra = phatSinhMaChiTietDoiTra(phatSinhMaDonDoiTra());
		        String maSanPham = (String) row[0];
		        SanPhamYTe maSP = new SanPhamYTe(maSanPham);
		        int soLuong = (int) row[4];
		        BigDecimal tongTien = (BigDecimal) row[5];
		       
		       // DonDoiTra maDonDoiTra = getSelectmaDonDoiTra();
		        
		        String maLo = LoHangDAO.maLoTheoSanPham(maSanPham);
		        LoHang maLoHang = new LoHang(maLo);
		        
		        String maLoThayThe = null;
		        LoHang maLohayThe = new LoHang(maLoThayThe);
		    
	
		       
		        
		        ChiTietDonDoiTra chiTietDoiTra = new ChiTietDonDoiTra(maChitietDoiTra, soLuong, tongTien,new DonDoiTra(maDonDoiTra), maSP, maLoHang, maLohayThe);
		      

		      
		        
		       

		        // Gọi DAO để thêm vào cơ sở dữ liệu
		        boolean successDoiHang = chiTietDoiTraDAO.themChiTietDoiTra(chiTietDoiTra);

		        if (successDoiHang) {
		            System.out.println("Đã thêm chi tiết đơn đổi hàng cho sản phẩm: " + maSanPham);
		           
		        } else {
		            System.out.println("Không thể thêm chi tiết đơn đổi hàng cho sản phẩm: " + maSanPham);
		            
		        }
		    }

		    // Thêm chi tiết cho bảng trả hàng
		    for (Object[] row : dataTraHang) {
		        if (row == null || row.length == 0) {
		            System.out.println("Dữ liệu dòng trả hàng rỗng hoặc null.");
		            continue;
		        }

		        String maChitietDoiTra = phatSinhMaChiTietDoiTra(phatSinhMaDonDoiTra());
		        String maSanPham = (String) row[0];
		        SanPhamYTe maSP = new SanPhamYTe(maSanPham);
		        int soLuong = (int) row[4];
		        BigDecimal tongTien = (BigDecimal) row[5];
		        //String maDonDoiTra = phatSinhMaDonDoiTra();
		        //DonDoiTra maDoiTra = new DonDoiTra(maDonDoiTra);
		        
		        String maLo = LoHangDAO.maLoTheoSanPham(maSanPham);
		        LoHang maLoHang = new LoHang(maLo);
		        
		        String maLoThayThe = null;
		        LoHang maLohayThe = new LoHang(maLoThayThe);
		        
		     
		        
		        ChiTietDonDoiTra chiTietDoiTra = new ChiTietDonDoiTra(maChitietDoiTra, soLuong, tongTien, new DonDoiTra(maDonDoiTra), maSP, maLoHang, maLohayThe);
		      
		      
		        
		       
		        // Gọi DAO để thêm vào cơ sở dữ liệu
		        boolean successTraHang = chiTietDoiTraDAO.themChiTietDoiTra(chiTietDoiTra);

		        if (successTraHang) {
		            System.out.println("Đã thêm chi tiết đơn trả hàng cho sản phẩm: " + maSanPham);
		           
		            
		        } else {
		            System.out.println("Không thể thêm chi tiết đơn trả hàng cho sản phẩm: " + maSanPham);
		        }
		    }
		}

//		private DonDoiTra getSelectmaDonDoiTra() {
//			String maDoiTra = phatSinhMaDonDoiTra();
//			return new DonDoiTra(maDoiTra);
//		}
		
		// tính tổng tiền cảu đơn đổi trả
		// Hàm tính tổng hóa đơn
		private void tinhTongHoaDon() {
		   
		    String tienTraHangText = txtTienTraHang.getText();
		    String tongTienMuaText = txtTongTienMua.getText();

		    BigDecimal tongTienTraKhach;
		    BigDecimal tongTienMuaHang;

		   
		    if (tienTraHangText.isEmpty()) {
		        tongTienTraKhach = BigDecimal.ZERO;
		    } else {
		        try {
		            tongTienTraKhach = new BigDecimal(tienTraHangText);
		        } catch (NumberFormatException e) {
		            JOptionPane.showMessageDialog(this, "Giá trị tiền trả hàng không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return; 
		        }
		    }

		    if (tongTienMuaText.isEmpty()) {
		    
		        tongTienMuaHang = BigDecimal.ZERO;
		    } else {
		        try {
		            tongTienMuaHang = new BigDecimal(tongTienMuaText);
		        } catch (NumberFormatException e) {
		            JOptionPane.showMessageDialog(this, "Giá trị tổng tiền mua không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return; 
		        }
		    }

		    // Tính tổng hóa đơn
		    BigDecimal tongHoaDon = tongTienTraKhach.subtract(tongTienMuaHang);

		    if (tongHoaDon.compareTo(BigDecimal.ZERO) < 0) {
		        
		        lblTienTraKhach.setText("Khách cần trả:"); 
		        txtTongHoaDon.setText(tongHoaDon.abs().toString()); 

		        txtKhachTra.setEnabled(true);
		        txtKhachTra.requestFocus();
		    } else {
		        lblTienTraKhach.setText("Tiền trả khách:"); 
		        txtTongHoaDon.setText(tongHoaDon.toString()); 

		        txtKhachTra.setEnabled(false);
		    }
		}

	
	

		private void thanhToan() {
		    DonDoiTra donDoiTra = taoDonDoiTra();
		    
		    if (donDoiTra != null && donDoiTraDAO != null) {
		        boolean kq = donDoiTraDAO.themDonDoiTra(donDoiTra); 
		        
		        if (kq) {
		            JOptionPane.showMessageDialog(panelTong, "Tạo đơn đổi trả thành công!");
		            
		            String maDonDoiTra = donDoiTraDAO.maDonDoiTra(donDoiTra.getMaDonDoiTra());
		            ThemChiTietDonDoiTra(maDonDoiTra);
		            
		            Object[][] dataDoiHang = tableDoiHang.getDataStart1();
				    Object[][] dataTraHang = tableTraHang.getDataStart1();
		            
		            String tienTraHang = txtTongGiaGoc.getText();
		            String phitrahang = txtPhiTraHang.getText();
		            String tienMua = txtTongTienHang.getText();
		            String giamGia = txtGiamGia.getText();
		            String tienKhachDua = txtKhachTra.getText();
		            String tongTien = txtTongHoaDon.getText();

		            
		            BigDecimal tienKhachDuaDecimal = BigDecimal.ZERO;
		            BigDecimal tongTienDecimal = BigDecimal.ZERO;

		            try {
		                tienKhachDuaDecimal = new BigDecimal(tienKhachDua);
		            } catch (NumberFormatException e) {
		                System.out.println("Giá trị khách đưa không hợp lệ: " + tienKhachDua);
		            }

		            try {
		                tongTienDecimal = new BigDecimal(tongTien);
		            } catch (NumberFormatException e) {
		                System.out.println("Giá trị tổng tiền không hợp lệ: " + tongTien);
		            }

		            BigDecimal tienTraKhach = tienKhachDuaDecimal.subtract(tongTienDecimal);
		            DecimalFormat df = new DecimalFormat("#.0000");  
		            String formattedTienTraKhach = df.format(tienTraKhach); 
		            String tienTra = formattedTienTraKhach;

		            HoaDonDoiTraUI hddoiTra = new HoaDonDoiTraUI(donDoiTra,dataTraHang, dataDoiHang, tienTraHang, phitrahang, tienMua, giamGia, tongTien, tienKhachDua, tienTra);
		            hddoiTra.setVisible(true);

		            int askPrint = JOptionPane.showConfirmDialog(this, 
		                "Bạn có muốn in hóa đơn không?", "In Vé", 
		                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		            if (askPrint == JOptionPane.YES_OPTION) {
		                BufferedImage bff = ScreenshotHelper.captureComponent(hddoiTra);
		                ScreenshotHelper.printImage(bff);
		                hddoiTra.setVisible(false); 
		            } else {
		                hddoiTra.setVisible(false);
		            }

		         
		            LamMoi();

		        } else {
		            JOptionPane.showMessageDialog(this, "Tạo đơn đổi trả thất bại!");
		            LamMoi();
		        }

		    } else {
		        JOptionPane.showMessageDialog(this, "Đối tượng chưa được khởi tạo!");
		    }
		}

	
	
}
