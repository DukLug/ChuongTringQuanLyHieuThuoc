package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import component.CustomButton;
import component.CustomTable;
import controller.SanPhamCTR;
import dao.SanPhamYTeDAO;
import component.CustomButton.CustomButtonIconSide;
import entity.SanPhamYTe;
import functionalClass.DataImporter;

public class TestSanPhamUI extends JPanel{
	
	private ArrayList<SanPhamYTe> danhSachSanPham = SanPhamCTR.layDanhSachTatCaSanPham();
	private String[][] duLieuBang = new String[][] {};
	private String[] tenCot = new String[] {"Mã vạch", "Tên", "Nước sản xuất", "Giá"};
	
	private CustomTable bangSanPham;
	
	public TestSanPhamUI() {		
		super();
			
		taoHinh();
		chuanBiDuLieu();
		bangSanPham.capNhatDuLieu(duLieuBang);
	}
	
	public void taoHinh() {
		setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
		this.setBackground(Color.gray);
		this.add(new JLabel("San pham"));
		
		// Create custom table
		bangSanPham = new CustomTable(duLieuBang, tenCot, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle, 20,
        		new int[] {300, 800, 500, 200}
        		);
       
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(bangSanPham);
        scrollPane.setPreferredSize(new Dimension(1800, 600));
        add(scrollPane);
        JScrollBar sb = scrollPane.getVerticalScrollBar();
        add(new CustomButton("Them san pham", UIStyles.LabelBarButtonStyle, UIStyles.HelpIcon, CustomButtonIconSide.LEFT, ()->themSanPham()));
	      
	}
	private void chuanBiDuLieu() {
		tenCot = new String[] {"Mã vạch", "Tên", "Nước sản xuất", "Giá"};
		duLieuBang = new String[danhSachSanPham.size()][tenCot.length];
		for(int i = 0; i < danhSachSanPham.size(); i++) {
			duLieuBang[i] = new String[] {
				danhSachSanPham.get(i).getMaVach(),
				danhSachSanPham.get(i).getTenSanPham(),
				danhSachSanPham.get(i).getNuocSanXuat(),
				danhSachSanPham.get(i).getGiaBanDonViTinh1().toString() + "đ",
			};
		}
		for(SanPhamYTe sp : danhSachSanPham) {
			
		}
		System.out.println(danhSachSanPham.getFirst().toString());
	}
	private void themSanPham() {
//		danhSachSanPham = DataImporter.importDataFromXLSX();
//		chuanBiDuLieu();
//		bangSanPham.capNhatDuLieu(duLieuBang);
	}
}