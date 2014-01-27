package org.caponford.renman.pages;

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

public class LivePage extends FluentWebDriverPage{
    public static final String TITLE = "Live";

    public LivePage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }
}
