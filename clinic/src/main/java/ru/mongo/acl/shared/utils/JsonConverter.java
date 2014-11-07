package ru.mongo.acl.shared.utils;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.*;
import ru.mongo.acl.shared.models.ClientDTO;
import ru.mongo.acl.shared.models.IClientDTO;
import ru.mongo.acl.shared.models.IPetDTO;

public class JsonConverter {
    interface ClientFactory extends AutoBeanFactory {
        AutoBean<IClientDTO> client(ClientDTO dto);

        AutoBean<IPetDTO> pet(IPetDTO dto);
    }

    public static String serializeToJson(ClientDTO clientDTO) {
        ClientFactory factory = GWT.create(ClientFactory.class);
        AutoBean<IClientDTO> autoBean = factory.client(clientDTO);
        Splittable splittable = AutoBeanCodex.encode(autoBean);
        return splittable.getPayload();
    }



    public static String serializeToJson(IPetDTO petDTO) {
        ClientFactory factory = GWT.create(ClientFactory.class);
        AutoBean<IPetDTO> autoBean = factory.pet(petDTO);
        Splittable splittable = AutoBeanCodex.encode(autoBean);
        return splittable.getPayload();
    }
}
