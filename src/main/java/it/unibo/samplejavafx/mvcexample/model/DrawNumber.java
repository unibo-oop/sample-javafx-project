package it.unibo.samplejavafx.mvcexample.model;

/**
 * This class models a draw number game.
 *
 */
public interface DrawNumber {

    /**
     * @return the maximum number
     */
    int getMax();

    /**
     * @return the minimum number
     */
    int getMin();

    /**
     * @return the number of attempts left
     */
    int getAttemptsLeft();

    /**
     * resets the game.
     */
    void reset();

    /**
     * Guesses a number.
     *
     * @param n the guess
     * @return the result of the guess
     */
    DrawResult attempt(int n);
}
