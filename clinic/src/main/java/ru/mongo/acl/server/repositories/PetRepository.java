package ru.mongo.acl.server.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mongo.acl.shared.models.Client;
import ru.mongo.acl.shared.models.Pet;

import java.util.List;

@Repository
public interface PetRepository extends CrudRepository<Pet, String> {
    List<Pet> findByOwner(Client client);
}
