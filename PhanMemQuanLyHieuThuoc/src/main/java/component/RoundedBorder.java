package component;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.border.Border;

public class RoundedBorder implements Border {
    private final Color color;
    private final int thickness;
    private final int radius;

    public RoundedBorder(Color color, int thickness, int radius) {
        this.color = color;
        this.thickness = thickness;
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Set color and thickness
        g2.setColor(color);
        g2.setStroke(new BasicStroke(thickness));
        
        // Draw rounded rectangle
        g2.drawRoundRect(x + thickness / 2, y + thickness / 2,
                         width - thickness, height - thickness,
                         radius, radius);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(radius + thickness, radius + thickness, radius + thickness, radius + thickness);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}
