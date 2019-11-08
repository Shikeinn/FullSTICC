package com.example.myapplication;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;

import com.vaadin.ui.*;

import com.example.myapplication.DataClasses.User;
import com.example.myapplication.Functions;
import com.example.myapplication.MySqlCon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class LoginView extends VerticalLayout implements View {

    public LoginView(Navigator navigator){

        final Window login = new Window();
        
        Connection dbConnection = MySqlCon.connect();
        
        final TextField username = new TextField();
        username.setCaption("Username");

        final PasswordField password = new PasswordField("Password");
        password.setCaption("Password");
        

        Button loginButton = new Button("Login");
        loginButton.addClickListener(e ->{
            String name = username.getValue();
            String pw = password.getValue();
            User user = null;
            try {
                user = Functions.pullUserData(name, pw, dbConnection);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            if(user == null){
        	//display error message
        }
        else {
        	UI.getCurrent().removeWindow(login);
            navigator.navigateTo("main");
        }});


        Button signUp = new Button("Sign Up");
        signUp.addClickListener(e -> {
            UI.getCurrent().removeWindow(login);
            navigator.navigateTo("SignUp");
        });
        signUp.setWidth("100%");

        // Bottom buttons
        HorizontalLayout btnLayout = new HorizontalLayout();
        VerticalLayout layout = new VerticalLayout();
        btnLayout.addComponents(loginButton, signUp);

        Label logo = new Label("<h2>MTConnect</h2>");
        logo.setContentMode(ContentMode.HTML);
        layout.addComponent(logo);
        layout.addComponents(username, password, btnLayout);

        // Alignment
        layout.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(username, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(password, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(btnLayout, Alignment.MIDDLE_CENTER);


        login.setContent(layout);
        login.center();
        login.setWidth(300f, Unit.PIXELS);
        login.setHeight(300f, Unit.PIXELS);
        login.setModal(true);
        login.setClosable(false);
        login.setDraggable(false);
        login.setResizable(false);

        UI.getCurrent().addWindow(login);
        login.bringToFront();
    }
}
