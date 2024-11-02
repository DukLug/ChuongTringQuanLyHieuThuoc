package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.DonDoiTra;

public class DonDoiTraDAO {
	// thêm đơn đổi trả
	 public boolean themDonDoiTra(DonDoiTra donDoiTra) {
	        String sql = "INSERT INTO DonDoiTra (MaDonDoiTra, NgayDoiTra, TienHoan, MaNhanVien, MaKhuyenMai, MaKhachHang, MaHoaDon) VALUES (?, ?, ?, ?, ?, ?, ?)";
	        
	        try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
	            ps.setString(1, donDoiTra.getMaDonDoiTra()); 
	            ps.setDate(2, new Date(donDoiTra.getNgayDoiTra().getTime()));
	            ps.setBigDecimal(3, donDoiTra.getTienHoan());
	            ps.setString(4, donDoiTra.getMaNhanVien().getMaNhanVien());
	            ps.setString(5, donDoiTra.getMaKhuyenMai().getMaKhuyenMai());
	            ps.setString(6, donDoiTra.getMaKhachhang().getMaKhachHang());
	            ps.setString(7, donDoiTra.getMaHoaDon().getMaHoaDon());

	            int rowsAffected = ps.executeUpdate(); 
	            return rowsAffected > 0; 
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false; 
	        }
	    }
	 
	 
	 
	 
	 // xóa đơn đổi trả
	 public boolean xoaDonDoiTra(String maDonDoiTra) {
		    String sql = "DELETE FROM DonDoiTra WHERE MaDonDoiTra = ?";
		    
		    try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
		        ps.setString(1, maDonDoiTra);
		        
		        int rowsAffected = ps.executeUpdate(); 
		        return rowsAffected > 0; 
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false; 
		    }
		}
	 
	 
		   
		    public boolean checkMaDonDoiTraExists(String maDonDoiTra) {
		        boolean exists = false;
		        String sql = "SELECT COUNT(*) FROM DonDoiTra WHERE MaDonDoiTra = ?";

		        try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
		             
		            ps.setString(1, maDonDoiTra);
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
