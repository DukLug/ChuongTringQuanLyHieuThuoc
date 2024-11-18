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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import component.CustomButton;
import component.CustomPanel;
import component.CustomTable;
import connectDB.ConnectDB;
import controller.KhuyenMaiCTR;
import customDataType.ChucVu;
import customDataType.GioiTinh;
import customDataType.TrangThaiLamViec;
import dao.KhuyenMaiDAO;
import dao.NhanVienDAO;
import component.CustomButton.CustomButtonIconSide;
import entity.KhuyenMai;
import entity.NhanVien;

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
	private KhuyenMaiCTR khuyenMaiCTR;
	private KhuyenMaiDAO khuyenMaiDAO;
	private CustomTable tableKhuyenMai;
	private JSpinner spChietKhau;
	private JFrame frameThem;
	private String lastFormattedDate = "";
	private JButton btnxoaTrang;

	
	 private static HashMap<String, Integer> soThuTuMap = new HashMap<>();

	public KhuyenMaiUI() {
	 	 try {
	 	 	ConnectDB.getInstance().connect();
	 	 }catch (Exception e) {
	 	 	e.printStackTrace();
	 	 }
		khuyenMaiCTR = new KhuyenMaiCTR();
		khuyenMaiDAO = new KhuyenMaiDAO();
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
			JPanel panelDanhSachNV = taoPanelDanhSachKM();
			panelChinh.add(panelDanhSachNV, BorderLayout.CENTER);
		
	
	 }
	 
	 private JPanel taoPanelTimKiem() {
		// Panel tìm kiếm kiểu border layout
		JPanel panelTimKiem = new JPanel();
		panelTimKiem.setBackground(new Color(232, 234, 236));
		panelTimKiem.setBorder(new EmptyBorder(180, 10, 50, 10));
			
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
		JLabel lblTimMaKM = new JLabel("Mã khuyến mãi");
		lblTimMaKM.setFont(new Font("Tahoma", Font.BOLD, 18));
		gbc.gridy = 1;
		panelTieuChi.add(lblTimMaKM, gbc);
			
		txtTimTheoMaKM = new JTextField();
		txtTimTheoMaKM.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTimTheoMaKM.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		txtTimTheoMaKM.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
		    }
		});
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
		jdcTimNgayKM.setPreferredSize(new Dimension(200, 40)); 
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
		jdcTimNgayKT.setPreferredSize(new Dimension(200, 40)); 
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
		txtTimTheoMaNV.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
		    }
		});
		gbc.gridy = 10;
		panelTieuChi.add(txtTimTheoMaNV, gbc);
		
		// Button Tìm kiếm
		btnTimKiem = new JButton ();
		btnTimKiem = new CustomButton("",UIStyles.TimButtonStyle,UIStyles.FindIcon,CustomButtonIconSide.LEFT,()->TimKhuyenMai());	
		gbc.gridx = 0;
		gbc.gridy = 11;
		gbc.fill = GridBagConstraints.HORIZONTAL;  // Kéo giãn nút theo chiều ngang
		gbc.ipadx = 50;  // Kéo giãn thêm 50 pixel theo chiều ngang
		gbc.insets = new Insets(20, 100, 20, 100); 
		panelTieuChi.add(btnTimKiem, gbc);
		 layDanhSachDieuKienTuDB();
			
		return panelTimKiem;
			
	 }
	 
	 private JPanel taoPanelDanhSachKM() {
			JPanel panelDanhSachKM = new JPanel(new BorderLayout());
			panelDanhSachKM.setBackground(new Color(232, 234, 236));  
			
			// Panel phía trên chứa tiêu đề và các nút
			JPanel panelTren = new JPanel(new GridBagLayout());  
			panelTren.setBackground(new Color(232, 234, 236)); 
			
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(20, 20, 20, 20); 
			
			// JLabel tiêu đề "Danh sách nhân viên"
			JLabel lblDanhSachNV = new JLabel("Quản lý khuyến mãi");
			lblDanhSachNV.setFont(new Font("Tahoma", Font.BOLD, 35));
			
			// Thiết lập vị trí của lblDanhSachNV
			gbc.gridx = 0;  // Cột đầu tiên
			gbc.gridy = 0;  // Hàng đầu tiên
			gbc.insets = new Insets(20, 300, 10, 10); 
			panelTren.add(lblDanhSachNV, gbc);
			
			// Nút "Thêm khuyến mãi"
			btnThemKM = new JButton();
			btnThemKM.setFont(new Font("Tahoma", Font.BOLD, 18));
			btnThemKM = new CustomButton("Thêm khuyến mãi",UIStyles.ThemButtonStyle,UIStyles.Add,CustomButtonIconSide.LEFT,()->formThongTinKhuyenMai(false));	
			gbc.gridy = 1;  
			gbc.insets = new Insets(10, 0, 30, -1750);
			panelTren.add(btnThemKM, gbc);

			// thêm sự kiện cho nút "Thêm nhân viên"
		
			

			
			// Nút "Cập nhật"
			btnCapNhatKM = new JButton("");
			btnCapNhatKM.setFont(new Font("Tahoma", Font.BOLD, 18));
			btnCapNhatKM = new CustomButton("Cập nhật",UIStyles.CapNhatButtonStyle,UIStyles.Update,CustomButtonIconSide.LEFT,()->layDuLieuTrongBangKhuyenMai());	
			gbc.gridx = 1;  
			gbc.gridy = 1;  
			gbc.insets = new Insets(10, 670, 30, 80);  // Khoảng cách
			gbc.anchor = GridBagConstraints.EAST;
			panelTren.add(btnCapNhatKM, gbc);
			
			

			panelDanhSachKM.add(panelTren, BorderLayout.NORTH);
		
			String[] headers = {"Mã khuyến mãi", "Ngày khuyến mãi", "Ngày kết thúc","Điều kiện","Chiết khấu","Mã nhân viên"};  
			// Dữ liệu cho bảng
			Object[][] data = new Object[0][headers.length];
		

		
			// Tạo bảng CustomTable
			 tableKhuyenMai = new CustomTable(data, headers, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle,     
		20                              
			);
			

	        // căn giữa dữ liệu trong bảng
//	        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//	        centerRenderer.setHorizontalAlignment(JLabel.CENTER); // Căn giữa theo chiều ngang
//	        centerRenderer.setVerticalAlignment(JLabel.CENTER);   // Căn giữa theo chiều dọc
//
//	        // Áp dụng renderer tùy chỉnh cho tất cả các cột
//	        for (int i = 0; i < table.getColumnCount(); i++) {
//	            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
//	        }


			// Thêm bảng vào JScrollPane
			JScrollPane scrollPane = new JScrollPane(tableKhuyenMai);
			scrollPane.setPreferredSize(new Dimension(1000, 800));  // Đặt kích thước mong muốn cho JScrollPane
			scrollPane.setBorder(new LineBorder(new Color(50, 250, 50), 3, true));  // Đặt viền cho bảng
		
			// Thêm JScrollPane chứa bảng vào panel ở giữa
			panelDanhSachKM.add(scrollPane, BorderLayout.CENTER);
			layToanBoDanhSach();
		
			return panelDanhSachKM;
		}
	 
	 private JFrame formThongTinKhuyenMai(boolean trangThai) {
			
		 	frameThem = new JFrame();
			frameThem.setSize(1000, 600); 
			frameThem.setResizable(false);
			frameThem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
			frameThem.setLocationRelativeTo(null); // Đặt cửa sổ ở giữa màn hình
			//frameThem.add(formThongTinKhuyenMai(true));
			frameThem.setVisible(true); // Hiển thị cửa sổ
			
		 
			JPanel panelThem = new JPanel();
		    panelThem.setLayout(null); 
		    frameThem.add(panelThem);

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
			cbDieuKien.setBounds(400, 250, 300, 30);
			cbDieuKien.setEditable(true);
			layDanhSachDieuKienTuDB();
			panelThem.add(cbDieuKien);
			
			// chiết khấu
			
			JLabel lblChietKhau = new JLabel("Chiết khấu");
			lblChietKhau.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblChietKhau.setBounds(100, 300, 150, 30);
			panelThem.add(lblChietKhau);
			
			SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 0.9, 0.01); // Giá trị khởi tạo, min, max, step
		    spChietKhau = new JSpinner(model);
		    spChietKhau.setBounds(400, 300, 300, 30);
		    panelThem.add(spChietKhau);
		    
		    // mã nhân viên
		    JLabel lblMaNV = new JLabel("Mã nhân viên");
			lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblMaNV.setBounds(100, 350, 150, 30);
			panelThem.add(lblMaNV);

			txtMaNV = new JTextField();
			txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 18));
			txtMaNV.setBounds(400, 350, 300, 30);
			//txtMaNV.setEditable(false);  // Không cho phép chỉnh sửa mã nhân viên
			panelThem.add(txtMaNV);


			// Button "Thêm"
			 btnAction = new JButton(trangThai ? "Cập nhật" : "Thêm");
			 btnAction.setFont(new Font("Tahoma", Font.BOLD, 18));
			 btnAction.setBounds(200, 450, 150, 40);
			 
			// Thiết lập màu sắc
			 btnAction.setForeground(Color.white); // Màu chữ
			 btnAction.setBackground(new Color(50,250,50)); // Màu nền mặc định
			 btnAction.setOpaque(true); // Bắt buộc màu nền hiển thị
			 btnAction.setBorderPainted(false); // Tắt viền nút nếu cần

			
			// button xóa trắng
				btnxoaTrang = new JButton("Xóa trắng");
				btnxoaTrang.setFont(new Font("Tahoma", Font.BOLD, 18));
				btnxoaTrang.setBounds(400, 450, 150, 40);
				btnxoaTrang.setForeground(Color.white); // Màu chữ
				btnxoaTrang.setBackground(new Color(50,250,50)); // Màu nền mặc định
				btnxoaTrang.setOpaque(true); // Bắt buộc màu nền hiển thị
				btnxoaTrang.setBorderPainted(false); // Tắt viền nút nếu cần
				btnxoaTrang.setVisible(!trangThai);
				panelThem.add(btnxoaTrang);
				
				btnxoaTrang.addActionListener(e -> {
			        
			       jdcNgayKM.setDate(null);
			       jdcNgayKT.setDate(null);
			       cbDieuKien.setSelectedIndex(-1);
			       spChietKhau.setValue(0);
			        
			    });

			// Thêm nút vào panel
			panelThem.add(btnAction);

			// Button "Hủy"
			btnHuy = new JButton("Hủy");
			btnHuy.setFont(new Font("Tahoma", Font.BOLD, 18));
			btnHuy.setBounds(600, 450, 150, 40);
			btnHuy.setForeground(Color.white); // Màu chữ
			btnHuy.setBackground(new Color(50,250,50)); // Màu nền mặc định
			btnHuy.setOpaque(true); // Bắt buộc màu nền hiển thị
			btnHuy.setBorderPainted(false); // Tắt viền nút nếu cần

			

			panelThem.add(btnHuy);
			
			btnAction.addActionListener(e -> {
		        if (!trangThai) {
		        	ThemKhuyenMai();
		        } else {
		        	capNhatKhuyenMai();
		          
		        }
		    });
			
			btnHuy.addActionListener(e -> {
				SwingUtilities.getWindowAncestor(panelThem).dispose();
			    
			});
			
		

		    return frameThem;
		}

	 
	private void TimKhuyenMai() {
		String makm = txtTimTheoMaKM.getText();
		Date ngaykm = null;
		Date ngaykt= null;
		String dieuKien = cbTimTheoDK.getSelectedItem().toString();
		String manv = txtTimTheoMaNV.getText();
		ArrayList<KhuyenMai> danhSachKhuyenMai= new ArrayList<>();

		if (jdcTimNgayKM != null && jdcTimNgayKM.getDate() != null) {
	        ngaykm = new Date(jdcTimNgayKM.getDate().getTime());
	    }
		

		if (jdcTimNgayKT != null && jdcTimNgayKT.getDate() != null) {
	        ngaykt = new Date(jdcTimNgayKT.getDate().getTime());
	    }
		
		    if (!makm.isEmpty()) {
		    	danhSachKhuyenMai = khuyenMaiCTR.timKhuyenMaiTheoMa(makm);
		        if (!danhSachKhuyenMai.isEmpty()) {
		            capNhatBangKhuyenMai(danhSachKhuyenMai);
		            return; 
		        }
		    }
		    if (!dieuKien.isEmpty()) {
		    	danhSachKhuyenMai = khuyenMaiCTR.timKhuyenMaiTheoDieuKien(dieuKien);
		        if (!danhSachKhuyenMai.isEmpty()) {
		            capNhatBangKhuyenMai(danhSachKhuyenMai);
		            return; 
		        }
		    }
		    
		    if (!manv.isEmpty()) {
		    	danhSachKhuyenMai = khuyenMaiCTR.timKhuyenMaiTheoMaNhanvien(manv);
		        if (!danhSachKhuyenMai.isEmpty()) {
		            capNhatBangKhuyenMai(danhSachKhuyenMai);
		            return; 
		        }
		    }
		    
		    if (ngaykm != null) {
		        danhSachKhuyenMai = khuyenMaiCTR.timKhuyenMaiTheoNgayKhuyenMai(ngaykm);
		        if (!danhSachKhuyenMai.isEmpty()) {
		            capNhatBangKhuyenMai(danhSachKhuyenMai);
		            return;
		        }
		    }
		    
		    if (ngaykt != null) {
		        danhSachKhuyenMai = khuyenMaiCTR.timKhuyenMaiTheoNgayKetThuc(ngaykt);
		        if (!danhSachKhuyenMai.isEmpty()) {
		            capNhatBangKhuyenMai(danhSachKhuyenMai);
		            return;
		        }
		    }
		    JOptionPane.showMessageDialog(this, "Không tìm thấy Khuyến mãi với các tiêu chí đã nhập.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		    layToanBoDanhSach();

		
	}
	
	
	public void layToanBoDanhSach() {
		ArrayList<KhuyenMai> dsKhuyenMai = khuyenMaiCTR.layDanhSachTatCaKhuyenMai();
		Object[][] data = new Object[dsKhuyenMai.size()][6]; 

		for (int i = 0; i < dsKhuyenMai.size(); i++) {
		    KhuyenMai km = dsKhuyenMai.get(i);
		    data[i][0] = km.getMaKhuyenMai();
		    data[i][1] = km.getNgayKhuyenMai();
		    data[i][2] = km.getNgayKetThuc();
		    data[i][3] = km.getDieuKien();
		    data[i][4] = km.getChietKhau();
		    data[i][5] = km.getMaNhanVien().getMaNhanVien();
		   
		}

		tableKhuyenMai.capNhatDuLieu(data); 
	   
	}
	
	// thêm khuyến mãi
	private void ThemKhuyenMai() {
		 if(!valid()) {
			  return;
			  
		  }
		NhanVienDAO nv_dao = new NhanVienDAO();
	    String maKM = phatSinhMaKhuyenMai();
	    Date ngayKhuyenMai = new Date(jdcNgayKM.getDate().getTime());
	    Date ngayKetThuc = new Date(jdcNgayKT.getDate().getTime());
	    String dieuKien = cbDieuKien.getSelectedItem().toString();
	    double chietKhau = (Double) spChietKhau.getValue();	
	    
	    String maNVString = txtMaNV.getText(); 
	    NhanVien maNV = nv_dao.layNhanVienTheoMa(maNVString); 

	    KhuyenMai khuyenMai = new KhuyenMai(maKM, ngayKhuyenMai, ngayKetThuc, dieuKien, chietKhau, maNV);

	    boolean kq = khuyenMaiCTR.themKhuyenMai(khuyenMai);

	    if (kq) {
	        JOptionPane.showMessageDialog(null, "Thêm khuyến mãi thành công!");
	        frameThem.dispose();
            layToanBoDanhSach(); 
	    } else {
	        JOptionPane.showMessageDialog(null, "Thêm khuyến mãi thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	    }
	}


	
	private void capNhatBangKhuyenMai(ArrayList<KhuyenMai> dsKhuyenMai ) {
		 
	    DefaultTableModel model = (DefaultTableModel) tableKhuyenMai.getModel();
	    model.setRowCount(0); 

	    
	    for (KhuyenMai km : dsKhuyenMai) {
	        model.addRow(new Object[]{
	            km.getMaKhuyenMai(),
	            km.getNgayKhuyenMai(),
	            km.getNgayKetThuc(),
	            km.getDieuKien(),
	            km.getChietKhau(),
	            km.getMaNhanVien().getMaNhanVien()
	        });
	    }
	}
	
	private void updateComboBox(ArrayList<String> danhSachDieuKien) {
	    if (cbTimTheoDK != null) {
	        cbTimTheoDK.removeAllItems();
	        cbTimTheoDK.addItem("");
	        for (String dieuKien : danhSachDieuKien) {
	            cbTimTheoDK.addItem(dieuKien);
	        }
	    }
	    
	    if (cbDieuKien != null) {
	        cbDieuKien.removeAllItems();
	        cbDieuKien.addItem("");
	        for (String dieuKien : danhSachDieuKien) {
	            cbDieuKien.addItem(dieuKien);
	        }
	    }
	}
	
	private void layDanhSachDieuKienTuDB() {
	    KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO();
	    ArrayList<String> danhSachDieuKien = khuyenMaiDAO.layDanhSachDieuKien();
	    updateComboBox(danhSachDieuKien);
	}

	private String phatSinhMaKhuyenMai() {
	    // Lấy ngày hiện tại
	    Date currentDate = new Date(System.currentTimeMillis());
	    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
	    String formattedDate = sdf.format(currentDate);

	    
	    if (!formattedDate.equals(lastFormattedDate)) {
	        soThuTuMap.clear(); 
	        lastFormattedDate = formattedDate; 
	    }

	    // Tăng số thứ tự cho ngày hiện tại
	    int stt = soThuTuMap.getOrDefault(formattedDate, 0) + 1;
	    soThuTuMap.put(formattedDate, stt); 

	    
	    String maKhuyenMai = "KM" + formattedDate + String.format("%02d", stt);
	    
	    return maKhuyenMai;
	}

	
	private void layDuLieuTrongBangKhuyenMai() {
	    int selectedRow = tableKhuyenMai.getSelectedRow();
	    System.out.println("Chỉ số hàng được chọn: " + selectedRow);

	    if (selectedRow != -1) {
	        Object[] rowData = ((CustomTable) tableKhuyenMai).getRowData(selectedRow);

	        if (rowData != null) {
	            formThongTinKhuyenMai(true);

	            txtMaKM.setText(rowData[0].toString());
	            jdcNgayKM.setDate(Date.valueOf(rowData[1].toString()));
	            jdcNgayKT.setDate(Date.valueOf(rowData[2].toString()));
		        cbDieuKien.setSelectedItem(rowData[3]);
		        spChietKhau.setValue(Double.parseDouble(rowData[4].toString()));
		        txtMaNV.setText(rowData[5].toString());
	        } else {
	            JOptionPane.showMessageDialog(null, "Không có dữ liệu cho hàng đã chọn.", "Thông báo", JOptionPane.WARNING_MESSAGE);
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Vui lòng chọn một khuyến mãi để cập nhật.", "Thông báo", JOptionPane.WARNING_MESSAGE);
	    }
	}
	
	private void capNhatKhuyenMai() {
		 if(!valid()) {
			  return;
			  
		  }
	    String maKM = txtMaKM.getText();
	    Date ngayBatDau = new Date(jdcNgayKM.getDate().getTime());
	    Date ngayKetThuc = new Date(jdcNgayKT.getDate().getTime());
	    String dieuKien = cbDieuKien.getSelectedItem().toString();
	    double chietKhau = (double) spChietKhau.getValue();
	    String maNV = txtMaNV.getText();
	    NhanVien nv = new NhanVien(maNV);

	 
	    KhuyenMai km = new KhuyenMai(maKM, ngayBatDau, ngayKetThuc, dieuKien, chietKhau, nv);

	    boolean kq = khuyenMaiCTR.capNhatKhuyenMai(km);

	    if (kq) {
	        JOptionPane.showMessageDialog(null, "Cập nhật khuyến mãi thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	        frameThem.dispose(); 
	        layToanBoDanhSach();
	       
	    } else {
	        JOptionPane.showMessageDialog(null, "Cập nhật không thành công. Vui lòng kiểm tra lại.", "Thông báo", JOptionPane.ERROR_MESSAGE);
	    }
	}

	private boolean valid() {
	    // Kiểm tra ngày bắt đầu khuyến mãi
	    if (jdcNgayKM.getDate() == null) {
	        JOptionPane.showMessageDialog(frameThem, "Vui lòng chọn ngày khuyến mãi!");
	        return false;
	    }
	    Date ngayBatDau = new Date(jdcNgayKM.getDate().getTime());
	    Date ngayHienTai = new Date(System.currentTimeMillis());
	    if (ngayBatDau.before(ngayHienTai)) {
	        JOptionPane.showMessageDialog(frameThem, "Ngày bắt đầu phải lớn hơn hoặc bằng ngày hiện tại!");
	        return false;
	    }

	    if (jdcNgayKT.getDate() == null) {
	        JOptionPane.showMessageDialog(frameThem, "Vui lòng chọn ngày kết thúc!");
	        return false;
	    }
	    Date ngayKetThuc = new Date(jdcNgayKT.getDate().getTime());
	    if (!ngayKetThuc.after(ngayBatDau)) {
	        JOptionPane.showMessageDialog(frameThem, "Ngày kết thúc phải lớn hơn ngày bắt đầu!");
	        return false;
	    }

	    String dieuKien = cbDieuKien.getSelectedItem().toString();
	    try {
	        int dieuKienValue = Integer.parseInt(dieuKien);
	        if (dieuKienValue <= 0) {
	            JOptionPane.showMessageDialog(frameThem, "Điều kiện khuyến mãi phải là số lớn hơn 0!");
	            return false;
	        }
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(frameThem, "Điều kiện khuyến mãi phải là số hợp lệ!");
	        return false;
	    }

	    double chietKhau = (double) spChietKhau.getValue();
	    if (chietKhau <= 0 || chietKhau >= 1) {
	        JOptionPane.showMessageDialog(frameThem, "Chiết khấu phải lớn hơn 0 và nhỏ hơn 1!");
	        return false;
	    }
	    
	    String maNV = txtMaNV.getText();
	    if (maNV.trim().isEmpty()) {
	        JOptionPane.showMessageDialog(frameThem, "Vui lòng nhập mã nhân viên!");
	        return false;
	    }

	    return true;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	 	}
	

}
