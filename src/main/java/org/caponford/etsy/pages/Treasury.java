package org.caponford.etsy.pages;

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.linkText;

public class Treasury extends FluentWebDriverPage {

    public Treasury(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public void chooseAbtractsPaintingsGallery() {
        div(By.className("content-wrap")).link(By.partialLinkText("Abstract Paintings")).click();
    }

}
