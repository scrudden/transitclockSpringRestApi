package org.transitclock.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.transitclock.db.structs.PredictionForStopPath;

@RepositoryRestResource(path = "stoppathprediction")
public interface StopPathPredictionRepository extends JpaRepository<PredictionForStopPath, Long> {	
	
	@Query("SELECT p FROM PredictionForStopPath p WHERE p.travelTime = :travelTime and p.stopPathIndex = :stopPathIndex and MOD(p.id, 10) = 0")
	List<PredictionForStopPath> findByStopPathIndex(@Param("stopPathIndex") Integer stopPathIndex, @Param("travelTime") Boolean travelTime);
	
	@Query("SELECT p FROM PredictionForStopPath p WHERE MOD(p.id, 10) = 0")
	List<PredictionForStopPath> findAll();
}
