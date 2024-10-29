package userInterface;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import component.CustomButton;
import component.CustomTable;
import component.CustomButton.CustomButtonIconSide;

public class TongQuanUI extends JPanel{
	private CustomTable customTable;
	Object[][] data = {
            {"1", "john@example.com", ""},
            {"2", "jane@example.com", "Designer"},
            {"3", "mike@example.com", "Manager"},
            {"John Doe", "john@example.com", "Developer"},
            {"Jane Smith", "jane@example.com", "Designer"},
            {"Mike Johnsonffffffffffffffffffffffffffffffffffffffffffffffffffffddddddddddddddddffffff", "mike@example.com", "Manager"},
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

	Object[][] data2 = {
            {"1", "john@example.com", ""},

            {"Emily Davis", "emily@example.com", "Tester"}
        };
    String[] columnNames = {"Name", "Email", "Role"};
	public TongQuanUI() {
		super();
		taoHinh();
	}
	

	public void taoHinh() {
		setPreferredSize(new Dimension(UIStyles.ApplicationWidth, UIStyles.MainSectionHeight));
		this.setBackground(Color.white);
		this.add(new JLabel("TongQuanUI"));
		
		
        // Create custom table
        customTable = new CustomTable(data, columnNames, UIStyles.NhanVienTableHeaderStyle, UIStyles.NhanVienTableRowStyle, 20,
        		new int[] {500, 300, 200},
        		()->ClickedMyTable()
        		);
       
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(customTable);
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
        add(new CustomButton("SelectedRow", UIStyles.LabelBarButtonStyle, UIStyles.HelpIcon, CustomButtonIconSide.LEFT, ()->ClickedMyTable()));
	        
	}
	
	private void ClickedMyTable() {
		Object[][] data3 = new Object[][] {};
		customTable.capNhatDuLieu(data2);
		System.out.println(customTable.getSelectedRow());
	}
}
