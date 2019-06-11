package com.revature.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import com.revature.dao.SystemDAO;
import com.revature.driver.CarDealershipDriver;
import com.revature.pojo.Car;
import com.revature.pojo.DealershipSys;
import com.revature.util.LoggerUtil;

public class SerializableTest {

	private static final String TEST_FILENAME = "testSystemFile.dat";
	
	@Test
	public void testSerializeBasic() {
		LoggerUtil.debug("=========== Test Serialize Basic ===========");
		
		List<Car> carLot = new ArrayList<Car>();
		carLot.add(new Car("honda"));
		carLot.add(new Car("subaru"));
		carLot.add(new Car("prius"));
		DealershipSys system = new DealershipSys();
		system.addCars(carLot);
		
		SystemDAO.saveSystem(system, TEST_FILENAME);
		
		DealershipSys reloadedSystem = SystemDAO.loadSystem(TEST_FILENAME);
		assertEquals(3, reloadedSystem.getCarLotSize());
		assertEquals("honda", reloadedSystem.getCar("honda").getName());
	}
	
	@Test
	public void testEndToEnd() throws Exception {
		LoggerUtil.debug("=========== Test End-To-End Serialization ===========");
		
		URL url = this.getClass().getResource("endtoendinputs.txt");
		File file = new File(url.getFile());
		Scanner scanner = new Scanner(file);
		DealershipSys system = new DealershipSys();
		
		CarDealershipDriver.run(system, scanner, TEST_FILENAME);
		
		DealershipSys reloadedSystem = SystemDAO.loadSystem(TEST_FILENAME);
		assertEquals(3, reloadedSystem.getCarLotSize());
		assertEquals("mustang", reloadedSystem.getCar("mustang").getName());
		assertEquals(3000.55, reloadedSystem.getCar("toyota").getAcceptedOffer().getRemainingPrice(), 0.001);
	}

}
