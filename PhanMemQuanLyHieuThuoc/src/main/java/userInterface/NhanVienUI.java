package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;



import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JFrame;
import com.toedter.calendar.JDateChooser;

//import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
//import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

// import javax.swing.plaf.basic.BasicScrollBarUI;
// import javax.swing.table.TableCellRenderer;
// import javax.swing.table.TableColumn;
// import javax.swing.table.TableModel;


import component.CustomTable;
import connectDB.ConnectDB;
import controller.NhanVienCTR;
import dao.NhanVienDAO;
import entity.NhanVien;
import userInterface.UIStyles;
import component.CustomButton.CustomButtonIconSide;

//import connectDB.ConnectDB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import component.CustomButton;
import component.CustomPanel;


public class NhanVienUI extends JPanel implements ActionListener {

		public JTextField txtTimTheoMa;
		public JTextField txtTimTheoTen;
		public JTextField txtTimTheoSDT;
		public JTextField txtMaNV;
		public JTextField txtHoTen;
		public JTextField txtSDT;
		public JDateChooser jdcNgaySinh;
		public JComboBox<String> cbTrangThaiLamViec;
		public JComboBox<String> cbChucVu;
		public JComboBox<String> cbGioiTinh;

		
		private JButton btnTimKiem;
		private JButton btnThemNV;
		private JButton btnCapNhatNV;
		private JButton btnAction;
		private JButton btnHuy;
		private NhanVienCTR nhanVienCTR;
		
	
		private CustomTable tableNhanVien;
		private NhanVienDAO nhanVienDAO;
		private DefaultTableModel modelNhanVien;
	

		


	
	public NhanVienUI() {
		 try {
		 	ConnectDB.getInstance().connect();
		 }catch (Exception e) {
		 	e.printStackTrace();
		 }
		 
		nhanVienCTR = new NhanVienCTR();
		nhanVienDAO = new NhanVienDAO();
		taoHinh();



	}
	
	public void taoHinh() {
		setLayout(new BorderLayout());
		JPanel panelChinh = new JPanel(new BorderLayout());
		panelChinh.setBackground(new Color(232, 234, 236));
		add(panelChinh, BorderLayout.CENTER);  // Đặt panel chính ở giữa cửa sổ
	
		// Panel tìm kiếm - đặt bên trái
		JPanel panelTimKiem = taoPanelTimKiem();
		panelChinh.add(panelTimKiem, BorderLayout.WEST);
	
		// Panel danh sách nhân viên - đặt ở giữa (CENTER)
		JPanel panelDanhSachNV = taoPanelDanhSachNV();
		panelChinh.add(panelDanhSachNV, BorderLayout.CENTER);
			
	}
	
	
	

	private JPanel taoPanelTimKiem() {

		// Panel tìm kiếm kiểu border layout
		JPanel panelTimKiem = new JPanel();
		panelTimKiem.setBackground(new Color(232, 234, 236));
		panelTimKiem.setBorder(new EmptyBorder(200, 10, 50, 10)); // Khoảng cách giữa các thành phần
		
		GridBagConstraints gbc = new GridBagConstraints(); // Để thiết lập vị trí và kích thước của các thành phần trong GridBagLayout 
		gbc.fill = GridBagConstraints.HORIZONTAL;  // Kéo giãn thành phần theo chiều ngang
		gbc.insets = new Insets(10, 10, 10, 10);// Thiết lập khoảng cách giữa các thành phần
		
		// Panel tiêu chí tìm kiếm kiểu GridBagLayout
		CustomPanel panelTieuChi = new CustomPanel(20, 5);
		panelTieuChi.setBackground(Color.white);
		panelTieuChi.setLayout(new GridBagLayout());

		gbc.fill = GridBagConstraints.BOTH;  // Kéo giãn cả chiều ngang và dọc
		panelTimKiem.add(panelTieuChi, gbc);

		// JLabel Tìm kiếm
		JLabel lblTimKiem = new JLabel("Tìm kiếm");
		lblTimKiem.setFont(new Font("Tahoma", Font.BOLD, 20));
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelTieuChi.add(lblTimKiem, gbc);
	
		// Tìm Mã nhân viên
		JLabel lblTimMaNV = new JLabel("Mã nhân viên");
		lblTimMaNV.setFont(new Font("Tahoma", Font.BOLD, 18));
		gbc.gridy = 1;
		panelTieuChi.add(lblTimMaNV, gbc);
	
		txtTimTheoMa = new JTextField();
		txtTimTheoMa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTimTheoMa.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		gbc.gridy = 2;
		gbc.ipadx = 200;  // Độ rộng mặc định cho text field
		panelTieuChi.add(txtTimTheoMa, gbc);
	
		// tìm Tên nhân viên
		JLabel lblTimTenNV = new JLabel("Họ và tên");
		lblTimTenNV.setFont(new Font("Tahoma", Font.BOLD, 18));
		gbc.gridy = 3;
		panelTieuChi.add(lblTimTenNV, gbc);
	
		txtTimTheoTen = new JTextField();
		txtTimTheoTen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTimTheoTen.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		gbc.gridy = 4;
		panelTieuChi.add(txtTimTheoTen, gbc);
	
		// Tìm Số điện thoại
		JLabel lblTimSDT = new JLabel("Số điện thoại");
		lblTimSDT.setFont(new Font("Tahoma", Font.BOLD, 18));
		gbc.gridy = 5;
		panelTieuChi.add(lblTimSDT, gbc);
	
		txtTimTheoSDT = new JTextField();
		txtTimTheoSDT.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTimTheoSDT.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		gbc.gridy = 6;
		panelTieuChi.add(txtTimTheoSDT, gbc);
	
		
		// Button Tìm kiếm
		btnTimKiem = new JButton ();
		btnTimKiem = new CustomButton("",UIStyles.TimButtonStyle,UIStyles.FindIcon,CustomButtonIconSide.LEFT,()->TimKiem());	
		//btnTimKiem.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.fill = GridBagConstraints.HORIZONTAL;  // Kéo giãn nút theo chiều ngang
		gbc.ipadx = 50;  // Kéo giãn thêm 50 pixel theo chiều ngang
		gbc.insets = new Insets(20, 100, 20, 100); 
		panelTieuChi.add(btnTimKiem, gbc);


	
		return panelTimKiem;
	}
	
	
	private JPanel taoPanelDanhSachNV() {
		JPanel panelDanhSachNV = new JPanel(new BorderLayout());
		panelDanhSachNV.setBackground(new Color(232, 234, 236));  
		
		// Panel phía trên chứa tiêu đề và các nút
		JPanel panelTren = new JPanel(new GridBagLayout());  
		panelTren.setBackground(new Color(232, 234, 236)); 
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(20, 20, 20, 20); 
		
		// JLabel tiêu đề "Danh sách nhân viên"
		JLabel lblDanhSachNV = new JLabel("Danh sách nhân viên");
		lblDanhSachNV.setFont(new Font("Tahoma", Font.BOLD, 35));
		
		// Thiết lập vị trí của lblDanhSachNV
		gbc.gridx = 0;  // Cột đầu tiên
		gbc.gridy = 0;  // Hàng đầu tiên
		gbc.insets = new Insets(20, 300, 10, 10); 
		panelTren.add(lblDanhSachNV, gbc);
		
		// Nút "Thêm nhân viên"
		btnThemNV = new JButton();
		btnThemNV.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnThemNV = new CustomButton("Thêm nhân viên",UIStyles.ThemButtonStyle,UIStyles.Add,CustomButtonIconSide.LEFT,()->formThongTinNhanVien(false));	
		gbc.gridy = 1;  
		gbc.insets = new Insets(10, 0, 30, -1750);
		panelTren.add(btnThemNV, gbc);

	


		
		// Nút "Cập nhật"
		btnCapNhatNV = new JButton("");
		btnCapNhatNV.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCapNhatNV = new CustomButton("Cập nhật",UIStyles.CapNhatButtonStyle,UIStyles.Update,CustomButtonIconSide.LEFT,()->formThongTinNhanVien(true));	
		gbc.gridx = 1;  
		gbc.gridy = 1;  
		gbc.insets = new Insets(10, 670, 30, 80);  // Khoảng cách
		gbc.anchor = GridBagConstraints.EAST;
		panelTren.add(btnCapNhatNV, gbc);
		


		panelDanhSachNV.add(panelTren, BorderLayout.NORTH);
	
		// Dữ liệu cho bảng
	
		String[] headers = {"Mã Nhân Viên", "Họ Tên", "SĐT","Cccd","Ngày Sinh","Trạng Thái","Chức Vụ","Giới Tính"};  // Tiêu đề cột của bảng
		
		
		Object[][] data = updateDataModel();
		// Tạo bảng CustomTable
		 tableNhanVien = new CustomTable(data, headers, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle,     
	20 );
		 
		

        // căn giữa dữ liệu trong bảng
//        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//        centerRenderer.setHorizontalAlignment(JLabel.CENTER); // Căn giữa theo chiều ngang
//        centerRenderer.setVerticalAlignment(JLabel.CENTER);   // Căn giữa theo chiều dọc
//
//        // Áp dụng renderer tùy chỉnh cho tất cả các cột
//        for (int i = 0; i < tableNhanVien.getColumnCount(); i++) {
//        	tableNhanVien.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
//        }


		// Thêm bảng vào JScrollPane
		JScrollPane scrollPane = new JScrollPane(tableNhanVien);
		scrollPane.setPreferredSize(new Dimension(1000, 800));  // Đặt kích thước mong muốn cho JScrollPane
		scrollPane.setBorder(new LineBorder(new Color(50, 250, 50), 3, true));  // Đặt viền cho bảng
	
		// Thêm JScrollPane chứa bảng vào panel ở giữa
		panelDanhSachNV.add(scrollPane, BorderLayout.CENTER);
	
		return panelDanhSachNV;
	}
	
	
	
	

	private JFrame formThongTinNhanVien(boolean trangThai) {
		JFrame frameThem = new JFrame(trangThai? "Cập Nhật Nhân Viên":"Thêm Nhân Viên");
		frameThem.setSize(1000, 700); // Đặt kích thước cửa sổ
		frameThem.setResizable(false);
		frameThem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		frameThem.setLocationRelativeTo(null); // Đặt cửa sổ ở giữa màn hình
//		frameThem.add(formThongTinNhanVien(false));
		frameThem.setVisible(true); // Hiển thị cửa sổ
		
	    JPanel panelThem = new JPanel();
	    panelThem.setLayout(null); 
	    frameThem.add(panelThem);

	    // Label "Thông tin nhân viên"
		JLabel lblThemNV = new JLabel(trangThai? "Cập Nhật Nhân Viên":"Thêm Nhân Viên");
		lblThemNV.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblThemNV.setBounds(350, 10, 600, 50);
		panelThem.add(lblThemNV);

		// Mã nhân viên
		JLabel lblMaNV = new JLabel("Mã nhân viên");
		lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaNV.setBounds(100, 100, 150, 30);
		panelThem.add(lblMaNV);

		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMaNV.setBounds(400, 100, 300, 30);
		txtMaNV.setEditable(false);  // Không cho phép chỉnh sửa mã nhân viên
		panelThem.add(txtMaNV);

		// Họ và tên
		JLabel lblHoTen = new JLabel("Họ và tên");
		lblHoTen.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblHoTen.setBounds(100, 150, 150, 30);
		panelThem.add(lblHoTen);

		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtHoTen.setBounds(400, 150, 300, 30);
		panelThem.add(txtHoTen);

		// Số điện thoại
		JLabel lblSDT = new JLabel("Số điện thoại");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSDT.setBounds(100, 200, 150, 30);
		panelThem.add(lblSDT);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSDT.setBounds(400, 200, 300, 30);
		panelThem.add(txtSDT);

		// Cccd
		JLabel lblCCCD = new JLabel("CCCD");
		lblCCCD.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCCCD.setBounds(100, 250, 150, 30);
		panelThem.add(lblCCCD);
		
		JTextField txtCCCD = new JTextField();
		txtCCCD.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtCCCD.setBounds(400, 250, 300, 30);
		panelThem.add(txtCCCD);

		// Ngày sinh
		JLabel lblNgaySinh = new JLabel("Ngày sinh");
		lblNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNgaySinh.setBounds(100, 300, 150, 30);
		panelThem.add(lblNgaySinh);

		// JDateChooser cho phép chọn ngày tháng năm
		jdcNgaySinh = new JDateChooser();
		jdcNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jdcNgaySinh.setBounds(400, 300, 300, 30);
		jdcNgaySinh.setDateFormatString("yyyy-MM-dd");
		panelThem.add(jdcNgaySinh);

		// trạng thái làm việc
		JLabel lblTrangThai = new JLabel("Trạng thái làm việc");
		lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTrangThai.setBounds(100, 350, 200, 30);
		panelThem.add(lblTrangThai);

		cbTrangThaiLamViec = new JComboBox<>();
		cbTrangThaiLamViec.addItem("Đang làm việc");
		cbTrangThaiLamViec.addItem("Đã nghỉ việc");
		cbTrangThaiLamViec.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbTrangThaiLamViec.setBounds(400, 350, 300, 30);
		cbTrangThaiLamViec.setSelectedIndex(0);  // Chọn mặc định là "Đang làm việc"
		cbTrangThaiLamViec.setEnabled(trangThai);  // Không cho phép chọn trạng thái làm việc
		panelThem.add(cbTrangThaiLamViec);

		// Chức vụ
		JLabel lblChucVu = new JLabel("Chức vụ");
		lblChucVu.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblChucVu.setBounds(100, 400, 150, 30);
		panelThem.add(lblChucVu);
		
		cbChucVu = new JComboBox<>();
		cbChucVu.addItem("Nhân viên");
		cbChucVu.addItem("Quản lý");
		cbChucVu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbChucVu.setBounds(400, 400, 300, 30);
		panelThem.add(cbChucVu);

		// Giới tính
		JLabel lblGioiTinh = new JLabel("Giới tính");
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGioiTinh.setBounds(100, 450, 150, 30);
		panelThem.add(lblGioiTinh);

		cbGioiTinh = new JComboBox<>();
		cbGioiTinh.addItem("Nam");
		cbGioiTinh.addItem("Nữ");
		cbGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbGioiTinh.setBounds(400, 450, 300, 30);
		panelThem.add(cbGioiTinh);

		// Button "Thêm"
		 btnAction = new JButton(trangThai ? "Cập nhật" : "Thêm");
		 btnAction.setFont(new Font("Tahoma", Font.BOLD, 18));
		 btnAction.setBounds(400, 550, 150, 40);
		 
		// Thiết lập màu sắc
		 btnAction.setForeground(Color.white); // Màu chữ
		 btnAction.setBackground(new Color(50,250,50)); // Màu nền mặc định
		 btnAction.setOpaque(true); // Bắt buộc màu nền hiển thị
		 btnAction.setBorderPainted(false); // Tắt viền nút nếu cần

		

		// Thêm nút vào panel
		panelThem.add(btnAction);

		// Button "Hủy"
		btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnHuy.setBounds(600, 550, 150, 40);
		btnHuy.setForeground(Color.white); // Màu chữ
		btnHuy.setBackground(new Color(50,250,50)); // Màu nền mặc định
		btnHuy.setOpaque(true); // Bắt buộc màu nền hiển thị
		btnHuy.setBorderPainted(false); // Tắt viền nút nếu cần



		panelThem.add(btnHuy);
		
		btnAction.addActionListener(e -> {
	        if (trangThai) {
	        	
	        } else {
	          
	        }
	    });
		
		btnHuy.addActionListener(e -> {
			SwingUtilities.getWindowAncestor(panelThem).dispose();
		    
		});


	    return frameThem;
	}

	
	
	private Object[][] updateDataModel() {
	ArrayList<NhanVien> list = nhanVienCTR.layDanhSachTatCaNhanVien();
	return convertListToDataArray(list);
	}

	private void TimKiem() {
	    // Lấy dữ liệu từ các trường nhập liệu
	    String maNV = txtTimTheoMa.getText().trim();
	    String tenNV = txtTimTheoTen.getText().trim();
	    String sdtNV = txtTimTheoSDT.getText().trim();

	    Object[][] data = null; // Khai báo mảng dữ liệu

	    if (!maNV.isEmpty()) {
	        // Tìm kiếm theo mã nhân viên
	        ArrayList<NhanVien> list = nhanVienCTR.timKiemTheoMaNV(maNV);
	        data = convertListToDataArray(list);
	    } 
	    if (!tenNV.isEmpty()) {
	        // Tìm kiếm theo mã nhân viên
	        ArrayList<NhanVien> list = nhanVienCTR.timKiemTheoHoTen(tenNV);
	        data = convertListToDataArray(list);
	    }
	    if (!sdtNV.isEmpty()) {
	        // Tìm kiếm theo mã nhân viên
	        ArrayList<NhanVien> list = nhanVienCTR.timKiemTheoSDT(sdtNV);
	        data = convertListToDataArray(list);
	    }
	    else {
	        // Nếu không nhập gì, lấy tất cả nhân viên
	        data = updateDataModel();
	    }

	    // Kiểm tra và cập nhật dữ liệu cho bảng CustomTable
	    if (data != null && data.length > 0) {
	    	tableNhanVien.clearData();
	        tableNhanVien.setData(data); // Cập nhật dữ liệu mới vào bảng
	    	
	    } else {
	        JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả phù hợp.");
	      
	    
	    }
	}
	

	
	private Object[][] convertListToDataArray(List<NhanVien> list) {
	    Object[][] data = new Object[list.size()][8];
	    int index = 0;

	    for (NhanVien nv : list) {
	        data[index++] = new Object[]{
	            nv.getMaNhanVien(),
	            nv.getHoTen(),
	            nv.getSdt(),
	            nv.getCccd(),
	            nv.getNgaySinh(),
	            nv.getTrangThaiLamViec(),
	            nv.getChucVu(),
	            nv.getGioiTinh()
	        };
	    }
	    return data;
	}

	private void ThemNhanVien(){

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	 	}
		
}
	

