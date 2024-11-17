package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.LoHang;

public class LoHangDAO {
	public static ArrayList<LoHang> danhSachLoHang;
	
	public static ArrayList<LoHang> layDanhSachTatCaLoHang(){
		return danhSachLoHang;
	}
	
	/// tìm lô hang theo mã sản phẩm
	public String maLoTheoSanPham(String maSP) {
	    String sql = "SELECT MaLo FROM LoHang WHERE MaSanPham = ?";
	    String maLo = null;

	    try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
	        ps.setString(1, maSP);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            maLo = rs.getString("MaLo");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return maLo; 
	}

	public boolean capNhatSoLuongSP(String maSanPham, int soLuongDaBan) {
	    int n = 0;
	    try {
	        PreparedStatement ps = ConnectDB.getConnection().prepareStatement("UPDATE LoHang SET SoLuong = SoLuong - ? WHERE MaSanPham = ?");
	        ps.setInt(1, soLuongDaBan);  
	        ps.setString(2, maSanPham); 

	        n = ps.executeUpdate();  

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return n > 0;
	}
}
