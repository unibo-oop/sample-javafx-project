package it.unibo.samplejavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JavaFXAppWithFXML extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/SimpleGui.fxml"));
        Scene scene = new Scene(root, 1000, 800);
        Label lbl = (Label) scene.lookup("#myLabel");
        lbl.setText(".........................");
        primaryStage.setTitle("App FXML");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    static class AnotherStage extends Stage {
        public AnotherStage() {
            super();
            setTitle("New stage created at " + System.currentTimeMillis());
            VBox pane = new VBox();
            pane.getChildren().add(new Label("First label"));
            pane.getChildren().add(new Label("Second label"));
            setScene(new Scene(pane, 100, 500));
        }
    }
}
