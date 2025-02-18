package it.unibo.samplejavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Sample JavaFX application with GUI declared in FXML file.
 */
public class JavaFXAppWithFXML extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/SimpleGui.fxml"));
        final Scene scene = new Scene(root, 1000, 800);
        final Label label = (Label) scene.lookup("#myLabel");
        label.setText(".........................");
        primaryStage.setTitle("App FXML");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void run(final String[] args) {
        launch(args);
    }

    /**
     * Entry point class.
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
            run(args);
        }
    }
}
