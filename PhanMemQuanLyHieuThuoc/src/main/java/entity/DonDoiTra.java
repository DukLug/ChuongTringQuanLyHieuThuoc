package entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class DonDoiTra {
	private String maDonDoiTra;
	private Date ngayDoiTra;
	private BigDecimal tienHoan;
	private NhanVien maNhanVien;
	private KhuyenMai maKhuyenMai;
	private KhachHang maKhachhang;
	private HoaDon maHoaDon;
	
	
	public DonDoiTra() {
		super();
	
	}
	public DonDoiTra(String maDonDoiTra) {
		this.maDonDoiTra = maDonDoiTra;
	}

	public DonDoiTra(String maDonDoiTra, Date ngayDoiTra, BigDecimal tienHoan, NhanVien maNhanVien,
			KhuyenMai maKhuyenMai, KhachHang maKhachhang, HoaDon maHoaDon) {
		super();
		this.maDonDoiTra = maDonDoiTra;
		setNgayDoiTra(ngayDoiTra);
		setTienHoan(tienHoan);
		setMaNhanVien(maNhanVien);
		setMaKhuyenMai(maKhuyenMai);
		setMaKhachhang(maKhachhang);
		setMaHoaDon(maHoaDon);
	}
	public String getMaDonDoiTra() {
		return maDonDoiTra;
	}
	public Date getNgayDoiTra() {
		return ngayDoiTra;
	}
	public void setNgayDoiTra(Date ngayDoiTra) {
		this.ngayDoiTra = ngayDoiTra;
	}
	public BigDecimal getTienHoan() {
		return tienHoan;
	}
	public void setTienHoan(BigDecimal tienHoan) {
		this.tienHoan = tienHoan;
	}
	public NhanVien getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(NhanVien maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public KhuyenMai getMaKhuyenMai() {
		return maKhuyenMai;
	}
	public void setMaKhuyenMai(KhuyenMai maKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
	}
	public KhachHang getMaKhachhang() {
		return maKhachhang;
	}
	public void setMaKhachhang(KhachHang maKhachhang) {
		this.maKhachhang = maKhachhang;
	}
	public HoaDon getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(HoaDon maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maDonDoiTra);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DonDoiTra other = (DonDoiTra) obj;
		return Objects.equals(maDonDoiTra, other.maDonDoiTra);
	}
	@Override
	public String toString() {
		return "DonDoiTra [maDonDoiTra=" + maDonDoiTra + ", ngayDoiTra=" + ngayDoiTra + ", tienHoan=" + tienHoan
				+ ", maNhanVien=" + maNhanVien + ", maKhuyenMai=" + maKhuyenMai + ", maKhachhang=" + maKhachhang
				+ ", maHoaDon=" + maHoaDon + "]";
	}
	
}
