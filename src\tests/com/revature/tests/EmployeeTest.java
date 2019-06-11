package com.revature.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import com.revature.driver.CarDealershipDriver;
import com.revature.pojo.Car;
import com.revature.pojo.DealershipSys;
import com.revature.pojo.Employee;
import com.revature.service.EmployeeService;
import com.revature.util.LoggerUtil;

public class EmployeeTest {
	
	private static final String TEST_FILENAME = "testSystemFile.dat";

	/* Adds two cars into system */
	@Test
	public void testAddCar() {
		LoggerUtil.debug("=========== Test Employee Add Car ===========");
		
		DealershipSys system = new DealershipSys();
		Employee employee = new Employee("jordan");
		EmployeeService employeeService = new EmployeeService(employee, system);
		
		String input = "ferrari\n"
				+ "   \n"
				+ "chevy\n"
				+ "ferrari\n";
		Scanner scanner = new Scanner(input);
		
		// input: ferrari
		employeeService.addCarService(scanner);
		
		// input: ferrari again
		employeeService.addCarService(scanner);
		
		// input: whitespace
		// input: chevy
		employeeService.addCarService(scanner);
		
		employeeService.viewCarLot();
		
		assertEquals("ferrari", system.getCar("ferrari").getName());
		assertEquals(2, system.getCarLotSize());
	}

	@Test
	public void testRemoveCar() {
		LoggerUtil.debug("=========== Test Employee Remove Car ===========");
		
		List<Car> carLot = new ArrayList<Car>();
		carLot.add(new Car("honda"));
		carLot.add(new Car("subaru"));
		carLot.add(new Car("prius"));
		DealershipSys system = new DealershipSys();
		system.addCars(carLot);
		
		Employee employee = new Employee("jordan");
		EmployeeService employeeService = new EmployeeService(employee, system);
		
		String input = "honda\n"
				+ "honda\n"
				+ "  \n"
				+ "mazda\n";
		Scanner scanner = new Scanner(input);
		
		// input honda
		employeeService.removeCarService(scanner);
		
		// input honda again
		employeeService.removeCarService(scanner);
		
		// input whitespace
		// input mazda
		employeeService.removeCarService(scanner);
		
		employeeService.viewCarLot();
		
		assertEquals(null, system.getCar("honda"));
		assertEquals(2, system.getCarLotSize());
	}
	
	@Test
	public void testRegisterEmployee() {
		LoggerUtil.debug("=========== Test Register Employee ===========");
		
		DealershipSys system = new DealershipSys();
		
		String input = "register\n"
				+ "employee\n"
				+ "jordan1234 flores\n"
				+ "sign out\n"
				+ "login\n"
				+ "employee\n"
				+ "jordan1234 flores\n"
				+ "exit";
		
		Scanner scanner = new Scanner(input);
		CarDealershipDriver.run(system, scanner, TEST_FILENAME);
	}
	
	@Test
	public void testEmployeeOffer() throws Exception {
		LoggerUtil.debug("=========== Test Employee Accept Offer ===========");
		
		List<Car> carLot = new ArrayList<Car>();
		carLot.add(new Car("honda"));
		carLot.add(new Car("subaru"));
		carLot.add(new Car("prius"));
		DealershipSys system = new DealershipSys();
		system.addCars(carLot);
		
		URL url = this.getClass().getResource("EmployeeOfferInputs.txt");
		File file = new File(url.getFile());
		
		Scanner scanner = new Scanner(file);
		CarDealershipDriver.run(system, scanner, TEST_FILENAME);
	}
}
