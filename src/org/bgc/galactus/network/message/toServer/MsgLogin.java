package org.bgc.galactus.network.message.toServer;

import org.bgc.galactus.database.PasswordHash;
import org.bgc.galactus.database.UserExceptions.IncorrectLoginDataException;
import org.bgc.galactus.network.message.Message;
import org.bgc.galactus.network.message.UserName;
import org.bgc.galactus.network.server.Server;

import java.io.Serializable;

public class MsgLogin extends Message implements Serializable {


    private String login;
    private String password;

    public MsgLogin(String login, String password) {
        this.login = login;
        this.password = new PasswordHash(password).toString();
    }

    @Override
    public void execute() {
        try {
            Server.accountManager.login(login, password);
            Server.userList.remove(getUser().getUserName());
            getUser().setUserName(new UserName(login));
            Server.userList.add(getUser().getUserName(), getUser());
        } catch (IncorrectLoginDataException e) {
            e.printStackTrace();
        }

    }
}
