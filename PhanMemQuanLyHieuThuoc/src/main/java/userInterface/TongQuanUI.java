package userInterface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.GanttRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;

import component.CustomButton;
import component.CustomItem;
import component.CustomItemList;
import component.CustomTable;
import component.RoundedBorder;
import controller.SanPhamCTR;
import dao.DonDoiTraDAO;
import dao.HoaDonDAO;
import dao.KhuyenMaiDAO;
import dao.SanPhamYTeDAO;
import entity.ChiTietHoaDon;
import entity.DonDoiTra;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;
import entity.SanPhamYTe;
import functionalClass.ImageLoader;
import testEntity.Thuoc;
import component.CustomButton.ButtonStyle;
import component.CustomButton.CustomButtonIconSide;

public class TongQuanUI extends JPanel{
	//value properties
	public JLabel soHoaDonTrongNgay;
	public JLabel soDoiTraTrongNgay;
	public JLabel duLieuThongKeCungKy;
	public ArrayList<SanPhamYTe> dsBanChay;
	
	public CustomItemList sanPhamBanChayList;
	
	//Quy
	private KhuyenMaiDAO thongKe = null;
	
	//style properties
	private int padding =  20;
	private int gap = 20;
	private int centerPanelWidth = (int)((UIStyles.ApplicationWidth - 2 * padding) * 0.7);
	private int rightPanelWidth = (int)((UIStyles.ApplicationWidth - 2 * padding) * 0.3);
	private int bigPanelHeight = UIStyles.ApplicationHeight - padding * 2;
	private int centerPanelComponentWidth;
	private int duLieuPanelGap = 40;
	private int labelMargin = 20;
	public TongQuanUI() {
		super();
		setStyle();
		taoHinh();
//		capNhatDanhSachBanChay(SanPhamYTeDAO.layDanhSachTatCaSanPham());
		
	}
	
	private void setStyle() {
		padding =  20;
		gap = 20;
		centerPanelWidth = (int)((UIStyles.ApplicationWidth - 2 * padding) * 0.7);
		rightPanelWidth = UIStyles.ApplicationWidth - 2 * padding - centerPanelWidth;
		bigPanelHeight = UIStyles.ApplicationHeight - padding * 2;
		centerPanelComponentWidth = centerPanelWidth - gap;
	}

	public void taoHinh() {
		this.thongKe = new KhuyenMaiDAO();
		setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
		this.setBackground(UIStyles.BackgroundColor);
		this.add(new JLabel("TongQuanUI"));
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
		/*
        scrollPane.getViewport().setBackground(Color.blue);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.red;
            }
        });
        scrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
        scrollPane.setPreferredSize(new Dimension(800, 500));
        */
	    int soHoaDon = laySoHoaDon(); // Giả sử bạn có phương thức này
	    int soDoiTra = laySoHoaDonTra(); // Sử dụng phương thức mới
	    BigDecimal tongDoanhThu = layTongDoanhThu(); 
		//Right Panel
	    JPanel rightPanel = new JPanel();
	    rightPanel.setPreferredSize(new Dimension(rightPanelWidth, bigPanelHeight));
	    rightPanel.setMinimumSize(new Dimension(rightPanelWidth, bigPanelHeight));
	    rightPanel.setBackground(Color.white);
	    
	    //Center Panel
	    JPanel centerPanel = new JPanel();
	    centerPanel.setPreferredSize(new Dimension(centerPanelWidth, bigPanelHeight));
	    centerPanel.setMaximumSize(new Dimension(centerPanelWidth, bigPanelHeight));

	    centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, gap));
	    
	    //ket qua hom nay
	    int todayResultPanelHeight = 350;
	    JPanel todayResultPanel = new JPanel();
	    todayResultPanel.setPreferredSize(new Dimension(centerPanelComponentWidth, todayResultPanelHeight));
	    todayResultPanel.setMinimumSize(new Dimension(centerPanelComponentWidth, todayResultPanelHeight));
	    todayResultPanel.setBackground(Color.white);
	    
	    JLabel todayResultPanelTitle = new JLabel("KẾT QUẢ HÔM NAY");
	    todayResultPanelTitle.setFont(UIStyles.TitleFont);
	    todayResultPanelTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
	    todayResultPanelTitle.setBorder(BorderFactory.createMatteBorder(labelMargin, labelMargin, labelMargin, labelMargin, Color.white));
	    
	    JPanel duLieuPanel = new JPanel();
	    duLieuPanel.setPreferredSize(new Dimension(centerPanelComponentWidth, todayResultPanelHeight - 100));
	    duLieuPanel.setMinimumSize(new Dimension(centerPanelComponentWidth, todayResultPanelHeight - 100));
	    duLieuPanel.setBackground(Color.white);
	    duLieuPanel.setLayout(new BoxLayout(duLieuPanel, BoxLayout.X_AXIS));
	    duLieuPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
	    
	    
	    //thong ke hoa don
	    JLabel moneyIcon = new JLabel(UIStyles.MoneyIcon);
	    JPanel thongKeHoaDon = new JPanel();
	    thongKeHoaDon.setLayout(new BoxLayout(thongKeHoaDon, BoxLayout.Y_AXIS));
	    
	    
	    soHoaDonTrongNgay = new JLabel("10", SwingConstants.CENTER);
	    soHoaDonTrongNgay.setFont(UIStyles.BoldFont);
	    soHoaDonTrongNgay.setAlignmentX(Component.CENTER_ALIGNMENT);
	    JLabel hoaDonLabel = new JLabel("Hóa đơn", SwingConstants.CENTER);
	    hoaDonLabel.setFont(UIStyles.BoldFont);
	    hoaDonLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    thongKeHoaDon.add(soHoaDonTrongNgay);
	    thongKeHoaDon.add(hoaDonLabel);
	    thongKeHoaDon.setBackground(Color.white);
	    //thong ke doi tra
	    JLabel returnProductIcon = new JLabel(UIStyles.ReturnProductIcon);
	    JPanel thongKeDoiTra = new JPanel();
	    thongKeDoiTra.setLayout(new BoxLayout(thongKeDoiTra, BoxLayout.Y_AXIS));
	    
	    
	    
	    soDoiTraTrongNgay = new JLabel("1", SwingConstants.CENTER);
	    soDoiTraTrongNgay.setFont(UIStyles.BoldFont);
	    soDoiTraTrongNgay.setAlignmentX(Component.CENTER_ALIGNMENT);
	    JLabel soDoiTraTrongNgayLabel = new JLabel("Đổi trả", SwingConstants.CENTER);
	    soDoiTraTrongNgayLabel.setFont(UIStyles.BoldFont);
	    soDoiTraTrongNgayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    thongKeDoiTra.add(soDoiTraTrongNgay);
	    thongKeDoiTra.add(soDoiTraTrongNgayLabel);
	    thongKeDoiTra.setBackground(Color.white);
	    //thong ke cung ky
	    JLabel changeIcon = new JLabel(UIStyles.IncreaseIcon);
	    JPanel thongKeCungKy = new JPanel();
	    thongKeCungKy.setLayout(new BoxLayout(thongKeCungKy, BoxLayout.Y_AXIS));
	    


	    // Cập nhật giao diện với số liệu
	    soHoaDonTrongNgay.setText(String.valueOf(soHoaDon));
	    soDoiTraTrongNgay.setText(String.valueOf(soDoiTra));
	    
	    duLieuThongKeCungKy = new JLabel("1", SwingConstants.CENTER);
	    duLieuThongKeCungKy.setFont(UIStyles.BoldFont);
	    duLieuThongKeCungKy.setAlignmentX(Component.CENTER_ALIGNMENT);
	    JLabel duLieuThongKeCungKyLabel = new JLabel("VNĐ", SwingConstants.CENTER);
	    duLieuThongKeCungKyLabel.setFont(UIStyles.BoldFont);
	    duLieuThongKeCungKyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    thongKeCungKy.add(duLieuThongKeCungKy);
	    thongKeCungKy.add(duLieuThongKeCungKyLabel);
	    thongKeCungKy.setBackground(Color.white);
	    duLieuThongKeCungKy.setText(tongDoanhThu.toString());
	    
	    //separator
	    JSeparator duLieuPanelSeparator1 = new JSeparator(SwingConstants.VERTICAL);
	    duLieuPanelSeparator1.setBackground(Color.GRAY);
	    Dimension size = new Dimension(duLieuPanelSeparator1.getPreferredSize().width,duLieuPanelSeparator1.getMaximumSize().height);
	    duLieuPanelSeparator1.setMaximumSize(size);
	    
	    JSeparator duLieuPanelSeparator2 = new JSeparator(SwingConstants.VERTICAL);
	    duLieuPanelSeparator2.setBackground(Color.GRAY);
	    duLieuPanelSeparator2.setMaximumSize(size);
	    //hoan thanh panel thong ke

	    duLieuPanel.add(Box.createRigidArea(new Dimension(duLieuPanelGap, duLieuPanelGap)));
	    duLieuPanel.add(moneyIcon);
	    duLieuPanel.add(Box.createRigidArea(new Dimension(duLieuPanelGap, duLieuPanelGap)));
	    duLieuPanel.add(thongKeHoaDon);
	    duLieuPanel.add(Box.createRigidArea(new Dimension(duLieuPanelGap * 2, duLieuPanelGap)));
	    duLieuPanel.add(duLieuPanelSeparator1);
	    duLieuPanel.add(Box.createRigidArea(new Dimension(duLieuPanelGap * 2, duLieuPanelGap)));
	    duLieuPanel.add(returnProductIcon);
	    duLieuPanel.add(Box.createRigidArea(new Dimension(duLieuPanelGap, duLieuPanelGap)));
	    duLieuPanel.add(thongKeDoiTra);
	    duLieuPanel.add(Box.createRigidArea(new Dimension(duLieuPanelGap * 2, duLieuPanelGap)));
	    duLieuPanel.add(duLieuPanelSeparator2);
	    duLieuPanel.add(Box.createRigidArea(new Dimension(duLieuPanelGap , duLieuPanelGap)));
	    duLieuPanel.add(changeIcon);
	    duLieuPanel.add(Box.createRigidArea(new Dimension(duLieuPanelGap, duLieuPanelGap)));
	    duLieuPanel.add(thongKeCungKy);
	    
	    todayResultPanel.setLayout(new BorderLayout());
	    todayResultPanel.setBorder(BorderFactory.createMatteBorder(0, 0, gap, 0, UIStyles.BackgroundColor));
	    todayResultPanel.add(todayResultPanelTitle, BorderLayout.NORTH);
	    todayResultPanel.add(duLieuPanel, BorderLayout.CENTER);	    
	    
	 // Unknown Panel
	    JPanel unknownPanel = new JPanel();
	    unknownPanel.setPreferredSize(new Dimension(centerPanelComponentWidth, bigPanelHeight - todayResultPanelHeight));
	    unknownPanel.setBackground(Color.white);

	    JLabel ThongKeLabel = new JLabel("BIỂU ĐỒ DOANH THU NGÀY", SwingConstants.CENTER);
	    ThongKeLabel.setFont(UIStyles.TitleFont);
	    unknownPanel.add(ThongKeLabel);

	    // Tạo biểu đồ doanh thu
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    Object[][] data = HoaDonDAO.layDanhSachHoaDonBanHangTheoNgay(); // Lấy dữ liệu từ DAO

	    for (Object[] row : data) {
	        String ngay = row[1].toString();
	        BigDecimal doanhThu = (BigDecimal) row[3];
	        dataset.addValue(doanhThu, "Doanh thu", ngay);
	    }

	    JFreeChart chart = ChartFactory.createBarChart(
	            "",
	            "Ngày",
	            "Doanh thu",
	            dataset
	    );
	    CategoryPlot plot = chart.getCategoryPlot();
	    BarRenderer renderer = (BarRenderer) plot.getRenderer();

	    renderer.setMaximumBarWidth(0.05); 
	    ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new Dimension(centerPanelComponentWidth, 400)); // Điều chỉnh kích thước biểu đồ
	    unknownPanel.add(chartPanel);

	    // Thiết lập rightPanel
	    JLabel sanPhamBanChayLabel = new JLabel("SẢN PHẨM BÁN CHẠY", SwingConstants.CENTER);
	    sanPhamBanChayLabel.setFont(UIStyles.TitleFont);
	    
	    CustomItemList sanPhamBanChayList = new CustomItemList(
	            500, 
	            700, 
	            100, 
	            50, 
	            Color.white, 
	            new int[]{100, 400}, 
	            new ArrayList<>()
	    );
	    
	    
	    
	    rightPanel.setLayout(new BorderLayout());
	    rightPanel.add(sanPhamBanChayLabel, BorderLayout.NORTH);
	    rightPanel.add(sanPhamBanChayList, BorderLayout.CENTER);
	    
	    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
	    centerPanel.add(todayResultPanel);
	    centerPanel.add(unknownPanel);
	    
	    this.add(rightPanel, BorderLayout.EAST);
	    this.add(centerPanel, BorderLayout.CENTER);
	    

	    
	}
	


//	
	private int laySoHoaDon() {
	    // Lấy dữ liệu từ DAO
	    Object[][] data = HoaDonDAO.layDanhSachHoaDonBanHangTheoNgay();
	    return data.length; // Trả về số lượng hóa đơn
	}
	
	private BigDecimal layTongDoanhThu() {
	    // Lấy dữ liệu từ DAO
	    Object[][] data = HoaDonDAO.layDanhSachHoaDonBanHangTheoNgay();
	    BigDecimal totalRevenue = BigDecimal.ZERO;

	    // Tính tổng doanh thu
	    for (Object[] row : data) {
	        BigDecimal thanhTien = (BigDecimal) row[3]; // Giả sử cột thứ 4 là thanh tiền
	        totalRevenue = totalRevenue.add(thanhTien);
	    }

	    return totalRevenue; // Trả về tổng doanh thu
	}
	
	private int laySoHoaDonTra() {
	    // Lấy dữ liệu từ DAO
	    Object[][] data = DonDoiTraDAO.layDanhSachHoaDonDoiTraCuoiNgay();
	    List<DonDoiTra> hoadondtlist = new ArrayList<>();

	    // Chuyển đổi dữ liệu từ Object[][] thành List<DonDoiTra>
	    for (Object[] row : data) {
	        String maHoaDon = (String) row[0];
	        Date ngayDoiTra = (Date) row[1];
	        BigDecimal tienHoan = (BigDecimal) row[2];
	        NhanVien nv = new NhanVien((String) row[3]);
	        KhuyenMai km = new KhuyenMai((String) row[4]);
	        KhachHang kh = new KhachHang((String) row[5]);
	        HoaDon hd = new HoaDon((String) row[6]);
	        
	        DonDoiTra ddt = new DonDoiTra(maHoaDon, ngayDoiTra, tienHoan, nv, km, kh, hd);
	        hoadondtlist.add(ddt);
	    }

	    return hoadondtlist.size(); // Trả về số lượng hóa đơn đổi trả
	}
	
	
	


//	private void capNhatDanhSachBanChay(ArrayList<SanPhamYTe> dsBanChay) {
//		for(int i = 0; i < dsBanChay.size()*0; i++) {
//			sanPhamBanChayList.addItem(new SanPhamBanChayRow(dsBanChay.get(i)));
//		}
//
//	}
//	
//	public static class SanPhamBanChayRow extends CustomItem{
//		//Item, ItemList chỉ phụ trách thể hiện thông tin, thông tin lưu ở các class con giống như NhapHangRow này
//		//Các thuộc tính style, code thẳng vào, phải có static, cop về chỉnh lại cũng được
//		private static int prefWidth = 500;
//		private static int prefHeight = 100;
//		private static Font font = UIStyles.DefaultFont;
//		private static Color backgroundColor = Color.white;
//		private static int infoMargin = 5;
//		private static Font titleFont = new Font("Tahoma", Font.BOLD, 18);	
//		private static Font priceFont = new Font("Tahoma", Font.BOLD, 16);	
//		//Rounded border phức tạp, có thể phá panel bên trong, bán kính càng lớn càng dễ hỏng
//		private static Border border = BorderFactory.createEmptyBorder();
//		//Chú ý nên match với lúc tạo ItemList
//		private static int[] cellsWidth = new int[] {100, 400};
//		
//		//cells
//		private JComponent[] cells;
//		
//		//Du lieu item
//		public SanPhamYTe sanPham;	
//		
//		//Các cell thêm vào đây rồi set phía dưới giống các ui, để public để sau đó lấy dữ liệu, set dữ liệu
//		public JLabel tenSanPham;
//		public JLabel giaSanPham;
//		
//		//Thêm nội dung cho các trường đã khai báo ở trên vào constructor, có stt mới thêm stt vào, không thì chỉ cần data
//		public SanPhamBanChayRow(SanPhamYTe sanPham) {
//			//Tạo item rỗng, trên cop thì ở đây  cop
//			super(prefWidth, prefHeight, backgroundColor, border, cellsWidth);
//			
//			//Set dữ liệu
//			this.sanPham = sanPham;
//
//			JPanel cellImage = new JPanel();
//			Image img = sanPham.getHinhAnh().getScaledInstance(100, 100,  Image.SCALE_SMOOTH);
//			JLabel hinhAnh = new JLabel(new ImageIcon(img));
//			cellImage.add(hinhAnh);
//			cellImage.setBackground(backgroundColor);
//			
//			JPanel cellInfo = new JPanel();
//			//cellImage.setPreferredSize(new Dimension(prefWidth, prefHeight));
//			tenSanPham = new JLabel("<html>"+ sanPham.getTenSanPham() +"</html>");
//			tenSanPham.setFont(titleFont);
//			
//			giaSanPham = new JLabel(sanPham.getGiaBanDonViTinh1().toString() + " đ");
//			giaSanPham.setFont(priceFont);
//			
//			cellInfo.setBackground(backgroundColor);
//			cellInfo.setLayout(new BorderLayout());
//			cellInfo.setBorder(BorderFactory.createEmptyBorder(infoMargin, infoMargin, infoMargin, infoMargin));
//			cellInfo.add(tenSanPham, BorderLayout.CENTER);
//			cellInfo.add(giaSanPham, BorderLayout.SOUTH);
//			
//
//			//Thêm cells vào Item
//			cells = new JComponent[] {
//					cellImage, cellInfo
//			};
//			
//			super.addCells(cells);
//		}
//		
//
//		public void layThongTin() {
//			System.out.println(this.sanPham);
//		}
		

//	}
	

}