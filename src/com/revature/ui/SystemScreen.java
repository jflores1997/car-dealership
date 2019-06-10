package com.revature.ui;

import java.util.Map;

import com.revature.pojo.Car;
import com.revature.util.LoggerUtil;

public class SystemScreen implements Screen {

	private static final String COULD_NOT_ADD_CAR = "Could not add Car to the lot";
	private static final String NON_UNIQUE_CAR = " already exists in the Car Lot";
	private static final String CAR_SUCCESSFULLY_ADDED = " was sucessfully added to the Car Lot";
	private static final String CAR_SUCCESSFULLY_REMOVED = " was sucessfully removed to the Car Lot";
	private static final String CAR_NOT_FOUND = " was not found in the Car Lot";
	
	private static final String CAR_LOT_EMPTY = "The Car Lot is currently empty";
	private static final String CAR_LOT_INTRO = "The Car Lot is currently holding the following Cars:\n";
	private static final String CAR_ALREADY_ACCEPTED_OFFER = " is already owned by another Customer";
	
	public static void displayCouldNotAddCar() {
		LoggerUtil.trace(COULD_NOT_ADD_CAR);
	}

	public static void displayNonUniqueCar(String carName) {
		LoggerUtil.trace(carName + NON_UNIQUE_CAR);
	}

	public static void displayCarAdded(String carName) {
		LoggerUtil.trace(carName + CAR_SUCCESSFULLY_ADDED);
	}

	public static void displayCarLotEmpty() {
		LoggerUtil.trace(CAR_LOT_EMPTY);
	}

	public static void displayCarLot(Map<String, Car> carLot) {
		StringBuilder msg = new StringBuilder(CAR_LOT_INTRO);
		for (String carName : carLot.keySet()) {
			msg.append(carName + "\n");
		}
		LoggerUtil.trace(msg.toString());
	}

	public static void displayCarRemoved(String carName) {
		LoggerUtil.trace(carName + CAR_SUCCESSFULLY_REMOVED);
	}
	
	public static void displayCarNotFound(String carName) {
		LoggerUtil.trace(carName + CAR_NOT_FOUND);
	}

	public static void displayCarAlreadyAcceptedOffer(String carName) {
		LoggerUtil.trace(carName + CAR_ALREADY_ACCEPTED_OFFER);
	}

	public static void displayCustomerNoOffer(String username, String carName) {
		LoggerUtil.trace(carName + " does not have any offers from " + username);
	}

	public static void displayCarOfferAccepted(String username, String carName) {
		LoggerUtil.trace(carName + " is now owned by " + username);
	}

	public static void displayCarOfferRemoved(String username, String carName) {
		LoggerUtil.trace(carName + " no longer has a pending Offer from user " + username);
	}
	
}
