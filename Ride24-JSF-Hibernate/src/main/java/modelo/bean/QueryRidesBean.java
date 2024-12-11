package modelo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementationHibernate;
import configuration.UtilDate;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import modelo.domain.Ride;

@Named("queryRides")
@ViewScoped
public class QueryRidesBean implements Serializable {
	private static final long serialVersionUID = 1L;

	BLFacade bl = BLFacadeImplementationHibernate.getInstance();

	private Date date;

	private String departCity;
	private List<String> departCities;

	private String arrivalCity;
	private List<String> destinationCities;
	
	private HashMap<String, List<String>> cityTable;

	private List<Date> daysWithRides;

	private List<Ride> rides;

	public QueryRidesBean() {
		departCities = bl.getDepartCities();
		departCity = departCities.get(0);
		
		cityTable = new HashMap<String, List<String>>();
		
		// Load all cities and possible destinations and store them into a HashMap.
		// This is to avoid calling "bl.getDestinationCities(s)" during runtime, which produces delays
		// and results in Arrival City selectOneMenu being empty after changing Departure city selection.		
		for (String s : departCities)
			cityTable.put(s, bl.getDestinationCities(s));
		
		destinationCities = cityTable.get(departCity);
		arrivalCity = destinationCities.get(0);

		daysWithRides = bl.getThisMonthDatesWithRides(departCity, arrivalCity, UtilDate.trim(new Date()));

		rides = bl.getRides(departCity, arrivalCity, UtilDate.trim(new Date()));
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDepartCity() {
		return departCity;
	}

	
	/**
	 * Setter for attribute departCity. Also updates destinationCities to be used in the view.
	 * cityTable contains all possible destinations for each of the departure cities. The goal
	 * is to avoid calls to database, which often result in the view displaying an empty list.
	 * 
	 * @param departCity 
	 */
	public void setDepartCity(String departCity) {
		if (departCity == null || departCity.isEmpty()) {
	        // Log the issue or handle it gracefully
	        System.out.println("DepartCity is null or empty");
	        return;
	    }
		this.departCity = departCity;
		this.updateDestinationCities();
	}
	
	private void updateDestinationCities() {
		System.out.println("Depart City: " + departCity);
		this.destinationCities = cityTable.get(departCity);
		this.setArrivalCity(this.destinationCities.getFirst());
	    System.out.println("Destination Cities: " + destinationCities);
	}

	public List<String> getDepartCities() {
		return departCities;
	}

	public void setDepartCities(List<String> departCities) {
		this.departCities = departCities;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public List<String> getDestinationCities() {
		return destinationCities;
	}

	public void setDestinationCities(List<String> destinationCities) {
		this.destinationCities = destinationCities;
	}

	public List<Date> getDaysWithRides() {
		return daysWithRides;
	}

	public void setDaysWithRides(SelectEvent<Date> event) {
		this.daysWithRides = bl.getThisMonthDatesWithRides(departCity, arrivalCity, UtilDate.trim(event.getObject()));

	}

	public List<Ride> getRides() {
		return rides;
	}

	public void setRides(SelectEvent<Date> event) {
		Date selectedDate = event.getObject(); // Extract the selected date
		this.date = selectedDate; // Update the bean's date
		this.rides = bl.getRides(departCity, arrivalCity, UtilDate.trim(selectedDate)); // Fetch the rides

	}
	
	public void setEmptyRides() {
		// Update the bean's date
		this.rides = new ArrayList<Ride>(); // Fetch the rides

	}

}
