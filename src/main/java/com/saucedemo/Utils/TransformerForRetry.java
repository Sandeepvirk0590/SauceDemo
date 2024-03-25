package com.saucedemo.Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import com.saucedemo.base.SauceDemoTestBase;

public class TransformerForRetry extends SauceDemoTestBase implements IAnnotationTransformer {

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {

		annotation.setRetryAnalyzer(RetryFailedTests.class);
	}
}
