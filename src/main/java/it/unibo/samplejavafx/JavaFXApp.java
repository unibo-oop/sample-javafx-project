package it.unibo.samplejavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public final class JavaFXApp extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        final Label message = new Label("Hello, JavaFX!"); 
        message.setFont(new Font(100));
        primaryStage.setScene(new Scene(message));
        primaryStage.setTitle("Hello");
        primaryStage.show();
    }

    public static void run(final String... args) {
        launch();
    }

    
    public static final class Main {
        private Main() {
            // the constructor will never be called directly.
        }
        
        public static void main(String... args) {
            Application.launch(JavaFXApp.class, args);
            // The following line raises: Error: class it.unibo.samplejavafx.JavaFXApp$Main is not a subclass of javafx.application.Application
        	// JavaFXApp.launch(args);
            // Whereas the following would do just fine:
            // JavaFXApp.run(args)
        }
    }
}
