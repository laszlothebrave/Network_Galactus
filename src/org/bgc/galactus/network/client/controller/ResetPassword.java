package org.bgc.galactus.network.client.controller;

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
import javafx.stage.Stage;
import org.bgc.galactus.network.client.controller.event.Login;

public class ResetPassword {
    private Stage primaryStage;
    private Scene scene;
    private GridPane grid;
    private Text notiFicationArea;
    private TextField userNameField;
    private PasswordField passwordField;

    ResetPassword(Stage primaryStage) {
        this.primaryStage = primaryStage;
        makeGrid();
        title();
        loginForm();
        setNotificationArea();
        buttons();
        makeScene();
    }

    void show (){
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void makeGrid() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
    }

    private void title() {
        Text scenetitle = new Text("Welcome");
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
        notiFicationArea = new Text();
        grid.add(notiFicationArea, 1, 6);
    }

    private void buttons() {
        Button loginBtn = new Button("Sign in");
        HBox hbLoginBtn = new HBox(10);
        loginBtn.setMinWidth(150);
        hbLoginBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbLoginBtn.getChildren().add(loginBtn);
        grid.add(hbLoginBtn, 1, 4);
        loginBtn.setOnAction(new Login(notiFicationArea, userNameField, passwordField));

        Button newAccountBtn = new Button("Create new account");
        HBox hbNewAccountBtn = new HBox(10);
        newAccountBtn.setMinWidth(150);
        hbNewAccountBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbNewAccountBtn.getChildren().add(newAccountBtn);
        grid.add(hbNewAccountBtn, 1, 5);
        newAccountBtn.setOnAction(new Login(notiFicationArea, userNameField, passwordField));

        Button resetPasswordBtn = new Button("Reset password");
        HBox hbResetPasswordBtn = new HBox(10);
        resetPasswordBtn.setMinWidth(150);
        hbResetPasswordBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbResetPasswordBtn.getChildren().add(resetPasswordBtn);
        grid.add(hbResetPasswordBtn, 1, 6);
        resetPasswordBtn.setOnAction(new Login(notiFicationArea, userNameField, passwordField));
    }

    private void makeScene() {
        //grid.setGridLinesVisible(true);
        scene = new Scene(grid, 300, 275);
    }
}
