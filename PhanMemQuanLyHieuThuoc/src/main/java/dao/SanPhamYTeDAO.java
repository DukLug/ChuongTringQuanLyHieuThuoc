package dao;

import java.math.BigDecimal;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import customDataType.DonViTinh;

import entity.SanPhamYTe;

public class SanPhamYTeDAO {
	public static ArrayList<SanPhamYTe> sanPhamYTe;
	
	public static ArrayList<SanPhamYTe> layDanhSachTatCaSanPhamYTe(){
		return sanPhamYTe;
	}
	
	// tìm sản phẩm theo mã 
	public ArrayList<SanPhamYTe> timSanPhamTheoMaTrongDDT(String maSP){
		ArrayList<SanPhamYTe> spYTe= new ArrayList<SanPhamYTe>();
		 try {
		        PreparedStatement ps = ConnectDB.getConnection().prepareStatement("Select MaSanPham, TenSanPham, GiaBan , DonViTinh from SanPhamYTe where MaSanPham = ?");
		        ps.setString(1,maSP);
		        ResultSet rs = ps.executeQuery();
		        while (rs.next()) {
		        	  String maSanPham = rs.getString("MaSanPham");
		              String tenSanPham = rs.getString("TenSanPham");
		              BigDecimal giaBan = rs.getBigDecimal("GiaBan");
		              DonViTinh donViTinh = DonViTinh.fromString(rs.getString("DonViTinh"));
		        
		              SanPhamYTe sanPham = new SanPhamYTe(maSanPham, tenSanPham, giaBan, donViTinh);
		              spYTe.add(sanPham);
		        }
		 }catch (SQLException e) {
		        e.printStackTrace();}
		 return spYTe;
	}
	

}
