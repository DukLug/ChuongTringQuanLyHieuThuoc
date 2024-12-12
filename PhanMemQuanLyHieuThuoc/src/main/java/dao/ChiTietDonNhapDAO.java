package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietDonNhap;
import entity.DonNhapHang;
import entity.SanPhamYTe;

public class ChiTietDonNhapDAO {
    
    public ChiTietDonNhapDAO() {}
    
    public static ArrayList<ChiTietDonNhap> layDanhSachChiTietDonNhap() {
        ArrayList<ChiTietDonNhap> dsChiTietDonNhap = new ArrayList<>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM ChiTietDonNhap";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            while (rs.next()) {
                String maChiTietDonNhap = rs.getNString("MaChiTietDonNhap");
                String maSanPham = rs.getNString("MaSanPham");
                int soLuongDonViTinh1 = rs.getInt("SoLuongDonViTinh1");
                int soLuongDonViTinh2 = rs.getInt("SoLuongDonViTinh2");
                int soLuongDonViTinh3 = rs.getInt("SoLuongDonViTinh3");
                BigDecimal giaNhap = rs.getBigDecimal("GiaNhap");
                String maDonNhap = rs.getNString("MaDonNhap");
                
                // You'll need to implement methods to fetch associated objects
                SanPhamYTe sanPham = new SanPhamYTe(maSanPham);
                DonNhapHang donNhapHang = new DonNhapHang(maDonNhap);
                
                ChiTietDonNhap chiTietDonNhap = new ChiTietDonNhap(
                    maChiTietDonNhap, 
                    sanPham, 
                    soLuongDonViTinh1, 
                    soLuongDonViTinh2, 
                    soLuongDonViTinh3, 
                    giaNhap, 
                    donNhapHang
                );
                
                dsChiTietDonNhap.add(chiTietDonNhap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsChiTietDonNhap;
    }
    
    public static ArrayList<ChiTietDonNhap> layDanhSachChiTietDonNhapTheoMaDonNhap(String maDonNhap) {
        ArrayList<ChiTietDonNhap> dsChiTietDonNhap = new ArrayList<>();
        PreparedStatement stmt = null;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM ChiTietDonNhap WHERE MaDonNhap = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maDonNhap);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                String maChiTietDonNhap = rs.getNString("MaChiTietDonNhap");
                String maSanPham = rs.getNString("MaSanPham");
                int soLuongDonViTinh1 = rs.getInt("SoLuongDonViTinh1");
                int soLuongDonViTinh2 = rs.getInt("SoLuongDonViTinh2");
                int soLuongDonViTinh3 = rs.getInt("SoLuongDonViTinh3");
                BigDecimal giaNhap = rs.getBigDecimal("GiaNhap");
                
                // You'll need to implement methods to fetch associated objects
                SanPhamYTe sanPham = new SanPhamYTe(maSanPham);
                DonNhapHang donNhapHang = new DonNhapHang(maDonNhap);
                
                ChiTietDonNhap chiTietDonNhap = new ChiTietDonNhap(
                    maChiTietDonNhap, 
                    sanPham, 
                    soLuongDonViTinh1, 
                    soLuongDonViTinh2, 
                    soLuongDonViTinh3, 
                    giaNhap, 
                    donNhapHang
                );
                
                dsChiTietDonNhap.add(chiTietDonNhap);
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
        return dsChiTietDonNhap;
    }
    
    public static boolean them(ChiTietDonNhap chiTietDonNhap) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement(
                "INSERT INTO ChiTietDonNhap VALUES(?, ?, ?, ?, ?, ?, ?)"
            );
            stmt.setNString(1, chiTietDonNhap.getMaChiTietDonNhap());
            stmt.setNString(2, chiTietDonNhap.getMaSanPham().getMaSanPham());
            stmt.setInt(3, chiTietDonNhap.getSoLuongDonViTinh1());
            stmt.setInt(4, chiTietDonNhap.getSoLuongDonViTinh2());
            stmt.setInt(5, chiTietDonNhap.getSoLuongDonViTinh3());
            stmt.setBigDecimal(6, chiTietDonNhap.getGiaNhap());
            stmt.setNString(7, chiTietDonNhap.getMaDonNhap().getMaDonNhap());
            
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
    
    public static boolean capNhat(ChiTietDonNhap chiTietDonNhap) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement(
                "UPDATE ChiTietDonNhap SET MaSanPham = ?, " +
                "SoLuongDonViTinh1 = ?, SoLuongDonViTinh2 = ?, SoLuongDonViTinh3 = ?, " +
                "GiaNhap = ?, MaDonNhap = ? WHERE MaChiTietDonNhap = ?"
            );
            stmt.setNString(1, chiTietDonNhap.getMaSanPham().getMaSanPham());
            stmt.setInt(2, chiTietDonNhap.getSoLuongDonViTinh1());
            stmt.setInt(3, chiTietDonNhap.getSoLuongDonViTinh2());
            stmt.setInt(4, chiTietDonNhap.getSoLuongDonViTinh3());
            stmt.setBigDecimal(5, chiTietDonNhap.getGiaNhap());
            stmt.setNString(6, chiTietDonNhap.getMaDonNhap().getMaDonNhap());
            stmt.setNString(7, chiTietDonNhap.getMaChiTietDonNhap());
            
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
    
    public static ChiTietDonNhap timChiTietDonNhap(String maChiTietDonNhap) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        ChiTietDonNhap chiTietDonNhap = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM ChiTietDonNhap WHERE MaChiTietDonNhap = ?");
            stmt.setString(1, maChiTietDonNhap);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String maSanPham = rs.getNString("MaSanPham");
                int soLuongDonViTinh1 = rs.getInt("SoLuongDonViTinh1");
                int soLuongDonViTinh2 = rs.getInt("SoLuongDonViTinh2");
                int soLuongDonViTinh3 = rs.getInt("SoLuongDonViTinh3");
                BigDecimal giaNhap = rs.getBigDecimal("GiaNhap");
                String maDonNhap = rs.getNString("MaDonNhap");
                
                // You'll need to implement methods to fetch associated objects
                SanPhamYTe sanPham = new SanPhamYTe(maSanPham);
                DonNhapHang donNhapHang = new DonNhapHang(maDonNhap);
                
                chiTietDonNhap = new ChiTietDonNhap(
                    maChiTietDonNhap, 
                    sanPham, 
                    soLuongDonViTinh1, 
                    soLuongDonViTinh2, 
                    soLuongDonViTinh3, 
                    giaNhap, 
                    donNhapHang
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
        return chiTietDonNhap;
    }
    
}