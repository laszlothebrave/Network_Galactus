package org.bgc.galactus.network.client.controller;

import javafx.application.Application;
import javafx.stage.Stage;
import org.bgc.galactus.network.client.*;
import org.bgc.galactus.network.client.controller.start.LoginScreen;
import org.bgc.galactus.network.client.controller.start.StartScreen;

public class GUI extends Application {
    public static ClientInterface client;

    public GUI(){};
    public GUI(ClientInterface client) {
        this.client = client;
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        new StartScreen(primaryStage).showLoginScreen();
    }
}
