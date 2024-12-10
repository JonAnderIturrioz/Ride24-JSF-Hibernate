package principal;

import java.util.Calendar;

import javax.persistence.EntityManager;

import configuration.UtilDate;
import modelo.JPAUtil;
import modelo.domain.Driver;

public class ApplicationLauncher {

	EntityManager db;
	public ApplicationLauncher(){
		db = JPAUtil.getEntityManager();
	}
	
	private void testFillDB() {
		
		
		db.getTransaction().begin();

		try {

			Calendar today = Calendar.getInstance();

			int month = today.get(Calendar.MONTH);
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 1;
				year += 1;
			}

			// Create drivers
			//Driver driver1 = new Driver("driver1@gmail.com", "Aitor Fernandez");
			//Driver driver2 = new Driver("driver2@gmail.com", "Ane GaztaÃ±aga");
			Driver driver3 = new Driver("driver3@gmail.com", "Test driver");

			// Create rides
			/*
			driver1.addRide("Donostia", "Bilbo", UtilDate.newDate(year, month, 15), 4, 7);
			driver1.addRide("Donostia", "Gazteiz", UtilDate.newDate(year, month, 6), 4, 8);
			driver1.addRide("Bilbo", "Donostia", UtilDate.newDate(year, month, 25), 4, 4);
			driver1.addRide("Donostia", "IruÃ±a", UtilDate.newDate(year, month, 7), 4, 8);

			driver2.addRide("Donostia", "Bilbo", UtilDate.newDate(year, month, 15), 3, 3);
			driver2.addRide("Bilbo", "Donostia", UtilDate.newDate(year, month, 25), 2, 5);
			driver2.addRide("Eibar", "Gasteiz", UtilDate.newDate(year, month, 6), 2, 5);
			 */
			driver3.addRide("Bilbo", "Donostia", UtilDate.newDate(year, month, 14), 1, 3);

			
			//db.persist(driver1);
			//db.persist(driver2);
			db.persist(driver3);

			db.getTransaction().commit();
		} catch (Exception e) {
			if (db.getTransaction().isActive()) {
				 db.getTransaction().rollback();
				 }
				 System.out.println("Error: " + e.getMessage());
		}finally {
			db.close();
		}
	}
	public static void main(String[] args) {
		
		ApplicationLauncher al = new ApplicationLauncher();
		al.testFillDB();

	}

}
