package it.unibo.samplejavafx.mvcexample.model;

/**
 * Encapsulates the concept of configuration.
 */
public final class Configuration {

    private final int max;
    private final int min;
    private final int attempts;

    private Configuration(final int max, final int min, final int attempts) {
        this.max = max;
        this.min = min;
        this.attempts = attempts;
    }

    /**
     * @return the maximum value
     */
    public int getMax() {
        return max;
    }

    /**
     * @return the minimum value
     */
    public int getMin() {
        return min;
    }

    /**
     * @return the number of attempts
     */
    public int getAttempts() {
        return attempts;
    }

    /**
     * @return true if the configuration is consistent
     */
    public boolean isConsistent() {
        return attempts > 0 && min < max;
    }

    /**
     * Pattern builder: used here because:
     *
     * <p>
     * - all the parameters of the Configuration class have a default value, which
     * means that we would like to have all the possible combinations of
     * constructors (one with three parameters, three with two parameters, three
     * with a single parameter), which are way too many and confusing to use
     *
     * <p>
     * - moreover, it would be impossible to provide all of them, because they are
     * all of the same type, and only a single constructor can exist with a given
     * list of parameter types.
     *
     * <p>
     * - the Configuration class has three parameters of the same type, and it is
     * unclear to understand, in a call to its contructor, which is which. By using
     * the builder, we emulate the so-called "named arguments".
     *
     */
    public static class Builder {

        private static final int MIN = 0;
        private static final int MAX = 100;
        private static final int ATTEMPTS = 10;

        private int min = MIN;
        private int max = MAX;
        private int attempts = ATTEMPTS;
        private boolean consumed;

        /**
         * @param minimum the minimum value
         * @return this builder, for method chaining
         */
        public Builder withMin(final int minimum) {
            this.min = minimum;
            return this;
        }

        /**
         * @param maximum the maximum value
         * @return this builder, for method chaining
         */
        public Builder withMax(final int maximum) {
            this.max = maximum;
            return this;
        }

        /**
         * @param maxAttempts the maxAttempts count
         * @return this builder, for method chaining
         */
        public Builder withMaxAttempts(final int maxAttempts) {
            this.attempts = maxAttempts;
            return this;
        }

        /**
         * @return a configuration
         */
        public final Configuration build() {
            if (consumed) {
                throw new IllegalStateException("The builder can only be used once");
            }
            consumed = true;
            return new Configuration(max, min, attempts);
        }
    }
}

