package ru.mongo.acl.client.gialogs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import ru.mongo.acl.client.ClientPage;
import ru.mongo.acl.shared.models.ClientDTO;
import ru.mongo.acl.shared.models.IClientDTO;

public class ClientDialog extends DialogBox {

    private static final ClientDialog INSTANCE = new ClientDialog();

    public static ClientDialog getInstance(){
        return INSTANCE;
    }

    private ClientDialog() {
        this.init();
    }

    private void init() {
        Button submit = new Button("Submit");
        Button cancel = new Button("Cancel");
        final TextBox login = new TextBox();
        submit.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                final IClientDTO client = new ClientDTO();
                client.setLogin(login.getText());

                RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, "api/client/");
                builder.setHeader("Content-Type", "application/json");
                try {
                    builder.sendRequest(client.toString(), new RequestCallback() {
                        public void onResponseReceived(Request request, Response response) {
                            ClientPage.getInstance().addClient(client);
                            hide();
                        }

                        @Override
                        public void onError(Request request, Throwable throwable) {
                        }
                    });
                } catch (RequestException e) {
                    GWT.log("error");
                }
            }
        });

        cancel.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                hide();
            }
        });

        FlexTable layout = new FlexTable();
        layout.setCellSpacing(6);
        layout.setText(0, 0, "Login:");
        layout.setWidget(0, 1, login);
        layout.setWidget(1, 0, submit);
        layout.setWidget(1, 1, cancel);
        this.add(layout);
        this.center();
    }
}
