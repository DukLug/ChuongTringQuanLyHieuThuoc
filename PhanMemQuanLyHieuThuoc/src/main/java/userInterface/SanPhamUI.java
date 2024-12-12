package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import component.CustomButton;
import component.CustomPanel;
import component.CustomTable;
import component.CustomButton.CustomButtonIconSide;
import controller.LoaiSanPhamCTR;
import controller.NhaCungCapCTR;
import controller.SanPhamCTR;
import entity.SanPhamYTe;

public class SanPhamUI extends JPanel {
    // Data-related fields
    private ArrayList<SanPhamYTe> danhSachSanPham = SanPhamCTR.layDanhSachTatCaSanPham();
    private String[][] duLieuBang = new String[][] {};
    private String[] tenCot = new String[] {"Mã vạch", "Tên", "Nước sản xuất", "Giá cơ bản", "Loại thuốc"};

    // UI Components
    private CustomTable bangSanPham;
    private JTextField txtTimKiem;
    private CustomPanel panelChiTietSanPham;
    private JLabel lblTieuDe, lblMaSanPham, lblTenSanPham, lblNuocSanXuat, lblGia1, lblGia2, lblGia3, lblDangBaoChe, lblThue, lblThanhPhan, lblNhaCungCap, lblLoai, lblYeuCaoKeDon;
    private CustomButton btnTimKiem, btnDungBan;
    private JPanel panelBang;

    public SanPhamUI() {
        setLayout(null);
        setBackground(UIStyles.BackgroundColor);
        
        taoHinh();
        chuanBiDuLieu();
        bangSanPham.capNhatDuLieu(duLieuBang);
    }

    public void taoHinh() {
        // Tiêu đề
        lblTieuDe = new JLabel("Sản Phẩm Y Tế");
        lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblTieuDe.setBounds(700, 10, 300, 49);
        add(lblTieuDe);

        // Bảng sản phẩm
        panelBang = new JPanel(new BorderLayout());
        panelBang.setBounds(50, 70, 900, 750);
        panelBang.setBackground(UIStyles.BackgroundColor);

        // Tìm kiếm
        JPanel panelTimKiem = new JPanel(null);
        panelTimKiem.setBackground(UIStyles.BackgroundColor);
        panelTimKiem.setPreferredSize(new Dimension(1350, 50));
        
        txtTimKiem = new JTextField("Nhập mã, tên hoặc loại sản phẩm");
        txtTimKiem.setBounds(0, 10, 300, 40);
        txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtTimKiem.setBorder(new LineBorder(Color.BLACK, 1));

        btnTimKiem = new CustomButton("",UIStyles.TimButtonStyle,UIStyles.FindIcon,CustomButtonIconSide.LEFT,()->timKiemSanPham());
        btnTimKiem.setBounds(320, 10, 150, 40);
        btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 20));

        panelTimKiem.add(txtTimKiem);
        panelTimKiem.add(btnTimKiem);
        panelBang.add(panelTimKiem, BorderLayout.NORTH);

        // Bảng dữ liệu
        bangSanPham = new CustomTable(
            duLieuBang, 
            tenCot, 
            UIStyles.NhanVienTableHeaderStyle, 
            UIStyles.NhanVienTableRowStyle, 
            20,
            new int[] {200, 200, 150, 150, 200}
        );
        bangSanPham.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        bangSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                hienThiChiTietSanPham();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(bangSanPham);
        scrollPane.setMaximumSize(new Dimension(900, 700));
        panelBang.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setBorder(BorderFactory.createLineBorder(UIStyles.LabelFontColorGreen));

        add(panelBang);

        // Panel chi tiết sản phẩm
        panelChiTietSanPham = new CustomPanel(20, 0);
        panelChiTietSanPham.setBackground(Color.WHITE);
        panelChiTietSanPham.setBounds(1000, 60, 900, 750);
        panelChiTietSanPham.setLayout(null);

        // Tiêu đề panel chi tiết
        JPanel panelChiTiet = new JPanel();
        panelChiTiet.setBorder(new TitledBorder(
            new LineBorder(Color.BLACK, 1), 
            "Chi Tiết Sản Phẩm", 
            TitledBorder.LEFT, 
            TitledBorder.TOP, 
            new Font("Tahoma", Font.PLAIN, 20), 
            Color.BLACK
        ));
        panelChiTiet.setBackground(Color.WHITE);
        panelChiTiet.setBounds(32, 50, 800, 700);
        panelChiTiet.setLayout(null);

        // Các label chi tiết
        lblMaSanPham = createDetailLabel("Mã Sản Phẩm:", 42, 50);
        lblTenSanPham = createDetailLabel("Tên Sản Phẩm:", 42, 100);
        lblNuocSanXuat = createDetailLabel("Nước Sản Xuất:", 42, 150);
        
        lblDangBaoChe = createDetailLabel("Dạng Bào Chế:", 42, 200);
        lblThue = createDetailLabel("Thuế:", 42, 250);
        lblThanhPhan = createDetailLabel("Thành phần:", 42, 300);
        lblNhaCungCap = createDetailLabel("Nhà cung cấp:", 42, 350);
        lblLoai = createDetailLabel("Loại:", 42, 400);
        lblYeuCaoKeDon = createDetailLabel("Yêu cầu kê đơn:", 42, 450);
        lblGia1 = createDetailLabel("Giá:", 42, 500);
        lblGia2 = createDetailLabel("Giá:", 42, 550);
        lblGia3 = createDetailLabel("Giá:", 42, 600);
        // Nút Dừng Bán
        btnDungBan = new CustomButton("Dừng Bán",UIStyles.DoiTraButtonStyle, ()->capNhatNgungBan());
        btnDungBan.setBounds(380, 650, 150, 40);
        btnDungBan.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnDungBan.setForeground(Color.RED);

        panelChiTiet.add(lblMaSanPham);
        panelChiTiet.add(lblTenSanPham);
        panelChiTiet.add(lblNuocSanXuat);
        
        panelChiTiet.add(lblDangBaoChe);
        panelChiTiet.add(lblThue);
        panelChiTiet.add(lblThanhPhan);
        panelChiTiet.add(lblNhaCungCap);
        panelChiTiet.add(lblLoai);
        panelChiTiet.add(lblYeuCaoKeDon);
        panelChiTiet.add(lblGia1);
        panelChiTiet.add(lblGia2);
        panelChiTiet.add(lblGia3);
        panelChiTiet.add(btnDungBan);

        panelChiTietSanPham.add(panelChiTiet);
        add(panelChiTietSanPham);
    }

    // Phương thức hỗ trợ tạo label chi tiết
    private JLabel createDetailLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label.setBounds(x, y, 700, 30);
        return label;
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
                LoaiSanPhamCTR.timTheoMa(danhSachSanPham.get(i).getLoaiSanPham().getMaLoai()).getTenLoai()
            };
        }
    }

    private void timKiemSanPham() {
        String tuKhoa = txtTimKiem.getText().trim().toLowerCase();
        ArrayList<SanPhamYTe> ketQuaTimKiem = new ArrayList<>();
        
        for (SanPhamYTe sp : danhSachSanPham) {
            if (sp.getMaVach().toLowerCase().contains(tuKhoa) || 
                sp.getTenSanPham().toLowerCase().contains(tuKhoa) ||
                (LoaiSanPhamCTR.timTheoMa(sp.getLoaiSanPham().getMaLoai()).getTenLoai()).contains(tuKhoa)) {
                ketQuaTimKiem.add(sp);
            }
        }
        
        // Cập nhật bảng với kết quả tìm kiếm
        duLieuBang = new String[ketQuaTimKiem.size()][tenCot.length];
        for(int i = 0; i < ketQuaTimKiem.size(); i++) {
            SanPhamYTe sp = ketQuaTimKiem.get(i);
            duLieuBang[i] = new String[] {
                sp.getMaVach(),
                sp.getTenSanPham(),
                sp.getNuocSanXuat(),
                sp.getGiaBanDonViTinh1().toString() + "đ",
                LoaiSanPhamCTR.timTheoMa(sp.getLoaiSanPham().getMaLoai()).getTenLoai(),
            };
        }
        bangSanPham.capNhatDuLieu(duLieuBang);
    }

    private void hienThiChiTietSanPham() {
        int selectedRow = bangSanPham.getSelectedRow();
        if (selectedRow >= 0) {
            SanPhamYTe selectedSanPham = danhSachSanPham.get(selectedRow);
            
            lblMaSanPham.setText("Mã Sản Phẩm: " + selectedSanPham.getMaSanPham());
            lblTenSanPham.setText("Tên Sản Phẩm: " + selectedSanPham.getTenSanPham());
            lblNuocSanXuat.setText("Nước Sản Xuất: " + selectedSanPham.getNuocSanXuat());
            
            lblDangBaoChe.setText("Dạng Bào Chế: " + selectedSanPham.getDangBaoChe());
            lblThue.setText("Thuế: " + selectedSanPham.getThue() + "%");
            lblThanhPhan.setText("Thành phần: " + selectedSanPham.getThanhPhan());
            lblNhaCungCap.setText("Nhà cung cấp: " + NhaCungCapCTR.timKiemTheoMa(selectedSanPham.getNhaCungCap().getMaNhaCungCap()).getTenNhaCungCap());
            lblLoai.setText("Loại: " + LoaiSanPhamCTR.timTheoMa(selectedSanPham.getLoaiSanPham().getMaLoai()).getTenLoai());
            if(Boolean.parseBoolean(selectedSanPham.getYeuCauKeDon())) {
            	lblYeuCaoKeDon.setText("Yêu cầu kê đơn: " + " Có");
            }else {
            	lblYeuCaoKeDon.setText("Yêu cầu kê đơn: " + " Không");
            }
            lblGia1.setText(selectedSanPham.getGiaBanDonViTinh1().toString() + "đ/ " + selectedSanPham.getDonViTinh1().toString());
            lblGia2.setText(selectedSanPham.getGiaBanDonViTinh2().toString() + "đ/ " + selectedSanPham.getDonViTinh2().toString() + " " + selectedSanPham.getGiaTriQuyDoi2());
            lblGia3.setText(selectedSanPham.getGiaBanDonViTinh3().toString() + "đ/ " + selectedSanPham.getDonViTinh3().toString() + " " + selectedSanPham.getGiaTriQuyDoi3());
        }
    }

    private void capNhatNgungBan() {
        int selectedRow = bangSanPham.getSelectedRow();
        if (selectedRow >= 0) {
            SanPhamYTe selectedSanPham = danhSachSanPham.get(selectedRow);
            // Gọi phương thức từ SanPhamCTR để cập nhật trạng thái ngừng bán
            SanPhamCTR.capNhatNgungBan(selectedSanPham);
            
            // Làm mới dữ liệu
            danhSachSanPham = SanPhamCTR.layDanhSachTatCaSanPham();
            chuanBiDuLieu();
            bangSanPham.capNhatDuLieu(duLieuBang);
        }
    }
}