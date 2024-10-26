package entity;

public enum GioiTinh {
	Nam("Nam"),Nu("Nữ");
	private String gioiTinh;

	GioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}


	@Override
	public String toString() {
		return this.gioiTinh;
	}
	

}
