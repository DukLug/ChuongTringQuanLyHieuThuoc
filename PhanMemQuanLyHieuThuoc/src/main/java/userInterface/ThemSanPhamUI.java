package userInterface;

import java.awt.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import controller.LoaiSanPhamCTR;
import controller.NhaCungCapCTR;
import customDataType.DonViTinh;
import entity.LoaiSanPham;
import entity.NhaCungCap;

public class ThemSanPhamUI extends JPanel {

    private JTextField tenSanPhamField;
    private JDateChooser ngaySanXuatChooser;
    private JDateChooser hanSuDungChooser;
    private JTextField nuocSanXuatField;
    private JComboBox<DonViTinh> donViTinhComboBox;
    private JComboBox<NhaCungCap> nhaCungCapComboBox;
    private JComboBox<LoaiSanPham> loaiSanPhamComboBox;
    private JSpinner giaBanSpinner;
    private JTextField maVachField;
    private JTextField ghiChuField;

    public ThemSanPhamUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Title Panel
        JLabel titleLabel = new JLabel("Thêm Sản Phẩm", JLabel.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setBorder(new EmptyBorder(10, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;

        // Input Fields
        tenSanPhamField = createStyledTextField();
        ngaySanXuatChooser = createStyledDateChooser();
        hanSuDungChooser = createStyledDateChooser();
        nuocSanXuatField = createStyledTextField();
        donViTinhComboBox = createStyledComboBox(DonViTinh.values());
        nhaCungCapComboBox = createStyledComboBox(layDanhSachNhaCungCap());
        loaiSanPhamComboBox = createStyledComboBox(layDanhSachLoaiSanPham());
        giaBanSpinner = createStyledSpinner();
        maVachField = createStyledTextField();
        ghiChuField = createStyledTextField();

        // Add Components to Form Panel
        addInputField(formPanel, "Tên Sản Phẩm:", tenSanPhamField, gbc, 0);
        addInputField(formPanel, "Ngày Sản Xuất:", ngaySanXuatChooser, gbc, 1);
        addInputField(formPanel, "Hạn Sử Dụng:", hanSuDungChooser, gbc, 2);
        addInputField(formPanel, "Nước Sản Xuất:", nuocSanXuatField, gbc, 3);
        addInputField(formPanel, "Đơn Vị Tính:", donViTinhComboBox, gbc, 4);
        addInputField(formPanel, "Nhà Cung Cấp:", nhaCungCapComboBox, gbc, 5);
        addInputField(formPanel, "Loại Sản Phẩm:", loaiSanPhamComboBox, gbc, 6);
        addInputField(formPanel, "Giá Bán:", giaBanSpinner, gbc, 7);
        addInputField(formPanel, "Mã Vạch:", maVachField, gbc, 8);
        addInputField(formPanel, "Ghi Chú:", ghiChuField, gbc, 9);

        add(formPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);

        JButton saveButton = new JButton("Lưu");
        saveButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        saveButton.addActionListener(e -> luuSanPham());
        buttonPanel.add(saveButton);

        JButton cancelButton = new JButton("Hủy");
        cancelButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        cancelButton.addActionListener(e -> huyBo());
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addInputField(JPanel panel, String labelText, JComponent inputField, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Tahoma", Font.BOLD, 16));
        panel.add(label, gbc);

        gbc.gridx = 1;
        panel.add(inputField, gbc);
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.BOLD, 18));
        textField.setPreferredSize(new Dimension(200, 30));
        return textField;
    }

    private JDateChooser createStyledDateChooser() {
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setFont(new Font("Tahoma", Font.BOLD, 18));
        dateChooser.setPreferredSize(new Dimension(200, 30));
        dateChooser.setDateFormatString("yyyy-MM-dd");
        return dateChooser;
    }

    private <T> JComboBox<T> createStyledComboBox(T[] items) {
        JComboBox<T> comboBox = new JComboBox<>(items);
        comboBox.setFont(new Font("Tahoma", Font.BOLD, 18));
        comboBox.setPreferredSize(new Dimension(200, 30));
        return comboBox;
    }

    private JSpinner createStyledSpinner() {
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, Double.MAX_VALUE, 1.0));
        spinner.setFont(new Font("Tahoma", Font.BOLD, 18));
        spinner.setPreferredSize(new Dimension(200, 30));
        return spinner;
    }

    private NhaCungCap[] layDanhSachNhaCungCap() {
        List<NhaCungCap> danhSach = NhaCungCapCTR.layDanhSachTatCaNhaCungCap();
        return danhSach.toArray(new NhaCungCap[0]);
    }

    private LoaiSanPham[] layDanhSachLoaiSanPham() {
        List<LoaiSanPham> danhSach = LoaiSanPhamCTR.layDanhSachTatCaLoaiSanPham();
        return danhSach.toArray(new LoaiSanPham[0]);
    }

    private void luuSanPham() {
        try {
            String tenSanPham = tenSanPhamField.getText();
            Date ngaySanXuat = new Date(ngaySanXuatChooser.getDate().getTime());
            Date hanSuDung = new Date(hanSuDungChooser.getDate().getTime());
            String nuocSanXuat = nuocSanXuatField.getText();
            DonViTinh donViTinh = (DonViTinh) donViTinhComboBox.getSelectedItem();
            NhaCungCap nhaCungCap = (NhaCungCap) nhaCungCapComboBox.getSelectedItem();
            LoaiSanPham loaiSanPham = (LoaiSanPham) loaiSanPhamComboBox.getSelectedItem();
            BigDecimal giaBan = BigDecimal.valueOf((Double) giaBanSpinner.getValue());
            String maVach = maVachField.getText();
            String ghiChu = ghiChuField.getText();

            // Call controller to save the data
            System.out.println("Sản phẩm đã được lưu!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void huyBo() {
        System.out.println("Hủy bỏ thêm sản phẩm.");
    }
}