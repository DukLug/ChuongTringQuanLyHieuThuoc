package dao;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;

import connectDB.ConnectDB;
import entity.LoaiSanPham;
import entity.NhaCungCap;
import entity.SanPhamYTe;
import customDataType.TrangThaiSanPham;
import customDataType.DonViTinh;

public class SanPhamYTeDAO {
    public static boolean insertSanPhamYTe(SanPhamYTe sanPhamYTe) {
        String sql = "INSERT INTO SanPhamYTe " +
            "(MaSanPham, TenSanPham, NuocSanXuat, TrangThai, GhiChu, MoTa, DangBaoChe, Thue, ThanhPhan, " +
            "DonViTinh1, DonViTinh2, DonViTinh3, GiaVonDonViTinh1, GiaBanDonViTinh2, GiaBanDonViTinh3, " +
            "GiaTriQuyDoi2, GiaTriQuyDoi3, MaNhaCungCap, MaLoai, MaVach, YeuCauKeDon) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, sanPhamYTe.getMaSanPham());
            ps.setString(2, sanPhamYTe.getTenSanPham());
            ps.setString(3, sanPhamYTe.getNuocSanXuat());
            ps.setString(4, sanPhamYTe.getTrangThaiSanPham().toString());
            ps.setString(5, sanPhamYTe.getGhiChu());
            ps.setString(6, sanPhamYTe.getMoTa());
            ps.setString(7, sanPhamYTe.getDangBaoChe());
            ps.setDouble(8, sanPhamYTe.getThue());
            ps.setString(9, sanPhamYTe.getThanhPhan());
            
            ps.setString(10, sanPhamYTe.getDonViTinh1() != null ? sanPhamYTe.getDonViTinh1().toString() : null);
            ps.setString(11, sanPhamYTe.getDonViTinh2() != null ? sanPhamYTe.getDonViTinh2().toString() : null);
            ps.setString(12, sanPhamYTe.getDonViTinh3() != null ? sanPhamYTe.getDonViTinh3().toString() : null);
            
            ps.setBigDecimal(13, sanPhamYTe.getGiaBanDonViTinh1());
            ps.setBigDecimal(14, sanPhamYTe.getGiaBanDonViTinh2());
            ps.setBigDecimal(15, sanPhamYTe.getGiaBanDonViTinh3());
            
            ps.setInt(16, sanPhamYTe.getGiaTriQuyDoi2());
            ps.setInt(17, sanPhamYTe.getGiaTriQuyDoi3());
            
            ps.setString(18, sanPhamYTe.getNhaCungCap().getMaNhaCungCap());
            ps.setString(19, sanPhamYTe.getLoaiSanPham().getMaLoai());
            ps.setString(20, sanPhamYTe.getMaVach());
            ps.setString(21, sanPhamYTe.getYeuCauKeDon());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<SanPhamYTe> layDanhSachTatCaSanPham() {
        ArrayList<SanPhamYTe> sanPhamYTeList = new ArrayList<>();
        String sql = "SELECT * FROM SanPhamYTe";
        
        try (Connection conn = ConnectDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                LoaiSanPham loai = new LoaiSanPham(rs.getString("MaLoai"));
                NhaCungCap ncc = new NhaCungCap(rs.getString("MaNhaCungCap")); 
                
                SanPhamYTe sanPhamYTe = new SanPhamYTe(
                    rs.getString("MaSanPham"),
                    rs.getString("TenSanPham"),
                    
                    rs.getString("NuocSanXuat"),
                    TrangThaiSanPham.fromString(rs.getString("TrangThai")),
                    rs.getString("GhiChu"),
                    rs.getString("MoTa"),
                    rs.getString("DangBaoChe"),
                    rs.getDouble("Thue"),
                    rs.getString("ThanhPhan"),
                    
                    DonViTinh.fromString(rs.getString("DonViTinh1")),
                    rs.getString("DonViTinh2") != null ? DonViTinh.fromString(rs.getString("DonViTinh2")) : null,
                    rs.getString("DonViTinh3") != null ? DonViTinh.fromString(rs.getString("DonViTinh3")) : null,
                    
                    rs.getBigDecimal("GiaVonDonViTinh1"),
                    rs.getBigDecimal("GiaBanDonViTinh2"),
                    rs.getBigDecimal("GiaBanDonViTinh3"),
                    
                    rs.getInt("GiaTriQuyDoi2"),
                    rs.getInt("GiaTriQuyDoi3"),
                    
                    ncc,
                    loai,
                    rs.getString("MaVach"),
                    rs.getString("YeuCauKeDon"),
                    null,
                    null
                );
                
                sanPhamYTeList.add(sanPhamYTe);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return sanPhamYTeList;
    }

    public static SanPhamYTe getSanPhamYTeByMaSanPham(String maSanPham) {
        String sql = "SELECT * FROM SanPhamYTe WHERE MaSanPham = ?";
        SanPhamYTe sanPhamYTe = null;
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, maSanPham);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                LoaiSanPham loai = new LoaiSanPham(rs.getString("MaLoai"));
                NhaCungCap ncc = new NhaCungCap(rs.getString("MaNhaCungCap")); 
                
                sanPhamYTe = new SanPhamYTe(
                    rs.getString("MaSanPham"),
                    rs.getString("TenSanPham"),
                    
                    rs.getString("NuocSanXuat"),
                    TrangThaiSanPham.valueOf(rs.getString("TrangThai")),
                    rs.getString("GhiChu"),
                    rs.getString("MoTa"),
                    rs.getString("DangBaoChe"),
                    rs.getDouble("Thue"),
                    rs.getString("ThanhPhan"),
                    
                    DonViTinh.valueOf(rs.getString("DonViTinh1")),
                    rs.getString("DonViTinh2") != null ? DonViTinh.valueOf(rs.getString("DonViTinh2")) : null,
                    rs.getString("DonViTinh3") != null ? DonViTinh.valueOf(rs.getString("DonViTinh3")) : null,
                    
                    rs.getBigDecimal("GiaVonDonViTinh1"),
                    rs.getBigDecimal("GiaBanDonViTinh2"),
                    rs.getBigDecimal("GiaBanDonViTinh3"),
                    
                    rs.getInt("GiaTriQuyDoi2"),
                    rs.getInt("GiaTriQuyDoi3"),
                    
                    ncc,
                    loai,
                    rs.getString("MaVach"),
                    rs.getString("YeuCauKeDon"),
                    null,
                    null
                );
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return sanPhamYTe;
    }

    public static boolean updateSanPhamYTe(SanPhamYTe sanPhamYTe) {
        String sql = "UPDATE SanPhamYTe SET " +
            "TenSanPham = ?, NuocSanXuat = ?, TrangThai = ?, GhiChu = ?, MoTa = ?, " +
            "DangBaoChe = ?, Thue = ?, ThanhPhan = ?, DonViTinh1 = ?, DonViTinh2 = ?, DonViTinh3 = ?, " +
            "GiaVonDonViTinh1 = ?, GiaBanDonViTinh2 = ?, GiaBanDonViTinh3 = ?, " +
            "GiaTriQuyDoi2 = ?, GiaTriQuyDoi3 = ?, MaNhaCungCap = ?, MaLoai = ?, " +
            "MaVach = ?, YeuCauKeDon = ? WHERE MaSanPham = ?";
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, sanPhamYTe.getTenSanPham());
            ps.setString(2, sanPhamYTe.getNuocSanXuat());
            ps.setString(3, sanPhamYTe.getTrangThaiSanPham().toString());
            ps.setString(4, sanPhamYTe.getGhiChu());
            ps.setString(5, sanPhamYTe.getMoTa());
            ps.setString(6, sanPhamYTe.getDangBaoChe());
            ps.setDouble(7, sanPhamYTe.getThue());
            ps.setString(8, sanPhamYTe.getThanhPhan());
            
            ps.setString(9, sanPhamYTe.getDonViTinh1() != null ? sanPhamYTe.getDonViTinh1().toString() : null);
            ps.setString(10, sanPhamYTe.getDonViTinh2() != null ? sanPhamYTe.getDonViTinh2().toString() : null);
            ps.setString(11, sanPhamYTe.getDonViTinh3() != null ? sanPhamYTe.getDonViTinh3().toString() : null);
            
            ps.setBigDecimal(12, sanPhamYTe.getGiaBanDonViTinh1());
            ps.setBigDecimal(13, sanPhamYTe.getGiaBanDonViTinh2());
            ps.setBigDecimal(14, sanPhamYTe.getGiaBanDonViTinh3());
            
            ps.setInt(15, sanPhamYTe.getGiaTriQuyDoi2());
            ps.setInt(16, sanPhamYTe.getGiaTriQuyDoi3());
            
            ps.setString(17, sanPhamYTe.getNhaCungCap().getMaNhaCungCap());
            ps.setString(18, sanPhamYTe.getLoaiSanPham().getMaLoai());
            ps.setString(19, sanPhamYTe.getMaVach());
            ps.setString(20, sanPhamYTe.getYeuCauKeDon());
            
            ps.setString(21, sanPhamYTe.getMaSanPham());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteSanPhamYTe(String maSanPham) {
        String sql = "DELETE FROM SanPhamYTe WHERE MaSanPham = ?";
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, maSanPham);
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}