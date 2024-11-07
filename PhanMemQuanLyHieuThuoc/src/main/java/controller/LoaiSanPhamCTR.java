package controller;

import java.util.ArrayList;

import dao.LoaiSanPhamDAO;
import entity.LoaiSanPham;

public class LoaiSanPhamCTR {
	public static ArrayList<LoaiSanPham> layDanhSachLoaiSanPham(){
		return LoaiSanPhamDAO.layDanhSachTatCaLoaiSanPham();
	}
}
