package org.bgc.galactus.database;


import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.bgc.galactus.database.UserExceptions.CreateUserExcaptions.EmailAdressOccupiedException;
import org.bgc.galactus.database.UserExceptions.CreateUserExcaptions.InvalidConfirmationKeyException;
import org.bgc.galactus.database.UserExceptions.CreateUserExcaptions.InvalidEmailAdressException;
import org.bgc.galactus.database.UserExceptions.CreateUserExcaptions.UserExistException;
import org.bgc.galactus.database.UserExceptions.IncorrectLoginDataException;
import org.bgc.galactus.email.ConfirmationEmail;
import org.bgc.galactus.email.SMTPServer;

import java.sql.SQLException;

public class AccountManager {
    public void createAccount(String login, String passwordHash, String email) throws InvalidEmailAdressException, UserExistException, EmailAdressOccupiedException {
        try {
            if (userExist(login)) throw new UserExistException();
            else if (invalidEmail(email)) throw new InvalidEmailAdressException();
            else if (emailOccupied(email)) throw new EmailAdressOccupiedException();
            else {
                /*String confirmationKey = DatatypeConverter.printHexBinary(RandomUtils.nextBytes(2));
                Mysql.executeUpdate("INSERT INTO users (login,password,email,confirmationKey) VALUES ('" + login + "','" + passwordHash + "','" + email + "','"+confirmationKey+"')");
                ConfirmationEmail confirmationEmail = new ConfirmationEmail(null,confirmationKey,email);
                SMTPServer.send(confirmationEmail);*/
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void confirmEmail(String login,String confirmationKey) throws InvalidConfirmationKeyException {
        try {
            if(Mysql.executeQuery("SELECT * FROM users WHERE login='" + login + "' AND confirmationKey='" + confirmationKey + "'").isBeforeFirst()) {
                Mysql.executeUpdate("UPDATE users SET confirmed=1 WHERE login='"+login+"'");
            }else{
                throw new InvalidConfirmationKeyException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void login(String login, String passwordHash) throws IncorrectLoginDataException {
        try {
            if (!Mysql.executeQuery("SELECT * FROM users WHERE login='" + login + "' AND password='" + passwordHash + "'").isBeforeFirst())
                throw new IncorrectLoginDataException();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean userExist(String login) throws SQLException {
        return Mysql.executeQuery("SELECT * FROM users WHERE login='" + login + "'").isBeforeFirst();
    }

    private boolean emailOccupied(String email) throws SQLException {
        return Mysql.executeQuery("SELECT * FROM users WHERE email='" + email + "'").isBeforeFirst();
    }

    private boolean invalidEmail(String email) {
        return !EmailValidator.getInstance().isValid(email);
    }
}
