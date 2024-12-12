package controller;

import java.sql.Date;
import java.util.ArrayList;

import dao.ChiTietDonNhapDAO;
import dao.DonNhapHangDAO;
import dao.LoHangDAO;
import entity.ChiTietDonNhap;
import entity.DonNhapHang;
import entity.LoHang;

public class KhoCTR {
	public static ArrayList<LoHang> layDanhSachTatCaLoHang(){
		return LoHangDAO.layDanhSachTatCaLoHang();
	}
	
	public static ArrayList<DonNhapHang> layDanhSachTatCaDonNhap(){
		return DonNhapHangDAO.layDanhSachTatCaDonNhapHang();
	}
	
	public static ArrayList<ChiTietDonNhap>  layDanhSachChiTietDonNhap(String maDon){
		return ChiTietDonNhapDAO.layDanhSachChiTietDonNhapTheoMaDonNhap(maDon);
	}
	
	public static ArrayList<DonNhapHang> layDanhSachDonNhapTheoNgay(Date fromDate, Date toDate) {
	    ArrayList<DonNhapHang> result = new ArrayList<DonNhapHang>();
	    ArrayList<DonNhapHang> data = layDanhSachTatCaDonNhap();

	    for (DonNhapHang dnh : data) {
	        if (dnh.getNgayNhap().compareTo(fromDate) >= 0 && dnh.getNgayNhap().compareTo(toDate) <= 0) {
	            result.add(dnh);
	        }
	    }

	    return result;
	}
	
	public static boolean capNhatLoHang(LoHang loHang) {
		return LoHangDAO.capNhatLoHang(loHang);
	}
}
