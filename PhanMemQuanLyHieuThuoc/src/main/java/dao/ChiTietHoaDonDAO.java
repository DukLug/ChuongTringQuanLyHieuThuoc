package dao;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import customDataType.DonViTinh;
import entity.ChiTietDonDoiTra;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoHang;
import entity.SanPhamYTe;

public class ChiTietHoaDonDAO {
	
	
	
	
	public ArrayList<ChiTietHoaDon> timChiTietHoaDonTheoMaHoaDon(String maHoaDon) {
	    ArrayList<ChiTietHoaDon> chiTietHoaDons = new ArrayList<>();
	    String sql = "select sp.MaSanPham, sp.TenSanPham, sp.GiaVonDonViTinh1, sp.GiaBanDonViTinh2, sp.GiaBanDonViTinh3, " +
	                 "sp.DonViTinh1, sp.DonViTinh2, sp.DonViTinh3, cthd.SoLuongDonViTinh1, cthd.SoLuongDonViTinh2, cthd.SoLuongDonViTinh3, " +
	                 "cthd.TongTien from ChiTietHoaDon cthd join SanPhamYTe sp on cthd.MaSanPham = sp.MaSanPham where cthd.MaHoaDon =?";

	    try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
	        ps.setString(1, maHoaDon); 
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {

	            String maSanPham = rs.getString("MaSanPham");
	            String tenSanPham = rs.getString("TenSanPham");
	            BigDecimal giaVonDonViTinh1 = rs.getBigDecimal("GiaVonDonViTinh1");
	            BigDecimal giaBanDonViTinh2 = rs.getBigDecimal("GiaBanDonViTinh2");
	            BigDecimal giaBanDonViTinh3 = rs.getBigDecimal("GiaBanDonViTinh3");

	            DonViTinh donViTinh1 = DonViTinh.fromString(rs.getString("DonViTinh1"));
	            DonViTinh donViTinh2 = DonViTinh.fromString(rs.getString("DonViTinh2"));
	            DonViTinh donViTinh3 = DonViTinh.fromString(rs.getString("DonViTinh3"));

	            int soLuongDonViTinh1 = rs.getInt("SoLuongDonViTinh1");
	            int soLuongDonViTinh2 = rs.getInt("SoLuongDonViTinh2");
	            int soLuongDonViTinh3 = rs.getInt("SoLuongDonViTinh3");

	            BigDecimal tongTien = rs.getBigDecimal("TongTien");

	            // Khởi tạo đối tượng SanPhamYTe
	            SanPhamYTe sanPhamYTe = new SanPhamYTe(maSanPham, tenSanPham, giaVonDonViTinh1, giaBanDonViTinh2, giaBanDonViTinh3, 
	                                                    donViTinh1, donViTinh2, donViTinh3);

	            // Khởi tạo đối tượng ChiTietHoaDon với các thông tin cho từng đơn vị tính
	            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(sanPhamYTe, soLuongDonViTinh1, soLuongDonViTinh2, soLuongDonViTinh3, tongTien);

	            chiTietHoaDons.add(chiTietHoaDon);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return chiTietHoaDons;
	}
	
	public static ArrayList<ChiTietHoaDon> layDSCTHDTheoMa(String maHoaDon) {
	    ArrayList<ChiTietHoaDon> dsCTHDTheoMa = new ArrayList<>();
	    PreparedStatement stmt = null;
	    try {
	        ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();
	        String sql = "SELECT * FROM ChiTietHoaDon where MaHoaDon = ?";
	        stmt = con.prepareStatement(sql);
	        stmt.setString(1, maHoaDon);
	        
	        // Thực thi câu lệnh SQL và trả về ResultSet
	        ResultSet rs = stmt.executeQuery();
	        
	        // Duyệt qua các kết quả trả về
	        while (rs.next()) {
//	        	String maChiTietHoaDon = rs.getNString("MaChiTietHoaDon");
               
                int soLuongDonViTinh1 = rs.getInt("SoLuongDonViTinh1");
                int soLuongDonViTinh2 = rs.getInt("SoLuongDonViTinh2");
                int soLuongDonViTinh3 = rs.getInt("SoLuongDonViTinh3");
                BigDecimal tongTien = rs.getBigDecimal("TongTien"); 
                String maSanPham = rs.getNString("MaSanPham");
                String malo = rs.getNString("MaLo");
                String malo2 = rs.getNString("MaLoHangThayThe");
                
                HoaDon hoaDon = new HoaDon(maHoaDon);
                SanPhamYTe spyt = new SanPhamYTe(maSanPham);
                LoHang lh1 = new LoHang(malo);
                LoHang lh2 = new LoHang(malo2);

                
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(maHoaDon, soLuongDonViTinh1, soLuongDonViTinh2, soLuongDonViTinh3, tongTien, hoaDon, spyt, lh1, lh2);
                

				 dsCTHDTheoMa.add(chiTietHoaDon);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return dsCTHDTheoMa;
	}
	
	// thêm chi tiết đơn đổi trả
		public boolean themChiTietHoadon(ChiTietHoaDon chiTietHoaDon) {
		    String sql = "INSERT INTO ChiTietHoaDon (MaChiTietHoaDon, SoLuongDonViTinh1, SoLuongDonViTinh2, SoLuongDonViTinh3, TongTien, MaHoaDon, MaSanPham, MaLo, MaLoHangThayThe) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		    
		    try (PreparedStatement ps = ConnectDB.getConnection().prepareStatement(sql)) {
		        ps.setString(1, chiTietHoaDon.getMaChiTietHoaDon()); 
		        ps.setInt(2, chiTietHoaDon.getSoLuongDonViTinh1());           
		        ps.setInt(3, chiTietHoaDon.getSoLuongDonViTinh2());      
		        ps.setInt(4, chiTietHoaDon.getSoLuongDonViTinh3());    
		        ps.setBigDecimal(5, chiTietHoaDon.getTongTien());  
		        ps.setString(6, chiTietHoaDon.getHoaDon().getMaHoaDon());  
		        ps.setString(7, chiTietHoaDon.getSanPhamYTe().getMaSanPham());  
		        ps.setString(8, chiTietHoaDon.getLoHang().getMaLo());            
		        ps.setString(9, chiTietHoaDon.getLoHangThayThe() != null ? chiTietHoaDon.getLoHangThayThe().getMaLo() : null);     

		        int rowsAffected = ps.executeUpdate(); 
		        return rowsAffected > 0;              
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false; 
		    }
		}
		
		// lấy danh sách các cgi tiet doi tra
		public ArrayList<ChiTietHoaDon> layDSCTHD() {
		    ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<>();
		    try {
		        ConnectDB.getInstance();
		        Connection con = ConnectDB.getConnection();
		        String sql = "SELECT * FROM ChiTietHoaDon";
		        Statement statement = con.createStatement();
		        
		        // Thực thi câu lệnh SQL và trả về ResultSet
		        ResultSet rs = statement.executeQuery(sql);
		        
		        // Duyệt qua các kết quả trả về
		        while (rs.next()) {
		            String maCTHD = rs.getString(1);
		            int soLuong = rs.getInt(2);
		            BigDecimal tongTien = rs.getBigDecimal(3);
		            String maHD = rs.getString(4);
		            HoaDon hoaDon = new HoaDon(maHD);
		            
		            String maSP = rs.getString(5);
		            SanPhamYTe sanPham = new SanPhamYTe(maSP);
		            
		            String maLo = rs.getString(6);
					LoHang LoHang = new LoHang(maLo);
					
					String maLoThayThe = rs.getString(7);
					LoHang LohayThe = new LoHang(maLoThayThe);

//					 = new ChiTietHoaDon(maCTHD, soLuong, tongTien, hoaDon, sanPham, LoHang, LohayThe);
					 ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(maCTHD, soLuong, soLuong, soLuong, tongTien, hoaDon, sanPham, LoHang, LohayThe);

		            dsCTHD.add(chiTietHoaDon);
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    
		    return dsCTHD;
		}
		
		
		

}
	

