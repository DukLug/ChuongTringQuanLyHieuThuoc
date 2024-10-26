package userInterfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.Icon
;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

import component.CustomButton.ButtonStyle;
import component.CustomTable.CustomTableRowStyle;

public class UIStyles {
	public static final int ApplicationWidth = 1920;
	public static final int ApplicationHeight = 1000;
	public static final int TopSectionHeight = 150;
	public static final int LabelBarHeight = 75;
	public static final int NavBarHeight = TopSectionHeight-LabelBarHeight;	
	public static final int MainSectionHeight = ApplicationHeight - TopSectionHeight;
	
	public static final ButtonStyle LabelBarButtonStyle = new ButtonStyle(
			220, NavBarHeight, 18, Color.black, Color.decode("#E1E1E1"), Color.decode("#dadada"), Color.decode("#cdcdcd")
		);
	
	public static final Color NavBarBackgroundColor = Color.decode("#527DFF");
	
	public static final ButtonStyle NavBarButtonStyle = new ButtonStyle(
			160, NavBarHeight, 18, Color.white, Color.decode("#527DFF"), Color.decode("#476FE8"), Color.decode("#3D5BB4")
		);
	
	public static final ButtonStyle DoiTraButtonStyle = new ButtonStyle(
			160, NavBarHeight, 18, Color.white, Color.decode("#E8C047"), Color.decode("#deb63e"), Color.decode("#d5ae38")
		);
	
	public static final ButtonStyle BanHangButtonStyle = new ButtonStyle(
			160, NavBarHeight, 18, Color.white, Color.decode("#15C91B"), Color.decode("#14c41a"), Color.decode("#0eb813")
		);
	
	public static final ButtonStyle ThemButtonStyle = new ButtonStyle(
			160, NavBarHeight, 25, Color.WHITE, Color.decode("#15C91B"), Color.decode("#14c41a"), Color.decode("#0eb813")
		);
	
	public static final ButtonStyle CapNhatButtonStyle = new ButtonStyle(
			160, NavBarHeight, 25, Color.WHITE, Color.decode("#E8C047"), Color.decode("#deb63e"), Color.decode("#d5ae38")
		);
	
	public static final ButtonStyle XoaTrangButtonStyle = new ButtonStyle(
			160, NavBarHeight, 25, Color.WHITE, Color.decode("#7A7A7A"), Color.decode("#7A7A7A"), Color.decode("#5E5E5E")
		);
	
	public static final int NavBarDropBoxItemHeight = NavBarHeight - 30;
	
	public static final Color MainSectionBackgroundColor = Color.gray;
	
	public static final ImageIcon GoBackIcon  = getIconFromPath("resources/GoBackIcon.png", 50, 50);
	public static final ImageIcon AboutIcon  = getIconFromPath("resources/AboutIcon.png", 30, 30);
	public static final ImageIcon HelpIcon  = getIconFromPath("resources/HelpIcon.png", 30, 30);
	public static final ImageIcon UserIcon  = getIconFromPath("resources/UserIcon.png", 30, 30);
	public static final ImageIcon HomeIcon  = getIconFromPath("resources/HomeIcon.png", 30, 30);
	public static final ImageIcon ProductIcon  = getIconFromPath("resources/ProductIcon.png", 30, 30);
	public static final ImageIcon TransferIcon  = getIconFromPath("resources/TransferIcon.png", 30, 30);
	public static final ImageIcon ContactIcon  = getIconFromPath("resources/ContactIcon.png", 30, 30);
	public static final ImageIcon EmployeeIcon  = getIconFromPath("resources/EmployeeIcon.png", 30, 30);
	public static final ImageIcon ReportIcon  = getIconFromPath("resources/ReportIcon.png", 30, 30);
	public static final ImageIcon AnalysingIcon  = getIconFromPath("resources/AnalysingIcon.png", 30, 30);
	public static final ImageIcon PolicyIcon  = getIconFromPath("resources/PolicyIcon.png", 42, 42);
	public static final ImageIcon ReturnIcon  = getIconFromPath("resources/ReturnIcon.png", 30, 30);
	public static final ImageIcon SellIcon  = getIconFromPath("resources/SellIcon.png", 30, 30);
	public static final ImageIcon MoneyIcon  = getIconFromPath("resources/MoneyIcon.png", 30, 30);
	public static final ImageIcon ReturnProductIcon  = getIconFromPath("resources/ReturnProductIcon.png", 30, 30);
	public static final ImageIcon IncreaseIcon  = getIconFromPath("resources/IncreaseIcon.png", 30, 30);
	public static final ImageIcon DecreaseIcon  = getIconFromPath("resources/DecreaseIcon.png", 30, 30);
<<<<<<< HEAD
	public static final ImageIcon FInd  = getIconFromPath("resources/find.png", 30, 30);
	public static final ImageIcon Add  = getIconFromPath("resources/add.png", 30, 30);
	public static final ImageIcon Update  = getIconFromPath("resources/update.png", 30, 30);
	public static final ImageIcon More  = getIconFromPath("resources/more.png", 20, 20);
	public static final ImageIcon trash  = getIconFromPath("resources/trash.png", 30, 30);
	public static final ImageIcon barCode  = getIconFromPath("resources/barCode.png", 30, 30);
	public static final Color backgroundColor = new Color(232, 234, 236);
	
	public static final Font FONT = new Font("Tahoma", Font.PLAIN, 20);
	public static final Font FONT_BLOD = new Font("Tahoma", Font.BOLD, 20);
=======
	public static final ImageIcon Find  = getIconFromPath("resources/find.png", 30, 30);
	public static final ImageIcon Add  = getIconFromPath("resources/add.png", 30, 30);
	public static final ImageIcon Update  = getIconFromPath("resources/update.png", 30, 30);
	
>>>>>>> main
	
	public static Font defaultFont = (new JLabel()).getFont();
	public static final CustomTableRowStyle NhanVienTableHeaderStyle = new CustomTableRowStyle(
			Color.decode("#DEF0DF"), Color.black, new Font(defaultFont.getName(), Font.BOLD, 20), 50, false);
	public static final CustomTableRowStyle NhanVienTableRowStyle = new CustomTableRowStyle(
			Color.white, Color.black, new Font(defaultFont.getName(), Font.PLAIN, 20), 50, false);
	
	private static ImageIcon getIconFromPath(String path, int width, int height) {
		ImageIcon imageIcon = new ImageIcon(path); 
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); 
		return new ImageIcon(newimg);
	}
	
	public static void setPlaceholder(JTextField textField, String placeholder) {
        // Đặt placeholder
        textField.setText(placeholder);
        textField.setForeground(Color.LIGHT_GRAY); // Màu chữ của placeholder
        textField.setFont(new Font("Tahoma", Font.ITALIC, 18));

        // Lắng nghe sự kiện focus
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText(""); // Xóa placeholder
                    textField.setForeground(Color.BLACK); // Đổi màu chữ khi nhập
                    textField.setFont(new Font("Tahoma", Font.PLAIN, 20)); // Đặt lại font khi nhập
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.LIGHT_GRAY); // Đổi lại màu chữ của placeholder
                    textField.setFont(new Font("Tahoma", Font.ITALIC, 18)); // Đặt font chữ nghiêng
                    textField.setText(placeholder); // Khôi phục placeholder
                }
            }
        });
    }
	
}
