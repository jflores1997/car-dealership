package com.revature.service;

import java.util.Scanner;

import com.revature.pojo.Car;
import com.revature.pojo.DealershipSys;
import com.revature.pojo.Offer;
import com.revature.pojo.User;
import com.revature.ui.SystemScreen;
import com.revature.ui.UserScreen;

public class EmployeeService implements UserService {
	
	private User user;
	private DealershipSys system;
	private boolean running;
	private boolean signedIn;

	public EmployeeService(User user, DealershipSys system) {
		
		this.user = user;
		this.system = system;
		running = true;
		signedIn = true;
		
	}

	@Override
	public boolean userMainPage(Scanner scanner) {
		
		UserScreen.displayWelcomeEmployee(user);
		while (signedIn) {
			UserScreen.displayEmployeeOptions();
			String input = scanner.nextLine().toLowerCase().strip();
			switch (input)
			{
				case ("add car"):
					addCarService(scanner);
					break;
				case ("remove car"):
					removeCarService(scanner);
					break;
				case ("accept offer"):
					acceptOfferService(scanner);
					break;
				case ("remove offer"):
					removeOfferService(scanner);
					break;
				case ("view all payments"):
					viewAllPaymentsService(scanner);
					break;
				case ("view car lot"):
					viewCarLot();
					break;
				case ("sign out"):
					signOut();
					break;
				case ("exit"):
					exit();
					break;
				default:
					UserScreen.displayInvalidOption();
			}
		}
		return running;
	}
	
	@Override
	public void viewCarLot() {
		system.showCarLot();
	}
	
	@Override
	public void signOut() {
		signedIn = false;
		UserScreen.displaySignOut(user);
	}

	@Override
	public void exit() {
		running = false;
		signedIn = false;
		UserScreen.displayExit();
	}
	
	public void addCarService(Scanner scanner) {
		UserScreen.displayAddCar();
		String carName = scanner.nextLine().strip();
		
		if (carName.length() == 0) {
			UserScreen.displayEmptyCarName();
			addCarService(scanner);
			return;
		}
		
		Car newCar = new Car(carName);
		system.addCar(newCar);
	}
	
	public void removeCarService(Scanner scanner) {
		UserScreen.displayRemoveCar();
		String carName = scanner.nextLine().strip();
		
		if (carName.length() == 0) {
			UserScreen.displayEmptyCarName();
			removeCarService(scanner);
			return;
		} else {
			system.removeCar(carName);
		}
		
	}
	
	public void acceptOfferService(Scanner scanner) {
		UserScreen.displayAcceptOfferCarName();
		String carName = scanner.nextLine();
		
		UserScreen.displayAcceptOfferUsername();
		String username = scanner.nextLine();
		
		system.acceptOffer(carName, username);
	}
	
	public void removeOfferService(Scanner scanner) {
		UserScreen.displayRemoveOfferCarName();
		String carName = scanner.nextLine();
		
		UserScreen.displayRemoveOfferUsername();
		String username = scanner.nextLine();
		
		system.removeOffer(carName, username);
	}
	
	public void viewAllPaymentsService(Scanner scanner) {
		if (system.getCarLotSize() == 0) {
			SystemScreen.displayCarLotEmpty();
		}
		
		StringBuilder displayCars = new StringBuilder();
		for (String carName : system.getCarLot().keySet()) {
			displayCars.append(carName + "\n");
		}
		
		UserScreen.displayViewPayments(displayCars.toString());
		String carName = scanner.nextLine();
		Car car = system.getCar(carName);
		
		if (car == null) {
			SystemScreen.displayCarNotFound(carName);
			return;
		}
		
		Offer acceptedOffer = car.getAcceptedOffer();
		if (acceptedOffer != null) {
			UserScreen.displayOffer(carName, acceptedOffer);
		}
		
		if (car.getPendingOffers().isEmpty()) {
			UserScreen.displayCarHasNoOffers(carName);
		}
		for (String userName : car.getPendingOffers().keySet()) {
			UserScreen.displayOffer(carName, car.getPendingOffer(userName));
		}
	}
}
