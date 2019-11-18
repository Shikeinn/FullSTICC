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
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class CreateEventView extends VerticalLayout implements View {

    public CreateEventView(Navigator navigator){
        final VerticalLayout layout = new VerticalLayout();

        // Title Bar
        HorizontalLayout titleBar = new HorizontalLayout();
        titleBar.setWidth("100%");
        addComponent(titleBar);
        Label logo = new Label("<h2>MTConnect</h2>");
        logo.setContentMode(ContentMode.HTML);
        titleBar.addComponent(logo);

        TextField eventName = new TextField("Event Name");
        DateField date = new DateField("Date");
        date.getData();
        DateTimeField date2 = new DateTimeField("Date");



        // Create Major Selection
        List<String> majorList = Arrays.asList("Computer Science", "Mathematics");
        final ComboBox<String> major = new ComboBox<String>("Major", majorList);
        major.setPlaceholder("Please select your major");
        major.setEmptySelectionAllowed(false);
        major.setWidth(300f, Unit.PIXELS);

        // Create Interest Selection
        List<String> interestList = Arrays.asList("Hiking", "Languages");
        final ComboBox<String> interest = new ComboBox<String>("Interest One", interestList);
        interest.setPlaceholder("Please select an interest");
        interest.setEmptySelectionAllowed(false);
        interest.setWidth(300f, Unit.PIXELS);

        // Create Club Selection
        List<String> clubList = Arrays.asList("ACM", "American Sign Language", "Cinema Club");
        final ComboBox<String> club = new ComboBox<String>("Club One", clubList);
        club.setPlaceholder("Please select a club");
        club.setWidth(300f, Unit.PIXELS);

        Button cont = new Button("Continue");
        cont.addClickListener(e -> {
            navigator.navigateTo("main");
            Notification.show("Sign Up Successful");
        });

        Button cancel = new Button("Cancel");
        cancel.addClickListener(e -> {
            System.out.println(date2.getValue().toString());
        });
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponents(cont, cancel);
        layout.addComponents(eventName, date, date2, major, interest, club, buttons);
        addComponent(layout);

        setComponentAlignment(layout, Alignment.MIDDLE_CENTER);
    }
}
