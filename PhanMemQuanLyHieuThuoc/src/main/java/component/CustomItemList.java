package component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import userInterfaces.TrangChuUI;

public class CustomItemList extends JPanel {
    private int numOfColumn = 0;
    private JPanel panel; 
    private JPanel header;
    private JScrollPane scrollPane;
    private int[] columnWidths;

    public CustomItemList(int prefWidth, int prefHeight, int itemHeight, int[] columnWidths, Color headerBackgroundColor, int headerHeight, String[] columnNames, Font headerFont) {
        super();
        numOfColumn = columnWidths.length;
        this.columnWidths = columnWidths;
        
        this.setPreferredSize(new Dimension(prefWidth, prefHeight));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        if(headerHeight!=0) {
        	setHeader(prefWidth, headerHeight,  headerBackgroundColor, headerFont,  columnWidths, columnNames);
        }
        

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.white);

        scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(prefWidth, prefHeight));
        scrollPane.setMaximumSize(new Dimension(prefWidth, prefHeight));
        scrollPane.setMinimumSize(new Dimension(prefWidth, prefHeight));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); 
        

        this.add(scrollPane);
    }
    
    public CustomItemList(int prefWidth, int prefHeight, int itemHeight, int[] columnWidths) {
        this( prefWidth, prefHeight, itemHeight, columnWidths, null, 0, null, null);
    }
    
    public void addItem(JPanel item) {
    	if(item.getPreferredSize().width > this.getPreferredSize().width) {
    		TrangChuUI.hienLoi(getClass(), new Exception("Item is larger than scrollable panel"));
    	}
        panel.add(item);
        panel.revalidate();
        panel.repaint();   
    }
    
    public void setHeader(int width, int headerHeight, Color headerBackgroundColor,Font font, int[] columnWidths, String[] columnNames) {
    	if(columnNames.length != columnWidths.length) {
    		TrangChuUI.hienLoi(getClass(), new Exception("Collumn names and column widths doesn't match"));
    		return;
    	}
    	
    	header = new JPanel();
    	header.setPreferredSize(new Dimension(width, headerHeight));
    	header.setMaximumSize(new Dimension(width, headerHeight));
    	header.setBackground(headerBackgroundColor);
    	header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
    	for(int i = 0; i< columnNames.length; i++) {
    		JPanel namePanel = new JPanel();
    		namePanel.setPreferredSize(new Dimension(columnWidths[i],  headerHeight));
    		namePanel.setBackground(headerBackgroundColor);
    		JLabel label = new JLabel(columnNames[i]);
    		label.setFont(font);
    		namePanel.add(label);
    		header.add(namePanel);
    	}
    	
    	add(header);
    	
    }
}
