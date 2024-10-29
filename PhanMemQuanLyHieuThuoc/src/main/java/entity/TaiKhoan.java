package entity;

import customDataType.TrangThaiTaiKhoan;

public class TaiKhoan {

	private String maTaiKhoan;
	private String tenDangNhap;
	private String matKhau;
	private TrangThaiTaiKhoan trangThaiTaiKhoan;
	
	public TaiKhoan() {
		super();
	}

	public TaiKhoan(String maTaiKhoan, String tenDangNhap, String matKhau, TrangThaiTaiKhoan trangThaiTaiKhoan) {
		super();
		this.maTaiKhoan = maTaiKhoan;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.trangThaiTaiKhoan = trangThaiTaiKhoan;
	}

	public TaiKhoan(String maTaiKhoan) {
		super();
		this.maTaiKhoan = maTaiKhoan;
	}

	public String getMaTaiKhoan() {
		return maTaiKhoan;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}
	
	public String getMatKhau() {
		return matKhau;
	}

	public TrangThaiTaiKhoan getTrangThaiTaiKhoan() {
		return trangThaiTaiKhoan;
	}

	public void setTenDangNhap(String tenDangNhap) {
		if (tenDangNhap == null)
			throw new  IllegalArgumentException("Tên đăng nhập không được null");
		if (tenDangNhap.isEmpty())
			throw new  IllegalArgumentException("Tên đăng nhập không được rỗng");
		if (tenDangNhap.length() < 1 || tenDangNhap.length() > 50)
			throw new  IllegalArgumentException("Tên đăng nhập tối thiểu 1 ký tự và tối đa 50 ký tự");
		
		this.tenDangNhap = tenDangNhap;
	}

	public void setMatKhau(String matKhau) {
		if (matKhau == null)
			throw new  IllegalArgumentException("Mật khẩu không được null");
		if (matKhau.isEmpty())
			throw new  IllegalArgumentException("Mật khẩu không được rỗng");
		if (matKhau.matches("^[a-zA-Z]+@[0-9]{4}$") == false)
			throw new  IllegalArgumentException("Sai mật khẩu");
		
		this.matKhau = matKhau;
	}

	public void setTrangThaiTaiKhoan(TrangThaiTaiKhoan trangThaiTaiKhoan) {
		this.trangThaiTaiKhoan = trangThaiTaiKhoan;
	}

	@Override
	public String toString() {
		return "TaiKhoan [maTaiKhoan=" + maTaiKhoan + ", tenDangNhap=" + tenDangNhap + ", matKhau=" + matKhau
				+ ", trangThaiTaiKhoan=" + trangThaiTaiKhoan + "]";
	}
	
	
	
}
