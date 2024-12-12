package controller;

import java.util.ArrayList;

import dao.LoaiSanPhamDAO;
import entity.LoaiSanPham;

public class LoaiSanPhamCTR {
	public static ArrayList<LoaiSanPham> layDanhSachTatCaLoaiSanPham(){
		return LoaiSanPhamDAO.layDanhSachTatCaLoaiSanPham();
	}
	
	public static LoaiSanPham timTheoMa(String ma) {
		return LoaiSanPhamDAO.timLoaiSanPham(ma);
	}
	
	public static String layMaMoi() {
	    ArrayList<LoaiSanPham> dsLoaiSanPham = layDanhSachTatCaLoaiSanPham();
	    int maxNumber = 0;

	    // Iterate through the list to find the maximum numeric value
	    for (LoaiSanPham loaiSP : dsLoaiSanPham) {
	        String maLoai = loaiSP.getMaLoai();

	        // Extract the numeric part and convert it to an integer
	        if (maLoai != null && maLoai.startsWith("LSP")) {
	            try {
	                int number = Integer.parseInt(maLoai.substring(3));
	                if (number > maxNumber) {
	                    maxNumber = number;
	                }
	            } catch (NumberFormatException e) {
	                // Handle unexpected formats, log if necessary
	                System.err.println("Invalid format in maLoai: " + maLoai);
	            }
	        }
	    }

	    // Increment the maximum number and format it back into the LSPXXXXX format
	    int newNumber = maxNumber + 1;
	    String newMaLoai = String.format("LSP%03d", newNumber);

	    return newMaLoai;
	}

	
	public static void them(LoaiSanPham loaiMoi) {
		LoaiSanPhamDAO.them(loaiMoi);
		LoaiSanPhamDAO.layDanhSachTatCaLoaiSanPham();
		
	}
}
