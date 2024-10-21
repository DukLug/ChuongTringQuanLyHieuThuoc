package application;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.*;

import controller.TimKiemCTR;
import customEnum.TrangThaiLamViec;
import enity.NhanVien;
import entity.TestNguoi;
import userInterfaces.*;

public class PhanMemQuanLyHieuThuoc {
	public static final boolean HienLoi = true;
	
	public static void main(String[] args) {
		System.setProperty("sun.java2d.uiScale", "1.0");
        TrangChuUI trangChuUI = new TrangChuUI();
        // Test
        ArrayList<Object> dataToSearch = new ArrayList<>();
        dataToSearch.add(new TestNguoi("A1", 10, new BigDecimal("1000.00"), TrangThaiLamViec.DANGHI));
        dataToSearch.add(new TestNguoi("A2", 20, new BigDecimal("2000.00"), TrangThaiLamViec.DANGLAM));
        dataToSearch.add(new TestNguoi("A3", 30, new BigDecimal("1500.50"), TrangThaiLamViec.DANGHI));
        dataToSearch.add(new TestNguoi("B1", 40, new BigDecimal("3000.75"), TrangThaiLamViec.DANGHI));
        dataToSearch.add(new TestNguoi("B2", 50, new BigDecimal("3500.20"), TrangThaiLamViec.DANGLAM));
        dataToSearch.add(new TestNguoi("B3", 60, new BigDecimal("2500.30"), TrangThaiLamViec.DANGHI));
        dataToSearch.add(new TestNguoi("C1", 70, new BigDecimal("10000.00"), TrangThaiLamViec.DANGHI));
        dataToSearch.add(new TestNguoi("C2", 80, new BigDecimal("200.00"), TrangThaiLamViec.DANGLAM));
        dataToSearch.add(new TestNguoi("C3", 90, new BigDecimal("500.00"), TrangThaiLamViec.DANGHI));
        dataToSearch.add(new TestNguoi("D1", 100, new BigDecimal("7000.00"), TrangThaiLamViec.DANGLAM));
        dataToSearch.add(new TestNguoi("D2", 110, new BigDecimal("300.00"), TrangThaiLamViec.DANGHI));
        dataToSearch.add(new TestNguoi("D3", 120, new BigDecimal("400.00"), TrangThaiLamViec.DANGLAM));
        dataToSearch.add(new TestNguoi("E1", 130, new BigDecimal("5000.00"), TrangThaiLamViec.DANGHI));
        dataToSearch.add(new TestNguoi("E2", 140, new BigDecimal("6000.50"), TrangThaiLamViec.DANGHI));
        dataToSearch.add(new TestNguoi("E3", 150, new BigDecimal("100.00"), TrangThaiLamViec.DANGLAM));
        dataToSearch.add(new TestNguoi("F1", 160, new BigDecimal("9000.00"), TrangThaiLamViec.DANGHI));
        dataToSearch.add(new TestNguoi("F2", 170, new BigDecimal("8500.75"), TrangThaiLamViec.DANGHI));
        dataToSearch.add(new TestNguoi("F3", 180, new BigDecimal("7500.25"), TrangThaiLamViec.DANGLAM));
        dataToSearch.add(new TestNguoi("G1", 190, new BigDecimal("6500.30"), TrangThaiLamViec.DANGHI));
        dataToSearch.add(new TestNguoi("G2", 200, new BigDecimal("5500.40"), TrangThaiLamViec.DANGLAM));

        ArrayList<Object> searchFields = new ArrayList<>();
        searchFields.add("B");
        searchFields.add(new TimKiemCTR.Limit<>(0, 20)); 
        searchFields.add(new TimKiemCTR.Limit<>(new BigDecimal("1000.00"), new BigDecimal("5000.00"))); 
        searchFields.add(TrangThaiLamViec.DANGHI); 

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
