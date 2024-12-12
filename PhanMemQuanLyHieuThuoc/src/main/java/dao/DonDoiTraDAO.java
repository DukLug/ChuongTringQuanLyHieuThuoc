package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.DonDoiTra;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.DonDoiTra;

public class DonDoiTraDAO {
	public static ArrayList<DonDoiTra> dsHoaDonDoiTra;
	public static Object[][] layDanhSachHoaDonDoiTra() {
		dsHoaDonDoiTra = new ArrayList<>();

		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from DonDoiTra";
			Statement statement = con.createStatement();

			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maHoaDon = rs.getString(1);
				Date ngayDoiTra = rs.getDate(2);
				BigDecimal tienHoan = rs.getBigDecimal(3);
				NhanVien nv = new NhanVien(rs.getString("MaNhanVien"));
				KhuyenMai km = new KhuyenMai(rs.getString("MaKhuyenMai"));
				KhachHang kh = new KhachHang(rs.getString("MaKhachHang"));
				HoaDon hd = new HoaDon(rs.getString("MaHoaDon"));
				DonDoiTra ddt = new DonDoiTra(maHoaDon, ngayDoiTra, tienHoan, nv, km, kh, hd);
				dsHoaDonDoiTra.add(ddt);
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Object[][] data = new Object[dsHoaDonDoiTra.size()][7];

		for (int i = 0; i < dsHoaDonDoiTra.size(); i++) {
			data[i][0] = dsHoaDonDoiTra.get(i).getMaDonDoiTra();
			data[i][1] = dsHoaDonDoiTra.get(i).getNgayDoiTra();
			data[i][2] = dsHoaDonDoiTra.get(i).getTienHoan();
			data[i][3] = dsHoaDonDoiTra.get(i).getMaNhanVien().getMaNhanVien();
			data[i][4] = dsHoaDonDoiTra.get(i).getMaKhuyenMai().getMaKhuyenMai();
			data[i][5] = dsHoaDonDoiTra.get(i).getMaKhachhang().getMaKhachHang();
			data[i][6] = dsHoaDonDoiTra.get(i).getMaHoaDon().getMaHoaDon();
		}
		return data;
	
	}

	// thêm đơn đổi trả
	public boolean themDonDoiTra(DonDoiTra donDoiTra) {
	    String sql = "INSERT INTO DonDoiTra (MaDonDoiTra, NgayDoiTra, TienHoan, MaNhanVien, MaKhuyenMai, MaKhachHang, MaHoaDon) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    
	    try (Connection conn = ConnectDB.getConnection()) {
	        if (donDoiTra.getMaKhuyenMai() != null) {
	            String checkMaKhuyenMaiSql = "SELECT 1 FROM KhuyenMai WHERE MaKhuyenMai = ?";
	            try (PreparedStatement checkStmt = conn.prepareStatement(checkMaKhuyenMaiSql)) {
	                checkStmt.setString(1, donDoiTra.getMaKhuyenMai().getMaKhuyenMai());
	                try (ResultSet rs = checkStmt.executeQuery()) {
	                    if (!rs.next()) {
	                        donDoiTra.setMaKhuyenMai(null);
	                    }
	                }
	            }
	        }
	        
	        try (PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1, donDoiTra.getMaDonDoiTra()); 
	            ps.setDate(2, new Date(donDoiTra.getNgayDoiTra().getTime()));
	            ps.setBigDecimal(3, donDoiTra.getTienHoan());
	            ps.setString(4, donDoiTra.getMaNhanVien().getMaNhanVien());
	            
	            if (donDoiTra.getMaKhuyenMai() != null) {
	                ps.setString(5, donDoiTra.getMaKhuyenMai().getMaKhuyenMai());
	            } else {
	                ps.setNull(5, java.sql.Types.VARCHAR);
	            }
	            
	            ps.setString(6, donDoiTra.getMaKhachhang().getMaKhachHang());
	            ps.setString(7, donDoiTra.getMaHoaDon().getMaHoaDon());

	            int rowsAffected = ps.executeUpdate(); 
	            return rowsAffected > 0; 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; 
	    }
	}

	 
	 
	 // xóa đơn đổi trả
	 public boolean xoaDonDoiTra(String maDonDoiTra) {
		    String sql = "DELETE FROM DonDoiTra WHERE MaDonDoiTra = ?";
		    
		    try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
		        ps.setString(1, maDonDoiTra);
		        
		        int rowsAffected = ps.executeUpdate(); 
		        return rowsAffected > 0; 
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false; 
		    }
		}
	 
	 
		   
		    public boolean checkMaDonDoiTraExists(String maDonDoiTra) {
		        boolean exists = false;
		        String sql = "SELECT COUNT(*) FROM DonDoiTra WHERE MaDonDoiTra = ?";

		        try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
		             
		            ps.setString(1, maDonDoiTra);
		            ResultSet rs = ps.executeQuery();

		            if (rs.next()) {
		                int count = rs.getInt(1);
		                exists = (count > 0);
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }

		        return exists;
		   }
		


		    public String maDonDoiTra(String maDonDoiTra) {
		    	 String sql = "SELECT MaDonDoiTra FROM DonDoiTra WHERE MaDonDoiTra = ?";
		         try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
		             ps.setString(1, maDonDoiTra);
		             ResultSet resultSet = ps.executeQuery();
		             
		             // Kiểm tra kết quả
		             if (resultSet.next()) {
		                 return resultSet.getString("MaDonDoiTra");
		             }
		         } catch (SQLException e) {
		             e.printStackTrace();
		         }
		         return null; // Trả về null nếu không tìm thấy
		    }
		    
			 // lấy thông tin đơn đổi trả theo mã đơn đổi trả
		   
		    public DonDoiTra layThongTinDonDoiTraTheoMa(String maDonDoiTra) {
		        DonDoiTra donDoiTra = null;
		        String sql = "select * from DonDoiTra where MaDonDoiTra =  ?";

		        try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
		            ps.setString(1, maDonDoiTra);
		            try (ResultSet rs = ps.executeQuery()) {
		                if (rs.next()) {
		                    String ma = rs.getString("MaDonDoiTra");
		                    Date ngayDoiTra = rs.getDate("NgayDoiTra"); 
		                    BigDecimal tienHoan = rs.getBigDecimal("TienHoan");
		                    String maNV = rs.getString("MaNhanVien");
		                    NhanVien nv = new NhanVien(maNV);
		                    String maKM = rs.getString("MaKhuyenMai");
		                    KhuyenMai km = new KhuyenMai(maKM);
		                    String maKH = rs.getString("MaKhachHang");
		                    KhachHang kh = new KhachHang(maKH);
		                    String maHD = rs.getString("MaHoaDon");
		                    HoaDon hd = new HoaDon(maHD);
		               
		                    
		                    donDoiTra = new DonDoiTra(maDonDoiTra, ngayDoiTra, tienHoan, nv, km, kh, hd);
		                }
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return donDoiTra;
		    }
		    
		    
		    public static Object[][] layDanhSachHoaDonDoiTraCuoiNgay() {
				dsHoaDonDoiTra = new ArrayList<>();

				try {
					ConnectDB.getInstance();
					Connection con = ConnectDB.getConnection();
					String sql = "select * from DonDoiTra  WHERE CONVERT(DATE, ngayDoiTra) = CAST(GETDATE() AS DATE)";
					Statement statement = con.createStatement();

					ResultSet rs = statement.executeQuery(sql);
					while (rs.next()) {
						String maHoaDon = rs.getString(1);
						Date ngayDoiTra = rs.getDate(2);
						BigDecimal tienHoan = rs.getBigDecimal(3);
						NhanVien nv = new NhanVien(rs.getString("MaNhanVien"));
						KhuyenMai km = new KhuyenMai(rs.getString("MaKhuyenMai"));
						KhachHang kh = new KhachHang(rs.getString("MaKhachHang"));
						HoaDon hd = new HoaDon(rs.getString("MaHoaDon"));
						DonDoiTra ddt = new DonDoiTra(maHoaDon, ngayDoiTra, tienHoan, nv, km, kh, hd);
						dsHoaDonDoiTra.add(ddt);
					}

				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				Object[][] data = new Object[dsHoaDonDoiTra.size()][7];

				for (int i = 0; i < dsHoaDonDoiTra.size(); i++) {
					data[i][0] = dsHoaDonDoiTra.get(i).getMaDonDoiTra();
					data[i][1] = dsHoaDonDoiTra.get(i).getNgayDoiTra();
					data[i][2] = dsHoaDonDoiTra.get(i).getTienHoan();
					data[i][3] = dsHoaDonDoiTra.get(i).getMaNhanVien().getMaNhanVien();
					data[i][4] = dsHoaDonDoiTra.get(i).getMaKhuyenMai().getMaKhuyenMai();
					data[i][5] = dsHoaDonDoiTra.get(i).getMaKhachhang().getMaKhachHang();
					data[i][6] = dsHoaDonDoiTra.get(i).getMaHoaDon().getMaHoaDon();
				}
				return data;
			
			}
		 
		   
		    
	 
	 

}
