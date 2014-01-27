package org.caponford.renman.pages;

import org.jbehave.web.selenium.WebDriverProvider;

public class PageFactory {

    private final WebDriverProvider webDriverProvider;

    public PageFactory(WebDriverProvider webDriverProvider) {
        this.webDriverProvider = webDriverProvider;
    }

    public HomePage newHome() {
        return new HomePage(webDriverProvider);
    }

    public LivePage newLife() {
        return new LivePage(webDriverProvider);
    }

    public VideoPage newVideo() {
        return new VideoPage(webDriverProvider);
    }

    public AskRenman newAskRenman() {
        return new AskRenman(webDriverProvider);
    }

    public ArticlesPage newArticles() {
        return new ArticlesPage(webDriverProvider);
    }

    public CommunityPage newCommunity() {
        return new CommunityPage(webDriverProvider);
    }

    public RegistrationPage newRegistration() {
        return new RegistrationPage(webDriverProvider);
    }

    public ErrorPage newError() {
        return new ErrorPage(webDriverProvider);
    }
}
