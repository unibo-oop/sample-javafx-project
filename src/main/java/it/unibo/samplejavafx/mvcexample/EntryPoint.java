package it.unibo.samplejavafx.mvcexample;

import it.unibo.samplejavafx.mvcexample.controller.DrawNumberController;
import it.unibo.samplejavafx.mvcexample.controller.DrawNumberControllerImpl;
import it.unibo.samplejavafx.mvcexample.view.JFXDrawNumberView;
import it.unibo.samplejavafx.mvcexample.view.PrintStreamView;
import java.io.FileNotFoundException;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.BoundingBox;
import javafx.stage.Stage;

/**
 * JavaFX application for the "draw number" game.
 */
public final class EntryPoint extends Application {

    private final DrawNumberController controller = new DrawNumberControllerImpl();

    @Override
    public void init() {
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        List.of(
            new JFXDrawNumberView(controller, new BoundingBox(0, 0, 0, 0)),
            new JFXDrawNumberView(controller, null),
            new PrintStreamView(System.out)
        ).forEach(controller::registerView);
        try {
            controller.registerView(new PrintStreamView("output.log"));
        } catch (final FileNotFoundException notFound) {
            // Quick suppression for demo purposes
            System.out.println("Cannot find output file: " + notFound.getMessage()); // NOPMD
        }
    }
}
