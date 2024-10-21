package userInterfaces;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.BoxLayout;

public class NhaCungCapUI_1 extends JPanel {

    private JTextField textField;
    private JTextField txtTim;

    public NhaCungCapUI_1() {
        super();
        taoHinh();
    }
    
    public void taoHinh() {
        setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
        this.setBackground(Color.white);
        setLayout(null);

        JPanel panelTong = new JPanel();
        panelTong.setBackground(Color.PINK);
        panelTong.setBounds(0, 0, UIStyles.ApplicationWidth, UIStyles.MainSectionHeight);
        add(panelTong);
        panelTong.setLayout(new BorderLayout(0, 0));

        // thanh công cụ
        JPanel panelThanhCongCu = new JPanel();
        panelTong.add(panelThanhCongCu, BorderLayout.NORTH);
        panelThanhCongCu.setLayout(new BoxLayout(panelThanhCongCu, BoxLayout.X_AXIS));

        // Nhãn "NHÀ CUNG CẤP"
        JPanel panelLabel = new JPanel();
        panelLabel.setLayout(new BorderLayout());
        JLabel lblNewLabel_1 = new JLabel("NHÀ CUNG CẤP");
        lblNewLabel_1.setFont(UIStyles.FONT_BLOD);
        lblNewLabel_1.setHorizontalAlignment(JLabel.CENTER); // Căn giữa nhãn
        panelLabel.add(lblNewLabel_1, BorderLayout.CENTER);
        
        // Đặt độ rộng tối thiểu cho nhãn (chiếm 20% chiều ngang)
        panelLabel.setPreferredSize(new Dimension((int)(UIStyles.ApplicationWidth * 0.05), 50));
        panelThanhCongCu.add(panelLabel);

        // Panel chứa phần tìm kiếm
        JPanel panelTim = new JPanel();
        panelTim.setLayout(new BorderLayout(0, 0));
        
        JLabel icon = new JLabel(UIStyles.FInd);
        panelTim.add(icon, BorderLayout.WEST);
        icon.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        txtTim = new JTextField();
        txtTim.setFont(UIStyles.FONT);
        panelTim.add(txtTim, BorderLayout.CENTER);
        txtTim.setBackground(Color.WHITE);
        txtTim.setColumns(10);
        txtTim.setBorder(BorderFactory.createEmptyBorder());

        // Đặt độ rộng tối thiểu cho phần tìm kiếm (chiếm 80% chiều ngang)
        panelTim.setPreferredSize(new Dimension((int)(UIStyles.ApplicationWidth * 0.4), 50));
        panelThanhCongCu.add(panelTim);
    }
}
