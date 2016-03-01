package ru.mongo.acl.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface ICRUDController<T> {
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasPermission(#t, 'create')")
    T create(T t);

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @PreAuthorize("hasPermission(#t, 'update')")
    T update(T t);

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @PreAuthorize("hasPermission(#t, 'delete')")
    void delete(T t);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PostAuthorize("hasPermission(returnObject, 'read')")
    T geById(@PathVariable String id);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @PostFilter("hasPermission(filterObject, 'read')")
    List<T> getAll();
}
