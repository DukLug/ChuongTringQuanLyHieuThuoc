package userInterface;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import application.PhanMemQuanLyHieuThuoc;

import java.util.ArrayList;
import component.CustomButton;
import component.CustomTable;
import component.CustomButton.CustomButtonIconSide;
import controller.KhoCTR;
import controller.LoaiSanPhamCTR;
import controller.SanPhamCTR;
import entity.DonHuyHang;
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
    	this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
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
        bangLoHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (huyHangMode) {
                    int selectedRow = bangLoHang.getSelectedRow();
                    if (selectedRow != -1) {
                        hienThiChinhSuaLoHang(selectedRow);
                    }
                }
            }
        });
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

        JLabel lblSearch = new JLabel("Tìm kiếm theo sản phẩm:");
        lblSearch.setFont(UIStyles.BoldFont);
        txtSearch = new JTextField(15);
        txtSearch.setBounds(0, 10, 300, 40);
        txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtSearch.setBorder(new LineBorder(Color.BLACK, 1));
        CustomButton btnSearch = new CustomButton("", UIStyles.TimButtonStyle,UIStyles.FindIcon,CustomButtonIconSide.LEFT, () -> timKiemSanPham());

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

    // Phương thức bắt đầu chế độ Hủy Hàng
    private void batDauHuyHang() {
        huyHangMode = !huyHangMode;
        if (huyHangMode) {
            JOptionPane.showMessageDialog(this, 
                "Chế độ Hủy Hàng đã bật. Chọn lô hàng để điều chỉnh số lượng.", 
                "Hủy Hàng", 
                JOptionPane.INFORMATION_MESSAGE);
            huyHangBTN.setText("Hoàn Thành");
            
        } else {
            JOptionPane.showMessageDialog(this, 
                "Chế độ Hủy Hàng đã tắt.", 
                "Hủy Hàng", 
                JOptionPane.INFORMATION_MESSAGE);
            huyHangBTN.setText("Hủy Hàng");
        }
    }
    // Method to display the dialog for editing a batch
    private void hienThiChinhSuaLoHang(int rowIndex) {
        LoHang loHang = danhSachLoHang.get(rowIndex);

        // Create dialog for editing
        JDialog dialog = new JDialog();
        dialog.setTitle("Điều Chỉnh Số Lượng Lô Hàng");
        dialog.setSize(600, 500);
        dialog.setModal(true);
        dialog.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setSize(600, 500);
        GridBagConstraints gbc = new GridBagConstraints();
        SanPhamYTe sp = SanPhamCTR.timSanPhamTheoMaSanPham( loHang.getSanPham().getMaSanPham());
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Display current details
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(createStyledLabel("Mã Lô: " + loHang.getMaLo(), 20), gbc);

        gbc.gridy++;
        panel.add(createStyledLabel("Sản Phẩm: " + sp.getTenSanPham(), 20), gbc);

        gbc.gridy++;
        panel.add(createStyledLabel("Số Lượng Hiện Tại:", 20), gbc);
        gbc.gridx = 1;
        panel.add(createStyledLabel(
        		loHang.getSoLuongDonViTinh1() +  " " + sp.getDonViTinh1().toString() + ", "+
        	            loHang.getSoLuongDonViTinh2() +  " " + sp.getDonViTinh2().toString() + ", "+
        	            loHang.getSoLuongDonViTinh3() +  " " + sp.getDonViTinh3().toString() + ", ", 20), gbc);

        // Input fields for new quantities
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(createStyledLabel("Số Lượng " + sp.getDonViTinh1() + " Mới:", 20), gbc);
        gbc.gridx = 1;
        JTextField txtSoLuongDVT1 = createStyledTextField(String.valueOf(loHang.getSoLuongDonViTinh1()), 10);
        panel.add(txtSoLuongDVT1, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(createStyledLabel("Số Lượng " + sp.getDonViTinh2() + " Mới:", 20), gbc);
        gbc.gridx = 1;
        JTextField txtSoLuongDVT2 = createStyledTextField(String.valueOf(loHang.getSoLuongDonViTinh2()), 10);
        panel.add(txtSoLuongDVT2, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(createStyledLabel("Số Lượng " + sp.getDonViTinh3() + " Mới:", 20), gbc);
        gbc.gridx = 1;
        JTextField txtSoLuongDVT3 = createStyledTextField(String.valueOf(loHang.getSoLuongDonViTinh3()), 10);
        panel.add(txtSoLuongDVT3, gbc);

        // Update button
        gbc.gridx = 1;
        gbc.gridy++;
        CustomButton btnCapNhat = new CustomButton("Cập Nhật", UIStyles.BanHangButtonStyle,() -> {
            try {
                int soLuongDVT1 = Integer.parseInt(txtSoLuongDVT1.getText().trim());
                int soLuongDVT2 = Integer.parseInt(txtSoLuongDVT2.getText().trim());
                int soLuongDVT3 = Integer.parseInt(txtSoLuongDVT3.getText().trim());

                if (soLuongDVT1 < 0 || soLuongDVT2 < 0 || soLuongDVT3 < 0) {
                    throw new IllegalArgumentException("Số lượng không được âm");
                }
                if (soLuongDVT1 > loHang.getSoLuongDonViTinh1() || soLuongDVT2 > loHang.getSoLuongDonViTinh2() || soLuongDVT3 > loHang.getSoLuongDonViTinh3()) {
                	throw new IllegalArgumentException("Số lượng phải nhỏ hơn hoặc bằng số lượng cũ");
                }
                if (soLuongDVT1 == loHang.getSoLuongDonViTinh1() && soLuongDVT2 == loHang.getSoLuongDonViTinh2() && soLuongDVT3 == loHang.getSoLuongDonViTinh3()) {
                	throw new IllegalArgumentException("Số liệu chưa được thay đổi");
                }
                LocalDate localDate = LocalDate.now();
                
                // Convert it to java.sql.Date
                Date sqlDate = Date.valueOf(localDate);
                
                DonHuyHang donHuy = new DonHuyHang(KhoCTR.layMaDonHuyMoi(), sqlDate, 
                		loHang.getSoLuongDonViTinh1()-soLuongDVT1,
                		loHang.getSoLuongDonViTinh2()-soLuongDVT2,
                		loHang.getSoLuongDonViTinh3()-soLuongDVT3,
                		loHang, 
                		PhanMemQuanLyHieuThuoc.nhanVienHienTai
                		);
                
                loHang.setSoLuongDonViTinh1(soLuongDVT1);
                loHang.setSoLuongDonViTinh2(soLuongDVT2);
                loHang.setSoLuongDonViTinh3(soLuongDVT3);

                ;
                boolean capNhatThanhCong = KhoCTR.capNhatLoHang(loHang) && KhoCTR.themDonHuy(donHuy);
                
                
                
                if (capNhatThanhCong) {
                    JOptionPane.showMessageDialog(dialog, "Cập nhật lô hàng thành công!", "Thành Công", JOptionPane.INFORMATION_MESSAGE);
                    refreshDanhSachLoHang();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Cập nhật lô hàng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Vui lòng nhập số lượng hợp lệ!", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(btnCapNhat, gbc);

        dialog.add(panel);
        dialog.setVisible(true);
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
    private JLabel createStyledLabel(String text, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
        return label;
    }

    // Helper method to create styled JTextField
    private JTextField createStyledTextField(String text, int columns) {
        JTextField textField = new JTextField(text, columns);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        return textField;
    }

    // Helper method to create styled JButton
    private JButton createStyledButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button.addActionListener(actionListener);
        return button;
    }
    public void refreshDanhSachLoHang() {
        danhSachLoHang = KhoCTR.layDanhSachTatCaLoHang();
        chuanBiDuLieu();
        bangLoHang.capNhatDuLieu(duLieuBang);
    }
}
