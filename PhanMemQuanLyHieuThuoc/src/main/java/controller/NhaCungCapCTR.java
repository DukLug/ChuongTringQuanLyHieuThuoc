package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import connectDB.ConnectDB;
import dao.NhaCungCapDAO;
import entity.NhaCungCap;
import userInterfaces.NhaCungCapUI;

public class NhaCungCapCTR {

//	private static List<NhaCungCap> danhSach;
	private static Object[][] danhSach;
	
	public static void ketNoiData() {
		ConnectDB.getInstance().connect();
		System.out.println("Connect!!");
	}
	
	public static Object[][] layData() {
	    danhSach = NhaCungCapDAO.getAllNhaCungCap();
	    return danhSach;
	}
	
	public static boolean themNCC(NhaCungCap ncc) {
		
		String maNCC = taoMa();
		ncc.setMaNhaCungCap(maNCC);
	
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
	
	private static String taoMa() {
	    String stt = "00000";
	    int soHienTai = Integer.parseInt(stt); 
	    soHienTai = danhSach.length + 1;
	   
	    String maNCC = String.format("NCC%05d", soHienTai);
	    
	    return maNCC;
	}
	
//	private static Object[][] timKiem(String maNCC, String tenNCC) {
//	    List<NhaCungCap> ketQua = new ArrayList<>();
//
//	    for (int i = 0; i < danhSach.length; i++) {
//	    	 NhaCungCap ncc = (NhaCungCap) danhSach[i][0];
//	    	if (ncc.getMaNhaCungCap().equals(maNCC))
//	    		ketQua.add(ncc);
//	    }
//	   
//	    Object[][] data = new Object[ketQua.size()][2]; 
//	    for (int i = 0; i < ketQua.size(); i++) {
//	        NhaCungCap ncc = ketQua.get(i);
//	        data[i][0] = ncc.getMaNhaCungCap();
//	        data[i][1] = ncc.getTenNhaCungCap();
//	        data[i][1] = ncc.getSdt();
//	        data[i][1] = ncc.getEmail();
//	        data[i][1] = ncc.getDiaChi();
//	    }
//
//	    return data;
//	}

	
	public static Object[][] timKiem(String thongTin) {
	    List<NhaCungCap> ketQua = new ArrayList<>();
	    thongTin = thongTin.toLowerCase();

	    // Duyệt qua danhSach để tìm kiếm nhà cung cấp
	    for (int i = 0; i < danhSach.length; i++) {
	        // Kiểm tra mã NCC (cột 0) và tên NCC (cột 1)
	        String maNCC = ((String) danhSach[i][0]).toLowerCase();
	        String tenNCC = ((String) danhSach[i][1]).toLowerCase();

	        if (maNCC.equals(thongTin) || tenNCC.contains(thongTin)) {
	            // Tạo đối tượng NhaCungCap từ danhSach và thêm vào kết quả
	            NhaCungCap ncc = new NhaCungCap(); // Tạo đối tượng mới
	            ncc.setMaNhaCungCap(maNCC);
	            ncc.setTenNhaCungCap(tenNCC);
	            ncc.setSdt((String) danhSach[i][2]); // Thêm SĐT từ cột 2
	            ncc.setEmail((String) danhSach[i][3]); // Thêm Email từ cột 3
	            ncc.setDiaChi((String) danhSach[i][4]); // Thêm Địa chỉ từ cột 4

	            ketQua.add(ncc); // Thêm nhà cung cấp vào kết quả
	        }
	    }

	    // Tạo mảng dữ liệu để trả về
	    Object[][] data = new Object[ketQua.size()][5]; // Kích thước để chứa đủ thông tin
	    for (int i = 0; i < ketQua.size(); i++) {
	        NhaCungCap ncc = ketQua.get(i);
	        data[i][0] = ncc.getMaNhaCungCap();
	        data[i][1] = ncc.getTenNhaCungCap();
	        data[i][2] = ncc.getSdt(); // SĐT
	        data[i][3] = ncc.getEmail(); // Email
	        data[i][4] = ncc.getDiaChi(); // Địa chỉ
	    }

	    return data;
	}




}
