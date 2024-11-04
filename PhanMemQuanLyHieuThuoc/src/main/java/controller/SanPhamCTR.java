package controller;

import java.util.ArrayList;

import dao.SanPhamYTeDAO;
import entity.SanPhamYTe;
import userInterface.BanHangUI_dau;
import userInterface.BanHangUI;

import userInterface.SanPhamYTeUI;

public class SanPhamCTR {
	
	public static void themSanPham(SanPhamYTeUI sanPhamUI) {
		
	}
	
	public static void kiemTraTonKho(BanHangUI_dau banHangUI) {
		
	}
	
	public static void capNhatTonKho(BanHangUI_dau banHangUI) {
		
	}
	
	public static void capNhatSoLuong(BanHangUI_dau banHangUI) {

		
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
