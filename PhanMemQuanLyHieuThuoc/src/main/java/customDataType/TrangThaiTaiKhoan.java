package customDataType;

public enum TrangThaiTaiKhoan {
	KhongCoDuLieu("Không có dữ liệu"),
	HoatDong("Hoạt động"),
	NgungHoatDong("Ngưng hoạt động");
	
	private String trangThaiTaiKhoan;

	private TrangThaiTaiKhoan(String trangThaiTaiKhoan) {
		this.trangThaiTaiKhoan = trangThaiTaiKhoan;
	}

	public String getTrangThaiTaiKhoan() {
		return trangThaiTaiKhoan;
	}
	
	@Override
	public String toString() {
		return this.trangThaiTaiKhoan;
	}
	
	public static TrangThaiTaiKhoan fromString(String trangThaiTaiKhoan) {
		for (TrangThaiTaiKhoan tttk : TrangThaiTaiKhoan.values()) {
			if (tttk.getTrangThaiTaiKhoan().equals(trangThaiTaiKhoan))
				return tttk;
		}
		throw new IllegalArgumentException("No enum constant with trangThaiTaiKhoan: " + trangThaiTaiKhoan);
	}
}
