package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.DonNhapHang;
import entity.NhanVien;

public class DonNhapHangDAO {
    
    public DonNhapHangDAO() {}
    
    static ArrayList<DonNhapHang> dsDonNhapHang;
    
    public static ArrayList<DonNhapHang> layDanhSachTatCaDonNhapHang() {
        dsDonNhapHang = new ArrayList<>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM DonNhapHang";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            while (rs.next()) {
                String maDonNhap = rs.getNString("MaDonNhap");
                java.sql.Date ngayNhap = rs.getDate("NgayNhap");
                
                // Fetch related NhanVien
                String maNhanVien = rs.getNString("MaNhanVien");
                NhanVien nhanVien = new NhanVien(maNhanVien);
                
                DonNhapHang donNhapHang = new DonNhapHang(
                    maDonNhap, ngayNhap, nhanVien
                );
                
                dsDonNhapHang.add(donNhapHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDonNhapHang;
    }
    
    public static boolean themDonNhapHang(DonNhapHang donNhapHang) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("INSERT INTO DonNhapHang VALUES(?, ?, ?)");
            stmt.setString(1, donNhapHang.getMaDonNhap());
            stmt.setDate(2, donNhapHang.getNgayNhap());
            stmt.setString(3, donNhapHang.getNhanVienNhap().getMaNhanVien());
            
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
    
    public static boolean capNhatDonNhapHang(DonNhapHang donNhapHang) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement(
                "UPDATE DonNhapHang SET " +
                "NgayNhap = ?, " +
                "MaNhanVien = ? " +
                "WHERE MaDonNhap = ?"
            );
            
            stmt.setDate(1, donNhapHang.getNgayNhap());
            stmt.setString(2, donNhapHang.getNhanVienNhap().getMaNhanVien());
            stmt.setString(3, donNhapHang.getMaDonNhap());
            
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
    
    // Optional: Add a method to find a specific DonNhapHang by its ID
    public static DonNhapHang timDonNhapHang(String maDonNhap) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        DonNhapHang donNhapHang = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM DonNhapHang WHERE MaDonNhap = ?");
            stmt.setString(1, maDonNhap);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                java.sql.Date ngayNhap = rs.getDate("NgayNhap");
                
                String maNhanVien = rs.getString("MaNhanVien");
                NhanVien nhanVien = new NhanVien(maNhanVien);
                
                donNhapHang = new DonNhapHang(
                    maDonNhap, ngayNhap, nhanVien
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
        return donNhapHang;
    }
    
    // Optional: Method to delete a DonNhapHang
    public static boolean xoaDonNhapHang(String maDonNhap) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("DELETE FROM DonNhapHang WHERE MaDonNhap = ?");
            stmt.setString(1, maDonNhap);
            
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
}