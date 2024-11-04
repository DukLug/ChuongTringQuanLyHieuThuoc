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
	
	// tìm chi tiết hóa đơn theo mã hóa đơn, hiển thị thông tin sản phẩm trong chi tiết hóa đơn đó gồm: tên sản phẩm, số lượng, giá bán
	public ArrayList<ChiTietHoaDon> timChiTietHoaDonTheoMaHoaDon(String maHoaDon) {
	    ArrayList<ChiTietHoaDon> chiTietHoaDons = new ArrayList<>();
	    String sql = "select sp.MaSanPham, sp.TenSanPham, sp.GiaBan, sp.DonViTinh, cthd.SoLuong,cthd.TongTien from ChiTietHoaDon cthd join SanPhamYTe sp on cthd.MaSanPham = sp.MaSanPham where cthd.MaHoaDon =? ";

	    try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
	        ps.setString(1, maHoaDon); 
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            String maSanPham = rs.getString("MaSanPham");
	            String tenSanPham = rs.getString("TenSanPham");
	            BigDecimal giaBanSanPham = rs.getBigDecimal("GiaBan");
	            DonViTinh donViTinh = DonViTinh.fromString(rs.getString("DonViTinh"));
	            int soLuong = rs.getInt("SoLuong");
	            BigDecimal giaBanChiTiet = rs.getBigDecimal("TongTien"); 

	            
	            SanPhamYTe sanPhamYTe = new SanPhamYTe(maSanPham, tenSanPham, giaBanSanPham, donViTinh);

	           
	            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(sanPhamYTe, soLuong, giaBanChiTiet);
	            chiTietHoaDons.add(chiTietHoaDon);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return chiTietHoaDons;
	}
	
	

}
	

