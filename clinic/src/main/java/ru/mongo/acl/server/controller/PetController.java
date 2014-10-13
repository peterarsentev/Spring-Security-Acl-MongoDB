package ru.mongo.acl.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.mongo.acl.shared.models.Client;
import ru.mongo.acl.shared.models.Pet;
import ru.mongo.acl.server.repositories.PetRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/pet")
public class PetController implements IGRUDController<Pet> {

    @Autowired
    private PetRepository petRepository;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Pet create(Pet pet) {
        return this.petRepository.save(pet);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Pet update(Pet pet) {
        return this.petRepository.save(pet);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public void delete(Pet pet) {
        this.petRepository.delete(pet);
    }

    @RequestMapping(value = "/list/{clientId}", method = RequestMethod.GET)
    public List<Pet> getByOwner(@PathVariable String clientId) {
        return this.petRepository.findByOwner(new Client(clientId));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Pet geById(@PathVariable String id) {
        return this.petRepository.findOne(id);
    }
}
