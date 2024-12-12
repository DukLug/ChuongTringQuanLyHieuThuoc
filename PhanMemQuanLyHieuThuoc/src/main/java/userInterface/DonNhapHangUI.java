package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.toedter.calendar.JDateChooser;

import component.CustomButton;
import component.CustomTable;
import controller.KhoCTR;
import controller.NhanVienCTR;
import controller.SanPhamCTR;
import entity.ChiTietDonNhap;
import entity.DonNhapHang;

public class DonNhapHangUI extends JPanel {

    private ArrayList<DonNhapHang> danhSachDonNhap = KhoCTR.layDanhSachTatCaDonNhap();
    private ArrayList<ChiTietDonNhap> danhSachChiTietDonNhap = KhoCTR.layDanhSachChiTietDonNhap(danhSachDonNhap.get(0).getMaDonNhap());
    private String[][] duLieuBangDonNhap = new String[][] {};
    private String[][] duLieuBangCTDN = new String[][] {};
    private String[] tenCotBangDonNhap = new String[] {"Mã đơn nhập", "Ngày nhập", "Nhân viên nhập"};
    private String[] tenCotCTDN = new String[] {"Mã sản phẩm", "Số lượng DVT1", "Số lượng DVT2", "Số lượng DVT3", "Giá cơ bản"};

    private CustomTable bangDonNhap;
    private CustomTable bangChiTietDonNhap;

    public DonNhapHangUI() {
        super();
        taoHinh();
        chuanBiDuLieu();
        bangDonNhap.capNhatDuLieu(duLieuBangDonNhap);
    }

    public void taoHinh() {
        setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
        this.setBackground(Color.gray);
        this.setLayout(new BorderLayout());

        // Title
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel title = new JLabel("Đơn Nhập");
        title.setFont(UIStyles.TitleFont);
        titlePanel.add(title, BorderLayout.CENTER);

        // Create custom table for DonNhapHang
        JPanel panelDonNhap = new JPanel();
        panelDonNhap.setPreferredSize(new Dimension(650, 700));
        panelDonNhap.setMaximumSize(new Dimension(650, 700));
        panelDonNhap.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        bangDonNhap = new CustomTable(duLieuBangDonNhap, tenCotBangDonNhap, UIStyles.NhanVienTableHeaderStyle,
                UIStyles.NhanVienTableRowStyle, 20, new int[] {200, 200, 300});

        JScrollPane scrollPane = new JScrollPane(bangDonNhap);
        scrollPane.setPreferredSize(new Dimension(650, 700));

        bangDonNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                capNhatDuLieuCTDN(danhSachDonNhap.get(bangDonNhap.getSelectedRow()).getMaDonNhap());
            }
        });
        
        panelDonNhap.add(scrollPane);

        // Create custom table for ChiTietDonNhap
        JPanel panelChiTiet = new JPanel();
        panelChiTiet.setPreferredSize(new Dimension(900, 700));
        panelChiTiet.setMinimumSize(new Dimension(900, 700));
        panelChiTiet.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        bangChiTietDonNhap = new CustomTable(duLieuBangCTDN, tenCotCTDN, UIStyles.NhanVienTableHeaderStyle,
                UIStyles.NhanVienTableRowStyle, 20, new int[] {250, 150, 150, 150, 200});

        JScrollPane scrollPaneCTDN = new JScrollPane(bangChiTietDonNhap);
        scrollPaneCTDN.setPreferredSize(new Dimension(900, 700));
        
        panelChiTiet.add(scrollPaneCTDN);
        
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(panelDonNhap, BorderLayout.CENTER);
        this.add(panelChiTiet, BorderLayout.EAST);

        // Add the search panel
        JPanel searchPanel = taoSearchPanel();
        this.add(searchPanel, BorderLayout.WEST);
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

    private void chuanBiDuLieu() {
        duLieuBangDonNhap = new String[danhSachDonNhap.size()][tenCotBangDonNhap.length];
        for (int i = 0; i < danhSachDonNhap.size(); i++) {
            duLieuBangDonNhap[i] = new String[] { danhSachDonNhap.get(i).getMaDonNhap(),
                    danhSachDonNhap.get(i).getNgayNhap().toString(), NhanVienCTR.timTheoMa(danhSachDonNhap.get(i).getNhanVienNhap().getMaNhanVien()).getHoTen() };
        }
    }

    private void capNhatDuLieuCTDN(String maDonNhap) {
        danhSachChiTietDonNhap = KhoCTR.layDanhSachChiTietDonNhap(maDonNhap);
        duLieuBangCTDN = new String[danhSachChiTietDonNhap.size()][tenCotCTDN.length];
        for (int i = 0; i < danhSachChiTietDonNhap.size(); i++) {
            duLieuBangCTDN[i] = new String[] { SanPhamCTR.timSanPhamTheoMaSanPham(danhSachChiTietDonNhap.get(i).getMaSanPham().getMaSanPham()).getTenSanPham(),
                    String.valueOf(danhSachChiTietDonNhap.get(i).getSoLuongDonViTinh1()),
                    String.valueOf(danhSachChiTietDonNhap.get(i).getSoLuongDonViTinh2()),
                    String.valueOf(danhSachChiTietDonNhap.get(i).getSoLuongDonViTinh3()),
                    String.valueOf(danhSachChiTietDonNhap.get(i).getGiaNhap() +"đ"),
            
            };
        }
        bangChiTietDonNhap.capNhatDuLieu(duLieuBangCTDN);
    }

    private void capNhatDuLieuDonNhap(Date fromDate, Date toDate) {
        ArrayList<DonNhapHang> filteredList = KhoCTR.layDanhSachDonNhapTheoNgay(fromDate, toDate);
        duLieuBangDonNhap = new String[filteredList.size()][tenCotBangDonNhap.length];
        for (int i = 0; i < filteredList.size(); i++) {
            duLieuBangDonNhap[i] = new String[] { filteredList.get(i).getMaDonNhap(),
                    filteredList.get(i).getNgayNhap().toString(), filteredList.get(i).getNhanVienNhap().getHoTen() };
        }
        bangDonNhap.capNhatDuLieu(duLieuBangDonNhap);
    }
    private void tim(JDateChooser fromDateChooser, JDateChooser toDateChooser) {
    	java.util.Date utilFromDate = fromDateChooser.getDate();
        java.util.Date utilToDate = toDateChooser.getDate();
        if (utilFromDate != null && utilToDate != null) {
            Date fromDate = new Date(utilFromDate.getTime());
            Date toDate = new Date(utilToDate.getTime());
            capNhatDuLieuDonNhap(fromDate, toDate);
        }
    }
}
