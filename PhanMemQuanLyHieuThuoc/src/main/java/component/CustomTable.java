package component;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

import userInterfaces.TrangChuUI;
import userInterfaces.UIStyles;

import java.awt.*;
import java.awt.event.*;
import application.*;
import component.CustomButton.CustomButtonFunction;
public class CustomTable extends JTable {
    private CustomTableRowStyle rowStyle;
    private int selectedRow = -1;

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

        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setRowSelectionAllowed(true); 
        setColumnSelectionAllowed(false); 

        setShowHorizontalLines(true);
        setShowVerticalLines(false);

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
        TableColumnModel columnModel = this.getColumnModel();
        for (int i = 0; i < columnNames.length; i++) {
            columnModel.getColumn(i).setPreferredWidth(columnsWidth[i]);
            columnModel.getColumn(i).setMaxWidth(columnsWidth[i]);
        }
        this.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
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

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component stamp = super.prepareRenderer(renderer, row, column);
        if (isRowSelected(row)) {
            stamp.setBackground(Color.cyan); 
            
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
