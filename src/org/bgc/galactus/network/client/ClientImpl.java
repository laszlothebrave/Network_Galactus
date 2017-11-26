package org.bgc.galactus.network.client;

import javafx.application.Application;
import org.bgc.galactus.network.client.controller.GUI;

public class ClientImpl implements ClientInterface{
    public static ClientImpl client;

    public static void main(String[] args){
        client = new ClientImpl();
        client.startGUI();
    }

    private void startGUI() {
        Application.launch(GUI.class, "");
    }

    @Override
    public void login(String name, String password) {

    }
}
