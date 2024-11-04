package entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class HoaDon {
	private String maHoaDon;
	private Date ngayTao;
	private int diemSuDung;
	private BigDecimal thanhTien;
	private NhanVien nhanVien;
	private KhuyenMai khuyenMai;
	private KhachHang khachHang;
	private ArrayList<ChiTietHoaDon> chiTietHoaDon;
	
	public HoaDon(String maHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
	}
	
	public HoaDon(String maHoaDon, Date ngayTao, int diemSuDung, BigDecimal thanhTien, NhanVien nhanVien,
			KhuyenMai khuyenMai, KhachHang khachHang, ArrayList<ChiTietHoaDon> chiTietHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
		setNgayTao(ngayTao);
		setDiemSuDung(diemSuDung);
		setThanhTien(thanhTien);
		setNhanVien(nhanVien);
		setKhuyenMai(khuyenMai);
		setKhachHang(khachHang);
		setChiTietHoaDon(chiTietHoaDon);
	}
	public HoaDon(String maHoaDon, Date ngayTao, int diemSuDung, BigDecimal thanhTien, NhanVien nhanVien,
			KhuyenMai khuyenMai, KhachHang khachHang) {
		super();
		this.maHoaDon = maHoaDon;
		setNgayTao(ngayTao);
		setDiemSuDung(diemSuDung);
		setThanhTien(thanhTien);
		setNhanVien(nhanVien);
		setKhuyenMai(khuyenMai);
		setKhachHang(khachHang);
	}
	
//	public HoaDon(String maHoaDon, Date ngayTao, BigDecimal thanhTien, NhanVien nhanVien,
//			KhuyenMai khuyenMai, KhachHang khachHang, ArrayList<ChiTietHoaDon> chiTietHoaDon) {
//		
//	}
	
//	public HoaDon(String maHD, String loaiHD, String ngayTao2, int soLuong, BigDecimal thanhTien2,
//			BigDecimal phiTraHang, BigDecimal thucThu) {
//		// TODO Auto-generated constructor stub
//	}

	public String getMaHoaDon() {
		return maHoaDon;
	}
	public Date getNgayTao() {
		return ngayTao;
	}
	public int getDiemSuDung() {
		return diemSuDung;
	}
	public BigDecimal getThanhTien() {
		return thanhTien;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public ArrayList<ChiTietHoaDon> getChiTietHoaDon() {
		return chiTietHoaDon;
	}
	public void setNgayTao(Date ngayTao) {
//		if(ngayTao != Date.valueOf(LocalDate.now())) {
//			throw new IllegalArgumentException("Đã cố gắng đặt ngày tạo bằng : " + ngayTao + " .Ngày tạo phải trùng với ngày hiện tại");
//		}
		this.ngayTao = ngayTao;
	}
	public void setDiemSuDung(int diemSuDung) {
		if(diemSuDung < 0) {
			throw new IllegalArgumentException("Đã cố gắng đặt điểm sử dụng bằng : " + diemSuDung + " .Điểm sử dụng phải lớn hơn hoặc bằng 0");
		}
		this.diemSuDung = diemSuDung;
	}
	public void setThanhTien(BigDecimal thanhTien) {
		if(thanhTien.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Đã cố gắng đặt thành tiền bằng : " + thanhTien + " .Thành tiền phải lớn hơn 0");
		}
		this.thanhTien = thanhTien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public void setChiTietHoaDon(ArrayList<ChiTietHoaDon> chiTietHoaDon) {
		if(chiTietHoaDon.size() > 10) {
			throw new IllegalArgumentException("Đã cố gắng tạo quá nhiều chi tiết hoa đơn cho một hóa đơn : " + thanhTien + " .Số lượng chi tiết hóa đơn không được vượt quá 10 cho mỗi hóa đơn");
		}
		this.chiTietHoaDon = chiTietHoaDon;
	}

	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", ngayTao=" + ngayTao + ", diemSuDung=" + diemSuDung + ", thanhTien="
				+ thanhTien + ", nhanVien=" + nhanVien + ", khuyenMai=" + khuyenMai + ", khachHang=" + khachHang
				+ ", chiTietHoaDon=" + chiTietHoaDon + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maHoaDon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHoaDon, other.maHoaDon);
	}
	
	
	
}
