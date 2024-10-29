package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import component.CustomButton;
import connectDB.ConnectDB;
import dao.NhaCungCapDAO;
import entity.NhaCungCap;
//import userInterfaces.NhaCungCapUI;

public class NhaCungCapCTR {

//	private static List<NhaCungCap> danhSach;
	private static Object[][] danhSach;
	
	public static void ketNoiData() {
		ConnectDB.getInstance().connect();
		System.out.println("Connect!!");
	}
	
	public static Object[][] layData() {
	    danhSach = NhaCungCapDAO.getAllNhaCungCap();
	    
	 // Tạo một mảng mới chỉ chứa 3 cột
	    Object[][] data3Cot = new Object[danhSach.length][3];
	    
	    for (int i = 0; i < danhSach.length; i++) {
	        data3Cot[i][0] = danhSach[i][0]; // Mã
	        data3Cot[i][1] = danhSach[i][1]; // Tên
	        data3Cot[i][2] = danhSach[i][2]; // Số điện thoại
	    }
	    
	    danhSach = data3Cot;
	    
	    return danhSach;
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

//	public static Object[][] timKiem(String thongTin) {
//	    List<NhaCungCap> ketQua = new ArrayList<>();
//	    thongTin = thongTin.toLowerCase();
//
//	    // Duyệt qua danhSach để tìm kiếm nhà cung cấp
//	    for (int i = 0; i < danhSach.length; i++) {
//	        String maNCC = ((String) danhSach[i][0]).toLowerCase();
//	        String tenNCC = ((String) danhSach[i][1]).toLowerCase();
//
//	        if (maNCC.equals(thongTin) || tenNCC.contains(thongTin)) {
//	            NhaCungCap ncc = new NhaCungCap(); // Tạo đối tượng mới
////	            ncc.setMaNhaCungCap(maNCC);
//	            ncc.setTenNhaCungCap(tenNCC);
//	            ncc.setSdt((String) danhSach[i][2]); // Thêm SĐT từ cột 2
////	            ncc.setEmail((String) danhSach[i][3]); // Thêm Email từ cột 3
////	            ncc.setDiaChi((String) danhSach[i][4]); // Thêm Địa chỉ từ cột 4
//
//	            ketQua.add(ncc); // Thêm nhà cung cấp vào kết quả
//	        }
//	    }
//
//	    // Tạo mảng dữ liệu để trả về
//	    Object[][] data = new Object[ketQua.size()][3]; // Kích thước để chứa đủ thông tin
//	    for (int i = 0; i < ketQua.size(); i++) {
//	        NhaCungCap ncc = ketQua.get(i);
//	        data[i][0] = ncc.getMaNhaCungCap();
//	        data[i][1] = ncc.getTenNhaCungCap();
//	        data[i][2] = ncc.getSdt(); // SĐT
////	        data[i][3] = ncc.getEmail(); // Email
////	        data[i][4] = ncc.getDiaChi(); // Địa chỉ
//	    }
//
//    
//	    return data;
//	}
	
	public static Object[][] timKiem(String thongTin){
	    thongTin = thongTin.toLowerCase();
	    int count = 0;
	  
	    // Đếm số lượng kết quả
	    for (Object[] row : danhSach) {
	        String maNCC = ((String) row[0]).toLowerCase();
	        String tenNCC = ((String) row[1]).toLowerCase();
	        if (maNCC.equals(thongTin) || tenNCC.contains(thongTin)) {
	            count++;
	        }
	    }

	    // Trả về mảng rỗng nếu không có kết quả
	    if (count == 0) return new Object[0][0];

	    // Tạo mảng kết quả
	    Object[][] data = new Object[count][3];
	    int index = 0;

	    // Thêm kết quả vào mảng
	    for (Object[] row : danhSach) {
	        String maNCC = ((String) row[0]).toLowerCase();
	        String tenNCC = ((String) row[1]).toLowerCase();
	        if (maNCC.equals(thongTin) || tenNCC.contains(thongTin)) {
	            data[index++] = new Object[]{row[0], row[1], row[2]};
	        }
	    }

	    return data;
	}

}
