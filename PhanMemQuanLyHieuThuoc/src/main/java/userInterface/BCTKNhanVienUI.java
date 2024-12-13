package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


//import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import component.CustomTable;
import controller.HoaDonCTR;
import dao.HoaDonDAO;
import dao.NhanVienDAO;
import entity.HoaDon;
import entity.NhanVien;



public class BCTKNhanVienUI extends JPanel implements ActionListener, MouseListener {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String[] headers = { "Mã hóa đơn", "Tổng tiền", "Ngày Bán", "Mã Nhân Viên", "Mã Khách Hàng" };
	private JComboBox<Object> dayComboBox;
	private JComboBox<Object> monthComboBox;
	private JComboBox<Object> yearComboBox;
	private JComboBox<Object> nvComboBox = new JComboBox<>();
	private JButton tim;
	private JButton loc;
	private CustomTable tableHoaDon;
	private Object sqlDate;
	private Object[][] data;
	private NhanVienDAO nv_dao;
	private ChartPanel chartPanel;
	private Object[][] filteredData;

	private JLabel lblTongDoanhThu;

	
	public BCTKNhanVienUI() {
		super();
		taoHinh();
		
	}


	private void taoHinh() {
		
		setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
        this.setBackground(Color.white);
        setLayout(null);
        
        JPanel panelTong = new JPanel();
        panelTong.setBackground(UIStyles.BackgroundColor);
        panelTong.setBounds(0, 0, UIStyles.ApplicationWidth, UIStyles.MainSectionHeight);
        add(panelTong);
        panelTong.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1920, 80);
        panelTong.add(panel);
        panel.setLayout(null);

        JLabel lblKhachHang = new JLabel("BÁO CÁO NHÂN VIÊN");
        lblKhachHang.setBounds(819, 20, 258, 29);
        lblKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
        lblKhachHang.setFont(new Font("Tahoma", Font.BOLD, 24));
        panel.add(lblKhachHang);

        JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.white);
		panel_1.setBounds(0, 0, 1020, UIStyles.MainSectionHeight);
		panelTong.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 90, 1000, 400);
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout());

		
		data = new Object[0][headers.length];

		tableHoaDon = new CustomTable(data, headers, UIStyles.NhanVienTableHeaderStyle,
		        UIStyles.NhanVienTableRowStyle, 20);
		JScrollPane scrollPane = new JScrollPane(tableHoaDon);
		panel_3.add(scrollPane, BorderLayout.CENTER);

		data = HoaDonCTR.layDataHD2();
		tableHoaDon.capNhatDuLieu(data);
		
	    


		
		lblTongDoanhThu = new JLabel("Tổng doanh thu: 0");
		lblTongDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTongDoanhThu.setBounds(127, 540, 600, 51); // Điều chỉnh vị trí cho phù hợp
		panel_1.add(lblTongDoanhThu);
		

		JLabel lblNewLabel_1_6 = new JLabel("Ngày bán :");
		lblNewLabel_1_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.BOLD, 18));
//		lblNewLabel_1_6.setBounds(310, 600, 233, 51);
		lblNewLabel_1_6.setBounds(0, 600, 233, 51);
		panel_1.add(lblNewLabel_1_6);
		// Tạo JComboBox cho ngày
		dayComboBox = new JComboBox<>();
		dayComboBox.addItem("Tất cả");
		dayComboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dayComboBox.setBounds(240, 615, 80, 25);
		IntStream.rangeClosed(1, 31).forEach(dayComboBox::addItem);
		panel_1.add(dayComboBox);

		// Tạo JComboBox cho tháng
		monthComboBox = new JComboBox<>();
		monthComboBox.addItem("Tất cả");
		monthComboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		monthComboBox.setBounds(330, 615, 80, 25);
		IntStream.rangeClosed(1, 12).forEach(monthComboBox::addItem);
		panel_1.add(monthComboBox);

		// Tạo JComboBox cho năm
		yearComboBox = new JComboBox<>();
		yearComboBox.addItem("Tất cả");
		yearComboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		yearComboBox.setBounds(420, 615, 80, 25);
		int currentYear = LocalDate.now().getYear();
		for (int year = currentYear; year >= currentYear - 5; year--) {
			yearComboBox.addItem(year);
		}
		panel_1.add(yearComboBox);

		JLabel lblNewLabel_1_7 = new JLabel("Nhân viên :");
		lblNewLabel_1_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_7.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_7.setBounds(0, 650, 233, 51);
		panel_1.add(lblNewLabel_1_7);
		nv_dao = new NhanVienDAO();

		nvComboBox.addItem("Tất cả");
		docDuLieuDatacbb();
		nvComboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nvComboBox.setBounds(240, 665, 200, 25);
		panel_1.add(nvComboBox);

		updateTongDoanhThu(data);
		
		tim = new JButton("Lọc");
		tim.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
			        int ngay = (dayComboBox.getSelectedIndex() != 0) ? Integer.parseInt(dayComboBox.getSelectedItem().toString()) : 0;
			        int thang = (monthComboBox.getSelectedIndex() != 0) ? Integer.parseInt(monthComboBox.getSelectedItem().toString()) : 0;
			        int nam = (yearComboBox.getSelectedIndex() != 0) ? Integer.parseInt(yearComboBox.getSelectedItem().toString()) : 0;
			        String nv = (nvComboBox.getSelectedIndex() != 0) ? nvComboBox.getSelectedItem().toString() : null;
			        
			        filltercbb(ngay, thang, nam, nv);
			        

			        
			    }
		});
		tim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tim.setBounds(660, 600, 233, 50);
		panel_1.add(tim);

		loc = new JButton("Làm mới");
		loc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Object[][] emptyData = new Object[0][headers.length]; // Mảng rỗng
		        tableHoaDon.capNhatDuLieu(data);
				dayComboBox.setSelectedIndex(0);
				monthComboBox.setSelectedIndex(0);
				yearComboBox.setSelectedIndex(0);
				nvComboBox.setSelectedIndex(0);
				tableHoaDon.clearSelection();
//				docDuLieuDatabaseVaoTable();
			}
		});
		loc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		loc.setBounds(660, 665, 233, 50);
		panel_1.add(loc);
		
		JPanel panelRight = new JPanel();
		panelRight.setBackground(Color.white);
		panelRight.setBounds(1000, 80, 920, UIStyles.MainSectionHeight);
		panelRight.setLayout(null);

	    List<HoaDon> dsHoaDon = HoaDonDAO.layDanhSachTatCaHoaDon();

	    // Tạo biểu đồ tròn
	    JFreeChart pieChart = createChartPanel(dsHoaDon);
	    chartPanel = new ChartPanel(pieChart);
	    
	    chartPanel.setBounds(30, 20, 880, 700); 
	    panelRight.add(chartPanel);

//	    panelTong.add(panelRight);
		

		tableHoaDon.addMouseListener(this);

		panelTong.add(panel_1);
		panelTong.add(panelRight);
		
	}
	
	private JFreeChart createChartPanel(List<HoaDon> dsHoaDon) {
	    DefaultPieDataset dataset = new DefaultPieDataset();

	    // Tạo dataset từ danh sách hóa đơn
	    for (HoaDon hd : dsHoaDon) {
	        String maNV = hd.getNhanVien().getMaNhanVien();
	        BigDecimal thanhTien = hd.getThanhTien();
	        
	        // Thêm dữ liệu vào dataset
	        dataset.setValue(maNV, thanhTien);
	    }

	    // Tạo biểu đồ tròn
	    JFreeChart pieChart = ChartFactory.createPieChart(
	            "Thống kê Doanh thu theo Nhân viên", // Tiêu đề
	            dataset, // Tập dữ liệu
	            true, // Hiển thị chú thích
	            true, // Hiển thị giá trị
	            false // Không sử dụng URL
	    );

	    return pieChart;
	}



	private void filltercbb(int ngay, int thang, int nam, String nv) {
		 Object[][] allData = HoaDonDAO.layDataHD(ngay, thang, nam, nv);
		   updateChart(allData);		    
		   tableHoaDon.capNhatDuLieu(allData);
		   updateTongDoanhThu(allData);
		

		   
	}
	
	private void updateTongDoanhThu(Object[][] allData) {
	    BigDecimal tongDoanhThu = BigDecimal.ZERO;
	    for (Object[] row : allData) {
	        // Giả sử thành tiền nằm ở cột 1
	        BigDecimal thanhTien = (BigDecimal) row[1];
	        tongDoanhThu = tongDoanhThu.add(thanhTien);
	    }
	    
	    // Cập nhật label tổng doanh thu
	    lblTongDoanhThu.setText("Tổng doanh thu: " + tongDoanhThu.toString());
	}
	private void updateChart(Object[][] allData) {
	    if (chartPanel == null) {
	        System.err.println("chartPanel is null!"); // Kiểm tra xem chartPanel có null không
	        return;
	    }
	    DefaultPieDataset dataset = new DefaultPieDataset();

	    // Giả sử allData có cấu trúc [][2]: [mã nhân viên, doanh thu]
	    for (Object[] row : allData) {
	        String maNV = (String) row[3]; // Giả sử mã nhân viên nằm ở cột 0
	        BigDecimal thanhTien = (BigDecimal) row[1]; // Giả sử doanh thu nằm ở cột 1
	        
	        // Thêm dữ liệu vào dataset
	        dataset.setValue(maNV, thanhTien);
	    }

	    // Cập nhật biểu đồ tròn
	    JFreeChart pieChart = ChartFactory.createPieChart(
	            "Thống kê Doanh thu theo Nhân viên",
	            dataset,
	            true,
	            true,
	            false
	    );

	    // Cập nhật chartPanel với biểu đồ mới
	    chartPanel.setChart(pieChart);
	}

	private void docDuLieuDatacbb() {
		List<NhanVien> list = nv_dao.layDanhSachTatCaNhanVien();
		for (NhanVien nv : list) {
			nvComboBox.addItem(nv.getMaNhanVien());
		}
	}



	@Override
	public void mouseClicked(MouseEvent e) {

	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
        
		
}
