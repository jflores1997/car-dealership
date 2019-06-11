package com.revature.driver;

import java.util.Scanner;

import com.revature.dao.SystemDAO;
import com.revature.pojo.Customer;
import com.revature.pojo.DealershipSys;
import com.revature.pojo.Employee;
import com.revature.pojo.User;
import com.revature.service.CustomerService;
import com.revature.service.EmployeeService;
import com.revature.service.UserService;
import com.revature.service.WelcomeService;
import com.revature.ui.UserScreen;

public class CarDealershipDriver {

	private static WelcomeService welcomeService;
	private static DealershipSys system;
	private static final String SYSTEM_DATA_FILENAME = "SystemData1.dat";
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		system = SystemDAO.loadSystem(SYSTEM_DATA_FILENAME);
		run(system, scanner, SYSTEM_DATA_FILENAME);
		
	}

	public static void run(DealershipSys system, Scanner scanner, String saveFileName) {
		
		welcomeService = new WelcomeService(system);
		UserService mainPage;
		boolean running = true;
		
		while (running) {
			User user = welcomeService.welcome(scanner);
			
			if (user instanceof Customer) {
				mainPage = new CustomerService(user, system);
			} else if (user instanceof Employee) {
				mainPage = new EmployeeService(user, system);
			} else {
				mainPage = null;
				UserScreen.displayExit();
				SystemDAO.saveSystem(system, saveFileName);
				System.exit(0);
			}
			
			running = mainPage.userMainPage(scanner);
			SystemDAO.saveSystem(system, saveFileName);
		}
	}
}
