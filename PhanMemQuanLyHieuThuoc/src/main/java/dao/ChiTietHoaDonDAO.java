package dao;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import customDataType.DonViTinh;
import entity.ChiTietDonDoiTra;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoHang;
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

	            
	            SanPhamYTe sanPhamYTe = new SanPhamYTe(maSanPham);

	           
	            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon("VoMa");
	            chiTietHoaDons.add(chiTietHoaDon);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return chiTietHoaDons;
	}
	
	// thêm chi tiết đơn đổi trả
//		public boolean themChiTietHoadon(ChiTietHoaDon chiTietHoaDon) {
//		    String sql = "INSERT INTO ChiTietHoaDon (MaChiTietHoaDon, SoLuong, TongTien, MaHoaDon, MaSanPham, MaLo, MaLoHangThayThe) VALUES (?, ?, ?, ?, ?, ?, ?)";
//		    
//		    try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
//		        ps.setString(1, chiTietHoaDon.getMaChiTietHoaDon()); 
//		        ps.setInt(2, chiTietHoaDon.getSoLuong());           
//		        ps.setBigDecimal(3, chiTietHoaDon.getGiaBan());      
//		        ps.setString(4, chiTietHoaDon.getHoaDon().getMaHoaDon());    
//		        ps.setString(5, chiTietHoaDon.getSanPhamYTe().getMaSanPham());       
//		        ps.setString(6, chiTietHoaDon.getLoHang().getMaLo());            
//		        ps.setString(7, chiTietHoaDon.getLoHangThayThe() != null ? chiTietHoaDon.getLoHangThayThe().getMaLo() : null);     
//
//		        int rowsAffected = ps.executeUpdate(); 
//		        return rowsAffected > 0;              
//		    } catch (SQLException e) {
//		        e.printStackTrace();
//		        return false; 
//		    }
//		}
		
		// lấy danh sách các cgi tiet doi tra
//		public ArrayList<ChiTietHoaDon> layDSCTHD() {
//		    ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<>();
//		    try {
//		        ConnectDB.getInstance();
//		        Connection con = ConnectDB.getConnection();
//		        String sql = "SELECT * FROM ChiTietHoaDon";
//		        Statement statement = con.createStatement();
//		        
//		        // Thực thi câu lệnh SQL và trả về ResultSet
//		        ResultSet rs = statement.executeQuery(sql);
//		        
//		        // Duyệt qua các kết quả trả về
//		        while (rs.next()) {
//		            String maCTHD = rs.getString(1);
//		            int soLuong = rs.getInt(2);
//		            BigDecimal tongTien = rs.getBigDecimal(3);
//		            String maHD = rs.getString(4);
//		            HoaDon hoaDon = new HoaDon(maHD);
//		            
//		            String maSP = rs.getString(5);
//		            SanPhamYTe sanPham = new SanPhamYTe(maSP);
//		            
//		            String maLo = rs.getString(6);
//					LoHang LoHang = new LoHang(maLo);
//					
//					String maLoThayThe = rs.getString(7);
//					LoHang LohayThe = new LoHang(maLoThayThe);
//
//					ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(maCTHD, soLuong, tongTien, hoaDon, sanPham, LoHang, LohayThe);
//
//		            dsCTHD.add(chiTietHoaDon);
//		        }
//		        
//		    } catch (SQLException e) {
//		        e.printStackTrace();
//		    }
//		    
//		    return dsCTHD;
//		}
		

}
	

