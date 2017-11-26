package org.bgc.galactus.network.server;


import org.bgc.galactus.network.message.Message;
import org.bgc.galactus.network.message.MsgAnnouncement;

class MessageProcessorForServer implements Runnable{
    public int numberOfMessages;


    MessageProcessorForServer() {
        numberOfMessages = 0;
    }

    public void run() {
        System.out.println("Message processor started");
        while (Server.isRunning){
            try {
                Message message = (Message) Server.linkedBlockingQueue.take();
                numberOfMessages++;
                message.execute();
            } catch (InterruptedException e) {
                System.out.println("Message processor crashed. Check for Error");
            }
        }
    }

    void stop() {
        try {
            Server.linkedBlockingQueue.put(new MsgAnnouncement("Message Processor stoped correctly"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}