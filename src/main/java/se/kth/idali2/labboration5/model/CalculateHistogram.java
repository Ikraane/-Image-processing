package se.kth.idali2.labboration5.model;

import javafx.scene.chart.XYChart;

/**
 * This class calculates the scale of each color and does the math behind the histogram
 * Source http://java-buddy.blogspot.com/2015/07/display-images-histogram-on-javafx.html
 */
public class CalculateHistogram {

    private long alpha[] = new long[256];
    private long red[] = new long[256];
    private long green[] = new long[256];
    private long blue[] = new long[256];

    XYChart.Series chartAlpha;
    XYChart.Series chartRed;
    XYChart.Series chartGreen;
    XYChart.Series chartBlue;

    private boolean success;

    /**
     * Calculates histogram
     * @param originalImage originalImage in pixelMatrix format
     */
    public CalculateHistogram(int[][] originalImage) {
        success = false;
        int width = originalImage.length;
        int height = originalImage[0].length;

        for(int i = 0; i < 256; i++){
            alpha[i] = red[i] = green[i] = blue[i] = 0;
        }

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                int color = originalImage[x][y];
                int a = (color >> 24) & 0xFF;
                int r = (color >> 16) & 0xFF;
                int g = (color >> 8) & 0xFF;
                int b = color & 0xFF;

                alpha[a]++;
                red[r]++;
                green[g]++;
                blue[b]++;
            }
        }
        chartAlpha = new XYChart.Series();
        chartRed = new XYChart.Series();
        chartGreen = new XYChart.Series();
        chartBlue = new XYChart.Series();
        chartAlpha.setName("alpha");
        chartRed.setName("red");
        chartGreen.setName("green");
        chartBlue.setName("blue");
        for (int i = 0; i < 256; i++) {
            chartAlpha.getData().add(new XYChart.Data(String.valueOf(i), alpha[i]));
            chartRed.getData().add(new XYChart.Data(String.valueOf(i), red[i]));
            chartGreen.getData().add(new XYChart.Data(String.valueOf(i), green[i]));
            chartBlue.getData().add(new XYChart.Data(String.valueOf(i), blue[i]));
        }

        success = true;

    }

    /**
     * Checks if histogram is calculated
     * @return true or false
     */
    public boolean isSuccess(){
        return success;
    }

    /**
     * Gets chart values of alpha color
     * @return Alpha color
     */
    public XYChart.Series getSeriesAlpha() {
        return chartAlpha;
    }

    /**
     * Gets chart values of red color
     * @return
     */
    public XYChart.Series getSeriesRed() {
        return chartRed;
    }

    /**
     * Gets chart values of green color
     * @return
     */
    public XYChart.Series getSeriesGreen() {
        return chartGreen;
    }

    /**
     * Gets chart values of blue color
     * @return
     */
    public XYChart.Series getSeriesBlue() {
        return chartBlue;
    }
}
