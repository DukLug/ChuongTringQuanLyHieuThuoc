package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
				Date ngayKhuyenMai = rs.getDate("NgayKhuyenMai");
				Date ngayKetThuc= rs.getDate("NgayKetThuc");
				String dieuKhien = rs.getString("DieuKien");
				double chietKhau = rs.getDouble("ChietKhau");
				NhanVien nv = new NhanVien(rs.getString("MaNhanVien"));
				
				KhuyenMai km = new KhuyenMai(maKhuyenMai, ngayKhuyenMai, ngayKetThuc, dieuKhien, chietKhau, nv);
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
					.prepareStatement("Insert into KhuyenMai values(?,?,?,?,?,?)");
			ps.setString(1, newKhuyenMai.getMaKhuyenMai());
			ps.setDate(2, new Date(newKhuyenMai.getNgayKhuyenMai().getTime()));
			ps.setDate(3, new Date(newKhuyenMai.getNgayKhuyenMai().getTime()));
			ps.setString(4, newKhuyenMai.getDieuKien());
			ps.setDouble(5, newKhuyenMai.getChietKhau());
			ps.setString(6,newKhuyenMai.getMaNhanVien().getMaNhanVien());
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
					"Update KhuyenMai set NgayKhuyenMai=?,NgayKetThuc=?,DieuKien=?,ChietKhau=?,MaNhanVien=? Where MaKhuyenMai=?");
			ps.setDate(1, new Date(km.getNgayKhuyenMai().getTime()));
			ps.setDate(2, new Date(km.getNgayKhuyenMai().getTime()));
			ps.setString(3, km.getDieuKien());
			ps.setDouble(4, km.getChietKhau());
			ps.setString(5, km.getMaNhanVien().getMaNhanVien());
			ps.setString(6, km.getMaKhuyenMai());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// tìm khuyến mãi theo mã khuyến mãi
	public KhuyenMai timKhuyenMai(String maKMCanTim) {
		KhuyenMai khuyenMaiCanTim = null;
		try {
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement("Select * from KhuyenMai where MaKuyenMai=?");
			ps.setString(1, maKMCanTim);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maKhuyenMai = rs.getString("MaKhuyenMai");
				Date ngayKhuyenMai = rs.getDate("NgayKhuyenMai");
				Date ngayKetThuc= rs.getDate("NgayKetThuc");
				String dieuKhien = rs.getString("DieuKien");
				double chietKhau = rs.getDouble("ChietKhau");
				NhanVien nv = new NhanVien(rs.getString("MaNhanVien"));
				khuyenMaiCanTim = new KhuyenMai(maKhuyenMai, ngayKhuyenMai, ngayKetThuc, dieuKhien, chietKhau, nv);
			}
		} catch (Exception e) {
			return null;
		}
		return khuyenMaiCanTim;
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
}
