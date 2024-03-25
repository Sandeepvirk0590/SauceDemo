package com.saucedemo.base;

public enum Browsers {
	
	CHROME("chrome"),
    FIREFOX("firefox"),
    EDGE("edge");

    private String browserName;

    Browsers(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserName() {
        return browserName;
    }
	
}
