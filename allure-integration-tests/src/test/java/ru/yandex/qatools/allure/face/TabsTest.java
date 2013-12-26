package ru.yandex.qatools.allure.face;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class TabsTest {

    WebDriver driver;

    @Before
    public void setupWebdriver() {
        driver = new PhantomJSDriver(new DesiredCapabilities());
    }


    @Before
    public void openBrowser() throws Exception {
        String port = System.getProperty("jetty.port");
        String url = String.format("http://localhost:%s/main", port);
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".tab-content")));
    }

    public void checkHash(String expectedHash) {
        String url = driver.getCurrentUrl();
        String hash = url.substring(url.indexOf('#'));
        assertEquals(expectedHash, hash);
    }

    @Test
    public void homeTab() throws Exception {
        checkHash("#/home");
    }

    @Test
    public void behaviorsTab() throws Exception {
        driver.findElement(By.cssSelector(".b-vert__icon.glyphicon-list")).click();
        checkHash("#/features");
    }

    @Test
    public void graphTab() throws Exception {
        driver.findElement(By.cssSelector(".b-vert__icon.glyphicon-stats")).click();
        checkHash("#/graph");
    }

    @Test
    public void timelineTab() throws Exception {
        driver.findElement(By.cssSelector(".b-vert__icon.glyphicon-time")).click();
        checkHash("#/timeline");
    }

    @After
    public void quit() {
        driver.quit();
    }

}
