package userInterfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import component.CustomTable;

public class NhanVienUI extends JPanel{
	
	public JTextField textField;
	
	public NhanVienUI() {
		super();
		taoHinh();
	}
	
	public void taoHinh() {
		setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
		this.setBackground(Color.white);
		this.add(new JLabel("NhanVienUI"));
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(100, 50));
		
		Object[][] data = {
	            {"1", "john@example.com", "Developer"},
	            {"2", "jane@example.com", "Designer"},
	            {"3", "mike@example.com", "Manager"},
	            {"John Doe", "john@example.com", "Developer"},
	            {"Jane Smith", "jane@example.com", "Designer"},
	            {"Mike Johnsonfffffffffffffffffffffffffffffffff", "mike@example.com", "Manager"},
	            {"John Doe", "john@example.com", "Developer"},
	            {"Jane Smith", "jane@example.com", "Designer"},
	            {"Mike Johnson", "mike@example.com", "Manager"},
	            {"John Doe", "john@example.com", "Developer"},
	            {"Jane Smith", "jane@example.com", "Designer"},
	            {"Mike Johnson", "mike@example.com", "Manager"},
	            {"John Doe", "john@example.com", "Developer"},
	            {"Jane Smith", "jane@example.com", "Designer"},
	            {"Mike Johnson", "mike@example.com", "Manager"},
	            {"John Doe", "john@example.com", "Developer"},
	            {"Jane Smith", "jane@example.com", "Designer"},
	            {"Mike Johnson", "mike@example.com", "Manager"},
	            {"John Doe", "john@example.com", "Developer"},
	            {"Jane Smith", "jane@example.com", "Designer"},
	            {"Mike Johnson", "mike@example.com", "Manager"},
	            {"John Doe", "john@example.com", "Developer"},
	            {"Jane Smith", "jane@example.com", "Designer"},
	            {"Mike Johnson", "mike@example.com", "Manager"},
	            {"John Doe", "john@example.com", "Developer"},
	            {"Jane Smith", "jane@example.com", "Designer"},
	            {"Mike Johnson", "mike@example.com", "Manager"},
	            {"John Doe", "john@example.com", "Developer"},
	            {"Jane Smith", "jane@example.com", "Designer"},
	            {"Mike Johnson", "mike@example.com", "Manager"},
	            {"Emily Davis", "emily@example.com", "Tester"}
	        };

        String[] columnNames = {"Name", "Email", "Role"};
        JTable a = new JTable(data, columnNames);
        // Create custom table
        CustomTable table = new CustomTable(data, columnNames, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle, 20);
       
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000, 800));
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
        add(scrollPane);
        JScrollBar sb = scrollPane.getVerticalScrollBar();
	        
	}
}
