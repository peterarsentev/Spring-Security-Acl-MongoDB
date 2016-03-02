package ru.mongo.acl.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.mongo.acl.repositories.ClientRepository;
import ru.mongo.acl.models.Client;

import java.util.List;

import static org.apache.commons.collections.IteratorUtils.toList;

@RestController
@RequestMapping(value = "/client")
public class ClientController implements ICRUDController<Client> {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientRepository clientRepository;

	@PreAuthorize("hasPermission(#client, 'create')")
    @Override
    public Client create(@RequestBody Client client) {
       return this.clientRepository.save(client);
    }

	@PreAuthorize("hasPermission(#client, 'update')")
    @Override
    public Client update(@RequestBody Client client) {
        return this.clientRepository.save(client);
    }

	@PreAuthorize("hasPermission(#client, 'delete')")
    @Override
    public void delete(@RequestBody Client client) {
        this.clientRepository.delete(client);
    }

    @Override
    public Client geById(String id) {
        return this.clientRepository.findOne(id);
    }

    @Override
    public List<Client> getAll() {
        return toList(this.clientRepository.findAll().iterator());
    }
}
