package entity;

import java.sql.Date;
import java.util.Objects;

public class KhuyenMai {
	private String maKhuyenMai;
	private Date ngayKhuyenMai;
	private Date ngayKetThuc;
	private String dieuKien;
	private double chietKhau;
	private NhanVien maNhanVien;
	
	
	public KhuyenMai() {
		super();
	}

	public KhuyenMai(String maKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
	}

	public KhuyenMai(String maKhuyenMai, Date ngayKhuyenMai, Date ngayKetThuc, String dieuKien, double chietKhau,
			NhanVien maNhanVien) {
		super();
		this.maKhuyenMai = maKhuyenMai;

		setNgayKhuyenMai(ngayKhuyenMai);
		setNgayKetThuc(ngayKetThuc);
		setDieuKien(dieuKien);
		setChietKhau(chietKhau);
		setMaNhanVien(maNhanVien);

	}

	public String getMaKhuyenMai() {
		return maKhuyenMai;
	}



	public Date getNgayKhuyenMai() {
		return ngayKhuyenMai;
	}

	public void setNgayKhuyenMai(Date ngayKhuyenMai) {

		try {
	        Date currentDate = new Date(System.currentTimeMillis());
	        if (!ngayKhuyenMai.after(currentDate)) {
	            this.ngayKhuyenMai = ngayKhuyenMai;
	        } else {
	            throw new IllegalArgumentException("Ngày khuyến mãi phải bằng hoặc sau ngày hiện tại.");
	        }
	    } catch (IllegalArgumentException e) {
	        System.out.println(e.getMessage());
	    }
	}

	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(Date ngayKetThuc) {

		try {
	        if (this.ngayKhuyenMai != null) {
	            if (ngayKetThuc.after(this.ngayKhuyenMai)) {
	                this.ngayKetThuc = ngayKetThuc;
	            } else {
	                throw new IllegalArgumentException("Ngày kết thúc phải sau ngày khuyến mãi.");
	            }
	        } else {
	            throw new IllegalArgumentException("Ngày khuyến mãi chưa được thiết lập.");
	        }
	    } catch (IllegalArgumentException e) {
	        System.out.println(e.getMessage());
	    }

	}

	public String getDieuKien() {
		return dieuKien;
	}

	public void setDieuKien(String dieuKien) {
		this.dieuKien = dieuKien;
	}

	public double getChietKhau() {
		return chietKhau;
	}

	public void setChietKhau(double chietKhau) {

		try {
	        if (chietKhau >= 0.0 && chietKhau <= 1.0) {
	            this.chietKhau = chietKhau;
	        } else {
	            throw new IllegalArgumentException("Chiết khấu phải nằm trong khoảng từ 0 đến 1.");
	        }
	    } catch (IllegalArgumentException e) {
	        System.out.println(e.getMessage());
	    }
	}



	public void setMaNhanVien(NhanVien maNhanVien) {
		this.maNhanVien = maNhanVien;

	}

	public NhanVien getMaNhanVien() {
		return maNhanVien;
	}


	@Override
	public int hashCode() {
		return Objects.hash(maKhuyenMai);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhuyenMai other = (KhuyenMai) obj;
		return Objects.equals(maKhuyenMai, other.maKhuyenMai);
	}

	@Override
	public String toString() {
		return "KhuyenMai [maKhuyenMai=" + maKhuyenMai + ", ngayKhuyenMai=" + ngayKhuyenMai + ", ngayKetThuc="
				+ ngayKetThuc + ", dieuKien=" + dieuKien + ", chietKhau=" + chietKhau + ", maNhanVien=" + maNhanVien
				+ "]";
	}

}
