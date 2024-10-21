package entity;

import java.math.BigDecimal;

import customEnum.TrangThaiLamViec;

public class TestNguoi {
	private String id;
	private int num;
	private BigDecimal money;
	private TrangThaiLamViec trangThai;
	
	public TestNguoi(String id, int num, BigDecimal money, TrangThaiLamViec trangThai) {
		this.id = id;
		this.num = num;
		this.money = money;
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "TestNguoi [id=" + id + ", num=" + num + ", money=" + money + ", trangThai=" + trangThai + "]";
	}
	
	
}
