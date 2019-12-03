package com.example.myapplication;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@PreserveOnRefresh
@Theme("mytheme")
public class NavigatorUI extends UI {
    public Navigator navigator;
    protected static final String MAINVIEW = "main";

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("MTConnect");

        // Create a navigator to control the views
        navigator = new Navigator(this, this);

        // Create and register the views
        LoginView login = new LoginView(navigator);
        navigator.addView("", login);
        try {
            navigator.addView(MAINVIEW, new HomeView(navigator));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        navigator.addView("MapView", new MapView(navigator));
        try {
            navigator.addView("SignUp", new SignUpView(navigator));
        } catch (SQLException ex) {
            Logger.getLogger(NavigatorUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            navigator.addView("CreateEventView", new CreateEventView(navigator));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        navigator.addView("blank", new StartView(navigator));

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = NavigatorUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
