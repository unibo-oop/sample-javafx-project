package it.unibo.samplejavafx.mvcexample;

import java.util.Optional;

import javafx.beans.property.Property;

/**
 * This class models a draw number game.
 *
 */
public interface DrawNumberObservable extends DrawNumber {
    /**
     * @return the property of the minimum allowed value
     */
    Property<Integer> minProperty();

    /**
     * @return the property of the maximum allowed value
     */
    Property<Integer> maxProperty();

    /**
     * @return the property of the last guess
     */
    Property<Optional<Integer>> lastGuessProperty();

    /**
     * @return the property of the outcome of the last guess
     */
    Property<Optional<DrawResult>> lastGuessResult();

    /**
     * @return the property of the number of allowed attempts for each game instance
     */
    Property<Integer> attemptsProperty();

    /**
     * @return the property of the remaining attempts for the user
     */
    Property<Integer> remainingAttemptsProperty();
}
