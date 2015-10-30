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
public class CreateAppPage extends AbstractMoussaRaserPage{
    By tfNameLocator = By.id("name");
    By tfDescriptionLocator = By.id("description");
    By bCreateAppLocator = By.id("bCreateApp");

    public CreateAppPage(WebDriver driver) {
        super(driver);
        
        // Check that we're on the right page.
        if (!"Create App".equals(driver.getTitle())) {
          throw new IllegalStateException("This is not the correct page");
        }
    }
    
    public CreateAppPage typeName(String name) {
        driver.findElement(tfNameLocator).sendKeys(name);
        return this;
    }
    public CreateAppPage typeDescription(String description) {
        driver.findElement(tfDescriptionLocator).sendKeys(description);
        return this;
    }
    
    public Page submitForm(Class<? extends Page> expectedPageClass) {
        driver.findElement(bCreateAppLocator).click();
        Page targetPage = null;
        try {
            targetPage = expectedPageClass.getConstructor(WebDriver.class).newInstance(driver);
        } catch (Exception ex) {
            Logger.getLogger(CreateAppPage.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Exception when using reflection: " + ex.getMessage());
        }
        
        return targetPage;
    }
    
}
