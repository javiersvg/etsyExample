package org.caponford.renman.pages;

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

public class CommunityPage extends FluentWebDriverPage{
    public static final String TITLE = "Community";

    public CommunityPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }
}
