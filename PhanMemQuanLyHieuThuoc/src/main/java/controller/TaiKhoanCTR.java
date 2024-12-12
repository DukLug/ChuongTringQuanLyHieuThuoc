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
}
