package controller;

import java.util.ArrayList;

import dao.LoHangDAO;
import entity.LoHang;

public class KhoCTR {
	public static ArrayList<LoHang> layDanhSachTatCaLoHang(){
		return LoHangDAO.layDanhSachTatCaLoHang();
	}
}
