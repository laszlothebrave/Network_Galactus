package org.bgc.galactus.network.client.controller.start.eventHandler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.bgc.galactus.network.client.controller.GUI;


public class NewAccount implements EventHandler<ActionEvent> {
    private Text notificationArea;
    private TextField userNameField;
    private PasswordField passwordField;
    private PasswordField repeatPasswordFiled;
    private TextField emailFiled;

    public NewAccount(Text notificationArea, TextField userNameField, PasswordField passwordField, PasswordField repeatPasswordFiled, TextField emailFiled) {
        this.notificationArea = notificationArea;
        this.userNameField = userNameField;
        this.passwordField = passwordField;
        this.repeatPasswordFiled = repeatPasswordFiled;
        this.emailFiled = emailFiled;
    }

    @Override
    public void handle(ActionEvent event) {
        if (passwordField.getText().equals(repeatPasswordFiled.getText())) {
            notificationArea.setFill(Color.FIREBRICK);
            notificationArea.setText("Creating new account");
            GUI.client.newAccount(userNameField.getText(), passwordField.getText(), emailFiled.getText());
        } else {
            notificationArea.setFill(Color.FIREBRICK);
            notificationArea.setText("Password not matching");
        }
    }
}
