package net.ddns.dimag.smartflyrest.repo;

import net.ddns.dimag.smartflyrest.models.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface TeamRepo extends CrudRepository<Team, Long> {
}
