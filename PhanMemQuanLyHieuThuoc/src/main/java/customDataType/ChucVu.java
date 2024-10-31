package customDataType;

public enum ChucVu {
	KhongCoDuLieu("Không có dữ liệu"),
    NhanVienBanHang("Nhân viên bán hàng"),
    ChuCuaHang("Chủ cửa hàng");

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

    public static ChucVu fromString(String chucVu) {
        for (ChucVu cv : ChucVu.values()) {
            if (cv.getChucVu().equals(chucVu)) {
                return cv;
            }
        }
        throw new IllegalArgumentException("No enum constant with chucVu: " + chucVu);
    }
}
