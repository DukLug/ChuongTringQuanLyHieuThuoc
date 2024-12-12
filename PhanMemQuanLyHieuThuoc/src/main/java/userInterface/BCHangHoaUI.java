package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import dao.HoaDonDAO;

public class BCHangHoaUI extends JPanel implements ActionListener,MouseListener {

	private JPanel chartPanel;

	public BCHangHoaUI() {
		super();
		taoHinh();
		
	}

	private void taoHinh() {
		// TODO Auto-generated method stub

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

        JLabel lblKhachHang = new JLabel("BÁO CÁO HÀNG HOÁ");
        lblKhachHang.setBounds(819, 20, 258, 29);
        lblKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
        lblKhachHang.setFont(new Font("Tahoma", Font.BOLD, 24));
        panel.add(lblKhachHang);
        
        JPanel filterPanel = new JPanel();
        filterPanel.setBounds(50, 100, 1800, 50);
        filterPanel.setLayout(new FlowLayout());
        panelTong.add(filterPanel);

        // Tạo các checkbox
        JCheckBox cbToday = new JCheckBox("Hôm nay");
        JCheckBox cbYesterday = new JCheckBox("Hôm qua");
        JCheckBox cbThisWeek = new JCheckBox("Tuần này");
        JCheckBox cbLastWeek = new JCheckBox("Tuần trước");
        JCheckBox cbThisMonth = new JCheckBox("Tháng này");
        JCheckBox cbLastMonth = new JCheckBox("Tháng trước");
        JCheckBox cbThisYear = new JCheckBox("Năm nay");
        JCheckBox cbLastYear = new JCheckBox("Năm trước");

        // Nút Lọc
        JButton btnFilter = new JButton("Lọc");

//        // Thêm các thành phần vào panel lọc

        
        
     // Tạo ButtonGroup để nhóm các JCheckBox
        ButtonGroup group = new ButtonGroup();
        group.add(cbToday);
        group.add(cbYesterday);
        group.add(cbThisWeek);
        group.add(cbLastWeek);
        group.add(cbThisMonth);
        group.add(cbLastMonth);
        group.add(cbThisYear);
        group.add(cbLastYear);
        
      filterPanel.add(cbToday);
      filterPanel.add(cbYesterday);
      filterPanel.add(cbThisWeek);
      filterPanel.add(cbLastWeek);
      filterPanel.add(cbThisMonth);
      filterPanel.add(cbLastMonth);
      filterPanel.add(cbThisYear);
      filterPanel.add(cbLastYear);
      filterPanel.add(btnFilter);
//
//        // Thêm các JCheckBox vào giao diện
//        panel.add(cbToday);
//        panel.add(cbYesterday);
//        panel.add(cbThisWeek);
//        panel.add(cbLastWeek);
//        panel.add(cbThisMonth);
//        panel.add(cbLastMonth);
//        panel.add(cbThisYear);
//        panel.add(cbLastYear);

        // Panel biểu đồ
        chartPanel = new JPanel();
        chartPanel.setBounds(50, 200, 1800, 600);
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setLayout(new BorderLayout());
        panelTong.add(chartPanel);
        
        
      chartPanel = new JPanel();
      chartPanel.setBounds(50, 200, 1800, 600);
      chartPanel.setBackground(Color.WHITE);
      chartPanel.setLayout(new BorderLayout());
      panelTong.add(chartPanel);
      

        // Sự kiện nút "Lọc"
      btnFilter.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	        // Xử lý logic lọc dựa trên các checkbox được chọn
    	        String filterCondition = "";
    	        if (cbToday.isSelected()) {
    	            filterCondition = "Hôm nay";
    	        } else if (cbYesterday.isSelected()) {
    	            filterCondition = "Hôm qua";
    	        } else if (cbThisWeek.isSelected()) {
    	            filterCondition = "Tuần này";
    	        } else if (cbLastWeek.isSelected()) {
    	            filterCondition = "Tuần trước";
    	        } else if (cbThisMonth.isSelected()) {
    	            filterCondition = "Tháng này";
    	        } else if (cbLastMonth.isSelected()) {
    	            filterCondition = "Tháng trước";
    	        } else if (cbThisYear.isSelected()) {
    	            filterCondition = "Năm nay";
    	        } else if (cbLastYear.isSelected()) {
    	            filterCondition = "Năm trước";
    	        }

    	        // Lấy dữ liệu dựa trên điều kiện lọc (giả sử bạn đã định nghĩa HoaDonDAO)
    	        Object[][] data = HoaDonDAO.layDataHDTK(filterCondition);
    	     // Tạo dataset từ dữ liệu
    	        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	        if (data == null || data.length == 0) {
    	            // Sử dụng hàm tạo biểu đồ mặc định
    	            JFreeChart defaultChart = createDefaultChart();
    	            updateChartPanel(defaultChart);
    	        } else {
    	            for (Object[] row : data) {
    	                if (row != null) {
    	                    String time = row[2] != null ? row[2].toString() : "Không xác định";
    	                    BigDecimal doanhThu = (BigDecimal) row[1];
    	                    dataset.addValue(doanhThu, "Doanh Thu", time);
    	                }
    	            }

    	            // Tạo biểu đồ mới
    	            JFreeChart barChart = ChartFactory.createBarChart(
    	                "Doanh Thu " + filterCondition,
    	                "Thời Gian", "Doanh Thu",
    	                dataset
    	            );

    	            // Cấu hình biểu đồ
    	            CategoryPlot plot = barChart.getCategoryPlot();
    	            BarRenderer renderer = new BarRenderer();
    	            renderer.setMaximumBarWidth(0.05); // Điều chỉnh chiều rộng cột
    	            plot.setRenderer(renderer);

    	            NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
    	            yAxis.setTickUnit(new NumberTickUnit(50000));
    	            NumberFormat customFormat = new DecimalFormat("###,###");
    	            yAxis.setNumberFormatOverride(customFormat);

    	            // Cập nhật panel biểu đồ
    	            chartPanel.removeAll();
    	            ChartPanel newChartPanel = new ChartPanel(barChart);
    	            newChartPanel.setPreferredSize(chartPanel.getSize());
    	            chartPanel.add(newChartPanel, BorderLayout.CENTER);
    	            chartPanel.revalidate(); // Yêu cầu cập nhật bố cục
    	            chartPanel.repaint();    // Vẽ lại panel
    	        }
    	    }
      });
    	    
      }
      
   // Hàm tạo biểu đồ mặc định
      private JFreeChart createDefaultChart() {
          // Tạo dataset mặc định
          DefaultCategoryDataset dataset = new DefaultCategoryDataset();
          dataset.addValue(0, "Thông báo", "Không có dữ liệu");

          // Tạo biểu đồ
          JFreeChart barChart = ChartFactory.createBarChart(
              "Doanh Thu", // Tiêu đề biểu đồ
              "Thời Gian", // Nhãn trục X
              "Doanh Thu", // Nhãn trục Y
              dataset
          );

          // Cấu hình biểu đồ
          CategoryPlot plot = barChart.getCategoryPlot();
          BarRenderer renderer = new BarRenderer();
          renderer.setMaximumBarWidth(0.05);
          plot.setRenderer(renderer);

          NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
          yAxis.setTickUnit(new NumberTickUnit(50000)); // Đặt khoảng cách giữa các tick là 1 triệu
          NumberFormat customFormat = new DecimalFormat("###,###");
          yAxis.setNumberFormatOverride(customFormat);

          return barChart;
      }
      
      private void updateChartPanel(JFreeChart chart) {
    	    chartPanel.removeAll();
    	    ChartPanel newChartPanel = new ChartPanel(chart);
    	    newChartPanel.setPreferredSize(chartPanel.getSize());
    	    chartPanel.add(newChartPanel, BorderLayout.CENTER);
    	    chartPanel.revalidate(); // Yêu cầu cập nhật bố cục
    	    chartPanel.repaint();    // Vẽ lại panel
    	}
      
    	        
    	        // Tạo dataset từ dữ liệu
//    	        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//    	        if (data == null || data.length == 0) {
//    	            // Thêm giá trị mặc định cho trường hợp không có dữ liệu
//    	           JFreeChart defaultChart = createDefaultChart();
//    	            updateChartPanel(defaultChart);
//    	        } else
//    	        {
//    	        	for (Object[] row : data) {
//        	            if (row != null) {
//        	                String time = row[2] != null ? row[2].toString() : "Không xác định";
//        	                BigDecimal doanhThu = (BigDecimal) row[1];
//        	                dataset.addValue(doanhThu, "Doanh Thu", time);
//        	            }
//        	        }
//
//        	        // Tạo biểu đồ mới
//        	        JFreeChart barChart = ChartFactory.createBarChart(
//        	                "Doanh Thu " + filterCondition,
//        	                "Thời Gian", "Doanh Thu",
//        	                dataset
//        	        );
//
//        	        // Cấu hình biểu đồ
//        	        CategoryPlot plot = barChart.getCategoryPlot();
//        	        BarRenderer renderer = new BarRenderer();
//        	        renderer.setMaximumBarWidth(0.1); // Điều chỉnh chiều rộng cột
//        	        plot.setRenderer(renderer);
//
//        	        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
//        	        NumberFormat customFormat = new DecimalFormat("#,###");
//        	        yAxis.setNumberFormatOverride(customFormat);
//
//        	        // Cập nhật panel biểu đồ
//        	        chartPanel.removeAll();
//        	        ChartPanel newChartPanel = new ChartPanel(barChart);
//        	        newChartPanel.setPreferredSize(chartPanel.getSize());
//        	        chartPanel.add(newChartPanel, BorderLayout.CENTER);
//        	        chartPanel.revalidate(); // Yêu cầu cập nhật bố cục
//        	        chartPanel.repaint();    // Vẽ lại panel
//        	    
//    	        }
//    	   
//    	    
//      }
//      });
//	}
//    	        
//	
//	private JFreeChart createDefaultChart() {
//	    // Tạo dataset mặc định
//	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//	    dataset.addValue(0, "Thông báo", "Không có dữ liệu");
//
//	    // Tạo biểu đồ
//	    JFreeChart barChart = ChartFactory.createBarChart(
//	            "Doanh Thu", // Tiêu đề biểu đồ
//	            "Thời Gian", // Nhãn trục X
//	            "Doanh Thu", // Nhãn trục Y
//	            dataset
//	    );
//
//	    // Cấu hình biểu đồ
//	    CategoryPlot plot = barChart.getCategoryPlot();
//	    BarRenderer renderer = new BarRenderer();
//	    renderer.setMaximumBarWidth(0.1);
//	    plot.setRenderer(renderer);
//
//	    NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
//	    NumberFormat customFormat = new DecimalFormat("#,###");
//	    yAxis.setNumberFormatOverride(customFormat);
//
//	    return barChart;
//	}

//     // Panel lọc ngày, tháng, năm
//        JPanel filterPanel = new JPanel();
//        filterPanel.setBounds(50, 100, 1800, 50);
//        filterPanel.setLayout(new FlowLayout());
//        panelTong.add(filterPanel);
//
//        JLabel lblNgay = new JLabel("Chọn Ngày:");
//        JComboBox<Integer> cbDay = new JComboBox<>();
//        for (int i = 12; i <= 12; i++) cbDay.addItem(i);
//
//        JLabel lblThang = new JLabel("Chọn Tháng:");
//        JComboBox<Integer> cbMonth = new JComboBox<>();
//        for (int i = 12; i <= 12; i++) cbMonth.addItem(i);
//
//        JLabel lblNam = new JLabel("Chọn Năm:");
//        JComboBox<Integer> cbYear = new JComboBox<>();
//        for (int i = 2024; i <= 2024; i++) cbYear.addItem(i);
//
//        JButton btnFilter = new JButton("Lọc");
//
//        // Thêm các thành phần vào panel lọc
//        filterPanel.add(lblNgay);
//        filterPanel.add(cbDay);
//        filterPanel.add(lblThang);
//        filterPanel.add(cbMonth);
//        filterPanel.add(lblNam);
//        filterPanel.add(cbYear);
//        filterPanel.add(btnFilter);
//
//     // Panel biểu đồ
//        JPanel chartPanel = new JPanel();
//        chartPanel.setBounds(50, 200, 1800, 600);
//        chartPanel.setBackground(Color.WHITE);
//        chartPanel.setLayout(new BorderLayout());
//        panelTong.add(chartPanel);
//
//        // Sự kiện nút "Lọc"
//        btnFilter.addActionListener(e -> {
//            int day = (int) cbDay.getSelectedItem();
//            int month = (int) cbMonth.getSelectedItem();
//            int year = (int) cbYear.getSelectedItem();
//            
//            // Lấy dữ liệu dựa trên điều kiện lọc từ DAO
//            Object[][] data = HoaDonDAO.layDataHD(day, month, year, null);  // null cho nhân viên nếu không lọc
//
//            // Tạo dataset từ dữ liệu
//            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//            for (Object[] row : data) {
//                if (row != null) {
//                    String time = row[2] != null ? row[2].toString() : "Không xác định";
//                    BigDecimal doanhThu = (BigDecimal) row[1];
//                    dataset.addValue(doanhThu, "Doanh Thu", time);
//                }
//            }
//
//            // Tạo biểu đồ mới
//            JFreeChart barChart = ChartFactory.createBarChart(
//                    "Doanh Thu Ngày " + day + "/" + month + "/" + year,
//                    "Thời Gian", "Doanh Thu",
//                    dataset
//            );
//            
//            CategoryPlot plot = barChart.getCategoryPlot();
//            BarRenderer renderer = new BarRenderer();
//            renderer.setMaximumBarWidth(0.1); // Điều chỉnh giá trị này để thay đổi chiều rộng cột
//            plot.setRenderer(renderer);
//            
//            NumberAxis yAxis = (NumberAxis) barChart.getCategoryPlot().getRangeAxis();
////            yAxis.setNumberFormatOverride(NumberFormat.getCurrencyInstance()); 
//            NumberFormat customFormat = new DecimalFormat("#,###");
//            yAxis.setNumberFormatOverride(customFormat);
//
//            // Đảm bảo chartPanel cập nhật đúng
//            chartPanel.removeAll();
//            ChartPanel newChartPanel = new ChartPanel(barChart);
//            newChartPanel.setPreferredSize(chartPanel.getSize());
//            chartPanel.add(newChartPanel, BorderLayout.CENTER);
//            chartPanel.revalidate(); // Yêu cầu cập nhật bố cục
//            chartPanel.repaint();    // Vẽ lại panel
//        });

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
