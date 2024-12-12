package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.KhachHang;
import entity.LoHang;
import entity.SanPhamYTe;
import entity.ChiTietDonNhap;

public class LoHangDAO {
    
    public LoHangDAO() {}
    
    static ArrayList<LoHang> dsLoHang;
    
    public static ArrayList<LoHang> layDanhSachTatCaLoHang() {
        dsLoHang = new ArrayList<>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM LoHang";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            while (rs.next()) {
                String maLo = rs.getString("MaLo");
                java.sql.Date ngaySanXuat = rs.getDate("NgaySanXuat");
                java.sql.Date hanSuDung = rs.getDate("HanSuDung");
                java.math.BigDecimal giaNhap = rs.getBigDecimal("GiaNhap");
                int soLuongDonViTinh1 = rs.getInt("SoLuongDonViTinh1");
                int soLuongDonViTinh2 = rs.getInt("SoLuongDonViTinh2");
                int soLuongDonViTinh3 = rs.getInt("SoLuongDonViTinh3");
                String viTri = rs.getString("ViTri");
                
                // Fetch related SanPhamYTe and ChiTietDonNhap
                String maSanPham = rs.getString("MaSanPham");
                String maChiTietDonNhap = rs.getString("MaChiTietDonNhap");
                
                SanPhamYTe sanPham = new SanPhamYTe(maSanPham);
                ChiTietDonNhap chiTietDonNhap = new ChiTietDonNhap(maChiTietDonNhap);
                
                LoHang loHang = new LoHang(maLo, ngaySanXuat, hanSuDung, giaNhap, 
                    soLuongDonViTinh1, soLuongDonViTinh2, soLuongDonViTinh3, 
                    viTri, sanPham, chiTietDonNhap);
                
                dsLoHang.add(loHang);

                loHang.setSoLuongDonViTinh1(soLuongDonViTinh1);
                loHang.setSoLuongDonViTinh2(soLuongDonViTinh2);
                loHang.setSoLuongDonViTinh3(soLuongDonViTinh3);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsLoHang;
    }
    
    public static boolean themLoHang(LoHang loHang) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("INSERT INTO LoHang VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, loHang.getMaLo());
            stmt.setDate(2, loHang.getNgaySanXuat());
            stmt.setDate(3, loHang.getHanSuDung());
            stmt.setBigDecimal(4, loHang.getGiaNhap());
            stmt.setInt(5, loHang.getSoLuongDonViTinh1());
            stmt.setInt(6, loHang.getSoLuongDonViTinh2());
            stmt.setInt(7, loHang.getSoLuongDonViTinh3());
            stmt.setString(8, loHang.getViTri());
            stmt.setString(9, loHang.getSanPham().getMaSanPham());
            stmt.setString(10, loHang.getMaChiTietDonNhap().getMaChiTietDonNhap());
            
            n = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return n > 0;
    }
    
    public static boolean capNhatLoHang(LoHang loHang) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement(
                "UPDATE LoHang SET " +
                "NgaySanXuat = ?, " +
                "HanSuDung = ?, " +
                "GiaNhap = ?, " +
                "SoLuongDonViTinh1 = ?, " +
                "SoLuongDonViTinh2 = ?, " +
                "SoLuongDonViTinh3 = ?, " +
                "ViTri = ?, " +
                "MaSanPham = ?, " +
                "MaChiTietDonNhap = ? " +
                "WHERE MaLo = ?"
            );
            
            stmt.setDate(1, loHang.getNgaySanXuat());
            stmt.setDate(2, loHang.getHanSuDung());
            stmt.setBigDecimal(3, loHang.getGiaNhap());
            stmt.setInt(4, loHang.getSoLuongDonViTinh1());
            stmt.setInt(5, loHang.getSoLuongDonViTinh2());
            stmt.setInt(6, loHang.getSoLuongDonViTinh3());
            stmt.setString(7, loHang.getViTri());
            stmt.setString(8, loHang.getSanPham().getMaSanPham());
            stmt.setString(9, loHang.getMaChiTietDonNhap().getMaChiTietDonNhap());
            stmt.setString(10, loHang.getMaLo());
            
            n = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return n > 0;
    }
    /// tìm lô hang theo mã sản phẩm
	public String maLoTheoSanPham(String maSP) {
	    String sql = "SELECT MaLo FROM LoHang WHERE MaSanPham = ?";
	    String maLo = null;

	    try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
	        ps.setString(1, maSP);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            maLo = rs.getString("MaLo");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return maLo; 
	}
	
	public LoHang timLoSP(String maSP) {
	    String sql = "SELECT MaLo, MaSanPham, SoLuongDonViTinh1, SoLuongDonViTinh2, SoLuongDonViTinh3 FROM LoHang WHERE MaSanPham = ?";
	    LoHang loHang = null;

	    try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
	        ps.setString(1, maSP);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            String maLo = rs.getString("MaLo");
	            String maSanPham = rs.getString("MaSanPham");
	            SanPhamYTe sp = new SanPhamYTe(maSanPham);
	            int soLuongDVT1 = rs.getInt("SoLuongDonViTinh1");
	            int soLuongDVT2 = rs.getInt("SoLuongDonViTinh2");
	            int soLuongDVT3 = rs.getInt("SoLuongDonViTinh3");

	            loHang = new LoHang(maLo, soLuongDVT1, soLuongDVT2, soLuongDVT3, sp);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return loHang;
	}


	public boolean capNhatSoLuongSP(String maSanPham, int soLuongDVT1, int soLuongDVT2, int soLuongDVT3) {
	    int n1 = 0, n2 = 0, n3 = 0;
	    try {
	        // Cập nhật SoLuongDonViTinh1
	        PreparedStatement ps1 = ConnectDB.getConnection().prepareStatement(
	            "UPDATE LoHang SET SoLuongDonViTinh1 = SoLuongDonViTinh1 - ? WHERE MaSanPham = ?");
	        ps1.setInt(1, soLuongDVT1);
	        ps1.setString(2, maSanPham);
	        n1 = ps1.executeUpdate();

	        // Cập nhật SoLuongDonViTinh2
	        PreparedStatement ps2 = ConnectDB.getConnection().prepareStatement(
	            "UPDATE LoHang SET SoLuongDonViTinh2 = SoLuongDonViTinh2 - ? WHERE MaSanPham = ?");
	        ps2.setInt(1, soLuongDVT2);
	        ps2.setString(2, maSanPham);
	        n2 = ps2.executeUpdate();

	        // Cập nhật SoLuongDonViTinh3
	        PreparedStatement ps3 = ConnectDB.getConnection().prepareStatement(
	            "UPDATE LoHang SET SoLuongDonViTinh3 = SoLuongDonViTinh3 - ? WHERE MaSanPham = ?");
	        ps3.setInt(1, soLuongDVT3);
	        ps3.setString(2, maSanPham);
	        n3 = ps3.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return (n1 > 0 && n2 > 0 && n3 > 0); // Chỉ trả về true nếu cả ba đều thành công
	}

}
