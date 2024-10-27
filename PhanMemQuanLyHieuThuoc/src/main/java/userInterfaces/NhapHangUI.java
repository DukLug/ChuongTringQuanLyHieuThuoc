package userInterfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.math.BigDecimal;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import component.CustomItemList;
import enity.DonViTinh;
import entity.TestNguoi;
import entity.Thuoc;

public class NhapHangUI extends JPanel {
	public NhapHangUI() {
		super();
		taoHinh();
	}
	
	public void taoHinh() {
		setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
		this.setBackground(Color.white);
		this.add(new JLabel("NhapHangUI"));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		CustomItemList nhapHangList = new CustomItemList(1500, 500, 100, new int[] {500, 500, 500}, Color.blue, 50, new String[] {"HI","Hello","A"}, UIStyles.defaultFont);
		nhapHangList.addItem(new NhapHangRow(1,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop)));
		nhapHangList.addItem(new NhapHangRow(2,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop)));
		nhapHangList.addItem(new NhapHangRow(3,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop)));
		nhapHangList.addItem(new NhapHangRow(4,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop)));
		nhapHangList.addItem(new NhapHangRow(5,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop)));
		nhapHangList.addItem(new NhapHangRow(6,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop)));
		nhapHangList.addItem(new NhapHangRow(7,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop)));
		nhapHangList.addItem(new NhapHangRow(8,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop)));
		
		nhapHangList.addItem(new NhapHangRow(9,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop)));
		nhapHangList.addItem(new NhapHangRow(10,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop)));
		nhapHangList.addItem(new NhapHangRow(11,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop)));
		nhapHangList.addItem(new NhapHangRow(12,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop)));
		nhapHangList.addItem(new NhapHangRow(13,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop)));
		nhapHangList.addItem(new NhapHangRow(14,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop)));
		nhapHangList.addItem(new NhapHangRow(15,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop)));
		add(nhapHangList);
	}
	
	public static class NhapHangRow extends JPanel{
		//style
		private static Dimension prefSize = new Dimension(1500, 100);
		private static Font font = UIStyles.defaultFont;
		private static Color backgroundColor = Color.white;
		private static Border border = BorderFactory.createLineBorder(Color.darkGray);
		private static int[] columnWidths = new int[] {500, 500, 500};
		
		//du lieu thuoc
		private String maHang;
		private String tenHang;
		private DonViTinh donViTinh;
		
		//du lieu nhap hang
		public Thuoc thuoc;	
		public int soLuong;
		public BigDecimal donGia;
		public double tiLeThue;
		public BigDecimal thanhTien;
			
		public NhapHangRow(int stt, Thuoc thuoc) {
			super();
			//set data
			if(thuoc == null) {
				TrangChuUI.hienLoi(getClass(), new Exception("Du lieu thuoc la null"));
			}
			this.thuoc = thuoc;
			
			maHang = thuoc.maThuoc;
			tenHang = thuoc.tenThuoc;
			donViTinh = thuoc.donViTinh;
			
			//set style
			//setLayout(new BoxLayout())
			this.setPreferredSize(prefSize);
			this.setMaximumSize(prefSize);
			this.setMinimumSize(prefSize);
			this.setBackground(backgroundColor);
			this.setBorder(border);
			this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			
			//add columns
			//columns
			JPanel sttColumn = new JPanel();
			sttColumn.setPreferredSize(new Dimension(columnWidths[0], prefSize.height));
			sttColumn.setMaximumSize(new Dimension(columnWidths[0], prefSize.height));
			JLabel sttLabel = new JLabel(String.valueOf(stt));
			sttLabel.setFont(font);
			sttColumn.add(sttLabel);
			
			add(sttColumn);
				
		}
		
		
	}
}
