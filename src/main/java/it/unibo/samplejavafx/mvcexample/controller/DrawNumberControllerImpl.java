package it.unibo.samplejavafx.mvcexample.controller;

import it.unibo.samplejavafx.mvcexample.model.Configuration;
import it.unibo.samplejavafx.mvcexample.model.DrawNumber;
import it.unibo.samplejavafx.mvcexample.model.DrawNumberImpl;
import it.unibo.samplejavafx.mvcexample.model.DrawResult;
import it.unibo.samplejavafx.mvcexample.view.DrawNumberView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * JavaFX application for the "draw number" game.
 */
public final class DrawNumberControllerImpl implements DrawNumberController {

    private final DrawNumber model;
    private final List<DrawNumberView> views = new ArrayList<>();

    /**
     *
     */
    public DrawNumberControllerImpl() {
        final Configuration.Builder configurationBuilder = new Configuration.Builder();
        final var config = Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("examplemvc/config.yml"));
        try (var contents = new BufferedReader(new InputStreamReader(config, StandardCharsets.UTF_8))) {
            for (var configLine = contents.readLine(); configLine != null; configLine = contents.readLine()) {
                final String[] lineElements = configLine.split(":");
                if (lineElements.length == 2) {
                    final int value = Integer.parseInt(lineElements[1].trim());
                    if (lineElements[0].contains("max")) {
                        configurationBuilder.withMax(value);
                    } else if (lineElements[0].contains("min")) {
                        configurationBuilder.withMin(value);
                    } else if (lineElements[0].contains("attempts")) {
                        configurationBuilder.withMaxAttempts(value);
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
    }

    private void displayError(final String error) {
        views.forEach(view -> view.displayError(error));
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            views.forEach(view -> view.result(result));
        } catch (final IllegalArgumentException e) {
            views.forEach(DrawNumberView::numberIncorrect);
        }
    }

    @Override
    public void registerView(final DrawNumberView view) {
        views.add(view);
        view.start();
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public int getMax() {
        return model.getMax();
    }

    @Override
    public int getMin() {
        return model.getMin();
    }

    @Override
    public int getAttemptsLeft() {
        return model.getAttemptsLeft();
    }
}
