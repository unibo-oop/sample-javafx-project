package it.unibo.samplejavafx.mvcexample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Objects;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.BoundingBox;
import javafx.stage.Stage;

/**
 * JavaFX application for the "draw number" game.
 */
public final class DrawNumberFXApplication extends Application implements DrawNumberViewObserver {

    private DrawNumberObservable model;
    private List<DrawNumberView> views;

    @Override
    @SuppressWarnings("PMD.SystemPrintln")
    public void init() {
        final Parameters params = getParameters();
        final String configFile = params.getRaw().stream().findFirst().orElseGet(() -> "examplemvc/config.yml");

        final Configuration.Builder configurationBuilder = new Configuration.Builder();
        final var config = Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(configFile));
        try (var contents = new BufferedReader(new InputStreamReader(config, StandardCharsets.UTF_8))) {
            for (var configLine = contents.readLine(); configLine != null; configLine = contents.readLine()) {
                final String[] lineElements = configLine.split(":");
                if (lineElements.length == 2) {
                    final int value = Integer.parseInt(lineElements[1].trim());
                    if (lineElements[0].contains("max")) {
                        configurationBuilder.setMax(value);
                    } else if (lineElements[0].contains("min")) {
                        configurationBuilder.setMin(value);
                    } else if (lineElements[0].contains("attempts")) {
                        configurationBuilder.setAttempts(value);
                    }
                } else {
                    displayError("I cannot understand \"" + configLine + '"');
                }
            }
        } catch (IOException | NumberFormatException e) {
            displayError(e.getMessage());
        }
        final Configuration configuration = configurationBuilder.build();
        if (configuration.isConsistent()) {
            this.model = new DrawNumberImpl(configuration);
        } else {
            displayError("Inconsistent configuration: "
                + "min: " + configuration.getMin() + ", "
                + "max: " + configuration.getMax() + ", "
                + "attempts: " + configuration.getAttempts() + ". Using defaults instead.");
            this.model = new DrawNumberImpl(new Configuration.Builder().build());
        }
        views = new ArrayList<>();
        views.addAll(Arrays.asList(
            new DrawNumberViewImpl(model, new BoundingBox(0, 0, 0, 0)),
            new DrawNumberViewImpl(model, null),
            new PrintStreamView(System.out))
        );
        try {
            views.add(new PrintStreamView("output.log"));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Cannot find output file: " + fnfe.getMessage());
        }
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        for (final DrawNumberView view : views) {
            view.setObserver(this);
            view.start();
        }
    }

    private void displayError(final String err) {
        for (final DrawNumberView view : views) {
            view.displayError(err);
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view : views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view : views) {
                view.numberIncorrect();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        Platform.exit();
    }
}
