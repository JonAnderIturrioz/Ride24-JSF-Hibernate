package principal;

import java.util.Date;
import java.util.List;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementationHibernate;
import configuration.UtilDate;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;

public class ApplicationLauncher {

	public ApplicationLauncher(){
	}

	private void checkGetDepartAndDestinationCities(BLFacade bl) {
		List<String> l1= bl.getDepartCities();
		
		for (String s: l1) {
			System.out.println(s+":");
			List<String> l2= bl.getDestinationCities(s);
			System.out.println(l2.toString());
		}
	}
	
	private void checkCreateAndGetRide(BLFacade bl) {
		
		try {
			bl.createRide("Eibar", "Donostia", UtilDate.trim(new Date()), 3, 1, "driver3@gmail.com");
			System.out.println(bl.getRides("Eibar", "Donostia", UtilDate.trim(new Date())).toString());
		} catch (RideMustBeLaterThanTodayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RideAlreadyExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
		ApplicationLauncher al = new ApplicationLauncher();
		BLFacade bl = BLFacadeImplementationHibernate.getInstance() ;
		
		al.checkCreateAndGetRide(bl);
		
		

	}

	

}
