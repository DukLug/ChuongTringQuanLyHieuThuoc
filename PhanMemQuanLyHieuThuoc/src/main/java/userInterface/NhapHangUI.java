package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import component.CustomButton;
import component.CustomItemList;
import component.RoundedBorder;
import component.CustomButton.ButtonStyle;
import component.CustomButton.CustomButtonIconSide;
import entity.DonViTinh;
import component.CustomItem;
import testEntity.TestNguoi;
import testEntity.Thuoc;

public class NhapHangUI extends JPanel {
	
	public ArrayList<Thuoc> bangNhapThuoc;
	public CustomButton btnCapNhat;

	
	
	NhapHangUI() {
		super();
		taoHinh();
	}
	
	public void taoHinh() {
		setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
		this.setBackground(Color.white);
		this.add(new JLabel("NhapHangUI"));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		
		 CustomItemList nhapHangList = new CustomItemList(
		            1500, 
		            500, 
		            100, 
		            50, 
		            Color.white, 
		            new int[]{500, 500, 500}, 
		            Color.blue, 
		            50, 
		            new String[]{"HI", "Hello", "A"}, 
		            new Font("Arial", Font.BOLD, 14), 
		            new ArrayList<CustomItem>()
		        );
		nhapHangList.addItem(new NhapHangRow(1,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop, 12)));
		nhapHangList.addItem(new NhapHangRow(2,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop, 12)));
		nhapHangList.addItem(new NhapHangRow(3,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop, 12)));
		nhapHangList.addItem(new NhapHangRow(4,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop, 12)));
		nhapHangList.addItem(new NhapHangRow(5,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop, 12)));
		nhapHangList.addItem(new NhapHangRow(6,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop, 12)));
		nhapHangList.addItem(new NhapHangRow(7,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop, 12)));
		nhapHangList.addItem(new NhapHangRow(8,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop, 12)));
		
		nhapHangList.addItem(new NhapHangRow(9,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop, 12)));
		nhapHangList.addItem(new NhapHangRow(10,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop, 12)));
		nhapHangList.addItem(new NhapHangRow(11,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop, 12)));
		nhapHangList.addItem(new NhapHangRow(12,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop, 12)));
		nhapHangList.addItem(new NhapHangRow(13,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop, 12)));
		nhapHangList.addItem(new NhapHangRow(14,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop, 12)));
		nhapHangList.addItem(new NhapHangRow(15,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop, 12)));
		
		btnCapNhat = new CustomButton("Cập nhật thông tin", UIStyles.LabelBarButtonStyle, UIStyles.GoBackIcon, CustomButtonIconSide.LEFT, ()->kiemTraCapNhat(nhapHangList));
		add(btnCapNhat);
		add(nhapHangList);
	}
	public void kiemTraCapNhat(CustomItemList list) {
		ArrayList<CustomItem> newItems = new ArrayList<CustomItem>();
		Random rand = new Random();
		int n = rand.nextInt();
		for(int i = 0; i < n%20; i++) {
			newItems.add(new NhapHangRow(i,new Thuoc("thuoc1", "ten 1", DonViTinh.Hop, 12)));
		}
		list.updateList(newItems);
	}
	
	public static class NhapHangRow extends CustomItem{
		//Item, ItemList chỉ phụ trách thể hiện thông tin, thông tin lưu ở các class con giống như NhapHangRow này
		//Các thuộc tính style, code thẳng vào, phải có static, cop về chỉnh lại cũng được
		private static int prefWidth = 1500;
		private static int prefHeight = 100;
		private static Font font = UIStyles.DefaultFont;
		private static Color backgroundColor = Color.white;
		//Rounded border phức tạp, có thể phá panel bên trong, bán kính càng lớn càng dễ hỏng
		private static Border border = new RoundedBorder(Color.BLUE, 3, 20);
		
		//Chú ý nên match với lúc tạo ItemList
		private static int[] cellsWidth = new int[] {500, 500, 500};
		
		//cells
		private JComponent[] cells;
		
		//Du lieu item
		public Thuoc thuoc;	
		
		//Các cell thêm vào đây rồi set phía dưới giống các ui, để public để sau đó lấy dữ liệu, set dữ liệu
		public JLabel sttLabel;
		//Các phần nằm chung trong một cell phải thêm vào đây , xuống dưới tạo một JPanel gộp lại
		public CustomButton btn1;
		public JTextField textField;
		public CustomButton btn2;
		public JLabel moneyLabel;
		public CustomButton btnThongTin;
		
		//Thêm nội dung cho các trường đã khai báo ở trên vào constructor, có stt mới thêm stt vào, không thì chỉ cần data
		public NhapHangRow(int stt, Thuoc thuoc) {
			//Tạo item rỗng, trên cop thì ở đây  cop
			super(prefWidth, prefHeight, backgroundColor, border, cellsWidth);
			
			//Set dữ liệu
			this.thuoc = thuoc;
			
			//Thiết kế các cell, TỰ gõ
			sttLabel = new JLabel(stt+"");
			
			JPanel cell2 = new JPanel();
			cell2.setLayout(new BoxLayout(cell2, BoxLayout.X_AXIS));
			ButtonStyle btnStyle = new ButtonStyle(
					20, 10, 25, Color.WHITE, Color.decode("#7A7A7A"), Color.decode("#7A7A7A"), Color.decode("#5E5E5E")
				);
			btn1 = new CustomButton("-", UIStyles.LabelBarButtonStyle, UIStyles.GoBackIcon, CustomButtonIconSide.LEFT, ()->capNhatSoLuong(-1));
			//thêm dữ liệu ban đầu
			textField = new JTextField(thuoc.soLuong + "");
			
			btn2 = new CustomButton("+", UIStyles.LabelBarButtonStyle, UIStyles.GoBackIcon, CustomButtonIconSide.LEFT, ()->capNhatSoLuong(1));
			cell2.add(btn1);
			cell2.add(textField);
			cell2.add(btn2);
			
			JPanel cell3 = new JPanel();
			moneyLabel = new JLabel(thuoc.maThuoc + ".000 đ");
			
			btnThongTin = new CustomButton("In thông tin", UIStyles.LabelBarButtonStyle, UIStyles.GoBackIcon, CustomButtonIconSide.LEFT, ()->layThongTin());
			cell3.add(moneyLabel);
			cell3.add(btnThongTin);
			
			//Thêm cells vào Item
			cells = new JComponent[] {
					sttLabel, cell2, cell3
			};
			
			super.addCells(cells);
		}
		
		public void capNhatSoLuong(int thayDoi) {
			thuoc.soLuong += thayDoi;
			textField.setText("" + thuoc.soLuong);
					
		}
		public void layThongTin() {
			System.out.println(this.thuoc);
		}
		

	}
}
