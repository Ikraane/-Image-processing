package se.kth.idali2.labboration5.model;

/**
 * This class is the calculation when app user manipulates the picture in Window/Level mode
 * Source https://stackoverflow.com/questions/47472759/how-to-change-the-contrast-and-brightness-of-colored-an-image-stored-as-a-2-dime
 */
public class WindowLevelProcess implements ImageProcess{
    private double window;
    private double level;

    public WindowLevelProcess(double window, double level) {
        this.window = window;
        this.level = level;
    }

    /**
     * Calculates image in pixelformat change when app user changes window and level value
     * @param originalImage
     * @return converted image in pixel matrix format
     */
    @Override
    public int[][] processImage(int[][] originalImage) {
        int[][] windowPixelMatrix = new int[originalImage.length][originalImage[0].length];
        for(int x = 0; x < originalImage.length; x++){
            for(int y = 0; y < originalImage[0].length; y++){
                int color = originalImage[x][y];
                int alpha = (int) (level * (color >> 24) + window);
                int red = (int) (level * (color >> 16) + window);
                int green = (int) (level * (color >> 8) + window);
                int blue = (int) (level * color + window);
                alpha = checkColorRange(alpha);
                red = checkColorRange(red);
                green = checkColorRange(green);
                blue = checkColorRange(blue);

                windowPixelMatrix[x][y] = (alpha << 24) | (red << 16) | (green << 8) | blue;
            }
        }
        return windowPixelMatrix;
    }

    /**
     * checks color range of alpha, red, green and blue and keeps it between 0 - 255
     * @param newColor
     * @return newColor (changed to 0 if under and 255 if over)
     */
    public int checkColorRange(int newColor){
        if(newColor > 255){
            newColor = 255;
        } else if(newColor < 0){
            newColor = 0;
        }
        return newColor;
    }
}
