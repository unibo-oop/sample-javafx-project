package it.unibo.samplejavafx.mvcexample.model;

import java.util.Random;

import static it.unibo.samplejavafx.mvcexample.model.DrawResult.DrawOutcome.YOURS_HIGH;
import static it.unibo.samplejavafx.mvcexample.model.DrawResult.DrawOutcome.YOURS_LOW;
import static it.unibo.samplejavafx.mvcexample.model.DrawResult.DrawOutcome.YOU_LOST;
import static it.unibo.samplejavafx.mvcexample.model.DrawResult.DrawOutcome.YOU_WON;

/**
 *
 */
public final class DrawNumberImpl implements DrawNumber {

    private final int min;
    private final int max;
    private final int attempts;
    private final Random random = new Random();
    private int remainingAttempts;
    private int correctValue;

    /**
     * @param configuration the game configuration
     * @throws IllegalStateException if the configuration is not consistent
     */
    public DrawNumberImpl(final Configuration configuration) {
        if (!configuration.isConsistent()) {
            throw new IllegalArgumentException("The game requires a valid configuration");
        }
        this.min = configuration.getMin();
        this.max = configuration.getMax();
        this.attempts = configuration.getAttempts();
        this.remainingAttempts = configuration.getAttempts();
        this.reset();
    }

    @Override
    public void reset() {
        remainingAttempts = attempts;
        correctValue = this.min + random.nextInt(max - min + 1);
    }

    @Override
    public DrawResult attempt(final int guess) {
        if (guess < min || guess > max) {
            throw new IllegalArgumentException("The number is outside boundaries");
        }
        switch (remainingAttempts) {
            case 0:
                return new DrawResult(guess, YOU_LOST);
            case 1:
                if (guess != correctValue) {
                    return new DrawResult(guess, YOU_LOST);
                }
            default:
                remainingAttempts--;
                if (guess == correctValue) {
                    return new DrawResult(guess, YOU_WON);
                } else if (guess > correctValue) {
                    return new DrawResult(guess, YOURS_HIGH);
                } else {
                    return new DrawResult(guess, YOURS_LOW);
                }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMax() {
        return max;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMin() {
        return min;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAttemptsLeft() {
        return remainingAttempts;
    }
}
