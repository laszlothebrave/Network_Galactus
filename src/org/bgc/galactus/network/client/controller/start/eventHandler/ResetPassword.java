package org.bgc.galactus.network.client.controller.start.eventHandler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ResetPassword implements EventHandler<ActionEvent> {
    private Text notificationArea;
    private TextField emailField;

    public ResetPassword(Text notificationArea, TextField emailField) {
        this.notificationArea = notificationArea;
        this.emailField = emailField;
    }

    @Override
    public void handle(ActionEvent event) {
        notificationArea.setFill(Color.VIOLET);
        notificationArea.setText("Reseting password");
    }
}
