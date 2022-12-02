package it.unibo.samplejavafx.mvcexample;

import java.io.FileNotFoundException;
import javafx.application.Application;

/**
 * This application is a porting of the Swing MVC application from OOP course at UNIBO.
 */
public final class DrawNumberApp {
    private DrawNumberApp() {

    }

    /**
     * @param args
     *            ignored
     * @throws FileNotFoundException 
     */
    public static void main(final String... args) throws FileNotFoundException {
        Application.launch(DrawNumberFXApplication.class, 
                "examplemvc/config.yml");
    }
}
