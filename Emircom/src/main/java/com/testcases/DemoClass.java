package com.testcases;

import org.testng.annotations.Test;

public class DemoClass extends BaseClass {

	@Test(priority = 0)
	private void ranji() {
		System.out.println("ranji");

	}
	
	@Test(priority = 1)
	private void barath() {
		System.out.println("barath");

	}
	
	@Test(priority = 2)
	private void raja() {
		System.out.println("raja");
	}
	
}
