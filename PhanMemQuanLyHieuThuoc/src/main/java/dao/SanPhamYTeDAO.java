package dao;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.LoaiSanPham;
import entity.NhaCungCap;
import entity.SanPhamYTe;
import customDataType.TrangThaiSanPham;
import customDataType.DonViTinh;

public class SanPhamYTeDAO {
	public static ArrayList<SanPhamYTe> sanPhamYTe;
    // Add a method to insert a SanPhamYTe
    public static boolean insertSanPhamYTe(SanPhamYTe sanPhamYTe) {
        String sql = "INSERT INTO SanPhamYTe (MaSanPham, TenSanPham, NgaySanXuat, HanSuDung, NuocSanXuat, TrangThai, GhiChu, MoTa, DangBaoChe, Thue, ThanhPhan, DonViTinh, MaNhaCungCap, MaLoai, GiaBan, MaVach, YeuCauKeDon) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setNString(1, sanPhamYTe.getMaSanPham());
            ps.setNString(2, sanPhamYTe.getTenSanPham());
            ps.setDate(3, sanPhamYTe.getNgaySanXuat());
            ps.setDate(4, sanPhamYTe.getHanSuDung());
            ps.setNString(5, sanPhamYTe.getNuocSanXuat());
            ps.setNString(6, sanPhamYTe.getTrangThaiSanPham().toString());
            ps.setNString(7, sanPhamYTe.getGhiChu());
            ps.setNString(8, sanPhamYTe.getMoTa());
            ps.setNString(9, sanPhamYTe.getDangBaoChe());
            ps.setDouble(10, sanPhamYTe.getThue());
            ps.setNString(11, sanPhamYTe.getThanhPhan());
            ps.setNString(12, sanPhamYTe.getDonViTinh().toString());
            ps.setNString(13, sanPhamYTe.getNhaCungCap().getMaNhaCungCap());
            ps.setNString(14, sanPhamYTe.getLoaiSanPham().getMaLoai());
            ps.setBigDecimal(15, sanPhamYTe.getGiaBan());
            ps.setNString(16, sanPhamYTe.getMaVach());
            ps.setNString(17, sanPhamYTe.getYeuCauKeDon());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get a list of all SanPhamYTe
    public static ArrayList<SanPhamYTe> layDanhSachTatCaSanPham() {
    	ArrayList<SanPhamYTe> sanPhamYTeList = new ArrayList<>();
        String sql = "SELECT * FROM SanPhamYTe";
        
        try (Connection conn = ConnectDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
//        	public SanPhamYTe(String maSanPham, String tenSanPham, Date ngaySanXuat, Date hanSuDung, String nuocSanXuat,
//        			TrangThaiSanPham trangThaiSanPham, String ghiChu, String moTa, String dangBaoChe, double thue,
//        			String thanhPhan, DonViTinh donViTinh, NhaCungCap nhaCungCap, LoaiSanPham loaiSanPham, BigDecimal giaBan,
//        			String maVach, String yeuCauKeDon, BufferedImage hinhAnh) {
            while (rs.next()) {
            	LoaiSanPham loai =  new LoaiSanPham(rs.getNString("MaLoai"));
            	NhaCungCap ncc = new NhaCungCap(rs.getNString("MaNhaCungCap")); 
                SanPhamYTe sanPhamYTe = new SanPhamYTe(
                        rs.getNString("MaSanPham"),
                        rs.getNString("TenSanPham"),
                        rs.getDate("NgaySanXuat"),
                        rs.getDate("HanSuDung"),
                        rs.getNString("NuocSanXuat"),
                        TrangThaiSanPham.fromString(rs.getNString("TrangThai")),
                        rs.getNString("GhiChu"),
                        rs.getNString("MoTa"),
                        rs.getNString("DangBaoChe"),
                        rs.getDouble("Thue"),
                        rs.getNString("ThanhPhan"),
                        DonViTinh.fromString(rs.getNString("DonViTinh")),
                        ncc,
                        loai,  // Assuming NhaCungCap and LoaiSanPham are retrieved elsewhere
                        rs.getBigDecimal("GiaBan"),
                        rs.getNString("MaVach"),
                        rs.getNString("YeuCauKeDon"),
                        null
                );
                sanPhamYTeList.add(sanPhamYTe);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return sanPhamYTeList;
    }

    // Get a SanPhamYTe by its MaSanPham
    public static SanPhamYTe getSanPhamYTeByMaSanPham(String maSanPham) {
        String sql = "SELECT * FROM SanPhamYTe WHERE MaSanPham = ?";
        SanPhamYTe sanPhamYTe = null;
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, maSanPham);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                sanPhamYTe = new SanPhamYTe(
                        rs.getNString("MaSanPham"),
                        rs.getNString("TenSanPham"),
                        rs.getDate("NgaySanXuat"),
                        rs.getDate("HanSuDung"),
                        rs.getNString("NuocSanXuat"),
                        TrangThaiSanPham.valueOf(rs.getNString("TrangThai")),
                        rs.getNString("GhiChu"),
                        rs.getNString("MoTa"),
                        rs.getNString("DangBaoChe"),
                        rs.getDouble("Thue"),
                        rs.getNString("ThanhPhan"),
                        DonViTinh.valueOf(rs.getNString("DonViTinh")),
                        null,  // Assuming NhaCungCap and LoaiSanPham are retrieved elsewhere
                        null,  // Assuming NhaCungCap and LoaiSanPham are retrieved elsewhere
                        rs.getBigDecimal("GiaBan"),
                        rs.getNString("MaVach"),
                        rs.getNString("YeuCauKeDon"),
                        null
                );
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return sanPhamYTe;
    }

    // Update a SanPhamYTe
    public static boolean updateSanPhamYTe(SanPhamYTe sanPhamYTe) {
        String sql = "UPDATE SanPhamYTe SET "
                + "TenSanPham = ?, NgaySanXuat = ?, HanSuDung = ?, NuocSanXuat = ?, TrangThai = ?, GhiChu = ?, MoTa = ?, DangBaoChe = ?, Thue = ?, ThanhPhan = ?, DonViTinh = ?, MaNhaCungCap = ?, MaLoai = ?, GiaBan = ?, MaVach = ?, YeuCauKeDon = ? "
                + "WHERE MaSanPham = ?";
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setNString(1, sanPhamYTe.getTenSanPham());
            ps.setDate(2, sanPhamYTe.getNgaySanXuat());
            ps.setDate(3, sanPhamYTe.getHanSuDung());
            ps.setNString(4, sanPhamYTe.getNuocSanXuat());
            ps.setNString(5, sanPhamYTe.getTrangThaiSanPham().toString());
            ps.setNString(6, sanPhamYTe.getGhiChu());
            ps.setNString(7, sanPhamYTe.getMoTa());
            ps.setNString(8, sanPhamYTe.getDangBaoChe());
            ps.setDouble(9, sanPhamYTe.getThue());
            ps.setNString(10, sanPhamYTe.getThanhPhan());
            ps.setNString(11, sanPhamYTe.getDonViTinh().toString());
            ps.setNString(12, sanPhamYTe.getNhaCungCap().getMaNhaCungCap());
            ps.setNString(13, sanPhamYTe.getLoaiSanPham().getMaLoai());
            ps.setBigDecimal(14, sanPhamYTe.getGiaBan());
            ps.setNString(15, sanPhamYTe.getMaVach());
            ps.setNString(16, sanPhamYTe.getYeuCauKeDon());
            ps.setNString(17, sanPhamYTe.getMaSanPham());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a SanPhamYTe by its MaSanPham
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
