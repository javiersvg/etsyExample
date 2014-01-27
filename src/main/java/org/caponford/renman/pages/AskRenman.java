package org.caponford.renman.pages;

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

public class AskRenman extends FluentWebDriverPage{
    public static final String TITLE = "Ask Renman";

    public AskRenman(WebDriverProvider driverProvider) {
        super(driverProvider);
    }
}
