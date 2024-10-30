package application;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.*;

import controller.TimKiemCTR;
import customDataType.ChucVu;
import customDataType.TrangThaiLamViec;
import entity.NhanVien;
import functionalClass.DataImporter;
import functionalClass.GlobalExceptionHandler;
import testEntity.TestNguoi;
import testEntity.Thuoc;
import userInterface.*;
import userInterface.NhapHangUI.NhapHangRow;

public class PhanMemQuanLyHieuThuoc {
	public static final boolean HienLoi = true;
	public static ArrayList<Thuoc> thuocList;

   public static void main(String[] args) {
	   if(HienLoi) {
		   GlobalExceptionHandler.registerExceptionHandler();
	   }
		System.setProperty("sun.java2d.uiScale", "1.0");
		//thuocList = DataImporter.readThuocFromExcel();
		
        TrangChuUI trangChuUI = new TrangChuUI(false);
        //TestSearch();
        
   }
   public static void hienLoi(Class<?> errorSource, Exception e) {	        
        String errorMessage = errorSource.getName() + ": " + e.getMessage();
        String email = "support@example.com";

        JLabel emailLabel = new JLabel("Please contact us for assistance: " + email);
        emailLabel.setFont(new Font("Tahoma", Font.BOLD, 12));

        JPanel panel = new JPanel(new BorderLayout());

        JTextArea errorText = new JTextArea(10, 40);
        errorText.setText(errorMessage);
        errorText.setEditable(false);
        errorText.setLineWrap(true);
        errorText.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(errorText);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 150));

        panel.add(scrollPane, BorderLayout.NORTH);
        panel.add(emailLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton copyEmailButton = new JButton("Copy Email");
        copyEmailButton.addActionListener((ActionEvent ae) -> {
            StringSelection emailSelection = new StringSelection(email);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(emailSelection, null);
            JOptionPane.showMessageDialog(null, "Email copied to clipboard.");
        });
        buttonPanel.add(copyEmailButton);

        JButton copyErrorButton = new JButton("Copy Error");
        copyErrorButton.addActionListener((ActionEvent ae) -> {
            StringSelection errorSelection = new StringSelection(errorMessage);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(errorSelection, null);
            JOptionPane.showMessageDialog(null, "Error copied to clipboard.");
        });
        buttonPanel.add(copyErrorButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(null,
                panel,
                "Error",
                JOptionPane.ERROR_MESSAGE);

        throw new IllegalArgumentException(errorMessage);
    }
   
	private static void TestSearch() {
		 // Test
        ArrayList<Object> dataToSearch = new ArrayList<>();
        dataToSearch.add(new TestNguoi("A1", 10, new BigDecimal("1000.00"), TrangThaiLamViec.DaNghiViec));
        dataToSearch.add(new TestNguoi("A2", 20, new BigDecimal("2000.00"), TrangThaiLamViec.DaNghiViec));
        dataToSearch.add(new TestNguoi("A3", 30, new BigDecimal("1500.50"), TrangThaiLamViec.DangLam));
        dataToSearch.add(new TestNguoi("B1", 40, new BigDecimal("3000.75"), TrangThaiLamViec.DaNghiViec));
        dataToSearch.add(new TestNguoi("B2", 50, new BigDecimal("3500.20"), TrangThaiLamViec.DangLam));
        dataToSearch.add(new TestNguoi("B3", 60, new BigDecimal("2500.30"), TrangThaiLamViec.DangLam));
        dataToSearch.add(new TestNguoi("C1", 70, new BigDecimal("10000.00"), TrangThaiLamViec.DaNghiViec));
        dataToSearch.add(new TestNguoi("C2", 80, new BigDecimal("200.00"), TrangThaiLamViec.DaNghiViec));

        ArrayList<Object> searchFields = new ArrayList<>();
        searchFields.add("B");
        searchFields.add(new TimKiemCTR.Limit<>(0, 20)); 
        searchFields.add(new TimKiemCTR.Limit<>(new BigDecimal("1000.00"), new BigDecimal("5000.00"))); 
        searchFields.add(TrangThaiLamViec.DaNghiViec); 

        ArrayList<TimKiemCTR.SearchCondition> conditions = new ArrayList<>();
        conditions.add(TimKiemCTR.SearchCondition.NONCONTIDION); 
        conditions.add(TimKiemCTR.SearchCondition.NONCONTIDION); 
        conditions.add(TimKiemCTR.SearchCondition.MATCH); 
        conditions.add(TimKiemCTR.SearchCondition.NONCONTIDION); 

        ArrayList<Object> result = TimKiemCTR.tim(dataToSearch, TestNguoi.class, searchFields, conditions);

        System.out.println("Matching Results:");
        for (Object obj : result) {
            TestNguoi nguoi = (TestNguoi) obj;
            System.out.println(nguoi.toString());
        }

	}

}
