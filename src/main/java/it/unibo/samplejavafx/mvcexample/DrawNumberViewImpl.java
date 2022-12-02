package it.unibo.samplejavafx.mvcexample;

import java.util.concurrent.CompletableFuture;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
@SuppressFBWarnings()
public final class DrawNumberViewImpl implements DrawNumberView {

    private static final String FRAME_NAME = "Draw Number App";
    private static final String QUIT = "Quit";
    private static final String RESET = "Reset";
    private static final String GO = "Go";
    private static final String NEW_GAME = ": a new game starts!";

    private DrawNumberViewObserver observer;
    private Stage frame;
    private Label message;
    private final DrawNumberObservable model;
    private final Bounds initialBounds;

    /**
     * Initialises a view implementation for a drawnumber game.
     * @param model
     * @param initialBounds
     */
    public DrawNumberViewImpl(final DrawNumberObservable model, final Bounds initialBounds) {
        this.model = model;
        this.initialBounds = initialBounds;
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
        final TextField tNumber = new TextField();
        final Button bGo = new Button(GO);
        message = new Label();
        playControlsLayout.getChildren().addAll(tNumber, bGo, message);

        final HBox gameControlsLayout = new HBox();
        final Button bReset = new Button(RESET);
        final Button bQuit = new Button(QUIT);
        gameControlsLayout.getChildren().addAll(bReset, bQuit);

        final Label stateMessage = new Label();
        stateMessage.textProperty().bind(new SimpleStringProperty("Min=")
            .concat(model.minProperty())
            .concat("; Max=")
            .concat(model.maxProperty())
            .concat("\nMaxAttempts=")
            .concat(model.attemptsProperty())
            .concat("; Remaining attempts=")
            .concat(model.remainingAttemptsProperty())
            .concat("\nLast guess:")
            .concat(model.lastGuessProperty())
            .concat("; Last outcome:")
            .concat(model.lastGuessResult())
        );

        vbox.getChildren().addAll(playControlsLayout, gameControlsLayout, stateMessage);

        bGo.setOnAction(e -> {
            try {
                observer.newAttempt(Integer.parseInt(tNumber.getText()));
            } catch (NumberFormatException exception) {
                MessageDialog.showMessageDialog(frame, "Validation error",
                        "You entered " + tNumber.getText() + ". Provide an integer please...");
            }
        });
        bQuit.setOnAction(e -> {
            final CompletableFuture<Boolean> f = new CompletableFuture<>();
            f.thenAccept(b -> {
                if (b) {
                    observer.quit();
                }
            });
            MessageDialog.showConfirmDialog(frame, "Confirmation needed", "Confirm quitting?", f);
        });
        bReset.setOnAction(e -> {
            final CompletableFuture<Boolean> f = new CompletableFuture<>();
            f.thenAccept(b -> {
                if (b) {
                    observer.resetGame();
                }
            });
            MessageDialog.showConfirmDialog(frame, "Confirmation needed", "Confirm resetting?", f);
        });

        final int sceneWidth = 600;
        final int sceneHeight = 200;
        final Scene scene = new Scene(vbox, sceneWidth, sceneHeight);

        this.frame.setScene(scene);

        this.frame.show();
    }

    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
        this.observer = observer;
    }

    @Override
    public void numberIncorrect() {
        displayError("Incorrect Number... try again");
    }

    @Override
    public void result(final DrawResult res) {
        switch (res) {
            case YOURS_HIGH:
                plainMessage(res.getDescription());
                return;
            case YOURS_LOW:
                plainMessage(res.getDescription());
                return;
            case YOU_WON:
                plainMessage(res.getDescription() + NEW_GAME);
                break;
            case YOU_LOST:
                // JOptionPane.showMessageDialog(frame, res.getDescription() + NEW_GAME, "Lost",
                // JOptionPane.WARNING_MESSAGE);
                plainMessage(res.getDescription() + NEW_GAME);
                break;
            default:
                throw new IllegalStateException("Unexpected result: " + res);
        }
        observer.resetGame();
    }

    private void plainMessage(final String msg) {
        // MessageDialog.showMessageDialog(frame, title, msg);
        message.setText(msg);
    }

    @Override
    public void displayError(final String msg) {
        // MessageDialog.showMessageDialog(frame, "Error", msg);
        message.setText(msg);
    }
}
