package entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.Objects;

import customDataType.ChucVu;
import customDataType.GioiTinh;
import customDataType.TrangThaiLamViec;

public class NhanVien {
	private String maNhanVien;
	private String hoTen;
	private String sdt;
	private String cccd;
	private Date ngaySinh;
	private TrangThaiLamViec trangThaiLamViec;
	private ChucVu chucVu;
	private GioiTinh gioiTinh;
	
	public NhanVien(String maNhanVien) {
		super();
		this.maNhanVien = maNhanVien;
	}

	public NhanVien(String maNhanVien, String hoTen, String sdt, String cccd, Date ngaySinh,
			TrangThaiLamViec trangThaiLamViec, ChucVu chucVu, GioiTinh gioiTinh) {
		super();
		this.maNhanVien = maNhanVien;
		setHoTen(hoTen);
		setSdt(sdt);
		setCccd(cccd);
		setNgaySinh(ngaySinh);
		setTrangThaiLamViec(trangThaiLamViec);
		setChucVu(chucVu);
		setGioiTinh(gioiTinh);
	}

	

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public String getHoTen() {
		return hoTen;
	}

	public String getSdt() {
		return sdt;
	}

	public String getCccd() {
		return cccd;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public TrangThaiLamViec getTrangThaiLamViec() {
		return trangThaiLamViec;
	}

	public ChucVu getChucVu() {
		return chucVu;
	}

	public GioiTinh getGioiTinh() {
		return gioiTinh;
	}

	public void setHoTen(String hoTen) {
		if(hoTen == null) 
			throw new IllegalArgumentException("Họ tên không được null");
		if(hoTen.isEmpty()) 
			throw new IllegalArgumentException("Họ tên không được rỗng");
		if(hoTen.length() > 50) 
			throw new IllegalArgumentException("Họ tên không được dài quá 50 Ký tự");
		
		this.hoTen = hoTen;
	}

	public void setSdt(String sdt) {
		if(sdt == null) 
			throw new IllegalArgumentException("Số điện thoại không được null");
		if(sdt.isEmpty()) 
			throw new IllegalArgumentException("Số điện thoại không được rỗng");
		if(sdt.length() > 10) 
			throw new IllegalArgumentException("Số điện thoại không được dài quá 10 Ký tự");
		
		this.sdt = sdt;
	}

	public void setCccd(String cccd) {
		if(cccd == null) 
			throw new IllegalArgumentException("Căn cước công dân không được null");
		if(cccd.isEmpty()) 
			throw new IllegalArgumentException("Căn cước công dân không được rỗng");
		if(cccd.length() > 20) 
			throw new IllegalArgumentException("Căn cước công dân không được dài quá 20 Ký tự");		
		
		this.cccd = cccd;
	}

	public void setNgaySinh(Date ngaySinh) {
		if(ngaySinh == null) 
			throw new IllegalArgumentException("Ngày sinh không được null");
		if(!ngaySinh.before(Date.valueOf(LocalDate.now()))) 
			throw new IllegalArgumentException("Ngày sinh phải trước ngày hiện tại");
		LocalDate birthDate = ngaySinh.toLocalDate();
	    int age = Period.between(birthDate, LocalDate.now()).getYears();
	    if (age < 18) 
	        throw new IllegalArgumentException("Tuổi phải từ 18 trở lên");	
		
		this.ngaySinh = ngaySinh;
	}

	public void setTrangThaiLamViec(TrangThaiLamViec trangThaiLamViec) {
		this.trangThaiLamViec = trangThaiLamViec;
	}

	public void setChucVu(ChucVu chucVu) {
		this.chucVu = chucVu;
	}

	public void setGioiTinh(GioiTinh gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNhanVien, other.maNhanVien);
	}

	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", hoTen=" + hoTen + ", sdt=" + sdt + ", cccd=" + cccd
				+ ", ngaySinh=" + ngaySinh + ", trangThaiLamViec=" + trangThaiLamViec + ", chucVu=" + chucVu
				+ ", gioiTinh=" + gioiTinh + "]";
	}
	
}
