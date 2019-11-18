package com.example.myapplication;

import com.example.myapplication.DataClasses.User;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import java.sql.Connection;
import java.sql.SQLException;
import jdk.nashorn.internal.ir.WhileNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignUpView extends VerticalLayout implements View {

    public SignUpView(Navigator navigator) throws SQLException{
        final VerticalLayout layout = new VerticalLayout();
        
        Connection dbConnection = MySqlCon.connect();
        // Title Bar
        HorizontalLayout titleBar = new HorizontalLayout();
        titleBar.setWidth("100%");
        addComponent(titleBar);
        Label logo = new Label("<h2>MTConnect</h2>");
        logo.setContentMode(ContentMode.HTML);
        titleBar.addComponent(logo);

        TextField name_f = new TextField("Full Name");
        TextField mNumber = new TextField("M#");
        TextField email = new TextField("Email");
        TextField password = new TextField("Password");


        // Create Major Selection

        List<String> majorList = Arrays.asList("Computer Science", "Mathematics");
        final ComboBox<String> major = new ComboBox<String>("Major", majorList);
        major.setPlaceholder("Please select your major");
        major.setEmptySelectionAllowed(false);
        major.setWidth(300f, Unit.PIXELS);

        // Create Interest Selection
        ArrayList<String> interestList = Functions.get_interest_names(dbConnection);
        final ComboBox interest = new ComboBox("Interest One", interestList);
        interest.setPlaceholder("Please select an interest");
        interest.setEmptySelectionAllowed(false);
        interest.setWidth(300f, Unit.PIXELS);

        // Create Club Selection
        ArrayList<String> clubList = Functions.get_club_names(dbConnection);
        final ComboBox<String> club = new ComboBox<String>("Club One", clubList);
        club.setPlaceholder("Please select a club");
        club.setWidth(300f, Unit.PIXELS);

        //add push to user function here and check if complete resend if null
        Button cont = new Button("Continue");
        cont.addClickListener(e -> {
           User user = new User();
           user.name_f = name_f.getValue();
           user.m_number = mNumber.getValue();
           user.email = email.getValue();
           user.pw = password.getValue();
           user.major = major.getValue();
           user.interest_1 = (String) interest.getValue();
           user.club_sk_1 = club.getValue();
           
            try {
                Functions.pushUsertoDatabase(user, dbConnection);
            } catch (SQLException ex) {
                Logger.getLogger(SignUpView.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           navigator.navigateTo("main");
           Notification.show("Sign Up Successful");
        });

        Button cancel = new Button("Cancel");
        cancel.addClickListener(e -> {
            new LoginView(navigator);
            navigator.navigateTo("Login");
        });
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponents(cont, cancel);
        layout.addComponents(name_f, mNumber, email, password, major, interest, club, buttons);
        addComponent(layout);

        setComponentAlignment(layout, Alignment.MIDDLE_CENTER);

    }
}
