package dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import connectDB.ConnectDB;
import customDataType.ChucVu;
import customDataType.GioiTinh;
import customDataType.TrangThaiLamViec;
import entity.NhanVien;

public class NhanVienDAO {
private ArrayList<NhanVien> dsNhanVien;
	
	// lấy toàn bộ bảng nhân viên
		//Phương thức chuyển đổi chuỗi thành TrangThaiLamViec
		private TrangThaiLamViec parseTrangThaiLamViec(String str) {
		    for (TrangThaiLamViec tt : TrangThaiLamViec.values()) {
		        if (tt.getTrangThaiLamViec().equals(str)) {
		            return tt;
		        }
		    }
		    return null; // hoặc giá trị mặc định nếu cần
		}
		
		// Phương thức chuyển đổi chuỗi thành ChucVu
		private ChucVu parseChucVu(String str) {
		    for (ChucVu cv : ChucVu.values()) {
		        if (cv.getChucVu().equals(str)) {
		            return cv;
		        }
		    }
		    return null;
		}
		
		// Phương thức chuyển đổi chuỗi thành GioiTinh
		private GioiTinh parseGioiTinh(String str) {
		    for (GioiTinh gt : GioiTinh.values()) {
		        if (gt.getGioiTinh().equals(str)) {
		            return gt;
		        }
		    }
		    return null;
		}
		
		// Lấy toàn bộ bảng nhân viên
		public ArrayList<NhanVien> layDanhSachTatCaNhanVien() {
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
		            GioiTinh gioiTinh = parseGioiTinh(rs.getString("GioiTinh"));
		            TrangThaiLamViec trangThai = parseTrangThaiLamViec(rs.getString("TrangThaiLamViec"));
		            ChucVu chucVu = parseChucVu(rs.getString("ChucVu"));
		            NhanVien nv = new NhanVien(maNhanVien, hoTen, sdt, cccd, ngaySinh, gioiTinh, chucVu, trangThai);
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
			ps.setString(2, newNhanVien.getHoTen());
			ps.setString(3, newNhanVien.getSdt());
			ps.setString(4, newNhanVien.getCccd());
			ps.setDate(5, new Date(newNhanVien.getNgaySinh().getTime()));
			ps.setString(6, newNhanVien.getGioiTinh().toString());
			ps.setString(7, newNhanVien.getChucVu().toString());
			ps.setString(8, newNhanVien.getTrangThaiLamViec().toString());

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
					"Update NhanVien set HoTen = ?, Sdt = ?, Cccd = ?, NgaySinh = ?, GioiTinh = ?, ChucVu = ?, TrangThaiLamViec = ? where MaNhanVien = ?");
			ps.setString(1, nv.getHoTen());

			ps.setString(2, nv.getSdt());
			ps.setString(3, nv.getCccd());
			ps.setDate(4, new Date(nv.getNgaySinh().getTime()));
			ps.setString(5, nv.getGioiTinh().toString());
			ps.setString(6, nv.getChucVu().toString());
			ps.setString(7, nv.getTrangThaiLamViec().toString());

			ps.setString(8, nv.getMaNhanVien());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// tìm nhân viên theo mã nhân viên
	public ArrayList<NhanVien> timNhanVienTheoMa(String maNhanVien) {
		ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
		 try {
		        PreparedStatement ps = ConnectDB.getConnection().prepareStatement("Select * from NhanVien where MaNhanVien = ?");
		        ps.setString(1, maNhanVien);
		        ResultSet rs = ps.executeQuery();
		        while (rs.next()) {

		            String maNV1 = rs.getString("MaNhanVien");

		            String hoTen = rs.getString("HoTen");
		            String sdt = rs.getString("Sdt");
		            String cccd = rs.getString("Cccd");
		            Date ngaySinh = rs.getDate("NgaySinh");

		            GioiTinh gioiTinh = parseGioiTinh(rs.getString("GioiTinh"));
		            TrangThaiLamViec trangThai = parseTrangThaiLamViec(rs.getString("TrangThaiLamViec"));
		            ChucVu chucVu = parseChucVu(rs.getString("ChucVu"));

		            NhanVien nv = new NhanVien(maNV1, hoTen, sdt, cccd, ngaySinh, gioiTinh, chucVu, trangThai);

		            dsNhanVien.add(nv);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		return dsNhanVien;
	}
	// tìm nhân viên theo mã nhân viên
		public static ArrayList<NhanVien> timNhanVienTheoMaStatic(String maNhanVien) {
			ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
			 try {
			        PreparedStatement ps = ConnectDB.getConnection().prepareStatement("Select * from NhanVien where MaNhanVien = ?");
			        ps.setString(1, maNhanVien);
			        ResultSet rs = ps.executeQuery();
			        while (rs.next()) {

			            String maNV1 = rs.getString("MaNhanVien");

			            String hoTen = rs.getString("HoTen");
			            String sdt = rs.getString("Sdt");
			            String cccd = rs.getString("Cccd");
			            Date ngaySinh = rs.getDate("NgaySinh");

			            GioiTinh gioiTinh = GioiTinh.fromString(rs.getString("GioiTinh"));
			            TrangThaiLamViec trangThai = TrangThaiLamViec.fromString(rs.getString("TrangThaiLamViec"));
			            ChucVu chucVu = ChucVu.fromString(rs.getString("ChucVu"));

			            NhanVien nv = new NhanVien(maNV1, hoTen, sdt, cccd, ngaySinh, gioiTinh, chucVu, trangThai);

			            dsNhanVien.add(nv);
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			return dsNhanVien;
		}
	public NhanVien layNhanVienTheoMa(String maNhanVien) {
	    NhanVien nv = null;

	    try {
	        PreparedStatement ps = ConnectDB.getConnection().prepareStatement("SELECT * FROM NhanVien WHERE MaNhanVien = ?");
	        ps.setString(1, maNhanVien);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            String maNV1 = rs.getString("MaNhanVien");
	            String hoTen = rs.getString("HoTen");
	            String sdt = rs.getString("Sdt");
	            String cccd = rs.getString("Cccd");
	            Date ngaySinh = rs.getDate("NgaySinh");

	            GioiTinh gioiTinh = parseGioiTinh(rs.getString("GioiTinh")); 
	            TrangThaiLamViec trangThai = parseTrangThaiLamViec(rs.getString("TrangThaiLamViec")); 
	            ChucVu chucVu = parseChucVu(rs.getString("ChucVu"));
	            nv = new NhanVien(maNV1, hoTen, sdt, cccd, ngaySinh, gioiTinh, chucVu, trangThai);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return nv;
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

				GioiTinh gioiTinh = parseGioiTinh(rs.getString("GioiTinh"));
	            TrangThaiLamViec trangThai = parseTrangThaiLamViec(rs.getString("TrangThaiLamViec"));
	            ChucVu chucVu = parseChucVu(rs.getString("ChucVu"));

				  NhanVien nv = new NhanVien(maNhanVien, hoTen, sdt, cccd, ngaySinh, gioiTinh, chucVu, trangThai);

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

				GioiTinh gioiTinh = parseGioiTinh(rs.getString("GioiTinh"));
	            TrangThaiLamViec trangThai = parseTrangThaiLamViec(rs.getString("TrangThaiLamViec"));
	            ChucVu chucVu = parseChucVu(rs.getString("ChucVu"));

				  NhanVien nv = new NhanVien(maNhanVien, hoTen, sdt1, cccd, ngaySinh, gioiTinh, chucVu, trangThai);

				dsNhanVien.add(nv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsNhanVien;
	}



	
	public String layMaNhanVienCuoi() {
	    String maNhanVienCuoi = null;
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conn = ConnectDB.getConnection(); 

	        if (conn != null) {
	            String sql = "SELECT TOP 1 MaNhanVien FROM NhanVien ORDER BY MaNhanVien DESC";
	            ps = conn.prepareStatement(sql);
	            rs = ps.executeQuery();

	            if (rs.next()) {
	            	maNhanVienCuoi = rs.getString("MaNhanVien");
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace(); 
		}
	    return maNhanVienCuoi;

	}

}
