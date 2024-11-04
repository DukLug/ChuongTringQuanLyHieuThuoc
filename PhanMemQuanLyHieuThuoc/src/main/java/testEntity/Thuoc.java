package testEntity;

import java.math.BigDecimal;

import customDataType.DonViTinh;

public class Thuoc {
	public String maThuoc;
	public String tenThuoc;
	public DonViTinh donViTinh;
	public int soLuong;
	public BigDecimal giaBan;
	
	public Thuoc(String maThuoc, String tenThuoc, DonViTinh donViTinh, int soLuong) {
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.donViTinh = donViTinh;
		this.soLuong = soLuong;
	}

	public Thuoc(String tenThuoc, DonViTinh donViTinh, int soLuong, BigDecimal giaBan) {
		super();
		this.tenThuoc = tenThuoc;
		this.donViTinh = donViTinh;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
	}

	@Override
	public String toString() {
		return "Thuoc [maThuoc=" + maThuoc + ", tenThuoc=" + tenThuoc + ", donViTinh=" + donViTinh + ", soLuong="
				+ soLuong + "]";
	}
	
	
}
