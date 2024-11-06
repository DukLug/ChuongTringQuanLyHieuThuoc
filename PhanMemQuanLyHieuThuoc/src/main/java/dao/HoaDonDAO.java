package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;

public class HoaDonDAO {

	public static boolean them(HoaDon hd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		
		int n = 0;
		
		try {
			stmt = con.prepareStatement("insert into " + "HoaDon values(?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, hd.getMaHoaDon());
			stmt.setDate(2, hd.getNgayTao());
			stmt.setInt(3, hd.getDiemSuDung());
			stmt.setBigDecimal(4, hd.getThanhTien());
			stmt.setString(5, hd.getNhanVien().getMaNhanVien());
			stmt.setString(5, hd.getKhuyenMai().getMaKhuyenMai());
			stmt.setString(6, hd.getKhachHang().getMaKhachHang());
			
			n = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return n > 0;
	}
}
