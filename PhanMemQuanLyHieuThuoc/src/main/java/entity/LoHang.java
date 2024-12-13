package entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class LoHang {
	private String maLo;
	private Date ngaySanXuat;
	private Date hanSuDung;
	private BigDecimal giaNhap;
	private int soLuongDonViTinh1;
	private int soLuongDonViTinh2;
	private int soLuongDonViTinh3;
	private String viTri;
	private SanPhamYTe sanPham;
	private ChiTietDonNhap maChiTietDonNhap;
	
	
	
	public LoHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoHang(String maLo) {
		super();
		this.maLo = maLo;
	}

	
	public LoHang(String maLo, Date ngaySanXuat, Date hanSuDung, BigDecimal giaNhap, int soLuongDonVinhTinh1, int soLuongDonVinhTinh2, int soLuongDonVinhTinh3, String viTri, SanPhamYTe sanPham,
			ChiTietDonNhap maChiTietDonNhap) {
		super();
		this.maLo = maLo;
		setNgaySanXuat(ngaySanXuat);
		setHanSuDung(hanSuDung);
		setGiaNhap(giaNhap);
		setSoLuongDonViTinh1(soLuongDonViTinh1);
		setSoLuongDonViTinh2(soLuongDonViTinh2);
		setSoLuongDonViTinh3(soLuongDonViTinh3);
		setViTri(viTri);
		setSanPham(sanPham);
		setMaChiTietDonNhap(maChiTietDonNhap);
	}
	public LoHang(String maLo, int soLuongDonViTinh1, int soLuongDonViTinh2, int soLuongDonViTinh3,
			SanPhamYTe sanPham) {
		super();
		this.maLo = maLo;
		this.soLuongDonViTinh1 = soLuongDonViTinh1;
		this.soLuongDonViTinh2 = soLuongDonViTinh2;
		this.soLuongDonViTinh3 = soLuongDonViTinh3;
		this.sanPham = sanPham;
	}
	public String getMaLo() {
		return maLo;
	}
	public Date getNgaySanXuat() {
		return ngaySanXuat;
	}
	public void setNgaySanXuat(Date ngaySanXuat) {
		try {
	        Date currentDate = new Date(System.currentTimeMillis());
	        if (ngaySanXuat.before(currentDate)) {
	            this.ngaySanXuat = ngaySanXuat;
	        } else {
	            throw new IllegalArgumentException("Ngày sản xuất phải trước ngày hiện tại.");
	        }
	    } catch (IllegalArgumentException e) {
	        System.out.println(e.getMessage());
	    }
	}
	
	public Date getHanSuDung() {
		return hanSuDung;
	}
	public void setHanSuDung(Date hanSuDung) {
		this.hanSuDung = hanSuDung;
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
	public String getViTri() {
		return viTri;
	}
	public void setViTri(String viTri) {
		this.viTri = viTri;
	}
	public SanPhamYTe getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPhamYTe sanPham) {
		this.sanPham = sanPham;
	}
	public ChiTietDonNhap getMaChiTietDonNhap() {
		return maChiTietDonNhap;
	}
	public void setMaChiTietDonNhap(ChiTietDonNhap maChiTietDonNhap) {
		this.maChiTietDonNhap = maChiTietDonNhap;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maLo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoHang other = (LoHang) obj;
		return Objects.equals(maLo, other.maLo);
	}
	@Override
	public String toString() {
		return "LoHang [maLo=" + maLo + ", ngaySanXuat=" + ngaySanXuat + ", hanSuDung=" + hanSuDung + ", giaNhap="
				+ giaNhap + ", soLuongDonViTinh1=" + soLuongDonViTinh1 + ", soLuongDonViTinh2=" + soLuongDonViTinh2
				+ ", soLuongDonViTinh3=" + soLuongDonViTinh3 + ", viTri=" + viTri + ", sanPham=" + sanPham
				+ ", maChiTietDonNhap=" + maChiTietDonNhap + "]";
	}
	
	

}

