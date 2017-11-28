package org.bgc.galactus.network.client.controller.start.eventHandler;

import javafx.event.EventHandler;
import javafx.scene.text.Text;
import org.bgc.galactus.network.client.controller.start.event.LoginFailed;

public class LoginFailedHandler implements EventHandler<LoginFailed> {
    private Text notificationArea;

    public LoginFailedHandler(Text notificationArea){
        this.notificationArea = notificationArea;
    }
    @Override
    public void handle(LoginFailed event) {
        notificationArea.setText("Login process failed");
    }
}
