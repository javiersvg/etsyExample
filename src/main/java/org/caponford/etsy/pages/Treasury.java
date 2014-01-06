package org.caponford.etsy.pages;

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.linkText;

public class Treasury extends FluentWebDriverPage {

    public Treasury(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public void chooseAbtractsPaintingsGallery() {
        link(linkText("\n" +
                "                        Abstract Paintings\n" +
                "                                                ")).h3().link().click();
    }

}
