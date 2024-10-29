package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

import com.toedter.calendar.JDateChooser;

import component.CustomButton;
import component.CustomPanel;
import component.CustomTable;
import component.CustomButton.CustomButtonIconSide;

public class KhuyenMaiUI extends JPanel implements ActionListener {

	private JTextField txtTimTheoMaKM;
	private JDateChooser jdcTimNgayKM;
	private JDateChooser jdcTimNgayKT;
	private JTextField txtTimTheoMaNV;
	private JComboBox<String> cbTimTheoDK;
	private JButton btnTimKiem;
	private JButton btnThemKM;
	private JButton btnCapNhatKM;
	private JTextField txtMaKM;
	private JDateChooser jdcNgayKM;
	private JDateChooser jdcNgayKT;
	private JComboBox<String> cbDieuKien;
	private JButton btnAction;
	private JButton btnHuy;
	private JTextField txtMaNV;


	public KhuyenMaiUI() {
	 	// try {
	 	// 	ConnectDB.getInstance().connect();
	 	// }catch (Exception e) {
	 	// 	e.printStackTrace();
	 	// }
		
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
		panelTimKiem.setBorder(new EmptyBorder(200, 10, 50, 10));
			
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
		
		// Tìm Mã Khuyến mãi
		JLabel lblTimMaKM = new JLabel("Mã nhân viên");
		lblTimMaKM.setFont(new Font("Tahoma", Font.BOLD, 18));
		gbc.gridy = 1;
		panelTieuChi.add(lblTimMaKM, gbc);
			
		txtTimTheoMaKM = new JTextField();
		txtTimTheoMaKM.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTimTheoMaKM.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		gbc.gridy = 2;
		gbc.ipadx = 200;  // Độ rộng mặc định cho text field
		panelTieuChi.add(txtTimTheoMaKM, gbc);

		// tìm theo ngày khuyến mãi
		JLabel lblTimNgayKM = new JLabel("Ngày khuyến mãi");
		lblTimNgayKM.setFont(new Font("Tahoma", Font.BOLD, 18));
		gbc.gridy = 3;
		panelTieuChi.add(lblTimNgayKM, gbc);

		jdcTimNgayKM = new JDateChooser();
		jdcTimNgayKM.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jdcTimNgayKM.setDateFormatString("yyyy-MM-dd");
		gbc.gridy = 4;
		panelTieuChi.add(jdcTimNgayKM, gbc);

		// tìm theo ngày kết thúc
		JLabel lblTimNgayKT = new JLabel("Ngày kết thúc");
		lblTimNgayKT.setFont(new Font("Tahoma", Font.BOLD, 18));
		gbc.gridy = 5;
		panelTieuChi.add(lblTimNgayKT, gbc);

		jdcTimNgayKT = new JDateChooser();
		jdcTimNgayKT.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jdcTimNgayKT.setDateFormatString("yyyy-MM-dd");
		gbc.gridy = 6;
		panelTieuChi.add(jdcTimNgayKT, gbc);

		// tìm theo điều kiện
		JLabel lblTimDieuKien = new JLabel("Điều kiện");
		lblTimDieuKien.setFont(new Font("Tahoma", Font.BOLD, 18));
		gbc.gridy = 7;
		panelTieuChi.add(lblTimDieuKien, gbc);
		
		cbTimTheoDK =  new JComboBox<>();
		cbTimTheoDK.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbTimTheoDK.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		gbc.gridy = 8;
		panelTieuChi.add(cbTimTheoDK, gbc);

		// tìm theo mã nhân viên
		JLabel lblTimMaNV = new JLabel("Mã nhân viên");
		lblTimMaNV.setFont(new Font("Tahoma", Font.BOLD, 18));
		gbc.gridy = 9;
		panelTieuChi.add(lblTimMaNV, gbc);
		
		txtTimTheoMaNV = new JTextField();
		txtTimTheoMaNV.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTimTheoMaNV.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		gbc.gridy = 10;
		panelTieuChi.add(txtTimTheoMaNV, gbc);
		
		// Button Tìm kiếm
		btnTimKiem = new JButton ();
		btnTimKiem = new CustomButton("",UIStyles.TimButtonStyle,UIStyles.FindIcon,CustomButtonIconSide.LEFT,()->TimKiem());	
		gbc.gridx = 0;
		gbc.gridy = 11;
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
			
			// Nút "Thêm khuyến mãi"
			btnThemKM = new JButton();
			btnThemKM.setFont(new Font("Tahoma", Font.BOLD, 18));
			btnThemKM = new CustomButton("Thêm nhân viên",UIStyles.ThemButtonStyle,UIStyles.Add,CustomButtonIconSide.LEFT,()->formThongTinKhuyenMai(false));	
			gbc.gridy = 1;  
			gbc.insets = new Insets(10, 0, 30, -1750);
			panelTren.add(btnThemKM, gbc);

			// thêm sự kiện cho nút "Thêm nhân viên"
		
			btnThemKM.addActionListener(e -> {
				JFrame frameThem = new JFrame("Thêm Nhân Viên");
				frameThem.setSize(1000, 700); // Đặt kích thước cửa sổ
				frameThem.setResizable(false);
				frameThem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
				frameThem.setLocationRelativeTo(null); // Đặt cửa sổ ở giữa màn hình
				frameThem.add(formThongTinKhuyenMai(false));
				frameThem.setVisible(true); // Hiển thị cửa sổ
				
			});

			
			// Nút "Cập nhật"
			btnCapNhatKM = new JButton("");
			btnCapNhatKM.setFont(new Font("Tahoma", Font.BOLD, 18));
			btnCapNhatKM = new CustomButton("Cập nhật",UIStyles.CapNhatButtonStyle,UIStyles.Update,CustomButtonIconSide.LEFT,()->formThongTinKhuyenMai(true));	
			gbc.gridx = 1;  
			gbc.gridy = 1;  
			gbc.insets = new Insets(10, 670, 30, 80);  // Khoảng cách
			gbc.anchor = GridBagConstraints.EAST;
			panelTren.add(btnCapNhatKM, gbc);
			
			btnCapNhatKM.addActionListener(e -> {
			// Hiển thị form thêm nhân viên khi nhấn nút
			JFrame frameThem = new JFrame("Cập nhật Nhân Viên");
			frameThem.setSize(1000, 700); // Đặt kích thước cửa sổ
			frameThem.setResizable(false);
			frameThem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
			frameThem.setLocationRelativeTo(null); // Đặt cửa sổ ở giữa màn hình
			frameThem.add(formThongTinKhuyenMai(true));
			frameThem.setVisible(true); // Hiển thị cửa sổ
		});

			panelDanhSachNV.add(panelTren, BorderLayout.NORTH);
		
			// Dữ liệu cho bảng
			Object[][] data = {
				{"1", "john@example.com", "Developer"},
				{"2", "jane@example.com", "Designer"},
				{"3", "mike@example.com", "Manager"},
				// Các dòng dữ liệu khác có thể được thêm vào...
			};
		
			String[] columnNames = {"Name", "Email", "Role"};  // Tiêu đề cột của bảng
		
			// Tạo bảng CustomTable
			CustomTable table = new CustomTable(data, columnNames, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle,     
		20                              
			);
			

	        // căn giữa dữ liệu trong bảng
	        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment(JLabel.CENTER); // Căn giữa theo chiều ngang
	        centerRenderer.setVerticalAlignment(JLabel.CENTER);   // Căn giữa theo chiều dọc

	        // Áp dụng renderer tùy chỉnh cho tất cả các cột
	        for (int i = 0; i < table.getColumnCount(); i++) {
	            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	        }


			// Thêm bảng vào JScrollPane
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(1000, 800));  // Đặt kích thước mong muốn cho JScrollPane
			scrollPane.setBorder(new LineBorder(new Color(50, 250, 50), 3, true));  // Đặt viền cho bảng
		
			// Thêm JScrollPane chứa bảng vào panel ở giữa
			panelDanhSachNV.add(scrollPane, BorderLayout.CENTER);
		
			return panelDanhSachNV;
		}
	 
	 private JPanel formThongTinKhuyenMai(boolean trangThai) {
			
		    JPanel panelThem = new JPanel();
		    panelThem.setLayout(null); 

		    // Label "Thông tin nhân viên"
			JLabel lblThemNV = new JLabel(trangThai? "Cập Nhật Khuyến Mãi":"Thêm Khuyến Mãi");
			lblThemNV.setFont(new Font("Tahoma", Font.BOLD, 35));
			lblThemNV.setBounds(350, 10, 600, 50);
			panelThem.add(lblThemNV);

			// Mã khuyến mãi
			JLabel lblMaKM = new JLabel("Mã khuyến mãi");
			lblMaKM.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblMaKM.setBounds(100, 100, 150, 30);
			panelThem.add(lblMaKM);

			txtMaKM = new JTextField();
			txtMaKM.setFont(new Font("Tahoma", Font.PLAIN, 18));
			txtMaKM.setBounds(400, 100, 300, 30);
			txtMaKM.setEditable(false);  // Không cho phép chỉnh sửa mã khuyến mãi
			panelThem.add(txtMaKM);

			
			// Ngày khuyến mãi
			JLabel lblNgayKM = new JLabel("Ngày khuyến mãi");
			lblNgayKM.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblNgayKM.setBounds(100, 150, 180, 30);
			panelThem.add(lblNgayKM);
			
			jdcNgayKM = new JDateChooser();
			jdcNgayKM.setFont(new Font("Tahoma", Font.PLAIN, 18));
			jdcNgayKM.setBounds(400, 150, 300, 30);
			jdcNgayKM.setDateFormatString("yyyy-MM-dd");
			panelThem.add(jdcNgayKM);
			
			// Ngày kết thúc
			JLabel lblNgayKT = new JLabel("Ngày kết thúc");
			lblNgayKT.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblNgayKT.setBounds(100, 200, 150, 30);
			panelThem.add(lblNgayKT);

			jdcNgayKT = new JDateChooser();
			jdcNgayKT.setFont(new Font("Tahoma", Font.PLAIN, 18));
			jdcNgayKT.setBounds(400, 200, 300, 30);
			jdcNgayKT.setDateFormatString("yyyy-MM-dd");
			panelThem.add(jdcNgayKT);

			// điều kiện
			JLabel lblDieuKien = new JLabel("Điều kiện");
			lblDieuKien.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblDieuKien.setBounds(100, 250, 150, 30);
			panelThem.add(lblDieuKien);

			cbDieuKien  = new JComboBox<>();
			cbDieuKien.setFont(new Font("Tahoma", Font.PLAIN, 18));
			cbDieuKien.addItem("Mua 2 tặng 1");
			cbDieuKien.setBounds(400, 250, 300, 30);
			panelThem.add(cbDieuKien);
			
			// chiết khấu
			
			JLabel lblChietKhau = new JLabel("Chiết khấu");
			lblChietKhau.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblChietKhau.setBounds(100, 300, 150, 30);
			panelThem.add(lblChietKhau);
			
			SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 0.9, 0.1); // Giá trị khởi tạo, min, max, step
		    JSpinner spinner = new JSpinner(model);
		    spinner.setBounds(400, 300, 300, 30);
		    panelThem.add(spinner);
		    
		    // mã nhân viên
		    JLabel lblMaNV = new JLabel("Mã nhân viên");
			lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblMaNV.setBounds(100, 350, 150, 30);
			panelThem.add(lblMaNV);

			txtMaNV = new JTextField();
			txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 18));
			txtMaNV.setBounds(400, 350, 300, 30);
			txtMaNV.setEditable(false);  // Không cho phép chỉnh sửa mã nhân viên
			panelThem.add(txtMaNV);


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


		    return panelThem;
		}

	 
	private void TimKiem() {
		
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	 	}
	

}
