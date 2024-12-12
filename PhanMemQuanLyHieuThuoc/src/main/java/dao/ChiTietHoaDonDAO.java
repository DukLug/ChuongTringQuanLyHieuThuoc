package dao;


import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import customDataType.DonViTinh;
import entity.ChiTietHoaDon;
import entity.SanPhamYTe;

public class ChiTietHoaDonDAO {
	
	
	
	
	public ArrayList<ChiTietHoaDon> timChiTietHoaDonTheoMaHoaDon(String maHoaDon) {
	    ArrayList<ChiTietHoaDon> chiTietHoaDons = new ArrayList<>();
	    String sql = "select sp.MaSanPham, sp.TenSanPham, sp.GiaVonDonViTinh1, sp.GiaBanDonViTinh2, sp.GiaBanDonViTinh3, " +
	                 "sp.DonViTinh1, sp.DonViTinh2, sp.DonViTinh3, cthd.SoLuongDonViTinh1, cthd.SoLuongDonViTinh2, cthd.SoLuongDonViTinh3, " +
	                 "cthd.TongTien from ChiTietHoaDon cthd join SanPhamYTe sp on cthd.MaSanPham = sp.MaSanPham where cthd.MaHoaDon =?";

	    try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
	        ps.setString(1, maHoaDon); 
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {

	            String maSanPham = rs.getString("MaSanPham");
	            String tenSanPham = rs.getString("TenSanPham");
	            BigDecimal giaVonDonViTinh1 = rs.getBigDecimal("GiaVonDonViTinh1");
	            BigDecimal giaBanDonViTinh2 = rs.getBigDecimal("GiaBanDonViTinh2");
	            BigDecimal giaBanDonViTinh3 = rs.getBigDecimal("GiaBanDonViTinh3");

	            DonViTinh donViTinh1 = DonViTinh.fromString(rs.getString("DonViTinh1"));
	            DonViTinh donViTinh2 = DonViTinh.fromString(rs.getString("DonViTinh2"));
	            DonViTinh donViTinh3 = DonViTinh.fromString(rs.getString("DonViTinh3"));

	            int soLuongDonViTinh1 = rs.getInt("SoLuongDonViTinh1");
	            int soLuongDonViTinh2 = rs.getInt("SoLuongDonViTinh2");
	            int soLuongDonViTinh3 = rs.getInt("SoLuongDonViTinh3");

	            BigDecimal tongTien = rs.getBigDecimal("TongTien");

	            // Khởi tạo đối tượng SanPhamYTe
	            SanPhamYTe sanPhamYTe = new SanPhamYTe(maSanPham, tenSanPham, giaVonDonViTinh1, giaBanDonViTinh2, giaBanDonViTinh3, 
	                                                    donViTinh1, donViTinh2, donViTinh3);

	            // Khởi tạo đối tượng ChiTietHoaDon với các thông tin cho từng đơn vị tính
	            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(sanPhamYTe, soLuongDonViTinh1, soLuongDonViTinh2, soLuongDonViTinh3, tongTien);

	            chiTietHoaDons.add(chiTietHoaDon);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return chiTietHoaDons;
	}

	
	

}
	

