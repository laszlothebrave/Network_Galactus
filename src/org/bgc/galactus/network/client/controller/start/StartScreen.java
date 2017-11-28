package org.bgc.galactus.network.client.controller.start;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.stage.Stage;
import org.bgc.galactus.network.client.controller.GUI;
import org.bgc.galactus.network.client.controller.start.event.LoginFailed;
import org.bgc.galactus.network.client.controller.start.eventHandler.LoginFailedHandler;

public class StartScreen {
    private Stage primaryStage;
    private LoginScreen loginScreen;
    private NewAccountScreen newAccountScreen;
    private ResetPasswordScreen resetPasswordScreen;

    public StartScreen(Stage primaryStage){
        this.primaryStage = primaryStage;
        loginScreen = new LoginScreen(this);
        newAccountScreen = new NewAccountScreen(this);
        resetPasswordScreen = new ResetPasswordScreen(this);
        showLoginScreen();
    }

    public void showLoginScreen(){
        loginScreen.reset();
        primaryStage.setScene(loginScreen.getScene());
        primaryStage.show();
    }

    public void showNewAccountScreen(){
        newAccountScreen.reset();
        primaryStage.setScene(newAccountScreen.getScene());
        primaryStage.show();
    }

    public void showResetPasswordScreen(){
        resetPasswordScreen.reset();
        primaryStage.setScene(resetPasswordScreen.getScene());
        primaryStage.show();
    }
}
