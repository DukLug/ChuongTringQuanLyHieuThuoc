package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import component.CustomButton;
import component.CustomPanel;
import component.CustomTable;
import customDataType.TrangThaiTaiKhoan;
import entity.TaiKhoan;
import controller.NhanVienCTR;
import controller.TaiKhoanCTR;

public class TaiKhoanUI extends JPanel {
    // Data-related fields
    private ArrayList<TaiKhoan> danhSachTaiKhoan = TaiKhoanCTR.layDanhSachTatCaTaiKhoan();
    private String[][] duLieuBang = new String[][] {};
    private String[] tenCot = new String[] {"Mã Tài Khoản", "Tên Đăng Nhập", "Trạng Thái", "Nhân Viên"};

    // UI Components
    private CustomTable bangTaiKhoan;
    private JTextField txtMaTaiKhoan, txtTenDangNhap, txtMatKhau;
    private JRadioButton rdHoatDong, rdKhoa, rdNgungHoatDong;
    private ButtonGroup groupTrangThai;
    private CustomButton btnTimKiem, btnThem, btnCapNhat;
    private JPanel panelBang, panelNhapLieu;
    private JLabel lblTieuDe;

    public TaiKhoanUI() {
        setLayout(null);
        setBackground(UIStyles.BackgroundColor);
        
        taoHinh();
        chuanBiDuLieu();
        bangTaiKhoan.capNhatDuLieu(duLieuBang);
    }

    public void taoHinh() {
        // Tiêu đề
        lblTieuDe = new JLabel("Quản Lý Tài Khoản");
        lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblTieuDe.setBounds(700, 10, 300, 49);
        add(lblTieuDe);

        // Bảng tài khoản
        panelBang = new JPanel(new BorderLayout());
        panelBang.setBounds(50, 70, 900, 750);
        panelBang.setBackground(UIStyles.BackgroundColor);

        // Bảng dữ liệu
        bangTaiKhoan = new CustomTable(
            duLieuBang, 
            tenCot, 
            UIStyles.NhanVienTableHeaderStyle, 
            UIStyles.NhanVienTableRowStyle, 
            20,
            new int[] {200, 200, 200, 300}
        );
        bangTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        bangTaiKhoan.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                hienThiChiTietTaiKhoan();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(bangTaiKhoan);
        scrollPane.setMaximumSize(new Dimension(900, 700));
        panelBang.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setBorder(BorderFactory.createLineBorder(UIStyles.LabelFontColorGreen));

        add(panelBang);

        // Panel nhập liệu
        JPanel panelEast = new JPanel();
        panelEast.setBounds(1000, 70, 900, 750);
        panelEast.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelEast.setBackground(Color.white);
        panelNhapLieu = new JPanel();
        panelNhapLieu.setBorder(new TitledBorder(
            new LineBorder(Color.BLACK, 1), 
            "Thông Tin Tài Khoản", 
            TitledBorder.LEFT, 
            TitledBorder.TOP, 
            new Font("Tahoma", Font.PLAIN, 20), 
            Color.BLACK
        ));
        panelNhapLieu.setBackground(Color.WHITE);
        panelNhapLieu.setBounds(1000, 70, 900, 350);
        panelNhapLieu.setLayout(null);

        // Các trường nhập liệu
        JLabel lblMaTaiKhoan = createLabel("Mã Tài Khoản:", 42, 50);
        txtMaTaiKhoan = createTextField(250, 50);

        JLabel lblTenDangNhap = createLabel("Tên Đăng Nhập:", 42, 100);
        txtTenDangNhap = createTextField(250, 100);

        JLabel lblMatKhau = createLabel("Mật Khẩu:", 42, 150);
        txtMatKhau = createTextField(250, 150);

        // Trạng Thái Tài Khoản
        JLabel lblTrangThai = createLabel("Trạng Thái:", 42, 200);
        
        // Tạo nhóm radio button
        groupTrangThai = new ButtonGroup();
        
        // Tạo các radio button
        rdHoatDong = createRadioButton("Hoạt Động", 250, 200);
        rdKhoa = createRadioButton("Khóa", 450, 200);
        rdNgungHoatDong = createRadioButton("Ngưng Hoạt Động", 450, 200);
        
        // Thêm các radio button vào nhóm
        groupTrangThai.add(rdHoatDong);
        //groupTrangThai.add(rdKhoa);
        groupTrangThai.add(rdNgungHoatDong);

        // Các nút chức năng
        btnThem = new CustomButton("Thêm", UIStyles.ThemButtonStyle, () -> themTaiKhoan());
        btnThem.setBounds(200, 300, 150, 40);
        btnThem.setFont(new Font("Tahoma", Font.PLAIN, 20));

        btnCapNhat = new CustomButton("Cập Nhật", UIStyles.CapNhatButtonStyle, () -> capNhatTaiKhoan());
        btnCapNhat.setBounds(400, 300, 150, 40);
        btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 20));

        panelNhapLieu.add(lblMaTaiKhoan);
        panelNhapLieu.add(txtMaTaiKhoan);
        panelNhapLieu.add(lblTenDangNhap);
        panelNhapLieu.add(txtTenDangNhap);
        panelNhapLieu.add(lblMatKhau);
        panelNhapLieu.add(txtMatKhau);
        panelNhapLieu.add(lblTrangThai);
        panelNhapLieu.add(rdHoatDong);
        //panelNhapLieu.add(rdKhoa);
        panelNhapLieu.add(rdNgungHoatDong);
        panelNhapLieu.add(btnThem);
        panelNhapLieu.add(btnCapNhat);
        
        panelEast.add(panelNhapLieu);
        add(panelNhapLieu);
    }

    // Chuẩn bị dữ liệu cho bảng
    private void chuanBiDuLieu() {
        duLieuBang = new String[danhSachTaiKhoan.size()][4];
        for (int i = 0; i < danhSachTaiKhoan.size(); i++) {
            TaiKhoan taiKhoan = danhSachTaiKhoan.get(i);
            duLieuBang[i][0] = taiKhoan.getMaTaiKhoan();
            duLieuBang[i][1] = taiKhoan.getTenDangNhap();
            duLieuBang[i][2] = taiKhoan.getTrangThaiTaiKhoan().toString();
            duLieuBang[i][3] = NhanVienCTR.timTheoMa(taiKhoan.getMaTaiKhoan()).getHoTen();
        }
    }


    // Phương thức hỗ trợ tạo radio button
    private JRadioButton createRadioButton(String text, int x, int y) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setBounds(x, y, 200, 30);
        radioButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        radioButton.setBackground(Color.WHITE);
        return radioButton;
    }

    // Phương thức hỗ trợ tạo label
    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label.setBounds(x, y, 200, 30);
        return label;
    }

    // Phương thức hỗ trợ tạo text field
    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 600, 40);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textField.setBorder(new LineBorder(Color.BLACK, 1));
        return textField;
    }

    // Thêm tài khoản
    private void themTaiKhoan() {
        try {
            String maTaiKhoan = txtMaTaiKhoan.getText();
            String tenDangNhap = txtTenDangNhap.getText();
            String matKhau = txtMatKhau.getText();

            // Xác định trạng thái tài khoản từ radio button
            TrangThaiTaiKhoan trangThai;
            if (rdHoatDong.isSelected()) {
                trangThai = TrangThaiTaiKhoan.HoatDong;
            } else if (rdKhoa.isSelected()) {
                trangThai = TrangThaiTaiKhoan.KhongCoDuLieu;
            } else if (rdNgungHoatDong.isSelected()) {
                trangThai = TrangThaiTaiKhoan.NgungHoatDong;
            } else {
                throw new IllegalArgumentException("Vui lòng chọn trạng thái tài khoản");
            }

            TaiKhoan taiKhoan = new TaiKhoan(maTaiKhoan, tenDangNhap, matKhau, trangThai);
            
            boolean ketQua = TaiKhoanCTR.themTaiKhoan(taiKhoan);
            
            if (ketQua) {
                danhSachTaiKhoan.add(taiKhoan);
                chuanBiDuLieu();
                bangTaiKhoan.capNhatDuLieu(duLieuBang);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Cập nhật tài khoản
    private void capNhatTaiKhoan() {
        try {
            String maTaiKhoan = txtMaTaiKhoan.getText();
            String tenDangNhap = txtTenDangNhap.getText();
            String matKhau = txtMatKhau.getText();

            // Xác định trạng thái tài khoản từ radio button
            TrangThaiTaiKhoan trangThai;
            if (rdHoatDong.isSelected()) {
                trangThai = TrangThaiTaiKhoan.HoatDong;
            } else if (rdKhoa.isSelected()) {
                trangThai = TrangThaiTaiKhoan.KhongCoDuLieu;
            } else if (rdNgungHoatDong.isSelected()) {
                trangThai = TrangThaiTaiKhoan.NgungHoatDong;
            } else {
                throw new IllegalArgumentException("Vui lòng chọn trạng thái tài khoản");
            }

            TaiKhoan taiKhoan = timTaiKhoan(maTaiKhoan);
            
            if (taiKhoan != null) {
                taiKhoan.setTenDangNhap(tenDangNhap);
                taiKhoan.setMatKhau(matKhau);
                taiKhoan.setTrangThaiTaiKhoan(trangThai);
                
                boolean ketQua = TaiKhoanCTR.capNhatTaiKhoan(taiKhoan);
                
                if (ketQua) {
                    chuanBiDuLieu();
                    bangTaiKhoan.capNhatDuLieu(duLieuBang);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Hiển thị chi tiết tài khoản khi chọn từ bảng
    private void hienThiChiTietTaiKhoan() {
        int selectedRow = bangTaiKhoan.getSelectedRow();
        if (selectedRow != -1) {
            String maTaiKhoan = (String) bangTaiKhoan.getValueAt(selectedRow, 0);
            TaiKhoan selectedTaiKhoan = timTaiKhoan(maTaiKhoan);
            
            if (selectedTaiKhoan != null) {
                txtMaTaiKhoan.setText(selectedTaiKhoan.getMaTaiKhoan());
                txtTenDangNhap.setText(selectedTaiKhoan.getTenDangNhap());
                txtMatKhau.setText(selectedTaiKhoan.getMatKhau());
                
                // Đặt radio button phù hợp với trạng thái tài khoản
                switch(selectedTaiKhoan.getTrangThaiTaiKhoan()) {
                    case HoatDong:
                        rdHoatDong.setSelected(true);
                        break;
                    case KhongCoDuLieu:
                        rdKhoa.setSelected(true);
                        break;
                    case NgungHoatDong:
                        rdNgungHoatDong.setSelected(true);
                        break;
                }
            }
        }
    }
    // Tìm tài khoản theo mã
    private TaiKhoan timTaiKhoan(String maTaiKhoan) {
        for (TaiKhoan taiKhoan : danhSachTaiKhoan) {
            if (taiKhoan.getMaTaiKhoan().equals(maTaiKhoan)) {
                return taiKhoan;
            }
        }
        return null;
    }
}