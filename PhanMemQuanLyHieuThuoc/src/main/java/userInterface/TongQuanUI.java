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
import java.sql.Date;
import java.util.ArrayList;

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
import org.jfree.chart.renderer.category.GanttRenderer;
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
import dao.KhuyenMaiDAO;
import entity.KhuyenMai;
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
		capNhatDanhSachBanChay(SanPhamCTR.layDanhSachTatCaSanPham());
		
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
	    
	    
	    duLieuThongKeCungKy = new JLabel("15.12%", SwingConstants.CENTER);
	    duLieuThongKeCungKy.setFont(UIStyles.BoldFont);
	    duLieuThongKeCungKy.setAlignmentX(Component.CENTER_ALIGNMENT);
	    JLabel duLieuThongKeCungKyLabel = new JLabel("So với cùng kỳ tháng trước", SwingConstants.CENTER);
	    duLieuThongKeCungKyLabel.setFont(UIStyles.BoldFont);
	    duLieuThongKeCungKyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    thongKeCungKy.add(duLieuThongKeCungKy);
	    thongKeCungKy.add(duLieuThongKeCungKyLabel);
	    thongKeCungKy.setBackground(Color.white);
	    
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
	    
	    //unknown Panel
	    JPanel unknownPanel = new JPanel();
	    unknownPanel.setPreferredSize(new Dimension(centerPanelComponentWidth, bigPanelHeight - todayResultPanelHeight));
	    unknownPanel.setBackground(Color.white);
	    JLabel ThongKeLabel = new JLabel("BIỂU ĐỒ THEO DÕI THỜI GIAN KHUYẾN MÃI", SwingConstants.CENTER);
	    ThongKeLabel.setFont(UIStyles.TitleFont);
	    ThongKeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    unknownPanel.add(ThongKeLabel);
	    JPanel bieuDo = new JPanel();
	    unknownPanel.add(bieuDo);
	    setDataToChart2(bieuDo);
	    unknownPanel.setLayout(new BoxLayout(unknownPanel, BoxLayout.Y_AXIS));
	    //san pham ban chay 
	    JLabel sanPhamBanChayLabel = new JLabel("SẢN PHẨM BÁN CHẠY", SwingConstants.CENTER);
	    sanPhamBanChayLabel.setFont(UIStyles.TitleFont);
	    sanPhamBanChayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    sanPhamBanChayLabel.setBorder(BorderFactory.createEmptyBorder(labelMargin, labelMargin, labelMargin, labelMargin));
	    
	    sanPhamBanChayList = new CustomItemList(
	            500, 
	            700, 
	            100, 
	            50, 
	            Color.white, 
	            new int[]{100, 400}, 
	            new ArrayList<CustomItem>()
	        );
	    
	    rightPanel.setBackground(Color.white);
	    rightPanel.setLayout(new BorderLayout());
	    rightPanel.add(sanPhamBanChayLabel, BorderLayout.NORTH);
	    rightPanel.add(sanPhamBanChayList, BorderLayout.CENTER);
	    
	    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
	    centerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, gap, UIStyles.BackgroundColor));
	    centerPanel.add(todayResultPanel);
	    centerPanel.add(unknownPanel);
	    centerPanel.setBackground(Color.white);
	    
	    this.add(rightPanel, BorderLayout.EAST);
	    this.add(centerPanel, BorderLayout.CENTER);
	    
	}
	
	public void setDataToChart2(JPanel jpnItem) {
		
	    ArrayList<KhuyenMai> listItem = thongKe.layDanhSachTatCaKhuyenMai();


	    TaskSeriesCollection ds = new TaskSeriesCollection();
	    JFreeChart ganttChart = ChartFactory.createGanttChart(
	            null,
	            "Khuyến mãi", "Thời gian", ds, true, false, false
	    );

	    TaskSeries taskSeries;
	    Task task;

	    if (listItem != null) {
	        int limit = Math.min(5, listItem.size()); // Giới hạn số lượng phần tử là 5
	        for (int i = 0; i < limit; i++) {
	            KhuyenMai item = listItem.get(i);
	            taskSeries = new TaskSeries(item.getMaKhuyenMai());
	            task = new Task(item.getMaKhuyenMai(),
	                    new SimpleTimePeriod(
	                        new Date(item.getNgayKhuyenMai().getTime()),
	                        new Date(item.getNgayKetThuc().getTime())
	                    )
	            );	            
	            taskSeries.add(task);
	            ds.add(taskSeries);
	        }
	    }
	    CategoryPlot plot = (CategoryPlot) ganttChart.getPlot();
	    GanttRenderer renderer = new GanttRenderer();
	    renderer.setMaximumBarWidth(0.3); // Tăng giá trị để các thanh dày hơn
	    plot.setRenderer(renderer); 
//	    ganttChart.getTitle().setFont(UIStyles.TitleFont); // font title
	    ChartPanel chartPanel = new ChartPanel(ganttChart);
	    chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 321));

	    jpnItem.removeAll();
	    jpnItem.setLayout(new CardLayout());
	    jpnItem.add(chartPanel);
	    jpnItem.validate();
	    jpnItem.repaint();
	}


	private void capNhatDanhSachBanChay(ArrayList<SanPhamYTe> dsBanChay) {
		for(int i = 0; i < 20; i++) {
			sanPhamBanChayList.addItem(new SanPhamBanChayRow(dsBanChay.get(i)));
		}
	}
	
	public static class SanPhamBanChayRow extends CustomItem{
		//Item, ItemList chỉ phụ trách thể hiện thông tin, thông tin lưu ở các class con giống như NhapHangRow này
		//Các thuộc tính style, code thẳng vào, phải có static, cop về chỉnh lại cũng được
		private static int prefWidth = 500;
		private static int prefHeight = 100;
		private static Font font = UIStyles.DefaultFont;
		private static Color backgroundColor = Color.white;
		private static int infoMargin = 5;
		private static Font titleFont = new Font("Tahoma", Font.BOLD, 18);	
		private static Font priceFont = new Font("Tahoma", Font.BOLD, 16);	
		//Rounded border phức tạp, có thể phá panel bên trong, bán kính càng lớn càng dễ hỏng
		private static Border border = BorderFactory.createEmptyBorder();
		//Chú ý nên match với lúc tạo ItemList
		private static int[] cellsWidth = new int[] {100, 400};
		
		//cells
		private JComponent[] cells;
		
		//Du lieu item
		public SanPhamYTe sanPham;	
		
		//Các cell thêm vào đây rồi set phía dưới giống các ui, để public để sau đó lấy dữ liệu, set dữ liệu
		public JLabel tenSanPham;
		public JLabel giaSanPham;
		
		//Thêm nội dung cho các trường đã khai báo ở trên vào constructor, có stt mới thêm stt vào, không thì chỉ cần data
		public SanPhamBanChayRow(SanPhamYTe sanPham) {
			//Tạo item rỗng, trên cop thì ở đây  cop
			super(prefWidth, prefHeight, backgroundColor, border, cellsWidth);
			
			//Set dữ liệu
			this.sanPham = sanPham;

			JPanel cellImage = new JPanel();
			Image img = sanPham.getHinhAnh().getScaledInstance(100, 100,  Image.SCALE_SMOOTH);
			JLabel hinhAnh = new JLabel(new ImageIcon(img));
			cellImage.add(hinhAnh);
			cellImage.setBackground(backgroundColor);
			
			JPanel cellInfo = new JPanel();
			//cellImage.setPreferredSize(new Dimension(prefWidth, prefHeight));
			tenSanPham = new JLabel("<html>"+ sanPham.getTenSanPham() +"</html>");
			tenSanPham.setFont(titleFont);
			
			giaSanPham = new JLabel(sanPham.getGiaBan().toString() + " đ");
			giaSanPham.setFont(priceFont);
			
			cellInfo.setBackground(backgroundColor);
			cellInfo.setLayout(new BorderLayout());
			cellInfo.setBorder(BorderFactory.createEmptyBorder(infoMargin, infoMargin, infoMargin, infoMargin));
			cellInfo.add(tenSanPham, BorderLayout.CENTER);
			cellInfo.add(giaSanPham, BorderLayout.SOUTH);
			

			//Thêm cells vào Item
			cells = new JComponent[] {
					cellImage, cellInfo
			};
			
			super.addCells(cells);
		}
		

		public void layThongTin() {
			System.out.println(this.sanPham);
		}
		

	}
	

}
