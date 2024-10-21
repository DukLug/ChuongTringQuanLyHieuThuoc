package enity;

import java.sql.Date;
import java.util.Objects;

public class NhanVien {
	private String maNhanVien;
	private String hoten;
	private String sdt;
	private String cccd;
	private Date ngaySinh;
	private TrangThaiLamViec trangThaiLamViec;
	private ChucVu chucVu;
	private GioiTinh gioiTinh;
	
	public NhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public NhanVien(String maNhanVien, String hoten, String sdt, String cccd, Date ngaySinh,
			TrangThaiLamViec trangThaiLamViec, ChucVu chucVu, GioiTinh gioiTinh) {
		super();
		this.maNhanVien = maNhanVien;
		this.hoten = hoten;
		this.sdt = sdt;
		this.cccd = cccd;
		this.ngaySinh = ngaySinh;
		this.trangThaiLamViec = trangThaiLamViec;
		this.chucVu = chucVu;
		this.gioiTinh = gioiTinh;
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public TrangThaiLamViec getTrangThaiLamViec() {
		return trangThaiLamViec;
	}

	public void setTrangThaiLamViec(TrangThaiLamViec trangThaiLamViec) {
		this.trangThaiLamViec = trangThaiLamViec;
	}

	public ChucVu getChucVu() {
		return chucVu;
	}

	public void setChucVu(ChucVu chucVu) {
		this.chucVu = chucVu;
	}

	public GioiTinh getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(GioiTinh gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNhanVien, other.maNhanVien);
	}

	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", hoten=" + hoten + ", sdt=" + sdt + ", cccd=" + cccd
				+ ", ngaySinh=" + ngaySinh + ", trangThaiLamViec=" + trangThaiLamViec + ", chucVu=" + chucVu
				+ ", gioiTinh=" + gioiTinh + "]";
	}
	
}
