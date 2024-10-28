package application;

import java.awt.Dimension;
import java.awt.Toolkit;
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
import customDataType.TrangThaiLamViec;
import entity.NhanVien;
import functionalClass.DataImporter;
import testEntity.TestNguoi;
import testEntity.Thuoc;
import userInterface.*;
import userInterface.NhapHangUI.NhapHangRow;

public class PhanMemQuanLyHieuThuoc {
	public static final boolean HienLoi = true;
	public static ArrayList<Thuoc> thuocList;

	   public static void main(String[] args) {
			System.setProperty("sun.java2d.uiScale", "1.0");
			thuocList = DataImporter.readThuocFromExcel();
	        TrangChuUI trangChuUI = new TrangChuUI(false);
	        TestSearch();
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
