package net.ddns.dimag.smartflyrest.repo;

import net.ddns.dimag.smartflyrest.models.TourPair;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TourPairRepo extends CrudRepository<TourPair, Long> {
}
