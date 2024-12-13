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

import javax.swing.BorderFactory;
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

public class BCBanHangUI extends JPanel implements ActionListener,MouseListener {

	private JPanel chartPanel;

	public BCBanHangUI() {
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

        ButtonGroup group = new ButtonGroup();
        group.add(cbToday);
        group.add(cbYesterday);
        group.add(cbThisWeek);
        group.add(cbLastWeek);
        group.add(cbThisMonth);
        group.add(cbLastMonth);
        group.add(cbThisYear);
        group.add(cbLastYear);
        
     // Tạo font mới với kích thước lớn hơn
        Font largerFont = new Font("Arial", Font.PLAIN, 22);

        // Áp dụng font cho các JCheckBox
        cbToday.setFont(largerFont);
        cbYesterday.setFont(largerFont);
        cbThisWeek.setFont(largerFont);
        cbLastWeek.setFont(largerFont);
        cbThisMonth.setFont(largerFont);
        cbLastMonth.setFont(largerFont);
        cbThisYear.setFont(largerFont);
        cbLastYear.setFont(largerFont);

        btnFilter.setFont(largerFont);

        cbToday.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 40));
        cbYesterday.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 40));
        cbThisWeek.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 40));
        cbLastWeek.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 40));
        cbThisMonth.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 40));
        cbLastMonth.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 40));
        cbThisYear.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 40));
        cbLastYear.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 40));

        
      filterPanel.add(cbToday);
      filterPanel.add(cbYesterday);
      filterPanel.add(cbThisWeek);
      filterPanel.add(cbLastWeek);
      filterPanel.add(cbThisMonth);
      filterPanel.add(cbLastMonth);
      filterPanel.add(cbThisYear);
      filterPanel.add(cbLastYear);
      filterPanel.add(btnFilter);


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

    	        Object[][] data = HoaDonDAO.layDataHDTK(filterCondition);
    	
    	        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	        if (data == null || data.length == 0) {
    	       
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
