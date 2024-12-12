package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiSanPham;

public class LoaiSanPhamDAO {
    
    public LoaiSanPhamDAO() {}

    static ArrayList<LoaiSanPham> dsLoaiSanPham;

    public static ArrayList<LoaiSanPham> layDanhSachTatCaLoaiSanPham() {
        dsLoaiSanPham = new ArrayList<>();

        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM LoaiSanPham";
            Statement statement = con.createStatement();

            // Execute SQL query and get the result set
            ResultSet rs = statement.executeQuery(sql);

            // Process each result
            while (rs.next()) {
                String maLoai = rs.getNString(1);
                String tenLoai = rs.getNString(2);
                
                LoaiSanPham loaiSanPham = new LoaiSanPham(maLoai);
                loaiSanPham.setTenLoai(tenLoai);
                
                dsLoaiSanPham.add(loaiSanPham);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsLoaiSanPham;

    }

    public static boolean them(LoaiSanPham loaiSanPham) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;

        int n = 0;

        try {
            stmt = con.prepareStatement("INSERT INTO LoaiSanPham VALUES(?, ?)");
            stmt.setNString(1, loaiSanPham.getMaLoai());
            stmt.setNString(2, loaiSanPham.getTenLoai());

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

    public static boolean capNhat(LoaiSanPham loaiSanPham) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;

        int n = 0;

        try {
            stmt = con.prepareStatement("UPDATE LoaiSanPham SET TenLoai = ? WHERE MaLoai = ?");
            stmt.setNString(1, loaiSanPham.getTenLoai());
            stmt.setNString(2, loaiSanPham.getMaLoai());

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

    public static LoaiSanPham timLoaiSanPham(String maLoai) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        LoaiSanPham loaiSanPham = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM LoaiSanPham WHERE MaLoai = ?");
            stmt.setString(1, maLoai);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String tenLoai = rs.getString("TenLoai");
                loaiSanPham = new LoaiSanPham(maLoai);
                loaiSanPham.setTenLoai(tenLoai);
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

        return loaiSanPham;
    }
}

