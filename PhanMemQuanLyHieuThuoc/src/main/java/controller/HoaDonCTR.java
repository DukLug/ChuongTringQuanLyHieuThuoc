package controller;

import java.util.ArrayList;
import java.util.Arrays;

import connectDB.ConnectDB;
import dao.HoaDonDAO;
import entity.DonDoiTra;
import entity.HoaDon;

public class HoaDonCTR {

	private static Object[][] danhSach;
	private static Object[][] dataBCCuoiNgay;
	private static Object[][] dataHD;
	public static void ketNoiDatabase() {
		ConnectDB.getInstance().connect();	
	}

	public static Object[][] layDataHDBan() {
//        danhSach = HoaDonDAO.layDanhSachHoaDonBanHangTheoNgay();
        danhSach = HoaDonDAO.layDanhSachHoaDon();
        
        dataBCCuoiNgay = new Object[danhSach.length][6];
        for (int i =0; i < danhSach.length; i++) {
        	dataBCCuoiNgay[i][0] = danhSach[i][0]; // Mã
        	dataBCCuoiNgay[i][1] = danhSach[i][1];// ngày
        	dataBCCuoiNgay[i][2] = danhSach[i][3];// doanhthu
        	dataBCCuoiNgay[i][3] = "Bán hàng";
        	dataBCCuoiNgay[i][4] = 0;
        	dataBCCuoiNgay[i][5] = danhSach[i][3];
        }
        
		
        return dataBCCuoiNgay;
    }

	public static Object[][] layDataHD() {
		danhSach = HoaDonDAO.layDanhSachHoaDon();
//		String[] headers = { "Mã hóa đơn", "Tổng tiền", "Ngày Bán", "Mã Nhân Viên", "Mã Khách Hàng" };
//
		dataHD = new Object[danhSach.length][5];
        for (int i =0; i < danhSach.length; i++) {
        	dataHD[i][0] = danhSach[i][0]; // Mã
        	dataHD[i][1] = danhSach[i][3];// ngày
        	dataHD[i][2] = danhSach[i][1];// doanhthu
        	dataHD[i][3] = danhSach[i][4];
        	dataHD[i][4] = danhSach[i][6];

        }
        
		
        return dataHD;
	}
	

	
	
}
