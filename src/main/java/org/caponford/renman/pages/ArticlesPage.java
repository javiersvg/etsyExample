package org.caponford.renman.pages;

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

public class ArticlesPage  extends FluentWebDriverPage{
    public static final String TITLE = "Articles";

    public ArticlesPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }
}
