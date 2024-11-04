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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


import component.CustomTable;
import controller.HoaDonCTR;
import dao.HoaDonDAO;
import dao.NhanVienDAO;
import entity.NhanVien;



public class BCBanHangUI extends JPanel implements ActionListener, MouseListener {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField mahd;
	private JTextField manv;
	private JTextField tongtien;
	private JTextField makh;
	private JTextField ngayban;
	private JTextField maban;
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

	
	public BCBanHangUI() {
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

        JLabel lblKhachHang = new JLabel("BÁO CÁO BÁN HÀNG");
        lblKhachHang.setBounds(819, 20, 258, 29);
        lblKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
        lblKhachHang.setFont(new Font("Tahoma", Font.BOLD, 24));
        panel.add(lblKhachHang);

        JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.white);
		panel_1.setBounds(0, 0, 1100, UIStyles.MainSectionHeight);
		panelTong.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(50, 80, 1000, 273);
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout());

		
		data = new Object[0][headers.length];

		tableHoaDon = new CustomTable(data, headers, UIStyles.NhanVienTableHeaderStyle,
		        UIStyles.NhanVienTableRowStyle, 20);
		JScrollPane scrollPane = new JScrollPane(tableHoaDon);
		panel_3.add(scrollPane, BorderLayout.CENTER);

		data = HoaDonCTR.layDataHD();
		tableHoaDon.capNhatDuLieu(data);

		JLabel lblNewLabel_1 = new JLabel("Mã hóa đơn :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(0, 388, 128, 51);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Mã nhân viên :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(474, 388, 164, 51);
		panel_1.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Mã Khách hàng :");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(474, 450, 164, 51);
		panel_1.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Tổng tiền :");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(0, 450, 128, 51);
		panel_1.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Ngày bán :");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_4.setBounds(0, 512, 128, 51);
		panel_1.add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_5 = new JLabel("Số thẻ :");
		lblNewLabel_1_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_5.setBounds(474, 512, 164, 51);
		panel_1.add(lblNewLabel_1_5);

		mahd = new JTextField();
		mahd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mahd.setEditable(false);
		mahd.setBounds(150, 396, 253, 40);
		panel_1.add(mahd);
		mahd.setColumns(10);

		manv = new JTextField();
		manv.setFont(new Font("Tahoma", Font.PLAIN, 18));
		manv.setEditable(false);
		manv.setColumns(10);
		manv.setBounds(660, 396, 253, 40);
		panel_1.add(manv);

		tongtien = new JTextField();
		tongtien.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tongtien.setEditable(false);
		tongtien.setColumns(10);
		tongtien.setBounds(150, 458, 253, 40);
		panel_1.add(tongtien);

		makh = new JTextField();
		makh.setEditable(false);
		makh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		makh.setColumns(10);
		makh.setBounds(660, 458, 253, 40);
		panel_1.add(makh);

		ngayban = new JTextField();
		ngayban.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ngayban.setEditable(false);
		ngayban.setColumns(10);
		ngayban.setBounds(150, 520, 253, 40);
		panel_1.add(ngayban);

		maban = new JTextField();
		maban.setFont(new Font("Tahoma", Font.PLAIN, 18));
		maban.setEditable(false);
		maban.setColumns(10);
		maban.setBounds(660, 520, 253, 40);
		panel_1.add(maban);

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
		nvComboBox.setBounds(240, 665, 100, 25);
		panel_1.add(nvComboBox);


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
				mahd.setText("");
				manv.setText("");
				makh.setText("");
				tongtien.setText("");
				ngayban.setText("");
				maban.setText("");
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
		panelRight.setBackground(Color.red);
		panelRight.setBounds(1100, 80, 800, UIStyles.MainSectionHeight);
		panelRight.setLayout(null);


		JFreeChart barChart = createChart();
		ChartPanel chartPanel = new ChartPanel(barChart);

		chartPanel.setBounds(50, 50, 700, 700); 
		panelRight.add(chartPanel);
		

		tableHoaDon.addMouseListener(this);

		panelTong.add(panel_1);
		panelTong.add(panelRight);
		
	}



	private void filltercbb(int ngay, int thang, int nam, String nv) {
		 Object[][] allData = HoaDonDAO.layDataHD(ngay, thang, nam, nv);
		    
		   tableHoaDon.capNhatDuLieu(allData);
		
		   updateChart(allData);
		   
	}
//	  filteredData = allData;
	   

	   
//	   updateChart(filteredData);
//	   System.out.println(Arrays.deepToString((Object[]) filteredData));//		    tableHoaDon.capNhatDuLieu(filteredData);
	   
//	   updateChart(filteredData);

	private void docDuLieuDatacbb() {
		List<NhanVien> list = nv_dao.layDanhSachTatCaNhanVien();
		for (NhanVien nv : list) {
			nvComboBox.addItem(nv.getMaNhanVien());
		}
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableHoaDon.getSelectedRow();
	    if (row >= 0) {
	        mahd.setText((String) tableHoaDon.getValueAt(row, 0));
	        
	        BigDecimal tongTien = (BigDecimal) tableHoaDon.getValueAt(row, 1);
	        tongtien.setText(tongTien.toString());
	        
	        sqlDate = tableHoaDon.getValueAt(row, 2);
	        ngayban.setText(sqlDate.toString());
	        
	        manv.setText((String) tableHoaDon.getValueAt(row, 3));
	        makh.setText((String) tableHoaDon.getValueAt(row, 4));
	    }
	}


	public void updateChart(Object[][] data) {
		 if (chartPanel == null) {
		        System.err.println("");
		        return; // Không làm gì nếu chartPanel chưa được khởi tạo
		    }
		 if (data == null || data.length == 0) {
		        System.err.println("");
		        return; 
		    }
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();


	    for (Object[] row : data) {
	        String year = row[2].toString(); // Năm
	        Number revenue = (Number) row[1]; // Doanh thu
	        dataset.addValue(revenue, "Doanh thu", year); 
	    }

	    JFreeChart newChart = ChartFactory.createBarChart(
	            "BIỂU ĐỒ DOANH THU",
	            "Thời gian",
	            "Doanh thu",
	            dataset,
	            PlotOrientation.VERTICAL,
	            false,
	            true,
	            false
	    );

	    chartPanel.setChart(newChart); 
	}
	
	private JFreeChart createChart() {
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ DOANH THU",
                "Thời gian",
                "Doanh thu",
                createDataset(),
                PlotOrientation.VERTICAL,
                false,  
                true,   
                false 
        );
        return barChart;
    }
	private CategoryDataset createDataset() {
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	    Object[][] allData = HoaDonDAO.layDataHD(0, 0, 0, ""); 

	    for (Object[] row : allData) {
	        String year = row[2].toString(); 
	        Number revenue = (Number) row[1]; 
	        dataset.addValue(revenue, "Doanh thu", year); 
	    }

	    return dataset; 
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
