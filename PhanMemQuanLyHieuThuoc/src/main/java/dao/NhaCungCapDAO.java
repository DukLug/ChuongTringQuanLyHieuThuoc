package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhaCungCap;


public class NhaCungCapDAO {

	public NhaCungCapDAO() {}
	
	static ArrayList<NhaCungCap> dsNCC;
	
	public static ArrayList<NhaCungCap> getAllNhaCungCap() {
		dsNCC = new ArrayList<NhaCungCap>();
		
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from NhaCungCap";
			Statement statement = con.createStatement();
			
			//  thực thi câu lệnh sql trả về đối tương ResultSet
			ResultSet rs = statement.executeQuery(sql);
			
			// duyệt trên kết quả trả về
			while (rs.next()) { // di chuyển con trỏ xuống bản ghi tiếp theo
				String maNCC = rs.getString(1);
				String tenNCC = rs.getString(2);
				String sdt = rs.getString(3);
				String email = rs.getString(4);
				String diaChi = rs.getString(5);
				
				NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, sdt, email, diaChi);
				dsNCC.add(ncc);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dsNCC;
	}

	public static boolean them(NhaCungCap ncc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		
		int n = 0;
		
		try {
			stmt = con.prepareStatement("insert into " + "NhaCungCap values(?, ?, ?, ?, ?)");
			stmt.setString(1, ncc.getMaNhaCungCap());
			stmt.setString(2, ncc.getTenNhaCungCap());
			stmt.setString(3, ncc.getSdt());
			stmt.setString(4, ncc.getEmail());
			stmt.setString(5, ncc.getDiaChi());
			
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
