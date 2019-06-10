package com.revature.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.pojo.Car;
import com.revature.pojo.DealershipSys;
import com.revature.pojo.Offer;
import com.revature.pojo.User;
import com.revature.ui.SystemScreen;
import com.revature.ui.UserScreen;

public class CustomerService implements UserService {
	
	private User user;
	private DealershipSys system;
	private boolean running;
	private boolean signedIn;

	public CustomerService(User user, DealershipSys system) {
		
		this.user = user;
		this.system = system;
		running = true;
		signedIn = true;
	}

	@Override
	public boolean userMainPage(Scanner scanner) {
		UserScreen.displayWelcomeCustomer(user);
		while (signedIn) {
			UserScreen.displayCustomerOptions();
			String input = scanner.nextLine().toLowerCase().strip();
			switch (input)
			{
				case ("make offer"):
					makeOfferService(scanner);
					break;
				case ("view my cars"):
					viewUserCarsService(scanner);
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
	public void signOut() {
		signedIn = false;
		UserScreen.displaySignOut(user);
	}

	@Override
	public void exit() {
		signedIn = false;
		running = false;
		UserScreen.displayExit();
	}

	@Override
	public void viewCarLot() {
	}
	
	private void makeOfferService(Scanner scanner) {
		UserScreen.displayMakeOffer();
		String carName = scanner.nextLine().strip();
		
		
		try {
			UserScreen.displayMakeOfferPrice();
			double price = Double.parseDouble(scanner.nextLine());
			
			UserScreen.displayOfferMonthsToPay();
			int monthsToPay = Integer.parseInt(scanner.nextLine());
			system.makeOffer(carName, user, price, monthsToPay);
		} catch (NumberFormatException e) {
			UserScreen.displayInvalidNumber();
		}
	}
	
	private void viewUserCarsService(Scanner scanner) {
		List<String> pendingCarNames = new ArrayList<String>();
		List<String> acceptedCarNames = new ArrayList<String>();
		
		for (String carName : system.getCarLot().keySet()) {
			Car car = system.getCar(carName);
			if (car.getAcceptedOffer() != null && car.getOwner().equals(user)) {
				acceptedCarNames.add(car.getName());
			} else if (car.getPendingOffer(user.getUsername()) != null) {
				pendingCarNames.add(car.getName());
			}
		}
		
		UserScreen.displayViewMyCars(user.getUsername(), pendingCarNames, acceptedCarNames);
		String input = scanner.nextLine().strip();
		System.out.println(input);
		
		if (input.equalsIgnoreCase("back")) {
			return;
		} else if (acceptedCarNames.contains(input)) {
			Offer offer = system.getCar(input).getAcceptedOffer();
			UserScreen.displayOffer(input, offer);
		} else if (pendingCarNames.contains(input)) {
			Offer offer = system.getCar(input).getPendingOffer(user.getUsername());
			if (offer != null) {
				UserScreen.displayOffer(input, offer);
			} else {
				SystemScreen.displayCarNotFound(input);
				viewUserCarsService(scanner);
			}
		} else {
			SystemScreen.displayCarNotFound(input);
			viewUserCarsService(scanner);
		}
	}
	
}
