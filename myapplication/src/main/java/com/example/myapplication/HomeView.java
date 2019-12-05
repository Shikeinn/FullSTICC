package com.example.myapplication;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.*;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Theme("mytheme")
public class HomeView extends AbsoluteLayout implements View {

    public HomeView(Navigator navigator) throws SQLException {

        Connection dbConnection = MySqlCon.connect();

        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath + "/WEBINF/images/Hackers-Choice-Award72-1170x780.jpg"));
        Image image = new Image("Image from file",resource);
        image.setHeight("100%");
        image.setWidth("100%");
        HorizontalLayout imageLayout = new HorizontalLayout();
        imageLayout.addComponent(image);
        imageLayout.setWidth("100%");
        imageLayout.setHeight("100%");
        addComponent(imageLayout, "right: 25%; left: 25%; bottom: 50%");

        /* ----- Title Bar ----- */
        HorizontalLayout titleBar = new HorizontalLayout();
        titleBar.setWidth("100%");
        titleBar.setHeight("125px");
        titleBar.setMargin(new MarginInfo(true, true, false, true));
        addComponent(titleBar);

        Label logo = new Label("MTConnect");
        titleBar.addComponent(logo);
        titleBar.setExpandRatio(logo, 1.0f);

        HorizontalLayout navbar = new HorizontalLayout();

        Button homeBtn = new Button("Home");
        Button mapBtn = new Button("Map");
        homeBtn.setStyleName(ValoTheme.BUTTON_BORDERLESS);
        mapBtn.setStyleName(ValoTheme.BUTTON_BORDERLESS);
        navbar.addComponents(homeBtn, mapBtn);
        mapBtn.addClickListener(e -> {
            navigator.navigateTo("MapView");
        });
        titleBar.addComponent(navbar);
        titleBar.setExpandRatio(navbar, 1.0f);

        MenuBar.Command createEvent = new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                navigator.navigateTo("CreateEventView");
            }
        };

        MenuBar.Command logOut = new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getSession().close();
                getUI().getPage().setLocation("/");
            }
        };

        MenuBar profileMenu = new MenuBar();
        profileMenu.setStyleName(ValoTheme.BUTTON_BORDERLESS);
        titleBar.addComponent(profileMenu);
        MenuBar.MenuItem profile = profileMenu.addItem("Profile", null, null);
        profile.addItem("Create an Event", null, createEvent);
        profile.addItem("Logout", null, logOut);

        ComboBox searchBar = new ComboBox();
        searchBar.setStyleName("searchbox");
        searchBar.setWidth("50%");
        addComponent(searchBar, "top: 30%; left: 32.5%");

        HorizontalLayout upcoming = new HorizontalLayout();
        Label upcomingLabel = new Label("Upcoming Events");
        upcomingLabel.setStyleName("upcoming");

        upcoming.addComponent(upcomingLabel);
        addComponent(upcoming,"top: 52.5%; left: 15%");

        /* ----- GRID LAYOUT -----*/
        GridLayout events = new GridLayout(1,10);
        events.setWidth("100%");
        addComponent(events, "top: 58.5%; left: 20%; right: 20%");
        ArrayList<DataClasses.Event> eventList = Functions.pullDaysEvents("Kirksey Old Main" ,dbConnection);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        for(int x = 0; x < 6; x++) {
            HorizontalLayout eventLine = new HorizontalLayout();
            Label title = new Label(eventList.get(x).getEvent_name());
            title.setStyleName("title");
            Label date = new Label(eventList.get(x).getDate_of_event() );
            date.setStyleName("date");
            //Label date = new Label(dateFormat.format(eventList.get(x).getDate_of_event()));
            eventLine.addComponents(title);
            eventLine.addComponents(date);
            eventLine.setComponentAlignment(title, Alignment.MIDDLE_RIGHT);
            eventLine.setComponentAlignment(title, Alignment.MIDDLE_LEFT);
            eventLine.setWidth("80%");
            eventLine.addLayoutClickListener(event -> {
                Window description = new Window("Event Description");
                VerticalLayout windowContent = new VerticalLayout();
                description.setContent(windowContent);
                description.setModal(true);
                description.center();
                description.setWidth("500px");
                description.setHeight("500px");

                Label text = new Label("<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed " +
                        "do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
                        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure " +
                        "dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur " +
                        "sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est " +
                        "laborum.</p>");

                text.setContentMode(ContentMode.HTML);
                VerticalLayout textBox = new VerticalLayout();
                text.setWidth("390px");
                windowContent.addComponent(text);

                UI.getCurrent().addWindow(description);
            });
            events.addComponents(eventLine);
        }
    }
}
