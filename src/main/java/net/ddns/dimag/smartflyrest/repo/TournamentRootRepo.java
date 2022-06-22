package net.ddns.dimag.smartflyrest.repo;

import net.ddns.dimag.smartflyrest.models.TournamentRoot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TournamentRootRepo extends CrudRepository<TournamentRoot, Long> {
}
