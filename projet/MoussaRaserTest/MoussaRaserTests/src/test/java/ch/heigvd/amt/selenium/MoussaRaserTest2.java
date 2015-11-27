/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.selenium;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author Mario Ferreira
 */
public class MoussaRaserTest2 {
    private final String baseUrl = "localhost:8080/MoussaRaser/";
    private WebDriver driver;

    @Before
    public void openBrowser() {
        driver = new FirefoxDriver();
    }
    
}
