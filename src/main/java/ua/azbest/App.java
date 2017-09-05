package ua.azbest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class App extends Application {
    public void start(Stage primaryStage) throws Exception {
        String fxmlFile = "../../mainfield.fxml";

        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResource(fxmlFile));
        primaryStage.setTitle("Translate");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
