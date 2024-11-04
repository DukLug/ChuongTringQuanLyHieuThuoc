package entity;

import java.util.Objects;

public class NhaCungCap {

	private String maNhaCungCap;
	private String tenNhaCungCap;
	private String sdt;
	private String email;
	private String diaChi;
	
	public NhaCungCap() {
		
	}

	public NhaCungCap(String maNhaCungCap, String tenNhaCungCap, String sdt, String email, String diaChi) {
		super();
		this.maNhaCungCap = maNhaCungCap;
		setTenNhaCungCap(tenNhaCungCap);
		setSdt(sdt);
		setEmail(email);
		setDiaChi(diaChi);
	}

	public NhaCungCap(String maNhaCungCap) {
		super();
		this.maNhaCungCap = maNhaCungCap;
	}

	public String getMaNhaCungCap() {
		return maNhaCungCap;
	}

	public String getTenNhaCungCap() {
		return tenNhaCungCap;
	}

	public String getSdt() {
		return sdt;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getDiaChi() {
		return diaChi;
	}

	public void setTenNhaCungCap(String tenNhaCungCap) {
		if (tenNhaCungCap == null)
			throw new  IllegalArgumentException("Tên nhà cung cấp không được null");
		if (tenNhaCungCap.isEmpty())
			throw new  IllegalArgumentException("Tên nhà cung cấp không được rỗng");
		if (tenNhaCungCap.length() < 1 || tenNhaCungCap.length() > 50)
			throw new  IllegalArgumentException("Tên nhà cung cấp tối thiểu 1 ký tự và tối đa 50 ký tự");
		
		this.tenNhaCungCap = tenNhaCungCap;
	}

	public void setSdt(String sdt) {
		if (sdt == null)
			throw new  IllegalArgumentException("Số điện thoại không được null");
		if (sdt.isEmpty())
			throw new  IllegalArgumentException("Số điện thoại không được rỗng");
		if (sdt.length() < 10 || sdt.length() > 14)
			throw new  IllegalArgumentException("Số điện thoại phải từ 10 đến 14 ký tự số");

		
		this.sdt = sdt;
	}

	public void setEmail(String email) {
		if (email == null)
			throw new  IllegalArgumentException("Email không được null");
		if (email.isEmpty())
			throw new  IllegalArgumentException("Email không được rỗng");
		if (email.matches("^.+@.+\\..+$") == false)

			throw new  IllegalArgumentException("Sai email");
		
		this.email = email;
	}

	public void setDiaChi(String diaChi) {
		if (diaChi == null)
			throw new  IllegalArgumentException("Địa chỉ không được null");
		if (diaChi.isEmpty())
			throw new  IllegalArgumentException("Địa chỉ không được rỗng");
		if (diaChi.length() < 1 || diaChi.length() > 50)
			throw new  IllegalArgumentException("Địa chỉ tối thiểu 1 ký tự và tối đa 50 ký tự");
		
		this.diaChi = diaChi;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNhaCungCap.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		NhaCungCap other = (NhaCungCap) obj;
		return Objects.equals(maNhaCungCap.toLowerCase(), other.maNhaCungCap.toLowerCase());
	}

	@Override
	public String toString() {
		return "NhaCungCap [maNhaCungCap=" + maNhaCungCap + ", tenNhaCungCap=" + tenNhaCungCap + ", sdt=" + sdt
				+ ", email=" + email + ", diaChi=" + diaChi + "]";
	}

}
