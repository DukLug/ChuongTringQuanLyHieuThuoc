package userInterface;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import component.CustomButton;
import component.CustomTable;
import controller.KhoCTR;
import controller.LoaiSanPhamCTR;
import controller.SanPhamCTR;
import entity.LoHang;
import entity.SanPhamYTe;

public class LoHangUI extends JPanel {

    private ArrayList<LoHang> danhSachLoHang = KhoCTR.layDanhSachTatCaLoHang();
    private String[][] duLieuBang;
    private String[] tenCot = {"Mã Lô", "Ngày Sản Xuất", "Hạn Sử Dụng", "Giá Nhập", "SL ĐVT1", "SL ĐVT2", "SL ĐVT3", "Vị Trí", "Sản Phẩm"};
    private CustomTable bangLoHang;
    private JTextField txtSearch;
    private boolean huyHangMode = false;
    private CustomButton huyHangBTN;

    public LoHangUI() {
        super(new BorderLayout());
        taoHinh();
        chuanBiDuLieu();
        bangLoHang.capNhatDuLieu(duLieuBang);
    }

    private void taoHinh() {
        // Tiêu đề panel ở phía Bắc
        JPanel titlePanel = new JPanel();
        JLabel lblTitle = new JLabel("Quản lý lô hàng");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 35));
        titlePanel.add(lblTitle);
        titlePanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        this.add(titlePanel, BorderLayout.NORTH);

        // Bảng ở trung tâm
        JPanel center = new JPanel();
        center.setMaximumSize(new Dimension(1300, 700));
        center.setPreferredSize(new Dimension(1300, 700));
        center.setLayout(new BorderLayout());
        center.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bangLoHang = new CustomTable(new String[][]{}, tenCot, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle, 20,
                new int[]{200, 200, 200, 150, 100, 100, 100, 150, 300});
        JScrollPane scrollPane = new JScrollPane(bangLoHang);
        scrollPane.setMaximumSize(new Dimension(1300, 700));
        center.add(scrollPane, BorderLayout.CENTER);
        this.add(center, BorderLayout.CENTER);

        // Form tìm kiếm ở phía Tây
        JPanel searchPanel = new JPanel(new GridBagLayout());
        searchPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblSearch = new JLabel("Tìm kiếm mã sản phẩm:");
        txtSearch = new JTextField(15);
        JButton btnSearch = new JButton("Tìm kiếm");
        btnSearch.addActionListener(e -> timKiemSanPham());

        gbc.gridx = 0;
        gbc.gridy = 0;
        searchPanel.add(lblSearch, gbc);

        gbc.gridy = 1;
        searchPanel.add(txtSearch, gbc);

        gbc.gridy = 2;
        searchPanel.add(btnSearch, gbc);

        this.add(searchPanel, BorderLayout.WEST);

        // Nút Nhập hàng và Hủy hàng ở phía Đông
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        CustomButton nhapHangBTN = new CustomButton("Nhập hàng", UIStyles.BanHangButtonStyle, () -> {
            TrangChuUI.singleton.taiTrang(new NhapHangUI());
        });
        huyHangBTN = new CustomButton("Hủy Hàng", UIStyles.DoiTraButtonStyle, () -> batDauHuyHang());

        nhapHangBTN.setAlignmentX(Component.CENTER_ALIGNMENT);
        huyHangBTN.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(nhapHangBTN);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(huyHangBTN);

        this.add(buttonPanel, BorderLayout.EAST);
    }

    private void chuanBiDuLieu() {
        duLieuBang = new String[danhSachLoHang.size()][tenCot.length];
        for (int i = 0; i < danhSachLoHang.size(); i++) {
            LoHang loHang = danhSachLoHang.get(i);
            duLieuBang[i] = new String[]{
                    loHang.getMaLo(),
                    loHang.getNgaySanXuat() != null ? loHang.getNgaySanXuat().toString() : "",
                    loHang.getHanSuDung() != null ? loHang.getHanSuDung().toString() : "",
                    loHang.getGiaNhap() != null ? loHang.getGiaNhap().toString() : "",
                    String.valueOf(loHang.getSoLuongDonViTinh1()),
                    String.valueOf(loHang.getSoLuongDonViTinh2()),
                    String.valueOf(loHang.getSoLuongDonViTinh3()),
                    loHang.getViTri(),
                    SanPhamCTR.timSanPhamTheoMaSanPham(loHang.getSanPham().getMaSanPham()).getTenSanPham(),
            };
        }
    }

    private void batDauHuyHang() {
        huyHangMode = !huyHangMode;
        huyHangBTN.setText(huyHangMode ? "Hoàn Thành" : "Hủy Hàng");
        String message = huyHangMode
                ? "Chế độ Hủy Hàng đã bật. Chọn lô hàng để điều chỉnh số lượng."
                : "Chế độ Hủy Hàng đã tắt.";
        JOptionPane.showMessageDialog(this, message, "Hủy Hàng", JOptionPane.INFORMATION_MESSAGE);
    }

    
    private void timKiemSanPham() {
        String tuKhoa = txtSearch.getText().trim().toLowerCase();
        ArrayList<SanPhamYTe> ketQuaTimKiem = new ArrayList<>();
        ArrayList<SanPhamYTe> danhSachSanPham  = SanPhamCTR.layDanhSachTatCaSanPham();
        
        for (SanPhamYTe sp : danhSachSanPham) {
            if (sp.getMaVach().toLowerCase().contains(tuKhoa) || 
                sp.getTenSanPham().toLowerCase().contains(tuKhoa) ||
                (LoaiSanPhamCTR.timTheoMa(sp.getLoaiSanPham().getMaLoai()).getTenLoai()).contains(tuKhoa)) {
                ketQuaTimKiem.add(sp);
            }
        }
        ArrayList<LoHang> loHangPhuHop = new ArrayList<LoHang>();
        for(int i = 0; i<danhSachLoHang.size(); i++) {
        	SanPhamYTe sp =SanPhamCTR.timSanPhamTheoMaSanPham(danhSachLoHang.get(i).getSanPham().getMaSanPham());
        	if(ketQuaTimKiem.contains(sp)) {
        		loHangPhuHop.add(danhSachLoHang.get(i));
        	}
        	
        }
        // Cập nhật bảng với kết quả tìm kiếm
        duLieuBang = new String[loHangPhuHop.size()][tenCot.length];
        for(int i = 0; i < loHangPhuHop.size(); i++) {
        	
        	LoHang lh = loHangPhuHop.get(i);
            duLieuBang[i] = new String[] {
                lh.getMaLo(),
                lh.getNgaySanXuat().toString(),
                lh.getHanSuDung().toString(),
                lh.getGiaNhap().toString() + "đ",
                lh.getSoLuongDonViTinh1()+"",
                lh.getSoLuongDonViTinh2()+"",
                lh.getSoLuongDonViTinh3()+"",
                lh.getViTri(),
                SanPhamCTR.timSanPhamTheoMaSanPham(lh.getSanPham().getMaSanPham()).getTenSanPham(),
            };
        }
        bangLoHang.capNhatDuLieu(duLieuBang);
    }
}
