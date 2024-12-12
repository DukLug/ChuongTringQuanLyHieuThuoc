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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import component.CustomTable;
import controller.KhoCTR;
import controller.NhanVienCTR;
import controller.SanPhamCTR;
import entity.DonHuyHang;
import entity.LoHang;
import entity.SanPhamYTe;

public class DonHuyHangUI extends JPanel {

    private ArrayList<DonHuyHang> danhSachDonHuy = KhoCTR.layDanhSachTatCaDonHuy();
    private String[][] duLieuBangDonHuy = new String[][] {};
    private String[] tenCotBangDonHuy = new String[] {"Mã đơn hủy", "Ngày hủy", "Số lượng DVT1", "Số lượng DVT2", "Số lượng DVT3", "Nhân viên hủy", "Lô hàng"};

    private CustomTable bangDonHuy;

    public DonHuyHangUI() {
        super();
        taoHinh();
        chuanBiDuLieu();
        bangDonHuy.capNhatDuLieu(duLieuBangDonHuy);
    }

    public void taoHinh() {
        setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
        this.setBackground(UIStyles.BackgroundColor);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel title = new JLabel("Đơn Hủy Hàng");
        title.setFont(UIStyles.TitleFont);
        titlePanel.add(title, BorderLayout.CENTER);

        // Create custom table for DonHuyHang
        JPanel panelDonHuy = new JPanel();
        panelDonHuy.setBorder(new TitledBorder(
                new LineBorder(Color.BLACK, 1), 
                "Danh sách đơn hủy", 
                TitledBorder.LEFT, 
                TitledBorder.TOP, 
                new Font("Tahoma", Font.PLAIN, 20), 
                Color.BLACK
            ));
        panelDonHuy.setPreferredSize(new Dimension(1500, 700));
        panelDonHuy.setMaximumSize(new Dimension(1500, 700));
        
        bangDonHuy = new CustomTable(duLieuBangDonHuy, tenCotBangDonHuy, UIStyles.NhanVienTableHeaderStyle,
                UIStyles.NhanVienTableRowStyle, 20, new int[] {250, 250, 250, 250, 250, 250, 250});

        JScrollPane scrollPane = new JScrollPane(bangDonHuy);
        scrollPane.setPreferredSize(new Dimension(1500, 700));

        panelDonHuy.add(scrollPane);
        
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(panelDonHuy, BorderLayout.CENTER);

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
        duLieuBangDonHuy = new String[danhSachDonHuy.size()][tenCotBangDonHuy.length];
        for (int i = 0; i < danhSachDonHuy.size(); i++) {
            DonHuyHang donHuy = danhSachDonHuy.get(i);
            duLieuBangDonHuy[i] = new String[] { 
                donHuy.getMaDonHuyHang(),
                donHuy.getNgayHuy().toString(), 
                donHuy.getSoLuongDonViTinh1()+"",
                donHuy.getSoLuongDonViTinh2()+"",
                donHuy.getSoLuongDonViTinh3()+"",
                NhanVienCTR.timTheoMa(donHuy.getNhanVien().getMaNhanVien()).getHoTen(),
                donHuy.getLoHang().getMaLo(),
            };
        }
    }

    private void capNhatDuLieuDonHuy(Date fromDate, Date toDate) {
        ArrayList<DonHuyHang> filteredList = KhoCTR.layDanhSachDonHuyTheoNgay(fromDate, toDate);
        duLieuBangDonHuy = new String[filteredList.size()][tenCotBangDonHuy.length];
        for (int i = 0; i < filteredList.size(); i++) {
            DonHuyHang donHuy = filteredList.get(i);
            duLieuBangDonHuy[i] = new String[] { 
                donHuy.getMaDonHuyHang(),
                donHuy.getNgayHuy().toString(), 
                NhanVienCTR.timTheoMa(donHuy.getNhanVien().getMaNhanVien()).getHoTen(),
                donHuy.getLoHang().getMaLo()
            };
        }
        bangDonHuy.capNhatDuLieu(duLieuBangDonHuy);
    }

    private void tim(JDateChooser fromDateChooser, JDateChooser toDateChooser) {
        java.util.Date utilFromDate = fromDateChooser.getDate();
        java.util.Date utilToDate = toDateChooser.getDate();
        if (utilFromDate != null && utilToDate != null) {
            Date fromDate = new Date(utilFromDate.getTime());
            Date toDate = new Date(utilToDate.getTime());
            capNhatDuLieuDonHuy(fromDate, toDate);
        }
    }
}