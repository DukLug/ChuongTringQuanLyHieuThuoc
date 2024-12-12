package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietDonDoiTra;
import entity.SanPhamYTe;


public class ChiTietDonDoiTraDAO {

	// thêm chi tiết đơn đổi trả
	public boolean themChiTietDoiTra(ChiTietDonDoiTra chiTietDonDoiTra) {
	    String sql = "INSERT INTO ChitietDoiTra (MaChiTietDoiTra, SoLuong, GiaBan, MaDonDoiTra, MaSanPham, MaLo, MaLoHangThayThe) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    
	    try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
	        ps.setString(1, chiTietDonDoiTra.getMaChiTietDonDoiTra()); 
	        ps.setInt(2, chiTietDonDoiTra.getSoLuongDonViTinh1());           
	        ps.setInt(3, chiTietDonDoiTra.getSoLuongDonViTinh2());  
	        ps.setInt(4, chiTietDonDoiTra.getSoLuongDonViTinh3());  
	        ps.setBigDecimal(5, chiTietDonDoiTra.getGiaBan());      
	        ps.setString(6, chiTietDonDoiTra.getMaDonDoiTra().getMaDonDoiTra());    
	        ps.setString(7, chiTietDonDoiTra.getMaSanPham().getMaSanPham());       
	        ps.setString(8, chiTietDonDoiTra.getMaLoHang().getMaLo());            
	        ps.setString(9, chiTietDonDoiTra.getMaLoHangThayThe() != null ? chiTietDonDoiTra.getMaLoHangThayThe().getMaLo() : null);     

	        int rowsAffected = ps.executeUpdate(); 
	        return rowsAffected > 0;              
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; 
	    }
	}
	
	// xóa chi tiết đơn đổi trả
	public boolean xoaChiTietDoiTra(String maChiTietDoiTra) {
	    String sql = "DELETE FROM ChitietDoiTra WHERE MaChiTietDoiTra = ?";

	    try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
	        ps.setString(1, maChiTietDoiTra); 

	        int rowsAffected = ps.executeUpdate();
	        return rowsAffected > 0; 
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; 
	    }
	}

	public boolean checkMaChiTietDoiTraExists(String maChiTietDoiTra) {
		 
		        boolean exists = false;
		        String sql = "SELECT COUNT(*) FROM ChiTietDoiTra WHERE MaChiTietDoiTra = ?";

		        try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
		             
		            ps.setString(1, maChiTietDoiTra);
		            ResultSet rs = ps.executeQuery();

		            if (rs.next()) {
		                int count = rs.getInt(1);
		                exists = (count > 0);
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }

		        return exists;
		   
	}
	
	



}
