package it.unibo.samplejavafx;

import javafx.application.Application;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Sample JavaFX application for playing with bounds.
 */
public final class PlayingWithBounds extends Application {

    @Override
    public void start(final Stage primaryStage) {
        final VBox vbox = new VBox();
        final TextField stageWidth = new TextField();
        final TextField stageHeight = new TextField();
        final TextField sceneWidth = new TextField();
        final TextField sceneHeight = new TextField();
        final TextField rootWidth = new TextField();
        final TextField rootHeight = new TextField();
        final TextField nodeWidth = new TextField();
        final TextField nodeHeight = new TextField();
        final Button go = new Button("Go!");
        vbox.getChildren().addAll(
            new HBox(new Label("Stage width/height: "), stageWidth, stageHeight),
            new HBox(new Label("Scene width/height: "), sceneWidth, sceneHeight),
            new HBox(new Label("Root width/height: "), rootWidth, rootHeight),
            new HBox(new Label("Node width/height: "), nodeWidth, nodeHeight),
            go
        );
        go.setOnAction(e -> {
            final Stage stage = new Stage();
            if (!stageWidth.getText().isBlank()) {
                stage.setTitle("W=" + stageWidth.getText());
                stage.setWidth(Double.parseDouble(stageWidth.getText()));
            }
            if (!stageHeight.getText().isBlank()) {
                stage.setTitle(stage.getTitle() + "; H=" + stageHeight.getText());
                stage.setHeight(Double.parseDouble(stageHeight.getText()));
            }
            final VBox rootNode = new VBox();
            final double borderWidth = 5;
            rootNode.setBorder(
                new Border(
                    new BorderStroke(
                        Color.RED,
                        BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY,
                        new BorderWidths(borderWidth)
                    )
                )
            );
            final Label someLabel0 = new Label();
            final Label someLabel1 = new Label("Label 1");
            if (!rootWidth.getText().isBlank()) {
                someLabel1.setText("VBox Pref W: " + rootWidth.getText() + "; ");
                rootNode.setPrefWidth(Double.parseDouble(rootWidth.getText()));
            }
            if (!rootHeight.getText().isBlank()) {
                someLabel1.setText(someLabel1.getText() + "VBox Pref W: " + rootHeight.getText() + ".");
                rootNode.setPrefHeight(Double.parseDouble(rootHeight.getText()));
            }
            final TextField someTextField = new TextField();
            if (!nodeWidth.getText().isBlank()) {
                someTextField.appendText("Pref W: " + nodeWidth.getText() + "; ");
                someTextField.setPrefWidth(Double.parseDouble(nodeWidth.getText()));
            }
            if (!nodeHeight.getText().isBlank()) {
                someTextField.appendText("Pref H: " + nodeWidth.getText() + ".");
                someTextField.setPrefHeight(Double.parseDouble(nodeHeight.getText()));
            }
            final Label someLabel2 = new Label("Label 2");
            rootNode.getChildren().addAll(someLabel0, someLabel2, someLabel1, someTextField);
            final Scene scene;
            if (!sceneWidth.getText().isBlank() && !sceneHeight.getText().isBlank()) {
                someLabel1.setText("Scene W/H: " + sceneWidth.getText() + ", " + sceneHeight.getText());
                scene = new Scene(rootNode, Double.parseDouble(sceneWidth.getText()), Double.parseDouble(sceneHeight.getText()));
            } else {
                scene = new Scene(rootNode);
            }
            someLabel0.textProperty().bind(
                StringBinding.stringExpression(
                    new SimpleStringProperty("Stage X=").concat(
                        stage.xProperty()
                    )
                    .concat(", Y=")
                    .concat(stage.yProperty())
                    .concat(" W=")
                    .concat(stage.widthProperty())
                    .concat(" H=")
                    .concat(stage.heightProperty())
                    .concat("\nScene W=")
                    .concat(scene.widthProperty())
                    .concat(" H=")
                    .concat(scene.heightProperty())
                )
            );
            scene.setFill(Color.BEIGE);
            stage.setScene(scene);
            stage.show();
        });
        primaryStage.setScene(new Scene(vbox));
        primaryStage.setTitle("Playing with bounds");
        primaryStage.show();
    }

    /**
     * Entry point's class.
     */
    public static final class Main {
        private Main() {
            // the constructor will never be called directly.
        }

        /**
         * Program's entry point.
         *
         * @param args ignored
         */
        public static void main(final String... args) {
            launch(PlayingWithBounds.class, args);
        }
    }
}
