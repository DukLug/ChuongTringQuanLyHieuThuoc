package component;

import java.awt.Color;
import java.awt.Container;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CustomItemList extends JPanel {
    private int prefWidth;
    private int prefHeight;
    private JPanel panel;
    private JScrollPane scrollPane;
    private int itemHeightGap;
    private int[] columnWidths;
    private Color backgroundColor;
    private int virtualItemCount;
    private int itemHeight;
    private int  minItemNeeded;

    private ArrayList<CustomItem> itemList;

    public CustomItemList(int prefWidth, int prefHeight, int itemHeight, int itemHeightGap, Color backgroundColor,
                          int[] columnWidths, Color headerBackgroundColor, int headerHeight, String[] columnNames,
                          Font headerFont, ArrayList<CustomItem> itemList) {
        super();
        this.prefWidth = prefWidth;
        this.prefHeight = prefHeight;
        this.itemHeightGap = itemHeightGap;
        this.columnWidths = columnWidths;
        this.backgroundColor = backgroundColor;

        this.setPreferredSize(new Dimension(prefWidth, prefHeight));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(backgroundColor);

        // Initialize header if needed
        if (headerHeight != 0) {
            setHeader(prefWidth, headerHeight, headerBackgroundColor, headerFont, columnWidths, columnNames);
        }

        // Content panel
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(backgroundColor);

        // Scroll pane
        scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(prefWidth, prefHeight));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Smooth scrolling
        scrollPane.setBorder(null);
        this.add(scrollPane);

        this.itemList = itemList;
        if (itemList != null) {
            updateList(itemList); // Populate the list if items are provided
        }
    }

    public CustomItemList(int prefWidth, int prefHeight, int itemHeight, int itemHeightGap, Color backgroundColor,
                          int[] columnWidths, ArrayList<CustomItem> itemList) {
        this(prefWidth, prefHeight, itemHeight, itemHeightGap, backgroundColor, columnWidths, null, 0, null, null,
                itemList);
    }

    public void addItem(CustomItem item) {
        if (item.getPreferredSize().width > this.getPreferredSize().width) {
            throw new IllegalArgumentException("Item is larger than scrollable panel");
        }
        if (!itemList.isEmpty()) {
            JPanel gapPanel = new JPanel();
            gapPanel.setPreferredSize(new Dimension(prefWidth, itemHeightGap));
            gapPanel.setBackground(backgroundColor);
            panel.add(gapPanel);
        }
        panel.add(item);
        itemList.add(item);
        panel.revalidate();
        panel.repaint();
        checkFewItem();
    }

    public void setHeader(int width, int headerHeight, Color headerBackgroundColor, Font font, int[] columnWidths,
                          String[] columnNames) {
        if (columnNames.length != columnWidths.length) {
            throw new IllegalArgumentException("Column names and column widths don't match");
        }

        JPanel header = new JPanel();
        header.setPreferredSize(new Dimension(width, headerHeight));
        header.setMaximumSize(new Dimension(width, headerHeight));
        header.setBackground(headerBackgroundColor);
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
        for (int i = 0; i < columnNames.length; i++) {
            JPanel namePanel = new JPanel();
            namePanel.setPreferredSize(new Dimension(columnWidths[i], headerHeight));
            namePanel.setBackground(headerBackgroundColor);
            JLabel label = new JLabel(columnNames[i]);
            label.setFont(font);
            namePanel.add(label);
            header.add(namePanel);
        }

        this.add(header);
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
    
//    public void removeAllItems() {
//        itemList.clear();  // Xóa toàn bộ phần tử trong danh sách itemList
//        
//        panel.removeAll();  
//        panel.revalidate();  
//        panel.repaint();  

    public void  checkFewItem() {    	
    	if(panel.getComponentCount() == 0) 
    		return;

    		for(int i = 0; i < minItemNeeded; i++) {   		
    			if(i>=panel.getComponentCount()) break;
        		Component c = panel.getComponent(i);
        		if(!(c instanceof CustomItem)) {
        			panel.remove(i);
        			i--;
        		}
        	}
    	
    	
    	itemHeight = panel.getComponent(0).getPreferredSize().height;
    	minItemNeeded = this.getPreferredSize().height / itemHeight + 1;
    	virtualItemCount = minItemNeeded - panel.getComponentCount();
    	for(int i = 0 ; i<virtualItemCount;i++) {
    		panel.add(new JPanel());
    	}

    }
}

