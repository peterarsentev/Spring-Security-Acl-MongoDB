package ru.mongo.acl.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mongo.acl.server.models.Client;
import ru.mongo.acl.shared.models.Pet;
import ru.mongo.acl.server.repositories.PetRepository;

import java.util.List;

import static org.apache.commons.collections.IteratorUtils.toList;

@RestController
@RequestMapping(value = "/pet")
public class PetController implements IPetController {

    @Autowired
    private PetRepository petRepository;

    public Pet create(Pet pet) {
        return this.petRepository.save(pet);
    }

    public Pet update(Pet pet) {
        return this.petRepository.save(pet);
    }

    public void delete(Pet pet) {
        this.petRepository.delete(pet);
    }

    public List<Pet> getByOwner(@PathVariable String clientId) {
        return this.petRepository.findByOwner(new Client(clientId));
    }

    public Pet geById(@PathVariable String id) {
        return this.petRepository.findOne(id);
    }

    public List<Pet> getAll() {
        return toList(this.petRepository.findAll().iterator());
    }
}
