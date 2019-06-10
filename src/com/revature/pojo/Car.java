package com.revature.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.revature.ui.UserScreen;

public class Car implements Serializable {
	
	private static final long serialVersionUID = 8180529011551895391L;

	private String name;
	
	/* Maps usernames to offers */
	private Map<String, Offer> pendingOffers;
	private Offer acceptedOffer;
	
	public Car(String name) {
		super();
		this.name = name;
		pendingOffers = new HashMap<String, Offer>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getOwner() {
		if (acceptedOffer != null) {
			return acceptedOffer.getOwner();
		}
		return null;
	}

	public Offer getPendingOffer(String username) {
		return pendingOffers.get(username);
	}

	public Offer getAcceptedOffer() {
		return acceptedOffer;
	}

	public void setAcceptedOffer(Offer acceptedOffer) {
		this.acceptedOffer = acceptedOffer;
	}

	public void addOffer(Offer offer) {
		if (acceptedOffer != null) {
			UserScreen.displayCarAlreadyTaken(getName());
			return;
		}
		
		String username = offer.getOwner().getUsername();
		if (pendingOffers.containsKey(username)) {
			UserScreen.displayOfferUpdated(name);
		} else {
			UserScreen.displayOfferAdded(name);
		}
		pendingOffers.put(username, offer);
	}

	public Map<String, Offer> getPendingOffers() {
		return pendingOffers;
	}

	public void removePendingOffers() {
		pendingOffers.clear();
	}

	public void removeOffer(String username) {
		pendingOffers.remove(username);
	}

}
