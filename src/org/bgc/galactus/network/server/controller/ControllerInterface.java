package org.bgc.galactus.network.server.controller;

import org.bgc.galactus.network.server.controller.message.Message;

interface ControllerInterface {
    Message getMessage();
    void SendMessage(Message message);
}
