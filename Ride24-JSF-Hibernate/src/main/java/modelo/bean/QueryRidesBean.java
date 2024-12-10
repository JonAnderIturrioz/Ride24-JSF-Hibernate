package modelo.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementationHibernate;
import configuration.UtilDate;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import modelo.domain.Ride;

@Named("queryRides")
@SessionScoped
public class QueryRidesBean implements Serializable {
	private static final long serialVersionUID = 1L;

	BLFacade bl = BLFacadeImplementationHibernate.getInstance();

	private Date date;

	private String departCity;
	private List<String> departCities;

	private String arrivalCity;
	private List<String> destinationCities;

	private List<Date> daysWithRides;

	private List<Ride> rides;

	public QueryRidesBean() {
		departCities = bl.getDepartCities();
		departCity = departCities.get(0);

		destinationCities = bl.getDestinationCities(departCity);
		arrivalCity = destinationCities.get(0);

		setDaysWithRides(bl.getThisMonthDatesWithRides(departCity, arrivalCity, UtilDate.trim(new Date())));

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

	public void setDepartCity(String departCity) {
		this.departCity = departCity;
		this.destinationCities = bl.getDestinationCities(departCity);

		/*
		 * ExecutorService threadpool = Executors.newCachedThreadPool();
		 * Future<List<String>> futureTask = threadpool.submit(() ->
		 * bl.getDestinationCities(departCity));
		 * 
		 * while (!futureTask.isDone()) {
		 * System.out.println("FutureTask is not finished yet..."); } try {
		 * this.destinationCities = futureTask.get(); } catch (InterruptedException |
		 * ExecutionException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * threadpool.shutdown();
		 */
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

	public void setDaysWithRides(List<Date> daysWithRides) {
		this.daysWithRides = daysWithRides;
	}

	public List<Ride> getRides() {
		return rides;
	}

	public void setRides(SelectEvent<Date> event) {
		Date selectedDate = event.getObject(); // Extract the selected date
		this.date = selectedDate; // Update the bean's date
		this.rides = bl.getRides(departCity, arrivalCity, UtilDate.trim(selectedDate)); // Fetch the rides

	}

}
