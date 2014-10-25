package ru.mongo.acl.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.*;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.*;


public class LoginPage extends SimplePanel {
    private final CwConstants constants = GWT.create(CwConstants.class);

    public static interface CwConstants extends Constants {

        @DefaultStringValue("Password:")
        String cwDecoratorPanelFormDescription();

        @DefaultStringValue("Login:")
        String cwDecoratorPanelFormName();

        @DefaultStringValue("Authorization")
        String cwDecoratorPanelFormTitle();

    }

    private static final LoginPage INSTANCE = new LoginPage();

    public static LoginPage getInstance(){
        return INSTANCE;
    }

    private LoginPage() {
        this.init();
        RootPanel.get().add(this);
    }

    public void show(boolean enable) {
        this.setVisible(enable);
    }

    private void init() {
        FlexTable layout = new FlexTable();
        layout.setCellSpacing(6);
        FlexTable.FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

        layout.setHTML(0, 0, constants.cwDecoratorPanelFormTitle());
        cellFormatter.setColSpan(0, 0, 2);
        cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
        Button button = new Button("Submit");
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, "j_spring_security_check?j_username=admin&j_password=password") {
                    @Override
                    public Request sendRequest(String requestData, RequestCallback callback) throws RequestException {
                        return super.sendRequest(requestData, callback);
                    }
                };

                try {
                    builder.sendRequest(null, new RequestCallback() {
                        public void onError(Request request, Throwable exception) {
                            GWT.log("error");
                        }

                        public void onResponseReceived(Request request, Response response) {
                            show(false);
                            ClientPage.getInstance().show(true);
                        }
                    });
                } catch (RequestException e) {
                    GWT.log("error");
                }
            }
        });
        layout.setHTML(1, 0, constants.cwDecoratorPanelFormName());
        layout.setWidget(1, 1, new TextBox());
        layout.setHTML(2, 0, constants.cwDecoratorPanelFormDescription());
        layout.setWidget(2, 1, new TextBox());
        layout.setWidget(3, 1, button);
        this.setWidget(layout);
    }
}
