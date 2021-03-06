/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.selenium.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author Mario Ferreira
 */
public class RegistrationPage extends AbstractMoussaRaserPage {

    By tfEmailLocator = By.id("email");
    By tfFNameLocator = By.id("fname");
    By tfLNameLocator = By.id("lname");
    By tfPasswordLocator = By.id("password");
    By tfRPasswordLocator = By.id("rpassword");
    By bRegisterLocator = By.id("bRegister");

    public RegistrationPage(WebDriver driver) {
        super(driver);

        // Check that we're on the right page.
        if (!"Registration".equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the correct page");
        }
    }

    public RegistrationPage typeEmailAddress(String email) {
        driver.findElement(tfEmailLocator).sendKeys(email);
        return this;
    }

    public RegistrationPage typePassword(String password) {
        driver.findElement(tfPasswordLocator).sendKeys(password);
        return this;
    }

    public RegistrationPage typeRPassword(String rpassword) {
        driver.findElement(tfRPasswordLocator).sendKeys(rpassword);
        return this;
    }

    public RegistrationPage typeFName(String fname) {
        driver.findElement(tfFNameLocator).sendKeys(fname);
        return this;
    }

    public RegistrationPage typeLName(String lname) {
        driver.findElement(tfLNameLocator).sendKeys(lname);
        return this;
    }

    public Page submitForm(Class<? extends Page> expectedPageClass) {
        driver.findElement(bRegisterLocator).click();
        Page targetPage = null;
        try {
            targetPage = expectedPageClass.getConstructor(WebDriver.class).newInstance(driver);
        } catch (Exception ex) {
            Logger.getLogger(RegistrationPage.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Exception when using reflection: " + ex.getMessage());
        }

        return targetPage;
    }

}
