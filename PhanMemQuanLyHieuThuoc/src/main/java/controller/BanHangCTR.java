package controller;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.DirectoryNotEmptyException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import component.CustomItem;
import component.CustomItemList;
import dao.ChiTietDonDoiTraDAO;
import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.KhuyenMaiDAO;
import dao.LoHangDAO;
import dao.SanPhamYTeDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.LoHang;
import entity.SanPhamYTe;
import testEntity.Thuoc;
import userInterface.BanHangUI.BanHangRow;

public class BanHangCTR {
	
	private KhachHangDAO kh_dao = new KhachHangDAO();
	private SanPhamYTeDAO sp_dao = new SanPhamYTeDAO();
	private KhuyenMaiDAO km_dao = new KhuyenMaiDAO();
	private HoaDonDAO hd_dao = new HoaDonDAO();
	private LoHangDAO lh_dao = new LoHangDAO();
	private ChiTietHoaDonDAO cthd_dao = new ChiTietHoaDonDAO();
	private ArrayList<KhachHang> dsKH;
	private ArrayList<SanPhamYTe> dsSP;
	private ArrayList<KhuyenMai> dsKM;
	
	// lấy ngày hiện tại
	private LocalDate now = LocalDate.now();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
	private String ngayHienTai = now.format(formatter);

	public BanHangCTR() {
		super();
	}
	
	public boolean themKH(KhachHang kh) {
		return kh_dao.them2(kh);
	}
	
	public boolean capNhatKH(KhachHang kh) {
		return kh_dao.capNhat1(kh);
	}
	
	public boolean themHD(HoaDon hd) {
		return hd_dao.them(hd);
	}
	
	public boolean capNhatSoLuongSP(String maSanPham, int soLuongDVT1, int soLuongDVT2, int soLuongDVT3) {
		return lh_dao.capNhatSoLuongSP(maSanPham, soLuongDVT1, soLuongDVT2, soLuongDVT3);
	}
	
	public void capNhatDTL(KhachHang kh, int diemDaDung, BigDecimal tongTien) {
		int DTL = 0;
		
		if (diemDaDung == 0) {
			DTL = tongTien.multiply(new BigDecimal("0.001")).intValue();
		}
		else
			DTL = -diemDaDung;
		
		kh_dao.capNhatDTL(kh, DTL);
	}
	
	public LoHang timLoHang(String msSP) {
		return lh_dao.timLoSP(msSP);
	}
	
	
	public KhachHang timKHTheoSDT(String SDT) {
		dsKH = kh_dao.timTheoSDT(SDT);

		for (KhachHang kh : dsKH) 
			if (kh.getSdt().equals(SDT))
				return kh;
		
		return null;
	}
	
	public KhachHang timKHTheoMaKH(String ma) {
		dsKH = kh_dao.timTheoMa(ma);

		for (KhachHang kh : dsKH) 
			if (kh.getMaKhachHang().equals(ma))
				return kh;
		
		return null;
	}
	
	public BigDecimal tinhTongTienTungSP(BigDecimal giaBan1, BigDecimal giaBan2, BigDecimal giaBan3, String sl1, String sl2, String sl3) {
		BigDecimal soLuong1 = new BigDecimal(sl1);
		BigDecimal soLuong2 = new BigDecimal(sl2);
		BigDecimal soLuong3 = new BigDecimal(sl3);
		
		BigDecimal tongGia1 = giaBan1.multiply(soLuong1);
		BigDecimal tongGia2 = giaBan2.multiply(soLuong2);
		BigDecimal tongGia3 = giaBan3.multiply(soLuong3);
		
		return (tongGia1.add(tongGia2)).add(tongGia3);
	}
	
	public BigDecimal tinhTongTienHoaDon(CustomItemList list) {
		BigDecimal tongTien = BigDecimal.ZERO;
		
		for (CustomItem item : list.getItemList()) {
			if (item instanceof BanHangRow) {
				BanHangRow row = (BanHangRow) item; // Chuyển đổi item thành BanHangRow
	            Thuoc sp = row.getSanPhamYTe();
	          
//	            BigDecimal soLuong = new BigDecimal(sp.soLuong);
//	            BigDecimal tongTienSP = soLuong.multiply(sp.giaBan);
	            BigDecimal tongTienSP = tinhTongTienTungSP(sp.giaBanDonViTinh1, sp.giaBanDonViTinh2, sp.giaBanDonViTinh3, String.valueOf(sp.soLuongDVT1), String.valueOf(sp.soLuongDVT2), String.valueOf(sp.soLuongDVT3));
	            tongTien = tongTien.add(tongTienSP);
			}
		}
		
		return tongTien;
	}
	
	public ArrayList<KhuyenMai> timMaKhuyenMai(String tongTienHD) {
		dsKM = km_dao.timKhuyenMaiTheoDieuKien1(tongTienHD);
		
		return dsKM;
	}
	
	public BigDecimal tinhKhuyenMai(BigDecimal tongTienHD, float phanTram) {
		BigDecimal tienKhuyenMai = BigDecimal.ZERO;
		
		if (phanTram == 0 ) {
			 double chietKhau = 0;

			    dsKM = km_dao.timKhuyenMaiTheoDieuKien1(tongTienHD.toString());
			    for (KhuyenMai km : dsKM) {
			        if (km.getChietKhau() > chietKhau) {
			            chietKhau = km.getChietKhau();
			        }
			    }
			    
			    tienKhuyenMai = tongTienHD.multiply(BigDecimal.valueOf(chietKhau));
		}
		else
			tienKhuyenMai = tongTienHD.multiply(BigDecimal.valueOf(phanTram));
	    
	    return tienKhuyenMai.setScale(0, RoundingMode.HALF_UP); // làm trong thành 0 số thập phân
	}
	
	 public void sapXepChietKhau(ArrayList<KhuyenMai> dsKM) {
	        // Sắp xếp ArrayList theo ChietKhau giảm dần
	        Collections.sort(dsKM, new Comparator<KhuyenMai>() {
	            @Override
	            public int compare(KhuyenMai km1, KhuyenMai km2) {
	                // So sánh ChietKhau giữa hai khuyến mãi, theo thứ tự giảm dần
	            	return Double.compare(km2.getChietKhau(), km1.getChietKhau()); // ChietKhau giảm dần
	            }
	        });
	    }
	
	public Object[][] timDSKhuyenMai(BigDecimal tongTienHD) {
		Object[][] data = new Object[0][0];
		BigDecimal tienKM;
		
	    dsKM = km_dao.timKhuyenMaiTheoDieuKien1(tongTienHD.toString());
	    
	    if (dsKM.size() == 0)
	    	return data;
	    else {       
	    	data = new Object[dsKM.size()][4];
	    	sapXepChietKhau(dsKM);
	    	
	  	    for (int i = 0; i < dsKM.size(); i++) {
	  	    	KhuyenMai km = dsKM.get(i);
	  	    	tienKM = tongTienHD.multiply(BigDecimal.valueOf(km.getChietKhau()));
	  	    	data[i][0] = km.getMaKhuyenMai(); 
	  	    	data[i][1] = km.getDieuKien();
	  	    	data[i][2] = km.getChietKhau();
	  	    	data[i][3] = formatDecimal(tienKM);
	  	    }
	    }

	    return data;
	}


	public BigDecimal tinhTienKhachPhaiTra(BigDecimal tongTienHD, BigDecimal tienKhuyenMai, BigDecimal tienChietKhau) {
	    return tongTienHD.subtract(tienKhuyenMai).subtract(tienChietKhau);
	}
	
	public ArrayList<Integer> tinhTienChietKhau(String sdt) {
	    ArrayList<Integer> danhSachChietKhau = new ArrayList<>();
	    
	    KhachHang kh = timKHTheoSDT(sdt);
	
	    if (kh.getMaKhachHang().equalsIgnoreCase("KH000000") || kh == null) {
	    	danhSachChietKhau.add(0);
	    	return danhSachChietKhau;
	    }
	    	
	    
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
	
	public int tinhSoDTLDaDung(BigDecimal chietKhauDaDung) {
	    // Chia chietKhauDaDung cho 10 và làm tròn kết quả
	    BigDecimal result = chietKhauDaDung.divide(BigDecimal.TEN, RoundingMode.FLOOR);
	    
	    // Chuyển kết quả từ BigDecimal sang int
	    return result.intValue();
	}

	
	public String taoMaHoaDon() {
	    String stt = "0000";
	    int soHienTai = Integer.parseInt(stt); 

	    Object[][] dsHD = hd_dao.layDanhSachHoaDonBanHangTheoNgay();
	    soHienTai = dsHD.length + 1;

//	    LocalDate ngayHienTai = LocalDate.now();
//	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
	    
	    String maHD = String.format("HD" + ngayHienTai + "%04d", soHienTai);

	    return maHD;
	}
	
	public String taoMaChiTietHoaDon(String maHD) {
//	    String stt = "00";
//	    int soHienTai1 = Integer.parseInt(stt); 
		
		int soHienTai1 = -1;
	    
		String chuoi = maHD.substring(2);
		
		ArrayList<ChiTietHoaDon> dsCTHD = layDSCTHDTheoMaHD(cthd_dao.layDSCTHD(), chuoi);
	    soHienTai1 = dsCTHD.size() + 1;
//	    
//	    ArrayList<ChiTietHoaDon> dsCTHD = layDSCTHDTheoNgayHienTai(cthd_dao.layDSCTHD());
//	    soHienTai1 = dsCTHD.size() + 1;
//	    
	    String maCTHD = String.format("CTHD" + chuoi + "%02d", soHienTai1);

	    return maCTHD;
	}
	
//	private ArrayList<ChiTietHoaDon> layDSCTHDTheoNgayHienTai(ArrayList<ChiTietHoaDon> danhSach) {
//		ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
//		    
//		for (ChiTietHoaDon cthd : danhSach)
//			if (cthd.getMaChiTietHoaDon().contains(ngayHienTai))
//				dsCTHD.add(cthd);
//		
//		return dsCTHD;
//		
//	}
	
	private ArrayList<ChiTietHoaDon> layDSCTHDTheoMaHD(ArrayList<ChiTietHoaDon> danhSach, String chuoi) {
		ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
		    
		for (ChiTietHoaDon cthd : danhSach)
			if (cthd.getMaChiTietHoaDon().contains(chuoi))
				dsCTHD.add(cthd);
		
		return dsCTHD;
		
	}
    
    public boolean themChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
    	return cthd_dao.themChiTietHoadon(chiTietHoaDon);
    }

	public String timMaLoTheoMaSP(String maSP) {
		String maLo = lh_dao.maLoTheoSanPham(maSP);
		return maLo;
	}
	
	public String formatDecimal(BigDecimal number) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator('.'); // Dấu phân cách hàng nghìn
		symbols.setDecimalSeparator(',');   // Dấu thập phân

		// Sử dụng mẫu định dạng với 'đ' ở cuối
		DecimalFormat decimalFormat = new DecimalFormat("#,###.### 'đ'", symbols);
	        return decimalFormat.format(number);
	}
	
	public BigDecimal removeFormatting(String formattedString) {
	    // Loại bỏ ký tự 'đ' và các dấu phân cách hàng nghìn
	    formattedString = formattedString.replace("đ", "").trim(); // Xóa ký tự "đ"
	    formattedString = formattedString.replace(".", ""); // Xóa dấu phân cách hàng nghìn (.)

	    // Đảm bảo rằng dấu phân cách thập phân là dấu phẩy
	    formattedString = formattedString.replace(",", ".");

	    // Chuyển chuỗi đã xử lý thành BigDecimal
	    try {
	        return new BigDecimal(formattedString);
	    } catch (NumberFormatException e) {
	        return BigDecimal.ZERO; // Trả về BigDecimal.ZERO nếu không thể chuyển đổi
	    }
	}
	
	
}
