package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietDonDoiTra;
import entity.SanPhamYTe;


public class ChiTietDonDoiTraDAO {

	// thêm chi tiết đơn đổi trả
	public boolean themChiTietDoiTra(ChiTietDonDoiTra chiTietDonDoiTra) {
	    String sql = "INSERT INTO ChitietDoiTra (MaChiTietDoiTra, SoLuong, GiaBan, MaDonDoiTra, MaSanPham, MaLo, MaLoThayThe) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    
	    try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
	        ps.setString(1, chiTietDonDoiTra.getMaChiTietDonDoiTra()); 
	        ps.setInt(2, chiTietDonDoiTra.getSoLuong());           
	        ps.setBigDecimal(3, chiTietDonDoiTra.getGiaBan());      
	        ps.setString(4, chiTietDonDoiTra.getMaDonDoiTra().getMaDonDoiTra());    
	        ps.setString(5, chiTietDonDoiTra.getMaSanPham().getMaSanPham());       
	        ps.setString(6, chiTietDonDoiTra.getMaLoHang().getMaLo());            
	        ps.setString(7, chiTietDonDoiTra.getMaLoHangThayThe() != null ? chiTietDonDoiTra.getMaLoHangThayThe().getMaLo() : null);     

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
	
	



}
