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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.sql.Timestamp;

public class CreateEventView extends VerticalLayout implements View {

    public CreateEventView(Navigator navigator) throws SQLException, ParseException{
        final VerticalLayout layout = new VerticalLayout();

        Connection dbConnection = MySqlCon.connect();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Title Bar
        HorizontalLayout titleBar = new HorizontalLayout();
        titleBar.setWidth("100%");
        addComponent(titleBar);
        Label logo = new Label("<h2>MTConnect</h2>");
        logo.setContentMode(ContentMode.HTML);
        titleBar.addComponent(logo);

        TextField eventName = new TextField("Event Name");
        eventName.setWidth(300f, Unit.PIXELS);
        
        TextField date2 = new TextField("Date (yyyy-mm-dd HH:mm:ss)"); //Outputs yyyy-MM-ddTHH:MM
        date2.setWidth(300f, Unit.PIXELS);
        
        TextField duration = new TextField("Duration of Event");
        duration.setPlaceholder("'30 minutes'/'1 hour'/etc.");
        duration.setWidth(300f, Unit.PIXELS);

        List<String> eventTypeList = Arrays.asList("Hiking", "Languages");
        final ComboBox<String> eventType = new ComboBox<String>("Event Type", eventTypeList);
        eventType.setPlaceholder("Please select an Event Type");
        eventType.setEmptySelectionAllowed(false);
        eventType.setWidth(300f, Unit.PIXELS);

        // Create Interest Selection
        List<String> interestList = Functions.get_interest_names(dbConnection);
        final ComboBox<String> interest = new ComboBox<String>("Interest", interestList);
        interest.setPlaceholder("Please select an interest");
        interest.setEmptySelectionAllowed(false);
        interest.setWidth(300f, Unit.PIXELS);

        // Create Club Selection
        List<String> clubList = Functions.get_club_names(dbConnection);
        final ComboBox<String> club = new ComboBox<String>("Host Club", clubList);
        club.setPlaceholder("Please select a club");
        club.setWidth(300f, Unit.PIXELS);

        // Create Location Selection
        List<String> locList = Functions.get_location_names(dbConnection);
        final ComboBox<String> location = new ComboBox<String>("Select a location", locList);
        location.setPlaceholder("Please select a location");
        location.setWidth(300f, Unit.PIXELS);

        TextArea description = new TextArea("Event Description");
        description.setHeight(200f, Unit.PIXELS);
        description.setWidth(300f, Unit.PIXELS);

        Button cont = new Button("Continue");
        cont.addClickListener(e -> {
            DataClasses.Event event = new DataClasses.Event();
            event.event_name = eventName.getValue();
 
            try {
                //java.util.Date date = new java.util.Date()
                //java.sql.Date sqlDate = new java.sql.Date(date.getTime())
                java.util.Date date = formatter.parse(date2.getValue());
                Timestamp ts = new Timestamp(date.getTime());
                //java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
                event.date_of_event = ts;
            } catch (ParseException ex) {
                Logger.getLogger(CreateEventView.class.getName()).log(Level.SEVERE, null, ex);
            }
 
            event.duration = duration.getValue();
            event.interest_name = interest.getValue();
            event.club_name = club.getValue();
            event.location_name = location.getValue();
            event.event_desc = description.getValue();
            
            try {
                String pass = Functions.pushEventtoDatabase(event, dbConnection);
                
                if(pass.equals("Failure"))
                {
                    Notification.show("Invalid entries for Event Creation");
                    navigator.navigateTo("CreateEvent");
                }
                else
                {
                   navigator.navigateTo("main");
                   Notification.show("Event Creation Successful"); 
                }
            } catch (SQLException ex) {
                Logger.getLogger(CreateEventView.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });

        Button cancel = new Button("Cancel");
        cancel.addClickListener(e -> {
            navigator.navigateTo("blank");
            new LoginView(navigator);
        });
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponents(cont, cancel);
        layout.addComponents(eventName, date2, duration, location, eventType, interest, club, description, buttons);
        addComponent(layout);

        setComponentAlignment(layout, Alignment.MIDDLE_CENTER);

    }
}
