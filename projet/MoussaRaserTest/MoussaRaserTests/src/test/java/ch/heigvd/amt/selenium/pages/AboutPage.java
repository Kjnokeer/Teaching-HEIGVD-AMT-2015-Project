package ch.heigvd.amt.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class is used to test the "About" page in the MVCDemo app.
 * 
 * @author Olivier Liechti
 */
public class AboutPage extends AbstractMoussaRaserPage {

  public AboutPage(WebDriver driver) {
    super(driver);

    // Check that we're on the right page.
    if (!"About".equals(driver.getTitle())) {
      throw new IllegalStateException("This is not the correct page");
    }
  }
  
}
