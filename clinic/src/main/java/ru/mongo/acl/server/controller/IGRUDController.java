package ru.mongo.acl.server.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IGRUDController<T> {
    @PreAuthorize("hasPermission(#pet, 'create')")
    T create(T t);

    @PreAuthorize("hasPermission(#pet, 'update')")
    T update(T t);

    @PreAuthorize("hasPermission(#pet, 'delete')")
    void delete(T t);

    @PostFilter("hasPermission(filterObject, 'read')")
    List<T> getByOwner(@PathVariable String clientId);

    @PostAuthorize("hasPermission(returnObject, 'read')")
    T geById(@PathVariable String id);
}
