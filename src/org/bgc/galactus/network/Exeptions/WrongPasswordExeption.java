package org.bgc.galactus.network.Exeptions;

public class WrongPasswordExeption extends Exception {
    public WrongPasswordExeption(){
        super("Wrong password");
    }
}
