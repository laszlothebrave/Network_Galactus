package org.bgc.galactus.network.message.toServer;


import org.bgc.galactus.network.message.Message;
import org.bgc.galactus.network.message.UserName;

import java.io.Serializable;

public class MsgPassword extends Message implements Serializable {
    private String password;
    private UserName userName;

    public MsgPassword() {}

    public MsgPassword(UserName userName, String password) {
        this.password = password;
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public UserName getUserName() {
        return userName;
    }

    @Override
    public void execute() {

    }
}
