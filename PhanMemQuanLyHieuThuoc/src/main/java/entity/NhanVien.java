package entity;

import java.sql.Date;
import java.util.Objects;

import customDataType.ChucVu;
import customDataType.GioiTinh;
import customDataType.TrangThaiLamViec;

public class NhanVien {
	private String maNhanVien;
	private String hoten;
	private String sdt;
	private String cccd;
	private Date ngaySinh;
	private GioiTinh gioiTinh;
	private ChucVu chucVu;
	private TrangThaiLamViec trangThaiLamViec;
	
	public NhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	

	public NhanVien(String maNhanVien, String hoten, String sdt, String cccd, Date ngaySinh, GioiTinh gioiTinh,
			ChucVu chucVu, TrangThaiLamViec trangThaiLamViec) {
		super();
		this.maNhanVien = maNhanVien;
		setHoten(hoten);
		setSdt(sdt);
		setCccd(cccd);
		setNgaySinh(ngaySinh);
		setGioiTinh(gioiTinh);
		setChucVu(chucVu);
		setTrangThaiLamViec(trangThaiLamViec);
	}



	public String getMaNhanVien() {
		return maNhanVien;
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
