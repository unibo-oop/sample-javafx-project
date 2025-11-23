package it.unibo.samplejavafx.mvcexample.model;

/**
 * This record models the result of a draw attempt.
 *
 * @param attempted  the attempted number
 * @param drawResult the outcome of the draw
 */
public record DrawResult(int attempted, DrawOutcome drawResult) {

    /**
     * Possible outcomes of a guess.
     * Here implemented as enum, but better implementations are possible.
     */
    public enum DrawOutcome {

        /**
         * Low number.
         */
        YOURS_LOW("Your number is too small"),
        /**
         * High number.
         */
        YOURS_HIGH("Your number is too big"),
        /**
         * Correct attempt.
         */
        YOU_WON("You won"),
        /**
         * No attempts left.
         */
        YOU_LOST("You lost");

        private final String message;

        DrawOutcome(final String message) {
            this.message = message;
        }

        /**
         * @return a description of the draw result
         */
        public String getDescription() {
            return message;
        }
    }
}

