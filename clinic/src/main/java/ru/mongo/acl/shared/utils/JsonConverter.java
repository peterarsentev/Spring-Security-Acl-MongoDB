package ru.mongo.acl.shared.utils;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.*;
import ru.mongo.acl.shared.models.ClientDTO;
import ru.mongo.acl.shared.models.IClientDTO;
import ru.mongo.acl.shared.models.IPetDTO;

public class JsonConverter {
    private static final ClientFactory factory = GWT.create(ClientFactory.class);

    private static final ClientDTOFactory factoryDTO = GWT.create(ClientDTOFactory.class);

    interface ClientFactory extends AutoBeanFactory {
        AutoBean<IClientDTO> client(ClientDTO dto);
        AutoBean<IPetDTO> pet(IPetDTO dto);
    }

    interface ClientDTOFactory extends AutoBeanFactory {
        AutoBean<IClientDTO> client();
    }

    public static String serializeToJson(ClientDTO clientDTO) {
        AutoBean<IClientDTO> autoBean = factory.client(clientDTO);
        Splittable splittable = AutoBeanCodex.encode(autoBean);
        return splittable.getPayload();
    }

    public static  IClientDTO deserializeFromJson(String json) {
        AutoBean<IClientDTO> bean = AutoBeanCodex.decode(factoryDTO, IClientDTO.class, json);
        return bean.as();
    }

    public static String serializeToJson(IPetDTO petDTO) {
        ClientFactory factory = GWT.create(ClientFactory.class);
        AutoBean<IPetDTO> autoBean = factory.pet(petDTO);
        Splittable splittable = AutoBeanCodex.encode(autoBean);
        return splittable.getPayload();
    }
}
