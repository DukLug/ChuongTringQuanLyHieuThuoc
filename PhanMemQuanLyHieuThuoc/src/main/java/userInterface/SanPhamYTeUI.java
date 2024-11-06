package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import component.CustomButton;
import component.CustomItem;
import component.CustomItemList;
import component.CustomTable;
import controller.SanPhamCTR;
import dao.SanPhamYTeDAO;
import component.CustomButton.CustomButtonIconSide;
import entity.SanPhamYTe;
import functionalClass.DataImporter;

public class SanPhamYTeUI extends JPanel{
	
	private ArrayList<SanPhamYTe> dsSanPham;
	private CustomItemList sanPhamYTeList;
	
	public SanPhamYTeUI() {		
		super();
		
		
		
		
		taoHinh();
		dsSanPham = SanPhamCTR.layDanhSachTatCaSanPham();
		capNhatBangSanPham(dsSanPham);

	}
	
	public void taoHinh() {
		setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
		this.setBackground(UIStyles.BackgroundColor);
		this.add(new JLabel("San pham"));
		
		sanPhamYTeList = new CustomItemList(
	            700, 
	            1200, 
	            800, 
	            20, 
	            Color.gray, 
	            new int[]{150, 150, 150, 150}, 
	            new ArrayList<CustomItem>()
	        );
		
		this.add(new SanPhamCell(SanPhamCTR.layDanhSachTatCaSanPham().getFirst()));
	}
	private void capNhatBangSanPham(ArrayList<SanPhamYTe> dsSanPham) {
		ArrayList<SanPhamRow> bangSp = layBang(dsSanPham);
		for(int i = 0; i < bangSp.size(); i++) {

			sanPhamYTeList.addItem(bangSp.get(i));
		}
	}
	
	private ArrayList<SanPhamRow> layBang(ArrayList<SanPhamYTe> dsSanPham) {
	    ArrayList<SanPhamRow> bang = new ArrayList<>();

	    // Iterate over the list in steps of 5
	    for (int i = 0; i < dsSanPham.size(); i += 5) {
	        // Get a sublist of up to 5 items
	        int end = Math.min(i + 5, dsSanPham.size());
	        List<SanPhamYTe> subList = dsSanPham.subList(i, end);

	        // Convert the sublist to an array and fill with nulls if less than 5
	        SanPhamYTe[] sanPhamArray = new SanPhamYTe[5];
	        for (int j = 0; j < subList.size(); j++) {
	            sanPhamArray[j] = subList.get(j);
	        }

	        // Create and add a new SanPhamRow
	        bang.add(new SanPhamRow(sanPhamArray));
	    }

	    return bang;
	}


	
	public static class SanPhamRow extends CustomItem{
		private static int prefWidth = 700;
		private static int prefHeight = 800;
		private static Color backgroundColor = Color.white;
		private static Border border = BorderFactory.createEmptyBorder();
		//Chú ý nên match với lúc tạo ItemList
		private static int[] cellsWidth = new int[] {150, 150, 150, 150};
		
		//cells
		private JComponent[] cells;
		
		//Du lieu item
		public SanPhamYTe[] sanPham;	
		
		//Các cell thêm vào đây rồi set phía dưới giống các ui, để public để sau đó lấy dữ liệu, set dữ liệu
		public JLabel tenSanPham;
		public JLabel giaSanPham;
		
		//Thêm nội dung cho các trường đã khai báo ở trên vào constructor, có stt mới thêm stt vào, không thì chỉ cần data
		public SanPhamRow(SanPhamYTe[] sanPham) {
			//Tạo item rỗng, trên cop thì ở đây  cop
			super(prefWidth, prefHeight, backgroundColor, border, cellsWidth);
			
			
			this.sanPham = sanPham;
			
			SanPhamCell cell0 = new SanPhamCell(sanPham[0]);
			SanPhamCell cell1 = new SanPhamCell(sanPham[1]);
			SanPhamCell cell2 = new SanPhamCell(sanPham[2]);
			SanPhamCell cell3 = new SanPhamCell(sanPham[3]);
		
			//Thêm cells vào Item
			cells = new JComponent[] {
					cell0, cell1, cell2, cell3
			};
			
			super.addCells(cells);
		}
		

		public void layThongTin() {
			System.out.println(this.sanPham);
		}
		

	}
	
	public static class SanPhamCell extends JPanel {
		// data
		private SanPhamYTe sanPham;
		
		//style
		public static int prefWidth = 150;
		public static int prefHeight = 200;
		public static Color backgroundColor = Color.white;
		public static Font nameFont =  new Font("Tahoma", Font.BOLD, 14);
		public static Font informationFont =  new Font("Tahoma", Font.BOLD, 12);;
		
		
		public SanPhamCell(SanPhamYTe sanPham) {
			super();
			this.sanPham = sanPham;
			
			setPreferredSize(new Dimension(prefWidth, prefHeight));
			this.setBackground(backgroundColor);
			this.setLayout(new BorderLayout());
			
			//image
			JPanel imagePanel = new JPanel();
			Image img = sanPham.getHinhAnh().getScaledInstance(prefWidth, prefWidth,  Image.SCALE_SMOOTH);
			JLabel hinhAnh = new JLabel(new ImageIcon(img));
			imagePanel.add(hinhAnh);
			imagePanel.setBackground(backgroundColor);
			
			//name and informations
			JPanel descriptionPanel = new JPanel();
			descriptionPanel.setLayout(new GridLayout(3, 1));
			descriptionPanel.setPreferredSize(new Dimension(prefWidth, prefHeight-prefWidth));
			descriptionPanel.setMinimumSize(new Dimension(prefWidth, prefHeight-prefWidth));
			
			JLabel nameLabel = new JLabel(sanPham.getTenSanPham());
			nameLabel.setFont(nameFont);
			
			JLabel priceLabel = new JLabel(sanPham.getGiaBan().toString() + "đ");
			priceLabel.setFont(informationFont);
			
			descriptionPanel.add(nameLabel);
			descriptionPanel.add(priceLabel);
			
			//add all 
			this.add(imagePanel, BorderLayout.NORTH);
			this.add(descriptionPanel, BorderLayout.CENTER);
		}
	}
}
