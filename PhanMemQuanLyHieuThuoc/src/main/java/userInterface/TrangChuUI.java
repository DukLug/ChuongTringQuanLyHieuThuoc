package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Stack;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;


import javax.imageio.ImageIO;
import javax.swing.*;
import component.CustomButton;
import component.CustomButton.CustomButtonFunction;
import component.CustomButton.CustomButtonIconSide;
import controller.NhanVienCTR;
import controller.TaiKhoanCTR;
import component.CustomComboBox;
import customDataType.ChucVu;
import entity.TaiKhoan;
import application.*;


public class TrangChuUI extends JFrame {		
	public static TrangChuUI singleton;
	
	public JPanel panel;
	
	public JPanel topSection;
	public JPanel mainSection;
	public JPanel labelBar;
	public JPanel labelBarWest;
	public JPanel labelBarCenter;
	public JPanel labelBarEast;
	public JPanel navBar;
	public JPanel navBarWest;
	public JPanel navBarEast;
	
	private Stack<JPanel> uiHistory;
	
	public TrangChuUI(boolean toanManHinh) {
		super();
        lapFrame(toanManHinh);
        uiHistory = new Stack<JPanel>();
        
        singleton = this;
	}
	
	public void lapFrame(boolean toanManHinh) {
		if(this != null) setVisible(false);	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.yellow);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.ApplicationHeight));
        panel.setBackground(Color.blue);
        panel.setLayout(new BorderLayout());         
        
        topSection = new JPanel();
        topSection.setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.TopSectionHeight));
        
        labelBar = new JPanel();
        labelBar.setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.LabelBarHeight));
        labelBar.setBackground(Color.white);
        
        labelBarWest = new JPanel();
        labelBarWest.setLayout(new BoxLayout(labelBarWest, BoxLayout.X_AXIS));
        labelBarWest.add(new CustomButton("Quay Lại", UIStyles.LabelBarButtonStyle, UIStyles.GoBackIcon, CustomButtonIconSide.LEFT, ()->quayLai()));
        
        labelBarEast = new JPanel();
        labelBarEast.setLayout(new BoxLayout(labelBarEast, BoxLayout.X_AXIS));
        labelBarEast.add(new CustomButton("About", UIStyles.LabelBarButtonStyle, UIStyles.AboutIcon, CustomButtonIconSide.LEFT, ()->quayLai()));
        labelBarEast.add(new CustomButton("Help", UIStyles.LabelBarButtonStyle, UIStyles.HelpIcon, CustomButtonIconSide.LEFT, ()->quayLai()));
        //labelBarEast.add(new CustomButton(PhanMemQuanLyHieuThuoc.nhanVienHienTai.getHoTen(), UIStyles.LabelBarButtonStyle, UIStyles.UserIcon, CustomButtonIconSide.RIGHT, ()->quayLai()));
        labelBarEast.add(new CustomComboBox(
        		PhanMemQuanLyHieuThuoc.nhanVienHienTai.getHoTen(), 
        		UIStyles.LabelBarButtonStyle, 
        		UIStyles.UserIcon,
        		CustomButtonIconSide.RIGHT,
        		0,
        		UIStyles.NavBarDropBoxItemHeight,
        		new String[]{"Đăng xuất", "Đổi mật  khẩu"},
        		new CustomButtonFunction[] {

        			()->dangXuat(),

        			()->doiMatKhau(),

        		}
        		));
        //lbaelBarEast.add(new )
        labelBar.setLayout(new BorderLayout());
        labelBar.add(labelBarWest, BorderLayout.WEST);
        labelBar.add(labelBarEast, BorderLayout.EAST);
        
        navBar = new JPanel();	
        navBarWest = new JPanel();
        navBarEast = new JPanel();
        
		navBar.setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.NavBarHeight));
		navBar.setMaximumSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.TopSectionHeight));
		navBar.setMinimumSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.TopSectionHeight));
		navBar.setBackground(UIStyles.NavBarBackgroundColor);  
		navBar.setLayout(new BoxLayout(navBar, BoxLayout.X_AXIS));
				
		navBarWest.setLayout(new BoxLayout(navBarWest, BoxLayout.X_AXIS));	
		navBarWest.add(new CustomButton("Tổng quan", UIStyles.NavBarButtonStyle, UIStyles.HomeIcon, CustomButtonIconSide.LEFT, ()->taiTrang(new TongQuanUI())));
		navBarWest.add(new CustomComboBox(
        		"Quản Lý", 
        		UIStyles.NavBarButtonStyle, 
        		UIStyles.ProductIcon,
        		CustomButtonIconSide.LEFT,
        		0,
        		UIStyles.NavBarDropBoxItemHeight,
        		new String[]{"Sản phẩm", "Lô hàng", "Khách hàng", "Nhà cung cấp"},
        		new CustomButtonFunction[] {

        			()->taiTrang(new SanPhamYTeUI()),

        			()->taiTrang(new LoHangUI()),
        			()->taiTrang(new KhachHangUI()),
        			()->taiTrang(new NhaCungCapUI()),
        		}
        		));
        
		navBarWest.add(new CustomComboBox(
        		"Thống kê", 
        		UIStyles.NavBarButtonStyle, 
        		UIStyles.TransferIcon,
        		CustomButtonIconSide.LEFT,
        		0,
        		UIStyles.NavBarDropBoxItemHeight,

        		new String[]{"Hóa đơn", "Đơn nhập", "Đơn hủy"},
        		new CustomButtonFunction[] {
					()->taiTrang(new HoaDonUI()),
					()->taiTrang(new DonNhapHangUI()),
					()->taiTrang(new DonHuyHangUI()),
        		}
        		));
		if(PhanMemQuanLyHieuThuoc.nhanVienHienTai.getChucVu() == ChucVu.ChuCuaHang) {
			navBarWest.add(new CustomComboBox(
	        		"Quản lý nâng cao", 
	        		UIStyles.NavBarButtonStyle, 
	        		UIStyles.ContactIcon,
	        		CustomButtonIconSide.LEFT,
	        		0,
	        		UIStyles.NavBarDropBoxItemHeight,
	        		new String[]{"Khuyến mãi", "Nhân viên", "Tài khoản", "Loại sản phẩm"},
	        		
	        		new CustomButtonFunction[] {
	        			()->taiTrang(new KhuyenMaiUI()),
	        			()->taiTrang(new NhanVienUI()),
	        			()->taiTrang(new TaiKhoanUI()),
	        			()->taiTrang(new LoaiSanPhamUI())
	        		}
	        		));
			        
			navBarWest.add(new CustomComboBox(
	        		"Thống kê nâng cao", 
	        		UIStyles.NavBarButtonStyle, 
	        		UIStyles.ReportIcon,
	        		CustomButtonIconSide.LEFT,
	        		0,
	        		UIStyles.NavBarDropBoxItemHeight,
	        		new String[]{"Cuối ngày", "Bán hàng", "Hàng hóa", "Nhân viên", "Nhà cung cấp"},
	        		new CustomButtonFunction[] {
	        			()->taiTrang(new BCCuoiNgayUI()),
	        			()->taiTrang(new BCBanHangUI()),
	        			()->taiTrang(new BCHangHoaUI()),
	        			()->taiTrang(new ThongKeUI()),
	        			()->taiTrang(new BCNhaCungCapUI())
	        		}
	        		));
		}
		
				
		navBarEast.setLayout(new BoxLayout(navBarEast, BoxLayout.X_AXIS));
        navBarEast.add(new CustomButton("Đổi trả", UIStyles.DoiTraButtonStyle, UIStyles.ReturnIcon, CustomButtonIconSide.LEFT, ()->taiTrang(new DoiTraUI())));
        navBarEast.add(new CustomButton("Bán hàng", UIStyles.BanHangButtonStyle, UIStyles.SellIcon, CustomButtonIconSide.LEFT, ()->taiTrang(new BanHangUI())));

        navBar.setLayout(new BorderLayout());
        navBar.add(navBarWest, BorderLayout.WEST);
        navBar.add(navBarEast, BorderLayout.EAST);
        
        
        mainSection = new TongQuanUI();
        
        topSection.setLayout(new BorderLayout());
        topSection.add(labelBar, BorderLayout.NORTH);
        topSection.add(navBar, BorderLayout.CENTER);
        panel.add(topSection, BorderLayout.NORTH);
        panel.add(mainSection, BorderLayout.CENTER);
        

        add(panel);
        if(toanManHinh) {
        	setExtendedState(JFrame.MAXIMIZED_BOTH); 
            setUndecorated(true);
        }
        
        setVisible(true);
        
	}
	
	public void taiTrang(JPanel trangDich) {	
		//Neu trang dang hien bo qua
		if(trangDich.getClass() == mainSection.getClass()) {
			return;
		}
		//Luu lich su trang
		if(uiHistory.size() >= 10) uiHistory.removeFirst();
		uiHistory.add(mainSection);

		//Sang trang moi
		panel.remove(mainSection);
		mainSection = trangDich;
		panel.add(mainSection, BorderLayout.CENTER);
		panel.revalidate();
		panel.repaint();		
	}
	
	public void quayLai() {
		if(uiHistory.isEmpty()) {
			System.out.println("Khong co lich su");
			return;
		}
		panel.remove(mainSection);
		mainSection = uiHistory.getLast();
		panel.add(mainSection, BorderLayout.CENTER);
		panel.revalidate();
		panel.repaint();
		
		uiHistory.removeLast();
		
	}
	
	private void dangXuat() {
		TrangChuUI.singleton.setVisible(false);
		new DangNhapUI().setVisible(true);
    	
    	
	}
	
	private void doiMatKhau() {
	    // Create a new JFrame for changing password
	    JFrame doiMatKhauFrame = new JFrame("Đổi Mật Khẩu");
	    doiMatKhauFrame.setLayout(new GridLayout(5, 2, 10, 10));
	    doiMatKhauFrame.setSize(500, 300);
	    doiMatKhauFrame.setLocationRelativeTo(null);
	    

	    // Create labels and text fields
	    JLabel lblMatKhauCu = createStyledLabel("Mật khẩu cũ:", 20);
	    JPasswordField txtMatKhauCu = new JPasswordField(20);
	    txtMatKhauCu.setFont(new Font("Tahoma", Font.PLAIN, 20));

	    JLabel lblMatKhauMoi = createStyledLabel("Mật khẩu mới:", 20);
	    JPasswordField txtMatKhauMoi = new JPasswordField(20);
	    txtMatKhauMoi.setFont(new Font("Tahoma", Font.PLAIN, 20));

	    JLabel lblXacNhanMatKhauMoi = createStyledLabel("Xác nhận mật khẩu mới:", 20);
	    JPasswordField txtXacNhanMatKhauMoi = new JPasswordField(20);
	    txtXacNhanMatKhauMoi.setFont(new Font("Tahoma", Font.PLAIN, 20));

	    // Create buttons
	    CustomButton btnDoiMatKhau = new CustomButton("Đổi Mật Khẩu", UIStyles.BanHangButtonStyle,()-> {
	            // Get current account
	            TaiKhoan taiKhoanHienTai = PhanMemQuanLyHieuThuoc.taiKhoanHienTai;

	            // Get password inputs
	            String matKhauCu = TaiKhoanCTR.hashPassword(new String(txtMatKhauCu.getPassword()));
	            String matKhauMoi = TaiKhoanCTR.hashPassword(new String(txtMatKhauMoi.getPassword()));
	            String xacNhanMatKhauMoi = TaiKhoanCTR.hashPassword(new String(txtXacNhanMatKhauMoi.getPassword()));

	            // Validate inputs
	            if (matKhauCu.isEmpty() || matKhauMoi.isEmpty() || xacNhanMatKhauMoi.isEmpty()) {
	                JOptionPane.showMessageDialog(doiMatKhauFrame, 
	                    "Vui lòng điền đầy đủ thông tin!", 
	                    "Lỗi", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            // Check if current password is correct
	            if (!matKhauCu.equals(taiKhoanHienTai.getMatKhau())) {
	            	
	                JOptionPane.showMessageDialog(doiMatKhauFrame, 
	                    "Mật khẩu cũ không chính xác!", 
	                    "Lỗi", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            // Check if new passwords match
	            if (!matKhauMoi.equals(xacNhanMatKhauMoi)) {
	                JOptionPane.showMessageDialog(doiMatKhauFrame, 
	                    "Mật khẩu mới và xác nhận mật khẩu không khớp!", 
	                    "Lỗi", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            // Update password
	            taiKhoanHienTai.setMatKhau(xacNhanMatKhauMoi);
	            try {
	                TaiKhoanCTR.capNhatTaiKhoan(taiKhoanHienTai);
	                JOptionPane.showMessageDialog(doiMatKhauFrame, 
	                    "Đổi mật khẩu thành công!", 
	                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	                doiMatKhauFrame.dispose();
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(doiMatKhauFrame, 
	                    "Lỗi khi cập nhật mật khẩu: " + ex.getMessage(), 
	                    "Lỗi", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    );

	    CustomButton btnHuy = new CustomButton("Hủy", UIStyles.CancelButtonStyle,()-> {
	    	doiMatKhauFrame.dispose();
	    });

	    // Add components to the frame
	    doiMatKhauFrame.add(lblMatKhauCu);
	    doiMatKhauFrame.add(txtMatKhauCu);
	    doiMatKhauFrame.add(lblMatKhauMoi);
	    doiMatKhauFrame.add(txtMatKhauMoi);
	    doiMatKhauFrame.add(lblXacNhanMatKhauMoi);
	    doiMatKhauFrame.add(txtXacNhanMatKhauMoi);
	    doiMatKhauFrame.add(new JLabel()); // Empty label for layout
	    doiMatKhauFrame.add(btnDoiMatKhau);
	    doiMatKhauFrame.add(new JLabel()); // Empty label for layout
	    doiMatKhauFrame.add(btnHuy);

	    // Make the frame visible
	    doiMatKhauFrame.setVisible(true);
	}
	private JLabel createStyledLabel(String text, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
        return label;
    }

    // Helper method to create styled JTextField
    private JTextField createStyledTextField(String text, int columns) {
        JTextField textField = new JTextField(text, columns);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        return textField;
    }

    // Helper method to create styled JButton
    private JButton createStyledButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button.addActionListener(actionListener);
        return button;
    }


}