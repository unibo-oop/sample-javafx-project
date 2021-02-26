package it.unibo.samplejavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class JavaFXAppWithFXMLController {
    @FXML
    private Label myLabel;

    @FXML
    private Button myButton;

    @FXML
    public final void myButtonOnClickHandler(final MouseEvent evt) {
        myLabel.setText("Clicked! " + evt.getClickCount());
    }
}
