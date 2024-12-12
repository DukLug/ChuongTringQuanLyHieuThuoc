package userInterface;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import component.*;
import controller.*;
import entity.*;
import customDataType.*;

public class NhapHangUI extends JPanel implements ActionListener, MouseListener {
    private JTextField txtTimNCC;
    private JTextField txtTenNCC;
    private JTextField txtSDTNCC;
    private CustomButton btnChonNCC;
    private CustomButton btnTaoDonNhap;
    private CustomButton btnLamMoi;
    private JTextField txtTimSP;
    private CustomButton btnThemSP;
    private JTextField txtGhiChu;
    private JLabel lblNgayNhap;
    private JLabel lblTenNV;
    private static CustomItemList nhapHangList;
    private JComboBox<String> comboBoxLoaiDonNhap;
    private JTextField txtTongTienDonNhap;
    private JTextField txtChietKhau;
    private JTextField txtTongTienSauChietKhau;

    public NhapHangUI() {
        super();
        taoHinh();
        lamMoi();
        layThoiGianHienTai();
    }

    public void taoHinh() {
        setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
        setLayout(null);
        setBackground(Color.WHITE);

        JPanel panelTong = new JPanel();
        panelTong.setBackground(UIStyles.BackgroundColor);
        panelTong.setBounds(0, 0, UIStyles.ApplicationWidth, UIStyles.MainSectionHeight);
        add(panelTong);
        panelTong.setLayout(null);

        // Phần tìm kiếm Nhà Cung Cấp
        JPanel panelTimKiemNCC = new JPanel();
        panelTimKiemNCC.setBounds(23, 13, 955, 45);
        panelTimKiemNCC.setBorder(new LineBorder(Color.BLACK, 1, true));
        panelTong.add(panelTimKiemNCC);
        panelTimKiemNCC.setLayout(null);

        txtTimNCC = new JTextField();
        txtTimNCC.setBackground(Color.WHITE);
        txtTimNCC.setFont(new Font("Tahoma", Font.PLAIN, 23));
        txtTimNCC.setBounds(59, 2, 787, 40);
        txtTimNCC.setBorder(new MatteBorder(0, 2, 0, 2, Color.BLACK));
        UIStyles.setPlaceholder(txtTimNCC, "Tìm kiếm Nhà Cung Cấp");
        panelTimKiemNCC.add(txtTimNCC);

        btnChonNCC = new CustomButton("Chọn NCC", UIStyles.NavBarButtonStyle, null, CustomButton.CustomButtonIconSide.LEFT, () -> quayLai());
        btnChonNCC.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnChonNCC.setBounds(848, 3, 105, 40);
        btnChonNCC.setFocusable(false);
        panelTimKiemNCC.add(btnChonNCC);

        // Phần tìm kiếm Sản Phẩm
        JPanel panelTimKiemSP = new JPanel();
        panelTimKiemSP.setBounds(23, 70, 955, 45);
        panelTimKiemSP.setBorder(new LineBorder(Color.BLACK, 1, true));
        panelTong.add(panelTimKiemSP);
        panelTimKiemSP.setLayout(null);

        txtTimSP = new JTextField();
        txtTimSP.setBackground(Color.WHITE);
        txtTimSP.setFont(new Font("Tahoma", Font.PLAIN, 23));
        txtTimSP.setBounds(59, 2, 787, 40);
        txtTimSP.setBorder(new MatteBorder(0, 2, 0, 2, Color.BLACK));
        UIStyles.setPlaceholder(txtTimSP, "Tìm kiếm Sản Phẩm");
        panelTimKiemSP.add(txtTimSP);

        btnThemSP = new CustomButton("Thêm SP", UIStyles.NavBarButtonStyle, null, CustomButton.CustomButtonIconSide.LEFT, () -> quayLai());
        btnThemSP.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnThemSP.setBounds(848, 3, 105, 40);
        btnThemSP.setFocusable(false);
        panelTimKiemSP.add(btnThemSP);

        // Danh sách Sản Phẩm Nhập Hàng
        nhapHangList = new CustomItemList(
            1255, 549, 10, 50, Color.white, 
            new int[]{30, 200, 240, 240, 240, 200, 100}, 
            Color.LIGHT_GRAY, 50, 
            new String[]{"", "Tên sản phẩm", "ĐVT1", "ĐVT2", "ĐVT3", "Tổng tiền", ""}, 
            new Font("Arial", Font.BOLD, 20), 
            new ArrayList<CustomItem>()
        );
        nhapHangList.setBounds(23, 130, 1255, 654);
        panelTong.add(nhapHangList);

        // Ghi chú
        txtGhiChu = new JTextField();
        txtGhiChu.setBounds(23, 790, 1255, 56);
        panelTong.add(txtGhiChu);
        txtGhiChu.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtGhiChu.setColumns(10);
        UIStyles.setPlaceholder(txtGhiChu, "Ghi chú đơn nhập hàng");

        // Phần thông tin tổng hợp
        JPanel panelThongTin = new JPanel();
        panelThongTin.setBackground(Color.WHITE);
        panelThongTin.setBounds(1318, 0, 602, 850);
        panelTong.add(panelThongTin);
        panelThongTin.setLayout(null);

        // Tổng tiền đơn nhập
        txtTongTienDonNhap = new JTextField();
        txtTongTienDonNhap.setBounds(20, 100, 560, 40);
        txtTongTienDonNhap.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtTongTienDonNhap.setEditable(false);
        panelThongTin.add(txtTongTienDonNhap);

        // Chiết khấu
        txtChietKhau = new JTextField();
        txtChietKhau.setBounds(20, 200, 560, 40);
        txtChietKhau.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panelThongTin.add(txtChietKhau);

        // Tổng tiền sau chiết khấu
        txtTongTienSauChietKhau = new JTextField();
        txtTongTienSauChietKhau.setBounds(20, 300, 560, 40);
        txtTongTienSauChietKhau.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtTongTienSauChietKhau.setEditable(false);
        panelThongTin.add(txtTongTienSauChietKhau);

        // Nút Tạo Đơn Nhập và Làm Mới
        btnTaoDonNhap = new CustomButton("Tạo Đơn Nhập", UIStyles.NavBarButtonStyle, null, CustomButton.CustomButtonIconSide.LEFT, () -> quayLai());
        btnTaoDonNhap.setBounds(20, 400, 270, 50);
        panelThongTin.add(btnTaoDonNhap);

        btnLamMoi = new CustomButton("Làm Mới", UIStyles.NavBarButtonStyle, null, CustomButton.CustomButtonIconSide.LEFT, () -> quayLai());
        btnLamMoi.setBounds(310, 400, 270, 50);
        panelThongTin.add(btnLamMoi);

        // Đăng ký sự kiện
        txtTimNCC.addActionListener(this);
        txtTimSP.addActionListener(this);
        txtGhiChu.addActionListener(this);
        txtChietKhau.addActionListener(this);

        btnChonNCC.addActionListener(this);
        btnThemSP.addActionListener(this);
        btnTaoDonNhap.addActionListener(this);
        btnLamMoi.addActionListener(this);
    }

    // Phương thức quay lại (placeholder)
    private void quayLai() {
        // Implement later
    }

    // Phương thức lấy thời gian hiện tại
    private void layThoiGianHienTai() {
        // Implement later
    }

    // Phương thức làm mới
    private void lamMoi() {
        // Implement later
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implement action handling
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Implement mouse click handling
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Implement mouse press handling
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Implement mouse release handling
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Implement mouse enter handling
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Implement mouse exit handling
    }

    // Nested class for NhapHangRow (similar to BanHangRow)
    public static class NhapHangRow extends CustomItem implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
        // Implement similar structure to BanHangRow
        // You can customize this based on your specific requirements for nhập hàng
    }
}