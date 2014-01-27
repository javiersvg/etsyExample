package org.caponford.renman.pages;

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.className;

public class RegistrationPage extends FluentWebDriverPage{
    public static final String TITLE = "RenmanMB.com - Community";

    public RegistrationPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    public void setEmail(String email) {
        input(className("username")).sendKeys(email);
    }

    public void setScreenName(String screenName) {
        input(className("screen_name")).sendKeys(screenName);
    }

    public void clickSubmit() {
        input(className("btn-submit")).click();
    }
}
