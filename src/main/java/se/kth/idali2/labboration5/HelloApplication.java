package se.kth.idali2.labboration5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import se.kth.idali2.labboration5.view.AppView;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        AppView view = new AppView(stage);
        MenuBar menuBar = view.getMenuBar();
        VBox root = new VBox(menuBar, view);

        Scene scene = new Scene(root, 1050, 600);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);


        stage.setTitle("Image Processor");

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}