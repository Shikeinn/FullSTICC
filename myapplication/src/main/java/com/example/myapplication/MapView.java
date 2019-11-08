package com.example.myapplication;


import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import static com.example.myapplication.NavigatorUI.MAINVIEW;

public class MapView extends AbsoluteLayout implements View {
    private final String apiKey = "AIzaSyDv0zoPcZ8BT_7PC1v_-gN6xyiAS0FTVhw";
    public MapView(Navigator navigator){
        setSizeFull();

        //   Panel layout = new Panel();
        GoogleMap googleMap = new GoogleMap(apiKey, null, "english");
        googleMap = new GoogleMap(apiKey, null, null);
        googleMap.setZoom(10);
        googleMap.setSizeFull();
        googleMap.setMinZoom(4);
        googleMap.setMaxZoom(16);
        googleMap.setCenter(new LatLon(35.849778, -86.362778));
        googleMap.setZoom(70);
        //     layout.setSizeFull();
        //   layout.setContent(googleMap);
        addComponent(googleMap);

        HorizontalLayout titleBarColor = new HorizontalLayout();
        titleBarColor.setWidth("100%");
        titleBarColor.setHeight("10%");
        //    titleBar.setMargin(new MarginInfo(true, true, false, true));
        addComponent(titleBarColor);

        HorizontalLayout titleBar = new HorizontalLayout();
        titleBar.setWidth("100%");
        titleBar.setHeight("10%");
        titleBar.setMargin(new MarginInfo(true, true, false, true));
        addComponent(titleBar);


        Label logo = new Label("MTConnect");
      //  logo.setContentMode(ContentMode.HTML);
        titleBar.addComponent(logo);
        titleBar.setExpandRatio(logo, 1.0f);

        HorizontalLayout navbar = new HorizontalLayout();
        Button homeBtn = new Button("Home");
        Button mapBtn = new Button("Map");
        homeBtn.setStyleName(ValoTheme.BUTTON_BORDERLESS);
        mapBtn.setStyleName(ValoTheme.BUTTON_BORDERLESS);
        navbar.addComponents(homeBtn, mapBtn);
        homeBtn.addClickListener(e -> {
            navigator.navigateTo(MAINVIEW);
        });
        titleBar.addComponent(navbar);
        titleBar.setExpandRatio(navbar, 1.0f);



    }
}
