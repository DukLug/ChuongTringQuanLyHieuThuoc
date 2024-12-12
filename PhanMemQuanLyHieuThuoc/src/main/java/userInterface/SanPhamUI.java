package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
import customDataType.DonViTinh;
import customDataType.TrangThaiSanPham;
import entity.LoaiSanPham;
import entity.NhaCungCap;
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
    private CustomButton btnThemSanPham;


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
        btnTimKiem.setBounds(320, 10, 150, 40);

        btnThemSanPham = new CustomButton("Thêm Sản Phẩm", UIStyles.ThemButtonStyle, () -> hienThiDialogThemSanPham());
        btnThemSanPham.setBounds(490, 10, 200, 40);
        btnThemSanPham.setFont(new Font("Tahoma", Font.PLAIN, 20));

        panelTimKiem.add(txtTimKiem);
        panelTimKiem.add(btnTimKiem);
        panelTimKiem.add(btnThemSanPham);

        
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
    private void hienThiDialogThemSanPham() {
        JDialog dialogThemSanPham = new JDialog();
        dialogThemSanPham.setTitle("Thêm Sản Phẩm Mới");
        dialogThemSanPham.setSize(1000, 800);
        dialogThemSanPham.setLocationRelativeTo(null);
        dialogThemSanPham.setModal(true);

        JPanel panelNoiDung = new JPanel(new GridBagLayout());
        panelNoiDung.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tiêu đề
        JLabel lblTieuDe = new JLabel("THÊM SẢN PHẨM Y TẾ MỚI", SwingConstants.CENTER);
        lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.ipady = 20;
        panelNoiDung.add(lblTieuDe, gbc);

        // Reset gridwidth và ipady
        gbc.gridwidth = 1;
        gbc.ipady = 0;

        // Các trường nhập liệu chính
        JTextField txtMaSanPham = createStyledTextField("Mã Sản Phẩm");
        JTextField txtTenSanPham = createStyledTextField("Tên Sản Phẩm");
        JTextField txtNuocSanXuat = createStyledTextField("Nước Sản Xuất");

        // Nhóm các trường nhập liệu nhỏ hơn
        JTextField txtThue = createStyledTextField("Thuế (%)");
        JTextField txtMaVach = createStyledTextField("Mã Vạch");
        JTextField txtDangBaoChe = createStyledTextField("Dạng Bào Chế");

        // Combobox cho các danh mục
        List<NhaCungCap> nhaCungCapList = NhaCungCapCTR.layDanhSachTatCaNhaCungCap();
        JComboBox<String> cbNhaCungCap = createStyledComboBox(
            nhaCungCapList.stream().map(NhaCungCap::getTenNhaCungCap).toArray(String[]::new), 
            "Nhà Cung Cấp"
        );

        List<LoaiSanPham> loaiSanPhamList = LoaiSanPhamCTR.layDanhSachTatCaLoaiSanPham();
        JComboBox<String> cbLoaiSanPham = createStyledComboBox(
            loaiSanPhamList.stream().map(LoaiSanPham::getTenLoai).toArray(String[]::new), 
            "Loại Sản Phẩm"
        );

        JComboBox<String> cbTrangThaiSanPham = createStyledComboBox(
            Arrays.stream(TrangThaiSanPham.values()).map(Enum::toString).toArray(String[]::new), 
            "Trạng Thái"
        );

        // Giá và quy đổi
        JTextField txtGiaBanDonViTinh1 = createStyledTextField("Giá Bán Đ.Vị 1");
        JTextField txtGiaBanDonViTinh2 = createStyledTextField("Giá Bán Đ.Vị 2");
        JTextField txtGiaBanDonViTinh3 = createStyledTextField("Giá Bán Đ.Vị 3");
        JTextField txtGiaTriQuyDoi2 = createStyledTextField("Giá Trị QĐ 2");
        JTextField txtGiaTriQuyDoi3 = createStyledTextField("Giá Trị QĐ 3");

        // Các trường nhập liệu mở rộng
        JTextArea txtThanhPhan = createStyledTextArea("Thành Phần", 3);
        JTextArea txtGhiChu = createStyledTextArea("Ghi Chú", 2);
        JTextArea txtMoTa = createStyledTextArea("Mô Tả", 3);

        // Đơn vị tính
        JComboBox<String> cbDonViTinh1 = createStyledComboBox(
            Arrays.stream(DonViTinh.values()).map(Enum::toString).toArray(String[]::new), 
            "Đơn Vị Tính 1"
        );
        JComboBox<String> cbDonViTinh2 = createStyledComboBox(
            Arrays.stream(DonViTinh.values()).map(Enum::toString).toArray(String[]::new), 
            "Đơn Vị Tính 2"
        );
        JComboBox<String> cbDonViTinh3= createStyledComboBox(
                Arrays.stream(DonViTinh.values()).map(Enum::toString).toArray(String[]::new), 
                "Đơn Vị Tính 2"
            );


        // Sắp xếp các thành phần
        // Hàng 1: Mã SP, Tên SP, Nước SX
        gbc.gridy = 1;
        gbc.gridx = 0;
        panelNoiDung.add(createLabeledComponent("Mã Sản Phẩm", txtMaSanPham), gbc);
        gbc.gridx = 1;
        panelNoiDung.add(createLabeledComponent("Tên Sản Phẩm", txtTenSanPham), gbc);
        gbc.gridx = 2;
        panelNoiDung.add(createLabeledComponent("Nước Sản Xuất", txtNuocSanXuat), gbc);

        // Hàng 2: Các trường nhỏ
        gbc.gridy = 2;
        gbc.gridx = 0;
        panelNoiDung.add(createLabeledComponent("Thuế (%)", txtThue), gbc);
        gbc.gridx = 1;
        panelNoiDung.add(createLabeledComponent("Mã Vạch", txtMaVach), gbc);
        gbc.gridx = 2;
        panelNoiDung.add(createLabeledComponent("Dạng Bào Chế", txtDangBaoChe), gbc);

        // Hàng 3: Combobox
        gbc.gridy = 3;
        gbc.gridx = 0;
        panelNoiDung.add(createLabeledComponent("Nhà Cung Cấp", cbNhaCungCap), gbc);
        gbc.gridx = 1;
        panelNoiDung.add(createLabeledComponent("Loại Sản Phẩm", cbLoaiSanPham), gbc);
        gbc.gridx = 2;
        panelNoiDung.add(createLabeledComponent("Trạng Thái", cbTrangThaiSanPham), gbc);

        // Hàng 4: Giá và quy đổi
        gbc.gridy = 4;
        gbc.gridx = 0;
        panelNoiDung.add(createLabeledComponent("Giá Bán Đ.Vị 1", txtGiaBanDonViTinh1), gbc);
        gbc.gridx = 1;
        panelNoiDung.add(createLabeledComponent("Giá Bán Đ.Vị 2", txtGiaBanDonViTinh2), gbc);
        gbc.gridx = 2;
        panelNoiDung.add(createLabeledComponent("Giá Bán Đ.Vị 2", txtGiaBanDonViTinh3), gbc);

        // Hàng 5: Đơn vị tính
        gbc.gridy = 5;
        gbc.gridx = 0;
        panelNoiDung.add(createLabeledComponent("Đơn Vị Tính 1", cbDonViTinh1), gbc);
        gbc.gridx = 1;
        panelNoiDung.add(createLabeledComponent("Đơn Vị Tính 2", cbDonViTinh2), gbc);
        gbc.gridx = 2;
        panelNoiDung.add(createLabeledComponent("Đơn Vị Tính 2", cbDonViTinh3), gbc);

        // Hàng 6-8: Các trường mở rộng
        // Add the YeuCauKeDon radio buttons
        JRadioButton rbYeuCauKeDonCo = new JRadioButton("Có");
        JRadioButton rbYeuCauKeDonKhong = new JRadioButton("Không");
        rbYeuCauKeDonCo.setFont(new Font("Tahoma", Font.PLAIN, 20));
        rbYeuCauKeDonKhong.setFont(new Font("Tahoma", Font.PLAIN, 20));

        // Group the radio buttons
        ButtonGroup bgYeuCauKeDon = new ButtonGroup();
        bgYeuCauKeDon.add(rbYeuCauKeDonCo);
        bgYeuCauKeDon.add(rbYeuCauKeDonKhong);

        // Set default selection
        rbYeuCauKeDonKhong.setSelected(true);

        // Add the radio buttons to the layout
        JPanel panelYeuCauKeDon = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel radioLabel = new JLabel("Yêu Cầu Kê Đơn:");
        radioLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        panelYeuCauKeDon.add(radioLabel);
        panelYeuCauKeDon.add(rbYeuCauKeDonCo);
        panelYeuCauKeDon.add(rbYeuCauKeDonKhong);

        gbc.gridy = 6; // Adjust as per layout
        gbc.gridx = 0;
        panelNoiDung.add(panelYeuCauKeDon, gbc);
        gbc.gridx = 1;
        panelNoiDung.add(createLabeledComponent("Giá trị quy đổi đơn vị 2:", txtGiaTriQuyDoi2), gbc);
        gbc.gridx = 2;
        panelNoiDung.add(createLabeledComponent("Giá trị quy đổi đơn vị 3:", txtGiaTriQuyDoi3), gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        panelNoiDung.add(createLabeledComponent("Thành Phần", new JScrollPane(txtThanhPhan)), gbc);

        gbc.gridy = 8;
        panelNoiDung.add(createLabeledComponent("Ghi Chú", new JScrollPane(txtGhiChu)), gbc);

        gbc.gridy = 9;
        panelNoiDung.add(createLabeledComponent("Mô Tả", new JScrollPane(txtMoTa)), gbc);

	    

        // Nút Xác Nhận
        gbc.gridy = 10;
        gbc.gridx = 1;
        CustomButton btnXacNhan = new CustomButton("Thêm Sản Phẩm", UIStyles.ThemButtonStyle, () -> {
            try {
                    // Tìm NhaCungCap và LoaiSanPham được chọn
                    NhaCungCap selectedNhaCungCap = nhaCungCapList.stream()
                        .filter(ncc -> ncc.getTenNhaCungCap().equals(cbNhaCungCap.getSelectedItem()))
                        .findFirst()
                        .orElseThrow(() -> new Exception("Không tìm thấy Nhà Cung Cấp"));

                    LoaiSanPham selectedLoaiSanPham = loaiSanPhamList.stream()
                        .filter(loai -> loai.getTenLoai().equals(cbLoaiSanPham.getSelectedItem()))
                        .findFirst()
                        .orElseThrow(() -> new Exception("Không tìm thấy Loại Sản Phẩm"));

                    // Validate and create new SanPhamYTe
                    SanPhamYTe newSanPham = new SanPhamYTe(
                        txtMaSanPham.getText(),
                        txtTenSanPham.getText(),
                        txtNuocSanXuat.getText(),
                        TrangThaiSanPham.fromString((String) cbTrangThaiSanPham.getSelectedItem()),
                        txtGhiChu.getText(),
                        txtMoTa.getText(),
                        txtDangBaoChe.getText(),
                        Double.parseDouble(txtThue.getText()),
                        txtThanhPhan.getText(),
                        DonViTinh.fromString((String) cbDonViTinh1.getSelectedItem()),
                        DonViTinh.fromString((String) cbDonViTinh2.getSelectedItem()),
                        DonViTinh.fromString((String) cbDonViTinh3.getSelectedItem()),
                        BigDecimal.valueOf(Double.parseDouble(txtGiaBanDonViTinh1.getText())),
                        BigDecimal.valueOf(Double.parseDouble(txtGiaBanDonViTinh2.getText())),
                        BigDecimal.valueOf(Double.parseDouble(txtGiaBanDonViTinh3.getText())),
                        Integer.parseInt(txtGiaTriQuyDoi2.getText()),
                        Integer.parseInt(txtGiaTriQuyDoi3.getText()),
                        selectedNhaCungCap,
                        selectedLoaiSanPham,
                        txtMaVach.getText(),
                        
                        rbYeuCauKeDonCo.isSelected() == true ? "Có" : "Không",
                        new ArrayList<>() // loHang - rỗng khi tạo mới
                    );

                    // Gọi phương thức thêm sản phẩm
                    SanPhamCTR.themSanPham(newSanPham);

                    // Làm mới dữ liệu
                    danhSachSanPham = SanPhamCTR.layDanhSachTatCaSanPham();
                    chuanBiDuLieu();
                    bangSanPham.capNhatDuLieu(duLieuBang);

                    JOptionPane.showMessageDialog(dialogThemSanPham, "Thêm sản phẩm thành công!");
                    dialogThemSanPham.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialogThemSanPham, "Lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
        });
        gbc.gridx = 2;
        panelNoiDung.add(btnXacNhan, gbc);

        // Thêm thanh cuộn
        JScrollPane scrollPane = new JScrollPane(panelNoiDung);
        dialogThemSanPham.add(scrollPane);
        dialogThemSanPham.pack();
        dialogThemSanPham.setVisible(true);
    }

    // Các phương thức hỗ trợ
    private JTextField createStyledTextField(String placeholder) {
        JTextField textField = new JTextField(24);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return textField;
    }

    private JTextArea createStyledTextArea(String placeholder, int rows) {
        JTextArea textArea = new JTextArea(rows, 24);
        textArea.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return textArea;
    }

    private JComboBox<String> createStyledComboBox(String[] items, String placeholder) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
        return comboBox;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));
        return button;
    }

    private JPanel createLabeledComponent(String labelText, JComponent component) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Tahoma", Font.BOLD, 20));
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        panel.add(label, BorderLayout.NORTH);
        panel.add(component, BorderLayout.CENTER);
        return panel;
    }

    

    // Phương thức hỗ trợ tạo TextField
    private JTextField createTextField(int x, int y, int width) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, 40);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textField.setBorder(new LineBorder(Color.BLACK, 1));
        return textField;
    }

    // Phương thức hỗ trợ tạo Label
    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label.setBounds(x, y, 150, 30);
        return label;
    }
}