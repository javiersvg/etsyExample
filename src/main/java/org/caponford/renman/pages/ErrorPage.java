package org.caponford.renman.pages;

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.seleniumhq.selenium.fluent.FluentWebElements;

import static org.openqa.selenium.By.id;

import java.util.List;

public class ErrorPage extends FluentWebDriverPage{
    public static final String TITLE = "Error";

    public ErrorPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    public FluentWebElements getErrorMessages() {
        return div(id("content")).lis();
    }
}
