package customDataType;

public enum DonViTinh {
	//KhongCoDuLieu("Không có dữ liệu"),
	Hop("Hộp"), Vien("Viên"), Vi("Vỉ");
	//Chai("Chai"), Goi("Gói"), Cai("Cái"), 
	private String donViTinh;

	DonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public String toString() {
		return this.donViTinh;
	}

	public static DonViTinh fromString(String donViTinh) {
		for (DonViTinh dvt : DonViTinh.values()) {
			if (dvt.donViTinh.equalsIgnoreCase(donViTinh)) {
				return dvt;
			}
		}
		return DonViTinh.Hop;
		//throw new IllegalArgumentException("Đơn vị tính không hợp lệ");
	}
}
