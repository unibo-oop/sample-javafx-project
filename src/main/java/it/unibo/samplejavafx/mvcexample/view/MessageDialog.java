package it.unibo.samplejavafx.mvcexample.view;

import java.util.concurrent.CompletableFuture;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Utility classes for message dialogs.
 */
public final class MessageDialog {
    private MessageDialog() {
    }

    /**
     * Shows a message dialog.
     *
     * @param owner the owner stage
     * @param title the title
     * @param text the text
     */
    public static void showMessageDialog(final Stage owner, final String title, final String text) {
        final Stage s = new Stage();
        s.initOwner(owner);
        s.initModality(Modality.APPLICATION_MODAL);

        final Label label = new Label(text);
        final Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> s.close());

        final VBox root = new VBox();
        root.getChildren().addAll(label, closeButton);
        final Scene scene = new Scene(root);
        s.setScene(scene);
        s.setTitle(title);
        s.show();
    }

    /**
     * Show a confirmation dialog.
     *
     * @param owner the owner stage
     * @param title the title
     * @param text the text
     * @param future the future to complete with the confirmation
     * @return a completable future for the confirmation
     */
    public static CompletableFuture<Boolean> showConfirmDialog(
        final Stage owner,
        final String title,
        final String text,
        final CompletableFuture<Boolean> future
    ) {
        final Stage s = new Stage();
        s.initOwner(owner);
        s.initModality(Modality.APPLICATION_MODAL);

        final Label label = new Label(text);
        final Button confirmButton = new Button("Confirm");
        final Button closeButton = new Button("Close");
        confirmButton.setOnAction(e -> {
            future.complete(true);
            s.close();
        });
        closeButton.setOnAction(e -> {
            future.complete(false);
            s.close();
        });

        final VBox root = new VBox();
        root.getChildren().addAll(label, confirmButton, closeButton);
        final Scene scene = new Scene(root);
        s.setScene(scene);
        s.setTitle(title);
        s.show();

        return future;
    }
}
