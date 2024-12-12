package userInterface;

import javax.swing.*;

import application.PhanMemQuanLyHieuThuoc;
import component.CustomButton;
import controller.NhanVienCTR;
import controller.TaiKhoanCTR;
import customDataType.TrangThaiTaiKhoan;
import entity.NhanVien;
import entity.TaiKhoan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DangNhapUI extends JFrame {
    private JTextField txtTenDangNhap;
    private JPasswordField txtMatKhau;
    private CustomButton btnDangNhap;
    private CustomButton btnThoat;

    public DangNhapUI() {
        setTitle("Đăng Nhập Hệ Thống");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create main panel with vertical layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel lblTitle = createStyledLabel("ĐĂNG NHẬP", 24);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Username
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTenDangNhap = createStyledLabel("Tên đăng nhập:", 16);
        txtTenDangNhap = createStyledTextField("", 30);
        txtTenDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txtTenDangNhap.setBounds(0, 0, 100, 60);
        userPanel.add(lblTenDangNhap);
        userPanel.add(txtTenDangNhap);
        mainPanel.add(userPanel);

        // Password
        JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblMatKhau = createStyledLabel("Mật khẩu:", 16);
        txtMatKhau = createStyledPasswordField(30);
        txtMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txtMatKhau.setBounds(0, 0, 100, 60);
        passPanel.add(lblMatKhau);
        passPanel.add(txtMatKhau);
        mainPanel.add(passPanel);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnDangNhap = new CustomButton("Đăng nhập",  UIStyles.BanHangButtonStyle, ()->xuLyDangNhap());
        
        btnThoat = new CustomButton("Thoát ra", UIStyles.DoiTraButtonStyle, ()->System.exit(0));

        buttonPanel.add(btnDangNhap);
        buttonPanel.add(btnThoat);
        mainPanel.add(buttonPanel);

        add(mainPanel);
    }

    // Styled label method (similar to your existing method)
    private JLabel createStyledLabel(String text, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
        return label;
    }

    // Styled text field method
    private JTextField createStyledTextField(String text, int columns) {
        JTextField textField = new JTextField(text, columns);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        return textField;
    }

    // Styled password field method
    private JPasswordField createStyledPasswordField(int columns) {
        JPasswordField passwordField = new JPasswordField(columns);
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        return passwordField;
    }

    // Styled button method
    private JButton createStyledButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button.addActionListener(actionListener);
        return button;
    }

    // Login validation method
    private void xuLyDangNhap() {
        String tenDangNhap = txtTenDangNhap.getText().trim();
        String matKhau = TaiKhoanCTR.hashPassword(new String(txtMatKhau.getPassword()));

        // Check for empty fields
        if (tenDangNhap.isEmpty() || matKhau.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu", 
                "Lỗi Đăng Nhập", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Get list of all accounts
        ArrayList<TaiKhoan> danhSachTaiKhoan = TaiKhoanCTR.layDanhSachTatCaTaiKhoan();

        // Flag to track login success
        boolean dangNhapThanhCong = false;
        NhanVien nhanVienDangNhap = null;
        TaiKhoan taiKhoanDangNhap = null;

        // Loop through accounts to check credentials
        for (TaiKhoan taiKhoan : danhSachTaiKhoan) {
            if (taiKhoan.getTenDangNhap().equals(tenDangNhap) && 
                taiKhoan.getMatKhau().equals(matKhau)) {
                // Check account status
                if (taiKhoan.getTrangThaiTaiKhoan() == TrangThaiTaiKhoan.HoatDong) {
                    dangNhapThanhCong = true;
                    
                    // Find the corresponding NhanVien
                    nhanVienDangNhap = NhanVienCTR.timTheoMa(taiKhoan.getMaTaiKhoan());
                    taiKhoanDangNhap = taiKhoan;
                    break;
                } else {
                    // Account exists but is not active
                    JOptionPane.showMessageDialog(this, 
                        "Tài khoản của bạn đã bị khóa hoặc không hoạt động", 
                        "Lỗi Đăng Nhập", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }

        // Handle login result
        if (dangNhapThanhCong && nhanVienDangNhap != null) {
            // Set the current logged-in employee
            PhanMemQuanLyHieuThuoc.nhanVienHienTai = nhanVienDangNhap;
            PhanMemQuanLyHieuThuoc.taiKhoanHienTai = taiKhoanDangNhap;
            
            // Close login window
            dispose();
            
            // Open main application window
            TrangChuUI trangChuUI = new TrangChuUI(false);
        } else {
            // Login failed
            JOptionPane.showMessageDialog(this, 
                "Tên đăng nhập hoặc mật khẩu không chính xác", 
                "Lỗi Đăng Nhập", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}