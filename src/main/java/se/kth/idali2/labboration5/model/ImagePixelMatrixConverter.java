package se.kth.idali2.labboration5.model;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 * Handels converstion between image format and pixel format
 */
public class ImagePixelMatrixConverter {
    /**
     * Converts image to pixel matrix
     * @param image
     * @return pixel matrix
     */
    public int[][] getPixelMatrix(Image image) {

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        int[][] pixelMatrix = new int[width][height];
        PixelReader reader = image.getPixelReader();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // store alpha, red, green, blue stored in an int
                pixelMatrix[x][y] = reader.getArgb(x, y);
            }
        }
        return pixelMatrix;
    }
/**
 * Converts from pixel matrix to image
 */
    public Image getImage(int[][] pixelMatrix) {
        WritableImage out = new WritableImage(pixelMatrix.length, pixelMatrix[0].length);
        PixelWriter writer = out.getPixelWriter();
        for(int x = 0; x < out.getWidth(); x++){
            for(int y = 0; y < out.getHeight(); y++){
                int a = pixelMatrix[x][y];
                int r = pixelMatrix[x][y];
                int g = pixelMatrix[x][y];
                int b = pixelMatrix[x][y];
                int color = (a << 24) | (r << 16) | (g << 8) | b;
                writer.setArgb(x, y, color);
            }
        }
        return out;
    }
}
