package com.revature.service;

import java.util.Map;
import java.util.Scanner;

import com.revature.ui.LoginScreen;
import com.revature.ui.WelcomeScreen;
import com.revature.pojo.Customer;
import com.revature.pojo.DealershipSys;
import com.revature.pojo.Employee;
import com.revature.pojo.User;

public class WelcomeService {

	private DealershipSys system;

	public WelcomeService(DealershipSys system) {
		super();
		this.system = system;
	}
	
	public User welcome(Scanner scanner) {
		WelcomeScreen.displayWelcome();
		String input = scanner.nextLine().strip();
		
		if (input.equalsIgnoreCase("login")) {
			return login(scanner);
		} else if (input.equalsIgnoreCase("register")) {
			return register(scanner);
		} else if (input.equalsIgnoreCase("exit")) {
			return null;
		}else {
			LoginScreen.displayInvalidInput();
			return welcome(scanner);
		}
	}
	
	public User login(Scanner scanner) {
		LoginScreen.displayUserType();
		String input = scanner.nextLine().strip();
		
		if (input.equalsIgnoreCase("customer")) {
			return customerLogin(scanner);
		} else if (input.equalsIgnoreCase("employee")) {
			return employeeLogin(scanner);
		} else {
			LoginScreen.displayInvalidInput();
			return welcome(scanner);
		}
	}

	public User employeeLogin(Scanner scanner) {
		
		LoginScreen.displayInputUsername();
		String username = scanner.nextLine().strip();
		
		Map<String, Employee> employeeUsernames = system.getEmployeeUsernames();
		
		if (employeeUsernames.containsKey(username)) {
			LoginScreen.displaySuccessfulLogin(username);
			return employeeUsernames.get(username);
		} else {
			LoginScreen.displayInvalidLogin();
			return welcome(scanner);
		}
	}
	
	public User customerLogin(Scanner scanner) {
		
		LoginScreen.displayInputUsername();
		String username = scanner.nextLine().strip();
		
		Map<String, Customer> customerUsernames = system.getCustomerUsernames();
		
		if (customerUsernames.containsKey(username)) {
			LoginScreen.displaySuccessfulLogin(username);
			return customerUsernames.get(username);
		} else {
			LoginScreen.displayInvalidLogin();
			return welcome(scanner);
		}
	}
	
	public User register(Scanner scanner) {
		
		LoginScreen.displayUserType();
		String input = scanner.nextLine().strip();
		
		if (input.equalsIgnoreCase("customer")) {
			return customerRegister(scanner);
		} else if (input.equalsIgnoreCase("employee")) {
			return employeeRegister(scanner);
		} else {
			LoginScreen.displayInvalidInput();
			return welcome(scanner);
		}
	}

	public User employeeRegister(Scanner scanner) {
		
		LoginScreen.displayInputUsername();
		String username = scanner.nextLine().strip();
		
		Employee newEmployee = system.registerEmployee(username);
		if (newEmployee != null) {
			LoginScreen.displaySuccessfulRegister(username);
			return (User) newEmployee;
		} else {
			LoginScreen.displayNonUniqueUsername();
			return welcome(scanner);
		}
	}
	
	public User customerRegister(Scanner scanner) {
		
		LoginScreen.displayInputUsername();
		String username = scanner.nextLine().strip();
		
		Customer newCustomer = system.registerCustomer(username);
		if (newCustomer != null) {
			LoginScreen.displaySuccessfulRegister(username);
			return (User) newCustomer;
		} else {
			LoginScreen.displayNonUniqueUsername();
			return welcome(scanner);
		}
	}
}
