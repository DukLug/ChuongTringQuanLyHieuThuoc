package userInterface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.print.Printable;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class ScreenshotHelper {
    
    // Chụp ảnh thành phần
    public static BufferedImage captureComponent(Component component) {
        int width = component.getWidth();
        int height = component.getHeight();

        // Kiểm tra nếu component là JScrollPane và lấy kích thước của view
        if (component instanceof JScrollPane) {
            JScrollPane scrollPane = (JScrollPane) component;
            Component view = scrollPane.getViewport().getView();
            width = view.getWidth();
            height = view.getHeight();
        }

        // Tạo ảnh BufferedImage với kích thước lấy được
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        component.paint(g2d);
        g2d.dispose();
        
        return image;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JButton button = new JButton("Click Me");
        frame.getContentPane().add(button, BorderLayout.SOUTH);

        JLabel label = new JLabel("Hello, this is a test label.");
        frame.getContentPane().add(label, BorderLayout.CENTER);

        frame.setVisible(true);

        // Đợi frame hiển thị trước khi chụp
        SwingUtilities.invokeLater(() -> {
            BufferedImage capturedImage = captureComponent(frame.getContentPane());
            ImageIcon imageIcon = new ImageIcon(capturedImage);
            JOptionPane.showMessageDialog(null, new JLabel(imageIcon));

            // In ảnh sau khi hiển thị
            printImage(capturedImage);
        });
    }

    // In ảnh
    public static void printImage(BufferedImage image) {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable(new Printable() {
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex != 0) {
                    return Printable.NO_SUCH_PAGE;
                }

                Graphics2D g2d = (Graphics2D) graphics;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                // Điều chỉnh tỷ lệ ảnh để vừa với trang in
                double scale = Math.min(
                        pageFormat.getImageableWidth() / (double) image.getWidth(),
                        pageFormat.getImageableHeight() / (double) image.getHeight()
                );
                g2d.scale(scale, scale);

                g2d.drawImage(image, 0, 0, null);
                return Printable.PAGE_EXISTS;
            }
        });

        try {
            if (printJob.printDialog()) { // Hiển thị hộp thoại cho người dùng chọn máy in
                printJob.print();
            }
        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi in ảnh: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
