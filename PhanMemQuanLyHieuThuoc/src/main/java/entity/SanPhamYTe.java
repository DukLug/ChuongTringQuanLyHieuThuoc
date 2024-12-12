package entity;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import customDataType.DonViTinh;
import customDataType.TrangThaiSanPham;
import functionalClass.ImageLoader;


public class SanPhamYTe {
	private String maSanPham;
	private String tenSanPham;

	private String nuocSanXuat;
	private TrangThaiSanPham trangThaiSanPham;
	private String ghiChu;
	private String moTa;
	private String dangBaoChe;
	private double thue;
	private String thanhPhan;
	
	private DonViTinh donViTinh1;
	private DonViTinh donViTinh2;
	private DonViTinh donViTinh3;
	
	private BigDecimal giaBanDonViTinh1;
	private BigDecimal giaBanDonViTinh2;
	private BigDecimal giaBanDonViTinh3;
	
	private int giaTriQuyDoi2;
	private int giaTriQuyDoi3;
	
	private NhaCungCap nhaCungCap;
	private LoaiSanPham loaiSanPham;
	private String maVach;
	private String yeuCauKeDon;
	private BufferedImage hinhAnh;
	private ArrayList<LoHang> loHang;

	public SanPhamYTe(String maSanPham) {
		super();
		this.maSanPham = maSanPham;
	}
	

	public SanPhamYTe(
			String maSanPham,
			String tenSanPham,

			String nuocSanXuat,
			TrangThaiSanPham trangThaiSanPham,
			String ghiChu,
			String moTa,
			String dangBaoChe,
			double thue,
			String thanhPhan,
			
			DonViTinh donViTinh1,
			DonViTinh donViTinh2,
			DonViTinh donViTinh3,
			
			BigDecimal giaBanDonViTinh1,
			BigDecimal giaBanDonViTinh2,
			BigDecimal giaBanDonViTinh3,
			
			int giaTriQuyDoi2,
			int giaTriQuyDoi3,
			
			NhaCungCap nhaCungCap,
			LoaiSanPham loaiSanPham,
			String maVach,
			String yeuCauKeDon,
			BufferedImage hinhAnh,
			ArrayList<LoHang> loHang
			) {
		super();
		this.maSanPham = maSanPham;
		setTenSanPham(tenSanPham);
		setNuocSanXuat(nuocSanXuat);
		setTrangThaiSanPham(trangThaiSanPham);
		setGhiChu(ghiChu);
		setMoTa(moTa);
		setDangBaoChe(dangBaoChe);
		setThue(thue);
		setThanhPhan(thanhPhan);

		setDonViTinh1(donViTinh1);
		setDonViTinh2(donViTinh2);
		setDonViTinh3(donViTinh3);
		
		setGiaBanDonViTinh1(giaBanDonViTinh1);
		setGiaBanDonViTinh2(giaBanDonViTinh2);
		setGiaBanDonViTinh3(giaBanDonViTinh3);
		
		setGiaTriQuyDoi2(giaTriQuyDoi2);
		setGiaTriQuyDoi3(giaTriQuyDoi3);
		
		setNhaCungCap(nhaCungCap);	
		setLoaiSanPham(loaiSanPham);
		setMaVach(maVach);
		setYeuCauKeDon(yeuCauKeDon);
		setHinhAnh(null);
		setLoHang(new ArrayList<LoHang>());
		
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


	public String getNuocSanXuat() {
		return nuocSanXuat;
	}


	public TrangThaiSanPham getTrangThaiSanPham() {
		return trangThaiSanPham;
	}


	public String getGhiChu() {
		return ghiChu;
	}


	public String getMoTa() {
		return moTa;
	}


	public String getDangBaoChe() {
		return dangBaoChe;
	}


	public double getThue() {
		return thue;
	}


	public String getThanhPhan() {
		return thanhPhan;
	}


	public DonViTinh getDonViTinh1() {
		return donViTinh1;
	}


	public DonViTinh getDonViTinh2() {
		return donViTinh2;
	}


	public DonViTinh getDonViTinh3() {
		return donViTinh3;
	}


	public BigDecimal getGiaBanDonViTinh1() {
		return giaBanDonViTinh1;
	}


	public BigDecimal getGiaBanDonViTinh2() {
		return giaBanDonViTinh2;
	}


	public BigDecimal getGiaBanDonViTinh3() {
		return giaBanDonViTinh3;
	}


	public int getGiaTriQuyDoi2() {
		return giaTriQuyDoi2;
	}


	public int getGiaTriQuyDoi3() {
		return giaTriQuyDoi3;
	}


	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}


	public LoaiSanPham getLoaiSanPham() {
		return loaiSanPham;
	}


	public String getMaVach() {
		return maVach;
	}


	public String getYeuCauKeDon() {
		return yeuCauKeDon;
	}


	public BufferedImage getHinhAnh() {
		return hinhAnh;
	}


	public ArrayList<LoHang> getLoHang() {
		return loHang;
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


	public void setTrangThaiSanPham(TrangThaiSanPham trangThaiSanPham) {
		this.trangThaiSanPham = trangThaiSanPham;
	}


	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}


	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}


	public void setDangBaoChe(String dangBaoChe) {
		this.dangBaoChe = dangBaoChe;
	}


	public void setThue(double thue) {
		if(thue < 0) {
			throw new IllegalArgumentException("Thuế không được nhỏ hơn 0");
		}

		thue = thue;
	}


	public void setThanhPhan(String thanhPhan) {
		this.thanhPhan = thanhPhan;
	}


	public void setDonViTinh1(DonViTinh donViTinh1) {
		this.donViTinh1 = donViTinh1;
	}


	public void setDonViTinh2(DonViTinh donViTinh2) {
		this.donViTinh2 = donViTinh2;
	}


	public void setDonViTinh3(DonViTinh donViTinh3) {
		this.donViTinh3 = donViTinh3;
	}


	public void setGiaBanDonViTinh1(BigDecimal giaBanDonViTinh1) {
		this.giaBanDonViTinh1 = giaBanDonViTinh1;
	}


	public void setGiaBanDonViTinh2(BigDecimal giaBanDonViTinh2) {
		this.giaBanDonViTinh2 = giaBanDonViTinh2;
	}


	public void setGiaBanDonViTinh3(BigDecimal giaBanDonViTinh3) {
		this.giaBanDonViTinh3 = giaBanDonViTinh3;
	}


	public void setGiaTriQuyDoi2(int giaTriQuyDoi2) {
		this.giaTriQuyDoi2 = giaTriQuyDoi2;
	}


	public void setGiaTriQuyDoi3(int giaTriQuyDoi3) {
		this.giaTriQuyDoi3 = giaTriQuyDoi3;
	}


	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}


	public void setLoaiSanPham(LoaiSanPham loaiSanPham) {
		this.loaiSanPham = loaiSanPham;
	}


	public void setMaVach(String maVach) {
		this.maVach = maVach;
	}


	public void setYeuCauKeDon(String yeuCauKeDon) {
		this.yeuCauKeDon = yeuCauKeDon;
	}


	public void setHinhAnh(BufferedImage hinhAnh) {
		this.hinhAnh = hinhAnh;
	}


	public void setLoHang(ArrayList<LoHang> loHang) {
		this.loHang = loHang;
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
		return "SanPhamYTe [maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", nuocSanXuat=" + nuocSanXuat
				+ ", trangThaiSanPham=" + trangThaiSanPham + ", ghiChu=" + ghiChu + ", moTa=" + moTa + ", dangBaoChe="
				+ dangBaoChe + ", thue=" + thue + ", thanhPhan=" + thanhPhan + ", donViTinh1=" + donViTinh1
				+ ", donViTinh2=" + donViTinh2 + ", donViTinh3=" + donViTinh3 + ", giaBanDonViTinh1=" + giaBanDonViTinh1
				+ ", giaBanDonViTinh2=" + giaBanDonViTinh2 + ", giaBanDonViTinh3=" + giaBanDonViTinh3
				+ ", giaTriQuyDoi2=" + giaTriQuyDoi2 + ", giaTriQuyDoi3=" + giaTriQuyDoi3 + ", nhaCungCap=" + nhaCungCap
				+ ", loaiSanPham=" + loaiSanPham + ", maVach=" + maVach + ", yeuCauKeDon=" + yeuCauKeDon + ", hinhAnh="
				+ hinhAnh + ", loHang=" + loHang + "]";
	}


	

	
}
