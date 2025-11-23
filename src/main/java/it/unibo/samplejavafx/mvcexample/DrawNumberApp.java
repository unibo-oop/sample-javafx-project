package it.unibo.samplejavafx.mvcexample;

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
     */
    public static void main(final String... args) {
        Application.launch(EntryPoint.class);
    }
}
