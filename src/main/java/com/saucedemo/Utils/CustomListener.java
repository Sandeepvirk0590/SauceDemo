package com.saucedemo.Utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.saucedemo.base.SauceDemoTestBase;

public class CustomListener extends SauceDemoTestBase implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		logger.info("***************Test Case Started******************" + result.getMethod().getMethodName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("******Passed********" + result.getMethod().getMethodName() + "*****************");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		logger.info("!!!!!!!!!!!!!!Test Case Failed: " + methodName + " Taking Screenshot!!!!!!!!!!!!!!!!!!!!");
		SauceDemoTestBase.screenShotOfFailedTestCases(methodName);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.info("??????????????????Test Case skipped: " + result.getMethod().getMethodName() + "????????????????");

	}

	@Override
	public void onFinish(ITestContext context) {
	}

}
