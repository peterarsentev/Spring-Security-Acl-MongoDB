package ru.mongo.acl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.mongo.acl.models.Client;
import ru.mongo.acl.models.Pet;
import ru.mongo.acl.repositories.PetRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class PetService {

    @PostAuthorize("hasPermission(returnObject, {'read'})")
    public List<Pet> findByOwner(String id) {
        return Arrays.asList(new Pet("1"), new Pet("2"));
    }


}
