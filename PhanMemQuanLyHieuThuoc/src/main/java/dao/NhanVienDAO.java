package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChucVu;
import entity.GioiTinh;
import entity.NhanVien;
import entity.TrangThaiLamViec;

public class NhanVienDAO {
private ArrayList<NhanVien> dsNhanVien;
	
	// lấy toàn bộ bảng nhân viên
	public ArrayList<NhanVien> layDanhSachTatCaNhanVien(){
		dsNhanVien = new ArrayList<NhanVien>();
		try {
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement("Select * from NhanVien");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
		
				String maNhanVien = rs.getString("MaNhanVien");
				String hoTen = rs.getString("HoTen");
				String sdt = rs.getString("Sdt");
				String cccd = rs.getString("Cccd");
				Date ngaySinh = rs.getDate("NgaySinh");
				TrangThaiLamViec trangThai = TrangThaiLamViec.valueOf(rs.getString("TrangThai"));
				ChucVu chucVu = ChucVu.valueOf(rs.getString("ChucVu"));
				GioiTinh gioiTinh = GioiTinh.valueOf(rs.getString("GioiTinh"));

				NhanVien nv = new NhanVien(maNhanVien, hoTen, sdt, cccd, ngaySinh, trangThai, chucVu, gioiTinh);
				dsNhanVien.add(nv);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsNhanVien;
	}

	// thêm nhân viên
	public boolean themNhanVien(NhanVien newNhanVien) {
		try {
			PreparedStatement ps = ConnectDB.getConnection()
					.prepareStatement("Insert into NhanVien values(?,?,?,?,?,?,?,?)");
			ps.setString(1, newNhanVien.getMaNhanVien());
			ps.setString(2, newNhanVien.getHoten());
			ps.setString(3, newNhanVien.getSdt());
			ps.setString(4, newNhanVien.getCccd());
			ps.setDate(5, new Date(newNhanVien.getNgaySinh().getTime()));
			ps.setString(6, newNhanVien.getTrangThaiLamViec().toString());
			ps.setString(7, newNhanVien.getChucVu().toString());
			ps.setString(8, newNhanVien.getGioiTinh().toString());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// cập nhật nhân viên
	public boolean capNhatNhanVien(NhanVien nv) {
		try {
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement(
					"Update NhanVien set HoTen = ?, Sdt = ?, Cccd = ?, NgaySinh = ?, TrangThai = ?, ChucVu = ?, GioiTinh = ? where MaNhanVien = ?");
			ps.setString(1, nv.getHoten());
			ps.setString(2, nv.getSdt());
			ps.setString(3, nv.getCccd());
			ps.setDate(4, new Date(nv.getNgaySinh().getTime()));
			ps.setString(5, nv.getTrangThaiLamViec().toString());
			ps.setString(6, nv.getChucVu().toString());
			ps.setString(7, nv.getGioiTinh().toString());
			ps.setString(8, nv.getMaNhanVien());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// tìm nhân viên theo mã nhân viên
	public NhanVien timNhanVienTheoMa(String maNhanVien) {
		try {
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement("Select * from NhanVien where MaNhanVien = ?");
			ps.setString(1, maNhanVien);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String maNhanVien1 = rs.getString("MaNhanVien");
				String hoTen = rs.getString("HoTen");
				String sdt = rs.getString("Sdt");
				String cccd = rs.getString("Cccd");
				Date ngaySinh = rs.getDate("NgaySinh");
				TrangThaiLamViec trangThai = TrangThaiLamViec.valueOf(rs.getString("TrangThai"));
				ChucVu chucVu = ChucVu.valueOf(rs.getString("ChucVu"));
				GioiTinh gioiTinh = GioiTinh.valueOf(rs.getString("GioiTinh"));

				return new NhanVien(maNhanVien1, hoTen, sdt, cccd, ngaySinh, trangThai, chucVu, gioiTinh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Tìm nhân viên theo tên
	public ArrayList<NhanVien> timNhanVienTheoTen(String tenNhanVien) {
		ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
		try {
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement("Select * from NhanVien where HoTen like ?");
			ps.setString(1, "%" + tenNhanVien + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maNhanVien = rs.getString("MaNhanVien");
				String hoTen = rs.getString("HoTen");
				String sdt = rs.getString("Sdt");
				String cccd = rs.getString("Cccd");
				Date ngaySinh = rs.getDate("NgaySinh");
				TrangThaiLamViec trangThai = TrangThaiLamViec.valueOf(rs.getString("TrangThai"));
				ChucVu chucVu = ChucVu.valueOf(rs.getString("ChucVu"));
				GioiTinh gioiTinh = GioiTinh.valueOf(rs.getString("GioiTinh"));

				NhanVien nv = new NhanVien(maNhanVien, hoTen, sdt, cccd, ngaySinh, trangThai, chucVu, gioiTinh);
				dsNhanVien.add(nv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsNhanVien;
	}

	// tìm nhân viên theo số điện thoại
	public ArrayList<NhanVien> timNhanVienTheoSDT(String sdt) {
		ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
		try {
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement("Select * from NhanVien where Sdt like ?");
			ps.setString(1, "%" + sdt + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maNhanVien = rs.getString("MaNhanVien");
				String hoTen = rs.getString("HoTen");
				String sdt1 = rs.getString("Sdt");
				String cccd = rs.getString("Cccd");
				Date ngaySinh = rs.getDate("NgaySinh");
				TrangThaiLamViec trangThai = TrangThaiLamViec.valueOf(rs.getString("TrangThai"));
				ChucVu chucVu = ChucVu.valueOf(rs.getString("ChucVu"));
				GioiTinh gioiTinh = GioiTinh.valueOf(rs.getString("GioiTinh"));

				NhanVien nv = new NhanVien(maNhanVien, hoTen, sdt1, cccd, ngaySinh, trangThai, chucVu, gioiTinh);
				dsNhanVien.add(nv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsNhanVien;
	}

	// tim nhân viên theo giới tính
	public ArrayList<NhanVien> timNhanVienTheoGioiTinh(GioiTinh gioiTinh) {
		ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
		try {
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement("Select * from NhanVien where GioiTinh = ?");
			ps.setString(1, gioiTinh.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maNhanVien = rs.getString("MaNhanVien");
				String hoTen = rs.getString("HoTen");
				String sdt = rs.getString("Sdt");
				String cccd = rs.getString("Cccd");
				Date ngaySinh = rs.getDate("NgaySinh");
				TrangThaiLamViec trangThai = TrangThaiLamViec.valueOf(rs.getString("TrangThai"));
				ChucVu chucVu = ChucVu.valueOf(rs.getString("ChucVu"));
				GioiTinh gioiTinh1 = GioiTinh.valueOf(rs.getString("GioiTinh"));

				NhanVien nv = new NhanVien(maNhanVien, hoTen, sdt, cccd, ngaySinh, trangThai, chucVu, gioiTinh1);
				dsNhanVien.add(nv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsNhanVien;
	}
	// tìm nhân viên theo tên và giới tính
	public ArrayList<NhanVien> timNhanVienTheoTenVaGioiTinh(String tenNhanVien, GioiTinh gioiTinh) {
		ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
		try {
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement("Select * from NhanVien where HoTen like ? and GioiTinh = ?");
			ps.setString(1, "%" + tenNhanVien + "%");
			ps.setString(2, gioiTinh.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maNhanVien = rs.getString("MaNhanVien");
				String hoTen = rs.getString("HoTen");
				String sdt = rs.getString("Sdt");
				String cccd = rs.getString("Cccd");
				Date ngaySinh = rs.getDate("NgaySinh");
				TrangThaiLamViec trangThai = TrangThaiLamViec.valueOf(rs.getString("TrangThai"));
				ChucVu chucVu = ChucVu.valueOf(rs.getString("ChucVu"));
				GioiTinh gioiTinh1 = GioiTinh.valueOf(rs.getString("GioiTinh"));

				NhanVien nv = new NhanVien(maNhanVien, hoTen, sdt, cccd, ngaySinh, trangThai, chucVu, gioiTinh1);
				dsNhanVien.add(nv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsNhanVien;
	}

}
