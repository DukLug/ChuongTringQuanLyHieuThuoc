package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.openxmlformats.schemas.drawingml.x2006.main.STTextIndent;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhaCungCap;
import entity.NhanVien;

public class KhachHangDAO {

	public KhachHangDAO() {}
	
	static ArrayList<KhachHang> dsKH;
	
	public static Object[][] getAllKhachHang() {
	    dsKH = new ArrayList<>();

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
	            String cccd = rs.getString(4);
	            String diaChi = rs.getString(5);
	            int diemTichLuy = rs.getInt(6);

	            KhachHang kh = new KhachHang(maKH, tenKH, sdt, cccd, diaChi, diemTichLuy);

	            dsKH.add(kh);
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    // Chuyển ArrayList thành mảng Object[][]
	    Object[][] data = new Object[dsKH.size()][6];

	    for (int i = 0; i < dsKH.size(); i++) {
	        KhachHang kh = dsKH.get(i);
	        data[i][0] = kh.getMaKhachHang();
	        data[i][1] = kh.getHoTen();
	        data[i][2] = kh.getSdt();
	        data[i][3] = kh.getCccd();
	        data[i][4] = kh.getDiaChi();
	        data[i][5] = kh.getDiemTichLuy();

	    }
	    
	    return data;
	}


	public static boolean them(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		
		int n = 0;
		
		try {
			stmt = con.prepareStatement("insert into " + "KhachHang values(?, ?, ?, ?, ?, ?)");
			stmt.setString(1, kh.getMaKhachHang());
			stmt.setString(2, kh.getHoTen());
			stmt.setString(3, kh.getSdt());
			stmt.setString(4, kh.getCccd());
			stmt.setString(5, kh.getDiaChi());
			stmt.setInt(6, kh.getDiemTichLuy());
			
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
	
	public static boolean them2(KhachHang kh) {
	    String sql = "INSERT INTO KhachHang(MaKhachHang, HoTen, Sdt, DiemTichLuy) VALUES(?, ?, ?, ?)";
	    try (
	        Connection con = ConnectDB.getConnection();
	        PreparedStatement stmt = con.prepareStatement(sql)
	    ) {
	    	stmt.setString(1, kh.getMaKhachHang());
	        stmt.setString(2, kh.getHoTen());
	        stmt.setString(3, kh.getSdt());
	        stmt.setInt(4, kh.getDiemTichLuy());
	        
	        int n = stmt.executeUpdate();
	        return n > 0;
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }
	    return false; 
	}

	
	public static boolean capNhat(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		
		int n = 0;
		
		try {
			stmt = con.prepareStatement("update KhachHang set HoTen = ?, Sdt = ?, Cccd = ?, DiaChi = ?, DiemTichLuy = ? WHERE MaKhachHang = ?");
			stmt.setString(1, kh.getHoTen());
			stmt.setString(2, kh.getSdt());
			stmt.setString(3, kh.getCccd());
			stmt.setString(4, kh.getDiaChi());
			stmt.setInt(5, kh.getDiemTichLuy());
			stmt.setString(6, kh.getMaKhachHang());
	

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
	
	public static boolean capNhat1(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		
		int n = 0;
		
		try {
			stmt = con.prepareStatement("update KhachHang set HoTen = ?, Sdt = ?, Cccd = ? WHERE MaKhachHang = ?");
			stmt.setString(1, kh.getHoTen());
			stmt.setString(2, kh.getSdt());
			stmt.setString(3, kh.getCccd());
			stmt.setString(4, kh.getMaKhachHang());
	

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

	public ArrayList<KhachHang> timTheoSDT(String SDT) {
	    ArrayList<KhachHang> khachHangCanTim = new ArrayList<KhachHang>();
	    try {
	        PreparedStatement ps = ConnectDB.getConnection().prepareStatement("SELECT * FROM KhachHang WHERE Sdt = ?");
	        ps.setString(1, SDT);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            String ma = rs.getString("MaKhachHang");
	            String ten = rs.getString("HoTen");
	            String sdt = rs.getString("Sdt");
	            String cccd = rs.getString("Cccd");
	            String diaChi = rs.getString("DiaChi");
	            int dtl = rs.getInt("DiemTichLuy");
	            KhachHang khachHang = new KhachHang(ma, ten, sdt, cccd, diaChi, dtl);
	           
	            khachHangCanTim.add(khachHang);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return khachHangCanTim;
	}
	
	public KhachHang timTheoSDT1(String SDT) {
		KhachHang khachHang = null;
	    try {
	        PreparedStatement ps = ConnectDB.getConnection().prepareStatement("SELECT * FROM KhachHang WHERE Sdt = ?");
	        ps.setString(1, SDT);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            String ma = rs.getString("MaKhachHang");
	            String ten = rs.getString("HoTen");
	            String sdt = rs.getString("Sdt");
	            String cccd = rs.getString("Cccd");
	            String diaChi = rs.getString("DiaChi");
	            int dtl = rs.getInt("DiemTichLuy");
	            khachHang = new KhachHang(ma, ten, sdt, cccd, diaChi, dtl);

	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return khachHang;
	}
	
	public ArrayList<KhachHang> timTheoMa(String ma) {
	    ArrayList<KhachHang> khachHangCanTim = new ArrayList<KhachHang>();
	    try {
	        PreparedStatement ps = ConnectDB.getConnection().prepareStatement("SELECT * FROM KhachHang WHERE MaKhachHang = ?");
	        ps.setString(1, ma);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            String maKH = rs.getString("MaKhachHang");
	            String ten = rs.getString("HoTen");
	            String sdt = rs.getString("Sdt");
	            String cccd = rs.getString("Cccd");
	            String diaChi = rs.getString("DiaChi");
	            int dtl = rs.getInt("DiemTichLuy");
	            KhachHang khachHang = new KhachHang(maKH, ten, sdt, cccd, diaChi, dtl);
	           
	            khachHangCanTim.add(khachHang);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return khachHangCanTim;
	}
	
	public boolean capNhatDTL(KhachHang kh, int DTL) {
	    int n = 0;
	    try {
	        PreparedStatement ps = ConnectDB.getConnection().prepareStatement("UPDATE KhachHang SET DiemTichLuy = DiemTichLuy + ? WHERE MaKhachHang = ?");
	        ps.setInt(1, DTL);  
	        ps.setString(2, kh.getMaKhachHang()); 

	        n = ps.executeUpdate();  

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return n > 0;
	}

	
	// lấy tên và sdt khách hàng theo mã đoiỉ trả
	public KhachHang layThongTinKhachHangTheoMaDonDoiTra(String maDonDoiTra) {
	    KhachHang khachHang = null;
	    String sql = "SELECT kh.HoTen, kh.Sdt FROM DonDoiTra ddt " +
	                 "JOIN KhachHang kh ON ddt.MaKhachHang = kh.MaKhachHang " +
	                 "WHERE ddt.MaDonDoiTra = ?";

	    try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
	        ps.setString(1, maDonDoiTra);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                String hoTen = rs.getString("HoTen");
	                String sdt = rs.getString("Sdt");
	                khachHang = new KhachHang(hoTen, sdt); 
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return khachHang;
	}

}
