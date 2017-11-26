package org.bgc.galactus.network.message.toServer;



import org.bgc.galactus.database.UserExceptions.CreateUserExcaptions.InvalidConfirmationKeyException;
import org.bgc.galactus.network.message.Message;
import org.bgc.galactus.network.server.Server;

import java.io.Serializable;

public class MsgVerification extends Message implements Serializable{
    public String code;

    public MsgVerification(String code) {
        this.code = code;
    }

    @Override
    public void execute() {
        try {
            Server.accountManager.confirmEmail(getUser().getUserName().getUserName(), code);
        } catch (InvalidConfirmationKeyException e) {
            e.printStackTrace();
        }
    }
}
