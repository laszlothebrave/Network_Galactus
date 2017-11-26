package org.bgc.galactus.network.client;

import org.bgc.galactus.network.message.Message;

import javax.net.SocketFactory;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public class ClientListener implements Runnable{
    private SSLSocket socket;
    private ObjectInputStream inOis;
    private ObjectOutputStream outOis;

    @Override
    public void run() {
        try {
            System.setProperty("javax.net.ssl.trustStore", "C:/Users/Zin/Desktop/pro/java/BGC/lib/truststore3");
            System.setProperty("javax.net.ssl.trustStorePassword", "password");
            SocketFactory ssf = SSLSocketFactory.getDefault();
            socket = (SSLSocket) ssf.createSocket("2a02:a31a:e03f:8280:eca0:1c55:2817:f855", 13579);
            //printSessionInfo(socket);
            outOis = new ObjectOutputStream(socket.getOutputStream());
            inOis = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connection established");
            while (Client.isRunning) Client.linkedBlockingQueue.put(inOis.readObject());
        } catch (InterruptedException | IOException | ClassNotFoundException e) {
            System.out.println("Socket closed");
        }
    }

    void stop() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void send(Message message) {
        try {
            outOis.writeObject(message);
        } catch (IOException e) {
            System.out.println("client can't send message");
        }
    }

    private void printSessionInfo(SSLSocket socket){
        SSLSession session = socket.getSession();
        Certificate[] cchain = new Certificate[0];
        try {
            cchain = session.getPeerCertificates();
        } catch (SSLPeerUnverifiedException e) {
            e.printStackTrace();
        }
        System.out.println("The Certificates used by peer");
        for (Certificate aCchain : cchain) {
            System.out.println(((X509Certificate) aCchain).getSubjectDN());
        }

        System.out.println("Peer host is " + session.getPeerHost());
        System.out.println("Cipher is " + session.getCipherSuite());
        System.out.println("Protocol is " + session.getProtocol());
        System.out.println("ID is " + new BigInteger(session.getId()));
        System.out.println("Session created in " + session.getCreationTime());
        System.out.println("Session accessed in " + session.getLastAccessedTime());
    }
}
