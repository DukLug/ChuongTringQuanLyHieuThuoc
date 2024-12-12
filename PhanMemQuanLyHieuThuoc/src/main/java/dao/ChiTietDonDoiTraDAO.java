package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import customDataType.DonViTinh;
import entity.ChiTietDonDoiTra;
import entity.SanPhamYTe;


public class ChiTietDonDoiTraDAO {

	// thêm chi tiết đơn đổi trả
	public boolean themChiTietDoiTra(ChiTietDonDoiTra chiTietDonDoiTra) {
	    String sql = "INSERT INTO ChitietDoiTra (MaChiTietDoiTra, SoLuongDonViTinh1, SoLuongDonViTinh2, SoLuongDonViTinh3, GiaBan, MaDonDoiTra, MaSanPham, MaLo, MaLoHangThayThe) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";
	    
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
	
	// kiểm tra số lượng hàng tồn
	public int laySoLuongTonKho(String maSanPham, DonViTinh donViTinhEnum) {
	    int soLuongTonKho = 0;
	    
	    String sql = "SELECT lh.SoLuongDonViTinh1, lh.SoLuongDonViTinh2, lh.SoLuongDonViTinh3 " +
	                   "FROM LoHang lh " +
	                   "INNER JOIN SanPhamYTe sp ON lh.MaSanPham = sp.MaSanPham " +
	                   "WHERE sp.MaSanPham = ?";
	    
	    try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
	        
	        ps.setString(1, maSanPham); 
	        
	        try (ResultSet rs = ps.executeQuery()) {
	           
	            if (rs.next()) {
	                switch (donViTinhEnum) {
	                    case Vien:
	                        soLuongTonKho = rs.getInt("SoLuongDonViTinh1");
	                        break;
	                    case Vi:
	                        soLuongTonKho = rs.getInt("SoLuongDonViTinh2");
	                        break;
	                    case Hop:
	                        soLuongTonKho = rs.getInt("SoLuongDonViTinh3");
	                        break;
	                }
	            }
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();  	
	    }
	    
	    return soLuongTonKho; 
	}
	  
	
	
	
	



}
