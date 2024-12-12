package userInterface;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import component.CustomButton;
import component.CustomTable;
import component.CustomButton.CustomButtonIconSide;
import controller.KhoCTR;
import controller.SanPhamCTR;
import dao.LoHangDAO;
import entity.LoHang;
import entity.SanPhamYTe;
import entity.ChiTietDonNhap;

public class LoHangUI extends JPanel {
    
    private ArrayList<LoHang> danhSachLoHang = KhoCTR.layDanhSachTatCaLoHang();
    private String[][] duLieuBang = new String[][] {};
    private String[] tenCot = new String[] {"Mã Lô", "Ngày Sản Xuất", "Hạn Sử Dụng", "Giá Nhập", "SL ĐVT1", "SL ĐVT2", "SL ĐVT3", "Vị Trí", "Sản Phẩm"};
    
    private CustomTable bangLoHang;
    

    // Biến để theo dõi chế độ Hủy Hàng
    private boolean huyHangMode = false;
    CustomButton huyHangBTN = new CustomButton("Hủy Hàng", UIStyles.DoiTraButtonStyle,() -> batDauHuyHang()); 

    public LoHangUI() {
        super();
        
        taoHinh();
        chuanBiDuLieu();
        bangLoHang.capNhatDuLieu(duLieuBang);
    }
    
    public void taoHinh() {
        setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));

        this.setBackground(UIStyles.BackgroundColor);
        
        // JLabel tiêu đề "Danh sách nhân viên"
        JPanel titlePanel = new JPanel();
     	JLabel lblTitle= new JLabel("Quản lý lô hàng");
     	lblTitle.setFont(new Font("Tahoma", Font.BOLD, 35));
     	titlePanel.add(lblTitle);
     	titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        
        // Create custom table
        bangLoHang = new CustomTable(duLieuBang, tenCot, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle, 20,
                new int[] {200, 200, 200, 150, 100, 100, 100, 150, 300}
        );
       
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(bangLoHang);
        scrollPane.setPreferredSize(new Dimension(1800, 600));


        
        add(titlePanel);
        add(scrollPane);
        // Thêm nút Thêm Lô Hàng
        add(new CustomButton("Thêm Lô Hàng", UIStyles.BanHangButtonStyle, 
                () -> themLoHang()));
        
        // Thêm nút Hủy Hàng
        add(huyHangBTN);
        
        // Sự kiện click chuột trên bảng
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
    }
    
    // Phương thức bắt đầu chế độ Hủy Hàng
    private void batDauHuyHang() {
        huyHangMode = !huyHangMode;
        if (huyHangMode) {
            JOptionPane.showMessageDialog(this, 
                "Chế độ Hủy Hàng đã bật. Chọn lô hàng để điều chỉnh số lượng.", 
                "Hủy Hàng", 
                JOptionPane.INFORMATION_MESSAGE);
            huyHangBTN.setText("Hoàn thành");
            
        } else {
            JOptionPane.showMessageDialog(this, 
                "Chế độ Hủy Hàng đã tắt.", 
                "Hủy Hàng", 
                JOptionPane.INFORMATION_MESSAGE);
            huyHangBTN.setText("Hủy Hàng");
        }
    }
    
 // Helper method to create styled JLabel
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

                loHang.setSoLuongDonViTinh1(soLuongDVT1);
                loHang.setSoLuongDonViTinh2(soLuongDVT2);
                loHang.setSoLuongDonViTinh3(soLuongDVT3);

                boolean capNhatThanhCong = KhoCTR.capNhatLoHang(loHang);

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
    
    private void chuanBiDuLieu() {
        tenCot = new String[] {"Mã Lô", "Ngày Sản Xuất", "Hạn Sử Dụng", "Giá Nhập", "SL ĐVT1", "SL ĐVT2", "SL ĐVT3", "Vị Trí", "Sản Phẩm"};
        duLieuBang = new String[danhSachLoHang.size()][tenCot.length];
        
        for (int i = 0; i < danhSachLoHang.size(); i++) {
            LoHang loHang = danhSachLoHang.get(i);
            duLieuBang[i] = new String[] {
                loHang.getMaLo(),
                loHang.getNgaySanXuat() != null ? loHang.getNgaySanXuat().toString() : "N/A",
                loHang.getHanSuDung() != null ? loHang.getHanSuDung().toString() : "N/A",
                loHang.getGiaNhap() != null ? loHang.getGiaNhap().toString() + "đ" : "0đ",
                String.valueOf(loHang.getSoLuongDonViTinh1()),
                String.valueOf(loHang.getSoLuongDonViTinh2()),
                String.valueOf(loHang.getSoLuongDonViTinh3()),
                loHang.getViTri() != null ? loHang.getViTri() : "Chưa xác định",

                loHang.getSanPham() != null ? SanPhamCTR.timSanPhamTheoMaSanPham( loHang.getSanPham().getMaSanPham()).getTenSanPham() : "Không có sản phẩm"
                	
            };
            

        }
    }
    
    private void themLoHang() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Thêm Lô Hàng Mới");
        dialog.setSize(600, 800);
        dialog.setModal(true);
        dialog.setLocationRelativeTo(null);
        
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Mã Lô
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Mã Lô:"), gbc);
        gbc.gridx = 1;
        JTextField txtMaLo = new JTextField(20);
        panel.add(txtMaLo, gbc);
        
        // Ngày Sản Xuất
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Ngày Sản Xuất (yyyy-mm-dd):"), gbc);
        gbc.gridx = 1;
        JTextField txtNgaySanXuat = new JTextField(20);
        panel.add(txtNgaySanXuat, gbc);
        
        // Hạn Sử Dụng
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Hạn Sử Dụng (yyyy-mm-dd):"), gbc);
        gbc.gridx = 1;
        JTextField txtHanSuDung = new JTextField(20);
        panel.add(txtHanSuDung, gbc);
        
        // Giá Nhập
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Giá Nhập:"), gbc);
        gbc.gridx = 1;
        JTextField txtGiaNhap = new JTextField(20);
        panel.add(txtGiaNhap, gbc);
        
        // Số Lượng Đơn Vị Tính 1
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Số Lượng ĐVT1:"), gbc);
        gbc.gridx = 1;
        JTextField txtSoLuongDVT1 = new JTextField(20);
        panel.add(txtSoLuongDVT1, gbc);
        
        // Số Lượng Đơn Vị Tính 2
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Số Lượng ĐVT2:"), gbc);
        gbc.gridx = 1;
        JTextField txtSoLuongDVT2 = new JTextField(20);
        panel.add(txtSoLuongDVT2, gbc);
        
        // Số Lượng Đơn Vị Tính 3
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Số Lượng ĐVT3:"), gbc);
        gbc.gridx = 1;
        JTextField txtSoLuongDVT3 = new JTextField(20);
        panel.add(txtSoLuongDVT3, gbc);
        
        // Vị Trí
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Vị Trí:"), gbc);
        gbc.gridx = 1;
        JTextField txtViTri = new JTextField(20);
        panel.add(txtViTri, gbc);
        
        // Sản Phẩm
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(new JLabel("Sản Phẩm:"), gbc);
        gbc.gridx = 1;
        ArrayList<SanPhamYTe> dsSanPham = SanPhamCTR.layDanhSachTatCaSanPham();
        JComboBox<String> cbSanPham = new JComboBox<>();
        for (SanPhamYTe sp : dsSanPham) {
            cbSanPham.addItem(sp.getMaSanPham() + " - " + sp.getTenSanPham());
        }
        panel.add(cbSanPham, gbc);
        
        // Chi Tiết Đơn Nhập (placeholder)
        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(new JLabel("Mã Chi Tiết Đơn Nhập:"), gbc);
        gbc.gridx = 1;
        JTextField txtMaChiTietDonNhap = new JTextField(20);
        panel.add(txtMaChiTietDonNhap, gbc);
        
        // Nút Thêm
        gbc.gridx = 1;
        gbc.gridy = 10;
        JButton btnThem = new JButton("Thêm Lô Hàng");
        btnThem.addActionListener(e -> {
            try {
                // Validate and create LoHang object
                String maLo = txtMaLo.getText().trim();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date ngaySanXuat = new Date(sdf.parse(txtNgaySanXuat.getText().trim()).getTime());
                Date hanSuDung = new Date(sdf.parse(txtHanSuDung.getText().trim()).getTime());
                BigDecimal giaNhap = new BigDecimal(txtGiaNhap.getText().trim());
                int soLuongDVT1 = Integer.parseInt(txtSoLuongDVT1.getText().trim());
                int soLuongDVT2 = Integer.parseInt(txtSoLuongDVT2.getText().trim());
                int soLuongDVT3 = Integer.parseInt(txtSoLuongDVT3.getText().trim());
                String viTri = txtViTri.getText().trim();
                
                // Get selected SanPham
                String selectedSanPham = (String) cbSanPham.getSelectedItem();
                String maSanPham = selectedSanPham.split(" - ")[0];
                SanPhamYTe sanPham = new SanPhamYTe(maSanPham);
                
                // Placeholder for ChiTietDonNhap
                ChiTietDonNhap chiTietDonNhap = new ChiTietDonNhap(txtMaChiTietDonNhap.getText().trim());
                
                LoHang loHang = new LoHang(maLo, ngaySanXuat, hanSuDung, giaNhap, 
                    soLuongDVT1, soLuongDVT2, soLuongDVT3, 
                    viTri, sanPham, chiTietDonNhap);
                
                // Add to database
                boolean themThanhCong = LoHangDAO.themLoHang(loHang);
                
                if (themThanhCong) {
                    JOptionPane.showMessageDialog(dialog, "Thêm lô hàng thành công!");
                    // Refresh data
                    danhSachLoHang = KhoCTR.layDanhSachTatCaLoHang();
                    chuanBiDuLieu();
                    bangLoHang.capNhatDuLieu(duLieuBang);
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Thêm lô hàng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (ParseException | IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(dialog, 
                    "Lỗi nhập liệu: " + ex.getMessage(), 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(btnThem, gbc);
        
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    // Optional: Method to refresh the table data
    public void refreshDanhSachLoHang() {
        danhSachLoHang = KhoCTR.layDanhSachTatCaLoHang();
        chuanBiDuLieu();
        bangLoHang.capNhatDuLieu(duLieuBang);
    }
}