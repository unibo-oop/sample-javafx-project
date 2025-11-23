package it.unibo.samplejavafx.mvcexample.view;

import it.unibo.samplejavafx.mvcexample.model.DrawResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * This class implements a view that can write on any PrintStream.
 */
public final class PrintStreamView implements DrawNumberView {

    private final PrintStream out;

    /**
     * @param stream the {@link PrintStream} where to write
     */
    public PrintStreamView(final PrintStream stream) {
        out = new PrintStream(stream, true, StandardCharsets.UTF_8);
    }

    /**
     * Builds a {@link PrintStreamView} that writes on file, given a path.
     *
     * @param path a file path
     * @throws FileNotFoundException if the file cannot be created
     */
    public PrintStreamView(final String path) throws FileNotFoundException {
        out = new PrintStream(new FileOutputStream(path), true, StandardCharsets.UTF_8);
    }

    @Override
    public void start() {
        /*
         * PrintStreams are always ready.
         */
    }

    @Override
    public void numberIncorrect() {
        out.println("You must enter a number");
    }

    @Override
    public void result(final DrawResult res) {
        out.println(res.drawResult().getDescription());
    }

    @Override
    public void displayError(final String message) {
        out.println("[ERROR]: " + message);
    }

}
