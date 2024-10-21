package userInterfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.LineBorder;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class BanHangUI extends JPanel implements ActionListener { 
	private JTextField txtTim;
	private JTextField txtTenNV;
	private JTextField txtThoiGian;
	private JTextField txtTenKHDM;
	private JTextField txtDTL;
	private JTextField txtTenKH;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JTextField txtTongTien;
	private JTextField txtGiamGia;
	private JRadioButton rdbtnChietKhau;
	private JTextField txtTienTra;
	private JTextField txtTienKhachDua;
	private JTextField txtTienTraKhach;
	private JButton btnAddTab;
	private int[] tabCounter;
	private JTabbedPane tabbedPaneHoaDon;
	private JTextField txtGhiChu;

//
	
	public BanHangUI() {
		super();
		taoHinh();
	}
	
	public void taoHinh() {
		setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
		setLayout(null);
		setBackground(Color.WHITE);
		
		JPanel panelTong = new JPanel();
		panelTong.setBackground(UIStyles.backgroundColor);
		panelTong.setBounds(0, 0, UIStyles.ApplicationWidth, UIStyles.MainSectionHeight);
		add(panelTong);
		panelTong.setLayout(null);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
	}
	
	private static void setPlaceholder(JTextField textField, String placeholder) {
        // Đặt placeholder
        textField.setText(placeholder);
        textField.setForeground(Color.LIGHT_GRAY); // Màu chữ của placeholder
        textField.setFont(new Font("Tahoma", Font.ITALIC, 20));

        // Lắng nghe sự kiện focus
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText(""); // Xóa placeholder
                    textField.setForeground(Color.BLACK); // Đổi màu chữ khi nhập
                    textField.setFont(new Font("Tahoma", Font.PLAIN, 24)); // Đặt lại font khi nhập
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.LIGHT_GRAY); // Đổi lại màu chữ của placeholder
                    textField.setFont(new Font("Tahoma", Font.ITALIC, 20)); // Đặt font chữ nghiêng
                    textField.setText(placeholder); // Khôi phục placeholder
                }
            }
        });
    }
}
