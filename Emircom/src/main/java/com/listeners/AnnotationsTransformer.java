package com.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class AnnotationsTransformer implements IAnnotationTransformer {

	@Override
	/**
	 * For retrying the failed testcases
	 * 
	 */
	public void transform(ITestAnnotation annotation, @SuppressWarnings("rawtypes") Class testClass,
			@SuppressWarnings("rawtypes") Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryFailestests.class);
	}
}
