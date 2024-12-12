package entity;

import java.math.BigDecimal;
import java.util.Objects;

public class ChiTietHoaDon {
	
	private String maChiTietHoaDon;
	private int soLuongDonViTinh1;
	private int soLuongDonViTinh2;
	private int soLuongDonViTinh3;
	private BigDecimal giaBan;
	private HoaDon hoaDon;
	private SanPhamYTe sanPhamYTe;
	private LoHang loHang;
	private LoHang loHangThayThe;
	
	public ChiTietHoaDon(String maChiTietHoaDon) {
		super();
		this.maChiTietHoaDon = maChiTietHoaDon;
	}
	
	public ChiTietHoaDon(String maChiTietHoaDon, int soLuongDonViTinh1, int soLuongDonViTinh2, int soLuongDonViTinh3, BigDecimal giaBan, HoaDon hoaDon, SanPhamYTe sanPhamYTe,
			LoHang loHang, LoHang loHangThayThe) {
		super();
		this.maChiTietHoaDon = maChiTietHoaDon;
		setSoLuongDonViTinh1(soLuongDonViTinh1);
		setSoLuongDonViTinh2(soLuongDonViTinh2);
		setSoLuongDonViTinh3(soLuongDonViTinh3);
		setGiaBan(giaBan);
		setHoaDon(hoaDon);
		setSanPhamYTe(sanPhamYTe);
		setLoHang(loHang);
		setLoHangThayThe(loHangThayThe);
	}
	

	public String getMaChiTietHoaDon() {
		return maChiTietHoaDon;
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

	@Override
	public String toString() {
		return "ChiTietHoaDon [maChiTietHoaDon=" + maChiTietHoaDon + ", soLuongDonViTinh1=" + soLuongDonViTinh1
				+ ", soLuongDonViTinh2=" + soLuongDonViTinh2 + ", soLuongDonViTinh3=" + soLuongDonViTinh3 + ", giaBan="
				+ giaBan + ", hoaDon=" + hoaDon + ", sanPhamYTe=" + sanPhamYTe + ", loHang=" + loHang
				+ ", loHangThayThe=" + loHangThayThe + "]";
	}
	
	
	
}
