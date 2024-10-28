package customDataType;

public enum TrangThaiLamViec {
	DangLam("Đang làm"),DaNghiViec("Đã nghỉ việc");
	
	private String trangThai;
	
	
	TrangThaiLamViec(String trangThai) {
			this.trangThai = trangThai; 
	}
	public String getTrangThai() {
		return trangThai;
	}		
	@Override
	public String toString() {
		return this.trangThai;
	}
}
