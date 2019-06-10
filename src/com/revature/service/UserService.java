package com.revature.service;

import java.util.Scanner;

public interface UserService {
	
	/*
	 * Returns false if program is exiting
	 * Returns true if User is only signing out
	 */
	public boolean userMainPage(Scanner scanner);
	
	public void exit();
	
	public void signOut();
	
	public void viewCarLot();
	
}
