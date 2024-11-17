package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import entity.KhuyenMai;
import entity.NhanVien;

public class HoaDonDAO {
	
	public HoaDonDAO() {

	}
	
	public static ArrayList<HoaDon> dsHoaDon;
	private static ArrayList<HoaDon> dsHoaDon2;
	
	public String getMaKhachHangByMaHoaDon(String maHoaDon) {
        String maKhachHang = null;
        String sql = "SELECT maKhachHang FROM HoaDon WHERE MaHoaDon = ?";

        try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql)) {
            stmt.setString(1, maHoaDon);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                maKhachHang = rs.getString("maKhachHang");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maKhachHang;
    }
	
	public HoaDon layThongTinKhachHangTheoMaHoaDon(String maHoaDon) {
	    HoaDon chiTietHoaDon = null ;
	    String sql = "	select kh.HoTen, kh.Sdt, hd.MaHoaDon, hd.ThanhTien from HoaDon hd join KhachHang kh on hd.MaKhachHang = kh.MaKhachHang where MaHoaDon = ? ";

	    try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
	        ps.setString(1, maHoaDon); 
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	           String hoten = rs.getString("HoTen");
	           String sdt = rs.getString("Sdt");
	           String mahd = rs.getString("MaHoaDon");
	           BigDecimal thanhTien = rs.getBigDecimal("ThanhTien");
	           
	           KhachHang kh = new KhachHang(hoten, sdt);
	           
	           chiTietHoaDon = new HoaDon(kh,mahd,thanhTien);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return chiTietHoaDon;
	}
	
	public static Object[][] layDanhSachHoaDon() {
		dsHoaDon = new ArrayList<>();

		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select MaHoaDon, NgayTao, DiemSuDung, ThanhTien, MaNhanVien, MaKhuyenMai, MaKhachHang from HoaDon";
			Statement statement = con.createStatement();

			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maHoaDon = rs.getString(1);
				Date ngayTao = rs.getDate(2);
				int diemSuDung = rs.getInt(3);
				BigDecimal thanhTien = rs.getBigDecimal(4);
				NhanVien nv = new NhanVien(rs.getString("MaNhanVien"));
				KhuyenMai km = new KhuyenMai(rs.getString("MaKhuyenMai"));
				KhachHang kh = new KhachHang(rs.getString("MaKhachHang"));

				HoaDon hd = new HoaDon(maHoaDon, ngayTao, diemSuDung, thanhTien, nv, km, kh);
				dsHoaDon.add(hd);
				
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Object[][] data = new Object[dsHoaDon.size()][7];
//		String [] headers = {"Mã hóa đơn","Loại hóa đơn", "Thời gian", "Số lượng", "Doanh thu", "Phí trả hàng", "Thực thu"};
//		0,;oai, 1,soluong, 3,phitrahang, thucthu
		for (int i = 0; i < dsHoaDon.size(); i++) {
			data[i][0] = dsHoaDon.get(i).getMaHoaDon();
			data[i][1] = dsHoaDon.get(i).getNgayTao();
			data[i][2] = dsHoaDon.get(i).getDiemSuDung();
			data[i][3] = dsHoaDon.get(i).getThanhTien();
			data[i][4] = dsHoaDon.get(i).getNhanVien().getMaNhanVien();
			data[i][5] = dsHoaDon.get(i).getKhuyenMai().getMaKhuyenMai();
			data[i][6] = dsHoaDon.get(i).getKhachHang().getMaKhachHang();
		}
		return data;
	
	}

	public Object[][] layDanhSachHoaDonBanHangTheoNgay() {
	    dsHoaDon2 = new ArrayList<>();

	    try {
	        ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();
	        
//	        String sql = "SELECT MaHoaDon, NgayTao, DiemSuDung, ThanhTien, MaNhanVien, MaKhuyenMai, MaKhachHang FROM HoaDon WHERE NgayTao = '2024-11-04'";
	        String sql = "SELECT MaHoaDon, NgayTao, DiemSuDung, ThanhTien, MaNhanVien, MaKhuyenMai, MaKhachHang " +
            "FROM HoaDon " +
            "WHERE CONVERT(DATE, NgayTao) = CAST(GETDATE() AS DATE)";
	        Statement statement = con.createStatement();
	        ResultSet rs = statement.executeQuery(sql);
	        
	        // Đọc dữ liệu và thêm vào dsHoaDon2
	        while (rs.next()) {
	            String maHoaDon2 = rs.getString(1);
	            Date ngayTao2 = rs.getDate(2);
	            int diemSuDung2 = rs.getInt(3);
	            BigDecimal thanhTien2 = rs.getBigDecimal(4);
	            
	            
	            NhanVien nv2 = new NhanVien(rs.getString("MaNhanVien"));
	            KhuyenMai km2 = new KhuyenMai(rs.getString("MaKhuyenMai"));
	            KhachHang kh2 = new KhachHang(rs.getString("MaKhachHang"));

	            
	            HoaDon hd = new HoaDon(maHoaDon2, ngayTao2, diemSuDung2, thanhTien2, nv2, km2, kh2);
	            dsHoaDon2.add(hd);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    Object[][] data = new Object[dsHoaDon2.size()][7];
	    
	    for (int i = 0; i < dsHoaDon2.size(); i++) {
	        data[i][0] = dsHoaDon2.get(i).getMaHoaDon();
	        data[i][1] = dsHoaDon2.get(i).getNgayTao();
	        data[i][2] = dsHoaDon2.get(i).getDiemSuDung();
	        data[i][3] = dsHoaDon2.get(i).getThanhTien();
	        data[i][4] = dsHoaDon2.get(i).getNhanVien().getMaNhanVien();
	        data[i][5] = dsHoaDon2.get(i).getKhuyenMai().getMaKhuyenMai();
	        data[i][6] = dsHoaDon2.get(i).getKhachHang().getMaKhachHang();
	    }
	    return data;
	}


	public static Object[][] layDataHD(int ngay, int thang, int nam, String nv) {
	    Object[][] data = null;
	    try {
	        ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();
	        String sql = "SELECT * FROM HoaDon WHERE (DAY(NgayTao) = ? OR ? = '') AND (MONTH(NgayTao) = ? OR ? = '') AND (YEAR(NgayTao) = ? OR ? = '') AND (MaNhanVien = ? OR ? = '') ORDER BY MaNhanVien ASC, NgayTao ASC";
	        
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

	        statement.setInt(1, ngay);
	        statement.setString(2, (ngay == 0) ? "" : String.valueOf(ngay));
	        statement.setInt(3, thang);
	        statement.setString(4, (thang == 0) ? "" : String.valueOf(thang));
	        statement.setInt(5, nam);
	        statement.setString(6, (nam == 0) ? "" : String.valueOf(nam));
	        statement.setString(7, nv);
	        statement.setString(8, (nv == null) ? "" : nv);
	        ResultSet rs = statement.executeQuery();
	        
	        rs.last();
	        int rowCount = rs.getRow();
	        rs.beforeFirst();
	        
	        data = new Object[rowCount][5];
	        
	        int i = 0;
	        while (rs.next()) {
	            data[i][0] = rs.getString("MaHoaDon");
	            data[i][1] = rs.getBigDecimal("ThanhTien");
	            data[i][2] = rs.getDate("NgayTao");
	            data[i][3] = rs.getString("MaNhanVien");
	            data[i][4] = rs.getString("MaKhachHang");
	            i++;
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return data;
	}
	

	
	// lấy 10 hóa đơn theo ngày gần nhất
	public ArrayList<HoaDon> getHoaDon() {
	    ArrayList<HoaDon> danhSachHoaDon = new ArrayList<>();
	    String sql = "SELECT top 10 * FROM HoaDon";

	    try (PreparedStatement stmt = ConnectDB.getConnection().prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            String maHD = rs.getString("MaHoaDon");
	            Date ngayTao = rs.getDate("NgayTao");
	            int diemichLuy = rs.getInt("DiemSuDung");
	            BigDecimal thanhTien = rs.getBigDecimal("ThanhTien");
	            String maNV = rs.getString("MaNhanVien");
	            NhanVien nv = new NhanVien(maNV);
	            String maKhuyenMai = rs.getString("MaKhuyenMai");
	            KhuyenMai km = new KhuyenMai(maKhuyenMai);
	            String maKH = rs.getString("MaKhachHang");
	            KhachHang kh = new KhachHang(maKH);
	            
	            HoaDon hd = new HoaDon(maHD, ngayTao, diemichLuy, thanhTien, nv, km, kh);
	            danhSachHoaDon.add(hd);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return danhSachHoaDon;
	}
	public boolean them(HoaDon hd) {
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
			
			if (hd.getKhuyenMai().getMaKhuyenMai() != null) {
				stmt.setString(6, hd.getKhuyenMai().getMaKhuyenMai());
            } else {
            	stmt.setNull(6, java.sql.Types.VARCHAR);
            }
			
			stmt.setString(7, hd.getKhachHang().getMaKhachHang());
			
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
