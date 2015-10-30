package ch.heigvd.amt.selenium;

import ch.heigvd.amt.selenium.pages.CreateAppPage;
import ch.heigvd.amt.selenium.pages.EditAppPage;
import ch.heigvd.amt.selenium.pages.EditProfilePage;
import ch.heigvd.amt.selenium.pages.HomePage;
import ch.heigvd.amt.selenium.pages.ListUsersPage;
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
  
  
  // Registration page
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
  public void itShouldNotBePossibleToCreateAccountWithoutPassword() {
    driver.get(baseUrl + "registrationPage");
    RegistrationPage registrationPage = new RegistrationPage(driver);
    registrationPage.typeEmailAddress("thibaud.duchoud4@heig-vd.ch");
    registrationPage.typeFName("Thibaud");
    registrationPage.typeLName("Duchoud");
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

  // ---------------------------------------------------------------------------
  
  // Login Page
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

  // ---------------------------------------------------------------------------
  
  // Edit profile page
  
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldModifyProfile() {
    driver.get(baseUrl + "editProfile");
    EditProfilePage editProfilePage = new EditProfilePage(driver);
    editProfilePage.typeFName("Mario");
    editProfilePage.typeLName("Ferreira");
    editProfilePage.typeRPassword("5678");
    editProfilePage.typePassword("5678");
    HomePage homePage = (HomePage)editProfilePage.submitForm(HomePage.class);
  }
  
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToModifyAccount2DifferentPasswords() {
    driver.get(baseUrl + "editProfile");
    EditProfilePage editProfilePage = new EditProfilePage(driver);
    editProfilePage.typeFName("Mario");
    editProfilePage.typeLName("Ferreira");
    editProfilePage.typeRPassword("5678");
    editProfilePage.typePassword("8765");
    EditProfilePage editProfilePageF = (EditProfilePage)editProfilePage.submitForm(EditProfilePage.class);
  }
  
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToModifyAccountWithoutFName() {
    driver.get(baseUrl + "editProfile");
    EditProfilePage editProfilePage = new EditProfilePage(driver);
    editProfilePage.typeLName("Ferreira");
    editProfilePage.typeRPassword("5678");
    editProfilePage.typePassword("5678");
    EditProfilePage editProfilePageF = (EditProfilePage)editProfilePage.submitForm(EditProfilePage.class);
  }
  
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToModifyAccountWithoutLName() {
    driver.get(baseUrl + "editProfile");
    EditProfilePage editProfilePage = new EditProfilePage(driver);
    editProfilePage.typeFName("Mario");
    editProfilePage.typeRPassword("5678");
    editProfilePage.typePassword("5678");
    EditProfilePage editProfilePageF = (EditProfilePage)editProfilePage.submitForm(EditProfilePage.class);
  }
  
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToModifyAccountWithoutPassword() {
    driver.get(baseUrl + "editProfile");
    EditProfilePage editProfilePage = new EditProfilePage(driver);
    editProfilePage.typeFName("Mario");
    editProfilePage.typeLName("Ferreira");
    EditProfilePage editProfilePageF = (EditProfilePage)editProfilePage.submitForm(EditProfilePage.class);
  }
  
  //----------------------------------------------------------------------------
  
  // Create App
  
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldCreateApp() {
    driver.get(baseUrl + "addApp");    
    CreateAppPage createAppPage = new CreateAppPage(driver);
    createAppPage.typeDescription("Test Description");
    createAppPage.typeName("Test Name");
    HomePage homePage = (HomePage)createAppPage.submitForm(HomePage.class);
  }
  
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToCreateAppWithoutName() {
    driver.get(baseUrl + "addApp");    
    CreateAppPage createAppPage = new CreateAppPage(driver);
    createAppPage.typeDescription("Test Description");
    CreateAppPage createAppPageF = (CreateAppPage)createAppPage.submitForm(CreateAppPage.class);
  }
  
  //----------------------------------------------------------------------------
  
  // Edit App
  
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldModifyApp() {
    driver.get(baseUrl + "editApp");    
    EditAppPage editAppPage = new EditAppPage(driver);
    editAppPage.typeDescription("Test Description");
    editAppPage.typeName("Test Name");
    HomePage homePage = (HomePage)editAppPage.submitForm(HomePage.class);
  }
  
   @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToModifyAppWithoutName() {
    driver.get(baseUrl + "addApp");    
    EditAppPage editAppPage = new EditAppPage(driver);
    editAppPage.typeDescription("Test Description");
    EditAppPage editAppPageF = (EditAppPage)editAppPage.submitForm(EditAppPage.class);
  }
  
  // Logout
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldLogout() {
    driver.get(baseUrl + "home");    
    HomePage homePage = new HomePage(driver);
    LoginPage loginPage = (LoginPage)homePage.submitLogout(LoginPage.class);
  }
  
  // Users List
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldDisplayListUsers() {
    driver.get(baseUrl + "home");    
    HomePage homePage = new HomePage(driver);
    ListUsersPage listUsersPage = (ListUsersPage)homePage.submitListUsers(ListUsersPage.class);
  }
  
  
  @After
  public void closeBrowser() {
    driver.close();
  }
}
