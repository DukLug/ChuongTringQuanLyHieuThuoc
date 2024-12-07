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
		ArrayList<SanPhamYTe>  dssp = SanPhamYTeDAO.layDanhSachTatCaSanPhamYTe1();
		
		for(SanPhamYTe sp : dssp) {
			String maVach = sp.getMaSanPham();
			if(maVach.equalsIgnoreCase(maVachCanTim)) {
				return sp;
			}
		}
		return null;
	}
	
	public static ArrayList<SanPhamYTe> layDanhSachTatCaSanPham() {
		return SanPhamYTeDAO.layDanhSachTatCaSanPhamYTe();
	}
	
	public static  ArrayList<SanPhamYTe> timSanPham(ArrayList<Object> searchFields, ArrayList<SearchCondition> conditions) {
	    // Sample data
	    ArrayList<Object> dataToSearch = new ArrayList<Object>();
	    for(SanPhamYTe sp : SanPhamYTeDAO.layDanhSachTatCaSanPhamYTe()) {
	    	dataToSearch.add(sp);
	    }


	    ArrayList<Object> result = SearchTool.search(dataToSearch, SanPhamYTe.class, searchFields, conditions);
	    ArrayList<SanPhamYTe> ketQuaTimSanPham = new ArrayList<SanPhamYTe>();
	    // Display results
	    System.out.println("Matching Results:");
	    for (Object obj : result) {
	        SanPhamYTe sp = (SanPhamYTe) obj;
	        ketQuaTimSanPham.add(sp);
	    }
	    return ketQuaTimSanPham;
	}

}
