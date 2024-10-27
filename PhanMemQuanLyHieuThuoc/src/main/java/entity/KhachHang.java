package entity;

import java.util.Objects;

/**
 * 
 */
public class KhachHang {
	private String maKhachHang;
	private String hoTen;
	private String sdt;
	private String diaChi;
	private int diemTichLuy;
	private String cccd;
	
	public KhachHang(String maKhachHang, String hoTen, String sdt, String diaChi, int diemTichLuy, String cccd) {
		super();
		this.maKhachHang = maKhachHang;
		this.hoTen = hoTen;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.diemTichLuy = diemTichLuy;
		this.cccd = cccd;
	}

	public KhachHang(String maKhachHang) {
		super();
		this.maKhachHang = maKhachHang;
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public int getDiemTichLuy() {
		return diemTichLuy;
	}

	public void setDiemTichLuy(int diemTichLuy) {
		this.diemTichLuy = diemTichLuy;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maKhachHang.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKhachHang.toLowerCase(), other.maKhachHang.toLowerCase());
	}

	@Override
	public String toString() {
		return "KhachHang [maKhachHang=" + maKhachHang + ", hoTen=" + hoTen + ", sdt=" + sdt + ", diaChi=" + diaChi
				+ ", diemTichLuy=" + diemTichLuy + ", cccd=" + cccd + "]";
	}
	
}
