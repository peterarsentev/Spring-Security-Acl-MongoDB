package ru.mongo.acl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.mongo.acl.models.Client;
import ru.mongo.acl.models.Pet;
import ru.mongo.acl.repositories.PetRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/pet")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetService petService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasPermission(#pet, {'create'})")
    public Pet create(Pet pet) {
        return this.petRepository.save(pet);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @PreAuthorize("hasPermission(#pet, {'create'})")
    public Pet update(Pet pet) {
        return this.petRepository.save(pet);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @PreAuthorize("hasPermission(#pet, {'create'})")
    public void delete(Pet pet) {
        this.petRepository.delete(pet);
    }

    @RequestMapping(value = "/list/{clientId}", method = RequestMethod.GET)
    @PostFilter("hasPermission(filterObject, {'read'})")
    public List<Pet> getByOwner(@PathVariable String clientId) {
        return this.petService.findByOwner(clientId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PostAuthorize("hasPermission(returnObject, {'read'})")
    public Pet geById(@PathVariable String id) {
        return new Pet();//this.petRepository.findOne(id);
    }
}
