package ru.mongo.acl.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MainPage implements EntryPoint {

    private final CwConstants constants = GWT.create(CwConstants.class);

    public static interface CwConstants extends Constants {

        @DefaultStringValue("Password:")
        String cwDecoratorPanelFormDescription();

        @DefaultStringValue("Login:")
        String cwDecoratorPanelFormName();

        @DefaultStringValue("Authorization")
        String cwDecoratorPanelFormTitle();

    }


    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        // Create a table to layout the form options
        FlexTable layout = new FlexTable();
        layout.setCellSpacing(6);
        FlexTable.FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

        // Add a title to the form
        layout.setHTML(0, 0, constants.cwDecoratorPanelFormTitle());
        cellFormatter.setColSpan(0, 0, 2);
        cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

        // Add some standard form options
        layout.setHTML(1, 0, constants.cwDecoratorPanelFormName());
        layout.setWidget(1, 1, new TextBox());
        layout.setHTML(2, 0, constants.cwDecoratorPanelFormDescription());
        layout.setWidget(2, 1, new TextBox());
        layout.setWidget(3, 1, new Button("Submit"));

        // Wrap the content in a DecoratorPanel
        DecoratorPanel decPanel = new DecoratorPanel();
        decPanel.setWidget(layout);
        RootPanel.get("gwtContainer").add(decPanel);
    }

    public HorizontalPanel buildInput(String label, TextBox box) {
        HorizontalPanel hPanel = new HorizontalPanel();
        hPanel.setSpacing(5);
        hPanel.add(new Label(label));
        hPanel.add(box);
        return hPanel;
    }
}