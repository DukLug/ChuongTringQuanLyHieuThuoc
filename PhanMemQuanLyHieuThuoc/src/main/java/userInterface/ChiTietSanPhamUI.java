package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import component.CustomButton;
import component.CustomItemList;
import component.CustomButton.CustomButtonIconSide;
import controller.SanPhamCTR;
import entity.SanPhamYTe;

public class ChiTietSanPhamUI extends JPanel{
	private SanPhamYTe sanPham;
	
	public ChiTietSanPhamUI(SanPhamYTe sanPham) {
		super();
		if(sanPham == null) {
			throw new IllegalArgumentException("SanPhamYTe is null");
		}
		this.sanPham = sanPham;
		taoHinh(sanPham);
	}
	
    public void taoHinh(SanPhamYTe sanPham) {
    	setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
        this.setBackground(UIStyles.BackgroundColor);	
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        //Title
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("CHI TIẾT SẢN PHẨM");
        title.setFont(UIStyles.TitleFont);
        titlePanel.add(title);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titlePanel.setBackground(UIStyles.BackgroundColor);
        
        //center panel
        //them button panel
        JPanel themButtonPanel = new JPanel();
        themButtonPanel.setLayout(new BorderLayout());
        //themButtonPanel.add(new CustomButton("Thêm sản phẩm",UIStyles.ThemButtonStyle,UIStyles.Add,CustomButtonIconSide.LEFT,()->thuCapNhat()), BorderLayout.EAST);
        themButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 35));
        themButtonPanel.setBackground(UIStyles.BackgroundColor);
        
        // Image panel
        JPanel imagePanel = new JPanel();
        JLabel hinhAnh = new JLabel(new ImageIcon(
            sanPham.getHinhAnh().getScaledInstance(500, 500, Image.SCALE_SMOOTH)
        ));
        imagePanel.add(hinhAnh);
        imagePanel.setBackground(UIStyles.BackgroundColor);
        imagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //timKiemPanel.add(new CustomButton("Tim",UIStyles.ThemButtonStyle,UIStyles.FindIcon,CustomButtonIconSide.LEFT,()->timSanPham()));
        //Thong tin san pham
        JPanel inforPanel = new JPanel();
        inforPanel.setBackground(Color.white);
        inforPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        inforPanel.add(inforGroup("Tên sản phẩm:", sanPham.getTenSanPham()));
        inforPanel.add(inforGroup("Nhà sản xuất:", sanPham.getNhaSanXuat().toString()));
        inforPanel.add(inforGroup("Giá:", sanPham.getGiaBan().toString() + "đ"));
        inforPanel.add(inforGroup("Công dụng", sanPham.getMoTa()));
        inforPanel.add(inforGroup("Hạn sử dụng", sanPham.getHanSuDung().toString()));
        
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(themButtonPanel, BorderLayout.NORTH);
        centerPanel.add(imagePanel, BorderLayout.WEST);
        centerPanel.add(inforPanel, BorderLayout.CENTER);
        
        
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
    }
    
    public JPanel inforGroup(String contentTitle, String content) {
    	JPanel inputGroub = new JPanel();
    	//inputGroub.setLayout(new BorderLayout());
    	inputGroub.setPreferredSize(new Dimension(500, 90));
    	inputGroub.setBackground(Color.white);
    	inputGroub.setLayout(new BoxLayout(inputGroub, BoxLayout.Y_AXIS));
    	inputGroub.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
    	JTextField contentField = new JTextField();
    	contentField.setDisabledTextColor(Color.BLACK);
    	contentField.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	contentField.setBounds(177, 115, 298, 30);
    	contentField.setColumns(10);
    	contentField.setBorder(new LineBorder(Color.BLACK, 1)); 
    	contentField.setPreferredSize(new Dimension(350, 45));
    	contentField.setEditable(false);
    	contentField.setText(content);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(150, 45));
		titlePanel.setLayout(new BorderLayout());
		JLabel title = new JLabel(contentTitle);
		title.setFont(new Font("Tahoma", Font.PLAIN, 20));
		title.setBounds(42, 162, 86, 25);
		title.setBackground(Color.white);
		title.setOpaque(true);
		titlePanel.add(title, BorderLayout.WEST);
		titlePanel.setBackground(Color.white);
		
		inputGroub.add(titlePanel);
		inputGroub.add(contentField);
    	return inputGroub;
    }

}