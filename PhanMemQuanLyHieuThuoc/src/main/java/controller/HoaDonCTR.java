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
	public static void ketNoiDatabase() {
		// TODO Auto-generated method stub
		ConnectDB.getInstance().connect();	
	}

//		String [] headers = {"Mã hóa đơn","Loại hóa đơn", "Thời gian", "Số lượng", "Doanh thu", "Phí trả hàng", "Thực thu"};
//		0,;oai, 1,soluong, 3,phitrahang, thucthu

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
	

	
	
}
