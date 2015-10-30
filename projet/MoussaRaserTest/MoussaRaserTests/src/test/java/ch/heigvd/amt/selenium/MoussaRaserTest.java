package ch.heigvd.amt.selenium;

import ch.heigvd.amt.selenium.pages.HomePage;
import ch.heigvd.amt.selenium.pages.LoginPage;
import ch.heigvd.amt.selenium.pages.RegistrationPage;
import io.probedock.client.annotations.ProbeTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author Mario Ferreira (mario.ferreira@heig-vd.ch)
 */
public class MoussaRaserTest {

  private final String baseUrl = "localhost:8080/MoussaRaser/";
  private WebDriver driver;

  @Before
  public void openBrowser() {
    driver = new FirefoxDriver();
    //System.setProperty("webdriver.chrome.driver", "/Users/admin/Downloads/chromedriver");
    //driver = new ChromeDriver();
  }
  
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldCorrectlyCreateAccount() {
    driver.get(baseUrl + "registrationPage");
    RegistrationPage registrationPage = new RegistrationPage(driver);
    registrationPage.typeEmailAddress("thibaud.duchoud@heig-vd.ch");
    registrationPage.typeFName("Thibaud");
    registrationPage.typeLName("Duchoud");
    registrationPage.typeRPassword("4321");
    registrationPage.typePassword("4321");
    LoginPage loginPage = (LoginPage)registrationPage.submitForm(LoginPage.class);
  }
  
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToCreateAccountWithoutFName() {
    driver.get(baseUrl + "registrationPage");
    RegistrationPage registrationPage = new RegistrationPage(driver);
    registrationPage.typeEmailAddress("thibaud.duchoud1@heig-vd.ch");
    registrationPage.typeLName("Duchoud");
    registrationPage.typeRPassword("4321");
    registrationPage.typePassword("4321");
    RegistrationPage registrationPageF = (RegistrationPage)registrationPage.submitForm(RegistrationPage.class);
  }
  
   @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToCreateAccountWithoutLName() {
    driver.get(baseUrl + "registrationPage");
    RegistrationPage registrationPage = new RegistrationPage(driver);
    registrationPage.typeEmailAddress("thibaud.duchoud2@heig-vd.ch");
    registrationPage.typeFName("Thibaud");
    registrationPage.typeRPassword("4321");
    registrationPage.typePassword("4321");
    RegistrationPage registrationPageF = (RegistrationPage)registrationPage.submitForm(RegistrationPage.class);
  }
  
   @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToCreateAccountWithoutEmail() {
    driver.get(baseUrl + "registrationPage");
    RegistrationPage registrationPage = new RegistrationPage(driver);
    registrationPage.typeFName("Thibaud");
    registrationPage.typeLName("Duchoud");
    registrationPage.typeRPassword("4321");
    registrationPage.typePassword("4321");
    RegistrationPage registrationPageF = (RegistrationPage)registrationPage.submitForm(RegistrationPage.class);
  }
  
   @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToCreateAccount2DifferentPasswords() {
    driver.get(baseUrl + "registrationPage");
    RegistrationPage registrationPage = new RegistrationPage(driver);
    registrationPage.typeEmailAddress("thibaud.duchoud3@heig-vd.ch");
    registrationPage.typeFName("Thibaud");
    registrationPage.typeLName("Duchoud");
    registrationPage.typeRPassword("4321");
    registrationPage.typePassword("1234");
    RegistrationPage registrationPageF = (RegistrationPage)registrationPage.submitForm(RegistrationPage.class);
  }
  
   @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToCreateAccountWithExistantEmail() {
    driver.get(baseUrl + "registrationPage");
    RegistrationPage registrationPageTmp = new RegistrationPage(driver);
    registrationPageTmp.typeEmailAddress("mario.ferreira@heig-vd.ch");
    registrationPageTmp.typeFName("Mario");
    registrationPageTmp.typeLName("Ferreira");
    registrationPageTmp.typeRPassword("1234");
    registrationPageTmp.typePassword("1234");
    
    driver.close();
      
    driver.get(baseUrl + "registrationPage");
    RegistrationPage registrationPage = new RegistrationPage(driver);
    registrationPage.typeEmailAddress("mario.ferreira@heig-vd.ch");
    registrationPage.typeFName("Mario");
    registrationPage.typeLName("Ferreira");
    registrationPage.typeRPassword("1234");
    registrationPage.typePassword("1234");
    RegistrationPage registrationPageF = (RegistrationPage)registrationPage.submitForm(RegistrationPage.class);
  }

  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToSigninWithAnInvalidEmail() {
    driver.get(baseUrl);
    LoginPage loginPage = new LoginPage(driver);
    loginPage.typeEmailAddress("this is not a valid email address");
    loginPage.typePassword("any password");
    loginPage.submitFormExpectingFailure();
  }
  
  @Test
  @ProbeTest(tags = "WebUI")
  public void successfulSigninShouldBringUserToHomePage() {
    // Create account
    driver.get(baseUrl + "registrationPage");
    RegistrationPage registrationPage = new RegistrationPage(driver);
    registrationPage.typeEmailAddress("mario.ferreira@heig-vd.ch");
    registrationPage.typeFName("Mario");
    registrationPage.typeLName("Ferreira");
    registrationPage.typeRPassword("1234");
    registrationPage.typePassword("1234");
    
    driver.close();
    
    
    driver.get(baseUrl);
    LoginPage loginPage = new LoginPage(driver);
    loginPage.typeEmailAddress("mario.ferreira@heig-vd.ch");
    loginPage.typePassword("1234");
    HomePage homePage = (HomePage)loginPage.submitForm(HomePage.class);
  }

  /*@Test
  @ProbeTest(tags = "WebUI")
  public void aUserTryingToGetToAboutPageShouldBeRedirectedThereAfterSignin() {
    driver.get(baseUrl + "/pages/about");
    LoginPage loginPage = new LoginPage(driver);
    loginPage.typeEmailAddress("a@a.com");
    loginPage.typePassword("any password");
    AboutPage aboutPage = (AboutPage)loginPage.submitForm(AboutPage.class);
  }

  @Test
  @ProbeTest(tags = "WebUI")
  public void aUserShouldBeAbleToVisitAllPagesAfterSignin() {
    driver.get(baseUrl);
    LoginPage loginPage = new LoginPage(driver);
    loginPage.typeEmailAddress("a@a.com");
    loginPage.typePassword("any password");
    HomePage homePage = (HomePage)loginPage.submitForm(HomePage.class);
    homePage.goToBeersPageViaMenu()
      .goToBeersPageViaMenu()
      .goToAJAXPageViaMenu()
      .goToGenerateTestDataPageViaMenu()
      .goToCorporateInformationPageViaMenu();
  }

  @Test
  @ProbeTest(tags = "WebUI")
  public void aUserShouldBeAbleToGetDetailsInformationAboutACompany() {
    driver.get(baseUrl);
    LoginPage loginPage = new LoginPage(driver);
    loginPage.typeEmailAddress("a@a.com");
    loginPage.typePassword("any password");
    HomePage homePage = (HomePage)loginPage.submitForm(HomePage.class);
    homePage.goToCorporateInformationPageViaMenu()
      .clickOnFirstCompanyLinkInCompaniesTable();
  }*/
  
  @After
  public void closeBrowser() {
    driver.close();
  }
}
