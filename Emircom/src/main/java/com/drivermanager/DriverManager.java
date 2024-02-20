package com.drivermanager;

import org.openqa.selenium.WebDriver;

public final class DriverManager {
	
	private DriverManager() {
		
	}
	
	//ThreadLocal is used to provide Thread Safety
	private static ThreadLocal<WebDriver> dr = new ThreadLocal<>();

	public static WebDriver getDriver(){
		return dr.get();
	}

	public static void setDriver(WebDriver driverref){
		dr.set(driverref);
	}

	public static void unLoad(){
		dr.remove();
	}

}
