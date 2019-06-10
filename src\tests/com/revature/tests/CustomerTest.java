package com.revature.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import com.revature.pojo.Car;
import com.revature.pojo.Customer;
import com.revature.pojo.DealershipSys;
import com.revature.service.CustomerService;

public class CustomerTest {

	@Test
	public void testMakeOffer() {
		List<Car> carLot = new ArrayList<Car>();
		carLot.add(new Car("honda"));
		carLot.add(new Car("subaru"));
		carLot.add(new Car("prius"));
		DealershipSys system = new DealershipSys();
		system.addCars(carLot);
		
		Customer customer = new Customer("jordan");
		CustomerService customerService = new CustomerService(customer, system);
		
		String input = "make offer\n"
				+ "honda\n"
				+ "1500.99\n"
				+ "12\n"
				+ "view my cars\n"
				+ "honda\n"
				+ "view my cars\n"
				+ "ferrari\n"
				+ "view my cars\n"
				+ "back\n"
				+ "exit\n";
		Scanner scanner = new Scanner(input);
		customerService.userMainPage(scanner);
		
		assertEquals((double) 1500.99, system.getCar("honda").getPendingOffer("jordan").getTotalPrice(), 0.001);
		assertEquals(false, system.getCar("honda").getPendingOffer("jordan").isAccepted());
	}

}
