package controller;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

import customDataType.DonViTinh;
import customDataType.TrangThaiSanPham;
import dao.SanPhamYTeDAO;
import entity.LoaiSanPham;
import entity.NhaCungCap;
import entity.SanPhamYTe;
import functionalClass.SearchTool;
import functionalClass.SearchTool.SearchCondition;
import userInterface.BanHangUI;

import userInterface.TaiKhoanUI;

public class SanPhamCTR {
	
	public static void themSanPham(TaiKhoanUI sanPhamUI) {
		
	}
	
	public static void kiemTraTonKho(BanHangUI banHangUI) {
		
	}
	
	public static void capNhatTonKho(BanHangUI banHangUI) {
		
	}
	
	public static void capNhatSoLuong(BanHangUI banHangUI) {

		
	}
	
	public static SanPhamYTe timSanPhamTheoMaVach(String maVachCanTim) {
		ArrayList<SanPhamYTe>  dssp = SanPhamYTeDAO.layDanhSachTatCaSanPham();
		
		for(SanPhamYTe sp : dssp) {
			String maVach = sp.getMaVach();
			if(maVach.equalsIgnoreCase(maVachCanTim)) {
				return sp;
			}
		}
		return null;
	}
	public static SanPhamYTe timSanPhamTheoMaSanPham(String maSPCanTim) {
		ArrayList<SanPhamYTe>  dssp = SanPhamYTeDAO.layDanhSachTatCaSanPham();
		
		for(SanPhamYTe sp : dssp) {
			String maVach = sp.getMaSanPham();
			if(maVach.equalsIgnoreCase(maSPCanTim)) {
				return sp;
			}
		}
		return null;
	}
	
	
	public static ArrayList<SanPhamYTe> layDanhSachTatCaSanPham() {
		return SanPhamYTeDAO.layDanhSachTatCaSanPham();
	}
	
	public static  ArrayList<SanPhamYTe> timSanPham(ArrayList<Object> searchFields, ArrayList<SearchCondition> conditions) {
	    // Sample data
	    ArrayList<Object> dataToSearch = new ArrayList<Object>();
	    for(SanPhamYTe sp : SanPhamYTeDAO.layDanhSachTatCaSanPham()) {
	    	dataToSearch.add(sp);
	    	System.out.println(sp.toString());
	    }
	    


	    ArrayList<Object> result = SearchTool.search(dataToSearch, SanPhamYTe.class, searchFields, conditions);
	    ArrayList<SanPhamYTe> ketQuaTimSanPham = new ArrayList<SanPhamYTe>();
	    // Display results
	    System.out.println("Matching Results: " + result.size());
	    for (Object obj : result) {
	        SanPhamYTe sp = (SanPhamYTe) obj;
	        ketQuaTimSanPham.add(sp);
	    }
	    return ketQuaTimSanPham;
	}
	
	public static String layMa() {
		return "";
	}

	public static void capNhatNgungBan(SanPhamYTe sanPham) {
		sanPham.setTrangThaiSanPham(TrangThaiSanPham.NgungBan);
		SanPhamYTeDAO.updateSanPhamYTe(sanPham);
	}
	
	public static void capNhatBan(SanPhamYTe sanPham) {
		sanPham.setTrangThaiSanPham(TrangThaiSanPham.DangBan);
		SanPhamYTeDAO.updateSanPhamYTe(sanPham);
	}

}



