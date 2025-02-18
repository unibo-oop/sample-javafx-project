package it.unibo.samplejavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Sample JavaFX application.
 */
public final class JavaFXApp extends Application {

    @Override
    public void start(final Stage primaryStage) {
        final Label message = new Label("Hello, JavaFX!");
        message.setFont(new Font(100));
        primaryStage.setScene(new Scene(message));
        primaryStage.setTitle("Hello");
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
            launch(JavaFXApp.class, args);
            /*
            The following line raises: Error: class it.unibo.samplejavafx.JavaFXApp$Main
            is not a subclass of javafx.application.Application
            Because if you do not provide the Application subclass to launch() it will consider the enclosing class)
            */
            // JavaFXApp.launch(args);
            // Whereas the following would do just fine:
            // JavaFXApp.run(args)
        }
    }
}
