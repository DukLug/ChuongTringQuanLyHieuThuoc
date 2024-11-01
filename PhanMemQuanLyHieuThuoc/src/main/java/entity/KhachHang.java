package entity;

import java.util.Objects;

public class KhachHang {
	private String maKhachHang;
	private String hoTen;
	private String sdt;
	private String diaChi;
	private int diemTichLuy;
	private String cccd;
	
	public KhachHang() {
		
	}

	public KhachHang(String maKhachHang, String hoTen, String sdt, String cccd, String diaChi, int diemTichLuy) {

		super();
		this.maKhachHang = maKhachHang;
		setHoTen(hoTen);
		setSdt(sdt);
		setDiaChi(diaChi);
		setDiemTichLuy(diemTichLuy);
		setCccd(cccd);
	}

	public KhachHang(String maKhachHang) {
		super();
		this.maKhachHang = maKhachHang;
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public String getHoTen() {
		return hoTen;
	}
	
	public String getSdt() {
		return sdt;
	}

	public void setHoTen(String hoTen) {

		if (hoTen.matches("^[a-zA-Z][\\sa-zA-Z]*$"))
			throw new  IllegalArgumentException("Họ tên chỉ chứa ký tự là chữ thường, chữ hoa và khoảng trắng");
		

		this.hoTen = hoTen;
	}
	
	public String getDiaChi() {
		return diaChi;
	}

	public void setSdt(String sdt) {
		if (sdt.length() > 0) {
			if (sdt.length() < 10 || sdt.length() > 14)
				throw new  IllegalArgumentException("Số điện thoại chỉ gồm 10 đến 14 ký tự số");
		}

		
		this.sdt = sdt;
	}

	public int getDiemTichLuy() {
		return diemTichLuy;
	}
	
	public String getCccd() {
		return cccd;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	
	public void setDiemTichLuy(int diemTichLuy) {
		this.diemTichLuy = diemTichLuy;
	}

	public void setCccd(String cccd) {
		if (cccd.length() > 0)
			if (cccd.length() != 12)
				throw new  IllegalArgumentException("Căn cước công dân phải gồm 12 ký tự só");

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
