package it.unibo.samplejavafx;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Swing applications that embeds a JavaFX panel.
 */
public final class SwingAppWithJavaFX {
    private static final int SCENE_WIDTH = 300;
    private static final int SCENE_HEIGHT = 300;

    private SwingAppWithJavaFX() {
        // the constructor will never be called directly.
    }

    /**
     * Program's entry point.
     *
     * @param args ignored
     */
    public static void main(final String[] args) {
        initMainJFrame(new JFrame("JFrame GUI"));
    }

    private static void initMainJFrame(final JFrame frame) {
        final JButton button = new JButton();
        button.setText("Launch JavaFX Scene");
        button.addActionListener(event -> {
            final JFXPanel jfxPanel = new JFXPanel();
            Platform.runLater(() -> {
                jfxPanel.setScene(new Scene(initJavaFXSceneUI(), SCENE_WIDTH, SCENE_HEIGHT));
                SwingUtilities.invokeLater(() -> {
                    final JFrame frameWithJavaFX = new JFrame("JFrame with JavaFX embedded!");
                    frameWithJavaFX.add(jfxPanel);
                    frameWithJavaFX.pack();
                    frameWithJavaFX.setVisible(true);
                });
            });
        });
        // Create a panel and add the button to it
        final JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(button);
        // Set the panel as the content pane of the frame
        frame.setContentPane(panel);
        frame.setSize(SCENE_WIDTH, SCENE_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static Parent initJavaFXSceneUI() {
        final Label label = new Label();
        label.setText("Hello, JavaFX World!");
        // Create a button and set its action
        final Button button = new Button();
        button.setText("Say Hello");
        button.setOnMouseClicked(event -> {
            label.setText("Hello from Button!");
        });
        // Create a layout and add the label and the button to it
        final VBox root = new VBox();
        root.getChildren().add(label);
        root.getChildren().add(button);
        return root;
    }
}
