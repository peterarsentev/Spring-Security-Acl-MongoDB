package ru.mongo.acl.server.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mongo.acl.server.models.Client;
import ru.mongo.acl.server.models.Pet;
import ru.mongo.acl.shared.models.IPetDTO;

import java.util.List;

@Repository
public interface PetRepository extends CrudRepository<Pet, String> {
}
