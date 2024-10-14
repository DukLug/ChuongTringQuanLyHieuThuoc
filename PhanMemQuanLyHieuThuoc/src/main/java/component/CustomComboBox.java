package component;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;

import component.CustomButton.ButtonStyle;
import component.CustomButton.CustomButtonFunction;
import component.CustomButton.CustomButtonIconSide;
import userInterfaces.UIStyles;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;

public class CustomComboBox extends JComboBox<String> {
	
    private String title; 
    private ButtonStyle style;
    private ImageIcon icon;
    private CustomButtonIconSide iconSide;
    private int itemHeight;
    private String[] items;
    private CustomButtonFunction[] funcs;
    public CustomComboBox(String title, ButtonStyle style, ImageIcon icon, CustomButtonIconSide iconSide, int itemHeight, String[] items, CustomButtonFunction[] funcs) {
    	super(items);       
        UIManager.put("ComboBox.borderPaintsFocus", Boolean.TRUE);
        this.title = title;
        this.style = style;
        this.icon = icon;
        this.iconSide = iconSide;
        this.itemHeight = itemHeight;
        this.items = items;
        this.funcs = funcs;
    	updateUI();
    	addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedItem = getSelectedItem();
                if (selectedItem != null && !selectedItem.equals(title)) {
                    funcs[getSelectedIndex()].pressed();
                    setSelectedItem(-1);
                }
            }
        });
    }
    public CustomComboBox(String title, ButtonStyle style, int itemHeight, String[] items, CustomButtonFunction[] funcs) {
    	this(title, style, null, null, itemHeight, items, funcs);
    }
	
    @Override
    public void setSelectedItem(Object anObject) {
        super.setSelectedItem(anObject);
    }

    @Override
    public Object getSelectedItem() {
        Object selected = super.getSelectedItem();
        return selected != null ? selected : title;
    }

    @Override public void updateUI() {
    	if(style == null) style = UIStyles.NavBarButtonStyle;
        super.updateUI();
        UIManager.put("ComboBox.squareButton", Boolean.FALSE);
        setUI(new BasicComboBoxUI() {
          @Override 
          protected JButton createArrowButton() {
        	if(icon != null) return new CustomButton(title, style, icon, iconSide, () -> System.out.println());
            return new CustomButton(title, style, () -> System.out.println());
          }
        });
        setBorder(BorderFactory.createEmptyBorder());
        setPreferredSize(new Dimension(style.getPrefWidth(), style.getPrefHeight()));	
        setMaximumSize(new Dimension(style.getPrefWidth(), style.getPrefHeight()));	
		setFont(new Font(getFont().getName(), getFont().getStyle(), style.getFontSize()));		
		setBackground(style.getBasicBackgroundColor());			   
        setBorder(BorderFactory.createEmptyBorder());
        setForeground(style.getTitleColor());
        
        //set popup
        setRenderer(new ListCellRenderer<String>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                JLabel result = new JLabel(value);
                result.setOpaque(true);
                result.setBackground(isSelected ? style.getHoverBackgroundColor() : style.getBasicBackgroundColor()); //---item background color		   
                result.setBorder(BorderFactory.createEmptyBorder());
                result.setForeground(style.getTitleColor());
                result.setHorizontalAlignment(SwingConstants.CENTER);
                result.setFont(new Font(getFont().getName(), getFont().getStyle(), style.getFontSize()));
                result.setPreferredSize(new Dimension(style.getPrefWidth(), itemHeight));	
                result.setMaximumSize(new Dimension(style.getPrefWidth(), itemHeight));	
                return result;
            }
        });
        
        Object child = this.getAccessibleContext().getAccessibleChild(0);
        BasicComboPopup popup = (BasicComboPopup)child;
        popup.setBorder(BorderFactory.createEmptyBorder());
        popup.setFont(new Font(getFont().getName(), getFont().getStyle(), style.getFontSize()));	
      }
}
