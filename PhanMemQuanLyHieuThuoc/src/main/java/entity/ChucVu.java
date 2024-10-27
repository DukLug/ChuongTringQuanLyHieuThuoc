package entity;

public enum ChucVu {
	NhanVienBanHang("Nhân viên bán hàng"),ChuCuaHang("Chủ cửa hàng");
	private String chucVu;

	 ChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public String getChucVu() {
		return chucVu;
	}

	@Override
	public String toString() {
		return this.chucVu;
	}
}
