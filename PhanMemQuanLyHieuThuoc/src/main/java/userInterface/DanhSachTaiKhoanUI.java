package userInterface;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DanhSachTaiKhoanUI extends JPanel{
	
	public DanhSachTaiKhoanUI() {
		super();
		taoHinh();
	}
	
	public void taoHinh() {
		setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
		this.setBackground(Color.green);
		this.add(new JLabel("DanhSachTaiKhoanUI"));
	}
}

