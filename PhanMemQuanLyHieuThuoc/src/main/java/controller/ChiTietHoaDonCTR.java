package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import entity.ChiTietHoaDon;

public class ChiTietHoaDonCTR {

	private static ChiTietHoaDonDAO chiTietHoaDonDAO;

    public ChiTietHoaDonCTR() {
        this.chiTietHoaDonDAO = new ChiTietHoaDonDAO(); 
    }

    // Hàm tìm chi tiết hóa đơn theo mã hóa đơn
    
    public ArrayList<ChiTietHoaDon> timChiTietHoaDonTheoMaHoaDon(String maHoaDon) {
        return chiTietHoaDonDAO.timChiTietHoaDonTheoMaHoaDon(maHoaDon);
    }
    

    
    public static ArrayList<ChiTietHoaDon> layDSCTHDTheoMa(String maHoaDon) {
        return chiTietHoaDonDAO.layDSCTHDTheoMa(maHoaDon);
    }

}
