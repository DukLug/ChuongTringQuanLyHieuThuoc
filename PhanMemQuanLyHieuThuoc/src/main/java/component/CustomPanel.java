package component;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class CustomPanel extends JPanel {

    private int cornerRadius;
    private int shadowSize;

    public CustomPanel(int radius, int shadowSize) {
        // Đặt JPanel trong suốt và lưu bán kính bo góc
        setOpaque(false);
        this.cornerRadius = radius;
        this.shadowSize = shadowSize;
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Bật chế độ khử răng cưa để vẽ mịn hơn
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Đặt màu bóng (đen, với độ trong suốt)
        g2.setColor(new Color(0, 0, 0, 30));

        // Vẽ bóng với kích thước xác định
        g2.fillRoundRect(shadowSize, shadowSize, getWidth() - shadowSize, getHeight() - shadowSize, cornerRadius, cornerRadius);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - shadowSize, getHeight() - shadowSize, cornerRadius, cornerRadius);
    }


    
}
