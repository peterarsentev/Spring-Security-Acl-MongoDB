package ru.mongo.acl.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Repository;
import ru.mongo.acl.models.Client;
import ru.mongo.acl.models.Pet;

import java.util.List;

@Repository
public interface PetRepository extends CrudRepository<Pet, String> {
    List<Pet> findByOwner(Client client);
}
