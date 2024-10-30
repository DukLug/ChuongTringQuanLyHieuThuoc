package controller;

import java.util.ArrayList;
import connectDB.ConnectDB;
import dao.NhaCungCapDAO;
import entity.NhaCungCap;


public class NhaCungCapCTR {

	private static Object[][] danhSach;
	private static Object[][] data3Cot;
	
	public static void ketNoiData() {
		ConnectDB.getInstance().connect();
		System.out.println("Connect!!");
	}
	
	public static Object[][] layData() {
	    danhSach = NhaCungCapDAO.getAllNhaCungCap();
	    data3Cot = new Object[danhSach.length][3];
	    
	    for (int i = 0; i < danhSach.length; i++) {
	        data3Cot[i][0] = danhSach[i][0]; // Mã
	        data3Cot[i][1] = danhSach[i][1]; // Tên
	        data3Cot[i][2] = danhSach[i][2]; // Số điện thoại
	    }
	    
	    return data3Cot;
	}
	
	public static boolean themNCC(NhaCungCap ncc) {
	
		if (NhaCungCapDAO.them(ncc))
			return true;
		else
			return false;
	}
	
	public static boolean capNhatNCC(NhaCungCap ncc) {
		if (NhaCungCapDAO.capNhat(ncc))
			return true;
		else 
			return false;
	}
	
	public static String taoMa() {
	    String stt = "00000";
	    int soHienTai = Integer.parseInt(stt); 
	    soHienTai = danhSach.length + 1;
	   
	    String maNCC = String.format("NCC%05d", soHienTai);
	    
	    return maNCC;
	}

//	public static Object[][] timKiem(String thongTin){
//	    thongTin = thongTin.toLowerCase();
//	    int count = 0;
//	  
//	    // Đếm số lượng kết quả
//	    for (Object[] row : danhSach) {
//	        String maNCC = ((String) row[0]).toLowerCase();
//	        String tenNCC = ((String) row[1]).toLowerCase();
//	        if (maNCC.equals(thongTin) || tenNCC.contains(thongTin)) {
//	            count++;
//	        }
//	    }
//
//	    // Trả về mảng rỗng nếu không có kết quả
//	    if (count == 0) return new Object[0][0];
//
//	    // Tạo mảng kết quả
//	    Object[][] data = new Object[count][3];
//	    int index = 0;
//
//	    // Thêm kết quả vào mảng
//	    for (Object[] row : danhSach) {
//	        String maNCC = ((String) row[0]).toLowerCase();
//	        String tenNCC = ((String) row[1]).toLowerCase();
//	        if (maNCC.equals(thongTin) || tenNCC.contains(thongTin)) {
//	            data[index++] = new Object[]{row[0], row[1], row[2]};
//	        }
//	    }
//
//	    return data;
//	}
	
	public static Object[][] timKiem(String thongTin) {
	    thongTin = thongTin.toLowerCase();
	    ArrayList<Object[]> ketQua = new ArrayList<>();  // Tạo danh sách tạm để lưu các kết quả
	    
	    // Duyệt danh sách và thêm các dòng phù hợp vào danh sách tạm
	    for (Object[] row : danhSach) {
	        String maNCC = ((String) row[0]).toLowerCase();
	        String tenNCC = ((String) row[1]).toLowerCase();
	        if (maNCC.equals(thongTin) || tenNCC.contains(thongTin)) {
	            ketQua.add(new Object[]{row[0], row[1], row[2]});
	        }
	    }
	    
	    // Chuyển danh sách kết quả thành mảng hai chiều
	    return ketQua.toArray(new Object[0][0]);
	}
	
	public static NhaCungCap timKiemTheoMa(String thongTin) {
	    for (Object[] row : danhSach) {
	        String maNCC = ((String) row[0]).toLowerCase();
	        if (maNCC.equals(thongTin.toLowerCase())) {
	            String tenNCC = (String) row[1];  
	            String sdt = (String) row[2];
	            String email = (String) row[3];
	            String diaChi = (String) row[4]; 
	           
	            return new NhaCungCap(maNCC, tenNCC, sdt, email, diaChi);
	        }
	    }
	    return null;
	}

}
