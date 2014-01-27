package org.caponford.jbehave;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.jbehave.core.failures.UUIDExceptionWrapper;
import org.jbehave.core.reporters.HtmlOutput;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.web.selenium.SauceWebDriverProvider;
import org.jbehave.web.selenium.WebDriverProvider;
import org.jbehave.web.selenium.WebDriverScreenshotOnFailure;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.PrintStream;
import java.security.GeneralSecurityException;
import java.security.Key;

public class ScreenshootingHtmlOutput extends HtmlOutput {

    private final WebDriverProvider webDriverProvider;
    private final Environment env;
    private WebDriverScreenshotOnFailure screenshotMaker;

    public ScreenshootingHtmlOutput(PrintStream output,
                                    StoryReporterBuilder reporterBuilder,
                                    ApplicationContext context) {
        super(output, reporterBuilder.keywords());
        this.webDriverProvider = (WebDriverProvider) context.getBean("driverProvider");
        this.env = context.getEnvironment();
        screenshotMaker = new WebDriverScreenshotOnFailure(
                this.webDriverProvider);
    }



    private String noLoginLinkFor(SessionId jobId) {
        String authCode = generateHMACFor(SauceWebDriverProvider.getSauceUser() + ":" + SauceWebDriverProvider.getSauceUser(), jobId);
        return "https://saucelabs.com/video-embed/" + jobId + ".js?auth=" + authCode;
    }

    private String generateHMACFor(String secretKey, SessionId sessionId) {
        try {
            byte[] keyBytes = secretKey.getBytes();
            Key key = new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacMD5");
            Mac mac = Mac.getInstance("HmacMD5");
            mac.init(key);
            return new String(Hex.encodeHex(mac.doFinal(sessionId.toString().getBytes())));
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("Could not generate HMAC for some reason", e);
        }
    }

    private void overrideFailedLine() {
        if (webDriverProvider.get() instanceof RemoteWebDriver) {
            RemoteWebDriver remoteWebDriver = (RemoteWebDriver) webDriverProvider.get();
            overwritePattern("failed",
                    "<div class=\"step failed\">{0} <span class=\"keyword failed\">({1})</span><br/><span class=\"message failed\">{2}</span>"
                            + "<br/><a color=\"black\" target=\"jb_scn_shot\" href=\"../screenshots/failed-scenario-{3}.png\"><img src=\"images/failing_screenshot.png\" alt=\"failing screenshot\"/></a></div>\n"
                            + getSauceVideoLink(remoteWebDriver));
        }
    }

    private String getSauceVideoLink(RemoteWebDriver remoteWebDriver) {
        for (String profile : env.getActiveProfiles()) {
            if ("sauceWebDriver".equals(profile)) {
                return "<script src=\""
                        + noLoginLinkFor(remoteWebDriver.getSessionId())
                        + "\"></script>\n";
            }
        }
        return StringUtils.EMPTY;
    }

    @Override
    public void failed(String step, Throwable storyFailure) {
        overrideFailedLine();
        super.failed(step, storyFailure);
        try {
            UUIDExceptionWrapper uuidWrappedFailure =
                    (UUIDExceptionWrapper) storyFailure;
            screenshotMaker.afterScenarioFailure(uuidWrappedFailure);
        } catch (Exception e) {
            System.out.println("Screenshot failed");
        }
    }
}
