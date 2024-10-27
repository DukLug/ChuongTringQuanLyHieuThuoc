package controller;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import connectDB.ConnectDB;
import dao.NhaCungCapDAO;
import entity.NhaCungCap;
import userInterfaces.NhaCungCapUI;

public class NhaCungCapCTR {

	private static List<NhaCungCap> danhSach;
	
	public static void ketNoiData() {
		ConnectDB.getInstance().connect();
		System.out.println("Connect!!");
	}
	
	public static Object[][] layData() {
	    danhSach = NhaCungCapDAO.getAllNhaCungCap();
	    
	    // Tạo mảng 2 chiều với số hàng là kích thước của danhSach và số cột là 3
	    Object[][] data = new Object[danhSach.size()][5];

	    for (int i = 0; i < danhSach.size(); i++) {
	        NhaCungCap ncc = danhSach.get(i);
	        data[i][0] = ncc.getMaNhaCungCap();  
	        data[i][1] = ncc.getTenNhaCungCap(); 
	        data[i][2] = ncc.getSdt();
	        data[i][3] = ncc.getEmail(); 
	        data[i][4] = ncc.getDiaChi();
	    }

	    return data;
	}
	
	public static boolean themNCC(NhaCungCap ncc) {
		
		int soLuongBanDau = danhSach.size();
		String maNCC = taoMa();
		
		ncc.setMaNhaCungCap(maNCC);
		
		NhaCungCapDAO.them(ncc);
		
		if (danhSach.size() > soLuongBanDau)
			return true;
		else
			return false;
	}
	
	private static String taoMa() {
	    String stt = "00000";
	    int soHienTai = Integer.parseInt(stt); 
	    soHienTai = danhSach.size() + 1;
	   
	    String maNCC = String.format("NCC%05d", soHienTai);
	    
	    return maNCC;
	}

}
