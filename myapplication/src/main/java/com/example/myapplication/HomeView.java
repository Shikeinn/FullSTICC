package com.example.myapplication;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@Theme("mytheme")
public class HomeView extends AbsoluteLayout implements View {

    public HomeView(Navigator navigator){

        /* ----- Title Bar ----- */
        HorizontalLayout titleBar = new HorizontalLayout();
        titleBar.setWidth("100%");
        titleBar.setHeight("150");
        titleBar.setMargin(new MarginInfo(true, true, false, true));
        addComponent(titleBar);

        Label logo = new Label("<h2>MTConnect</h2>");
        logo.setContentMode(ContentMode.HTML);
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

        MenuBar profileMenu = new MenuBar();
        profileMenu.setStyleName(ValoTheme.BUTTON_BORDERLESS);
        titleBar.addComponent(profileMenu);
        MenuBar.MenuItem profile = profileMenu.addItem("Profile", null, null);
        profile.addItem("Create an Event", null, createEvent);


        ComboBox searchBar = new ComboBox();
        searchBar.setStyleName("searchbox");
        searchBar.setWidth("75%");
        addComponent(searchBar, "top: 30%; left: 20%");


        /* ----- GRID LAYOUT -----*/
        GridLayout events = new GridLayout(4,1);
        events.setWidth("100%");
        addComponent(events, "top: 500px");

        // Packaging up one poster
        VerticalLayout eventpackage1 = new VerticalLayout();
        Image poster = new Image("",
                new ExternalResource("https://about.canva.com/wp-content/uploads/sites/3/2015/01/concert_poster.png"));
        poster.setWidth("200px");
        poster.setHeight("266px");
        Label title = new Label("Jazz Concert");
        eventpackage1.addComponents(poster, title);
        eventpackage1.addLayoutClickListener(event -> {
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
        VerticalLayout eventpackage2 = new VerticalLayout();
        poster = new Image("",
                new ExternalResource("https://about.canva.com/wp-content/uploads/sites/3/2015/01/volleyball_poster.png"));
        poster.setWidth("200px");
        poster.setHeight("266px");
        title = new Label("Beach Volleyball");
        eventpackage2.addComponents(poster, title);
        eventpackage2.addLayoutClickListener(event -> {
            Window description = new Window("Event Description");
            VerticalLayout windowContent = new VerticalLayout();
            description.setContent(windowContent);
            description.setModal(true);
            description.center();
            description.setWidth("390px");
            description.setHeight("400px");

            Label text = new Label("<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed " +
                    "do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
                    "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure " +
                    "dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur " +
                    "sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est " +
                    "laborum.</p>");
            text.setContentMode(ContentMode.HTML);
            text.setWidth("400px");
            windowContent.addComponent(text);;


            UI.getCurrent().addWindow(description);
        });
        events.addComponents(eventpackage1, eventpackage2);






    }
}
