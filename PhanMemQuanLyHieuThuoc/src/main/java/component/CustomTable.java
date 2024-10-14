package component;

import javax.swing.*;
import javax.swing.table.*;

import userInterfaces.UIStyles;

import java.awt.*;
import java.awt.event.*;

public class CustomTable extends JTable {
	private CustomTableRowStyle rowStyle;
    // Constructor
    public CustomTable(Object[][] data, Object[] columnNames, CustomTableRowStyle headerStyle, CustomTableRowStyle rowStyle, int gapBetweenColumns) {
        this(data, columnNames);
        this.setEnabled(false);
        setShowHorizontalLines(true);
        setShowVerticalLines(false);
        
        setHeader(headerStyle, columnNames.length);
        
        this.setRowHeight(rowStyle.getHeight());
        this.rowStyle = rowStyle;
        this.setRowMargin(gapBetweenColumns);

    }
    
    
    
    public CustomTable(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
		// TODO Auto-generated constructor stub
	}



	@Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
    	super.prepareRenderer(renderer, row, column);
        Component stamp = super.prepareRenderer(renderer, row, column);
        if (true) {
        	stamp.setBackground(rowStyle.getBackgroundColor());
        	stamp.setFont(rowStyle.getFont());
        }
           
        else
            stamp.setBackground(rowStyle.getForegroundColor());
        	stamp.setFont(rowStyle.getFont());
        return stamp;
    }
    
    private void setHeader(CustomTableRowStyle headerStyle, int numOfRows) {
    	JTableHeader header = getTableHeader();
    	DefaultTableCellRenderer myHeaderRender = new DefaultTableCellRenderer() {
        	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column) {
        	if (value == null)
        	return super.getTableCellRendererComponent(table,
        	value, isSelected, hasFocus, row, column);
        	JLabel label = new JLabel(value.toString());
        	label.setFont(headerStyle.getFont());
        	return label;
        	}
        };
        header.setBackground(headerStyle.getBackgroundColor());
        header.setForeground(headerStyle.getForegroundColor());
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, headerStyle.getHeight()));
        for(int i = 0 ; i<numOfRows; i++) {
        	
        	getTableHeader().getColumnModel().getColumn(i).setHeaderRenderer(myHeaderRender);
        }
        
    }
    

    public static class CustomTableRowStyle{
    	private Color backgroundColor;
    	private Color foregroundColor;
    	private Font font;
    	private int height;
    	private boolean centerAlignment;
    	public CustomTableRowStyle(Color backgroundColor, Color foregroundColor, Font font, int height, boolean centerAlignment) {
			super();
			this.backgroundColor = backgroundColor;
			this.foregroundColor = foregroundColor;
			this.font = font;
			this.height = height;
			this.centerAlignment = centerAlignment;
		}
		public Color getBackgroundColor() {
			return backgroundColor;
		}
		public Color getForegroundColor() {
			return foregroundColor;
		}
		public Font getFont() {
			return font;
		}
		public int getHeight() {
			return height;
		}
		public boolean getCenterAlignment() {
			return centerAlignment;
		}
		public void setBackgroundColor(Color backgroundColor) {
			this.backgroundColor = backgroundColor;
		}
		public void setForegroundColor(Color foregroundColor) {
			this.foregroundColor = foregroundColor;
		}
		public void setFont(Font font) {
			this.font = font;
		}
		public void setHeight(int height) {
			this.height = height;
		}
		public void setCenterAlignment(boolean centerAlignment) {
			this.centerAlignment = centerAlignment;
		}
    	
    	
    }


}

