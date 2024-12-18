package controller;


import java.sql.Date;
import java.util.ArrayList;

import dao.ChiTietDonNhapDAO;
import dao.DonHuyHangDAO;
import dao.DonNhapHangDAO;
import dao.LoHangDAO;
import entity.ChiTietDonNhap;
import entity.DonHuyHang;
import entity.DonNhapHang;

import java.util.ArrayList;

import dao.LoHangDAO;

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
	
	public static ArrayList<DonHuyHang> layDanhSachTatCaDonHuy(){
		return DonHuyHangDAO.layDanhSachTatCaDonHuyHang();
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
	
	public static ArrayList<DonHuyHang> layDanhSachDonHuyTheoNgay(Date fromDate, Date toDate) {
	    ArrayList<DonHuyHang> result = new ArrayList<DonHuyHang>();
	    ArrayList<DonHuyHang> data = layDanhSachTatCaDonHuy();

	    for (DonHuyHang dnh : data) {
	        if (dnh.getNgayHuy().compareTo(fromDate) >= 0 && dnh.getNgayHuy().compareTo(toDate) <= 0) {
	            result.add(dnh);
	        }
	    }

	    return result;
	}
	
	public static boolean capNhatLoHang(LoHang loHang) {
		return LoHangDAO.capNhatLoHang(loHang);
	}
	
	public static LoHang timLoHang(String maLo) {
		ArrayList<LoHang> data = layDanhSachTatCaLoHang();
		for(int i=0; i<data.size(); i++) {
			if(data.get(i).getMaLo() == maLo) {
				return data.get(i);
			}
		}
		return null;
	}
	
	public static boolean themDonHuy(DonHuyHang donHuy) {
		return DonHuyHangDAO.themDonHuyHang(donHuy);
	}
	public static String layMaDonHuyMoi() {
        ArrayList<DonHuyHang> data = layDanhSachTatCaDonHuy(); // Get the current list of objects
        
        // Initialize the highest number found to 0
        int maxNumber = 0;

        for (DonHuyHang donHuy : data) {
            String currentId = donHuy.getMaDonHuyHang(); // Get the current ID
            
            // Check if the ID starts with "DHH" and has a valid format
            if (currentId.startsWith("DHH") && currentId.length() == 8) {
                try {
                    // Extract the numeric part of the ID
                    int numberPart = Integer.parseInt(currentId.substring(3));
                    
                    // Update maxNumber if the current number is larger
                    if (numberPart > maxNumber) {
                        maxNumber = numberPart;
                    }
                } catch (NumberFormatException e) {
                    // Handle the case where the numeric part is invalid (optional)
                    System.err.println("Invalid ID format: " + currentId);
                }
            }
        }

        // Generate the next ID by incrementing the highest number found
        int nextNumber = maxNumber + 1;
        
        // Format the new ID with leading zeros
        String newId = String.format("DHH%05d", nextNumber);

        return newId;
    }
}

