package testEntity;

import java.math.BigDecimal;

import customDataType.DonViTinh;

public class Thuoc {
	public String maThuoc;
	public String tenThuoc;
	public DonViTinh donViTinh1;
	public DonViTinh donViTinh2;
	public DonViTinh donViTinh3;
	public int soLuongDVT1;
	public int soLuongDVT2;
	public int soLuongDVT3;
	public BigDecimal giaBanDonViTinh1;
	public BigDecimal giaBanDonViTinh2;
	public BigDecimal giaBanDonViTinh3;
	public int giaTriQuyDoi2;
	public int giaTriQuyDoi3;
	public String maLo;
	
	public DonViTinh donViTinh;
	public int soLuong;
	
	public Thuoc(String maThuoc, String tenThuoc, DonViTinh donViTinh, int soLuong) {
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.donViTinh = donViTinh;
		this.soLuong = soLuong;
	}

	public Thuoc(String maThuoc, String tenThuoc, DonViTinh donViTinh1, DonViTinh donViTinh2,
			DonViTinh donViTinh3, int soLuongDVT1, int soLuongDVT2, int soLuongDVT3, BigDecimal giaBanDonViTinh1,
			BigDecimal giaBanDonViTinh2, BigDecimal giaBanDonViTinh3, int giaTriQuyDoi2, int giaTriQuyDoi3) {
		super();
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.donViTinh1 = donViTinh1;
		this.donViTinh2 = donViTinh2;
		this.donViTinh3 = donViTinh3;
		this.soLuongDVT1 = soLuongDVT1;
		this.soLuongDVT2 = soLuongDVT2;
		this.soLuongDVT3 = soLuongDVT3;
		this.giaBanDonViTinh1 = giaBanDonViTinh1;
		this.giaBanDonViTinh2 = giaBanDonViTinh2;
		this.giaBanDonViTinh3 = giaBanDonViTinh3;
		this.giaTriQuyDoi2 = giaTriQuyDoi2;
		this.giaTriQuyDoi3 = giaTriQuyDoi3;
	}

	public Thuoc(String maThuoc, String tenThuoc, DonViTinh donViTinh1, DonViTinh donViTinh2, DonViTinh donViTinh3,
			int soLuongDVT1, int soLuongDVT2, int soLuongDVT3, BigDecimal giaBanDonViTinh1, BigDecimal giaBanDonViTinh2,
			BigDecimal giaBanDonViTinh3, int giaTriQuyDoi2, int giaTriQuyDoi3, String maLo, DonViTinh donViTinh,
			int soLuong) {
		super();
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.donViTinh1 = donViTinh1;
		this.donViTinh2 = donViTinh2;
		this.donViTinh3 = donViTinh3;
		this.soLuongDVT1 = soLuongDVT1;
		this.soLuongDVT2 = soLuongDVT2;
		this.soLuongDVT3 = soLuongDVT3;
		this.giaBanDonViTinh1 = giaBanDonViTinh1;
		this.giaBanDonViTinh2 = giaBanDonViTinh2;
		this.giaBanDonViTinh3 = giaBanDonViTinh3;
		this.giaTriQuyDoi2 = giaTriQuyDoi2;
		this.giaTriQuyDoi3 = giaTriQuyDoi3;
		this.maLo = maLo;
		this.donViTinh = donViTinh;
		this.soLuong = soLuong;
	}

	@Override
	public String toString() {
		return "Thuoc [maThuoc=" + maThuoc + ", tenThuoc=" + tenThuoc + ", donViTinh1="
				+ donViTinh1 + ", donViTinh2=" + donViTinh2 + ", donViTinh3=" + donViTinh3 + ", soLuongDVT1="
				+ soLuongDVT1 + ", soLuongDVT2=" + soLuongDVT2 + ", soLuongDVT3=" + soLuongDVT3 + ", giaBanDonViTinh1="
				+ giaBanDonViTinh1 + ", giaBanDonViTinh2=" + giaBanDonViTinh2 + ", giaBanDonViTinh3=" + giaBanDonViTinh3
				+ ", giaTriQuyDoi2=" + giaTriQuyDoi2 + ", giaTriQuyDoi3=" + giaTriQuyDoi3 + "]";
	}


	

	
}
