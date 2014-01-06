package org.caponford.etsy.pages;

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

import static org.openqa.selenium.By.*;
import static org.seleniumhq.selenium.fluent.Period.secs;

public class Home extends FluentWebDriverPage {

    public Home(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public void go() {
        get("http://www.etsy.com");
    }

    public void go(String section) {
        go();
        link(xpath("@title = '" + section + "'")).click();
    }

    public void search(String thing) {
        input(id("autocomplete-field")).sendKeys(thing);
        button(name("search_submit")).click();
    }

    public void goToBuySection() {
        link(linkText("Buy")).click();
    }

    public int cartSize() {
        return Integer.parseInt(within(secs(2)).div(id("cart_ref-count")).getText().toString());
    }
}
