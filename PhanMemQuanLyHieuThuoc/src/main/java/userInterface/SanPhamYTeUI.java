package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import component.CustomButton;
import component.CustomItem;
import component.CustomItemList;
import component.CustomButton.CustomButtonIconSide;
import controller.SanPhamCTR;
import entity.SanPhamYTe;
import functionalClass.SearchTool;

public class SanPhamYTeUI extends JPanel {
	//data
    private ArrayList<SanPhamYTe> dsSanPham;
   
    private CustomItemList sanPhamYTeList;
    
    private JTextField tenTextField = new JTextField();
    private JTextField nhaSanXuatTextField = new JTextField();
    private JTextField nhaCungCapTextField = new JTextField();
    private JTextField nuocSanXuatTextField = new JTextField();
    private JTextField thanhPhanTextField = new JTextField();
    private JTextField congDungTextField = new JTextField();
    //style
    private static int timKiemPanelPrefWidth = 400;

    public SanPhamYTeUI() {
        super(new BorderLayout()); 
        
        taoHinh();

        capNhatBangSanPham(dsSanPham);
    }
    
    public void taoHinh() {
    	setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
        this.setBackground(UIStyles.BackgroundColor);	
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        //Title
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("SẢN PHẨM Y TẾ");
        title.setFont(UIStyles.TitleFont);
        titlePanel.add(title);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titlePanel.setBackground(UIStyles.BackgroundColor);
        
        //center panel
        //them button panel
        JPanel themButtonPanel = new JPanel();
        themButtonPanel.setLayout(new BorderLayout());
        themButtonPanel.add(new CustomButton("Thêm sản phẩm",UIStyles.ThemButtonStyle,UIStyles.Add,CustomButtonIconSide.LEFT,()->thuCapNhat()), BorderLayout.EAST);
        themButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 35));
        themButtonPanel.setBackground(UIStyles.BackgroundColor);
        
        //muc tim kiem
        JPanel timKiemPanel = new JPanel();
        JLabel timKiemLabel = new JLabel("Tìm sản phẩm");
        timKiemLabel.setFont(UIStyles.TitleFont);
        timKiemPanel.setPreferredSize(new Dimension(500, 800));
        timKiemPanel.setBackground(Color.white);
        timKiemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        timKiemPanel.add(timKiemLabel);
        timKiemPanel.add(addInputGroub("Tên:", tenTextField));
        timKiemPanel.add(addInputGroub("Nhà sản xuất:", nhaSanXuatTextField));
        timKiemPanel.add(addInputGroub("Nước sản xuất:", nuocSanXuatTextField));
        timKiemPanel.add(addInputGroub("Không chứa thành phần:", thanhPhanTextField));
        timKiemPanel.add(addInputGroub("Công dụng:", congDungTextField));
        timKiemPanel.add(new CustomButton("Tim",UIStyles.ThemButtonStyle,UIStyles.FindIcon,CustomButtonIconSide.LEFT,()->timSanPham()));
        //Bang san pham
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        
        
        dsSanPham = SanPhamCTR.layDanhSachTatCaSanPham();
        sanPhamYTeList = new CustomItemList(
        	1300, 650, 500, 20, UIStyles.BackgroundColor, 
            new int[] {300, 25, 300, 25, 300, 25, 300, 0}, 
            new ArrayList<>()
        );
        sanPhamYTeList.setBorder(BorderFactory.createEmptyBorder());
        centerPanel.add(themButtonPanel, BorderLayout.NORTH);
        centerPanel.add(timKiemPanel, BorderLayout.WEST);
        centerPanel.add(sanPhamYTeList,BorderLayout.CENTER);
        
        
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
    }

    private void capNhatBangSanPham(ArrayList<SanPhamYTe> dsSanPham) {
        ArrayList<CustomItem> bangSp = layBang(dsSanPham);
        sanPhamYTeList.updateList(bangSp);
    }

    private ArrayList<CustomItem> layBang(ArrayList<SanPhamYTe> dsSanPham) {
        ArrayList<CustomItem> bang = new ArrayList<>();
        for (int i = 0; i < dsSanPham.size(); i += 4) { 
            int end = Math.min(i + 4, dsSanPham.size());
            SanPhamYTe[] sanPhamArray = new SanPhamYTe[4];
            for (int j = 0; j < (end - i); j++) {
                sanPhamArray[j] = dsSanPham.get(i + j);
            }
            bang.add((CustomItem)(new SanPhamRow(sanPhamArray)));
        }
        return bang;
    }
    
    private void thuCapNhat() {
    	dsSanPham = new ArrayList<SanPhamYTe>();
    	ArrayList<SanPhamYTe> dsTong = SanPhamCTR.layDanhSachTatCaSanPham();
    	 
    	for(int i = 0; i < dsTong.size()/2; i++) {
    		dsSanPham.add(dsTong.get(i));
    	}
    	
    	capNhatBangSanPham(dsSanPham);
    }
    
    private void timSanPham() {
	    ArrayList<Object> searchFields = new ArrayList<>();
	    ArrayList<SearchTool.SearchCondition> conditions = new ArrayList<>();
//	    private String maSanPham;
	    searchFields.add(null);  
	    conditions.add(SearchTool.SearchCondition.NONCONDITION); 
//	    private String tenSanPham;
	    searchFields.add(tenTextField.getText()); 
	    conditions.add(SearchTool.SearchCondition.INCLUDE); 
//	    private Date ngaySanXuat;
	    searchFields.add(null); 
	    conditions.add(SearchTool.SearchCondition.NONCONDITION); 
//	    private Date hanSuDung;
	    searchFields.add(null); 
	    conditions.add(SearchTool.SearchCondition.NONCONDITION); 
//	    private String nuocSanXuat;
	    searchFields.add(null); 
	    conditions.add(SearchTool.SearchCondition.NONCONDITION); 
//	    private TrangThaiSanPham trangThaiSanPham;
	    searchFields.add(null); 
	    conditions.add(SearchTool.SearchCondition.NONCONDITION); 
//	    private String ghiChu;
	    searchFields.add(null); 
	    conditions.add(SearchTool.SearchCondition.NONCONDITION); 
//		private String moTa;
	    searchFields.add(null); 
	    conditions.add(SearchTool.SearchCondition.NONCONDITION); 
//		private double Thue;
	    searchFields.add(null); 
	    conditions.add(SearchTool.SearchCondition.NONCONDITION); 
//		private BufferedImage hinhAnh;
	    searchFields.add(null); 
	    conditions.add(SearchTool.SearchCondition.NONCONDITION); 
//		private String thanhPhan;
	    searchFields.add(null); 
	    conditions.add(SearchTool.SearchCondition.NONCONDITION); 
//		private BigDecimal giaBan;
	    searchFields.add(null); 
	    conditions.add(SearchTool.SearchCondition.NONCONDITION); 
//		private NhaCungCap nhaCungCap;
	    searchFields.add(null); 
	    conditions.add(SearchTool.SearchCondition.NONCONDITION); 
//		private DonViTinh donViTinh;
	    searchFields.add(null); 
	    conditions.add(SearchTool.SearchCondition.NONCONDITION); 
//		private LoaiSanPham loaiSanPham;
	    searchFields.add(null); 
	    conditions.add(SearchTool.SearchCondition.NONCONDITION); 
//		private String maVach;
	    searchFields.add(null); 
	    conditions.add(SearchTool.SearchCondition.NONCONDITION); 
//		private String yeuCauKeDon;
	    searchFields.add(null); 
	    conditions.add(SearchTool.SearchCondition.NONCONDITION); 
//		private String dangBaoChe;
	    searchFields.add(null); 
	    conditions.add(SearchTool.SearchCondition.NONCONDITION); 
//		private String nhaSanXuat;
	    searchFields.add(null); 
	    conditions.add(SearchTool.SearchCondition.NONCONDITION); 
	    
	    capNhatBangSanPham(SanPhamCTR.timSanPham(searchFields, conditions));
    }
    
    private JPanel addInputGroub(String inputTitle, JTextField inputField) {
    	JPanel inputGroub = new JPanel();
    	//inputGroub.setLayout(new BorderLayout());
    	inputGroub.setPreferredSize(new Dimension(500, 90));
    	inputGroub.setBackground(Color.white);
    	inputGroub.setLayout(new BoxLayout(inputGroub, BoxLayout.Y_AXIS));
    	inputGroub.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
    	inputField.setDisabledTextColor(Color.BLACK);
    	inputField.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	inputField.setBounds(177, 115, 298, 30);
		inputField.setColumns(10);
		inputField.setBorder(new LineBorder(Color.BLACK, 1)); 
		inputField.setPreferredSize(new Dimension(350, 45));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(150, 45));
		titlePanel.setLayout(new BorderLayout());
		JLabel title = new JLabel(inputTitle);
		title.setFont(new Font("Tahoma", Font.PLAIN, 20));
		title.setBounds(42, 162, 86, 25);
		title.setBackground(Color.white);
		title.setOpaque(true);
		titlePanel.add(title, BorderLayout.WEST);
		titlePanel.setBackground(Color.white);
		
		inputGroub.add(titlePanel);
		inputGroub.add(inputField);
    	return inputGroub;
    }

    public static class SanPhamRow extends CustomItem {
        private static final int prefWidth = 1300;
        private static final int prefHeight = 500;
        private static final Color backgroundColor = UIStyles.BackgroundColor;
        private static final Border border = BorderFactory.createEmptyBorder();
        private static final int[] cellsWidth = {300, 25, 300, 25, 300, 25, 300, 0};

        private JComponent[] cells;
        private SanPhamYTe[] sanPham;

        public SanPhamRow(SanPhamYTe[] sanPham) {
            super(prefWidth, prefHeight, backgroundColor, border, cellsWidth);
            this.sanPham = sanPham;
            
            cells = new JComponent[8];
            for (int i = 0; i < cells.length; i+=2) {
                cells[i] = sanPham[i/2] != null ? new SanPhamCell(sanPham[i/2]) : new JPanel(); 
                cells[i+1] = new JPanel();
            }
            super.addCells(cells);
        }
    }

    public static class SanPhamCell extends JPanel {
    	
        private static final int prefWidth = 300;
        private static final int prefHeight = 200;
        private static final Color backgroundColor = Color.white;
        private static final Font nameLabelFont = new Font("Tahoma", Font.BOLD, 18);
        private static final Font priceLabelFont = new Font("Tahoma", Font.PLAIN, 16);
        private static final Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        public SanPhamCell(SanPhamYTe sanPham) {
            super(new BorderLayout());
            setPreferredSize(new Dimension(prefWidth, prefHeight));
            this.setBackground(backgroundColor);
            this.setBorder(border);

            // Image panel
            JPanel imagePanel = new JPanel();
            JLabel hinhAnh = new JLabel(new ImageIcon(
                sanPham.getHinhAnh().getScaledInstance(prefWidth, prefWidth, Image.SCALE_SMOOTH)
            ));
            imagePanel.add(hinhAnh);
            imagePanel.setBackground(backgroundColor);

            // Description panel
            JPanel descriptionPanel = new JPanel(new GridLayout(2, 1));
            descriptionPanel.setPreferredSize(new Dimension(prefWidth - 50, prefHeight - prefWidth - 50));

            JLabel nameLabel = new JLabel("<HTML>" + sanPham.getTenSanPham() + "</HTML>");
            nameLabel.setFont(nameLabelFont);
            JLabel priceLabel = new JLabel(sanPham.getMaVach() + "đ");
            priceLabel.setFont(priceLabelFont);

            descriptionPanel.add(nameLabel);
            descriptionPanel.add(priceLabel);
            descriptionPanel.setBackground(backgroundColor);

            // Add panels to the main cell
            this.add(imagePanel, BorderLayout.NORTH);
            this.add(descriptionPanel, BorderLayout.CENTER);

            // Add mouse click listener for debugging
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TrangChuUI.singleton.taiTrang(new ChiTietSanPhamUI(sanPham));;
                }
            });
        }
    }
}
