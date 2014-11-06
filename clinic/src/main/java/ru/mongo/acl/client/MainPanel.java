package ru.mongo.acl.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

public class MainPanel extends SimplePanel {
    private HorizontalPanel mainPanel = new HorizontalPanel();
    private VerticalPanel control = new VerticalPanel();
    private SimplePanel content = new SimplePanel();

    private Anchor showClients = new Anchor("Show clients");
    private Anchor showPets = new Anchor("Show pets");
    private Anchor showAcls = new Anchor("Show pets");

    private static final MainPanel INSTANCE = new MainPanel();

    public static MainPanel getInstance(){
        return INSTANCE;
    }

    private MainPanel() {
        this.init();
        RootPanel.get().add(this);
    }

    private void init() {
        mainPanel.setStyleName("center");
        mainPanel.add(control);
        mainPanel.setCellWidth(control,"150px");
        mainPanel.add(content);
        control.setSpacing(5);
        control.add(showClients);
        control.add(showPets);
        control.add(showAcls);
        showClients.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                ClientPage.getInstance().show(true);
            }
        });
        RootPanel.get().add(mainPanel);
    }

    public void setContent(Widget widget) {
        this.content.setWidget(widget);
    }

    public void show(boolean enable) {
        this.setVisible(enable);
    }
}
