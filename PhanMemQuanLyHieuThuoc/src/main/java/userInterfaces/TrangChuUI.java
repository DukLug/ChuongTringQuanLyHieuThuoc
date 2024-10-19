package userInterfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
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
import component.CustomComboBox;


public class TrangChuUI extends JFrame {		
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
	
	public TrangChuUI() {
		super();
        lapFrame();
        uiHistory = new Stack<JPanel>();
        taiTrang(new NhanVienUI());
	}
	
	public void lapFrame() {
		if(this != null) setVisible(false);	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.ApplicationHeight)); 
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
        labelBarEast.add(new CustomButton("Cá nhândsfassdfsdfsdffsaf", UIStyles.LabelBarButtonStyle, UIStyles.UserIcon, CustomButtonIconSide.RIGHT, ()->quayLai()));
        
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
        		"Hàng Hóa", 
        		UIStyles.NavBarButtonStyle, 
        		UIStyles.ProductIcon,
        		CustomButtonIconSide.LEFT,
        		UIStyles.NavBarDropBoxItemHeight,
        		new String[]{"Thêm sản phẩm", "Kho hàng"},
        		new CustomButtonFunction[] {
        			()->taiTrang(new NhanVienUI()),
        			()->taiTrang(new NhapHangUI())
        		}
        		));
        
		navBarWest.add(new CustomComboBox(
        		"Giao dịch", 
        		UIStyles.NavBarButtonStyle, 
        		UIStyles.TransferIcon,
        		CustomButtonIconSide.LEFT,
        		UIStyles.NavBarDropBoxItemHeight,
        		new String[]{"Nhập hàng", "Hủy hàng"},
        		new CustomButtonFunction[] {
        			()->taiTrang(new NhapHangUI()),
        			()->taiTrang(new HuyHangUI())
        		}
        		));
		
		navBarWest.add(new CustomComboBox(
        		"Đối tác", 
        		UIStyles.NavBarButtonStyle, 
        		UIStyles.ContactIcon,
        		CustomButtonIconSide.LEFT,
        		UIStyles.NavBarDropBoxItemHeight,
        		new String[]{"Khách hàng", "Nhà cung cấp"},
        		new CustomButtonFunction[] {
        			()->taiTrang(new KhachHangUI()),
        			()->taiTrang(new NhaCungCapUI())
        		}
        		));
		
		navBarWest.add(new CustomButton("Nhân viên", UIStyles.NavBarButtonStyle, UIStyles.EmployeeIcon, CustomButtonIconSide.LEFT, ()->taiTrang(new NhanVienUI())));
        
		navBarWest.add(new CustomComboBox(
        		"Báo cáo", 
        		UIStyles.NavBarButtonStyle, 
        		UIStyles.ReportIcon,
        		CustomButtonIconSide.LEFT,
        		UIStyles.NavBarDropBoxItemHeight,
        		new String[]{"Thống kê hết hạn", "Thống kê sản phẩm"},
        		new CustomButtonFunction[] {
        			()->taiTrang(new NhanVienUI()),
        			()->taiTrang(new NhapHangUI())
        		}
        		));
		
		navBarWest.add(new CustomComboBox(
        		"Thống kê", 
        		UIStyles.NavBarButtonStyle, 
        		UIStyles.AnalysingIcon,
        		CustomButtonIconSide.LEFT,
        		UIStyles.NavBarDropBoxItemHeight,
        		new String[]{"Thống kê hết hạn", "Thống kê sản phẩm"},
        		new CustomButtonFunction[] {
        			()->taiTrang(new NhanVienUI()),
        			()->taiTrang(new NhapHangUI())
        		}
        		));
		navBarWest.add(new CustomComboBox(
        		"Chính sách", 
        		UIStyles.NavBarButtonStyle, 
        		UIStyles.PolicyIcon,
        		CustomButtonIconSide.LEFT,
        		UIStyles.NavBarDropBoxItemHeight,
        		new String[]{"Thống kê hết hạn", "Thống kê sản phẩm"},
        		new CustomButtonFunction[] {
        			()->taiTrang(new NhanVienUI()),
        			()->taiTrang(new NhapHangUI())
        		}
        		));
		
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
        setVisible(true);
        
	}
	


	public void taiTrang(JPanel trangDich) {	
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
	
}
