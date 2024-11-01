package controller;

import java.util.ArrayList;

import connectDB.ConnectDB;
import dao.KhachHangDAO;
import dao.NhaCungCapDAO;
import entity.KhachHang;
import entity.NhaCungCap;
import entity.NhanVien;

public class KhachHangCTR {
	private static Object[][] danhSach;
	private static Object[][] data4Cot;
	private static ArrayList<Object[]> ketQua = new ArrayList<>();
	
	public static void ketNoiData() {
		ConnectDB.getInstance().connect();
//		System.out.println("Connect!!");
	}
	
	public static Object[][] layData() {
	    danhSach = KhachHangDAO.getAllKhachHang();
	 // Giảm kích thước của mảng data4Cot xuống 1 để bỏ qua dòng đầu tiên
	    data4Cot = new Object[danhSach.length - 1][4];
	    
	    for (int i = 1; i < danhSach.length; i++) {
	        data4Cot[i - 1][0] = danhSach[i][0]; // Mã
	        data4Cot[i - 1][1] = danhSach[i][1]; // Tên
	        data4Cot[i - 1][2] = danhSach[i][2]; // Số điện thoại
	        data4Cot[i - 1][3] = danhSach[i][5]; // Điểm tích lũy
	    }
	    
	    return data4Cot;
	}
	
	public static boolean themKH(KhachHang kh) {
		if (KhachHangDAO.them(kh))
			return true;
		else
			return false;
	}
	
	public static boolean capNhatKH(KhachHang kh) {
		if (KhachHangDAO.capNhat(kh))
			return true;
		else 
			return false;
	}
	
	public static String taoMa() {
	    String stt = "000000";
	    int soHienTai = Integer.parseInt(stt); 
	    soHienTai = danhSach.length + 1;
	   
	    String maKH = String.format("KH%06d", soHienTai);
	    
	    return maKH;
	}
	
	public static Object[][] timKiem(String thongTin) { 
		ketQua = new ArrayList<>();
	    if (thongTin == null || thongTin.trim().isEmpty()) {
	        return data4Cot;
	    }
	    
	    for (Object[] row : danhSach) {
	        String maKH = ((String) row[0]);
	        String sdt = ((String) row[2]);
	        if (maKH.equalsIgnoreCase(thongTin) || sdt.equals(thongTin)) {
	            ketQua.add(new Object[]{row[0], row[1], row[2], row[5]});
	        }
	    }
	    
	    // Chuyển danh sách kết quả thành mảng hai chiều
	    return ketQua.toArray(new Object[0][0]);
	}
	
	public static KhachHang timKiemTheoMa(String thongTin) {
	    for (Object[] row : danhSach) {
	        String maKH = ((String) row[0]);
	        if (maKH.equalsIgnoreCase(thongTin)) {
	            String tenKH = (String) row[1];  
	            String sdt = (String) row[2];
	            String cccd = (String) row[3];
	            String diaChi = (String) row[4]; 
	            int diemTichLuy = (int) row[5];
	           
	            return new KhachHang(maKH, tenKH, sdt, cccd, diaChi, diemTichLuy);
	        }
	    }
	    return null;
	}
	
	public static boolean kiemTraTrung(String maKH) {
		for (Object[] row : danhSach) {
	        if (row[0] != null && row[0].toString().equalsIgnoreCase(maKH)) {
	            return true; 
	        }
	    }
	    return false; 
	}
	
	public static Object[][] locDTL(int batDau, int ketThuc) {
		ketQua = new ArrayList<>();
		
		for (Object[] row : danhSach) {
			int dtl =  (int) row[5];
			if (dtl >= batDau && dtl <= ketThuc) 
				ketQua.add(new Object[]{row[0], row[1], row[2], row[5]});
		}
		    
		// Chuyển danh sách kết quả thành mảng hai chiều
		return ketQua.toArray(new Object[0][0]);
	}
}
