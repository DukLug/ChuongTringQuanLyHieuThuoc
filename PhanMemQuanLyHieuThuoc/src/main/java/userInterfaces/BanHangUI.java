package userInterfaces;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BanHangUI extends JPanel {
	
	public BanHangUI() {
		super();
		taoHinh();
	}
	
	public void taoHinh() {
		setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight + UIStyles.NavBarHeight));
		setLayout(null);
		setBackground(Color.WHITE);
		
		JPanel panelTong = new JPanel();
		panelTong.setBackground(Color.RED);
		panelTong.setBounds(0, 0, 1800, 925);
		add(panelTong);
	}

}
