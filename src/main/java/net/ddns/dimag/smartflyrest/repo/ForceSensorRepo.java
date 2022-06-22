package net.ddns.dimag.smartflyrest.repo;

import net.ddns.dimag.smartflyrest.models.ForceSensor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface ForceSensorRepo extends CrudRepository<ForceSensor, Long> {
    @Query("SELECT u FROM ForceSensor u WHERE u.mac = ?1")
    Optional<ForceSensor> findSensorByMac(String mac);
}
