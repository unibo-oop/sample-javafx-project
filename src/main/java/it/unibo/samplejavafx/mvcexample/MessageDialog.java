package it.unibo.samplejavafx.mvcexample;

import java.util.concurrent.CompletableFuture;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MessageDialog {

    public static void showMessageDialog(Stage owner, String title, String text) {
        Stage s = new Stage();
        s.initOwner(owner);
        s.initModality(Modality.APPLICATION_MODAL);

        Label label = new Label(text);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> s.close());

        VBox root = new VBox();
        root.getChildren().addAll(label, closeButton);
        Scene scene = new Scene(root, 200, 100);
        s.setScene(scene);
        s.setTitle(title);
        s.show();
    }

    public static CompletableFuture<Boolean> showConfirmDialog(Stage owner, String title, String text, CompletableFuture<Boolean> future) {
        Stage s = new Stage();
        s.initOwner(owner);
        s.initModality(Modality.APPLICATION_MODAL);

        Label label = new Label(text);
        Button confirmButton = new Button("Confirm");
        Button closeButton = new Button("Close");
        confirmButton.setOnAction(e -> {
            future.complete(true);
            s.close();
        });
        closeButton.setOnAction(e -> {
            future.complete(false);
            s.close();
        });

        VBox root = new VBox();
        root.getChildren().addAll(label, confirmButton, closeButton);
        Scene scene = new Scene(root, 200, 100);
        s.setScene(scene);
        s.setTitle(title);
        s.show();

        return future;
    }
}
