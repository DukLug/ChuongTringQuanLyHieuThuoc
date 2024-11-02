package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.NhaCungCap;

public class KhachHangDAO {

	public KhachHangDAO() {}
	
	static ArrayList<KhachHang> dsKH;
	
	public static Object[][] getAllNhaCungCap() {
	    ArrayList<KhachHang> dsNCC = new ArrayList<>();
	    
	
	    try {
	        ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();
	        String sql = "SELECT * FROM KhachHang";
	        Statement statement = con.createStatement();
	        
	        // Thực thi câu lệnh SQL và trả về ResultSet
	        ResultSet rs = statement.executeQuery(sql);
	        
	        // Duyệt qua các kết quả trả về
	        while (rs.next()) {
	            String maKH = rs.getString(1);
	            String tenKH = rs.getString(2);
	            String sdt = rs.getString(3);
	            String diaChi = rs.getString(4);
	            int diemTichLuy = rs.getInt(5);
	            String cccd = rs.getString(6);
	            
	            KhachHang kh = new KhachHang(maKH, tenKH, sdt, diaChi, diemTichLuy, cccd);
	            dsKH.add(kh);
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    // Chuyển ArrayList thành mảng Object[][]
	    Object[][] data = new Object[dsKH.size()][5];
	    for (int i = 0; i < dsKH.size(); i++) {
	        KhachHang kh = dsKH.get(i);
	        data[i][0] = kh.getMaKhachHang();
	        data[i][1] = kh.getHoTen();
	        data[i][2] = kh.getSdt();
	        data[i][3] = kh.getDiaChi();
	        data[i][4] = kh.getDiemTichLuy();
	        data[i][5] = kh.getCccd();
	    }
	    
	    return data;
	}


	public static boolean them(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		
		int n = 0;
		
		try {
			stmt = con.prepareStatement("insert into " + "NhaCungCap values(?, ?, ?, ?, ?)");
			stmt.setString(1, kh.getMaKhachHang());
			stmt.setString(2, kh.getHoTen());
			stmt.setString(3, kh.getSdt());
			stmt.setString(4, kh.getDiaChi());
			stmt.setInt(5, kh.getDiemTichLuy());
			stmt.setString(6, kh.getCccd());
			
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
	
	public static boolean capNhat(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		
		int n = 0;
		
		try {
			stmt = con.prepareStatement("update KhachHang set HoTen = ?, Sdt = ?, DiaChi = ?, DiemTichLuy = ?, Cccd = ? WHERE MaKhachHang = ?");
			stmt.setString(1, kh.getMaKhachHang());
			stmt.setString(2, kh.getHoTen());
			stmt.setString(3, kh.getSdt());
			stmt.setString(4, kh.getDiaChi());
			stmt.setInt(5, kh.getDiemTichLuy());
			stmt.setString(6, kh.getCccd());
			
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
