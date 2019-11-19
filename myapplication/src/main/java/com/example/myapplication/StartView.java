package com.example.myapplication;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.example.myapplication.NavigatorUI;

import static com.example.myapplication.NavigatorUI.MAINVIEW;

public class StartView extends VerticalLayout implements View {
    public StartView(Navigator navigator) {
        setSizeFull();
    }
}
