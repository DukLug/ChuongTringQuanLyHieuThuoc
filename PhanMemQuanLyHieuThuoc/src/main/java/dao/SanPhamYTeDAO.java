package dao;

import java.util.ArrayList;

import entity.SanPhamYTe;

public class SanPhamYTeDAO {
	public static ArrayList<SanPhamYTe> sanPhamYTe;
	
	public static ArrayList<SanPhamYTe> layDanhSachTatCaSanPhamYTe(){
		return sanPhamYTe;
	}
	
}
