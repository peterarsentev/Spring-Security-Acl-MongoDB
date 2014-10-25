package ru.mongo.acl.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.*;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.client.ui.*;
import ru.mongo.acl.client.gialogs.ClientDialog;
import ru.mongo.acl.shared.models.IClientDTO;

public class ClientPage extends SimplePanel {
    final Grid grid = new Grid(1, 2);

    private static final ClientPage INSTANCE = new ClientPage();

    public static ClientPage getInstance(){
        return INSTANCE;
    }

    private ClientPage() {
        this.init();
        this.show(false);
        RootPanel.get().add(this);
    }

    public void show(boolean enable) {
        this.setVisible(enable);
    }

    public void init() {
        grid.setWidget(0, 0, new Label("login"));
        grid.setWidget(0, 1, new Label("pet"));
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, "api/client/") {
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
                    JSONArray array = JSONParser.parseStrict(response.getText()).isArray();
                    for (int i = 0; i != array.size(); i++) {
                        int row = grid.getRowCount();
                        grid.insertRow(row);
                        JSONObject value = array.get(i).isObject();
                        grid.setWidget(row, 0, new Label(value.get("login").isString().stringValue()));
                        grid.setWidget(row, 1, new Label("0"));
                    }
                }
            });
        } catch (RequestException e) {
            GWT.log("error");
        }

        Button button = new Button("Add");
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                ClientDialog.getInstance().show();
            }
        });
        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.add(grid);
        verticalPanel.add(button);
        this.add(verticalPanel);
    }

    public void addClient(IClientDTO client) {
        int row = grid.getRowCount();
        grid.insertRow(row);
        grid.setWidget(row, 0, new Label(client.getLogin()));
        grid.setWidget(row, 1, new Label("0"));
    }
}
