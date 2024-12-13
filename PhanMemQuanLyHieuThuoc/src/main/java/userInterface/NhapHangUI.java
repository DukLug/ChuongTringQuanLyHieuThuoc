package userInterface;

import javax.swing.*;
import javax.swing.border.*;
import com.toedter.calendar.JDateChooser;
import application.PhanMemQuanLyHieuThuoc;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import component.*;
import component.CustomButton.CustomButtonIconSide;
import controller.*;
import entity.*;
import customDataType.*;

public class NhapHangUI extends JPanel {
    private JTextField maDonNhap;
    private JDateChooser ngayNhap;
    private JTextField nhanVienNhap;
    private DonNhapHang donNhap;
    private JTextField txtTimKiem;
    private CustomButton btnTimKiem;
    private ArrayList<ChiTietDonNhap> chiTiet;
    private ArrayList<SanPhamYTe> dsSanPham;
    private ArrayList<CustomItem> items;
    
    private CustomItemList danhSachSanPhamNhap ;

    public NhapHangUI() {
        super();
        
        dsSanPham = new ArrayList<SanPhamYTe>();
        items = new ArrayList<CustomItem>();
        chiTiet = new ArrayList<ChiTietDonNhap>();
        System.out.println(KhoCTR.layMaNhapHangMoi());
        donNhap = new DonNhapHang(KhoCTR.layMaNhapHangMoi());
        
        taoHinh();
    }

    public void taoHinh() {
        setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
        setLayout(null);
        setBackground(Color.WHITE);

        JPanel panelTong = new JPanel();
        panelTong.setBackground(UIStyles.BackgroundColor);
        panelTong.setBounds(0, 0, UIStyles.ApplicationWidth, UIStyles.MainSectionHeight);
        add(panelTong);
        panelTong.setLayout(new BorderLayout());

        
        // Tiêu đề panel ở phía Bắc
        JPanel titlePanel = new JPanel();
        JLabel lblTitle = new JLabel("Nhập hàng");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 35));
        titlePanel.add(lblTitle);
        titlePanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        panelTong.add(titlePanel, BorderLayout.NORTH);
        
        
        danhSachSanPhamNhap = new CustomItemList(
        		1550, 800, 100, 10, Color.WHITE,
            new int[]{200, 200, 200, 200, 200, 150, 150, 100},
            UIStyles.LabelFontColorGreen, 50,
            new String[]{"Sản phẩm", "Số lượng DVT1", "Số lượng DVT2", "Số lượng DVT3", "Giá cơ bản", "Ngày sản xuất", "Hạn sử dụng", "Lưu kho"},
            UIStyles.BoldFont,
            new ArrayList<>()
        );
        
        panelTong.add(danhSachSanPhamNhap, BorderLayout.CENTER);
        
        // Tìm kiếm
        JPanel panelTimKiem = new JPanel(null);
        panelTimKiem.setBackground(Color.white);
        panelTimKiem.setPreferredSize(new Dimension(500, 50));
        
        txtTimKiem = new JTextField("Nhập mã vạch");
        txtTimKiem.setBounds(10, 10, 300, 40);
        txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtTimKiem.setBorder(new LineBorder(Color.BLACK, 1));

        btnTimKiem = new CustomButton("",UIStyles.TimButtonStyle,UIStyles.FindIcon,CustomButtonIconSide.LEFT,()->timKiem("1234567890123"));
        btnTimKiem.setBounds(320, 10, 150, 40);
        btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnTimKiem.setBounds(320, 10, 150, 40);
        
        CustomButton btnNhapHang = new CustomButton("Xác nhận",UIStyles.TimButtonStyle,()->nhapHang());
        btnNhapHang.setBounds(150, 100, 200, 40);
        btnNhapHang.setFont(new Font("Tahoma", Font.PLAIN, 20));

        panelTimKiem.add(txtTimKiem);
        panelTimKiem.add(btnTimKiem);
        panelTimKiem.add(btnNhapHang);
        panelTimKiem.setBorder(BorderFactory.createLineBorder(UIStyles.LabelFontColorGreen, 2));
        
        panelTong.add(panelTimKiem, BorderLayout.EAST);
        
        this.add(panelTong);

        
    }

    // Phương thức stub để compile (bạn sẽ triển khai chi tiết sau)
    private void luuDonNhap() {
        // Xử lý lưu đơn nhập hàng
    }

    private void lamMoiDonNhap() {
        // Xử lý làm mới form nhập hàng
    }
    
    private void nhapHang() {
        try {
            // Set current date for the import order
            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

            // Set the current date for the import order
            donNhap.setNgayNhap(currentDate);

            // Set the current employee
            donNhap.setNhanVienNhap(PhanMemQuanLyHieuThuoc.nhanVienHienTai);

            // Add the import order
            KhoCTR.themDonNhap(donNhap);

            for (int i = 0; i < items.size(); i++) {
                DonNhapRow row = (DonNhapRow) items.get(i);
                ChiTietDonNhap ct = row.layThongTin();

                // Add import order details
                KhoCTR.themChiTietDonNhap(ct);

                // Create lot with all necessary parameters
                LoHang lh = new LoHang(
                    "LH" + donNhap.getMaDonNhap().substring(2) + String.format("%02d", dsSanPham.size()), // Generate a unique lot code
                    parseStringToDate(row.getNSX()), // Manufacturing date
                    parseStringToDate(row.getHSD()), // Expiration date
                    ct.getGiaNhap(), // Purchase price
                    ct.getSoLuongDonViTinh1(),
                    ct.getSoLuongDonViTinh2(),
                    ct.getSoLuongDonViTinh3(),
                    row.getKho(), // Empty or generate position
                    ct.getMaSanPham(), // Retrieve product by code
                    ct
                );

                // Add lot
                KhoCTR.themLoHang(lh);
            }

            // Hiển thị thông báo thành công
            JOptionPane.showMessageDialog(this, "Nhập hàng thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            TrangChuUI.singleton.taiTrang(new NhapHangUI());

        } catch (Exception e) {
            // Hiển thị bảng thông báo khi có lỗi
            JOptionPane.showMessageDialog(this, 
                "Dữ liệu đã nhập không hợp lệ", 
                "Lỗi", 
                JOptionPane.ERROR_MESSAGE);
        }
    }


    private void timKiem(String maVach) {
        // Tìm kiếm sản phẩm theo mã vạch
        SanPhamYTe sanPham = SanPhamCTR.timSanPhamTheoMaVach(maVach);
        if (sanPham != null) {
            // Create a new ChiTietHoaDon and add it to the list
            ChiTietDonNhap chiTietDonNhap = new ChiTietDonNhap("CT" + donNhap.getMaDonNhap() + String.format("%02d", dsSanPham.size()));
            chiTietDonNhap.setMaSanPham(sanPham);
            chiTietDonNhap.setMaDonNhap(donNhap);
            // Add this item to the list
            CustomItem item = new DonNhapRow( sanPham,chiTietDonNhap);
            if(dsSanPham.contains(sanPham)) {
            	
            	return;
            }
            danhSachSanPhamNhap.addItem(item);
            items.add(item);
            chiTiet.add(chiTietDonNhap);
            dsSanPham.add(sanPham);
        }
    }

    public static java.sql.Date parseStringToDate(String dateString) {
        try {
            // Define the expected date format
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Adjust format as needed
            
            // Parse the string into a java.util.Date
            java.util.Date utilDate = dateFormat.parse(dateString);
            
            // Convert java.util.Date to java.sql.Date
            return new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            // Handle invalid date format
            System.err.println("Invalid date format: " + e.getMessage());
            return null; // Or throw an exception if needed
        }
    }

    public static class DonNhapRow extends CustomItem{
		//Item, ItemList chỉ phụ trách thể hiện thông tin, thông tin lưu ở các class con giống như NhapHangRow này
		//Các thuộc tính style, code thẳng vào, phải có static, cop về chỉnh lại cũng được
		private static int prefWidth = 1450;
		private static int prefHeight = 100;
		private static Font font = UIStyles.DefaultFont;
		private static Color backgroundColor = Color.white;
		private static int infoMargin = 5;
		private static Font titleFont = new Font("Tahoma", Font.BOLD, 18);	
		private static Font normalFont = new Font("Tahoma", Font.BOLD, 18);	
		//Rounded border phức tạp, có thể phá panel bên trong, bán kính càng lớn càng dễ hỏng
		private static Border border = BorderFactory.createEmptyBorder();
		//Chú ý nên match với lúc tạo ItemList
		private static int[] cellsWidth = new int[] {200, 200, 200, 200, 200, 150, 150, 100};
		
		//cells
		private JComponent[] cells;
		//Du lieu item
		
		public SanPhamYTe sanPham;
		public ChiTietDonNhap chiTiet;
		
		//Các cell thêm vào đây rồi set phía dưới giống các ui, để public để sau đó lấy dữ liệu, set dữ liệu
		public JLabel tenSanPham;
		public JLabel giaSanPham;
		
		public JTextField sl1;
		public JTextField sl2;
		public JTextField sl3;
		
		public JTextField gia;
		public JTextField nsx;
		public JTextField hsd;
		public JTextField kho;
		
		private CustomButton sl1Dec;
		private CustomButton sl1Inc;
		private CustomButton sl2Dec;
		private CustomButton sl2Inc;
		private CustomButton sl3Dec;
		private CustomButton sl3Inc;
		
		
		//Thêm nội dung cho các trường đã khai báo ở trên vào constructor, có stt mới thêm stt vào, không thì chỉ cần data
		public DonNhapRow(SanPhamYTe sanPham, ChiTietDonNhap chiTiet) {
			//Tạo item rỗng, trên cop thì ở đây  cop
			super(prefWidth, prefHeight, backgroundColor, border, cellsWidth);
			
			//Set dữ liệu
			this.sanPham = sanPham;
			this.chiTiet = chiTiet;

			JPanel cellTen = new JPanel();
			tenSanPham = new JLabel(sanPham.getTenSanPham());
			tenSanPham.setFont(titleFont);
			cellTen.setBorder(BorderFactory.createEmptyBorder(infoMargin, infoMargin, infoMargin, infoMargin));
			cellTen.add(tenSanPham);
			cellTen.setBackground(backgroundColor);
			
			JPanel cellSL1 = new JPanel();
			
			sl1 = new JTextField();
			sl1.setBounds(0, 0, 50, 50);
			sl1.setText("0");
			sl1.setFont(normalFont);
			
			

			sl1Dec = new CustomButton("-", UIStyles.SmallButtonStyle, null, CustomButtonIconSide.LEFT, () -> {
			    // Decrease sl1 by 1 unit
			    int currentValue = Integer.parseInt(sl1.getText());  // Get the current value as an integer
			    if (currentValue > 0) {  // Prevent negative values
			        sl1.setText(String.valueOf(currentValue - 1));  // Decrease the value and update the text field
			    }
			});

			sl1Inc = new CustomButton("+", UIStyles.SmallButtonStyle, null, CustomButtonIconSide.LEFT, () -> {
			    // Increase sl1 by 1 unit
			    int currentValue = Integer.parseInt(sl1.getText());  // Get the current value as an integer
			    sl1.setText(String.valueOf(currentValue + 1));  // Increase the value and update the text field
			});
			
			cellSL1.setBackground(backgroundColor);
			cellSL1.setLayout(new BorderLayout());
			cellSL1.setBorder(BorderFactory.createEmptyBorder(infoMargin, infoMargin, infoMargin, infoMargin));
			cellSL1.add(sl1, BorderLayout.CENTER);
			cellSL1.add(sl1Dec, BorderLayout.WEST);
			cellSL1.add(sl1Inc, BorderLayout.EAST);
			JLabel dvt1 = new JLabel(sanPham.getDonViTinh1().toString());
			dvt1.setFont(normalFont);
			cellSL1.add(dvt1, BorderLayout.SOUTH);
			
			JPanel cellSL2 = new JPanel();
			sl2 = new JTextField();
			sl2.setBounds(0,0, 50, 50);
			sl2.setText("0");
			sl2.setFont(normalFont);
			cellSL2.setBackground(backgroundColor);
			cellSL2.setLayout(new BorderLayout());
			cellSL2.setBorder(BorderFactory.createEmptyBorder(infoMargin, infoMargin, infoMargin, infoMargin));
			cellSL2.add(sl2, BorderLayout.CENTER);
			sl2Dec = new CustomButton("-", UIStyles.SmallButtonStyle, null, CustomButtonIconSide.LEFT, () -> {
			    // Decrease sl1 by 1 unit
			    int currentValue = Integer.parseInt(sl2.getText());  // Get the current value as an integer
			    if (currentValue > 0) {  // Prevent negative values
			        sl2.setText(String.valueOf(currentValue - 1));  // Decrease the value and update the text field
			    }
			});

			sl2Inc = new CustomButton("+", UIStyles.SmallButtonStyle, null, CustomButtonIconSide.LEFT, () -> {
			    // Increase sl1 by 1 unit
			    int currentValue = Integer.parseInt(sl2.getText());  // Get the current value as an integer
			    sl2.setText(String.valueOf(currentValue + 1));  // Increase the value and update the text field
			});
			cellSL2.add(sl2Dec, BorderLayout.WEST);
			cellSL2.add(sl2Inc, BorderLayout.EAST);
			JLabel dvt2 = new JLabel(sanPham.getDonViTinh2().toString() +" "+ sanPham.getGiaTriQuyDoi2());
			dvt2.setFont(normalFont);
			cellSL2.add(dvt2, BorderLayout.SOUTH);
			
			
			JPanel cellSL3 = new JPanel();
			sl3 = new JTextField();
			sl3.setBounds(0,0, 50, 50);
			sl3.setText("0");
			sl3.setFont(normalFont);
			cellSL3.setBackground(backgroundColor);
			cellSL3.setLayout(new BorderLayout());
			cellSL3.setBorder(BorderFactory.createEmptyBorder(infoMargin, infoMargin, infoMargin, infoMargin));
			cellSL3.add(sl3, BorderLayout.CENTER);
			sl3Dec = new CustomButton("-", UIStyles.SmallButtonStyle, null, CustomButtonIconSide.LEFT, () -> {
			    // Decrease sl1 by 1 unit
			    int currentValue = Integer.parseInt(sl3.getText());  // Get the current value as an integer
			    if (currentValue > 0) {  // Prevent negative values
			        sl3.setText(String.valueOf(currentValue - 1));  // Decrease the value and update the text field
			    }
			});

			sl3Inc = new CustomButton("+", UIStyles.SmallButtonStyle, null, CustomButtonIconSide.LEFT, () -> {
			    // Increase sl1 by 1 unit
			    int currentValue = Integer.parseInt(sl3.getText());  // Get the current value as an integer
			    sl3.setText(String.valueOf(currentValue + 1));  // Increase the value and update the text field
			});
			cellSL3.add(sl3Dec, BorderLayout.WEST);
			cellSL3.add(sl3Inc, BorderLayout.EAST);
			JLabel dvt3 = new JLabel(sanPham.getDonViTinh3().toString() + " "+ sanPham.getGiaTriQuyDoi3());
			dvt3.setFont(normalFont);
			cellSL3.add(dvt3, BorderLayout.SOUTH);
			
			JPanel cellGia = new JPanel();
			gia = new JTextField();
			gia.setBounds(0,0, 25, 20);
			gia.setText("0");
			gia.setFont(normalFont);
			cellGia.setBackground(backgroundColor);
			cellGia.setLayout(new BorderLayout());
			cellGia.setBorder(BorderFactory.createEmptyBorder(infoMargin, infoMargin, infoMargin, infoMargin));
			cellGia.add(gia, BorderLayout.CENTER);
			
			JPanel cellnsx = new JPanel();
			nsx = new JTextField();
			nsx.setBounds(0,0, 25, 20);
			nsx.setText("YYYY-MM-DD");
			nsx.setFont(normalFont);
			cellnsx.setBackground(backgroundColor);
			cellnsx.setLayout(new BorderLayout());
			cellnsx.setBorder(BorderFactory.createEmptyBorder(infoMargin, infoMargin, infoMargin, infoMargin));
			cellnsx.add(nsx, BorderLayout.CENTER);
			JLabel ct1 = new JLabel("(YYYY-MM-DD)");
			ct1.setFont(normalFont);
			cellnsx.add(ct1, BorderLayout.SOUTH);
			
			JPanel cellhsd = new JPanel();
			hsd = new JTextField();
			hsd.setBounds(0,0, 25, 20);
			hsd.setText("YYYY-MM-DD");
			hsd.setFont(normalFont);
			cellhsd.setBackground(backgroundColor);
			cellhsd.setLayout(new BorderLayout());
			cellhsd.setBorder(BorderFactory.createEmptyBorder(infoMargin, infoMargin, infoMargin, infoMargin));
			cellhsd.add(hsd, BorderLayout.CENTER);
			JLabel ct2 = new JLabel("(YYYY-MM-DD)");
			ct2.setFont(normalFont);
			cellhsd.add(ct2, BorderLayout.SOUTH);
			
			JPanel cellKho = new JPanel();
			kho = new JTextField();
			kho.setBounds(0,0, 25, 20);
			kho.setText("0");
			kho.setFont(normalFont);
			cellKho.setBackground(backgroundColor);
			cellKho.setLayout(new BorderLayout());
			cellKho.setBorder(BorderFactory.createEmptyBorder(infoMargin, infoMargin, infoMargin, infoMargin));
			cellKho.add(kho, BorderLayout.CENTER);
			
			
			JPanel cellDelete = new  JPanel();
			
			//Thêm cells vào Item
			cells = new JComponent[] {
					cellTen, cellSL1, cellSL2, cellSL3, cellGia, cellnsx, cellhsd, cellKho
			};
			
			super.addCells(cells);
		}

		

		public ChiTietDonNhap layThongTin() {
			chiTiet.setSoLuongDonViTinh1(Integer.parseInt(sl1.getText()));
			chiTiet.setSoLuongDonViTinh2(Integer.parseInt(sl2.getText()));
			chiTiet.setSoLuongDonViTinh3(Integer.parseInt(sl3.getText()));
			chiTiet.setGiaNhap(BigDecimal.valueOf(Double.parseDouble(gia.getText())));
			return chiTiet;
		}
		
		public String getNSX() {
			return nsx.getText();
		}
		
		public String getHSD() {
			return hsd.getText();
		}
		public String getKho() {
			return kho.getText();
		}
    }
}