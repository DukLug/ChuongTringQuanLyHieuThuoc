package dao;

import java.util.ArrayList;

import entity.LoHang;

public class LoHangDAO {
	public static ArrayList<LoHang> danhSachLoHang;
	
	public static ArrayList<LoHang> layDanhSachTatCaLoHang(){
		return danhSachLoHang;
	}
}
