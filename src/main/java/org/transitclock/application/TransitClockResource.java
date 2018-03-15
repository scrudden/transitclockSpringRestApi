package org.transitclock.application;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.transitclock.db.structs.AvlReport;
import org.transitclock.db.structs.PredictionAccuracy;
import org.transitclock.db.structs.PredictionForStopPath;
import org.transitclock.db.structs.VehicleEvent;
import org.transitclock.repository.AVLReportRepository;
import org.transitclock.repository.PredictionAccuracyRepository;
import org.transitclock.repository.StopPathPredictionRepository;
import org.transitclock.repository.VehicleEventRepository;

@RestController
public class TransitClockResource
{
	@Autowired
	private AVLReportRepository avlreportRepository;
	
	@Autowired
	private VehicleEventRepository vehicleeventRepository;
	
	@Autowired
	private StopPathPredictionRepository stoppathpredictionRepository;
	
	@Autowired
	private PredictionAccuracyRepository predictionaccuracyRepository;
	
	@GetMapping("/avlreports")
	public List<AvlReport> retrieveAllAvlReports() {
		return avlreportRepository.findAll();
	}
	
	@GetMapping("/avlreports/findByVehicleId")
	public List<AvlReport> retrieveAllAvlReports(@RequestParam("vehicleId") String vehicleId)
	{
		return avlreportRepository.findByVehicleId(vehicleId);
	}
	
	@GetMapping("/avlreports/findByVehicleIdandDate")
	public List<AvlReport> retrieveAvlReportsByVehicleIdandDate(@RequestParam("vehicleId") String vehicleId, @RequestParam("startDate") @DateTimeFormat(pattern="yyyyMMdd") Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern="yyyyMMdd") Date endDate)
	{
		return avlreportRepository.findByVehicleIdandDate(vehicleId, startDate, endDate);
	}
	
	@GetMapping("/avlreports/findByVehicleIdandDateandTime")
	public List<AvlReport> retrieveAvlReportsByVehicleIdandDateandTime(@RequestParam("vehicleId") String vehicleId, @RequestParam("startDate") @DateTimeFormat(pattern="yyyyMMdd") Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern="yyyyMMdd") Date endDate, @RequestParam("startTime") @DateTimeFormat(pattern="hhmmss") Date startTime, @RequestParam("endTime") @DateTimeFormat(pattern="hhmmss") Date endTime)
	{
		return avlreportRepository.findByVehicleIdandDateandTimeOfDay(vehicleId, startDate, endDate, startTime, endTime);
	}
	
	@GetMapping("/vehicleevents")
	public List<VehicleEvent> retrieveAllVehicleEvents() {
		return vehicleeventRepository.findAll();
	}
	
	@GetMapping("/vehicleevents/findByVehicleId")
	public List<VehicleEvent> retrieveAllVehicleEvents(@RequestParam("vehicleId") String vehicleId)
	{
		return vehicleeventRepository.findByVehicleId(vehicleId);
	}
	
	@GetMapping("/stoppathpredictions/findByStopPathIndex")
	public List<PredictionForStopPath> retrieveStopPathPredictionsByStopPathIndex(@RequestParam("stopPathIndex") Integer stopPathIndex,  @RequestParam("travelTime") Boolean travelTime)
	{
		return stoppathpredictionRepository.findByStopPathIndex(stopPathIndex, travelTime);
	}
	
	@GetMapping("/predictionaccuracy/findByRouteId")
	public List<PredictionAccuracy> retrivePredictionAccuracyByRouteId(@RequestParam("routeId") String routeId)
	{
		return predictionaccuracyRepository.findByRouteId(routeId);
	}
	
}
