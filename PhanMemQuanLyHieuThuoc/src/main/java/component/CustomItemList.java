package component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import userInterface.TrangChuUI;

public class CustomItemList extends JPanel {
	private int prefWidth;
	private int prefHeight;
    private JPanel panel; 
    private JPanel header;
    private JScrollPane scrollPane;
    private int itemHeightGap;
    private int[] columnWidths;
    private Color backgroundColor;
    
    private ArrayList<CustomItem> itemList;

    public CustomItemList(int prefWidth, int prefHeight, int itemHeight, int itemHeightGap, Color backgroundColor, int[] columnWidths, Color headerBackgroundColor, int headerHeight, String[] columnNames, Font headerFont,  ArrayList<CustomItem> itemList) {
        super();
        this.prefWidth = prefWidth;
        this.prefHeight = prefHeight;
        this.itemHeightGap= itemHeightGap; 
        this.columnWidths = columnWidths;
        this.backgroundColor = backgroundColor;
        
        this.setPreferredSize(new Dimension(prefWidth, prefHeight));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(backgroundColor);
        if(headerHeight!=0) {
        	setHeader(prefWidth, headerHeight,  headerBackgroundColor, headerFont,  columnWidths, columnNames);
        }
        

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(backgroundColor);

        scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(prefWidth, prefHeight));
        scrollPane.setMaximumSize(new Dimension(prefWidth, prefHeight));
        scrollPane.setMinimumSize(new Dimension(prefWidth, prefHeight));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); 
        scrollPane.setBackground(backgroundColor);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(scrollPane);
        
        this.itemList = itemList;
    }
 
    
    public CustomItemList(int prefWidth, int prefHeight, int itemHeight,int itemHeightGap, Color backgroundColor, int[] columnWidths, ArrayList<CustomItem> itemList) {
        this( prefWidth, prefHeight, itemHeight, itemHeightGap, backgroundColor, columnWidths, null, 0, null, null, itemList);
    }
    
    public void addItem(CustomItem item) {
    	if(item.getPreferredSize().width > this.getPreferredSize().width) {
    		throw new IllegalArgumentException("Item is larger than scrollable panel");
    	}
    	if(itemList.size()!=0) {
    		JPanel gapPanel = new JPanel();
    		gapPanel.setPreferredSize(new Dimension(prefWidth, itemHeightGap));
    		gapPanel.setBackground(backgroundColor);
    		panel.add(gapPanel);
    	}
        panel.add(item);
        itemList.add(item);
        panel.revalidate();
        panel.repaint();   
    }
    
    public void setHeader(int width, int headerHeight, Color headerBackgroundColor,Font font, int[] columnWidths, String[] columnNames) {
    	if(columnNames.length != columnWidths.length) {
    		throw new IllegalArgumentException("Collumn names and column widths doesn't match");
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
    
    public ArrayList<CustomItem> getItemList() {
        return itemList;
    }

    public void updateList(ArrayList<CustomItem> newItemList) {
        panel.removeAll();
        itemList = new ArrayList<>();
        for (CustomItem item : newItemList) {
            addItem(item);
        }
    }

    public void removeItem(CustomItem item) {
        if (itemList.contains(item)) {
            panel.remove(item);
            itemList.remove(item);
            panel.revalidate();
            panel.repaint();
        }
    }

    public void removeItemAt(int index) {
        if (index >= 0 && index < itemList.size()) {
            CustomItem item = itemList.get(index);
            panel.remove(item);
            itemList.remove(index);
            panel.revalidate();
            panel.repaint();
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }
    

}
