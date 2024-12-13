package userInterface;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import component.CustomTable;
import controller.ChiTietHoaDonCTR;
import controller.HoaDonCTR;
import controller.KhoCTR;
import controller.NhanVienCTR;
import controller.SanPhamCTR;
import entity.ChiTietHoaDon;
import entity.DonNhapHang;
import entity.HoaDon;



public class HoaDonUI extends JPanel  {

	private ArrayList<HoaDon> danhSachHoaDon = HoaDonCTR.layDanhSachTatCaHoaDon();
	private ArrayList<ChiTietHoaDon> danhSachChiTietHoaDon = ChiTietHoaDonCTR.layDSCTHDTheoMa(danhSachHoaDon.get(0).getMaHoaDon());
	
	private String[][] duLieuBangHoaDon = new String[][] {};
	private String[][] duLieuBangCTHD = new String[][] {};
	
	private String[] tenCotBangHoaDon = new String[] {"Mã HĐ", "Ngày tạo", "Điểm TL", "Thành tiền", "Mã NV", "Mã KM", "Mã KH", "Loại hóa đơn"};
	private String[] tenCotBangCTHD = new String[] {"Mã CTHĐ", "Số lượng DVT1", "Số lượng DVT2" , "Số lượng DVT3","Tổng tiền", "Mã hóa đơn","Mã sản phẩm", "Mã lô", "Mã lô 2"};		

	private CustomTable bangHoaDon;
	private CustomTable bangChiTietHoaDon;
	
	public HoaDonUI() {
        super();
        taoHinh();
        chuanBiDuLieu();
        bangHoaDon.capNhatDuLieu(duLieuBangHoaDon);
    }



	private void taoHinh() {
        setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
        this.setBackground(UIStyles.BackgroundColor);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel title = new JLabel("Hóa Đơn");
        title.setFont(UIStyles.TitleFont);
        titlePanel.add(title, BorderLayout.CENTER);

//        JPanel panelLoc = new JPanel();
        JPanel panelLoc = taoSearchPanel();
        JPanel panelHoaDon = new JPanel();
        panelHoaDon.setBorder(new TitledBorder(
                new LineBorder(Color.BLACK, 1), 
                "Danh sách hóa đơn", 
                TitledBorder.LEFT, 
                TitledBorder.TOP, 
                new Font("Tahoma", Font.PLAIN, 20), 
                Color.BLACK
            ));
        panelHoaDon.setPreferredSize(new Dimension(1300, 460));
        panelHoaDon.setMaximumSize(new Dimension(1300,460));

        // Assuming 'duLieuBangHoaDon' and 'tenCotBangHoaDon' are defined elsewhere
        bangHoaDon = new CustomTable(duLieuBangHoaDon, tenCotBangHoaDon, 
                UIStyles.NhanVienTableHeaderStyle, 
                UIStyles.NhanVienTableRowStyle, 
                20, 
                new int[] {250, 150, 100, 150, 150, 150, 150, 150});
        JScrollPane scrollPane = new JScrollPane(bangHoaDon);
        scrollPane.setPreferredSize(new Dimension(1300, 270));

        bangHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                capNhatDuLieuCTHD(danhSachHoaDon.get(bangHoaDon.getSelectedRow()).getMaHoaDon());
            }
        });

        panelHoaDon.add(scrollPane);        
       
        
        JPanel panelCTHD = new JPanel();
        panelCTHD.setPreferredSize(new Dimension(1800, 500));
        panelCTHD.setMinimumSize(new Dimension(1800, 500));
        panelCTHD.setBorder(new TitledBorder(
                new LineBorder(Color.BLACK, 1), 
                "Chi Tiết hóa đơn", 
                TitledBorder.LEFT, 
                TitledBorder.TOP, 
                new Font("Tahoma", Font.PLAIN, 20), 
                Color.BLACK
            ));

        // Assuming 'duLieuBangCTHD' and 'tenCotCTHD' are defined elsewhere
        bangChiTietHoaDon = new CustomTable(duLieuBangCTHD, tenCotBangCTHD, 
                UIStyles.NhanVienTableHeaderStyle, 
                UIStyles.NhanVienTableRowStyle, 
                20, 
                new int[] {250, 180, 180, 180, 200, 250, 180, 180, 150});

        JScrollPane scrollPaneCTHD = new JScrollPane(bangChiTietHoaDon);
        scrollPaneCTHD.setPreferredSize(new Dimension(1800, 430));

        panelCTHD.add(scrollPaneCTHD);
        
        this.add(panelLoc, BorderLayout.WEST);
        this.add(panelHoaDon, BorderLayout.CENTER);
        this.add(panelCTHD, BorderLayout.SOUTH);
	}
	
		private void capNhatDuLieuCTHD(String maHoaDon) {
			danhSachChiTietHoaDon = ChiTietHoaDonCTR.layDSCTHDTheoMa(maHoaDon);
            duLieuBangCTHD = new String[danhSachChiTietHoaDon.size()][tenCotBangCTHD.length];
            for (int i = 0; i < danhSachChiTietHoaDon.size(); i++) {
            	duLieuBangCTHD[i] = new String[] {
            			String.valueOf(danhSachChiTietHoaDon.get(i).getMaChiTietHoaDon()),
            			String.valueOf(danhSachChiTietHoaDon.get(i).getSoLuongDonViTinh1()),
            			String.valueOf(danhSachChiTietHoaDon.get(i).getSoLuongDonViTinh2()),
            			String.valueOf(danhSachChiTietHoaDon.get(i).getSoLuongDonViTinh3()),
            			String.valueOf(danhSachChiTietHoaDon.get(i).getTongTien()),
            			String.valueOf(danhSachChiTietHoaDon.get(i).getHoaDon().getMaHoaDon()),
            			danhSachChiTietHoaDon.get(i).getSanPhamYTe().getMaSanPham(),
            			String.valueOf(danhSachChiTietHoaDon.get(i).getLoHang()),
            			String.valueOf(danhSachChiTietHoaDon.get(i).getLoHangThayThe())
            		};
                  }
            
                 bangChiTietHoaDon.capNhatDuLieu(duLieuBangCTHD);		
	}

		private void chuanBiDuLieu() {
		duLieuBangHoaDon = new String[danhSachHoaDon.size()][tenCotBangHoaDon.length];
			for (int i = 0; i < danhSachHoaDon.size(); i++) {

				duLieuBangHoaDon[i] = new String[] {
					danhSachHoaDon.get(i).getMaHoaDon(),
					danhSachHoaDon.get(i).getNgayTao().toString(),
                    String.valueOf(danhSachHoaDon.get(i).getDiemSuDung()),
                    String.valueOf(danhSachHoaDon.get(i).getThanhTien()),
                    danhSachHoaDon.get(i).getNhanVien().getMaNhanVien(),
                    danhSachHoaDon.get(i).getKhuyenMai().getMaKhuyenMai(),
                    danhSachHoaDon.get(i).getKhachHang().getMaKhachHang(),
                    danhSachHoaDon.get(i).getLoaiHD().toString() 
                                                                               
				};
				bangHoaDon.capNhatDuLieu(duLieuBangHoaDon);
			}
		
	}
		private void tim(JDateChooser fromDateChooser, JDateChooser toDateChooser) {
	    	java.util.Date utilFromDate = fromDateChooser.getDate();
	        java.util.Date utilToDate = toDateChooser.getDate();
	        if (utilFromDate != null && utilToDate != null) {
	            Date fromDate = new Date(utilFromDate.getTime());
	            Date toDate = new Date(utilToDate.getTime());
	            capNhatDuLieuHoaDon(fromDate, toDate);
	            
	        }
	    }



		private void capNhatDuLieuHoaDon(Date fromDate, Date toDate) {
			ArrayList<HoaDon> filteredList = HoaDonCTR.layDanhSachDonNhapTheoNgay(fromDate, toDate);
			duLieuBangHoaDon = new String[filteredList.size()][tenCotBangHoaDon.length];
			for (int i = 0; i < filteredList.size(); i++) {
				duLieuBangHoaDon[i] = new String[] {
						danhSachHoaDon.get(i).getMaHoaDon(),
						danhSachHoaDon.get(i).getNgayTao().toString(),
                        String.valueOf(danhSachHoaDon.get(i).getDiemSuDung()),
                        String.valueOf(danhSachHoaDon.get(i).getThanhTien()),
                        danhSachHoaDon.get(i).getNhanVien().getMaNhanVien(),
                        danhSachHoaDon.get(i).getKhuyenMai().getMaKhuyenMai(),
                        danhSachHoaDon.get(i).getKhachHang().getMaKhachHang(),
                        danhSachHoaDon.get(i).getLoaiHD().toString()
                        
						
				};
				
			}
			bangHoaDon.capNhatDuLieu(duLieuBangHoaDon);
		}
		

		
		private JPanel taoSearchPanel() {
	        JPanel searchPanel = new JPanel();
	        searchPanel.setPreferredSize(new Dimension(320, 250));
	        searchPanel.setLayout(null);
	        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	        
	        JLabel lblFromDate = new JLabel("Từ ngày:");
	        lblFromDate.setBounds(10, 20, 100, 50);
	        lblFromDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        
	        searchPanel.add(lblFromDate);

	        JDateChooser fromDateChooser = new JDateChooser();
	        fromDateChooser.setBounds(110, 20, 200, 50);
	        fromDateChooser.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        searchPanel.add(fromDateChooser);

	        JLabel lblToDate = new JLabel("Đến ngày:");
	        lblToDate.setBounds(10, 80, 100, 50);
	        lblToDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        searchPanel.add(lblToDate);

	        JDateChooser toDateChooser = new JDateChooser();
	        toDateChooser.setBounds(110, 80, 200, 50);
	        toDateChooser.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        searchPanel.add(toDateChooser);

	        JButton btnSearch = new JButton("Tìm");
	        btnSearch.setBounds(70, 150, 100, 50);
	        btnSearch.setBackground(UIStyles.LabelFontColorGreen);
	        btnSearch.setFont(UIStyles.BoldFont);
	        btnSearch.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	tim(fromDateChooser, toDateChooser);
	            }
	        });
	        searchPanel.add(btnSearch);

	        return searchPanel;
	    }
}
