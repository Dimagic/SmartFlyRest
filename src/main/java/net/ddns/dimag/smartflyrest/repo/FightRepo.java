package net.ddns.dimag.smartflyrest.repo;

import net.ddns.dimag.smartflyrest.models.Fight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FightRepo extends CrudRepository<Fight, Long> {
}
