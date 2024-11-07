package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.sql.Date;
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
import customDataType.DonViTinh;
import customDataType.TrangThaiSanPham;
import entity.LoaiSanPham;
import entity.NhaCungCap;
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
        inforPanel.setPreferredSize(new Dimension(1000, 700));
//    	private String tenSanPham;
        inforPanel.add(inforGroup("Tên sản phẩm:", sanPham.getTenSanPham()));
//    	private Date ngaySanXuat;
        inforPanel.add(inforGroup("Ngày sản xuất:", sanPham.getNgaySanXuat().toString()));
//    	private Date hanSuDung;
        inforPanel.add(inforGroup("Hạn sử dụng", sanPham.getHanSuDung().toString()));
//    	private String nuocSanXuat;
        inforPanel.add(inforGroup("Nước sản xuất:", sanPham.getNuocSanXuat().toString()));
//    	private TrangThaiSanPham trangThaiSanPham;
        inforPanel.add(inforGroup("Trạng thái sản phẩm:", sanPham.getTrangThaiSanPham().toString()));
//    	private String ghiChu;
        inforPanel.add(inforGroup("Ghi chú", sanPham.getGhiChu().toString()));
//    	private String moTa;
        //inforPanel.add(inforGroup("Nhà sản xuất:", sanPham.getMoTa().toString()));
//    	private String dangBaoChe;
        inforPanel.add(inforGroup("Dạng bào chế:", sanPham.getDangBaoChe()));
//    	private double Thue;
        inforPanel.add(inforGroup("Thuế:", sanPham.getThue() + "%"));
//    	private BufferedImage hinhAnh;
        //inforPanel.add(inforGroup("Nhà sản xuất:", sanPham.getNhaSanXuat().toString()));
//    	private String thanhPhan;
        inforPanel.add(inforGroup("Thành phần:", sanPham.getMoTa().toString()));
//    	private DonViTinh donViTinh;
        inforPanel.add(inforGroup("Đơn vị tính:", sanPham.getDonViTinh().toString()));
//    	private NhaCungCap nhaCungCap;
        inforPanel.add(inforGroup("Nhà cung cấp:", sanPham.getNhaCungCap().toString()));
//    	private LoaiSanPham loaiSanPham;
        inforPanel.add(inforGroup("Loại sản phẩm:", sanPham.getLoaiSanPham().toString()));
//    	private BigDecimal giaBan;
        inforPanel.add(inforGroup("Giá bán:", sanPham.getGiaBan().toString() + "đ"));
//    	private String maVach;
        inforPanel.add(inforGroup("Mã vạch:", sanPham.getMaVach().toString()));
//    	private String yeuCauKeDon;
        inforPanel.add(inforGroup("Yêu cầu kê đơn:", sanPham.getYeuCauKeDon().toString()));
              
        
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
    	inputGroub.setPreferredSize(new Dimension(1000, 50));
    	inputGroub.setLayout(new BoxLayout(inputGroub, BoxLayout.X_AXIS));
    	inputGroub.setBackground(Color.white);
    	//inputGroub.setLayout(new BoxLayout(inputGroub, BoxLayout.Y_AXIS));
    	inputGroub.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
    	JLabel contentField = new JLabel();
    	//contentField.setDisabledTextColor(Color.BLACK);
    	contentField.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	contentField.setBounds(177, 115, 298, 30);
    	//contentField.setColumns(10);
    	contentField.setPreferredSize(new Dimension(700, 50));
    	//contentField.setEditable(false);
    	contentField.setText("<HTML>" + content + "</HTML>");
		
		JPanel titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(300, 50));
		titlePanel.setLayout(new BorderLayout());
		JLabel title = new JLabel(contentTitle);
		title.setFont(new Font("Tahoma", Font.BOLD, 20));
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
