package uchus;

import java.util.concurrent.TimeUnit;

import org.apache.bcel.classfile.Constant;
import org.apache.bcel.generic.RETURN;
import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Class1 {
	
	final String cUserName = "qwerty@q.ty";
	final String cPassword = "1234567";
	final String cFirstName = "qwerty";
	
	
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  
  

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
	//driver = new ChromeDriver();
    baseUrl = "http://www.seek.com.au";
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.get(baseUrl + "/");
  }

  @Test
  public void SignInTest() throws Exception {
    
    WebElement inpEmail = driver.findElement(By.id("username"));
    WebElement inpPassword = driver.findElement(By.id("password"));
    WebElement btnLogIn = driver.findElement(By.id("logInButton"));
    By pnlLogin = By.id("loggedInName");
    By spanUserName = By.className("username");
    
    inpEmail.clear();
    inpEmail.sendKeys(cUserName);
    inpPassword.clear();
    inpPassword.sendKeys(cPassword);
    btnLogIn.click();

    assertFalse("You are not sign in (Sign In panel is still here)", isElementPresent(pnlLogin));
    assertTrue("You are not sign in (Username didn't appear)", isElementPresent(spanUserName));
    assertTrue("You are logged in with incorrect First Name", driver.findElement(spanUserName).getText().equals(cFirstName)); 
  }
  
  @Test
  public void SignOutTest() throws Exception {
	  
  }


@After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

