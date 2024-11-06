package entity;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

import customDataType.DonViTinh;
import customDataType.TrangThaiSanPham;
import functionalClass.ImageLoader;


public class SanPhamYTe {
	private String maSanPham;
	private String tenSanPham;
	private Date ngaySanXuat;
	private Date hanSuDung;
	private String nuocSanXuat;
	private TrangThaiSanPham trangThaiSanPham;
	private String ghiChu;
	private String moTa;
	private double Thue;
	private BufferedImage hinhAnh;
	private String thanhPhan;
	private BigDecimal giaBan;
	private NhaCungCap nhaCungCap;
	private DonViTinh donViTinh;
	private LoaiSanPham loaiSanPham;
	private String maVach;
	private String yeuCauKeDon;
	private String dangBaoChe;
	private String nhaSanXuat;

	public SanPhamYTe(String maSanPham) {
		super();
		this.maSanPham = maSanPham;
	}
	
	

	public SanPhamYTe(String maSanPham, String tenSanPham, Date ngaySanXuat, Date hanSuDung, String nuocSanXuat,
			TrangThaiSanPham trangThaiSanPham, String ghiChu, String moTa, double thue, BufferedImage hinhAnh,
			String thanhPhan, BigDecimal giaBan, NhaCungCap nhaCungCap, DonViTinh donViTinh, LoaiSanPham loaiSanPham,
			String maVach, String yeuCauKeDon, String dangBaoChe, String nhaSanXuat) {
		super();
		this.maSanPham = maSanPham;
		setTenSanPham(tenSanPham);
		setNgaySanXuat(ngaySanXuat);
		setHanSuDung(hanSuDung);
		setNuocSanXuat(nuocSanXuat);
		setTrangThaiSanPham(trangThaiSanPham);
		setGhiChu(ghiChu);
		setMoTa(moTa);
		setThue(thue);
		this.hinhAnh = ImageLoader.taiHinhAnh(maVach);
		setThanhPhan(thanhPhan);
		setGiaBan(giaBan);
		setNhaCungCap(nhaCungCap);
		setDonViTinh(donViTinh);
		setLoaiSanPham(loaiSanPham);
		setMaVach(maVach);
		setYeuCauKeDon(yeuCauKeDon);
		setDangBaoChe(dangBaoChe);
		setNhaSanXuat(nhaSanXuat);
	}

	public SanPhamYTe(String maSanPham, String tenSanPham, Date ngaySanXuat, Date hanSuDung, String nuocSanXuat,
			TrangThaiSanPham trangThaiSanPham, String ghiChu, String moTa, double thue, String thanhPhan,
			BigDecimal giaBan, NhaCungCap nhaCungCap, DonViTinh donViTinh, LoaiSanPham loaiSanPham, String dangBaoChe) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.ngaySanXuat = ngaySanXuat;
		this.hanSuDung = hanSuDung;
		this.nuocSanXuat = nuocSanXuat;
		this.trangThaiSanPham = trangThaiSanPham;
		this.ghiChu = ghiChu;
		this.moTa = moTa;
		Thue = thue;
		this.thanhPhan = thanhPhan;
		this.giaBan = giaBan;
		this.nhaCungCap = nhaCungCap;
		this.donViTinh = donViTinh;
		this.loaiSanPham = loaiSanPham;
		this.dangBaoChe = dangBaoChe;
	}



	public SanPhamYTe(String maSanPham, String tenSanPham, BigDecimal giaBanSanPham, DonViTinh donViTinh) {
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.giaBan = giaBanSanPham;
		this.donViTinh = donViTinh;
	}


	


	public SanPhamYTe() {
		
	}



	public String getMaSanPham() {
		return maSanPham;
	}

	// public void setMaSanPham(String maSanPham) {
	// 	this.maSanPham = maSanPham;
	// }

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		if(tenSanPham == null) {
			throw new IllegalArgumentException("Tên sản phẩm không được để trống");
		}
		if(tenSanPham.isEmpty()) {
			throw new IllegalArgumentException("Tên sản phẩm không được để rỗng");
		}
		if(tenSanPham.length() > 200) {
			throw new IllegalArgumentException("Tên sản phẩm không được quá 200 ký tự");
		}
		this.tenSanPham = tenSanPham;
	}

	public Date getNgaySanXuat() {
		return ngaySanXuat;
	}

	public void setNgaySanXuat(Date ngaySanXuat) {
		if(ngaySanXuat == null) {
			throw new IllegalArgumentException("Ngày sản xuất không được để trống");
		}
		if(!ngaySanXuat.before(Date.valueOf(LocalDate
		.now()))) {
			throw new IllegalArgumentException("Ngày sản xuất không được lớn hơn ngày hiện tại");
		}
		// ngày sản xuất phải trước hạn sử dụng
		if(hanSuDung != null && ngaySanXuat.after(hanSuDung)) {
			throw new IllegalArgumentException("Ngày sản xuất phải trước hạn sử dụng");
		}
		this.ngaySanXuat = ngaySanXuat;
	}

	public Date getHanSuDung() {
		return hanSuDung;
	}

	public void setHanSuDung(Date hanSuDung) {
		if (hanSuDung == null) {
			throw new IllegalArgumentException("Hạn sử dụng không được để trống");
		}
		// hạn sử dụng phải sau ngày sản xuất, nếu ngày sản xuất đã được thiết lập
		if (ngaySanXuat != null && !hanSuDung.after(ngaySanXuat)) {
			throw new IllegalArgumentException("Hạn sử dụng phải sau ngày sản xuất");
		}
		this.hanSuDung = hanSuDung;
	}

	public String getNuocSanXuat() {
		return nuocSanXuat;
	}

	public void setNuocSanXuat(String nuocSanXuat) {
		if(nuocSanXuat == null) {
			throw new IllegalArgumentException("Nước sản xuất không được để trống");
		}
		if(nuocSanXuat.isEmpty()) {
			throw new IllegalArgumentException("Nước sản xuất không được để rỗng");
		}
		if(nuocSanXuat.length() > 200) {
			throw new IllegalArgumentException("Nước sản xuất không được quá 200 ký tự");
		}
		this.nuocSanXuat = nuocSanXuat;
	}

	public TrangThaiSanPham getTrangThaiSanPham() {
		return trangThaiSanPham;
	}

	public void setTrangThaiSanPham(TrangThaiSanPham trangThaiSanPham) {
		this.trangThaiSanPham = trangThaiSanPham;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public double getThue() {
		return Thue;
	}

	public void setThue(double thue) {
		if(thue < 0) {
			throw new IllegalArgumentException("Thuế không được nhỏ hơn 0");
		}

		Thue = thue;
	}

	public BufferedImage getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(BufferedImage hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public String getThanhPhan() {
		return thanhPhan;
	}

	public void setThanhPhan(String thanhPhan) {
		this.thanhPhan = thanhPhan;
	}

	public BigDecimal getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(BigDecimal giaBan) {
		 
		this.giaBan = giaBan;
	}

	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	public DonViTinh getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(DonViTinh donViTinh) {
		this.donViTinh = donViTinh;
	}

	public LoaiSanPham getLoaiSanPham() {
		return loaiSanPham;
	}

	public void setLoaiSanPham(LoaiSanPham loaiSanPham) {
		this.loaiSanPham = loaiSanPham;
	}

	public String getMaVach() {
		return maVach;
	}

	public void setMaVach(String maVach) {
		this.maVach = maVach;
	}

	public String getYeuCauKeDon() {
		return yeuCauKeDon;
	}

	public void setYeuCauKeDon(String yeuCauKeDon) {
		this.yeuCauKeDon = yeuCauKeDon;
	}

	public String getDangBaoChe() {
		return dangBaoChe;
	}

	public void setDangBaoChe(String dangBaoChe) {
		this.dangBaoChe = dangBaoChe;
	}

	public String getNhaSanXuat() {
		return nhaSanXuat;
	}

	public void setNhaSanXuat(String nhaSanXuat) {
		this.nhaSanXuat = nhaSanXuat;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maSanPham);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SanPhamYTe other = (SanPhamYTe) obj;
		return Objects.equals(maSanPham, other.maSanPham);
	}

	@Override
	public String toString() {
		return "SanPhamYTe [maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", ngaySanXuat=" + ngaySanXuat
				+ ", hanSuDung=" + hanSuDung + ", nuocSanXuat=" + nuocSanXuat + ", trangThaiSanPham=" + trangThaiSanPham
				+ ", ghiChu=" + ghiChu + ", moTa=" + moTa + ", Thue=" + Thue + ", hinhAnh=" + hinhAnh + ", thanhPhan="
				+ thanhPhan + ", giaBan=" + giaBan + ", nhaCungCap=" + nhaCungCap + ", donViTinh=" + donViTinh
				+ ", loaiSanPham=" + loaiSanPham + ", maVach=" + maVach + ", yeuCauKeDon=" + yeuCauKeDon
				+ ", dangBaoChe=" + dangBaoChe + ", nhaSanXuat=" + nhaSanXuat + "]";
	}
	
}
