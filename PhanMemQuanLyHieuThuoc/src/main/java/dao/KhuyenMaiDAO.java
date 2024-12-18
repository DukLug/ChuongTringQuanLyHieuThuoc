package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import connectDB.ConnectDB;
import entity.KhuyenMai;
import entity.NhanVien;

public class KhuyenMaiDAO {
	private ArrayList<KhuyenMai> dsKhuyenMai;
	// lấy toàn bộ bảng khuyến mãi
	public ArrayList<KhuyenMai> layDanhSachTatCaKhuyenMai(){
		dsKhuyenMai = new ArrayList<KhuyenMai>();
		try {
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement("Select * from KhuyenMai");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
		
				String maKhuyenMai = rs.getString("MaKhuyenMai");
				Date ngayKhuyenMai = rs.getDate("NgayBatDau");
				Date ngayKetThuc= rs.getDate("NgayKetThuc");
				String dieuKhien = rs.getString("DieuKien");
				double chietKhau = rs.getDouble("ChietKhau");
				NhanVien nv = new NhanVien(rs.getString("MaNhanVien"));
				int soLuong = rs.getInt("SoLuongGioiHan");
				
				KhuyenMai km = new KhuyenMai(maKhuyenMai, ngayKhuyenMai, ngayKetThuc, dieuKhien, chietKhau, nv,soLuong);
				dsKhuyenMai.add(km);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsKhuyenMai;
	}
	
	// thêm khuyến mãi
	public boolean themKhuyenMai(KhuyenMai newKhuyenMai) {
		try {
			PreparedStatement ps = ConnectDB.getConnection()
					.prepareStatement("Insert into KhuyenMai values(?,?,?,?,?,?,?)");
			ps.setString(1, newKhuyenMai.getMaKhuyenMai());
			ps.setDate(2, new Date(newKhuyenMai.getNgayKhuyenMai().getTime()));
			ps.setDate(3, new Date(newKhuyenMai.getNgayKetThuc().getTime()));
			ps.setString(4, newKhuyenMai.getDieuKien());
			ps.setInt(5, newKhuyenMai.getSoLuongGioiHan());
			ps.setDouble(6, newKhuyenMai.getChietKhau());
			ps.setString(7,newKhuyenMai.getMaNhanVien().getMaNhanVien());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// cập nhật khuyến mãi
	public boolean capNhatKuyenMai(KhuyenMai km) {
		try {
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement(
					"Update KhuyenMai set NgayBatDau=?,NgayKetThuc=?,DieuKien=?, SoLuongGioiHan=?, ChietKhau=?,MaNhanVien=? Where MaKhuyenMai=?");
			ps.setDate(1, new Date(km.getNgayKhuyenMai().getTime()));
			ps.setDate(2, new Date(km.getNgayKetThuc().getTime()));
			ps.setString(3, km.getDieuKien());
			ps.setDouble(4, km.getChietKhau());
			ps.setInt(5, km.getSoLuongGioiHan());
			ps.setString(6, km.getMaNhanVien().getMaNhanVien());
			ps.setString(7, km.getMaKhuyenMai());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// tìm khuyến mãi theo mã khuyến mãi
	public ArrayList<KhuyenMai> timKhuyenMaiTheoMa(String maKMCanTim) {
		ArrayList<KhuyenMai> khuyenMaiCanTim = new ArrayList<KhuyenMai>();
		try {
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement("Select * from KhuyenMai where MaKhuyenMai=?");
			ps.setString(1, maKMCanTim);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maKhuyenMai = rs.getString("MaKhuyenMai");
				Date ngayKhuyenMai = rs.getDate("NgayBatDau");
				Date ngayKetThuc= rs.getDate("NgayKetThuc");
				String dieuKhien = rs.getString("DieuKien");
				int soLuong = rs.getInt("SoLuongGioiHan");
				double chietKhau = rs.getDouble("ChietKhau");
				NhanVien nv = new NhanVien(rs.getString("MaNhanVien"));
				KhuyenMai kmCanTim = new KhuyenMai(maKhuyenMai, ngayKhuyenMai, ngayKetThuc, dieuKhien, chietKhau, nv,soLuong); 
				khuyenMaiCanTim.add(kmCanTim);
			}
		} catch (Exception e) {
			return null;
		}
		return khuyenMaiCanTim;
	}
	
	// tìm theo ngày bắt đầu
	public ArrayList<KhuyenMai> timKhuyenMaiTheoNgayBatDau(Date ngayBatDau) {
	    ArrayList<KhuyenMai> khuyenMaiTheoNgay = new ArrayList<>();
	    try {
	        PreparedStatement ps = ConnectDB.getConnection().prepareStatement(
	            "SELECT * FROM KhuyenMai WHERE NgayBatDau = ?"
	        );
	        ps.setDate(1, new Date(ngayBatDau.getTime()));  
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            String maKhuyenMai = rs.getString("MaKhuyenMai");
	            Date ngayKhuyenMai = rs.getDate("NgayBatDau");
	            Date ngayKetThuc = rs.getDate("NgayKetThuc");
	            int soLuong = rs.getInt("SoLuongGioiHan");;
	            String dieuKhien = rs.getString("DieuKien");
	            double chietKhau = rs.getDouble("ChietKhau");
	            NhanVien nv = new NhanVien(rs.getString("MaNhanVien"));
	            
	            KhuyenMai kmTheoNgay = new KhuyenMai(maKhuyenMai, ngayKhuyenMai, ngayKetThuc, dieuKhien, chietKhau, nv, soLuong);
	            khuyenMaiTheoNgay.add(kmTheoNgay);
	        }
	    } catch (Exception e) {
	        return null;
	    }
	    return khuyenMaiTheoNgay;
	}
	
	// tìm theo ngày kết thúc
		public ArrayList<KhuyenMai> timKhuyenMaiTheoNgayKetThuc(Date ngayKetThuc) {
		    ArrayList<KhuyenMai> khuyenMaiTheoNgay = new ArrayList<>();
		    try {
		        PreparedStatement ps = ConnectDB.getConnection().prepareStatement(
		            "SELECT * FROM KhuyenMai WHERE NgayKetThuc = ?"
		        );
		        ps.setDate(1, new Date(ngayKetThuc.getTime()));  
		        ResultSet rs = ps.executeQuery();
		        
		        while (rs.next()) {
		            String maKhuyenMai = rs.getString("MaKhuyenMai");
		            Date ngayKhuyenMai = rs.getDate("NgayBatDau");
		            Date ngayketThuc = rs.getDate("NgayKetThuc");
		            String dieuKhien = rs.getString("DieuKien");
		            int soLuong = rs.getInt("SoLuongGioiHan");
		            double chietKhau = rs.getDouble("ChietKhau");
		            NhanVien nv = new NhanVien(rs.getString("MaNhanVien"));
		            
		            KhuyenMai kmTheoNgay = new KhuyenMai(maKhuyenMai, ngayKhuyenMai, ngayketThuc, dieuKhien, chietKhau, nv, soLuong);
		            khuyenMaiTheoNgay.add(kmTheoNgay);
		        }
		    } catch (Exception e) {
		        return null;
		    }
		    return khuyenMaiTheoNgay;
		}
		
		
		// lấy điều kiện
		
		public ArrayList<String> layDanhSachDieuKien() {
		    Set<String> setDieuKien = new HashSet<>(); 
		    ArrayList<String> danhSachDieuKien = new ArrayList<>();

		    try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(
		            "SELECT DISTINCT DieuKien FROM KhuyenMai" 
		    );
		         ResultSet rs = ps.executeQuery()) {

		        while (rs.next()) {
		            setDieuKien.add(rs.getString("DieuKien")); 
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    danhSachDieuKien.addAll(setDieuKien);
		    return danhSachDieuKien; 
		}

		// tìm khuyến mãi theo điều kiện
		
		public ArrayList<KhuyenMai> timKhuyenMaiTheoDieuKien(String DieuKien) {
		    ArrayList<KhuyenMai> khuyenMaiTheoDieuKien = new ArrayList<>();
		    try {
		        PreparedStatement ps = ConnectDB.getConnection().prepareStatement(
		            "SELECT * FROM KhuyenMai WHERE DieuKien = ?"
		        );
		        ps.setString(1,DieuKien);  
		        ResultSet rs = ps.executeQuery();
		        
		        while (rs.next()) {
		            String maKhuyenMai = rs.getString("MaKhuyenMai");
		            Date ngayKhuyenMai = rs.getDate("NgayBatDau");
		            Date ngayketThuc = rs.getDate("NgayKetThuc");
		            String dieuKien = rs.getString("DieuKien");
		            int soLuong = rs.getInt("SoLuongGioiHan");
		            double chietKhau = rs.getDouble("ChietKhau");
		            NhanVien nv = new NhanVien(rs.getString("MaNhanVien"));
		            
		            KhuyenMai kmTheoNgay = new KhuyenMai(maKhuyenMai, ngayKhuyenMai, ngayketThuc, dieuKien, chietKhau, nv,soLuong);
		            khuyenMaiTheoDieuKien.add(kmTheoNgay);
		        }
		    } catch (Exception e) {
		        return null;
		    }
		    return khuyenMaiTheoDieuKien;
		}
		
	// tìm khuyến mãi theo mã nhân viên
		
		public ArrayList<KhuyenMai> timKhuyenMaiTheoNhanVien(String manv) {
		    ArrayList<KhuyenMai> khuyenMaiTheoDieuKien = new ArrayList<>();
		    try {
		        PreparedStatement ps = ConnectDB.getConnection().prepareStatement(
		            "SELECT * FROM KhuyenMai WHERE MaNhanVien = ?"
		        );
		        ps.setString(1,manv);  
		        ResultSet rs = ps.executeQuery();
		        
		        while (rs.next()) {
		            String maKhuyenMai = rs.getString("MaKhuyenMai");
		            Date ngayKhuyenMai = rs.getDate("NgayBatDau");
		            Date ngayketThuc = rs.getDate("NgayKetThuc");
		            String dieuKien = rs.getString("DieuKien");
		            int soLuong = rs.getInt("SoLuongGioiHan");
		            double chietKhau = rs.getDouble("ChietKhau");
		            NhanVien nv = new NhanVien(rs.getString("MaNhanVien"));
		            
		            KhuyenMai kmTheoNgay = new KhuyenMai(maKhuyenMai, ngayKhuyenMai, ngayketThuc, dieuKien, chietKhau, nv, soLuong);
		            khuyenMaiTheoDieuKien.add(kmTheoNgay);
		        }
		    } catch (Exception e) {
		        return null;
		    }
		    return khuyenMaiTheoDieuKien;
		}
		
	// lấy mã khuyến mãi cuối cùng
	public String layMaKhuyenMaiCuoi() {
	    String maKhuyenMaiCuoi = null;
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conn = ConnectDB.getConnection(); 

	        if (conn != null) {
	            String sql = "SELECT TOP 1 MaKhuyenMai FROM KhuyenMai ORDER BY MaKhuyenMai DESC";
	            ps = conn.prepareStatement(sql);
	            rs = ps.executeQuery();

	            if (rs.next()) {
	            	maKhuyenMaiCuoi = rs.getString("MaKHuyenMai");
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace(); 
		}
	    return maKhuyenMaiCuoi;
	}
	
	public ArrayList<KhuyenMai> timKhuyenMaiTheoDieuKien1(String DieuKien) {
	    ArrayList<KhuyenMai> khuyenMaiTheoDieuKien = new ArrayList<>();
	    try {
	        PreparedStatement ps = ConnectDB.getConnection().prepareStatement(
	            "SELECT * FROM KhuyenMai WHERE TRY_CAST(DieuKien AS DECIMAL) < ? and NgayKetThuc < GETDATE() and SoLuongGioiHan > 0;"
	        );
	        ps.setString(1,DieuKien);  
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            String maKhuyenMai = rs.getString("MaKhuyenMai");
	            Date ngayKhuyenMai = rs.getDate("NgayBatDau");
	            Date ngayketThuc = rs.getDate("NgayKetThuc");
	            String dieuKien = rs.getString("DieuKien");
	            int soLuong = rs.getInt("SoLuongGioiHan");
	            double chietKhau = rs.getDouble("ChietKhau");
	            //int soLuong = rs.getInt("SoLuongGioiHan");
	            		
	            NhanVien nv = new NhanVien(rs.getString("MaNhanVien"));
	            
	            KhuyenMai kmTheoNgay = new KhuyenMai(maKhuyenMai, ngayKhuyenMai, ngayketThuc, dieuKien, chietKhau, nv,soLuong);
	            khuyenMaiTheoDieuKien.add(kmTheoNgay);
	        }
	    } catch (Exception e) {
	        return null;
	    }
	    return khuyenMaiTheoDieuKien;
	}
	
	public boolean capNhatSoLuongGioHan(String maKM) {
	    int n = 0;
	    try {
	        PreparedStatement ps1 = ConnectDB.getConnection().prepareStatement(
	            "UPDATE KhuyenMai SET SoLuongGioiHan = SoLuongGioiHan - 1 WHERE MaKhuyenMai = ?");
	        ps1.setString(1, maKM);
	        n = ps1.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return (n > 0); 
	}
	

}
