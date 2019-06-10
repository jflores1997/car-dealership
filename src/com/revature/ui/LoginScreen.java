package com.revature.ui;

import com.revature.util.LoggerUtil;

public class LoginScreen implements Screen {

	private static final String USERNAME_MSG = "Please input username: ";
	private static final String PASSWORD_MSG = "Please input password: ";
	private static final String USER_TYPE_MSG = "Are you a Customer or an Employee?";
	
	private static final String SUCCESSFUL_LOGIN = "Login Successful";
	private static final String SUCCESSFUL_REGISTER = "Register Successful";
	
	private static final String INVALID_LOGIN_MSG = "Invalid login";
	private static final String INVALID_INPUT_MSG = "Invalid input";
	private static final String NON_UNIQUE_USERNAME = "Username not unique";
	
	public static void displayInputUsername() {
		LoggerUtil.trace(USERNAME_MSG);
	}
	
	public static void displayInputPassword() {
		LoggerUtil.trace(PASSWORD_MSG);
	}
	
	public static void displayInvalidLogin() {
		LoggerUtil.trace(INVALID_LOGIN_MSG);
	}
	
	public static void displayUserType() {
		LoggerUtil.trace(USER_TYPE_MSG);
	}
	
	public static void displayInvalidInput() {
		LoggerUtil.trace(INVALID_INPUT_MSG);
	}
	
	public static void displayNonUniqueUsername() {
		LoggerUtil.trace(NON_UNIQUE_USERNAME);
	}

	public static void displaySuccessfulLogin(String username) {
		LoggerUtil.trace(SUCCESSFUL_LOGIN + " " + username + "!");
	}
	
	public static void displaySuccessfulRegister(String username) {
		LoggerUtil.trace(SUCCESSFUL_REGISTER + " " + username + "!");
	}
}
