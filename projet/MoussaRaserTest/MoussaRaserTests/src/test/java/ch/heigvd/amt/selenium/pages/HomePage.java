package ch.heigvd.amt.selenium.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class is used to test the "Home" page in the MVCDemo app.
 * 
 * @author Olivier Liechti
 */
public class HomePage extends AbstractMoussaRaserPage {

    By tfLogoutLocator = By.id("logout");
    By tfListUsersLocator = By.id("listusers");

  public HomePage(WebDriver driver) {
    super(driver);

    // Check that we're on the right page.
    if (!"Welcome".equals(driver.getTitle())) {
      throw new IllegalStateException("This is not the correct page");
    }
  }
  
public Page submitLogout(Class<? extends Page> expectedPageClass) {
    driver.findElement(tfLogoutLocator).click();
    Page targetPage = null;
    try {
      targetPage = expectedPageClass.getConstructor(WebDriver.class).newInstance(driver);
    } catch (Exception ex) {
      Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
      throw new RuntimeException("Exception when using reflection: " + ex.getMessage());
    }
    return targetPage;
  }

public Page submitListUsers(Class<? extends Page> expectedPageClass) {
    driver.findElement(tfListUsersLocator).click();
    Page targetPage = null;
    try {
      targetPage = expectedPageClass.getConstructor(WebDriver.class).newInstance(driver);
    } catch (Exception ex) {
      Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
      throw new RuntimeException("Exception when using reflection: " + ex.getMessage());
    }
    return targetPage;
  }

  
}
