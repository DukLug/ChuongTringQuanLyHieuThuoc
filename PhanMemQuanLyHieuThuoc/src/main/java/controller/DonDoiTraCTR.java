package controller;

import connectDB.ConnectDB;
import dao.DonDoiTraDAO;


public class DonDoiTraCTR {

	private static Object[][] danhSachDonDoiTra;
	private static Object[][] dataBCDoiTraCuoiNgay;
	public static void ketNoiDatabase() {
		// TODO Auto-generated method stub
		ConnectDB.getInstance().connect();	
	}

	public static Object[][] layDataHDDoiTra() {
	    // Lấy danh sách từ DAO
	    danhSachDonDoiTra = DonDoiTraDAO.layDanhSachHoaDonDoiTra();
	    
	    // Khởi tạo mảng cho báo cáo
	    dataBCDoiTraCuoiNgay = new Object[danhSachDonDoiTra.length][6]; // Không cần giảm 1
	    
	    for (int i = 0; i < danhSachDonDoiTra.length; i++) { // Bắt đầu từ 0
	        dataBCDoiTraCuoiNgay[i][0] = danhSachDonDoiTra[i][0]; // Mã
	        dataBCDoiTraCuoiNgay[i][1] = danhSachDonDoiTra[i][1]; // Ngày
	        dataBCDoiTraCuoiNgay[i][2] = 0; // Hoặc giá trị tương ứng
	        dataBCDoiTraCuoiNgay[i][3] = "DonTra";
	        dataBCDoiTraCuoiNgay[i][4] = danhSachDonDoiTra[i][2]; // Tiền hoàn
	        dataBCDoiTraCuoiNgay[i][5] = danhSachDonDoiTra[i][2]; // Hoặc giá trị tương ứng
	    }
	    
	    return dataBCDoiTraCuoiNgay; // Trả về mảng báo cáo
	}


}
