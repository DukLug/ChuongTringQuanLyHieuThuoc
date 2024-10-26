package component;

import javax.swing.*;
import javax.swing.table.*;

import userInterfaces.TrangChuUI;
import userInterfaces.UIStyles;

import java.awt.*;
import java.awt.event.*;
import application.*;

public class CustomTable extends JTable {
	private CustomTableRowStyle rowStyle;
    // Constructor
    public CustomTable(Object[][] data, Object[] columnNames, CustomTableRowStyle headerStyle, CustomTableRowStyle rowStyle, int gapBetweenColumns) {
    	super(data, columnNames);
    	if(PhanMemQuanLyHieuThuoc.HienLoi) {
    		for (Object[] row : data) {
                if (row.length != columnNames.length) {
                    TrangChuUI.hienLoi(this.getClass(), new Exception("Data row length does not match the number of column names"));
                }
            }
    	}
    	
        this.setEnabled(false);
        setShowHorizontalLines(true);
        setShowVerticalLines(false);
        
        setHeader(headerStyle, columnNames.length);
        
        this.setRowHeight(rowStyle.getHeight());
        this.rowStyle = rowStyle;
        
        TableColumnModel columnModel = this.getColumnModel();
    }
    
    
    
    public CustomTable(Object[][] data, Object[] columnNames, CustomTableRowStyle headerStyle, CustomTableRowStyle rowStyle, int gapBetweenColumns, int[] columnsWidth) {
		this(data, columnNames, headerStyle, rowStyle, gapBetweenColumns);
		TableColumnModel columnModel = this.getColumnModel();
		for(int i = 0; i<columnNames.length; i++) {
			columnModel.getColumn(i).setPreferredWidth(columnsWidth[i]);
			columnModel.getColumn(i).setMaxWidth(columnsWidth[i]);
		}
		this.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	}
    
    public void setData(Object[][] newData) {
        // Kiểm tra nếu dữ liệu mới không khớp với số lượng cột hiện tại
        if (newData.length > 0 && newData[0].length != this.getColumnCount()) {
            TrangChuUI.hienLoi(this.getClass(), new Exception("New data row length does not match the number of columns"));
            return; // Ngừng thực hiện nếu có lỗi về số cột
        }

        // Cập nhật dữ liệu bảng
        for (int i = 0; i < newData.length; i++) {
            for (int j = 0; j < newData[i].length; j++) {
                this.setValueAt(newData[i][j], i, j);
            }
        }

        // Thông báo cho bảng rằng dữ liệu đã thay đổi để bảng có thể vẽ lại
        ((AbstractTableModel) this.getModel()).fireTableDataChanged();
    }
    
    public void clearData() {
        // Tạo một mảng rỗng và cập nhật dữ liệu bảng
        Object[][] emptyData = new Object[0][getColumnCount()]; // Mảng rỗng với số cột hiện tại
        setData(emptyData); // Gọi phương thức setData để cập nhật dữ liệu

        // Thông báo cho bảng rằng dữ liệu đã thay đổi để bảng có thể vẽ lại
        ((AbstractTableModel) this.getModel()).fireTableDataChanged();
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
