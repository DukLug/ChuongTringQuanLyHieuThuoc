package entity;

import java.math.BigDecimal;
import java.util.Objects;

public class ChiTietDonNhap {
	private String maChiTietDonNhap;
	private SanPhamYTe maSanPham;
	private int soLuong;
	private BigDecimal giaNhap;
	private DonNhapHang maDonNhap;
	
	
	
	public ChiTietDonNhap() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChiTietDonNhap(String maChiTietDonNhap) {
		super();
		this.maChiTietDonNhap = maChiTietDonNhap;
	}



	public ChiTietDonNhap(String maChiTietDonNhap, SanPhamYTe maSanPham, int soLuong, BigDecimal giaNhap,
			DonNhapHang maDonNhap) {
		super();
		this.maChiTietDonNhap = maChiTietDonNhap;
		setMaSanPham(maSanPham);
		setSoLuong(soLuong);
		setGiaNhap(giaNhap);
		setMaDonNhap(maDonNhap);
	}
	public String getMaChiTietDonNhap() {
		return maChiTietDonNhap;
	}
	public SanPhamYTe getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(SanPhamYTe maSanPham) {
		this.maSanPham = maSanPham;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		try {
	        if (soLuong > 0) {
	            this.soLuong = soLuong;
	        } else {
	            throw new IllegalArgumentException("Số lượng phải lớn hơn 0.");
	        }
	    } catch (IllegalArgumentException e) {
	        System.out.println(e.getMessage());
	    }
	}
	public BigDecimal getGiaNhap() {
		return giaNhap;
	}
	public void setGiaNhap(BigDecimal giaNhap) {
		try {
	        if (giaNhap.compareTo(BigDecimal.ZERO) > 0) {
	            this.giaNhap = giaNhap;
	        } else {
	            throw new IllegalArgumentException("Giá nhập phải lớn hơn 0.");
	        }
	    } catch (IllegalArgumentException e) {
	        System.out.println(e.getMessage());
	    }
	}
	public DonNhapHang getMaDonNhap() {
		return maDonNhap;
	}
	public void setMaDonNhap(DonNhapHang maDonNhap) {
		this.maDonNhap = maDonNhap;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maChiTietDonNhap);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietDonNhap other = (ChiTietDonNhap) obj;
		return Objects.equals(maChiTietDonNhap, other.maChiTietDonNhap);
	}
	@Override
	public String toString() {
		return "ChiTietDonNhap [maChiTietDonNhap=" + maChiTietDonNhap + ", maSanPham=" + maSanPham + ", soLuong="
				+ soLuong + ", giaNhap=" + giaNhap + ", maDonNhap=" + maDonNhap + "]";
	}
	
	

}
