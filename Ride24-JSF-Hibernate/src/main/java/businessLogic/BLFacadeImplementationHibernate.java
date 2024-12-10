package businessLogic;

import java.util.Date;
import java.util.List;

import dataAccess.HibernateDataAccess;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;
import modelo.domain.Ride;

/**
 * It implements the business logic.
 */
public class BLFacadeImplementationHibernate implements BLFacade {
	private static BLFacadeImplementationHibernate instance;
	private HibernateDataAccess dbManager;

	private BLFacadeImplementationHibernate() {
		System.out.println("Creating BLFacadeImplementation instance with Hibernate DataAccess");

		dbManager = new HibernateDataAccess();

		// dbManager.close();

	}
	
	/*
	 * private BLFacadeImplementationHibernate(HibernateDataAccess da) {
	 * 
	 * System.out.
	 * println("Creating BLFacadeImplementation instance with DataAccess parameter"
	 * );
	 * 
	 * dbManager = da; }
	 */

	/**
	 * Generates new BLFacade object if not already initialized.
	 * Returns initialized BLFacade object.
	 * 
	 * @return static instance of the business logic object
	 */
	
	public static BLFacadeImplementationHibernate getInstance() {
		if (instance == null) {
			instance = new BLFacadeImplementationHibernate(); // Default constructor
		}
		return instance;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<String> getDepartCities() {
		dbManager.open();

		List<String> departLocations = dbManager.getDepartCities();

		dbManager.close();

		return departLocations;

	}

	/**
	 * {@inheritDoc}
	 */
	public List<String> getDestinationCities(String from) {
		dbManager.open();

		List<String> targetCities = dbManager.getArrivalCities(from);

		dbManager.close();

		return targetCities;
	}

	/**
	 * {@inheritDoc}
	 */
	public Ride createRide(String from, String to, Date date, int nPlaces, float price, String driverEmail)
			throws RideMustBeLaterThanTodayException, RideAlreadyExistException {

		dbManager.open();
		Ride ride = dbManager.createRide(from, to, date, nPlaces, price, driverEmail);
		dbManager.close();
		return ride;
	};

	/**
	 * {@inheritDoc}
	 */
	public List<Ride> getRides(String from, String to, Date date) {
		dbManager.open();
		List<Ride> rides = dbManager.getRides(from, to, date);
		dbManager.close();
		return rides;
	}
	
	public Ride getRideFromId(int rideNumber) {
		dbManager.open();
		Ride ride = dbManager.getRideFromId(rideNumber);
		dbManager.close();
		return ride;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date) {
		dbManager.open();
		List<Date> dates = dbManager.getThisMonthDatesWithRides(from, to, date);
		dbManager.close();
		return dates;
	}

	public void close() {
		HibernateDataAccess dB4oManager = new HibernateDataAccess();

		dB4oManager.close();

	}

	/**
	 * {@inheritDoc}
	 */
	public void initializeBD() {
		dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}

}
