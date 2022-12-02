package it.unibo.samplejavafx.mvcexample;

import java.util.Optional;
import java.util.Random;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 */
@SuppressFBWarnings()
public final class DrawNumberImpl implements DrawNumberObservable {

    private final Property<Integer> choice;
    private final Property<Integer> min;
    private final Property<Integer> max;
    private final Property<Integer> attempts;
    private final Property<Integer> remainingAttempts;
    private final Property<Optional<Integer>> lastGuess;
    private final Property<Optional<DrawResult>> lastGuessResult;
    private final Random random = new Random();

    /**
     * @param configuration the game configuration
     * @throws IllegalStateException if the configuration is not consistent
     */
    public DrawNumberImpl(final Configuration configuration) {
        lastGuess = new SimpleObjectProperty<>(Optional.empty());
        lastGuessResult = new SimpleObjectProperty<>(Optional.empty());
        if (!configuration.isConsistent()) {
            throw new IllegalArgumentException("The game requires a valid configuration");
        }
        this.min = new SimpleObjectProperty<>(configuration.getMin());
        this.max = new SimpleObjectProperty<>(configuration.getMax());
        this.attempts = new SimpleObjectProperty<>(configuration.getAttempts());
        this.remainingAttempts = new SimpleObjectProperty<>(configuration.getAttempts());
        this.choice = new SimpleObjectProperty<>(configuration.getAttempts());
        this.reset();
    }

    @Override
    public void reset() {
        this.remainingAttempts.setValue(this.attempts.getValue());
        this.choice.setValue(this.min.getValue() + random.nextInt(this.max.getValue() - this.min.getValue() + 1));
    }

    @Override
    public DrawResult attempt(final int guess) {
        lastGuess.setValue(Optional.of(guess));
        DrawResult result = lastGuessResult.getValue().orElse(DrawResult.YOU_LOST);
        if (this.remainingAttempts.getValue() <= 0) {
            result = DrawResult.YOU_LOST;
        }
        if (guess < this.min.getValue() || guess > this.max.getValue()) {
            throw new IllegalArgumentException("The number is outside boundaries");
        }
        remainingAttempts.setValue(remainingAttempts.getValue() - 1);
        if (guess > this.choice.getValue()) {
            result = DrawResult.YOURS_HIGH;
        }
        if (guess < this.choice.getValue()) {
            result = DrawResult.YOURS_LOW;
        }
        if (guess == this.choice.getValue()) {
            result = DrawResult.YOU_WON;
        }
        lastGuessResult.setValue(Optional.of(result));
        return result;
    }

    @Override
    public Property<Integer> minProperty() {
        return min;
    }

    @Override
    public Property<Integer> maxProperty() {
        return max;
    }

    @Override
    public Property<Integer> remainingAttemptsProperty() {
        return remainingAttempts;
    }

    @Override
    public Property<Integer> attemptsProperty() {
        return attempts;
    }

    @Override
    public Property<Optional<Integer>> lastGuessProperty() {
        return lastGuess;
    }

    @Override
    public Property<Optional<DrawResult>> lastGuessResult() {
        return lastGuessResult;
    }
}
