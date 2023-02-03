package se.kth.idali2.labboration5.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class gets all processes
 */
public class MainModel {
    private GrayScaleProcess grayScaleProcess;
    private NegativeProcess negativeProcess;
    private WindowLevelProcess windowLevelProcess;

    public MainModel() {
        this.grayScaleProcess = new GrayScaleProcess();
        this.negativeProcess = new NegativeProcess();
    }

    /**
     * Converts image in pixel format to grey scale
     * @param pixelMatrix
     * @return converted image in pixel format
     */
    public int[][] getGrayScale(int[][] pixelMatrix) {
        int[][] newPixelMatrix = grayScaleProcess.processImage(pixelMatrix);
        return newPixelMatrix;
    }

    /**
     * Converts the image in pixel format to negative
     * @param pixelMatrix
     * @return converted image in pixel format
     */
    public int[][] getNegative(int[][] pixelMatrix)
    {
        int[][] newPixelMatrix = negativeProcess.processImage(pixelMatrix);
        return newPixelMatrix;
    }

    /**
     * Calculates image in pixelformat change when app user changes window and level value
     * @param pixelMatrix
     * @param windowValue
     * @param levelValue
     * @return converted image in pixel matrix format
     */
    public int[][] getWindowLevel(int[][] pixelMatrix,double windowValue,double levelValue)
    {
        windowLevelProcess = new WindowLevelProcess(windowValue, levelValue);
        int[][] newPixelMatrix = windowLevelProcess.processImage(pixelMatrix);
        return newPixelMatrix;
    }

    /**
     * Saves image to file
     * @param image
     * @throws IOException
     */
    public void saveToFile(BufferedImage image) throws IOException{
        try {
            ImageIO.write(image, "png", new File("test1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}