package ru.mongo.acl.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mongo.acl.models.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, String> {
}
