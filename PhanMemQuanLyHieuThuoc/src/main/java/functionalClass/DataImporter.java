package functionalClass;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import customDataType.DonViTinh;
import testEntity.Thuoc;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataImporter {

    public static Thuoc createThuocFromRow(Row row) {
        DataFormatter dataFormatter = new DataFormatter();

        // Retrieve and format cell values
        String maThuoc = dataFormatter.formatCellValue(row.getCell(0));
        String tenThuoc = dataFormatter.formatCellValue(row.getCell(1));
        String donViTinhStr = dataFormatter.formatCellValue(row.getCell(2));
        int soLuong = (int) row.getCell(3).getNumericCellValue();

        // Convert DonViTinh string to enum
        DonViTinh donViTinh;
        if (donViTinhStr.equalsIgnoreCase("Hộp")) {
            donViTinh = DonViTinh.Hop;
        } else if (donViTinhStr.equalsIgnoreCase("Chai")) {
            donViTinh = DonViTinh.Chai;
        } else {
            throw new IllegalArgumentException("Invalid DonViTinh value: " + donViTinhStr);
        }

        return new Thuoc(maThuoc, tenThuoc, donViTinh, soLuong);
    }

    public static ArrayList<Thuoc> readThuocFromExcel() {
        ArrayList<Thuoc> thuocList = new ArrayList<>();

        // Open a file chooser dialog to select the Excel file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an Excel File");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel Files", "xls", "xlsx"));

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try (FileInputStream fileInputStream = new FileInputStream(selectedFile);
                 Workbook workbook = new XSSFWorkbook(fileInputStream)) {

                // Get the first sheet
                Sheet sheet = workbook.getSheetAt(0);

                // Skip header row (row 0)
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        Thuoc thuoc = createThuocFromRow(row);
                        thuocList.add(thuoc);
                    }
                }

            } catch (IOException e) {
                System.out.println("Error reading the Excel file: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("File selection canceled.");
        }

        return thuocList;
    }

    public static void main(String[] args) {
        ArrayList<Thuoc> thuocList = readThuocFromExcel();
        for (Thuoc thuoc : thuocList) {
            System.out.println(thuoc);
        }
    }
}
