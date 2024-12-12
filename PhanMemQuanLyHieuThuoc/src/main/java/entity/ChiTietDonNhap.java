package entity;

import java.math.BigDecimal;
import java.util.Objects;

public class ChiTietDonNhap {
	private String maChiTietDonNhap;
	private SanPhamYTe maSanPham;
	private int soLuongDonViTinh1;
	private int soLuongDonViTinh2;
	private int soLuongDonViTinh3;
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



	public ChiTietDonNhap(String maChiTietDonNhap, SanPhamYTe maSanPham, int soLuongDonViTinh1, int soLuongDonViTinh2, int soLuongDonViTinh3, BigDecimal giaNhap,
			DonNhapHang maDonNhap) {
		super();
		this.maChiTietDonNhap = maChiTietDonNhap;
		setMaSanPham(maSanPham);
		setSoLuongDonViTinh1(soLuongDonViTinh1);
		setSoLuongDonViTinh2(soLuongDonViTinh2);
		setSoLuongDonViTinh3(soLuongDonViTinh3);
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
	public int getSoLuongDonViTinh1() {
		return soLuongDonViTinh1;
	}
	public void setSoLuongDonViTinh1(int soLuongDonViTinh1) {
		try {
	        if (soLuongDonViTinh1 > 0) {
	            this.soLuongDonViTinh1 = soLuongDonViTinh1;
	        } else {
	            throw new IllegalArgumentException("Số lượng phải lớn hơn 0.");
	        }
	    } catch (IllegalArgumentException e) {
	        System.out.println(e.getMessage());
	    }
	}
	public int getSoLuongDonViTinh2() {
		return soLuongDonViTinh2;
	}
	public void setSoLuongDonViTinh2(int soLuongDonViTinh2) {
		try {
	        if (soLuongDonViTinh2 > 0) {
	            this.soLuongDonViTinh2 = soLuongDonViTinh2;
	        } else {
	            throw new IllegalArgumentException("Số lượng phải lớn hơn 0.");
	        }
	    } catch (IllegalArgumentException e) {
	        System.out.println(e.getMessage());
	    }
	}
	public int getSoLuongDonViTinh3() {
		return soLuongDonViTinh3;
	}
	public void setSoLuongDonViTinh3(int soLuongDonViTinh3) {
		try {
	        if (soLuongDonViTinh3 > 0) {
	            this.soLuongDonViTinh3 = soLuongDonViTinh3;
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
		return "ChiTietDonNhap [maChiTietDonNhap=" + maChiTietDonNhap + ", maSanPham=" + maSanPham
				+ ", soLuongDonViTinh1=" + soLuongDonViTinh1 + ", soLuongDonViTinh2=" + soLuongDonViTinh2
				+ ", soLuongDonViTinh3=" + soLuongDonViTinh3 + ", giaNhap=" + giaNhap + ", maDonNhap=" + maDonNhap
				+ "]";
	}
	
	
	
	

}