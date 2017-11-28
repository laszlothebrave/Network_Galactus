package org.bgc.galactus.network.client;

public interface ClientInterface {
    public void login(String name, String password);
    public void newAccount(String name, String password, String email);
    public void resetPassword(String email);
}
