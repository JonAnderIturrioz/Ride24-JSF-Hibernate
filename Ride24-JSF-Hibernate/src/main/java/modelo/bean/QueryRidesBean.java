package modelo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("queryRides")
@SessionScoped
public class QueryRidesBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date date;

	private String departCity;
	private List<String> departCities;

	private String arrivalCity;
	private List<String> destinationCities;

	// private List<Ride> rides;
	// private Ride ride;

	public QueryRidesBean() {
		departCities = new ArrayList<String>();// bl.getDepartCities();
		departCities.add("Bilbo");
		departCities.add("Donostia");
		departCities.add("Gasteiz");
		departCity = departCities.get(0);
		destinationCities = new ArrayList<String>();// bl.getDestinationCities(departCity);
		destinationCities.add("Donostia");
		destinationCities.add("Gasteiz");
		arrivalCity = destinationCities.get(0);
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
		//this.destinationCities = bl.getDestinationCities(departCity);

		if ("Bilbo".equals(departCity)) {
			this.destinationCities = Arrays.asList("Donostia", "Gasteis");
		} else if ("Donostia".equals(departCity)) {
			this.destinationCities = Arrays.asList("Bilbo", "Gasteiz");
		} else if ("Gasteiz".equals(departCity)) {
			this.destinationCities = Arrays.asList("Bilbo", "Donostia");
		}

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
}
