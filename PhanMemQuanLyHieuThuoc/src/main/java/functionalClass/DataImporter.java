package functionalClass;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import customDataType.DonViTinh;
import customDataType.TrangThaiSanPham;
import entity.LoaiSanPham;
import entity.NhaCungCap;
import entity.SanPhamYTe;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataImporter {
	
	public static ArrayList<SanPhamYTe> importDataFromXLSXQuickly(String file) {
        String filePath = file; // Open file chooser to select the file
        if (filePath == null) {
            System.out.println("No file selected.");
            return new ArrayList<>(); // Return an empty list if no file is selected
        }

        ArrayList<SanPhamYTe> sanPhamList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Get the first sheet
            Map<String, Integer> columnMapping = new HashMap<>();
            boolean isFirstRow = true;

            for (Row row : sheet) {
                if (isFirstRow) {
                    // Populate the column mapping from the header row
                    for (Cell cell : row) {
                        columnMapping.put(getCellValue(cell), cell.getColumnIndex());
                    }
                    isFirstRow = false;
                    continue; // Skip header row
                }

                // Extract values from the row using column names
                String maSanPham = getCellValue(row.getCell(columnMapping.get("Mã sản phẩm"))) + "SP_HEHE";
                String tenSanPham = getCellValue(row.getCell(columnMapping.get("Tên sản phẩm")));
                Date ngaySanXuat = Date.valueOf(LocalDate.of(2000, 12, 20));
                Date hanSuDung = Date.valueOf(LocalDate.of(2030, 12, 20));
                String nuocSanXuat = getCellValue(row.getCell(columnMapping.get("Nước sản xuất")));
                TrangThaiSanPham trangThaiSanPham = TrangThaiSanPham.KhongCoDuLieu;
                String ghiChu = getCellValue(row.getCell(columnMapping.get("Ghi chú")));
                String moTa = getCellValue(row.getCell(columnMapping.get("Mô tả")));
                double thue = 0.0f;
                BufferedImage hinhAnh = new BufferedImage(50, 50, BufferedImage.TYPE_4BYTE_ABGR);
                String thanhPhan = getCellValue(row.getCell(columnMapping.get("Thành phần")));
                String giaBanStr = getCellValue(row.getCell(columnMapping.get("Giá bán")));
                BigDecimal giaBan = parseBigDecimal(giaBanStr);
                NhaCungCap nhaCungCap = new NhaCungCap("NCCKHONGTONTAI");
                DonViTinh donViTinh = DonViTinh.KhongCoDuLieu;
                LoaiSanPham loaiSanPham = new LoaiSanPham("LSPKHONGTONTAI");
                String maVach = getCellValue(row.getCell(columnMapping.get("Mã vạch")));
                String yeuCauKeDon = getCellValue(row.getCell(columnMapping.get("Yêu cầu kê đơn")));
                String dangBaoChe = getCellValue(row.getCell(columnMapping.get("Dạng bào chế")));
                String nhaSanXuat = getCellValue(row.getCell(columnMapping.get("Nhà sản xuất")));

                // Construct SanPhamYTe object
                SanPhamYTe sanPham = new SanPhamYTe(
                    maSanPham,
                    tenSanPham,
                    ngaySanXuat,
                    hanSuDung,
                    nuocSanXuat,
                    trangThaiSanPham,
                    ghiChu,
                    moTa,
                    thue,
                    hinhAnh,
                    thanhPhan,
                    giaBan,
                    nhaCungCap,
                    donViTinh,
                    loaiSanPham,
                    maVach,
                    yeuCauKeDon,
                    dangBaoChe,
                    nhaSanXuat
                );

                sanPhamList.add(sanPham);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sanPhamList;
    }
    
	
    public static ArrayList<SanPhamYTe> importDataFromXLSX() {
        String filePath = chooseFile(); // Open file chooser to select the file
        if (filePath == null) {
            System.out.println("No file selected.");
            return new ArrayList<>(); // Return an empty list if no file is selected
        }

        ArrayList<SanPhamYTe> sanPhamList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Get the first sheet
            Map<String, Integer> columnMapping = new HashMap<>();
            boolean isFirstRow = true;

            for (Row row : sheet) {
                if (isFirstRow) {
                    // Populate the column mapping from the header row
                    for (Cell cell : row) {
                        columnMapping.put(getCellValue(cell), cell.getColumnIndex());
                    }
                    isFirstRow = false;
                    continue; // Skip header row
                }

                // Extract values from the row using column names
                String maSanPham = getCellValue(row.getCell(columnMapping.get("Mã sản phẩm"))) + "SP_HEHE";
                String tenSanPham = getCellValue(row.getCell(columnMapping.get("Tên sản phẩm")));
                Date ngaySanXuat = Date.valueOf(LocalDate.of(2000, 12, 20));
                Date hanSuDung = Date.valueOf(LocalDate.of(2030, 12, 20));
                String nuocSanXuat = getCellValue(row.getCell(columnMapping.get("Nước sản xuất")));
                TrangThaiSanPham trangThaiSanPham = TrangThaiSanPham.KhongCoDuLieu;
                String ghiChu = getCellValue(row.getCell(columnMapping.get("Ghi chú")));
                String moTa = getCellValue(row.getCell(columnMapping.get("Mô tả")));
                double thue = 0.0f;
                BufferedImage hinhAnh = new BufferedImage(50, 50, BufferedImage.TYPE_4BYTE_ABGR);
                String thanhPhan = getCellValue(row.getCell(columnMapping.get("Thành phần")));
                String giaBanStr = getCellValue(row.getCell(columnMapping.get("Giá bán")));
                BigDecimal giaBan = parseBigDecimal(giaBanStr);
                NhaCungCap nhaCungCap = new NhaCungCap("NCCKHONGTONTAI");
                DonViTinh donViTinh = DonViTinh.KhongCoDuLieu;
                LoaiSanPham loaiSanPham = new LoaiSanPham("LSPKHONGTONTAI");
                String maVach = getCellValue(row.getCell(columnMapping.get("Mã vạch")));
                String yeuCauKeDon = getCellValue(row.getCell(columnMapping.get("Yêu cầu kê đơn")));
                String dangBaoChe = getCellValue(row.getCell(columnMapping.get("Dạng bào chế")));
                String nhaSanXuat = getCellValue(row.getCell(columnMapping.get("Nhà sản xuất")));

                // Construct SanPhamYTe object
                SanPhamYTe sanPham = new SanPhamYTe(
                    maSanPham,
                    tenSanPham,
                    ngaySanXuat,
                    hanSuDung,
                    nuocSanXuat,
                    trangThaiSanPham,
                    ghiChu,
                    moTa,
                    thue,
                    hinhAnh,
                    thanhPhan,
                    giaBan,
                    nhaCungCap,
                    donViTinh,
                    loaiSanPham,
                    maVach,
                    yeuCauKeDon,
                    dangBaoChe,
                    nhaSanXuat
                );

                sanPhamList.add(sanPham);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sanPhamList;
    }
    
    

    private static String chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose an XLSX file");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel files", "xlsx"));

        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath(); // Return the file path
        }
        return null; // Return null if no file is selected
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "Không dữ liệu";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "Không dữ liệu";
        }
    }

    private static double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private static BigDecimal parseBigDecimal(String value) {
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;
        }
    }
}
