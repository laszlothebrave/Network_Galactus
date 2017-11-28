package org.bgc.galactus.network.client.controller;

import javafx.application.Application;
import javafx.event.EventType;
import javafx.stage.Stage;
import org.bgc.galactus.network.client.*;
import org.bgc.galactus.network.client.controller.start.StartScreen;
import org.bgc.galactus.network.client.controller.start.event.LoginFailed;

public class GUI extends Application {
    public static ClientImpl client;
    private StartScreen startScreen;
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        client = new ClientImpl(primaryStage);
        new Thread(client).start();
        this.primaryStage = primaryStage;
        startScreen = new StartScreen(primaryStage);
    }

    public void loginFailed(){
        primaryStage.fireEvent(new LoginFailed());
    }
}
