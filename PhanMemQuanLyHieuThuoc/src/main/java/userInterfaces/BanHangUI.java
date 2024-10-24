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
		setPreferredSize(new Dimension(1800, 925));
		this.setBackground(Color.ORANGE);
		setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight + UIStyles.NavBarHeight));
		setLayout(null);
		setBackground(Color.WHITE);

		JPanel panelTong = new JPanel();
		panelTong.setBackground(Color.PINK);
		panelTong.setBackground(Color.RED);
		panelTong.setBounds(0, 0, 1800, 925);
		add(panelTong);
		panelTong.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(1274, 0, 526, 925);
		panelTong.add(panel);
	}

}