package controller;

import java.util.ArrayList;

import dao.ChiTietHoaDonDAO;
import entity.ChiTietHoaDon;

public class ChiTietHoaDonCTR {

	private ChiTietHoaDonDAO chiTietHoaDonDAO;

    public ChiTietHoaDonCTR() {
        this.chiTietHoaDonDAO = new ChiTietHoaDonDAO(); 
    }

    // Hàm tìm chi tiết hóa đơn theo mã hóa đơn
    public ArrayList<ChiTietHoaDon> timChiTietHoaDonTheoMaHoaDon(String maHoaDon) {
        return chiTietHoaDonDAO.timChiTietHoaDonTheoMaHoaDon(maHoaDon);
    }
}
