package it.unibo.samplejavafx.mvcexample.view;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.samplejavafx.mvcexample.controller.DrawNumberController;
import it.unibo.samplejavafx.mvcexample.model.DrawResult;
import java.util.concurrent.CompletableFuture;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Graphical {@link DrawNumberView} implementation.
 */
@SuppressFBWarnings
public final class JFXDrawNumberView implements DrawNumberView {

    private static final String FRAME_NAME = "Draw Number App";
    private static final String QUIT = "Quit";
    private static final String RESET = "Reset";
    private static final String GO = "Go";
    private static final String NEW_GAME = ": a new game starts!";

    private final DrawNumberController controller;
    private Stage frame;
    private Label message;
    private final Bounds initialBounds;
    private final IntegerProperty max = new SimpleIntegerProperty();
    private final IntegerProperty min = new SimpleIntegerProperty();
    private final IntegerProperty last = new SimpleIntegerProperty();
    private final IntegerProperty remaining = new SimpleIntegerProperty();
    private final Property<DrawResult> lastResult = new SimpleObjectProperty<>();

    /**
     * Initializes a view implementation for a draw number game.
     *
     * @param controller the controller
     * @param initialBounds the initial bounds
     */
    public JFXDrawNumberView(final DrawNumberController controller, final Bounds initialBounds) {
        this.initialBounds = initialBounds;
        this.controller = controller;
        updateState();
    }

    @Override
    public void start() {
        frame = new Stage();
        frame.setTitle(FRAME_NAME);
        if (initialBounds != null) {
            frame.setX(initialBounds.getMinX());
            frame.setY(initialBounds.getMinY());
        }

        final VBox vbox = new VBox();
        final HBox playControlsLayout = new HBox();
        final TextField theNumber = new TextField();
        final Button go = new Button(GO);
        message = new Label();
        playControlsLayout.getChildren().addAll(theNumber, go, message);

        final HBox gameControlsLayout = new HBox();
        final Button bReset = new Button(RESET);
        final Button bQuit = new Button(QUIT);
        gameControlsLayout.getChildren().addAll(bReset, bQuit);

        final Label stateMessage = new Label();
        stateMessage.textProperty()
            .bind(new SimpleStringProperty("Min=")
            .concat(min)
            .concat("; Max=")
            .concat(max)
            .concat("; Remaining attempts=")
            .concat(remaining)
            .concat("\nLast guess:")
            .concat(last)
            .concat("; Last outcome:")
            .concat(lastResult.map(DrawResult::drawResult).map(DrawResult.DrawOutcome::getDescription))
        );

        vbox.getChildren().addAll(playControlsLayout, gameControlsLayout, stateMessage);

        go.setOnAction(e -> {
            try {
                final var attempt = Integer.parseInt(theNumber.getText());
                last.set(attempt);
                controller.newAttempt(attempt);
            } catch (final NumberFormatException exception) {
                MessageDialog.showMessageDialog(frame, "Validation error",
                        "You entered " + theNumber.getText() + ". Provide an integer please...");
            }
        });
        bQuit.setOnAction(e -> {
            final CompletableFuture<Boolean> future = new CompletableFuture<>();
            future.thenAccept(shouldExit -> {
                if (shouldExit) {
                    Platform.exit();
                }
            });
            MessageDialog.showConfirmDialog(
                frame,
                "Confirmation needed",
                "Confirm quitting?",
                future
            );
        });
        bReset.setOnAction(e -> {
            final CompletableFuture<Boolean> future = new CompletableFuture<>();
            future.thenAccept(b -> {
                if (b) {
                    controller.resetGame();
                }
            });
            MessageDialog.showConfirmDialog(frame, "Confirmation needed", "Confirm resetting?", future);
        });
        // GRAPHICS
        final int sceneWidth = 600;
        final int sceneHeight = 200;
        final Scene scene = new Scene(vbox, sceneWidth, sceneHeight);
        this.frame.setScene(scene);
        this.frame.show();
    }

    @Override
    public void numberIncorrect() {
        displayError("Incorrect Number... try again");
    }

    @Override
    public void result(final DrawResult result) {
        last.setValue(result.attempted());
        lastResult.setValue(result);
        updateState();
        switch (result.drawResult()) {
            case YOURS_HIGH, YOURS_LOW:
                plainMessage(result.drawResult().getDescription());
                return;
            case YOU_WON, YOU_LOST:
                plainMessage(result.drawResult().getDescription() + NEW_GAME);
                break;
        }
        controller.resetGame();
        updateState();
    }

    @Override
    public void displayError(final String msg) {
        // MessageDialog.showMessageDialog(frame, "Error", msg);
        message.setText(msg);
    }

    private void plainMessage(final String msg) {
        // MessageDialog.showMessageDialog(frame, title, msg);
        message.setText(msg);
    }

    private void updateState() {
        max.set(controller.getMax());
        min.set(controller.getMin());
        remaining.set(controller.getAttemptsLeft());
    }
}
