package org.bgc.galactus.network.client.controller.start.eventHandler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.bgc.galactus.network.client.controller.GUI;

public class Login implements EventHandler<ActionEvent> {
    private Text notificationArea;
    private TextField userNameField;
    private PasswordField passwordField;

    public Login(Text notificationArea, TextField userNameField, PasswordField passwordField) {
        this.notificationArea = notificationArea;
        this.userNameField = userNameField;
        this.passwordField = passwordField;
    }

    @Override
    public void handle(ActionEvent event) {
        notificationArea.setFill(Color.FIREBRICK);
        notificationArea.setText("Loging in progress");
        GUI.client.login(userNameField.getText(), passwordField.getText());
    }
}
