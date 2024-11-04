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
import java.math.BigDecimal;
import java.sql.Date;
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
import dao.ChiTietDonDoiTraDAO;
import dao.DonDoiTraDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
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
	private JTextField txtMaKhachHang;
	private JTextField txtSDT;
	private CustomButton btnLamMoi;
	private JLabel lblTenNV;
	private JLabel lblNgayLap;
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
	private CustomTable tableTraHang;
	private ChiTietHoaDonCTR chiTietHoaDonCTR;
	private JTextField txtDiemTichLuy;
	private CustomTable tableDoiHang;
	private DonDoiTraDAO donDoiTraDAO;
	private BigDecimal tienTraKhach;
	
	private String lastDate = "";
	private Map<String, Integer> soThuTuMap = new HashMap<>();
	

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
				
				JLabel lblNewLabel_1_1_1 = new JLabel("Điểm tích lũy:");
				lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblNewLabel_1_1_1.setBounds(42, 150, 140, 25);
				panelKhachHang.add(lblNewLabel_1_1_1);
				
				txtDiemTichLuy = new JTextField();
				txtDiemTichLuy.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtDiemTichLuy.setEnabled(false);
				txtDiemTichLuy.setColumns(10);
				txtDiemTichLuy.setBorder(new LineBorder(Color.BLACK, 1)); 
				txtDiemTichLuy.setBounds(177, 145, 298, 30);
				panelKhachHang.add(txtDiemTichLuy);
				
				
				
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
				
				
				btnTraHang = new CustomButton("Thanh Toán", UIStyles.ThemButtonStyle, null, CustomButtonIconSide.LEFT, () -> thanhToan());
				btnTraHang.setForeground(Color.WHITE);
				btnTraHang.setFont(new Font("Tahoma", Font.BOLD, 20));
				btnTraHang.setBounds(90, 453, 170, 40);
				panelHoaDon.add(btnTraHang);
				
				btnLamMoi = new CustomButton("Làm mới", UIStyles.NavBarButtonStyle, null, CustomButtonIconSide.LEFT, () -> LamMoi());
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
		        scrollPaneBang.setBounds(10, 72, 1255, 270); // Đặt kích thước và vị trí của scrollPane
		        panelTraHang.add(scrollPaneBang);
		        
		        tableTraHang.addMouseListener(new MouseAdapter() {
		            @Override
		            public void mouseClicked(MouseEvent e) {
		                int row = tableTraHang.rowAtPoint(e.getPoint());
		                if (row >= 0) { 
		                    DefaultTableModel model = (DefaultTableModel) tableTraHang.getModel();
		                    
		                    
		                    BigDecimal tongTien = (BigDecimal) model.getValueAt(row, 5);
		                    
		                    
		                    String currentTongGiaTraText = txtTongGiaTra.getText();
		                    BigDecimal tongGiaTra;
		                    
		                
		                    if (currentTongGiaTraText == null || currentTongGiaTraText.isEmpty()) {
		                        tongGiaTra = BigDecimal.ZERO; 
		                    } else {
		                        try {
		                            tongGiaTra = new BigDecimal(currentTongGiaTraText); 
		                        } catch (NumberFormatException ex) {
		                            JOptionPane.showMessageDialog(null, "Giá trị trong ô Tổng Giá Trả không hợp lệ.");
		                            return; 
		                        }
		                    }
		                    
		                   
		                    tongGiaTra = tongGiaTra.add(tongTien); 
		                    
		                  
		                    txtTongGiaTra.setText(tongGiaTra.toString());
		                    capNhatTienTraKhach();
		                    
		                
		                    model.removeRow(row);
		                }
		            }
		        });


		     
		        
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
		        scrollPaneBangDoi.setPreferredSize(new Dimension(1123, 711)); // thay đổi theo khung chứa
		        scrollPaneBangDoi.setBorder(new LineBorder(Color.GRAY, 1, true));
		        scrollPaneBangDoi.setBounds(10, 72, 1255, 270); // Đặt kích thước và vị trí của scrollPane
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

		                           
		                            model.setValueAt(newQuantity, row, column); 

		                            // Cập nhật tổng tiền dựa trên số lượng mới
		                            BigDecimal giaBan = (BigDecimal) model.getValueAt(row, 3);
		                            BigDecimal tongTien = giaBan.multiply(BigDecimal.valueOf(newQuantity)); 
		                            model.setValueAt(tongTien, row, 5); 

		                            capNhatTienTraKhach(); 

		                        } catch (NumberFormatException ex) {
		                            JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ, vui lòng nhập lại.");
		                        }
		                    }
		                }
		            }
		        });


		    
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
	
	
	




	
	
	// tìm hóa đơn cần đổi trả
	private void timChiTietHoaDonTheoMa(String maHD) {
		ArrayList<ChiTietHoaDon> dsChiTietHoaDon = chiTietHoaDonCTR.timChiTietHoaDonTheoMaHoaDon(maHD);
		 if (dsChiTietHoaDon != null && !dsChiTietHoaDon.isEmpty()) {
			 LayThongTinKhachHangTheoHoadon(maHD);
		Object[][] data = new Object[dsChiTietHoaDon.size()][6];
		for (int i = 0; i < dsChiTietHoaDon.size(); i++) {
			ChiTietHoaDon chiTietHoaDon = dsChiTietHoaDon.get(i);
			 data[i][0] = chiTietHoaDon.getSanPhamYTe().getMaSanPham(); 
		     data[i][1] = chiTietHoaDon.getSanPhamYTe().getTenSanPham(); 
		     data[i][2] = chiTietHoaDon.getSanPhamYTe().getGiaBan();
		     data[i][3] = chiTietHoaDon.getSanPhamYTe().getDonViTinh();
		     data[i][4] = chiTietHoaDon.getSoLuong();
		     data[i][5] = chiTietHoaDon.getGiaBan();
		     
		}
		tableTraHang.setData(data);
		txtTimTheoMaHoaDon.setText(null);
		 } else {
		     JOptionPane.showMessageDialog(this, "Không tìm thấy chi tiết hóa đơn cho mã: " + maHD);
		        
		       
		    }
	}
	
	// thêm sản phẩm mới vào chiTietDoiTra
	
	private void themSanPhamVaoChiTietDoiTra(String maSP) {
	    SanPhamYTeDAO spDao = new SanPhamYTeDAO();
	    ArrayList<SanPhamYTe> dsSP = spDao.timSanPhamTheoMaTrongDDT(maSP);
	    
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
	            newData[currentData.length][2] = spYTe.getDonViTinh();
	            newData[currentData.length][3] = spYTe.getGiaBan();
	            newData[currentData.length][4] = 1;
	            newData[currentData.length][5] = tinhGiaBan(spYTe, 1); 

	            tableDoiHang.setData(newData);
	        } else {
	            tableDoiHang.setData(currentData); 
	        }
	        capNhatTienTraKhach();

	        txtTimTheoMaSP.setText(null);
	        JOptionPane.showMessageDialog(this, "Sản phẩm đã được tìm thấy và thêm vào danh sách chi tiết đổi trả.");
	    } else {
	        JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm với mã: " + maSP);
	    }
	}


	// tính tiền trả khách
	private BigDecimal tinhGiaBan(SanPhamYTe spYTe, int soLuong) {
	    
	    return spYTe.getGiaBan().multiply(new BigDecimal(soLuong));
	}
	
	
	// phát sinh mã chi tiết đơn đổi trả
	 public String phatSinhMaChiTietDoiTra(String maDonDoiTra) {
	      
	        String ddMMYYYYXXXX = maDonDoiTra.substring(2, 14);
	        String prefix = "CTDDT" + ddMMYYYYXXXX; 

	        int stt = soThuTuMap.getOrDefault(maDonDoiTra, 0);
	        stt = (stt + 1) % 100; 
	        soThuTuMap.put(maDonDoiTra, stt); 

	       
	        String maChiTietDoiTra = prefix + String.format("%02d", stt); 
	        return maChiTietDoiTra;
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
		    
		    // Kiểm tra xem mã đã tồn tại trong cơ sở dữ liệu hay chưa
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
			 txtDiemTichLuy.setText(String.valueOf(hd.getKhachHang().getDiemTichLuy()));
			 txtmaHoaDon.setText(hd.getMaHoaDon());
			 txtTongGiaGoc.setText(String.valueOf(hd.getThanhTien()));
		 } else {
        
        JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin khách hàng cho mã hóa đơn: " + maHD);
    }
		 
	}
	
	private void capNhatTienTraKhach() {
	    // Tính tổng tiền trong bảng DoiHang
	    BigDecimal tongTienDoiHang = BigDecimal.ZERO;
	    Object[][] currentData = tableDoiHang.getData();

	    for (Object[] row : currentData) {
	        BigDecimal tongTienRow = (BigDecimal) row[5]; // Giả sử cột 5 chứa tổng tiền
	        tongTienDoiHang = tongTienDoiHang.add(tongTienRow);
	    }

	    // Tính tổng giá trả từ txtTongGiaTra
	    BigDecimal tongGiaTra;
	    String currentTongGiaTraText = txtTongGiaTra.getText();

	    if (currentTongGiaTraText == null || currentTongGiaTraText.isEmpty()) {
	        tongGiaTra = BigDecimal.ZERO; // Nếu ô rỗng, khởi tạo bằng 0
	    } else {
	        try {
	            tongGiaTra = new BigDecimal(currentTongGiaTraText); // Khởi tạo với giá trị hiện tại
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(null, "Giá trị trong ô Tổng Giá Trả không hợp lệ.");
	            return; // Thoát khỏi hàm nếu giá trị không hợp lệ
	        }
	    }

	    // Tính toán số tiền trả khách
	   tienTraKhach = tongTienDoiHang.subtract(tongGiaTra);

	    // Cập nhật vào txtTienTraKhach
	    txtTienTraKhach.setText(tienTraKhach.toString());
	}
	
	private void LamMoi() {
	  
	    DefaultTableModel modelDoiHang = (DefaultTableModel) tableDoiHang.getModel();
	    modelDoiHang.setRowCount(0); 

	    
	    DefaultTableModel modelTraHang = (DefaultTableModel) tableTraHang.getModel();
	    modelTraHang.setRowCount(0); 

	   
	    txtTenKH.setText("");
	    txtDiemTichLuy.setText("");
	    txtmaHoaDon.setText("");
	    txtTongGiaGoc.setText("");
	    txtPhiTraHang.setText("");
	    txtSDT.setText("");
	    txtTongGiaTra.setText(""); 
	    txtTienTraKhach.setText(""); 
	    txtGhiChu.setText("");

	    
	    cbGiamGia.setSelectedIndex(-1); 

	    JOptionPane.showMessageDialog(null, "Đã làm mới dữ liệu.");
	}

	

	
	
	// tạo đơn đổi trả

		private DonDoiTra taoDonDoiTra() {
		    String maDonDoiTra = phatSinhMaDonDoiTra(); 
		    Date ngayDoiTra = new Date(System.currentTimeMillis()); 
		    BigDecimal tienHoan = calculateTotalRefund(); 
		    
		    NhanVien nv = getSelectedNhanVien(); 
		    KhuyenMai km = getSelectedKhuyenMai();
		    KhachHang kh = getSelectedKhachHang(txtmaHoaDon.getText()); 
		    HoaDon hd = getSelectedHoaDon(); 

		    // Kiểm tra tính hợp lệ của nhân viên, khách hàng và hóa đơn
		    if (nv == null || kh == null || hd == null) {
		        JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên, khách hàng, và hóa đơn hợp lệ.");
		        return null; // Trả về null nếu có vấn đề
		    }

		    // Tạo đối tượng DonDoiTra
		    DonDoiTra donDoiTra = new DonDoiTra(maDonDoiTra, ngayDoiTra, tienHoan, nv, km, kh, hd);

		    if (donDoiTraDAO != null) {
		        boolean kq = donDoiTraDAO.themDonDoiTra(donDoiTra); 
		        if (kq) {
		            JOptionPane.showMessageDialog(this, "Tạo đơn đổi trả thành công!");
		            LamMoi(); 
		            return donDoiTra; // Trả về đối tượng DonDoiTra nếu thành công
		        } else {
		            JOptionPane.showMessageDialog(this, "Tạo đơn đổi trả thất bại!");
		            return null; // Trả về null nếu thất bại
		        }
		    } else {
		        JOptionPane.showMessageDialog(this, "Đối tượng DonDoiTraDAO chưa được khởi tạo!");
		        return null;
		    }
		}


		private NhanVien getSelectedNhanVien() {
			
		    return new NhanVien("NV000001");
		}

		private KhuyenMai getSelectedKhuyenMai() {
		    
		    return new KhuyenMai(); 
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

		private BigDecimal calculateTotalRefund() {
		    return tienTraKhach; 
		}


		
		
		private Object[][] layDuLieuTuBangDoiHang() {
		    int rowCount = tableDoiHang.getRowCount(); 
		    int columnCount = tableDoiHang.getColumnCount(); 

		    System.out.println("Số hàng trong bảng DoiHang: " + rowCount);
		    System.out.println("Số cột trong bảng DoiHang: " + columnCount);

		    if (rowCount == 0 || columnCount == 0) {
		        System.out.println("Bảng DoiHang không có dữ liệu.");
		        return new Object[0][0]; // Trả về mảng rỗng
		    }

		    Object[][] dataDoiHang = new Object[rowCount][columnCount]; 

		    for (int i = 0; i < rowCount; i++) {
		        for (int j = 0; j < columnCount; j++) {
		            dataDoiHang[i][j] = tableDoiHang.getValueAt(i, j); 
		            System.out.println("Dữ liệu tại [" + i + "][" + j + "]: " + dataDoiHang[i][j]);
		        }
		    }

		    return dataDoiHang; 
		}


		private Object[][] layDuLieuTuBangTraHang() {
		    int rowCount = tableTraHang.getRowCount(); 
		    int columnCount = tableTraHang.getColumnCount(); 

		    // Kiểm tra xem bảng có dữ liệu không
		    if (rowCount == 0 || columnCount == 0) {
		        System.out.println("Bảng TraHang không có dữ liệu.");
		        return new Object[0][0]; // Trả về mảng rỗng
		    }

		    Object[][] dataTraHang = new Object[rowCount][columnCount]; 

		    for (int i = 0; i < rowCount; i++) {
		        for (int j = 0; j < columnCount; j++) {
		            dataTraHang[i][j] = tableTraHang.getValueAt(i, j); 
		        }
		    }

		    return dataTraHang; 
		}

	
	// Phương thức chính để thực hiện quy trình
		private void xuLyDonDoiTra(DonDoiTra donDoiTra, LoHang loHang) {
		    // Lấy dữ liệu từ bảng DoiHang và TraHang
		    Object[][] dataDoiHang = layDuLieuTuBangDoiHang();
		    Object[][] dataTraHang = layDuLieuTuBangTraHang(); 

		    // Thông báo để theo dõi
		    System.out.println("Số hàng trong bảng DoiHang: " + dataDoiHang.length);
		    System.out.println("Số hàng trong bảng TraHang: " + dataTraHang.length);

		    // Kiểm tra dữ liệu	
		    if (dataDoiHang.length == 0) {
		        System.out.println("Bảng DoiHang không có dữ liệu.");
		    }
		    if (dataTraHang.length == 0) {
		        System.out.println("Bảng TraHang không có dữ liệu.");
		    }

		    // Lấy chi tiết đơn đổi trả từ dữ liệu
		    ArrayList<ChiTietDonDoiTra> chiTietList = layChiTietDonDoiTraTuBang(dataDoiHang, dataTraHang, donDoiTra, loHang);
		    
		    // Thông báo để kiểm tra chi tiết
		    System.out.println("Số chi tiết trong chiTietList: " + chiTietList.size());
		    
		    // Tiết đơn đổi trả vào cơ sở dữ liệu
		    luuChiTietDonDoiTra(chiTietList);
		}

	// Lấy chi tiết đơn đổi trả từ bảng
		private ArrayList<ChiTietDonDoiTra> layChiTietDonDoiTraTuBang(Object[][] dataDoiHang, Object[][] dataTraHang, DonDoiTra donDoiTra, LoHang loHang) {
		    ArrayList<ChiTietDonDoiTra> chiTietList = new ArrayList<>();

		    // Lấy chi tiết từ bảng đổi hàng
		    addChiTietFromData(dataDoiHang, donDoiTra, loHang, chiTietList);
		    // Lấy chi tiết từ bảng trả hàng
		    addChiTietFromData(dataTraHang, donDoiTra, loHang, chiTietList);

		    // Kiểm tra chi tiết đã được thêm thành công
		    if (chiTietList.isEmpty()) {
		        System.out.println("Không có chi tiết nào được thêm vào chiTietList.");
		    }

		    return chiTietList;
		}

	// Hàm để xử lý dữ liệu từ bảng và thêm vào chi tiết đơn đổi trả
	private void addChiTietFromData(Object[][] data, DonDoiTra donDoiTra, LoHang loHang, ArrayList<ChiTietDonDoiTra> chiTietList) {
	    for (Object[] row : data) {
	       
	        if (row.length > 5 && row[0] instanceof SanPhamYTe && row[4] instanceof Integer && row[5] instanceof BigDecimal) { 
	            // Tạo đối tượng ChiTietDonDoiTra
	            ChiTietDonDoiTra chiTiet = new ChiTietDonDoiTra	
	            		(
	                phatSinhMaChiTietDoiTra(donDoiTra.getMaDonDoiTra()), 
	                (Integer) row[4], 
	                (BigDecimal) row[5], 
	                donDoiTra, 
	                (SanPhamYTe) row[0], 
	                loHang, 
	                loHang 
	            );
	            System.out.println("Chi tiết sắp lưu: " + chiTiet);
	            chiTietList.add(chiTiet); 
	        } else {
	            System.out.println("Lấy thất bại ");
	        }
	    }
	}

	

	// Lưu chi tiết đơn đổi trả vào cơ sở dữ liệu
	private void luuChiTietDonDoiTra(ArrayList<ChiTietDonDoiTra> chiTietList) {
	    ChiTietDonDoiTraDAO chiTietDonDoiTraDAO = new ChiTietDonDoiTraDAO();
	    boolean coLoi = false; // Biến kiểm tra xem có lỗi xảy ra hay không

	    for (ChiTietDonDoiTra chiTiet : chiTietList) {
	        if (!chiTietDonDoiTraDAO.themChiTietDoiTra(chiTiet)) {
	            JOptionPane.showMessageDialog(null, "Lỗi khi lưu chi tiết đơn đổi trả: ");
	            coLoi = true; 
	        }
	    }

	    if (!coLoi) {
	        JOptionPane.showMessageDialog(null, "Lưu chi tiết đơn đổi trả thành công!");
	    }
	}
	
	
	private void thanhToan() {
	 
	    DonDoiTra donDoiTra = taoDonDoiTra();
	    
	    if (donDoiTra != null) {
	    	
	    	String maLo = "LH010920240001";
	    	LoHang lohang = new LoHang(maLo);
	        xuLyDonDoiTra(donDoiTra,lohang);
	    } else {
	        JOptionPane.showMessageDialog(null, "Lỗi khi tạo đơn đổi trả. Vui lòng kiểm tra lại.");
	    }
	}

	
	
}
