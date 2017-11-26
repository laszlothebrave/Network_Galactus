package org.bgc.galactus.network.server;



import org.bgc.galactus.network.Exeptions.FullServerExeption;
import org.bgc.galactus.network.message.Message;
import org.bgc.galactus.network.message.MsgAnnouncement;
import org.bgc.galactus.network.message.UserName;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class User implements Runnable{
    private Socket clientSocket;
    private LinkedBlockingQueue linkedBlockingQueue;
    private UserName userName;
    private ObjectOutputStream outOis;
    private ObjectInputStream inOis;

    User(Socket clientSocket, LinkedBlockingQueue linkedBlockingQueue) {
        this.linkedBlockingQueue = linkedBlockingQueue;
        this.clientSocket = clientSocket;
        userName = new UserName(Integer.toString(Server.userList.getAndIncreaseUserCounter()));
    }

    private void createStreams() throws IOException {
        outOis = new ObjectOutputStream(clientSocket.getOutputStream());
        inOis = new ObjectInputStream(clientSocket.getInputStream());
    }

    private void addToServer() throws IOException, FullServerExeption {
        if (Server.userList.getCurrentUser() >= Server.userList.getMaxUsers()) {
            outOis.writeObject(new MsgAnnouncement("Can't connect. server full."));
            throw new FullServerExeption();
        }
        Server.userList.add(userName, this);
    }

    private void listen() throws IOException, ClassNotFoundException, InterruptedException{
        while (true) linkedBlockingQueue.put(((Message) inOis.readObject()).sign(this));
    }

    void send(Message message) throws IOException {
        outOis.writeObject(message);
    }

    public void run() {
        try {
            createStreams();
            addToServer();
            listen();
        } catch (InterruptedException | IOException | ClassNotFoundException | FullServerExeption e) {
            System.out.println("User:   " + userName.getUserName() + "   disconnected");
        }
        disconnect();
    }

    void disconnect() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Server.userList.remove(userName);
    }

    public void setUserName(UserName userName) {
        this.userName = userName;
    }

    public UserName getUserName() {
        return userName;
    }
}
