package se.kth.idali2.labboration5.view;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class AppView extends BorderPane {

    private MenuBar menuBar;
    private Controller controller;
    private ImageView imageView;
    private Image image;
    private StackPane stackPane;
    private GridPane gridPane;
    private Slider windowSlider;
    private Slider levelSlider;
    private DecimalFormat df;
    private double windowValue, levelValue;
    private Alert alert;
    private FileChooser fileChooser;
    private File imageFile;


    public AppView(Stage stage){
        super();

        controller = new Controller(this, stage);
        createMenuBar();

    }


    private void createMenuBar() {
        Menu fileMenu = new Menu("File");
        MenuItem openItem = new MenuItem("Open");
        EventHandler<ActionEvent> openHandler = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    controller.handleOpenFile(); // save data and exit
                } catch (IOException e) {
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Could not open file ");
                    alert.showAndWait();
                }
            }
        };
        openItem.addEventHandler(ActionEvent.ACTION, openHandler);
        fileMenu.getItems().add(openItem);

        MenuItem exitItem = new MenuItem("Exit");
        EventHandler<ActionEvent> exitHandler = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    controller.handleSaveImage(image);
                    Platform.exit();// save data and exit
                } catch (IOException e) {
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Could not save to file ");
                    alert.showAndWait();
                }

            }
        };
        exitItem.addEventHandler(ActionEvent.ACTION, exitHandler);
        fileMenu.getItems().add(exitItem);

        Menu generateMenu = new Menu("Generate");
        MenuItem grayscaleItem = new MenuItem("Grayscale");
        EventHandler<ActionEvent> grayscaleHandler = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (image == null) throw new IllegalStateException("Cannot enter process without image!");{
                    controller.handleGrayscale(image);
                    showLable(grayscaleItem.getText());
                }

            }
        };
        grayscaleItem.addEventHandler(ActionEvent.ACTION, grayscaleHandler);
        generateMenu.getItems().add(grayscaleItem);

        MenuItem negativeItem = new MenuItem("Negative");
        EventHandler<ActionEvent> negativeHandler = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (image == null) throw new IllegalStateException("Cannot enter process without image!");{
                    controller.handleNegative(image);
                    showLable(negativeItem.getText());
                }
            }
        };
        negativeItem.addEventHandler(ActionEvent.ACTION, negativeHandler);
        generateMenu.getItems().add(negativeItem);

        MenuItem histogramItem = new MenuItem("Histogram");
        EventHandler<ActionEvent> histogramHandler = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (image == null)throw new IllegalStateException("Cannot enter process without image!");{
                    controller.handleHistogram(image);
                }
            }
        };
        histogramItem.addEventHandler(ActionEvent.ACTION, histogramHandler);
        generateMenu.getItems().add(histogramItem);

        MenuItem windowLevelItem = new MenuItem("Window/Level");
        EventHandler<ActionEvent> windowLevelHandler = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (image == null) throw new IllegalStateException("Cannot enter process without image!");{
                    showWindowLevel(image);
                }
            }
        };
        windowLevelItem.addEventHandler(ActionEvent.ACTION, windowLevelHandler);
        generateMenu.getItems().add(windowLevelItem);

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, generateMenu);

    }

    private void showLable(String text) {
        Label labelText = new Label(text);
        labelText.setMinWidth(20);
        labelText.setMinHeight(20);
        labelText.setPadding(new Insets(5, 1, 1, 1));
        this.setBottom(labelText);
    }


    public MenuBar getMenuBar() {
        return this.menuBar;
    }

    public Image openFile(Stage stage){

        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(
                "Image files", "*.png", ".jpg", "*.bmp");
        fileChooser.getExtensionFilters().add(filter);

        imageFile = fileChooser.showOpenDialog(stage);

         image = new Image(imageFile.toURI().toString());

         return image;
    }

    public void showFile(Image openFile) {

        this.image = openFile;
        imageView = new ImageView();
        imageView.setImage(openFile);
        imageView.setFitHeight(500);
        imageView.setFitWidth(450);
        stackPane = new StackPane();
        stackPane.getChildren().add(imageView);
        stackPane.setPadding(new Insets(10, 10, 10, 10));
        StackPane.setAlignment(imageView, Pos.CENTER_RIGHT );
        this.setRight(stackPane);


    }

    public void showImage(Image newImage) {
        stackPane.getChildren().clear();
        image = newImage;
        imageView = new ImageView();
        imageView.setImage(newImage);
        imageView.setFitHeight(500);
        imageView.setFitWidth(450);
        stackPane.getChildren().add(imageView);
    }

    public void showHistogram(Image image, LineChart<String, Number> chartHistogram) {
        imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(500);
        imageView.setFitWidth(450);
        stackPane.getChildren().addAll(imageView, chartHistogram);
        stackPane.setPadding(new Insets(10, 10, 10, 10));
        Label histogram = new Label("Histogram");


        StackPane.setAlignment(imageView, Pos.CENTER_RIGHT );
        StackPane.setAlignment(chartHistogram, Pos.CENTER_LEFT);
        StackPane.setAlignment(histogram, Pos.BOTTOM_LEFT);
        this.setRight(imageView);
        this.setLeft(chartHistogram);
        this.setBottom(histogram);
    }

    public void showWindowLevel(Image newImage) {
        windowValue = 0;
        levelValue = 0;
        df = new DecimalFormat("#.00");
        windowSlider = new Slider(100, 255, 0);
        levelSlider = new Slider(0, 1, 0);

        windowSlider.setMajorTickUnit(64);
        windowSlider.setShowTickMarks(true);
        windowSlider.setShowTickLabels(true);
        levelSlider.setMajorTickUnit(0.01);
        levelSlider.setShowTickMarks(true);
        levelSlider.setShowTickLabels(true);


        gridPane = new GridPane();
        imageView = new ImageView();
        imageView.setImage(newImage);
        imageView.setFitHeight(500);
        imageView.setFitWidth(450);

        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label windowLabel = new Label("Window: ");
        Label levelLabel = new Label("Level: ");
        Label windowLabel2 = new Label("Window: " + 0);
        Label levelLabel2 = new Label("Level: " + 0);

        gridPane.setPadding(new Insets(1, 1, 3, 1));
        gridPane.add(windowLabel, 1, 2);
        gridPane.add(levelLabel, 1, 5);
        gridPane.add(windowSlider, 1, 3);
        gridPane.add(levelSlider, 1, 6);
        gridPane.add(windowLabel2, 1, 30);
        gridPane.add(levelLabel2, 10, 30);

        windowSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                windowLabel2.setText("");
                windowLabel2.setText("Window: " +  df.format(newValue));
                windowValue = (double) newValue;

                controller.handleWindowLevel(newImage, windowValue, levelValue);
            }
        });

        levelSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                levelLabel2.setText("");
                levelLabel2.setText("Level: " + df.format(newValue));
                levelValue = (double) newValue;

                controller.handleWindowLevel(newImage, windowValue, levelValue);
            }
        });

        this.setLeft(gridPane);

    }
}
