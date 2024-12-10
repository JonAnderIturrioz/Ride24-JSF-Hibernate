package modelo.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ride {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rideNumber;
	private String origin;
	private String destination;
	private int seats;
	private Date date;
	private float price;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "driver_email", nullable = false)
	private Driver driver;

	public Ride() {
		super();
	}

	public Ride(Integer rideNumber, String origin, String dest, Date date, int nPlaces, float price, Driver driver) {
		super();
		this.rideNumber = rideNumber;
		this.origin = origin;
		this.destination = dest;
		this.seats = nPlaces;
		this.date = date;
		this.price = price;
		this.driver = driver;
	}

	public Ride(String origin, String dest, Date date, int nPlaces, float price, Driver driver) {
		super();
		this.origin = origin;
		this.destination = dest;
		this.seats = nPlaces;
		this.date = date;
		this.price = price;
		this.driver = driver;
	}

	/**
	 * Get the number of the ride
	 * 
	 * @return the ride number
	 */
	public Integer getRideNumber() {
		return rideNumber;
	}

	/**
	 * Set the ride number to a ride
	 * 
	 * @param ride Number to be set
	 */

	public void setRideNumber(Integer rideNumber) {
		this.rideNumber = rideNumber;
	}

	/**
	 * Get the origin of the ride
	 * 
	 * @return the origin location
	 */

	public String getOrigin() {
		return origin;
	}

	/**
	 * Set the origin of the ride
	 * 
	 * @param origin to be set
	 */

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * Get the destination of the ride
	 * 
	 * @return the destination location
	 */

	public String getDestination() {
		return destination;
	}

	/**
	 * Set the origin of the ride
	 * 
	 * @param destination to be set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * Get the free places of the ride
	 * 
	 * @return the available places
	 */

	/**
	 * Get the date of the ride
	 * 
	 * @return the ride date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Set the date of the ride
	 * 
	 * @param date to be set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	public float getSeats() {
		return seats;
	}

	/**
	 * Set the free places of the ride
	 * 
	 * @param nPlaces places to be set
	 */

	public void setSeats(int nPlaces) {
		this.seats = nPlaces;
	}

	/**
	 * Get the driver associated to the ride
	 * 
	 * @return the associated driver
	 */
	public Driver getDriver() {
		return driver;
	}

	/**
	 * Set the driver associated to the ride
	 * 
	 * @param driver to associate to the ride
	 */
	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return rideNumber + ";" + origin + ";" + destination + ";" + date + ";" + seats + ";" + price;
	}
}
