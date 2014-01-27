package org.caponford.renman.steps;

import org.caponford.renman.pages.*;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

public class NavigationSteps {

    private final HomePage home;
    private LivePage live;
    private VideoPage video;
    private AskRenman askRenman;
    private ArticlesPage articles;

    private CommunityPage community;

    public NavigationSteps(PageFactory pageFactory){
        home = pageFactory.newHome();
        live = pageFactory.newLife();
        video = pageFactory.newVideo();
        askRenman = pageFactory.newAskRenman();
        articles = pageFactory.newArticles();
        community = pageFactory.newCommunity();
    }

    @Given("I am on renmanmb.com")
    public void goToRenmanmbLandingPage() {
        home.go();
    }

    @When("I navigate to the $section section")
    public void navigateToSection(String section) {
        home.clickToSection(section);
    }

    @When("I go to the Registration page")
    public void navigateRegistrationPage () {
        home.clickRegisterLink();
    }

    @Then("I see the LIVE page")
    public void verifyLivePagePresent() {
        assertThat(live.getTitle(), containsString(LivePage.TITLE));
    }

    @Then("I see the VIDEO page")
    public void verifyVideoPagePresent() {
        assertThat(video.getTitle(), containsString(VideoPage.TITLE));
    }

    @Then("I see the ASK RENMAN page")
    public void verifyAskRenmanPagePresent() {
        assertThat(askRenman.getTitle(), containsString(AskRenman.TITLE));
    }

    @Then("I see the ARTICLES page")
    public void verifyArticlesPagePresent() {
        assertThat(articles.getTitle(), containsString(ArticlesPage.TITLE));
    }

    @Then("I see the COMMUNITY page")
    public void verifyCommunityPagePresent() {
        assertThat(community.getTitle(), containsString(CommunityPage.TITLE));
    }
}
