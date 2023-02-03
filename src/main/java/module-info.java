module se.kth.idali2.labboration5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.swing;


    opens se.kth.idali2.labboration5 to javafx.fxml;
    //exports se.kth.idali2.labboration5;
    exports se.kth.idali2.labboration5.view;
    opens se.kth.idali2.labboration5.view to javafx.fxml;
    exports se.kth.idali2.labboration5;
}