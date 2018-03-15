package org.transitclock.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.transitclock.db.structs.VehicleEvent;
import org.transitclock.db.structs.VehicleEventId;

@RepositoryRestResource(path = "vehicleevent")
public interface VehicleEventRepository extends JpaRepository<VehicleEvent, VehicleEventId> {
	List<VehicleEvent> findByVehicleId(@Param("vehicleId") String vehicleId); 
}
