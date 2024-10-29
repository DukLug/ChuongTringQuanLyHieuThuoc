package customDataType;

public enum TrangThaiLamViec {
	DangLam("Đang làm"),
    DaNghiViec("Đã nghỉ việc");

    private String trangThaiLamViec;

    TrangThaiLamViec(String trangThaiLamViec) {
        this.trangThaiLamViec = trangThaiLamViec;
    }

    public String getTrangThaiLamViec() {
        return trangThaiLamViec;
    }

    @Override
    public String toString() {
        return this.trangThaiLamViec;
    }

    public static TrangThaiLamViec fromString(String trangThaiLamViec) {
        for (TrangThaiLamViec ttlv: TrangThaiLamViec.values()) {
            if (ttlv.getTrangThaiLamViec().equals(trangThaiLamViec)) {
                return ttlv;
            }
        }
        throw new IllegalArgumentException("No enum constant with trangThaiLamViec: " + trangThaiLamViec);
    }

}
