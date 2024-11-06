package functionalClass;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageLoader {
	
	public static  BufferedImage taiHinhAnh(String hinhAnhID) {
		String path = "data/HinhAnh/" + hinhAnhID +".png";
		// Check if the file exists
        File file = new File(path);
        if (!file.exists()) {
            System.err.println("Error: File not found at path: " + path);
            return null;
        }

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            if (bufferedImage == null) {
                System.err.println("Error: Unable to read image file at path: " + path);
                return taiHinhAnhDuPhong();
            }

            return bufferedImage;
        } catch (IOException e) {
            e.printStackTrace();
            return taiHinhAnhDuPhong();
        }
	} 
	
	public static  BufferedImage taiHinhAnhDuPhong() {
		String path = "data/HinhAnh/" + "image-not-found" +".png";
		// Check if the file exists
        File file = new File(path);
        if (!file.exists()) {
            System.err.println("Error: File not found at path: " + path);
            return null;
        }

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            return bufferedImage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
	} 
	
	
}
