package ru.mongo.acl.server.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mongo.acl.server.models.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, String> {
}
