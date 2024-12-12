package functionalClass;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader {

    private static final String IMAGE_PATH = "data/HinhAnh/";
    private static final String FALLBACK_IMAGE = "image-not-found.png";

    public static BufferedImage taiHinhAnh(String hinhAnhID) {
        String path = IMAGE_PATH + hinhAnhID + ".png";
        BufferedImage bufferedImage = loadImageFromFile(path);

        // If the primary image is not found or cannot be read, load the fallback image
        if (bufferedImage == null) {
            System.err.println("Primary image not found, loading fallback image.");
            bufferedImage = taiHinhAnhDuPhong();
        }
        return bufferedImage;
    }

    public static BufferedImage taiHinhAnhDuPhong() {
        String path = IMAGE_PATH + FALLBACK_IMAGE;
        return loadImageFromFile(path);
    }

    private static BufferedImage loadImageFromFile(String path) {
        File file = new File(path);

        // Check if the file exists
        if (!file.exists()) {
            System.err.println("Error: File not found at path: " + path);
            return null;
        }

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            if (bufferedImage == null) {
                System.err.println("Error: Unable to read image file at path: " + path);
            }
            return bufferedImage;
        } catch (IOException e) {
            System.err.println("Error: IOException while reading image at path: " + path);
            e.printStackTrace();
            return null;
        }
    }
}
