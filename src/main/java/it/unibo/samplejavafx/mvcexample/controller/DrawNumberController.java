package it.unibo.samplejavafx.mvcexample.controller;

import it.unibo.samplejavafx.mvcexample.view.DrawNumberView;

/**
 * Controller interface.
 */
public interface DrawNumberController {

    /**
     * @return the number of attempts left
     */
    int getAttemptsLeft();

    /**
     * @return the maximum number
     */
    int getMax();

    /**
     * @return the maximum value
     */
    int getMin();

    /**
     * Makes a guess.
     *
     * @param n the attempt
     */
    void newAttempt(int n);

    /**
     * Registers a view to the controller.
     *
     * @param view the view to register
     */
    void registerView(DrawNumberView view);

    /**
     * Resets the current game (if any is running) and starts a new one.
     */
    void resetGame();
}
