//package userInterface;

package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import component.CustomButton;
import component.CustomTable;
import component.CustomButton.CustomButtonIconSide;
import controller.DonDoiTraCTR;
import controller.HoaDonCTR;
import dao.HoaDonDAO;

public class BCCuoiNgayUI3 extends JPanel implements ActionListener, MouseListener {
    private static final long serialVersionUID = 1L;
    private HoaDonDAO hoaDonDao;
    private HoaDonCTR hoaDonCTR;
    private CustomTable tableCuoiNgay;
    private Object[][] data = new Object[0][0];
    private JComponent lblNgayLap;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private JLabel labelNgayBan;
    private JButton btnXuatFile;
    private String[] headers = {"Mã hóa đơn", "Ngày Bán", "Doanh thu", "Loại hóa đơn", "Phí trả hàng", "Thực thu"};

    public BCCuoiNgayUI3() {
        super();
        hoaDonCTR = new HoaDonCTR();
        hoaDonDao = new HoaDonDAO();
        taoHinh();
        layThoiGianHienTai();
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
        panel.setBounds(0, 0, 1800, 120);
        panelTong.add(panel);
        panel.setLayout(null);

        JLabel lblKhachHang = new JLabel("BÁO CÁO THU CHI");
        lblKhachHang.setBounds(819, 20, 258, 29);
        lblKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
        lblKhachHang.setFont(new Font("Tahoma", Font.BOLD, 24));
        panel.add(lblKhachHang);

        JPanel panelNgayBan = new JPanel();
        panelNgayBan.setBounds(761, 63, 378, 47);
        panel.add(panelNgayBan);
        panelNgayBan.setLayout(null);

        labelNgayBan = new JLabel("Ngày bán:");
        labelNgayBan.setBounds(10, 8, 116, 30);
        panelNgayBan.add(labelNgayBan);
        labelNgayBan.setFont(new Font("Tahoma", Font.BOLD, 20));

        lblNgayLap = new JLabel();
        lblNgayLap.setForeground(Color.BLACK);
        lblNgayLap.setFont(new Font("Tahoma", Font.BOLD, 20)); 
        lblNgayLap.setBounds(150, 8, 306, 30); 
        panelNgayBan.add(lblNgayLap);

        CustomButton btnXuatFile = new CustomButton("Xuất file", UIStyles.BanHangButtonStyle, UIStyles.ReportIcon, CustomButtonIconSide.LEFT);
        btnXuatFile.setBounds(1600, 20, 150, 40);
        panel.add(btnXuatFile);

        JTable table = new JTable();
        Object[][] data = new Object[0][headers.length];

        tableCuoiNgay = new CustomTable(data, headers, UIStyles.NhanVienTableHeaderStyle,
                UIStyles.NhanVienTableRowStyle, 20);
        JScrollPane scrollPane = new JScrollPane(tableCuoiNgay);
        scrollPane.setPreferredSize(new Dimension(1200, 600)); // Giảm chiều rộng cho bảng
        panelTong.add(scrollPane);
        scrollPane.setBounds(55, 149, 1200, 600); // Di chuyển bảng sang bên trái

        data = layDataKetHop();
        tableCuoiNgay.capNhatDuLieu(data);
        
        int tongHoaDon = data.length;
        BigDecimal tongDoanhThu = BigDecimal.ZERO;
        BigDecimal tongPhiTra = BigDecimal.ZERO;
        BigDecimal tongThucThu = BigDecimal.ZERO;

        for (Object[] hang : data) {
            if (hang.length > 0) {
                BigDecimal doanhThu = (hang[2] instanceof BigDecimal) ? (BigDecimal) hang[2] : BigDecimal.ZERO;
                BigDecimal phiTra = (hang[4] instanceof BigDecimal) ? (BigDecimal) hang[4] : BigDecimal.ZERO;

                tongDoanhThu = tongDoanhThu.add(doanhThu);
                tongPhiTra = tongPhiTra.add(phiTra);
                tongThucThu = tongThucThu.add(doanhThu.subtract(phiTra));
            }
        }

        JLabel lblTongHoaDon = new JLabel("Tổng số hóa đơn: " + tongHoaDon);
        JLabel lblTongDoanhThu = new JLabel("Tổng doanh thu: " + tongDoanhThu.toString());
        JLabel lblTongPhiTra = new JLabel("Tổng phí trả: " + tongPhiTra.toString());
        JLabel lblTongThucThu = new JLabel("Tổng thực thu: " + tongThucThu.toString());
//

        
        lblTongHoaDon.setFont(UIStyles.LabelFontBoldGreen);
        lblTongHoaDon.setForeground(UIStyles.LabelFontColorGreen);

        lblTongDoanhThu.setFont(UIStyles.LabelFontBoldGreen);
        lblTongDoanhThu.setForeground(UIStyles.LabelFontColorGreen);

        lblTongPhiTra.setFont(UIStyles.LabelFontBoldGreen);
        lblTongPhiTra.setForeground(UIStyles.LabelFontColorGreen);

        lblTongThucThu.setFont(UIStyles.LabelFontBoldGreen);
        lblTongThucThu.setForeground(UIStyles.LabelFontColorGreen);
        
        lblTongHoaDon.setBounds(80, 780, 400, 30);
        lblTongDoanhThu.setBounds(500, 780, 400, 30);
        lblTongPhiTra.setBounds(1100, 780, 400, 30);
        lblTongThucThu.setBounds(1500, 780, 400, 30);
        
        panelTong.add(lblTongHoaDon);
        panelTong.add(lblTongDoanhThu);
        panelTong.add(lblTongPhiTra);
        panelTong.add(lblTongThucThu);
        
        
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        dataset.addValue(tongHoaDon, "Tổng Hóa Đơn", "Hóa Đơn");
//        dataset.addValue(tongDoanhThu, "Tổng Doanh Thu", "Doanh Thu");
//        dataset.addValue(tongPhiTra, "Tổng Phí Trả", "Phí Trả");
//        dataset.addValue(tongThucThu, "Tổng Thực Thu", "Thực Thu");
//
//        // Tạo biểu đồ cột
//        JFreeChart barChart = ChartFactory.createBarChart(
//                "Thống Kê Doanh Thu",
//                "Loại",
//                "Giá Trị",
//                dataset,
//                PlotOrientation.VERTICAL,
//                true,
//                true,
//                false
//        );
//
//        // Thêm biểu đồ vào panel
//        ChartPanel barChartPanel = new ChartPanel(barChart);
//        barChartPanel.setPreferredSize(new Dimension(600, 600));
//        barChartPanel.setBounds(1300, 149, 600, 600);
//        panelTong.add(barChartPanel);
//        
        
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        pieDataset.setValue("Doanh Thu", tongDoanhThu);
        pieDataset.setValue("Phí Trả", tongPhiTra);
//        pieDataset.setValue("Thực Thu", tongThucThu);

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Thống Kê Doanh Thu (Biểu Đồ Tròn)",
                pieDataset,
                true,
                true,
                false
        );

        // Thêm biểu đồ tròn vào panel
        ChartPanel pieChartPanel = new ChartPanel(pieChart);
        pieChartPanel.setPreferredSize(new Dimension(600, 600));
        pieChartPanel.setBounds(1300, 149, 600, 600); // Đặt vị trí cho biểu đồ tròn
        panelTong.add(pieChartPanel);  
        btnXuatFile.addActionListener(this);
    }



    public Object[][] layDataKetHop() {
        Object[][] dataHoaDon = HoaDonCTR.layDataHDBan();
        Object[][] dataDonDoiTra = DonDoiTraCTR.layDataHDDoiTraCuoiNgay();

        int totalRows = (dataHoaDon != null ? dataHoaDon.length : 0) + 
                        (dataDonDoiTra != null ? dataDonDoiTra.length : 0);
        
        Object[][] data = new Object[totalRows][];

        int index = 0;
        if (dataHoaDon != null) {
            for (int i = 0; i < dataHoaDon.length; i++) {
                data[index++] = dataHoaDon[i];
            }
        }
        if (dataDonDoiTra != null) {
            for (int i = 0; i < dataDonDoiTra.length; i++) {
                data[index++] = dataDonDoiTra[i];
            }
        }

        return data;
    }


    private void layThoiGianHienTai() {
        hienThiTGHienTai();
        Timer timer = new Timer(1000, new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) {
                hienThiTGHienTai();
            }
        });
        timer.start();
    }

    private void hienThiTGHienTai() {
        LocalDateTime tgHienTai = LocalDateTime.now();
        ((JLabel) lblNgayLap).setText(tgHienTai.format(formatter));
    }
    
    public void xuatFileExcel(Object[][] data, String[] headers) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("BaoCaoCuoiNgay");

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);

            CellStyle style = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            cell.setCellStyle(style);
        }

        for (int i = 0; i < data.length; i++) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < data[i].length; j++) {
                Cell cell = row.createCell(j);
                if (data[i][j] instanceof String) {
                    cell.setCellValue((String) data[i][j]);
                } else if (data[i][j] instanceof Integer) {
                    cell.setCellValue((Integer) data[i][j]);
                } else if (data[i][j] instanceof Double) {
                    cell.setCellValue((Double) data[i][j]);
                } else if (data[i][j] instanceof java.sql.Date) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    cell.setCellValue(sdf.format((java.sql.Date) data[i][j]));
                } else if (data[i][j] instanceof BigDecimal) {
                    cell.setCellValue(((BigDecimal) data[i][j]).doubleValue());
                }
            }
        }

        // Mở JFileChooser để lưu file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Lưu file Excel");
        fileChooser.setSelectedFile(new File("BaoCaoCuoiNgay.xlsx")); // Tên file mặc định

        
        
        
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            // Tạo file và ghi dữ liệu vào
            try (FileOutputStream fileOut = new FileOutputStream(fileToSave)) {
                workbook.write(fileOut);
                System.out.println("Xuất file Excel thành công!");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        Object[][] data =layDataKetHop(); 
        String[] headers = {"Mã hóa đơn", "Ngày Bán", "Doanh thu", "Loại hóa đơn", "Phí trả hàng", "Thực thu"};

        if (data.length == 0) {
            System.out.println("Dữ liệu rỗng! Không có dữ liệu để xuất.");
            return; // Dừng thực hiện nếu dữ liệu rỗng
        }

        // In dữ liệu để kiểm tra
//        for (Object[] row : data) {
//            for (Object cell : row) {
//                System.out.print(cell + "\t");
//            }
//            System.out.println();
//        }
        xuatFileExcel(data, headers);
    }
}
