package entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class LoHang {
	private String maLo;
	private Date ngaySanXuat;
	private BigDecimal giaNhap;
	private int soLuong;
	private String viTri;
	private SanPhamYTe sanPham;
	private ChiTietDonNhap maChiTietDonNhap;
	
	
	
	public LoHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoHang(String maLo) {
		this.maLo = maLo;
	}




	public LoHang(String maLo, Date ngaySanXuat, BigDecimal giaNhap, int soLuong, String viTri, SanPhamYTe sanPham,
			ChiTietDonNhap maChiTietDonNhap) {
		super();
		this.maLo = maLo;
		setNgaySanXuat(ngaySanXuat);
		setGiaNhap(giaNhap);
		setSoLuong(soLuong);
		setViTri(viTri);
		setSanPham(sanPham);
		setMaChiTietDonNhap(maChiTietDonNhap);
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
		return "LoHang [maLo=" + maLo + ", ngaySanXuat=" + ngaySanXuat + ", giaNhap=" + giaNhap + ", soLuong=" + soLuong
				+ ", viTri=" + viTri + ", sanPham=" + sanPham + ", maChiTietDonNhap=" + maChiTietDonNhap + "]";
	}
	
	
	
	

}
