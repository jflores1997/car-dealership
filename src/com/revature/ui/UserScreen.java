package com.revature.ui;

import java.util.List;

import com.revature.pojo.Offer;
import com.revature.pojo.User;
import com.revature.util.LoggerUtil;

public class UserScreen implements Screen {
	
	private static final String WELCOME_EMPLOYEE = "Welcome, Employee ";
	private static final String EMPLOYEE_OPTIONS = "Employee options are: \"add car\", \"remove car \", "
			+ "\"accept offer\", \"reject offer\", \"view all payments\", \"view car lot\", \"sign out\", \"exit\"";
	
	private static final String WELCOME_CUSTOMER = "Welcome, Customer ";
	private static final String CUSTOMER_OPTIONS = "Customer options are: \"make offer\", \"view my cars\", "
			+ "\"view car lot\", \"sign out\", \"exit\"";
	private static final String VIEW_MY_CARS_OPTIONS = ", welcome to your garage\n"
			+ "Input the name of one of your cars to view its payment status or type \"back\" to return to the main page\n";
	
	private static final String INVALID_OPTION = "Please input a valid option";
	private static final String INVALID_NUMBER = "Number invalid. Do not input number with any special characters or punctuation";
		
	private static final String EXITING_PROGRAM = "The Dealership is closing. Thank you for using our program.";
	private static final String SIGNING_OUT = " has signed out";
	
	private static final String ADD_CAR_NAME = "What is the name of the Car you would like to add?";
	private static final String EMPTY_CAR_NAME = "Please input non-empty Car name";
	private static final String REMOVE_CAR_NAME = "What is the name of the Car you would like to remove?";
	
	private static final String MAKE_OFFER_NAME = "What is the name of the Car you would like to make an offer for?";
	private static final String MAKE_OFFER_PRICE = "How much would you like to offer for the car?";
	private static final String OFFER_MONTHS_TO_PAY = "How many months do you need to pay off the car?";
	private static final String CAR_ALREADY_TAKEN = " has already been bought";
	private static final String OFFER_UPDATED = "'s offer has been updated";
	private static final String OFFER_ADDED = "'s offer has been added";
	
	private static final String ACCEPT_OFFER_CAR_NAME = "What is the name of the Car of the Offer you would like to accept?";
	private static final String ACCEPT_OFFER_USERNAME = "What is the username of the owner of the Offer?";
	private static final String REMOVE_OFFER_CAR_NAME = "What is the name of the Car of the Offer you would like to remove?";
	private static final String REMOVE_OFFER_USERNAME = "What is the username of the owner of the Offer?";
	private static final String CAR_HAS_NO_OFFERS = " currently has no pending or accepted offers";
	
	public static void displayWelcomeEmployee(User user) {
		LoggerUtil.trace(WELCOME_EMPLOYEE + user.getUsername() + "!");
	}
	
	public static void displayEmployeeOptions() {
		LoggerUtil.trace(EMPLOYEE_OPTIONS);
	}
	
	public static void displayWelcomeCustomer(User user) {
		LoggerUtil.trace(WELCOME_CUSTOMER + user.getUsername() + "!");
	}
	
	public static void displayCustomerOptions() {
		LoggerUtil.trace(CUSTOMER_OPTIONS);
	}

	public static void displayExit() {
		LoggerUtil.trace(EXITING_PROGRAM);
	}
	
	public static void displayAddCar() {
		LoggerUtil.trace(ADD_CAR_NAME);
	}

	public static void displayEmptyCarName() {
		LoggerUtil.trace(EMPTY_CAR_NAME);
	}

	public static void displayInvalidOption() {
		LoggerUtil.trace(INVALID_OPTION);
	}

	public static void displayRemoveCar() {
		LoggerUtil.trace(REMOVE_CAR_NAME);
	}

	public static void displaySignOut(User user) {
		LoggerUtil.trace(user.getUsername() + SIGNING_OUT);
	}

	public static void displayMakeOffer() {
		LoggerUtil.trace(MAKE_OFFER_NAME);
	}

	public static void displayMakeOfferPrice() {
		LoggerUtil.trace(MAKE_OFFER_PRICE);
	}

	public static void displayInvalidNumber() {
		LoggerUtil.trace(INVALID_NUMBER);
	}

	public static void displayOfferMonthsToPay() {
		LoggerUtil.trace(OFFER_MONTHS_TO_PAY);
	}

	public static void displayCarAlreadyTaken(String name) {
		LoggerUtil.trace(name + CAR_ALREADY_TAKEN);
	}

	public static void displayOfferUpdated(String name) {
		LoggerUtil.trace(name + OFFER_UPDATED);
	}

	public static void displayOfferAdded(String name) {
		LoggerUtil.trace(name + OFFER_ADDED);
	}

	public static void displayViewMyCars(String name, List<String> pendingCarNames, List<String> acceptedCarNames) {
		StringBuilder display = new StringBuilder(name + VIEW_MY_CARS_OPTIONS);
		
		display.append("Pending Offers:\n");
		for (String carName : pendingCarNames) {
			display.append(carName + "\n");
		}
		
		display.append("Owned Cars:\n");
		for (String carName : acceptedCarNames) {
			display.append(carName + "\n");
		}
		LoggerUtil.trace(display.toString());
		
	}

	public static void displayOffer(String carName, Offer offer) {
		
		StringBuilder display = new StringBuilder();
		String acceptStatus = (offer.isAccepted()) ? "Accepted" : "Pending";
		
		display.append("Offer Owner Username: " + offer.getOwner().getUsername() + "\n");
		display.append("Car Name: " + carName + "\n");
		display.append("Offer status: " + acceptStatus + "\n");
		display.append("Total Price: " + offer.getTotalPrice() + "\n");
		display.append("Remaining Price: " + offer.getRemainingPrice() + "\n");
		display.append("Number of payment months left: " + offer.getNumberOfPaymentsRemaining() + "\n");
		display.append("Next monthly payment due: " + offer.getNextPayment());
		
		LoggerUtil.trace(display.toString());
	}

	public static void displayViewPayments(String carLot) {
		LoggerUtil.trace("Input Car name to view its payment history\n"
				+ "Car Lot:\n" + carLot);
	}

	public static void displayAcceptOfferCarName() {
		LoggerUtil.trace(ACCEPT_OFFER_CAR_NAME);
	}

	public static void displayAcceptOfferUsername() {
		LoggerUtil.trace(ACCEPT_OFFER_USERNAME);
	}

	public static void displayRemoveOfferCarName() {
		LoggerUtil.trace(REMOVE_OFFER_CAR_NAME);
	}

	public static void displayRemoveOfferUsername() {
		LoggerUtil.trace(REMOVE_OFFER_USERNAME);
	}

	public static void displayCarHasNoOffers(String carName) {
		LoggerUtil.trace(carName + CAR_HAS_NO_OFFERS);
	}

}
