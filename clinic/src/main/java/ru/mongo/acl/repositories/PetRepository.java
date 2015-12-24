package ru.mongo.acl.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mongo.acl.models.Pet;

@Repository
public interface PetRepository extends CrudRepository<Pet, String> {
}
