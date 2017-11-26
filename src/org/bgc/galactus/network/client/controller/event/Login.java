package org.bgc.galactus.network.client.controller.event;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.bgc.galactus.network.client.ClientImpl;

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
        notificationArea.setFill(Color.BLACK);
        notificationArea.setText("Loging in progress");
    }
}
