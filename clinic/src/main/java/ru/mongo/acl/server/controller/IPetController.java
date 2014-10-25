package ru.mongo.acl.server.controller;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.mongo.acl.shared.models.Pet;

import java.util.List;

public interface IPetController extends ICRUDController<Pet> {

    @RequestMapping(value = "/list/{clientId}", method = RequestMethod.GET)
    @PostFilter("hasPermission(filterObject, 'read')")
    List<Pet> getByOwner(@PathVariable String clientId);
}