package dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import customDataType.DonViTinh;
import customDataType.TrangThaiSanPham;
import entity.LoaiSanPham;
import entity.NhaCungCap;
import entity.SanPhamYTe;

public class SanPhamYTeDAO {
	public static ArrayList<SanPhamYTe> sanPhamYTe;
	
	public static ArrayList<SanPhamYTe> layDanhSachTatCaSanPhamYTe(){
		return sanPhamYTe;
	}
	
	public ArrayList<SanPhamYTe> timTheoMaVach(String ma) {
	    ArrayList<SanPhamYTe> SanPhamCanTim = new ArrayList<SanPhamYTe>();
	    try {
	        PreparedStatement ps = ConnectDB.getConnection().prepareStatement("SELECT * FROM SanPhamYTe WHERE MaSanPham = ?");
	        ps.setString(1, ma);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            // Lấy các thông tin cần thiết từ bảng KhachHang
	            String maSP = rs.getString("MaSanPham");
	            String ten = rs.getString("TenSanPham");
	            Date nsx = rs.getDate("NgaySanXuat");
	            Date hsd = rs.getDate("HanSuDung");
	            String nuocSX = rs.getString("NuocSanXuat");
	            TrangThaiSanPham trangThaiSanPham = TrangThaiSanPham.fromString(rs.getString("TrangThai"));
	            String ghiChu = rs.getString("GhiChu");
	            String moTa = rs.getString("MoTa");
	            String dangBaoChe = rs.getString("DangBaoChe");
	            float thue = rs.getFloat("Thue");
	            String thanhPhan = rs.getString("ThanhPhan");
	            DonViTinh donViTinh = DonViTinh.fromString(rs.getString("DonViTinh"));
	            NhaCungCap ncc = new NhaCungCap(rs.getString("MaNhaCungCap"));
	            LoaiSanPham loai = new LoaiSanPham(rs.getString("MaLoai"));
	            BigDecimal giaBan = rs.getBigDecimal("GiaBan");
	            
	            SanPhamYTe sp = new SanPhamYTe(maSP, ten, nsx, hsd, nuocSX, trangThaiSanPham, ghiChu, moTa, thue, thanhPhan, giaBan, ncc, donViTinh, loai, dangBaoChe);
	            SanPhamCanTim.add(sp);
	            
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return SanPhamCanTim;
	}
}
