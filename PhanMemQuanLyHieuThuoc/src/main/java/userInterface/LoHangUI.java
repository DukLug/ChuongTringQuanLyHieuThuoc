package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
    
    public LoHangUI() {
        super();
        
        taoHinh();
        chuanBiDuLieu();
        bangLoHang.capNhatDuLieu(duLieuBang);
    }
    
    public void taoHinh() {
        setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
        this.setBackground(Color.LIGHT_GRAY);
        this.add(new JLabel("Quản Lý Lô Hàng"));
        
        // Create custom table
        bangLoHang = new CustomTable(duLieuBang, tenCot, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle, 20,
                new int[] {200, 200, 200, 150, 100, 100, 100, 150, 300}
        );
       
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(bangLoHang);
        scrollPane.setPreferredSize(new Dimension(1800, 600));
        add(scrollPane);
        
        JScrollBar sb = scrollPane.getVerticalScrollBar();
        add(new CustomButton("Thêm Lô Hàng", UIStyles.LabelBarButtonStyle, UIStyles.HelpIcon, 
                CustomButtonIconSide.LEFT, () -> themLoHang()));
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
                loHang.getSanPham() != null ? loHang.getSanPham().getTenSanPham() : "Không có sản phẩm"
            };
        }
    }
    
    private void themLoHang() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Thêm Lô Hàng Mới");
        dialog.setSize(500, 600);
        dialog.setModal(true);
        dialog.setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridBagLayout());
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