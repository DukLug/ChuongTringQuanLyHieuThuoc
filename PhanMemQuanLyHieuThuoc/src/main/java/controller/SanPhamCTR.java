package controller;

import java.util.ArrayList;

import dao.SanPhamYTeDAO;
import entity.SanPhamYTe;
import userInterface.BanHangUI;

import userInterface.SanPhamYTeUI;

public class SanPhamCTR {
	
	public static void themSanPham(SanPhamYTeUI sanPhamUI) {
		
	}
	
	public static void kiemTraTonKho(BanHangUI banHangUI) {
		
	}
	
	public static void capNhatTonKho(BanHangUI banHangUI) {
		
	}
	
	public static void capNhatSoLuong(BanHangUI banHangUI) {

		
	}
	
	public static SanPhamYTe timSanPhamTheoMaVach(String maVachCanTim) {
		ArrayList<SanPhamYTe>  dssp = SanPhamYTeDAO.layDanhSachTatCaSanPhamYTe();
		
		for(SanPhamYTe sp : dssp) {
			String maVach = sp.getMaVach();
			if(maVach.equals(maVachCanTim)) {
				return sp;
			}
		}
		return null;
	}
	
	public static ArrayList<SanPhamYTe> layDanhSachTatCaSanPham() {
		return SanPhamYTeDAO.layDanhSachTatCaSanPhamYTe();
	}
}
