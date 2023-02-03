package se.kth.idali2.labboration5.model;

/**
 * This class calculates the alph, red, green and blue values so that the image will appear in grey scale.
 * Source https://dyclassroom.com/image-processing-project/how-to-convert-a-color-image-into-grayscale-image-in-java
 */
public class GrayScaleProcess implements ImageProcess{
    /**
     * Converts image in pixel format to grey scale
     * @param originalImage
     * @return converted image in pixel format
     */
    @Override
    public int[][] processImage(int[][] originalImage) {

        int width = originalImage.length;
        int height = originalImage[0].length;
        int[][] newPixelMatrix = new int[width][height];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                int color = originalImage[x][y];
                int alpha = (color >> 24) & 0xFF000000;
                int red = (color >> 16) & 0xFF;
                int green = (color >> 8) & 0xFF;
                int blue = color & 0xFF;
                int gray = (red+green+blue) / 3;

                newPixelMatrix[x][y] = (alpha << 24) | (gray << 16) | (gray << 8) | gray;
            }
        }
        return newPixelMatrix;
    }
}
