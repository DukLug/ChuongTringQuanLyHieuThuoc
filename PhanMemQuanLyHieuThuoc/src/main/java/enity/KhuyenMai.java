package enity;

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
		// TODO Auto-generated constructor stub
	}

	public KhuyenMai(String maKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
	}

	public KhuyenMai(String maKhuyenMai, Date ngayKhuyenMai, Date ngayKetThuc, String dieuKien, double chietKhau,
			NhanVien maNhanVien) {
		super();
		this.maKhuyenMai = maKhuyenMai;
		this.ngayKhuyenMai = ngayKhuyenMai;
		this.ngayKetThuc = ngayKetThuc;
		this.dieuKien = dieuKien;
		this.chietKhau = chietKhau;
		this.maNhanVien = maNhanVien;
	}

	public String getMaKhuyenMai() {
		return maKhuyenMai;
	}

	public void setMaKhuyenMai(String maKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
	}

	public Date getNgayKhuyenMai() {
		return ngayKhuyenMai;
	}

	public void setNgayKhuyenMai(Date ngayKhuyenMai) {
		this.ngayKhuyenMai = ngayKhuyenMai;
	}

	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
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
		
		this.chietKhau = chietKhau;
	}

	public NhanVien getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(NhanVien maNhanVien) {
		this.maNhanVien = maNhanVien;
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
