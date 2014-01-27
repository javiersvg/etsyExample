package org.caponford.renman.pages;

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.partialLinkText;

public class HomePage extends FluentWebDriverPage {
    public HomePage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    public void go() {
        get("http://www.renmanmb.com");
    }

    public void clickToSection(String section) {
        link(partialLinkText(section)).click();
    }

    public void clickRegisterLink() {
        div(className("top")).link(partialLinkText("Register"));
    }
}
