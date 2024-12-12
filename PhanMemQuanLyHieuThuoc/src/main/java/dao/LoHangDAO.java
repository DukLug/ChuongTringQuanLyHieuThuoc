package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
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

}
