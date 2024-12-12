package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import component.CustomButton;
import component.CustomTable;
import component.CustomButton.CustomButtonIconSide;
import controller.LoaiSanPhamCTR;
import dao.LoaiSanPhamDAO;
import dao.SanPhamYTeDAO;
import entity.LoaiSanPham;
import entity.SanPhamYTe;

public class LoaiSanPhamUI extends JPanel{
	
	//data
	private ArrayList<LoaiSanPham> dsLoaiSanPham = LoaiSanPhamCTR.layDanhSachTatCaLoaiSanPham();
	private String[][] duLieuBang = new String[][] {};
	private String[] tenCot = new String[] {"Mã loại", "Tên loại"};
	
	private CustomTable bangLoaiSP;
	
	public LoaiSanPhamUI() {
		super();
		taoHinh();
		
		capNhatDuLieu();
	}
	
	public void taoHinh() {
		setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.setLayout(new BorderLayout());
		
		//Title
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("LOẠI SẢN PHẨM");
        title.setFont(UIStyles.TitleFont);
        titlePanel.add(title);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titlePanel.setBackground(UIStyles.BackgroundColor);
		
        //
        JPanel tablePanel = new JPanel();
		bangLoaiSP = new CustomTable(duLieuBang, tenCot, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle, 20,
        		new int[] {200, 400}
        		);
		bangLoaiSP.setMaximumSize(new Dimension(600, 800));
		
		tablePanel.add(bangLoaiSP);
		tablePanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
		tablePanel.setBackground(UIStyles.BackgroundColor);
		
		//them button panel
        JPanel themButtonPanel = new JPanel();
        themButtonPanel.setLayout(new BorderLayout());
        themButtonPanel.add(new CustomButton("Thêm loại",UIStyles.ThemButtonStyle,UIStyles.Add,CustomButtonIconSide.LEFT,()->hienPaneThemLoai()), BorderLayout.EAST);
        themButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 35));
        themButtonPanel.setBackground(UIStyles.BackgroundColor);
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(tablePanel, BorderLayout.CENTER);
		this.add(themButtonPanel, BorderLayout.SOUTH);
		
	}
	
	private void capNhatDuLieu() {
        // Convert ArrayList to Object[][]
        Object[][] data = new Object[dsLoaiSanPham.size()][2];
        for (int i = 0; i < dsLoaiSanPham.size(); i++) {
            LoaiSanPham loai = dsLoaiSanPham.get(i);
            data[i][0] = loai.getMaLoai();
            data[i][1] = loai.getTenLoai();
        }
        bangLoaiSP.capNhatDuLieu(data);
	}
	
	private void hienPaneThemLoai() {
	    // Create a new JFrame for adding a new LoaiSanPham
	    JFrame frameThem = new JFrame("Thêm Loại Sản Phẩm");
	    frameThem.setSize(600, 400); // Set the size of the window
	    frameThem.setResizable(false);
	    frameThem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frameThem.setLocationRelativeTo(null); // Center the window
	    frameThem.setVisible(true);

	    // Main panel for the form
	    JPanel panelThem = new JPanel();
	    panelThem.setLayout(null);
	    frameThem.add(panelThem);

	    // Title label
	    JLabel lblTitle = new JLabel("Thêm Loại Sản Phẩm");
	    lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
	    lblTitle.setBounds(150, 20, 500, 50);
	    panelThem.add(lblTitle);

	    // Mã loại
	    JLabel lblMaLoai = new JLabel("Mã loại:");
	    lblMaLoai.setFont(new Font("Tahoma", Font.BOLD, 18));
	    lblMaLoai.setBounds(50, 100, 150, 30);
	    panelThem.add(lblMaLoai);

	    JTextField txtMaLoai = new JTextField();
	    txtMaLoai.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    txtMaLoai.setBounds(200, 100, 300, 30);
	    panelThem.add(txtMaLoai);
	    txtMaLoai.setEditable(false);
	    txtMaLoai.setText(LoaiSanPhamCTR.layMaMoi());

	    // Tên loại
	    JLabel lblTenLoai = new JLabel("Tên loại:");
	    lblTenLoai.setFont(new Font("Tahoma", Font.BOLD, 18));
	    lblTenLoai.setBounds(50, 150, 150, 30);
	    panelThem.add(lblTenLoai);

	    JTextField txtTenLoai = new JTextField();
	    txtTenLoai.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    txtTenLoai.setBounds(200, 150, 300, 30);
	    panelThem.add(txtTenLoai);

	    // Số lượng tối đa
	    JLabel lblSoLuongToiDa = new JLabel("Số lượng tối đa:");
	    lblSoLuongToiDa.setFont(new Font("Tahoma", Font.BOLD, 18));
	    lblSoLuongToiDa.setBounds(50, 200, 150, 30);
	    panelThem.add(lblSoLuongToiDa);

	    JTextField txtSoLuongToiDa = new JTextField();
	    txtSoLuongToiDa.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    txtSoLuongToiDa.setBounds(200, 200, 300, 30);
	    panelThem.add(txtSoLuongToiDa);

	    // Save button
	    JButton btnSave = new JButton("Lưu");
	    btnSave.setFont(new Font("Tahoma", Font.BOLD, 18));
	    btnSave.setBounds(150, 280, 120, 40);
	    btnSave.setBackground(Color.GREEN);
	    btnSave.setForeground(Color.WHITE);
	    panelThem.add(btnSave);

	    // Cancel button
	    JButton btnCancel = new JButton("Hủy");
	    btnCancel.setFont(new Font("Tahoma", Font.BOLD, 18));
	    btnCancel.setBounds(300, 280, 120, 40);
	    btnCancel.setBackground(Color.RED);
	    btnCancel.setForeground(Color.WHITE);
	    panelThem.add(btnCancel);

	    // Add action listeners for buttons
	    btnSave.addActionListener(e -> {
	        String maLoai = txtMaLoai.getText().trim();
	        String tenLoai = txtTenLoai.getText().trim();
	        String soLuongToiDaStr = txtSoLuongToiDa.getText().trim();

	        if (maLoai.isEmpty() || tenLoai.isEmpty() || soLuongToiDaStr.isEmpty()) {
	            JOptionPane.showMessageDialog(frameThem, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        
	        if(tenLoai.length()>20) {
	        	JOptionPane.showMessageDialog(frameThem, "Tên loại không được dài quá 20 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        try {
	            int soLuongToiDa = Integer.parseInt(soLuongToiDaStr);

	            // Create a new LoaiSanPham and add it to the list
	            LoaiSanPham loaiSanPham = new LoaiSanPham(maLoai);
	            loaiSanPham.setTenLoai(tenLoai);
	            //loaiSanPham.setSoLuongToiDa(soLuongToiDa);

	            if (dsLoaiSanPham.contains(loaiSanPham)) {
	                JOptionPane.showMessageDialog(frameThem, "Mã loại đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            
	            LoaiSanPhamCTR.them(loaiSanPham);
	            dsLoaiSanPham = LoaiSanPhamCTR.layDanhSachTatCaLoaiSanPham();
	            for(LoaiSanPham lsp : dsLoaiSanPham) {
	            	System.out.println(lsp);
	            }
	            capNhatDuLieu(); // Update table data
	            frameThem.dispose(); // Close the frame
	            JOptionPane.showMessageDialog(this, "Thêm loại sản phẩm thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(frameThem, "Số lượng tối đa phải là số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }
	    });

	    btnCancel.addActionListener(e -> frameThem.dispose());
	}

}