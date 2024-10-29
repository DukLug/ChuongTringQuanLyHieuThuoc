package component;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import application.*;
import component.CustomButton.CustomButtonFunction;
import userInterface.TrangChuUI;
import userInterface.UIStyles;
public class CustomTable extends JTable {
    private CustomTableRowStyle rowStyle;
    private int selectedRow = -1;
    private Object[] columnNames;
    private CustomTableRowStyle headerStyle;
    private int[] columnsWidth;
    private Color selectedColor = Color.LIGHT_GRAY;
    // Constructor
    public CustomTable(Object[][] data, Object[] columnNames, CustomTableRowStyle headerStyle, CustomTableRowStyle rowStyle, int gapBetweenColumns) {
        super(new DefaultTableModel(data, columnNames));
        if(PhanMemQuanLyHieuThuoc.HienLoi) {
            for (Object[] row : data) {
                if (row.length != columnNames.length) {
                	throw new IllegalArgumentException("Data row length does not match the number of column names");
                }
            }
        }
        this.columnNames = columnNames;
        this.headerStyle = headerStyle;
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setRowSelectionAllowed(true); 
        setColumnSelectionAllowed(false); 

        setShowHorizontalLines(true);
        setShowVerticalLines(false);
        setDefaultEditor(Object.class, null);

        setHeader(headerStyle, columnNames.length);

        this.setRowHeight(rowStyle.getHeight());
        this.rowStyle = rowStyle;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickedRow = getSelectedRow();
                if (clickedRow != -1) {
                    selectedRow = clickedRow;
                    
                }
            }
        });
        
    }

    public CustomTable(Object[][] data, Object[] columnNames, CustomTableRowStyle headerStyle, CustomTableRowStyle rowStyle, int gapBetweenColumns, int[] columnsWidth) {
        this(data, columnNames, headerStyle, rowStyle, gapBetweenColumns);
        this.columnsWidth = columnsWidth;
        setColumnsWidth();
    }
    
    public CustomTable(Object[][] data, Object[] columnNames, CustomTableRowStyle headerStyle, CustomTableRowStyle rowStyle, int gapBetweenColumns, int[] columnsWidth, CustomButtonFunction func) {
    	this(data, columnNames, headerStyle, rowStyle, gapBetweenColumns, columnsWidth);
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	int clickedRow = getSelectedRow();
                if (clickedRow != -1) {
                    selectedRow = clickedRow;                 
                }
                func.pressed();
            }
        });
    }
    

    
    public void setData(Object[][] newData) {
        // Kiểm tra nếu dữ liệu mới không khớp với số lượng cột hiện tại
        if (newData.length > 0 && newData[0].length != this.getColumnCount()) {
            throw new IllegalArgumentException("New data row length does not match the number of columns");
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
        Component stamp = super.prepareRenderer(renderer, row, column);
        if (isRowSelected(row)) {
            stamp.setBackground(selectedColor); 
            
        } else {
            stamp.setBackground(rowStyle.getBackgroundColor());
        }
        stamp.setFont(rowStyle.getFont());
        return stamp;
    }

    private void setHeader(CustomTableRowStyle headerStyle, int numOfRows) {
        JTableHeader header = getTableHeader();
        DefaultTableCellRenderer myHeaderRender = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value == null)
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                JLabel label = new JLabel(value.toString());
                label.setFont(headerStyle.getFont());
                return label;
            }
        };
        header.setBackground(headerStyle.getBackgroundColor());
        header.setForeground(headerStyle.getForegroundColor());
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, headerStyle.getHeight()));
        header.setAlignmentY(Component.CENTER_ALIGNMENT);
        for (int i = 0; i < numOfRows; i++) {
            getTableHeader().getColumnModel().getColumn(i).setHeaderRenderer(myHeaderRender);
        }
    }
    
    private void setColumnsWidth() {
    	if(columnsWidth!=null) {
    		TableColumnModel columnModel = this.getColumnModel();
            for (int i = 0; i < columnNames.length; i++) {
                columnModel.getColumn(i).setPreferredWidth(columnsWidth[i]);
                columnModel.getColumn(i).setMaxWidth(columnsWidth[i]);
            }
            this.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
    	}
    }
    
    public void capNhatDuLieu(Object[][] newData) {
    	if(newData.length == 0) {
    		this.setModel(new DefaultTableModel(null, columnNames));
    	}
    	else if(newData[0].length != this.getModel().getColumnCount()) {
    		throw new IllegalArgumentException("Du lieu cap nhat khong hop le");
    	}
    	else {
    		this.setModel(new DefaultTableModel(newData, columnNames));
    	}
    	setHeader(headerStyle, columnNames.length);
    	setColumnsWidth();

    }

    public static class CustomTableRowStyle {
        private Color backgroundColor;
        private Color foregroundColor;
        private Font font;
        private int height;
        private boolean centerAlignment;

        public CustomTableRowStyle(Color backgroundColor, Color foregroundColor, Font font, int height, boolean centerAlignment) {
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