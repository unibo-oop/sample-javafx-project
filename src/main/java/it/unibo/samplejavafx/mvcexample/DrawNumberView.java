package it.unibo.samplejavafx.mvcexample;

/**
 *
 */
public interface DrawNumberView {

    /**
     * @param observer the controller to attach
     */
    void setObserver(DrawNumberViewObserver observer);

    /**
     * This method is called before the UI is used. It should finalize its status (if needed).
     */
    void start();

    /**
     * Informs the user that the inserted number is not correct.
     */
    void numberIncorrect();

    /**
     * @param res the result of the last draw
     */
    void result(DrawResult res);

    /**
     * Some unexpected error occurred in the Controller, and the user should be informed.
     * 
     * @param message the message associated with the error.
     */
    void displayError(String message);

}
