package controller;


import java.sql.Date;
import java.util.ArrayList;

import dao.KhuyenMaiDAO;
import entity.KhuyenMai;


public class KhuyenMaiCTR {
	private KhuyenMaiDAO km_dao;
	
	 // Constructor
    public  KhuyenMaiCTR() {
		km_dao = new KhuyenMaiDAO();
	}
    
    // lấy danh sách khuyến mãi
    public ArrayList<KhuyenMai> layDanhSachTatCaKhuyenMai(){
    	return km_dao.layDanhSachTatCaKhuyenMai();
    	
    }
    
    // tìm khuyến mãi theo mã khuyến mãi
    
    public ArrayList<KhuyenMai> timKhuyenMaiTheoMa(String makm){
    	return km_dao.timKhuyenMaiTheoMa(makm);
    }
    
    // tìm theo ngày bắt đầu
    public ArrayList<KhuyenMai> timKhuyenMaiTheoNgayKhuyenMai(Date ngayKM){
    	return km_dao.timKhuyenMaiTheoNgayBatDau(ngayKM);
    }
    
    // tìm theo ngày kết thúc
    public ArrayList<KhuyenMai> timKhuyenMaiTheoNgayKetThuc(Date ngayKT){
    	return km_dao.timKhuyenMaiTheoNgayKetThuc(ngayKT);
    }
    
    // tìm khuyến mãi heo điều kiện
    
    public ArrayList<KhuyenMai> timKhuyenMaiTheoDieuKien(String DK){
    	return km_dao.timKhuyenMaiTheoDieuKien(DK);
    }
    
  // tìm khuyến mãi theo mã nhân viên
    
    public ArrayList<KhuyenMai> timKhuyenMaiTheoMaNhanvien(String maNV){
    	return km_dao.timKhuyenMaiTheoNhanVien(maNV);
    }
    
   // thêm khuyến mãi
    public boolean themKhuyenMai(KhuyenMai newKM) {
    	return km_dao.themKhuyenMai(newKM);
    }
    
    public boolean capNhatKhuyenMai(KhuyenMai newkm) {
    	return km_dao.capNhatKuyenMai(newkm);
    }
    

}
