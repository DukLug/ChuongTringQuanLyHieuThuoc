package userInterfaces;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DoiTraUI extends JPanel{
	
	public JTextField textField;
	
	public DoiTraUI() {
		super();
		taoHinh();
	}
	
	public void taoHinh() {
		setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
		this.setBackground(Color.WHITE);
		this.add(new JLabel("DoiTraUI"));
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(100, 50));
		
		add(textField);
	}
}
