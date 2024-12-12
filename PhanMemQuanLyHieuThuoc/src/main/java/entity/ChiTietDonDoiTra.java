package entity;

import java.math.BigDecimal;
import java.util.Objects;

public class ChiTietDonDoiTra {
	private String maChiTietDonDoiTra;
	private int soLuongDonViTinh1;
	private int soLuongDonViTinh2;
	private int soLuongDonViTinh3;
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




	public ChiTietDonDoiTra(String maChiTietDonDoiTra, int soLuongDonViTinh1, int soLuongDonViTinh2,  int soLuongDonViTinh3, BigDecimal giaBan, DonDoiTra maDonDoiTra,
			SanPhamYTe maSanPham, LoHang maLoHang, LoHang maLoHangThayThe) {
		super();
		this.maChiTietDonDoiTra = maChiTietDonDoiTra;
		setSoLuongDonViTinh1(soLuongDonViTinh1);
		setSoLuongDonViTinh2(soLuongDonViTinh2);
		setSoLuongDonViTinh3(soLuongDonViTinh3);
		setGiaBan(giaBan);
		setMaDonDoiTra(maDonDoiTra);
		setMaSanPham(maSanPham);
		setMaLoHang(maLoHang);
		setMaLoHangThayThe(maLoHangThayThe);
	}


	public String getMaChiTietDonDoiTra() {
		return maChiTietDonDoiTra;
	}








	public int getSoLuongDonViTinh1() {
		return soLuongDonViTinh1;
	}


	public void setSoLuongDonViTinh1(int soLuongDonViTinh1) {
		this.soLuongDonViTinh1 = soLuongDonViTinh1;
	}


	public int getSoLuongDonViTinh2() {
		return soLuongDonViTinh2;
	}


	public void setSoLuongDonViTinh2(int soLuongDonViTinh2) {
		this.soLuongDonViTinh2 = soLuongDonViTinh2;
	}


	public int getSoLuongDonViTinh3() {
		return soLuongDonViTinh3;
	}


	public void setSoLuongDonViTinh3(int soLuongDonViTinh3) {
		this.soLuongDonViTinh3 = soLuongDonViTinh3;

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
		return "ChiTietDonDoiTra [maChiTietDonDoiTra=" + maChiTietDonDoiTra + ", soLuongDonViTinh1=" + soLuongDonViTinh1
				+ ", soLuongDonViTinh2=" + soLuongDonViTinh2 + ", soLuongDonViTinh3=" + soLuongDonViTinh3 + ", giaBan="
				+ giaBan + ", maDonDoiTra=" + maDonDoiTra + ", maSanPham=" + maSanPham + ", maLoHang=" + maLoHang
				+ ", maLoHangThayThe=" + maLoHangThayThe + "]";
	}


	public void setMaChiTietDoiTra(String maChiTietDoiTra) {
		this.maChiTietDonDoiTra = maChiTietDoiTra;
		
	}

	
	
	

}
