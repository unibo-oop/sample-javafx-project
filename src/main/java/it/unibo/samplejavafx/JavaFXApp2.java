package it.unibo.samplejavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JavaFXApp2 extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final Label message = new Label("Hello again JavaFX!"); 
        message.setFont(new Font(100));
        primaryStage.setScene(new Scene(message));
        primaryStage.setTitle("JavaFXApp2");
        primaryStage.show();
    }

    public static void run(final String... args) {
        launch();
    }

    public static final class Main {
        private Main() {
            // the constructor will never be called directly.
        }

        public static void main(final String... args) {
            JavaFXApp2.run(args);
        }
    }
}
