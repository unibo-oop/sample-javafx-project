package it.unibo.samplejavafx.mvcexample;

import java.util.Optional;

import javafx.beans.property.Property;

/**
 * This class models a draw number game.
 *
 */
public interface DrawNumberObservable extends DrawNumber {
    Property<Integer> minProperty();
    Property<Integer> maxProperty();
    Property<Optional<Integer>> lastGuessProperty();
    Property<Optional<DrawResult>> lastGuessResult();
    Property<Integer> attemptsProperty();
    Property<Integer> remainingAttemptsProperty();
}
