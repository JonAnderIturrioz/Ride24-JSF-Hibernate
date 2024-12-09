package modelo.bean;

import java.io.Serializable;
import java.util.Date;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import modelo.domain.Driver;
import modelo.domain.Ride;

@Named("createRide")
@SessionScoped
public class CreateRideBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// BLFacade bl = new BLFacadeImplementationHibernate();

	private Date date;

	private String departCity;

	private String arrivalCity;

	private int seats;

	private float price;
	// private List<Ride> rides;
	// private Ride ride;

	public CreateRideBean() {

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
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void validateDate() {
		// Get the current date
		Date currentDate = new Date();

		// Check if the selected date is before today
		if (date != null && date.before(currentDate)) {
			// Add a validation error message to be displayed in the JSF page
			FacesMessage message = new FacesMessage("The date must be later than today.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void create() {
		Driver d = new Driver();
		Ride r = new Ride(departCity, arrivalCity, date, seats, price, d);
		System.out.println(r.toString());
	}

	/*
	 * public List<String> getRides() { List<String> rideNames = new
	 * ArrayList<String>(); for (Ride r : this.rides) rideNames.add(r.toString());
	 * 
	 * return rideNames; }
	 * 
	 * public void setRides(List<Ride> rides) { this.rides = rides; }
	 * 
	 * 
	 * public String getRide() { return ride.toString(); }
	 * 
	 * public void setRide(Ride ride) { this.ride = ride; }
	 */
}
