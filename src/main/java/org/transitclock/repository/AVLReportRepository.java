package org.transitclock.repository;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.transitclock.db.structs.AvlReport;
import org.transitclock.db.structs.AvlReportId;

@RepositoryRestResource(path = "avlreport")
public interface AVLReportRepository extends JpaRepository<AvlReport, AvlReportId> {
	List<AvlReport> findByVehicleId(@Param("vehicleId") String vehicleId);
	
	@Query("SELECT a FROM AvlReport a WHERE LOWER(a.vehicleId) = LOWER(:vehicleId) and a.time > :startDate and a.time < :endDate")
	List<AvlReport> findByVehicleIdandDate(
			@Param("vehicleId") String vehicleId, 
			@DateTimeFormat(pattern="yyyy-MM-dd") @Param("startDate")Date startDate,
			@DateTimeFormat(pattern="yyyy-MM-dd") @Param("endDate")Date endDate);
	
	@Query(value="SELECT a FROM AvlReport a WHERE "
			+ "LOWER(a.vehicleId) = LOWER(:vehicleId) and "
			+ "extract(SECONDS_SINCE_MIDNIGHT from a.time) > extract(SECONDS_SINCE_MIDNIGHT from :startTime) and "
			+ "extract(SECONDS_SINCE_MIDNIGHT from a.time) < extract(SECONDS_SINCE_MIDNIGHT from :endTime) and "
			+ "a.time > :startDate and a.time < :endDate"						
			, nativeQuery=false)	
	List<AvlReport> findByVehicleIdandDateandTimeOfDay(
			@Param("vehicleId") String vehicleId, 
			@DateTimeFormat(pattern="yyyy-MM-dd") @Param("startDate")Date startDate,
			@DateTimeFormat(pattern="yyyy-MM-dd") @Param("endDate")Date endDate,
			@DateTimeFormat(pattern="hh-mm-ss") @Param("startTime")Date startTime,
			@DateTimeFormat(pattern="hh-mm-ss") @Param("endTime")Date endTime);
	
}
