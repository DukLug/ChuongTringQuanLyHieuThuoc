package controller;

import java.util.ArrayList;
import dao.NhanVienDAO;
import entity.NhanVien;

public class NhanVienCTR {
	 private NhanVienDAO nvDAO;

	    // Constructor
	    public NhanVienCTR() {
	        nvDAO = new NhanVienDAO(); // Khởi tạo DAO để làm việc với cơ sở dữ liệu
	    }

	    /**
	     * Hàm tìm kiếm nhân viên theo mã nhân viên.
	     * @param maNV Mã nhân viên cần tìm kiếm.
	     * @return Mảng hai chiều chứa dữ liệu của nhân viên (Object[][]).
	     */
	    public ArrayList<NhanVien> timKiemTheoMaNV(String maNV) {
	    	 NhanVienDAO ds = new NhanVienDAO();
			 ArrayList<NhanVien> list = ds.timNhanVienTheoMa(maNV);
			 return list;
	       
	    }
	    
	    public ArrayList<NhanVien> timKiemTheoSDT(String sdt) {
	    	 NhanVienDAO ds = new NhanVienDAO();
			 ArrayList<NhanVien> list = ds.timNhanVienTheoSDT(sdt);
			 return list;
	       
	    }
	    
	    public ArrayList<NhanVien> timKiemTheoHoTen(String hoTen) {
	    	 NhanVienDAO ds = new NhanVienDAO();
			 ArrayList<NhanVien> list = ds.timNhanVienTheoTen(hoTen);
			 return list;
	       
	    }
	  
	    
	   public ArrayList<NhanVien> layDanhSachTatCaNhanVien(){
		   NhanVienDAO ds = new NhanVienDAO();
		   ArrayList<NhanVien> list = ds.layDanhSachTatCaNhanVien();
		   return list;		
	   }
	   
	   public boolean themNhanVien(NhanVien nv) {
	        return nvDAO.themNhanVien(nv); // Gọi phương thức trong DAO để thêm nhân viên
	    }
}
