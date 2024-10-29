package customDataType;

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
	 public static GioiTinh fromString(String gioiTinh) {
	        for (GioiTinh gt : GioiTinh.values()) {
	            if (gt.getGioiTinh().equals(gioiTinh)) {
	                return gt;
	            }
	        }
	        throw new IllegalArgumentException("No enum constant with gioiTinh: " + gioiTinh);
	    }
	

}
