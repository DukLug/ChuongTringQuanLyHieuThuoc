package dao;


import java.math.BigDecimal;

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

                LoaiSanPham loai = new LoaiSanPham(rs.getNString("MaLoai"));
                NhaCungCap ncc = new NhaCungCap(rs.getNString("MaNhaCungCap")); 
                
                sanPhamYTe = new SanPhamYTe(
                    rs.getNString("MaSanPham"),
                    rs.getNString("TenSanPham"),
                    
                    rs.getNString("NuocSanXuat"),
                    TrangThaiSanPham.valueOf(rs.getString("TrangThai")),
                    rs.getNString("GhiChu"),
                    rs.getNString("MoTa"),
                    rs.getNString("DangBaoChe"),
                    rs.getDouble("Thue"),
                    rs.getNString("ThanhPhan"),
                    
                    DonViTinh.valueOf(rs.getNString("DonViTinh1")),
                    rs.getNString("DonViTinh2") != null ? DonViTinh.valueOf(rs.getNString("DonViTinh2")) : null,
                    rs.getNString("DonViTinh3") != null ? DonViTinh.valueOf(rs.getNString("DonViTinh3")) : null,

                    
                    rs.getBigDecimal("GiaVonDonViTinh1"),
                    rs.getBigDecimal("GiaBanDonViTinh2"),
                    rs.getBigDecimal("GiaBanDonViTinh3"),
                    
                    rs.getInt("GiaTriQuyDoi2"),
                    rs.getInt("GiaTriQuyDoi3"),
                    
                    ncc,
                    loai,

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

    public static boolean updateSanPhamYTe(SanPhamYTe sanPhamYTe) {
        String sql = "UPDATE SanPhamYTe SET " +
            "TenSanPham = ?, NuocSanXuat = ?, TrangThai = ?, GhiChu = ?, MoTa = ?, " +
            "DangBaoChe = ?, Thue = ?, ThanhPhan = ?, DonViTinh1 = ?, DonViTinh2 = ?, DonViTinh3 = ?, " +
            "GiaVonDonViTinh1 = ?, GiaBanDonViTinh2 = ?, GiaBanDonViTinh3 = ?, " +
            "GiaTriQuyDoi2 = ?, GiaTriQuyDoi3 = ?, MaNhaCungCap = ?, MaLoai = ?, " +
            "MaVach = ?, YeuCauKeDon = ? WHERE MaSanPham = ?";
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            

            ps.setNString(1, sanPhamYTe.getTenSanPham());
            ps.setNString(2, sanPhamYTe.getNuocSanXuat());
            ps.setNString(3, sanPhamYTe.getTrangThaiSanPham().toString());
            ps.setNString(4, sanPhamYTe.getGhiChu());
            ps.setNString(5, sanPhamYTe.getMoTa());
            ps.setNString(6, sanPhamYTe.getDangBaoChe());
            ps.setDouble(7, sanPhamYTe.getThue());
            ps.setNString(8, sanPhamYTe.getThanhPhan());
            
            ps.setNString(9, sanPhamYTe.getDonViTinh1() != null ? sanPhamYTe.getDonViTinh1().toString() : null);
            ps.setNString(10, sanPhamYTe.getDonViTinh2() != null ? sanPhamYTe.getDonViTinh2().toString() : null);
            ps.setNString(11, sanPhamYTe.getDonViTinh3() != null ? sanPhamYTe.getDonViTinh3().toString() : null);

            
            ps.setBigDecimal(12, sanPhamYTe.getGiaBanDonViTinh1());
            ps.setBigDecimal(13, sanPhamYTe.getGiaBanDonViTinh2());
            ps.setBigDecimal(14, sanPhamYTe.getGiaBanDonViTinh3());
            
            ps.setInt(15, sanPhamYTe.getGiaTriQuyDoi2());
            ps.setInt(16, sanPhamYTe.getGiaTriQuyDoi3());
            

            ps.setNString(17, sanPhamYTe.getNhaCungCap().getMaNhaCungCap());
            ps.setNString(18, sanPhamYTe.getLoaiSanPham().getMaLoai());
            ps.setNString(19, sanPhamYTe.getMaVach());
            ps.setNString(20, sanPhamYTe.getYeuCauKeDon());
            
            ps.setNString(21, sanPhamYTe.getMaSanPham());

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

    
 // tìm sản phẩm theo mã 
 	public ArrayList<SanPhamYTe> timSanPhamTheoMaTrongDDT(String maSP){
 		ArrayList<SanPhamYTe> spYTe= new ArrayList<SanPhamYTe>();
 		 try {
 		        PreparedStatement ps = ConnectDB.getConnection().prepareStatement("Select MaSanPham, TenSanPham, GiaVonDonViTinh1 , DonViTinh1  from SanPhamYTe where MaSanPham = ?");
 		        ps.setString(1,maSP);
 		        ResultSet rs = ps.executeQuery();
 		        while (rs.next()) {
 		        	  String maSanPham = rs.getString("MaSanPham");
 		              String tenSanPham = rs.getString("TenSanPham");
 		              BigDecimal giaVonDonViTinh1 = rs.getBigDecimal("GiaVonDonViTinh1");
 		              DonViTinh donViTinh1 = DonViTinh.fromString(rs.getString("DonViTinh1"));
 		        
 		              SanPhamYTe sanPham = new SanPhamYTe(maSanPham, tenSanPham, giaVonDonViTinh1, donViTinh1);
 		              spYTe.add(sanPham);
 		        }
 		 }catch (SQLException e) {
 		        e.printStackTrace();}
 		 return spYTe;
 	}
 	
 	    
 	    // Hàm lấy giá bán theo đơn vị tính
 	    public BigDecimal getGiaBanTheoDonViTinh(String maSanPham, DonViTinh donViTinh) {
 	        String sql = "SELECT ";
 	        
 	        // Chọn giá bán tương ứng với đơn vị tính
 	        if (donViTinh == DonViTinh.Vien) {
 	            sql += "giaVonDonViTinh1";
 	        } else if (donViTinh == DonViTinh.Vi) {
 	            sql += "giaBanDonViTinh2";
 	        } else if (donViTinh == DonViTinh.Hop) {
 	            sql += "giaBanDonViTinh3";
 	        } else {
 	            throw new IllegalArgumentException("Đơn vị tính không hợp lệ");
 	        }
 	        
 	        sql += " FROM SanPhamYTe WHERE maSanPham = ?";
 	        
 	        // Thực thi truy vấn
 	        try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
 	            
 	            // Gán giá trị cho tham số maSanPham
 	            ps.setString(1, maSanPham);
 	            
 	            // Thực thi truy vấn và lấy kết quả
 	            ResultSet rs = ps.executeQuery();
 	            if (rs.next()) {
 	                // Trả về giá bán tương ứng với đơn vị tính
 	                return rs.getBigDecimal(1);
 	            }
 	        } catch (SQLException e) {
 	            e.printStackTrace();
 	        }
 	        return null; // Trả về null nếu không tìm thấy sản phẩm
 	    }


}