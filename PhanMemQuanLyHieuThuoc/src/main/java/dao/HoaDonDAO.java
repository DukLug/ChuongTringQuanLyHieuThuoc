package dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;

public class HoaDonDAO {
	// lấy thông tin khách hàng và hóa đơn theo mã hóa đơn
	public HoaDon layThongTinKhachHangTheoMaHoaDon(String maHoaDon) {
	    HoaDon chiTietHoaDon = null ;
	    String sql = "	select kh.HoTen, kh.Sdt, kh.DiemTichLuy, hd.MaHoaDon, hd.ThanhTien from HoaDon hd join KhachHang kh on hd.MaKhachHang = kh.MaKhachHang where MaHoaDon = ? ";

	    try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
	        ps.setString(1, maHoaDon); 
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	           String hoten = rs.getString("HoTen");
	           String sdt = rs.getString("Sdt");
	           int diemTichLuy = rs.getInt("DiemTichLuy");
	           String mahd = rs.getString("MaHoaDon");
	           BigDecimal thanhTien = rs.getBigDecimal("ThanhTien");
	           
	           KhachHang kh = new KhachHang(hoten, sdt, diemTichLuy);
	           
	           chiTietHoaDon = new HoaDon(kh,mahd,thanhTien);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return chiTietHoaDon;
	}

}
