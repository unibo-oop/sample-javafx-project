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

public class SwingAppWithJavaFX {

    public static void main(final String[] args){
        initMainJFrame(new JFrame("JFrame GUI"));
    }
    
    private static void initMainJFrame(final JFrame frame) {
        final JButton button = new JButton();
        button.setText("Launch JavaFX Scene");
        button.addActionListener(event -> {
          final JFXPanel jfxPanel = new JFXPanel();
          Platform.runLater(() -> {
            jfxPanel.setScene(new Scene(initJavaFXSceneUI(), 300, 300));
            SwingUtilities.invokeLater(() -> {
              final JFrame frameWithJavaFX = new JFrame("JFrame with JavaFX embedded!");
              frameWithJavaFX.add(jfxPanel);
              frameWithJavaFX.pack();
              frameWithJavaFX.setVisible(true);
        }); }); });

        final JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(button);

        frame.setContentPane(panel);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    private static Parent initJavaFXSceneUI() {
        final Label lbl = new Label();
        lbl.setText("Hello, JavaFX World!");

        final Button btn = new Button();
        btn.setText("Say Hello");
        btn.setOnMouseClicked(event -> {
          lbl.setText("Hello from Button!");
        });

        final VBox root = new VBox();
        root.getChildren().add(lbl);
        root.getChildren().add(btn);

        return root;
    }
    
}
