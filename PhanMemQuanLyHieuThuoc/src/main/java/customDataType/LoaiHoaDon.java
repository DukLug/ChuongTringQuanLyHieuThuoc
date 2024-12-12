package customDataType;

public enum LoaiHoaDon {
	KhongKeDon("Không kê đơn"),
    KeDon("Kê đơn"),
    ThuocDacBiet("Thuốc đặc biệt");
	
	private String loaiHoaDon;

	LoaiHoaDon(String loaiHoaDon) {
		this.loaiHoaDon = loaiHoaDon;
	}

	public String getLoaiHoaDon() {
		return loaiHoaDon;
	}

	@Override
    public String toString() {
        return this.loaiHoaDon;
    }
	
	public static LoaiHoaDon fromString(String loaiHoaDon) {
        for (LoaiHoaDon loaiHD : LoaiHoaDon.values()) {
            if (loaiHD.getLoaiHoaDon().equals(loaiHoaDon)) {
                return loaiHD;
            }
        }
        throw new IllegalArgumentException("No enum constant with loaiHoaDon: " + loaiHoaDon);
    }
	
}