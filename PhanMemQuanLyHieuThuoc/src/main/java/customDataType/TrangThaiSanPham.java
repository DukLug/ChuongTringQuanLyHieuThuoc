package customDataType;

public enum TrangThaiSanPham {
	KhongCoDuLieu("Không có dữ liệu"),
    DangBan("Đang bán"),
    NgungBan("Ngừng bán"),
    HetHang("Hết hàng");
    
    private String trangThaiSanPham;

    TrangThaiSanPham(String trangThaiSanPham) {
        this.trangThaiSanPham = trangThaiSanPham;
    }
    public String getTrangThaiSanPham() {
        return trangThaiSanPham;
    }

    public String toString() {
        return this.trangThaiSanPham;
    }

    public static TrangThaiSanPham fromString(String trangThaiSanPham) {
        for (TrangThaiSanPham ttsp : TrangThaiSanPham.values()) {
            if (ttsp.trangThaiSanPham.equalsIgnoreCase(trangThaiSanPham)) {
                return ttsp;
            }
        }
        throw new IllegalArgumentException("Trạng thái sản phẩm không hợp lệ");
    }
}
