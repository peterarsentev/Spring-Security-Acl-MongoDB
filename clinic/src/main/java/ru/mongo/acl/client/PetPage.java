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
import ru.mongo.acl.shared.models.PetDTO;

public class PetPage extends SimplePanel {
    final Grid grid = new Grid(1, 2);

    private static final PetPage INSTANCE = new PetPage();

    public static PetPage getInstance(){
        return INSTANCE;
    }

    private PetPage() {
        this.init();
        this.show(false);
        MainPanel.getInstance().setContent(this);
    }

    public void show(boolean enable) {
        this.setVisible(enable);
    }

    public void init() {
        grid.setStyleName("border");
        grid.getColumnFormatter().setWidth(0, "150px");
        grid.getColumnFormatter().setWidth(1, "150px");
        grid.getRowFormatter().addStyleName(0, "header");
        grid.setWidget(0, 0, new Label("Name"));
        grid.setWidget(0, 2, new Label("Action"));
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, "api/pet/") {
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
                        String login = value.get("login").isString().stringValue();
                        grid.setWidget(row, 0, new Label(login != null ? login : ""));
                        HorizontalPanel actions = new HorizontalPanel();
                        actions.setSpacing(5);
                        actions.add(buildBtn("edit", clickHandler(null)));
                        actions.add(buildBtn("delete", clickHandler(null)));
                        grid.setWidget(row, 1, actions);
                    }
                }
            });
        } catch (RequestException e) {
            GWT.log("error");
        }

        Button button = new Button("+ new pet");
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                ClientDialog.getInstance().show();
            }
        });
        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setSpacing(5);
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

    public Button buildBtn(String label, ClickHandler handler) {
        Button button = new Button(label);
        button.addClickHandler(handler);
        return button;
    }

    public ClickHandler clickHandler(String id) {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {

            }
        };
    }
}
