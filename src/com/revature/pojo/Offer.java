package com.revature.pojo;

import java.io.Serializable;

public class Offer implements Serializable {

	private static final long serialVersionUID = 8468508953739915810L;
	
	private User owner;
	private double totalPrice;
	private double remainingPrice;
	private int numberOfPaymentsRemaining;
	private boolean accepted;
	
	public Offer(User owner, double totalPrice, int monthsToPay) {
		super();
		this.owner = owner;
		this.totalPrice = totalPrice;
		
		numberOfPaymentsRemaining = monthsToPay;
		remainingPrice = totalPrice;
		setAccepted(false);
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getNumberOfPaymentsRemaining() {
		return numberOfPaymentsRemaining;
	}

	public void setNumberOfPaymentsRemaining(int numberOfPaymentsRemaining) {
		this.numberOfPaymentsRemaining = numberOfPaymentsRemaining;
	}

	public double getRemainingPrice() {
		return remainingPrice;
	}

	public void setRemainingPrice(double remainingPrice) {
		this.remainingPrice = remainingPrice;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public double getNextPayment() {
		if (numberOfPaymentsRemaining != 0) {
			return remainingPrice / numberOfPaymentsRemaining;
		}
		return 0;
	}

}
