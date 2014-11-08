package ru.mongo.acl.client.gialogs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import ru.mongo.acl.shared.models.PetDTO;

import static ru.mongo.acl.shared.utils.JsonConverter.serializeToJson;

public class PetDialog extends DialogBox {

    private static final PetDialog INSTANCE = new PetDialog();

    private String clientId;

    public static PetDialog getInstance(){
        return INSTANCE;
    }

    private PetDialog() {
        this.init();
    }

    private void init() {
        this.setText("Create the new pet");
        Button submit = new Button("Submit");
        Button cancel = new Button("Cancel");
        final TextBox name = new TextBox();
        submit.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                PetDTO petDTO = new PetDTO();
                petDTO.setName(name.getText());
                petDTO.setClientId(clientId);
                RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, "api/pet/");
                builder.setHeader("Content-Type", "application/json");
                try {
                    builder.sendRequest(serializeToJson(petDTO), new RequestCallback() {
                        public void onResponseReceived(Request request, Response response) {
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
        layout.setText(0, 0, "Name:");
        layout.setWidget(0, 1, name);
        layout.setWidget(1, 0, submit);
        layout.setWidget(1, 1, cancel);
        this.add(layout);
        this.center();
    }

    public void show(String clientId) {
        this.clientId = clientId;
        this.show();
    }
}
