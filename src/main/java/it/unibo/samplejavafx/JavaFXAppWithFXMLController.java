package it.unibo.samplejavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * Controller for the `JavaFXAppWithFXML` application.
 */
public class JavaFXAppWithFXMLController {
    @FXML
    private Label myLabel;

    @FXML
    private Button myButton;

    /**
     * Event handler for `myButton`.
     * @param evt
     */
    @FXML
    public final void myButtonOnClickHandler(final MouseEvent evt) {
        myLabel.setText("Clicked! " + evt.getClickCount());
    }
}
