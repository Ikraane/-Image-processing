package se.kth.idali2.labboration5.view;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import se.kth.idali2.labboration5.model.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Controller {
    private MainModel mainModel;
    private AppView view;
    private Stage stage;
    private CalculateHistogram calculateHistogram;
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final LineChart<String, Number> chartHistogram = new LineChart<>(xAxis, yAxis);
    private ImagePixelMatrixConverter imagePixelMatrixConverter;


    public Controller(AppView view, Stage stage) {
        this.view = view;
        this.stage = stage;
        this.mainModel = new MainModel();
        this.imagePixelMatrixConverter = new ImagePixelMatrixConverter();
    }

    public void handleOpenFile() throws IOException {
        Image image = view.openFile(stage);
        view.showFile(image);

    }

    public void handleGrayscale(Image image) {
        int[][] newPixelMatrix = mainModel.getGrayScale(imagePixelMatrixConverter.getPixelMatrix(image));
        Image newImage = imagePixelMatrixConverter.getImage(newPixelMatrix);  //ändrad getImage och getPixelMatrix
        view.showImage(newImage);
    }


    public void handleNegative(Image image) {
        int[][] newPixelMatrix = mainModel.getNegative(imagePixelMatrixConverter.getPixelMatrix(image));
        Image newImage = imagePixelMatrixConverter.getImage(newPixelMatrix);  //ändrad getImage och getPixelMatrix
        view.showImage(newImage);
    }

    public void handleHistogram(Image image) {
        int[][] originalPixelMatrix = imagePixelMatrixConverter.getPixelMatrix(image);
        calculateHistogram = new CalculateHistogram(originalPixelMatrix);
        chartHistogram.getData().clear();
        if (calculateHistogram.isSuccess()){
        chartHistogram.getData().addAll(calculateHistogram.getSeriesRed(), calculateHistogram.getSeriesGreen(), calculateHistogram.getSeriesBlue());
        }
        view.showHistogram(image, chartHistogram);
    }


  public void handleWindowLevel(Image image, double windowValue, double levelValue) {
        int[][] newPixelMatrix = mainModel.getWindowLevel(imagePixelMatrixConverter.getPixelMatrix(image), windowValue, levelValue);
        Image newImage = imagePixelMatrixConverter.getImage(newPixelMatrix);
        view.showImage(newImage);

    }

    public void handleSaveImage(Image image) throws IOException {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        mainModel.saveToFile(bufferedImage);
    }
}