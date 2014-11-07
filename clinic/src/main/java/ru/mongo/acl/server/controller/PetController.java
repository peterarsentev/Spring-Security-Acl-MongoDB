package ru.mongo.acl.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mongo.acl.server.models.Client;
import ru.mongo.acl.server.models.Pet;
import ru.mongo.acl.server.repositories.ClientRepository;
import ru.mongo.acl.server.repositories.PetRepository;
import ru.mongo.acl.shared.models.IPetDTO;

import java.util.List;

import static org.apache.commons.collections.IteratorUtils.toList;

@RestController
@RequestMapping(value = "/pet")
public class PetController implements IPetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Pet create(@RequestBody Pet pet) {
        this.petRepository.save(pet);
        Client client = this.clientRepository.findOne(pet.getClientId());
        client.getPets().add(pet);
        return pet;
    }

    public Pet update(@RequestBody Pet pet) {
        return this.petRepository.save(pet);
    }

    public void delete(@RequestBody Pet pet) {
        this.petRepository.delete(pet);
    }

    public Pet geById(@PathVariable String id) {
        return this.petRepository.findOne(id);
    }

    public List<Pet> getAll() {
        return toList(this.petRepository.findAll().iterator());
    }
}
