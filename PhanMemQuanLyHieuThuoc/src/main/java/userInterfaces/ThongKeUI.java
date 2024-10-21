
package userInterfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import component.CustomTable;

public class ThongKeUI extends JPanel {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
	private DefaultTableModel modelKH;
	private JTable tableKH;
    
    public ThongKeUI() {
        super();
        // Sử dụng layout mặc định (null layout)
        setLayout(null);
        taoHinh();
    }

    private void taoHinh() {
        setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
        this.setBackground(Color.white);
        
        contentPane = new JPanel();  // Sửa lại để sử dụng thuộc tính của lớp
        contentPane.setBackground((new Color(232, 234, 236)));
        contentPane.setBounds(0, 0, 1800, 850);
        add(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(38, 26, 350, 773);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("THỐNG KÊ THEO NHÂN VIÊN");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setBounds(21, 12, 303, 28);
        panel.add(lblNewLabel);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(0, 99, 350, 230);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("Khoảng thời gian");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_1.setBounds(28, 10, 236, 37);
        panel_1.add(lblNewLabel_1);
        
        JButton btnNewButton = new JButton("Ngày tháng năm");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Hành động khi nhấn nút
            }
        });
        btnNewButton.setBounds(50, 57, 251, 36);
        panel_1.add(btnNewButton);
        
        JLabel lblNewLabel_2 = new JLabel("Từ");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2.setBounds(34, 117, 48, 29);
        panel_1.add(lblNewLabel_2);
        
        textField = new JTextField();
        textField.setBounds(92, 117, 172, 29);
        panel_1.add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_2_1 = new JLabel("Đến");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1.setBounds(34, 168, 48, 29);
        panel_1.add(lblNewLabel_2_1);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(92, 168, 172, 29);
        panel_1.add(textField_1);
        
        JPanel panel_1_1 = new JPanel();
        panel_1_1.setBackground(new Color(255, 255, 255));
        panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        panel_1_1.setLayout(null);
        panel_1_1.setBounds(0, 385, 350, 199);
        panel.add(panel_1_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Nhân Viên Bán");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_1_1.setBounds(28, 10, 236, 37);
        panel_1_1.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_2_2 = new JLabel("Mã NV");
        lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_2.setBounds(38, 69, 59, 29);
        panel_1_1.add(lblNewLabel_2_2);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(96, 69, 172, 29);
        panel_1_1.add(textField_2);
        
        JLabel lblNewLabel_2_1_1 = new JLabel("Tên NV");
        lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_1.setBounds(38, 120, 59, 29);
        panel_1_1.add(lblNewLabel_2_1_1);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(96, 120, 172, 29);
        panel_1_1.add(textField_3);
        
        JButton btnNewButton_1 = new JButton("Xuất File");
        btnNewButton_1.setBackground(new Color(50,205,50));
        btnNewButton_1.setForeground(new Color(255, 255, 255));
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Hành động khi nhấn nút
            }
        });
        btnNewButton_1.setBounds(0, 656, 350, 57);
        panel.add(btnNewButton_1);
        
     
        JPanel panelBang = new JPanel();
        panelBang.setBorder(new LineBorder(Color.GRAY, 1, true));
        panelBang.setBounds(550, 45, 1100,750);
        contentPane.add(panelBang);

        
        Object[][] data = {
        	    {"John Doe", 5, 1000.0, 200.0, 800.0},
        	    {"Jane Smith", 3, 600.0, 100.0, 500.0},
        	    {"Mike Johnson", 4, 800.0, 150.0, 650.0},
        	    {"Emily Davis", 2, 400.0, 50.0, 350.0}
        	};

        
        String[] header = {"Tên nhân viên", "Số lượng đơn", "Doanh thu", "Giá trị trả", "Doanh thu thuần"};

        
        CustomTable table = new CustomTable(data, header, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle, 20);

        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(885, 711)); // Kích thước của JScrollPane
        scrollPane.setBorder(new LineBorder(Color.GRAY, 1, true));

        // Thêm JScrollPane vào panelBang
        panelBang.setLayout(new BorderLayout()); // Đảm bảo panelBang có layout
        panelBang.add(scrollPane, BorderLayout.CENTER);

        // Kiểm tra JScrollBar nếu cần
        JScrollBar sb = scrollPane.getVerticalScrollBar();

    
    }
}
