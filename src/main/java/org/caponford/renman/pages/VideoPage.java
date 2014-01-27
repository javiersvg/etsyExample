package org.caponford.renman.pages;

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

public class VideoPage extends FluentWebDriverPage{
    public static final String TITLE = "Video";

    public VideoPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }
}
