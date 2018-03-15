package org.transitclock.repository;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.transitclock.db.structs.PredictionAccuracy;

@RepositoryRestResource(path = "predictionaccuracy")
public interface PredictionAccuracyRepository extends JpaRepository<PredictionAccuracy, Long> {	
	
	List<PredictionAccuracy> findByRouteId(@Param("routeId") String routeId);
	
	List<PredictionAccuracy> findByStopId(@Param("stopId") String stopId);
	
	@Query("SELECT p FROM PredictionAccuracy p WHERE LOWER(p.routeId) = LOWER(:routeId) and p.predictionReadTime > :startDate and p.predictionReadTime < :endDate")
	List<PredictionAccuracy>findByRouteIdandDate(@Param("routeId") String routeId, 
			@DateTimeFormat(pattern="yyyy-MM-dd") @Param("startDate")Date startDate,
			@DateTimeFormat(pattern="yyyy-MM-dd") @Param("endDate")Date endDate);		
}
