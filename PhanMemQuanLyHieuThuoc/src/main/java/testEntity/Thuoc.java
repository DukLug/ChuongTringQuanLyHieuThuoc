package testEntity;

import customDataType.DonViTinh;

public class Thuoc {
	public String maThuoc;
	public String tenThuoc;
	public DonViTinh donViTinh;
	public int soLuong;
	
	public Thuoc(String maThuoc, String tenThuoc, DonViTinh donViTinh, int soLuong) {
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.donViTinh = donViTinh;
		this.soLuong = soLuong;
	}

	@Override
	public String toString() {
		return "Thuoc [maThuoc=" + maThuoc + ", tenThuoc=" + tenThuoc + ", donViTinh=" + donViTinh + ", soLuong="
				+ soLuong + "]";
	}
	
	
}
