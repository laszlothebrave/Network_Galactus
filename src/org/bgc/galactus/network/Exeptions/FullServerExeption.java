package org.bgc.galactus.network.Exeptions;

public class FullServerExeption extends Exception {
    public FullServerExeption(){
        super ("ServerListener is full. Can't add player.");
    }
}
