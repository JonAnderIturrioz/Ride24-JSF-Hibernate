package modelo.bean;

import java.io.Serializable;
import java.util.Date;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementationHibernate;
import configuration.UtilDate;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import modelo.domain.Ride;

@Named("createRide")
@SessionScoped
public class CreateRideBean implements Serializable {
	private static final long serialVersionUID = 1L;

	BLFacade bl = BLFacadeImplementationHibernate.getInstance();

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

	public void validateDate(SelectEvent<Date> event) {
	    // Retrieve the selected date from the event
	    Date selectedDate = event.getObject();

	    // Get the current date
	    Date currentDate = new Date();

	    // Check if the selected date is before today
	    if (selectedDate != null && selectedDate.before(currentDate)) {
	        // Add a validation error message to be displayed in the JSF page
	        FacesMessage message = new FacesMessage("The date must be later than today.");
	        message.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage("date", message);
	    }}

	public void create() {
				
		FacesMessage message;
		
		try {
			Ride r = bl.createRide(departCity, arrivalCity, UtilDate.newDate(2024, 11, 15), seats, price, "driver3@gmail.com");
			message = new FacesMessage("Successfully created Ride "+r.toString());
		} catch (RideMustBeLaterThanTodayException e) {
			e.printStackTrace();
			message = new FacesMessage("ERROR: The date must be later than today.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			
		} catch (RideAlreadyExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = new FacesMessage("ERROR: This ride already exists.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
        
        FacesContext.getCurrentInstance().addMessage("submit", message);
		
		
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
