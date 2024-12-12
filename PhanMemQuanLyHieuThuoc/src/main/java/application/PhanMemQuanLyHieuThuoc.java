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
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import connectDB.ConnectDB;
import controller.LoaiSanPhamCTR;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import customDataType.ChucVu;
import customDataType.TrangThaiLamViec;
import dao.SanPhamYTeDAO;
import entity.NhanVien;
import entity.SanPhamYTe;
import functionalClass.DataImporter;
import functionalClass.GlobalExceptionHandler;
import functionalClass.SearchTool;
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
		
        
		TrangChuUI trangChuUI = new TrangChuUI(false);

        
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

}