package org.bgc.galactus.network.client;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.stage.Stage;
import org.bgc.galactus.network.client.controller.GUI;
import org.bgc.galactus.network.client.controller.start.event.LoginFailed;
import org.bgc.galactus.network.message.UserName;
import org.bgc.galactus.network.message.toServer.MsgLogin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientImpl implements ClientInterface, Runnable {
    private Stage primaryStage;
    static int port;
    static boolean isRunning;
    static LinkedBlockingQueue linkedBlockingQueue;
    static UserName userName;
    private static ClientListener clientListener;
    private static MessageProcessorForClient messageProcessorForClient;
    private static BufferedReader keyboardIn;
    private static String command;
    private static LinkedBlockingQueue<String> queue;

    private void initializeVariables() {
        userName = new UserName("default username");
        port = 13579;
        isRunning = false;
        linkedBlockingQueue = new LinkedBlockingQueue();
        queue = new LinkedBlockingQueue<>();
        clientListener = new ClientListener();
        messageProcessorForClient = new MessageProcessorForClient();
        keyboardIn = new BufferedReader(new InputStreamReader(System.in));
        command = "null";
    }

    public ClientImpl(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void login(String name, String password) {
        //clientListener.send(new MsgLogin(name, password));
    }

    private static void stop() {
        isRunning = false;
        clientListener.stop();
        messageProcessorForClient.stop();
    }

    @Override
    public void newAccount(String name, String password, String email) {

    }

    @Override
    public void resetPassword(String email) {

    }

    @Override
    public void run() {
        isRunning = true;
        while (isRunning){
            try {
                Thread.sleep(5000);
                Event.fireEvent(primaryStage.getScene(), new LoginFailed(this, primaryStage));
                System.out.println("Event fired");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
