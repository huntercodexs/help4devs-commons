package com.huntercodexs.demo.services.web;

import com.huntercodexs.demo.services.web.constant.Help4DevsBrowserForSelenium;
import com.huntercodexs.demo.services.web.constant.Help4DevsBrowserForSeleniumDto;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Help4DevsWebControl {

    private final Help4DevsWebDriver help4DevsWebDriver;
    private final Help4DevsBrowserForSeleniumDto codexsBrowserDto;

    public Help4DevsWebControl(Help4DevsBrowserForSeleniumDto help4DevsBrowserForSeleniumDto) {
        this.help4DevsWebDriver = new Help4DevsWebDriver();
        this.codexsBrowserDto = help4DevsBrowserForSeleniumDto;
    }

    private void chromeSetup() {
        ChromeOptions options = new ChromeOptions();

        if (this.codexsBrowserDto.getOptions() != null) {
            options.addArguments(this.codexsBrowserDto.getOptions());
        }

        if (this.codexsBrowserDto.isQuietMode()) {
            options.addArguments("-headless");
        }

        this.help4DevsWebDriver.setDriver(new ChromeDriver(options));
    }

    private void firefoxSetup() {
        FirefoxOptions options = new FirefoxOptions();

        if (this.codexsBrowserDto.getOptions() != null) {
            options.addArguments(this.codexsBrowserDto.getOptions());
        }

        if (this.codexsBrowserDto.isQuietMode()) {
            options.addArguments("-headless");
        }

        this.help4DevsWebDriver.setDriver(new FirefoxDriver(options));
    }

    private void operaSetup() {
        OperaOptions options = new OperaOptions();
        options.addArguments(this.codexsBrowserDto.getOptions());

        if (this.codexsBrowserDto.isQuietMode()) {
            options.addArguments("-headless");
        }

        if (this.codexsBrowserDto.getBrowserBinaryPath() != null) {
            options.setBinary(this.codexsBrowserDto.getBrowserBinaryPath());
        }

        this.help4DevsWebDriver.setDriver(new OperaDriver(options));
    }

    private void netscapeSetup() {
        this.firefoxSetup();
    }

    private void safariSetup() {
        SafariOptions options = new SafariOptions();
        this.help4DevsWebDriver.setDriver(new SafariDriver(options));
    }

    private void ieSetup() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        this.help4DevsWebDriver.setDriver(new InternetExplorerDriver(options));
    }

    private void edgeSetup() {
        EdgeOptions options = new EdgeOptions();
        this.help4DevsWebDriver.setDriver(new EdgeDriver(options));
    }

    public void browserSetup() {

        System.setProperty(this.codexsBrowserDto.getWebDriverName(), this.codexsBrowserDto.getWebDriverPath());

        if (this.codexsBrowserDto.getBrowser().name().equals(Help4DevsBrowserForSelenium.IE.name())) {
            this.ieSetup();
        } else if (this.codexsBrowserDto.getBrowser().name().equals(Help4DevsBrowserForSelenium.EDGE.name())) {
            this.edgeSetup();
        } else if (this.codexsBrowserDto.getBrowser().name().equals(Help4DevsBrowserForSelenium.CHROME.name())) {
            this.chromeSetup();
        } else if (this.codexsBrowserDto.getBrowser().name().equals(Help4DevsBrowserForSelenium.FIREFOX.name())) {
            this.firefoxSetup();
        } else if (this.codexsBrowserDto.getBrowser().name().equals(Help4DevsBrowserForSelenium.NETSCAPE.name())) {
            this.netscapeSetup();
        } else if (this.codexsBrowserDto.getBrowser().name().equals(Help4DevsBrowserForSelenium.OPERA.name())) {
            this.operaSetup();
        } else if (this.codexsBrowserDto.getBrowser().name().equals(Help4DevsBrowserForSelenium.SAFARI.name())) {
            this.safariSetup();
        }

        this.help4DevsWebDriver.getDriver().manage().window().maximize();
        this.help4DevsWebDriver.setWait(new WebDriverWait(this.help4DevsWebDriver.getDriver(), 15L));

    }

    public WebDriver driver() {
        return this.help4DevsWebDriver.getDriver();
    }

    public WebDriverWait await() {
        return this.help4DevsWebDriver.getWait();
    }

    public void navigate(String url) {
        if (this.codexsBrowserDto.getBrowser().name().equals(Help4DevsBrowserForSelenium.FIREFOX.name())) {
            this.help4DevsWebDriver.getDriver().navigate().to(url);
        } else {
            this.help4DevsWebDriver.getDriver().get(url);
        }
    }

    public void finish() {
        try {
            // Force login page alive for 5 seconds (just for visualize the login successfully)
            Thread.sleep(2000);
            this.help4DevsWebDriver.getDriver().quit();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
