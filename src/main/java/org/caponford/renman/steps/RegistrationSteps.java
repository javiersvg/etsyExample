package org.caponford.renman.steps;

import org.caponford.renman.pages.ErrorPage;
import org.caponford.renman.pages.PageFactory;
import org.caponford.renman.pages.RegistrationPage;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebElement;
import org.seleniumhq.selenium.fluent.FluentMatcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.StringContains.containsString;

public class RegistrationSteps {

    private RegistrationPage registration;
    private ErrorPage error;

    public RegistrationSteps(PageFactory pageFactory) {
        registration = pageFactory.newRegistration();
        error = pageFactory.newError();
    }

    @When("I register with an existing mail and username")
    public void registerWithExistingMailAndUsername() {
        registration.setEmail("user@mail.com");
        registration.setScreenName("asd");
        registration.clickSubmit();
    }

    @Then("I see the registration page")
    public void verifyRegistrationPagePresent() {
        assertThat(registration.getTitle(), containsString(RegistrationPage.TITLE));
    }

    @Then("I see the error page")
    public void verifyErrorPagePresent() {
        assertThat(error.getTitle(), containsString(ErrorPage.TITLE));
    }

    @Then("I see an error message indicating that the mail is nor available nor confirmed either")
    public void verifyUsedButNotConfirmedErrorMessage() {
        error.getErrorMessages().filter(new FluentMatcher() {
            public boolean matches(WebElement webElement) {
                return webElement.getText().contains("the mail is nor available nor confirmed either");
            }
        });
    }
}
