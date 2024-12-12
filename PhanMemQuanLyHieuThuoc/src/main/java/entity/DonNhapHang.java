package entity;

import java.sql.Date;
import java.util.Objects;

public class DonNhapHang {
	private String maDonNhap;
	private Date ngayNhap;
	private NhanVien nhanVienNhap;
	
	public DonNhapHang(String maDonNhap) {
		super();
		this.maDonNhap = maDonNhap;
	}
	
	public DonNhapHang(String maDonNhap, Date ngayNhap, NhanVien nhanVienNhap) {
		super();
		this.maDonNhap = maDonNhap;
		setNgayNhap(ngayNhap);
		setNhanVienNhap(nhanVienNhap);
	}

	public String getMaDonNhap() {
		return maDonNhap;
	}

	public Date getNgayNhap() {
		return ngayNhap;
	}

	public NhanVien getNhanVienNhap() {
		return nhanVienNhap;
	}

//	public void setMaDonNhap(String maDonNhap) {
//		this.maDonNhap = maDonNhap;
//	}

	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

	public void setNhanVienNhap(NhanVien nhanVienNhap) {
		this.nhanVienNhap = nhanVienNhap;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(maDonNhap);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DonNhapHang other = (DonNhapHang) obj;
		return Objects.equals(maDonNhap, other.maDonNhap);
	}

	@Override
	public String toString() {
		return "DonNhapHang [maDonNhap=" + maDonNhap + ", ngayNhap=" + ngayNhap + ", nhanVienNhap=" + nhanVienNhap
				+ "]";
	}
	
	
}

