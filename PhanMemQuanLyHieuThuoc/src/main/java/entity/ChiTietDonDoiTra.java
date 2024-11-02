package entity;

import java.math.BigDecimal;
import java.util.Objects;

public class ChiTietDonDoiTra {
	private String maChiTietDonDoiTra;
	private int soLuong;
	private BigDecimal giaBan;
	private DonDoiTra maDonDoiTra;
	private SanPhamYTe maSanPham;
	private LoHang maLoHang;
	private LoHang maLoHangThayThe;
	
	
	
	public ChiTietDonDoiTra() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ChiTietDonDoiTra(String maChiTietDonDoiTra) {
		this.maChiTietDonDoiTra = maChiTietDonDoiTra;
	}



	public ChiTietDonDoiTra(String maChiTietDonDoiTra, int soLuong, BigDecimal giaBan, DonDoiTra maDonDoiTra,
			SanPhamYTe maSanPham, LoHang maLoHang, LoHang maLoHangThayThe) {
		super();
		this.maChiTietDonDoiTra = maChiTietDonDoiTra;
		setSoLuong(soLuong);
		setGiaBan(giaBan);
		setMaDonDoiTra(maDonDoiTra);
		setMaSanPham(maSanPham);
		setMaLoHang(maLoHang);
		setMaLoHangThayThe(maLoHangThayThe);
	}


	public String getMaChiTietDonDoiTra() {
		return maChiTietDonDoiTra;
	}




	public int getSoLuong() {
		return soLuong;
	}


	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}


	public BigDecimal getGiaBan() {
		return giaBan;
	}


	public void setGiaBan(BigDecimal giaBan) {
		this.giaBan = giaBan;
	}


	public DonDoiTra getMaDonDoiTra() {
		return maDonDoiTra;
	}


	public void setMaDonDoiTra(DonDoiTra maDonDoiTra) {
		this.maDonDoiTra = maDonDoiTra;
	}


	public SanPhamYTe getMaSanPham() {
		return maSanPham;
	}


	public void setMaSanPham(SanPhamYTe maSanPham) {
		this.maSanPham = maSanPham;
	}


	public LoHang getMaLoHang() {
		return maLoHang;
	}


	public void setMaLoHang(LoHang maLoHang) {
		this.maLoHang = maLoHang;
	}


	public LoHang getMaLoHangThayThe() {
		return maLoHangThayThe;
	}


	public void setMaLoHangThayThe(LoHang maLoHangThayThe) {
		this.maLoHangThayThe = maLoHangThayThe;
	}


	@Override
	public int hashCode() {
		return Objects.hash(maChiTietDonDoiTra);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietDonDoiTra other = (ChiTietDonDoiTra) obj;
		return Objects.equals(maChiTietDonDoiTra, other.maChiTietDonDoiTra);
	}


	@Override
	public String toString() {
		return "ChiTietDonDoiTra [maChiTietDonDoiTra=" + maChiTietDonDoiTra + ", soLuong=" + soLuong + ", giaBan="
				+ giaBan + ", maDonDoiTra=" + maDonDoiTra + ", maSanPham=" + maSanPham + ", maLoHang=" + maLoHang
				+ ", maLoHangThayThe=" + maLoHangThayThe + "]";
	}


	public void setMaChiTietDoiTra(String maChiTietDoiTra) {
		this.maChiTietDonDoiTra = maChiTietDoiTra;
		
	}
	
	
	

}
