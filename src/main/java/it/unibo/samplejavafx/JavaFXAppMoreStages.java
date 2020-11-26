package it.unibo.samplejavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JavaFXAppMoreStages extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button button = new Button("Create a new stage!"); 
        button.setFont(new Font(100));
        button.setOnMouseClicked(mouseEvent -> {
            new AnotherStage().show();
        });
        primaryStage.setScene(new Scene(button));
        primaryStage.setTitle("Hello");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
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
