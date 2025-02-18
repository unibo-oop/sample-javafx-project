package it.unibo.samplejavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * Controller for the `JavaFXAppWithFXML` application.
 */
public class JavaFXAppWithFXMLController {
    @FXML
    private Label myLabel;

    /**
     * Event handler for `myButton`.
     *
     * @param event ignored
     */
    @FXML
    public final void myButtonOnClickHandler(final MouseEvent event) {
        myLabel.setText("Clicked! " + event.getClickCount());
    }
}
