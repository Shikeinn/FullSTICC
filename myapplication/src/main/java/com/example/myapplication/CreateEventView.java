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

//import javax.xml.soap.Text;
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
        eventName.setWidth(300f, Unit.PIXELS);
        TextField date2 = new TextField("Date");  //Outputs yyyy-MM-ddTHH:MM
        date2.setPlaceholder("yyyy-mm-dd HH:mm:ss");
        date2.setWidth(300f, Unit.PIXELS);
        TextField duration = new TextField("Duration of Event");
        duration.setPlaceholder("'30 minutes'/'1 hour'/etc.");
        duration.setWidth(300f, Unit.PIXELS);


        // Create Interest Selection
        List<String> interestList = Arrays.asList("Hiking", "Languages");
        final ComboBox<String> interest = new ComboBox<String>("Interest", interestList);
        interest.setPlaceholder("Please select an interest");
        interest.setEmptySelectionAllowed(false);
        interest.setWidth(300f, Unit.PIXELS);

        // Create Club Selection
        List<String> clubList = Arrays.asList("ACM", "American Sign Language", "Cinema Club");
        final ComboBox<String> club = new ComboBox<String>("Host Club", clubList);
        club.setPlaceholder("Please select a club");
        club.setWidth(300f, Unit.PIXELS);

        // Create Location Selection
        List<String> locList = Arrays.asList("KOM", "Your Mom's House", "Student Union", "Walker Library");
        final ComboBox<String> location = new ComboBox<String>("Select a location", locList);
        location.setPlaceholder("Please select a location");
        location.setWidth(300f, Unit.PIXELS);

        TextField description = new TextField("Event Description");
        description.setHeight(200f, Unit.PIXELS);
        description.setWidth(300f, Unit.PIXELS);

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
        layout.addComponents(eventName, date2, duration, location, interest, club, description, buttons);
        addComponent(layout);

        setComponentAlignment(layout, Alignment.MIDDLE_CENTER);

    }
}
