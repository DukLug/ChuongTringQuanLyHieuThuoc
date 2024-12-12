package controller;

import java.util.ArrayList;

import dao.TaiKhoanDAO;
import entity.TaiKhoan;

public class TaiKhoanCTR {
	public static ArrayList<TaiKhoan> layDanhSachTatCaTaiKhoan() {
		return TaiKhoanDAO.layDanhSachTatCaTaiKhoan();
	}
	
	public static boolean themTaiKhoan(TaiKhoan taiKhoan) {
		return TaiKhoanDAO.them(taiKhoan);
	}
	
	public static boolean capNhatTaiKhoan(TaiKhoan taiKhoan) {
		return TaiKhoanDAO.capNhat(taiKhoan);
	}
	public static TaiKhoan timTheoMa(String ma) {
		return TaiKhoanDAO.timTaiKhoan(ma);
	}
	public static String hashPassword(String password) {
	    if (password == null || password.isEmpty()) {
	        return "";
	    }

	    long hash = 5381;
	    for (char c : password.toCharArray()) {
	        hash = ((hash << 5) + hash) + c; // hash * 33 + c
	    }

	    // Ensure the result is positive and convert it to a string
	    String hashString = String.valueOf(Math.abs(hash));

	    // Truncate or pad the result to ensure it is at most 20 characters
	    if (hashString.length() > 20) {
	        hashString = hashString.substring(0, 20);
	    }

	    return hashString;
	}
}
