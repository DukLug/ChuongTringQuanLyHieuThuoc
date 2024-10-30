package entity;

import java.util.Objects;

public class LoaiSanPham {
    private String maLoai;
    private String tenLoai;
    private int soLuongToiDa;

    public LoaiSanPham(String maLoai) {
        super();
        this.maLoai = maLoai;
    }

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	public int getSoLuongToiDa() {
		return soLuongToiDa;
	}

	public void setSoLuongToiDa(int soLuongToiDa) {
		this.soLuongToiDa = soLuongToiDa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLoai);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiSanPham other = (LoaiSanPham) obj;
		return Objects.equals(maLoai, other.maLoai);
	}

	@Override
	public String toString() {
		return "LoaiSanPham [maLoai=" + maLoai + ", tenLoai=" + tenLoai + ", soLuongToiDa=" + soLuongToiDa + "]";
	}


}
