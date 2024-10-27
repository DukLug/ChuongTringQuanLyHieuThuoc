package entity;

import enity.DonViTinh;

public class Thuoc {
	public String maThuoc;
	public String tenThuoc;
	public DonViTinh donViTinh;
	
	public Thuoc(String maThuoc, String tenThuoc, DonViTinh donViTinh) {
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.donViTinh = donViTinh;
	}
}
