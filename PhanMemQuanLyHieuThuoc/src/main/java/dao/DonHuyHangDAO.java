package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.DonHuyHang;
import entity.LoHang;
import entity.NhanVien;

public class DonHuyHangDAO {
    
    public DonHuyHangDAO() {}
    
    static ArrayList<DonHuyHang> dsDonHuyHang;
    
    public static ArrayList<DonHuyHang> layDanhSachTatCaDonHuyHang() {
        dsDonHuyHang = new ArrayList<>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM DonHuyHang";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            while (rs.next()) {
                String maDonHuyHang = rs.getNString("MaDonHuyHang");
                java.sql.Date ngayHuy = rs.getDate("NgayHuy");
                int soLuongDonViTinh1 = rs.getInt("SoLuongDonViTinh1");
                int soLuongDonViTinh2 = rs.getInt("SoLuongDonViTinh2");
                int soLuongDonViTinh3 = rs.getInt("SoLuongDonViTinh3");
                
                // Fetch related LoHang and NhanVien
                String maLoHang = rs.getNString("MaLoHang");
                String maNhanVien = rs.getNString("MaNhanVien");
                
                LoHang loHang = new LoHang(maLoHang);
                NhanVien nhanVien = new NhanVien(maNhanVien);
                
                DonHuyHang donHuyHang = new DonHuyHang(
                    maDonHuyHang, ngayHuy, 
                    soLuongDonViTinh1, soLuongDonViTinh2, soLuongDonViTinh3, 
                    loHang, nhanVien
                );
                
                dsDonHuyHang.add(donHuyHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDonHuyHang;
    }
    
    public static boolean themDonHuyHang(DonHuyHang donHuyHang) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("INSERT INTO DonHuyHang VALUES(?, ?, ?, ?, ?, ?, ?)");
            stmt.setNString(1, donHuyHang.getMaDonHuyHang());
            stmt.setDate(2, donHuyHang.getNgayHuy());
            stmt.setInt(3, donHuyHang.getSoLuongDonViTinh1());
            stmt.setInt(4, donHuyHang.getSoLuongDonViTinh2());
            stmt.setInt(5, donHuyHang.getSoLuongDonViTinh3());
            stmt.setNString(6, donHuyHang.getLoHang().getMaLo());
            stmt.setNString(7, donHuyHang.getNhanVien().getMaNhanVien());
            
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
    
    public static boolean capNhatDonHuyHang(DonHuyHang donHuyHang) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement(
                "UPDATE DonHuyHang SET " +
                "NgayHuy = ?, " +
                "SoLuongDonViTinh1 = ?, " +
                "SoLuongDonViTinh2 = ?, " +
                "SoLuongDonViTinh3 = ?, " +
                "MaLoHang = ?, " +
                "MaNhanVien = ? " +
                "WHERE MaDonHuyHang = ?"
            );
            
            stmt.setDate(1, donHuyHang.getNgayHuy());
            stmt.setInt(2, donHuyHang.getSoLuongDonViTinh1());
            stmt.setInt(3, donHuyHang.getSoLuongDonViTinh2());
            stmt.setInt(4, donHuyHang.getSoLuongDonViTinh3());
            stmt.setString(5, donHuyHang.getLoHang().getMaLo());
            stmt.setString(6, donHuyHang.getNhanVien().getMaNhanVien());
            stmt.setString(7, donHuyHang.getMaDonHuyHang());
            
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
    
    // Optional: Add a method to find a specific DonHuyHang by its ID
    public static DonHuyHang timDonHuyHang(String maDonHuyHang) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        DonHuyHang donHuyHang = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM DonHuyHang WHERE MaDonHuyHang = ?");
            stmt.setString(1, maDonHuyHang);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                java.sql.Date ngayHuy = rs.getDate("NgayHuy");
                int soLuongDonViTinh1 = rs.getInt("SoLuongDonViTinh1");
                int soLuongDonViTinh2 = rs.getInt("SoLuongDonViTinh2");
                int soLuongDonViTinh3 = rs.getInt("SoLuongDonViTinh3");
                
                String maLoHang = rs.getNString("MaLoHang");
                String maNhanVien = rs.getNString("MaNhanVien");
                
                LoHang loHang = new LoHang(maLoHang);
                NhanVien nhanVien = new NhanVien(maNhanVien);
                
                donHuyHang = new DonHuyHang(
                    maDonHuyHang, ngayHuy, 
                    soLuongDonViTinh1, soLuongDonViTinh2, soLuongDonViTinh3, 
                    loHang, nhanVien
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return donHuyHang;
    }
}