package entity;

import java.math.BigDecimal;
import java.util.Objects;

public class ChiTietHoaDon {
	
	private String maChiTietHoaDon;
	private int soLuong;
	private BigDecimal giaBan;
	private HoaDon hoaDon;
	private SanPhamYTe sanPhamYTe;
	private LoHang loHang;
	private LoHang loHangThayThe;
	
	public ChiTietHoaDon(String maChiTietHoaDon) {
		super();
		this.maChiTietHoaDon = maChiTietHoaDon;
	}
	
	public ChiTietHoaDon(String maChiTietHoaDon, int soLuong, BigDecimal giaBan, HoaDon hoaDon, SanPhamYTe sanPhamYTe,
			LoHang loHang, LoHang loHangThayThe) {
		super();
		this.maChiTietHoaDon = maChiTietHoaDon;
		setSoLuong(soLuong);
		setGiaBan(giaBan);
		setHoaDon(hoaDon);
		setSanPhamYTe(sanPhamYTe);
		setLoHang(loHang);
		setLoHangThayThe(loHangThayThe);
	}
	
	
	public ChiTietHoaDon(SanPhamYTe sanPhamYTe, int soLuong, BigDecimal tongGia) {
	    super();
	    this.sanPhamYTe = sanPhamYTe;
	    this.soLuong = soLuong;
	    this.giaBan = tongGia;
	}

	public String getMaChiTietHoaDon() {
		return maChiTietHoaDon;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public BigDecimal getGiaBan() {
		return giaBan;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public SanPhamYTe getSanPhamYTe() {
		return sanPhamYTe;
	}

	public LoHang getLoHang() {
		return loHang;
	}

	public LoHang getLoHangThayThe() {
		return loHangThayThe;
	}

	public void setSoLuong(int soLuong) {
		if(soLuong <= 0) {
			throw new IllegalArgumentException("Đã cố gắng đặt số lượng bằng : " + soLuong + " .Số lượng phải lớn hơn 0");
		}
		this.soLuong = soLuong;
	}

	public void setGiaBan(BigDecimal giaBan) {
		if(giaBan.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Đã cố gắng đặt giá bán bằng : " + giaBan + " .Giá bán phải lớn hơn 0");
		}
		this.giaBan = giaBan;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public void setSanPhamYTe(SanPhamYTe sanPhamYTe) {
		this.sanPhamYTe = sanPhamYTe;
	}

	public void setLoHang(LoHang loHang) {
		this.loHang = loHang;
	}

	public void setLoHangThayThe(LoHang loHangThayThe) {
		this.loHangThayThe = loHangThayThe;
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [maChiTietHoaDon=" + maChiTietHoaDon + ", soLuong=" + soLuong + ", giaBan=" + giaBan
				+ ", hoaDon=" + hoaDon + ", sanPhamYTe=" + sanPhamYTe + ", loHang=" + loHang + ", loHangThayThe="
				+ loHangThayThe + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maChiTietHoaDon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietHoaDon other = (ChiTietHoaDon) obj;
		return Objects.equals(maChiTietHoaDon, other.maChiTietHoaDon);
	}
	
	
	
}
