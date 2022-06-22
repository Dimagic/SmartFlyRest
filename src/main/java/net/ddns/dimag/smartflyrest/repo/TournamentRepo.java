package net.ddns.dimag.smartflyrest.repo;

import net.ddns.dimag.smartflyrest.models.Tournament;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TournamentRepo extends CrudRepository<Tournament, Long> {
}
