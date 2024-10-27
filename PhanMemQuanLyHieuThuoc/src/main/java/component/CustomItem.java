package component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;

import userInterfaces.TrangChuUI;

public class CustomItem extends JPanel{
	private int itemPrefWidth;
	private int itemPrefHeight;
	private Color backgroundColor;
	private int[] cellsWidth;
	private JComponent[] cells;
	
	
	public CustomItem(int prefWidth, int prefHeight, Color backgroundColor, Border border, int[]  cellsWidth) {
		super();
		this.itemPrefWidth = prefWidth;
		this.itemPrefHeight = prefHeight;
		this.backgroundColor = backgroundColor;
		this.cellsWidth = cellsWidth;
		
		this.setBorder(border);
		this.setPreferredSize(new Dimension(prefWidth, prefHeight));
		this.setMaximumSize(new Dimension(prefWidth, prefHeight));
		this.setMinimumSize(new Dimension(prefWidth, prefHeight));
		this.setBackground(backgroundColor);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			
	}
	
	public void addCells(JComponent[] cells) {
		this.cells = cells;	
		if(cells.length != cellsWidth.length) {
			TrangChuUI.hienLoi(getClass(), new Exception("Columns's length and columnsWidth's length are different"));
		}
		for(int i = 0; i < cells.length; i++) {
			JPanel cell = new JPanel();
			cell.setPreferredSize(new Dimension(cellsWidth[i], itemPrefHeight));
			cell.setMaximumSize(new Dimension(cellsWidth[i], itemPrefHeight));
			cell.setMinimumSize(new Dimension(cellsWidth[i], itemPrefHeight));
			cell.setBackground(backgroundColor);
			cell.setLayout(new BorderLayout());
			cell.add(cells[i]);
			
			add(cell);
		}
	}
	
	public JComponent[] getCells() {
		return cells;
	}
}
