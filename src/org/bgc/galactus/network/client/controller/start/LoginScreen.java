package org.bgc.galactus.network.client.controller.start;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.bgc.galactus.network.client.controller.start.event.LoginFailed;
import org.bgc.galactus.network.client.controller.start.eventHandler.Login;
import org.bgc.galactus.network.client.controller.start.eventHandler.LoginFailedHandler;

import java.awt.event.MouseEvent;

public class LoginScreen {
    private StartScreen startScreen;
    private Scene scene;
    private GridPane grid;
    private Text notificationArea;
    private TextField userNameField;
    private PasswordField passwordField;

    public LoginScreen(StartScreen startScreen) {
        this.startScreen = startScreen;
        makeGrid();
        title();
        loginForm();
        setNotificationArea();
        buttons();
        makeScene();
    }

    public Scene getScene() {
        return scene;
    }

    public void reset(){
        notificationArea.setText("");
    }

    private void makeGrid() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
    }

    private void title() {
        Text scenetitle = new Text("Login to Galactus");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
    }

    private void loginForm() {
        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        userNameField = new TextField();
        grid.add(userNameField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        passwordField = new PasswordField();
        grid.add(passwordField, 1, 2);
    }

    private void setNotificationArea() {
        notificationArea = new Text();
        grid.add(notificationArea, 1, 3);
    }

    private void buttons() {
        Button loginBtn = new Button("Sign in");
        HBox hbLoginBtn = new HBox(10);
        loginBtn.setMinWidth(150);
        hbLoginBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbLoginBtn.getChildren().add(loginBtn);
        grid.add(hbLoginBtn, 1, 5);
        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startScreen.showLoginScreen();
            }
        });

        Button newAccountBtn = new Button("Create new account");
        HBox hbNewAccountBtn = new HBox(10);
        newAccountBtn.setMinWidth(150);
        hbNewAccountBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbNewAccountBtn.getChildren().add(newAccountBtn);
        grid.add(hbNewAccountBtn, 1, 6);
        newAccountBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startScreen.showNewAccountScreen();
            }
        });

        Button actionButton = new Button("Login");
        HBox hbActionButton = new HBox(10);
        actionButton.setMinWidth(150);
        hbActionButton.setAlignment(Pos.BOTTOM_RIGHT);
        hbActionButton.getChildren().add(actionButton);
        grid.add(hbActionButton, 2, 2);
        actionButton.setOnAction(new Login(notificationArea, userNameField, passwordField));

        Button resetPasswordBtn = new Button("Reset password");
        HBox hbResetPasswordBtn = new HBox(10);
        resetPasswordBtn.setMinWidth(150);
        hbResetPasswordBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbResetPasswordBtn.getChildren().add(resetPasswordBtn);
        grid.add(hbResetPasswordBtn, 1, 7);
        resetPasswordBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startScreen.showResetPasswordScreen();
            }
        });
    }

    private void makeScene() {
        //grid.setGridLinesVisible(true);
        scene = new Scene(grid, 600, 400);
        scene.addEventHandler(LoginFailed.LOGIN_FAILED, new LoginFailedHandler(notificationArea));
    }
}
