package com.revature.ui;

import com.revature.util.LoggerUtil;

public class WelcomeScreen implements Screen {
	
	private static final String WELCOME_MSG = "Welcome to the Dealership!\nEnter \"login\""
			+ " or \"register\" to continue.";
		
	public static void displayWelcome() {
		LoggerUtil.trace(WELCOME_MSG);
	}
	
	
}
