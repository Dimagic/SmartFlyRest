package net.ddns.dimag.smartflyrest.repo;

import net.ddns.dimag.smartflyrest.models.TourCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TourCategoryRepo extends CrudRepository<TourCategory, Long> {
}
