package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import component.CustomButton;
import component.CustomTable;
import entity.SanPhamYTe;
import component.CustomButton.CustomButtonIconSide;

public class TongQuanUI extends JPanel{
	//value properties
	public JLabel soHoaDonTrongNgay;
	public JLabel soDoiTraTrongNgay;
	public JLabel duLieuThongKeCungKy;
	public ArrayList<SanPhamYTe> dsBanChay;
	
	//style properties
	private int padding =  20;
	private int gap = 20;
	private int centerPanelWidth = (int)((UIStyles.ApplicationWidth - 2 * padding) * 0.7);
	private int leftPanelWidth = (int)((UIStyles.ApplicationWidth - 2 * padding) * 0.3);
	private int bigPanelHeight = UIStyles.ApplicationHeight - padding * 2;
	private int centerPanelComponentWidth;
	private int duLieuPanelGap = 30;
	public TongQuanUI() {
		super();
		setStyle();
		taoHinh();
	}
	
	private void setStyle() {
		padding =  20;
		gap = 20;
		centerPanelWidth = (int)((UIStyles.ApplicationWidth - 2 * padding) * 0.7);
		leftPanelWidth = UIStyles.ApplicationWidth - 2 * padding - centerPanelWidth;
		bigPanelHeight = UIStyles.ApplicationHeight - padding * 2;
		centerPanelComponentWidth = centerPanelWidth - gap;
	}

	public void taoHinh() {
		setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
		this.setBackground(UIStyles.BackgroundColor);
		this.add(new JLabel("TongQuanUI"));
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
		/*
        scrollPane.getViewport().setBackground(Color.blue);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.red;
            }
        });
        scrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
        scrollPane.setPreferredSize(new Dimension(800, 500));
        */
		
		//Right Panel
	    JPanel rightPanel = new JPanel();
	    rightPanel.setPreferredSize(new Dimension(leftPanelWidth, bigPanelHeight));
	    rightPanel.setMinimumSize(new Dimension(leftPanelWidth, bigPanelHeight));
	    rightPanel.setBackground(Color.white);
	    
	    //Center Panel
	    JPanel centerPanel = new JPanel();
	    centerPanel.setPreferredSize(new Dimension(centerPanelWidth, bigPanelHeight));
	    centerPanel.setMaximumSize(new Dimension(centerPanelWidth, bigPanelHeight));

	    centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, gap));
	    
	    //ket qua hom nay
	    int todayResultPanelHeight = 350;
	    JPanel todayResultPanel = new JPanel();
	    todayResultPanel.setPreferredSize(new Dimension(centerPanelComponentWidth, todayResultPanelHeight));
	    todayResultPanel.setMinimumSize(new Dimension(centerPanelComponentWidth, todayResultPanelHeight));
	    todayResultPanel.setBackground(Color.white);
	    
	    JLabel todayResultPanelTitle = new JLabel("Kết quả hôm nay");
	    todayResultPanelTitle.setFont(UIStyles.TitleFont);
	    todayResultPanelTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
	    
	    JPanel duLieuPanel = new JPanel();
	    duLieuPanel.setPreferredSize(new Dimension(centerPanelComponentWidth, todayResultPanelHeight - 100));
	    duLieuPanel.setMinimumSize(new Dimension(centerPanelComponentWidth, todayResultPanelHeight - 100));
	    duLieuPanel.setBackground(Color.white);
	    duLieuPanel.setLayout(new BoxLayout(duLieuPanel, BoxLayout.X_AXIS));
	    duLieuPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
	    
	    
	    //thong ke hoa don
	    JLabel moneyIcon = new JLabel(UIStyles.MoneyIcon);
	    JPanel thongKeHoaDon = new JPanel();
	    thongKeHoaDon.setLayout(new BoxLayout(thongKeHoaDon, BoxLayout.Y_AXIS));
	    
	    
	    soHoaDonTrongNgay = new JLabel("10", SwingConstants.CENTER);
	    soHoaDonTrongNgay.setFont(UIStyles.BoldFont);
	    soHoaDonTrongNgay.setAlignmentX(Component.CENTER_ALIGNMENT);
	    JLabel hoaDonLabel = new JLabel("Hóa đơn", SwingConstants.CENTER);
	    hoaDonLabel.setFont(UIStyles.BoldFont);
	    hoaDonLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    thongKeHoaDon.add(soHoaDonTrongNgay);
	    thongKeHoaDon.add(hoaDonLabel);
	    thongKeHoaDon.setBackground(Color.white);
	    //thong ke doi tra
	    JLabel returnProductIcon = new JLabel(UIStyles.ReturnProductIcon);
	    JPanel thongKeDoiTra = new JPanel();
	    thongKeDoiTra.setLayout(new BoxLayout(thongKeDoiTra, BoxLayout.Y_AXIS));
	    
	    
	    soDoiTraTrongNgay = new JLabel("1", SwingConstants.CENTER);
	    soDoiTraTrongNgay.setFont(UIStyles.BoldFont);
	    soDoiTraTrongNgay.setAlignmentX(Component.CENTER_ALIGNMENT);
	    JLabel soDoiTraTrongNgayLabel = new JLabel("Đổi trả", SwingConstants.CENTER);
	    soDoiTraTrongNgayLabel.setFont(UIStyles.BoldFont);
	    soDoiTraTrongNgayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    thongKeDoiTra.add(soDoiTraTrongNgay);
	    thongKeDoiTra.add(soDoiTraTrongNgayLabel);
	    thongKeDoiTra.setBackground(Color.white);
	    //thong ke cung ky
	    JLabel changeIcon = new JLabel(UIStyles.IncreaseIcon);
	    JPanel thongKeCungKy = new JPanel();
	    thongKeCungKy.setLayout(new BoxLayout(thongKeCungKy, BoxLayout.Y_AXIS));
	    
	    
	    duLieuThongKeCungKy = new JLabel("15.12%", SwingConstants.CENTER);
	    duLieuThongKeCungKy.setFont(UIStyles.BoldFont);
	    duLieuThongKeCungKy.setAlignmentX(Component.CENTER_ALIGNMENT);
	    JLabel duLieuThongKeCungKyLabel = new JLabel("So với cùng kỳ tháng trước", SwingConstants.CENTER);
	    duLieuThongKeCungKyLabel.setFont(UIStyles.BoldFont);
	    duLieuThongKeCungKyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    thongKeCungKy.add(duLieuThongKeCungKy);
	    thongKeCungKy.add(duLieuThongKeCungKyLabel);
	    thongKeCungKy.setBackground(Color.white);
	    
	    //separator
	    JSeparator duLieuPanelSeparator1 = new JSeparator(SwingConstants.VERTICAL);
	    duLieuPanelSeparator1.setBackground(Color.GRAY);
	    Dimension size = new Dimension(duLieuPanelSeparator1.getPreferredSize().width,duLieuPanelSeparator1.getMaximumSize().height);
	    duLieuPanelSeparator1.setMaximumSize(size);
	    
	    JSeparator duLieuPanelSeparator2 = new JSeparator(SwingConstants.VERTICAL);
	    duLieuPanelSeparator2.setBackground(Color.GRAY);
	    duLieuPanelSeparator2.setMaximumSize(size);
	    //hoan thanh panel thong ke

	    duLieuPanel.add(Box.createRigidArea(new Dimension(duLieuPanelGap, duLieuPanelGap)));
	    duLieuPanel.add(moneyIcon);
	    duLieuPanel.add(Box.createRigidArea(new Dimension(duLieuPanelGap, duLieuPanelGap)));
	    duLieuPanel.add(thongKeHoaDon);
	    duLieuPanel.add(Box.createRigidArea(new Dimension(duLieuPanelGap * 2, duLieuPanelGap)));
	    duLieuPanel.add(duLieuPanelSeparator1);
	    duLieuPanel.add(Box.createRigidArea(new Dimension(duLieuPanelGap * 2, duLieuPanelGap)));
	    duLieuPanel.add(returnProductIcon);
	    duLieuPanel.add(Box.createRigidArea(new Dimension(duLieuPanelGap, duLieuPanelGap)));
	    duLieuPanel.add(thongKeDoiTra);
	    duLieuPanel.add(Box.createRigidArea(new Dimension(duLieuPanelGap * 2, duLieuPanelGap)));
	    duLieuPanel.add(duLieuPanelSeparator2);
	    duLieuPanel.add(Box.createRigidArea(new Dimension(duLieuPanelGap , duLieuPanelGap)));
	    duLieuPanel.add(changeIcon);
	    duLieuPanel.add(Box.createRigidArea(new Dimension(duLieuPanelGap, duLieuPanelGap)));
	    duLieuPanel.add(thongKeCungKy);
	    
	    todayResultPanel.setLayout(new BorderLayout());
	    todayResultPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    todayResultPanel.add(todayResultPanelTitle, BorderLayout.NORTH);
	    todayResultPanel.add(duLieuPanel, BorderLayout.CENTER);	    
	    
	    //unknown Panel
	    JPanel unknownPanel = new JPanel();
	    unknownPanel.setPreferredSize(new Dimension(centerPanelComponentWidth, bigPanelHeight - todayResultPanelHeight));
	    unknownPanel.setBackground(Color.white);
	    
	    
	    //san pham ban chay panel
	    JPanel sanPhamBanChayPanel = new JPanel();
	    JLabel anhThuoc = new JLabel(UIStyles.ProductImage);
	    
	    rightPanel.add(anhThuoc);
	    
	    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
	    centerPanel.add(todayResultPanel);
	    centerPanel.add(Box.createRigidArea(new Dimension(centerPanelWidth - gap, gap)));
	    centerPanel.add(unknownPanel);
	    
	    this.add(rightPanel, BorderLayout.EAST);
	    this.add(centerPanel, BorderLayout.CENTER);
	    
	}
	

}
