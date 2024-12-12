package entity;

import java.sql.Date;
import java.util.Objects;

public class DonHuyHang {
	private String maDonHuyHang;
	private Date ngayHuy;
	private int soLuongDonViTinh1;
	private int soLuongDonViTinh2;
	private int soLuongDonViTinh3;
	
	private LoHang loHang;
	private NhanVien nhanVien;
	
	public DonHuyHang(String maDonHuyHang) {
		super();
		this.maDonHuyHang = maDonHuyHang;
	}
	
	public DonHuyHang(String maDonHuyHang, Date ngayHuy, int soLuongDonViTinh1, int soLuongDonViTinh2,
			int soLuongDonViTinh3, LoHang loHang, NhanVien nhanVien) {
		super();
		this.maDonHuyHang = maDonHuyHang;
		setNgayHuy(ngayHuy);
		setSoLuongDonViTinh1(soLuongDonViTinh1);
		setSoLuongDonViTinh2(soLuongDonViTinh2);
		setSoLuongDonViTinh3(soLuongDonViTinh3);
		setLoHang(loHang);
		setNhanVien(nhanVien);
	}
	public String getMaDonHuyHang() {
		return maDonHuyHang;
	}
	public Date getNgayHuy() {
		return ngayHuy;
	}
	public int getSoLuongDonViTinh1() {
		return soLuongDonViTinh1;
	}
	public int getSoLuongDonViTinh2() {
		return soLuongDonViTinh2;
	}
	public int getSoLuongDonViTinh3() {
		return soLuongDonViTinh3;
	}
	public LoHang getLoHang() {
		return loHang;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNgayHuy(Date ngayHuy) {
		this.ngayHuy = ngayHuy;
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
	public void setLoHang(LoHang loHang) {
		this.loHang = loHang;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maDonHuyHang);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DonHuyHang other = (DonHuyHang) obj;
		return Objects.equals(maDonHuyHang, other.maDonHuyHang);
	}

	@Override
	public String toString() {
		return "DonHuyHang [maDonHuyHang=" + maDonHuyHang + ", ngayHuy=" + ngayHuy + ", soLuongDonViTinh1="
				+ soLuongDonViTinh1 + ", soLuongDonViTinh2=" + soLuongDonViTinh2 + ", soLuongDonViTinh3="
				+ soLuongDonViTinh3 + ", loHang=" + loHang + ", nhanVien=" + nhanVien + "]";
	}
	
	
	
}
