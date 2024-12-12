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
	
	public static Object[][] getAllNhaCungCap() {
	    dsNCC = new ArrayList<>();

	    
	    try {
	        ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();
	        String sql = "SELECT * FROM NhaCungCap";
	        Statement statement = con.createStatement();
	        
	        // Thực thi câu lệnh SQL và trả về ResultSet
	        ResultSet rs = statement.executeQuery(sql);
	        
	        // Duyệt qua các kết quả trả về
	        while (rs.next()) {
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
	    
	    // Chuyển ArrayList thành mảng Object[][]
	    Object[][] data = new Object[dsNCC.size()][5];
	    for (int i = 0; i < dsNCC.size(); i++) {
	        NhaCungCap ncc = dsNCC.get(i);
	        data[i][0] = ncc.getMaNhaCungCap();
	        data[i][1] = ncc.getTenNhaCungCap();
	        data[i][2] = ncc.getSdt();
	        data[i][3] = ncc.getEmail();
	        data[i][4] = ncc.getDiaChi();
	    }
	    
	    return data;
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
	
	public static boolean capNhat(NhaCungCap ncc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		
		int n = 0;
		
		try {
			stmt = con.prepareStatement("update NhaCungCap set TenNhaCungCap = ?, Sdt = ?, Email = ?, DiaChi = ? WHERE MaNhaCungCap = ?");
			stmt.setString(1, ncc.getTenNhaCungCap());
			stmt.setString(2, ncc.getSdt());
			stmt.setString(3, ncc.getEmail());
			stmt.setString(4, ncc.getDiaChi());
			stmt.setString(5, ncc.getMaNhaCungCap());
			
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
	
	public static ArrayList<NhaCungCap> layDanhSachTatCaNhaCungCap() {
	    dsNCC = new ArrayList<>();

	    
	    try {
	        ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();
	        String sql = "SELECT * FROM NhaCungCap";
	        Statement statement = con.createStatement();
	        
	        // Thực thi câu lệnh SQL và trả về ResultSet
	        ResultSet rs = statement.executeQuery(sql);
	        
	        // Duyệt qua các kết quả trả về
	        while (rs.next()) {
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
	
}