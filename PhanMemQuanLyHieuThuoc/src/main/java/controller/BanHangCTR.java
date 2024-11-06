package controller;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;

import component.CustomItem;
import component.CustomItemList;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.KhuyenMaiDAO;
import dao.SanPhamYTeDAO;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.SanPhamYTe;
import testEntity.Thuoc;
import userInterface.BanHangUI.BanHangRow;

public class BanHangCTR {
	
	private KhachHangDAO kh_dao = new KhachHangDAO();
	private SanPhamYTeDAO sp_dao = new SanPhamYTeDAO();
	private KhuyenMaiDAO km_dao = new KhuyenMaiDAO();
	private HoaDonDAO hd_dao = new HoaDonDAO();
	private ArrayList<KhachHang> dsKH;
	private ArrayList<SanPhamYTe> dsSP;
	private ArrayList<KhuyenMai> dsKM;

	public BanHangCTR() {
		super();
	}
	
	public KhachHang timKHTheoSDT(String SDT) {
		dsKH = kh_dao.timTheoSDT(SDT);

		for (KhachHang kh : dsKH) 
			if (kh.getSdt().equals(SDT))
				return kh;
		
		return null;

	}
	
	public SanPhamYTe timSPTheoMaVach(String ma) {
		dsSP = sp_dao.timTheoMaVach(ma);
		
		for (SanPhamYTe sp : dsSP) 
			if (sp.getMaSanPham().equalsIgnoreCase(ma))
				return sp;
		
		return null;
	}
	
	public BigDecimal tinhTongTienTungSP(BigDecimal giaBan, String sl) {
		BigDecimal soLuong = new BigDecimal(sl);
		return giaBan.multiply(soLuong);
	}
	
	public BigDecimal tinhTongTienHoaDon(CustomItemList list) {
		BigDecimal tongTien = BigDecimal.ZERO;
		
		for (CustomItem item : list.getItemList()) {
			if (item instanceof BanHangRow) {
				BanHangRow row = (BanHangRow) item; // Chuyển đổi item thành BanHangRow
	            Thuoc sp = row.getSanPhamYTe();
	            
	            BigDecimal soLuong = new BigDecimal(sp.soLuong);
	            BigDecimal tongTienSP = soLuong.multiply(sp.giaBan);
	            tongTien = tongTien.add(tongTienSP);
			}
		}
		
		return tongTien;
	}
	
	public ArrayList<KhuyenMai> timMaKhuyenMai(String tongTienHD) {
		dsKM = km_dao.timKhuyenMaiTheoDieuKien1(tongTienHD);
		
		return dsKM;
	}
	
	public BigDecimal tinhKhuyenMai(BigDecimal tongTienHD) {
	    double chietKhau = 0;

	    dsKM = km_dao.timKhuyenMaiTheoDieuKien1(tongTienHD.toString());
	    for (KhuyenMai km : dsKM) {
	        if (km.getChietKhau() > chietKhau) {
	            chietKhau = km.getChietKhau();
	        }
	    }
	    
	    BigDecimal tienKhuyenMai = tongTienHD.multiply(BigDecimal.valueOf(chietKhau));

	    return tienKhuyenMai;
	}


	public BigDecimal tinhTienKhachPhaiTra(BigDecimal tongTienHD, BigDecimal tienKhuyenMai, BigDecimal tienChietKhau) {
	    return tongTienHD.subtract(tienKhuyenMai).subtract(tienChietKhau);
	}
	
	public ArrayList<Integer> tinhTienChietKhau(String sdt) {
	    ArrayList<Integer> danhSachChietKhau = new ArrayList<>();
	    
	    KhachHang kh = timKHTheoSDT(sdt);
	    
	    if (kh.getDiemTichLuy() > 1000) {
	        danhSachChietKhau.add(10000);
	        danhSachChietKhau.add(0);
	    } else if (kh.getDiemTichLuy() < 1000 && kh.getDiemTichLuy() > 0) {
	        danhSachChietKhau.add(kh.getDiemTichLuy()*10);
	        danhSachChietKhau.add(0);
	    } else {
	        danhSachChietKhau.add(0); 
	    }
	    
	    return danhSachChietKhau;
	}
	
}
