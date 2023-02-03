package se.kth.idali2.labboration5.model;

public class NegativeProcess implements ImageProcess{
    /**
     * Calculates the alpha, red, green and blue values so that the image will appear in negative
     * Source https://dyclassroom.com/image-processing-project/how-to-convert-a-color-image-into-negative
     */
    public NegativeProcess() {
    }

    /**
     * Converts the image in pixel format to negative
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
                int alpha = (color >> 24) & 0xFF;
                int red = (color >> 16) & 0xFF;
                int green = (color >> 8) & 0xFF;
                int blue = color & 0xFF;

                red = 255 - red;
                green = 255 - green;
                blue = 255 - blue;

                newPixelMatrix[x][y] = (alpha << 24) | (red << 16) | (green << 8) | blue;
            }
        }
        return newPixelMatrix;
    }
}
