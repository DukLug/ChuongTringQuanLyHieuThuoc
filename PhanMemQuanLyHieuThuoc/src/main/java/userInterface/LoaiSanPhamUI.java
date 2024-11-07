package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import component.CustomTable;
import controller.LoaiSanPhamCTR;
import dao.LoaiSanPhamDAO;
import dao.SanPhamYTeDAO;
import entity.LoaiSanPham;
import entity.SanPhamYTe;

public class LoaiSanPhamUI extends JPanel{
	
	//data
	private ArrayList<LoaiSanPham> dsLoaiSanPham = LoaiSanPhamCTR.layDanhSachLoaiSanPham();
	private String[][] duLieuBang = new String[][] {};
	private String[] tenCot = new String[] {"Mã loại", "Tên loại"};
	
	private CustomTable bangLoaiSP;
	
	public LoaiSanPhamUI() {
		super();
		taoHinh();
		
		capNhatDuLieu();
	}
	
	public void taoHinh() {
		setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
		this.setBackground(Color.gray);
		this.setLayout(new BorderLayout());
		this.add(new JLabel("LoaiSanPhamUI"), BorderLayout.NORTH);
		
		bangLoaiSP = new CustomTable(duLieuBang, tenCot, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle, 20);
		this.add(bangLoaiSP, BorderLayout.CENTER);
	}
	
	private void capNhatDuLieu() {
        // Convert ArrayList to Object[][]
        Object[][] data = new Object[dsLoaiSanPham.size()][2];
        for (int i = 0; i < dsLoaiSanPham.size(); i++) {
            LoaiSanPham loai = dsLoaiSanPham.get(i);
            data[i][0] = loai.getMaLoai();
            data[i][1] = loai.getTenLoai();
        }
        bangLoaiSP.capNhatDuLieu(data);
	}
}