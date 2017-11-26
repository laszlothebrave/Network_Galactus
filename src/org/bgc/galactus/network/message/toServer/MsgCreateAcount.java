package org.bgc.galactus.network.message.toServer;



import org.bgc.galactus.database.PasswordHash;
import org.bgc.galactus.database.UserExceptions.CreateUserExcaptions.CreateUserException;
import org.bgc.galactus.network.message.Message;
import org.bgc.galactus.network.server.Server;

import java.io.Serializable;

public class MsgCreateAcount extends Message implements Serializable {

    private String login;
    private String passwordHash;
    private String email;

    public MsgCreateAcount(String login, String password, String email) {
        this.login = login;
        this.passwordHash = new PasswordHash(password).toString();
        this.email = email;
    }

    @Override
    public void execute() {
        try {
            Server.accountManager.createAccount(login, passwordHash, email);
        } catch (CreateUserException e) {
            e.printStackTrace();
        }
    }
}
