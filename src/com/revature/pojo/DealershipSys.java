package com.revature.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.ui.SystemScreen;
import com.revature.util.LoggerUtil;

public class DealershipSys implements Serializable {
	
	static final long serialVersionUID = -1434992879835872291L;

	private Map<String, Customer> customerUsernames;
	private Map<String, Employee> employeeUsernames;
	private Map<String, Car> carLot;

	public DealershipSys() {
		customerUsernames = new HashMap<String, Customer>();
		employeeUsernames = new HashMap<String, Employee>();
		setCarLot(new HashMap<String, Car>());
	}
	
	public void addCar(Car newCar) {
		if (newCar == null) {
			LoggerUtil.debug("tried to add null car");
			SystemScreen.displayCouldNotAddCar();
		}
		
		Map<String, Car> carLot = getCarLot();
		String carName = newCar.getName();
		
		if (carLot.containsKey(carName)) {
			SystemScreen.displayNonUniqueCar(carName);
		} else {
			carLot.put(carName, newCar);
			SystemScreen.displayCarAdded(carName);
			setCarLot(carLot);
		}
		
	}
	
	public void addCars(List<Car> carList) {
		for (Car car : carList) {
			addCar(car);
		}
	}
	
	public Car getCar(String carName) {
		if (carLot.containsKey(carName)) {
			return carLot.get(carName);
		} else {
			return null;
		}
	}

	public int getCarLotSize() {
		return carLot.size();
	}

	public void showCarLot() {
		if (getCarLotSize() == 0) {
			SystemScreen.displayCarLotEmpty();
		} else {
			SystemScreen.displayCarLot(carLot);
		}
	}
	
	/* DealershipSys Util functions */
	
	public Map<String, Customer> getCustomerUsernames() {
		return customerUsernames;
	}

	public void setCustomerUsernames(Map<String, Customer> customerUsernames) {
		this.customerUsernames = customerUsernames;
	}

	public Map<String, Employee> getEmployeeUsernames() {
		return employeeUsernames;
	}

	public void setEmployeeUsernames(Map<String, Employee> employeeUsernames) {
		this.employeeUsernames = employeeUsernames;
	}
	
	public Employee getEmployee(String username) {
		return employeeUsernames.get(username);
	}

	public Map<String, Car> getCarLot() {
		return carLot;
	}

	public void setCarLot(Map<String, Car> carLot) {
		this.carLot = carLot;
	}

	public void removeCar(String carName) {
		if (carLot.remove(carName) != null) {
			SystemScreen.displayCarRemoved(carName);
		} else {
			SystemScreen.displayCarNotFound(carName);
		}
	}

	public Employee registerEmployee(String username) {
		if (employeeUsernames.containsKey(username)) {
			return null;
		} else {
			Employee newEmployee = new Employee(username);
			employeeUsernames.put(username, newEmployee);
			return newEmployee;
		}
	}
	
	public Customer registerCustomer(String username) {
		if (customerUsernames.containsKey(username)) {
			return null;
		} else {
			Customer newCustomer = new Customer(username);
			customerUsernames.put(username, newCustomer);
			return newCustomer;
		}
	}

	public void makeOffer(String carName, User user, double price, int monthsToPay) {
		Car car = getCar(carName);
		if (car == null) {
			SystemScreen.displayCarNotFound(carName);
			return;
		}
		
		Offer offer = new Offer(user, price, monthsToPay);
		car.addOffer(offer);
	
	}

	public void acceptOffer(String carName, String username) {
		Car car = getCar(carName);
		if (car == null) {
			SystemScreen.displayCarNotFound(carName);
			return;
		}
		
		if (car.getAcceptedOffer() != null) {
			SystemScreen.displayCarAlreadyAcceptedOffer(carName);
			return;
		}
		
		Offer offer = car.getPendingOffer(username);
		if (offer == null) {
			SystemScreen.displayCustomerNoOffer(username, carName);
			return;
		}
		
		offer.setAccepted(true);
		car.setAcceptedOffer(offer);
		removeAllPendingOffers(car);
		SystemScreen.displayCarOfferAccepted(username, carName);
	}

	public void removeOffer(String carName, String username) {
		Car car = getCar(carName);
		if (car == null) {
			SystemScreen.displayCarNotFound(carName);
			return;
		}
		
		if (car.getAcceptedOffer() != null) {
			SystemScreen.displayCarAlreadyAcceptedOffer(carName);
			return;
		}
		
		Offer offer = car.getPendingOffer(username);
		if (offer == null) {
			SystemScreen.displayCustomerNoOffer(username, carName);
			return;
		}
		
		offer.setAccepted(false);
		car.removeOffer(username);
		SystemScreen.displayCarOfferRemoved(username, carName);
	}
	
	private void removeAllPendingOffers(Car car) {
		car.removePendingOffers();
	}
	
}
