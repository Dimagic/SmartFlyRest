package net.ddns.dimag.smartflyrest.repo;

import net.ddns.dimag.smartflyrest.models.Competitor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface CompetitorRepo extends CrudRepository<Competitor, Long> {
}
