package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import customDataType.TrangThaiTaiKhoan;
import entity.TaiKhoan;

public class TaiKhoanDAO {
    
    public TaiKhoanDAO() {}
    
    public static ArrayList<TaiKhoan> layDanhSachTatCaTaiKhoan() {
        ArrayList<TaiKhoan> dsTaiKhoan = new ArrayList<>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM TaiKhoan";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            while (rs.next()) {
                String maTaiKhoan = rs.getNString("MaTaiKhoan");
                String tenDangNhap = rs.getNString("TenDangNhap");
                String matKhau = rs.getString("MatKhau");
                String trangThaiStr = rs.getNString("TrangThai");
                

                TrangThaiTaiKhoan trangThai = TrangThaiTaiKhoan.fromString(trangThaiStr);

                
                TaiKhoan taiKhoan = new TaiKhoan(maTaiKhoan, tenDangNhap, matKhau, trangThai);
                dsTaiKhoan.add(taiKhoan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsTaiKhoan;
    }
    
    public static TaiKhoan dangNhap(String tenDangNhap, String matKhau) {
        TaiKhoan taiKhoan = null;
        PreparedStatement stmt = null;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM TaiKhoan WHERE TenDangNhap = ? AND MatKhau = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, tenDangNhap);
            stmt.setString(2, matKhau);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String maTaiKhoan = rs.getNString("MaTaiKhoan");
                String trangThaiStr = rs.getNString("TrangThai");
                
                TrangThaiTaiKhoan trangThai = TrangThaiTaiKhoan.valueOf(trangThaiStr);
                
                taiKhoan = new TaiKhoan(maTaiKhoan, tenDangNhap, matKhau, trangThai);
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
        return taiKhoan;
    }
    
    public static boolean them(TaiKhoan taiKhoan) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement(
                "INSERT INTO TaiKhoan (MaTaiKhoan, TenDangNhap, MatKhau, TrangThai) VALUES (?, ?, ?, ?)"
            );
            stmt.setNString(1, taiKhoan.getMaTaiKhoan());
            stmt.setNString(2, taiKhoan.getTenDangNhap());
            stmt.setString(3, taiKhoan.getMatKhau());
            stmt.setNString(4, taiKhoan.getTrangThaiTaiKhoan().toString());
            
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
    
    public static boolean capNhat(TaiKhoan taiKhoan) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement(
                "UPDATE TaiKhoan SET TenDangNhap = ?, MatKhau = ?, TrangThai = ? WHERE MaTaiKhoan = ?"
            );
            stmt.setNString(1, taiKhoan.getTenDangNhap());
            stmt.setString(2, taiKhoan.getMatKhau());
            stmt.setNString(3, taiKhoan.getTrangThaiTaiKhoan().toString());
            stmt.setNString(4, taiKhoan.getMaTaiKhoan());
            
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
    
    public static TaiKhoan timTaiKhoan(String maTaiKhoan) {
        TaiKhoan taiKhoan = null;
        PreparedStatement stmt = null;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM TaiKhoan WHERE MaTaiKhoan = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maTaiKhoan);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String tenDangNhap = rs.getNString("TenDangNhap");
                String matKhau = rs.getString("MatKhau");
                String trangThaiStr = rs.getNString("TrangThai");
                
                TrangThaiTaiKhoan trangThai = TrangThaiTaiKhoan.valueOf(trangThaiStr);
                
                taiKhoan = new TaiKhoan(maTaiKhoan, tenDangNhap, matKhau, trangThai);
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
        return taiKhoan;
    }
    
    public static boolean doiMatKhau(String maTaiKhoan, String matKhauMoi) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement(
                "UPDATE TaiKhoan SET MatKhau = ? WHERE MaTaiKhoan = ?"
            );
            stmt.setString(1, matKhauMoi);
            stmt.setNString(2, maTaiKhoan);
            
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
    
    public static boolean xoaTaiKhoan(String maTaiKhoan) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("DELETE FROM TaiKhoan WHERE MaTaiKhoan = ?");
            stmt.setString(1, maTaiKhoan);
            
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
    
    public static boolean kiemTraTonTai(String tenDangNhap) {
        PreparedStatement stmt = null;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT COUNT(*) FROM TaiKhoan WHERE TenDangNhap = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, tenDangNhap);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
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
        return false;
    }
}