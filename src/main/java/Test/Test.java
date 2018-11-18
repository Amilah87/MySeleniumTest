package Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import PageObject.HomePage;
import PageObject.NewCustomer;
import PageObject.login;
import org.apache.log4j.Logger;

public class Test {
	
	WebDriver driver;

    login objLogin;

    HomePage objHomePage;
    
    NewCustomer objNewCustomer;


    @BeforeTest

    public void setup(){

    	System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://demo.guru99.com/V4/");

    }

    /**

     * This test go to http://demo.guru99.com/V4/

     * Verify login page title as guru99 bank

     * Login to application

     * Verify the home page using Dashboard message

     */

    @org.testng.annotations.Test(priority=0)

    public void test_Home_Page_Appear_Correct(){

        //Create Login Page object

    objLogin = new login(driver);

    //Verify login page title

    String loginPageTitle = objLogin.getLoginTitle();

    Assert.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));

    //login to application

    objLogin.loginToGuru99("mgr123", "mgr!23");

    // go the next page

    objHomePage = new HomePage(driver);

    //Verify home page

    Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mgr123"));
    
    objNewCustomer = new NewCustomer(driver);
    objNewCustomer.navigateNewCustomer(driver);
    objNewCustomer.createNewCustomer();
    
    Logger log = Logger.getLogger("devpinoyLogger");
    log.debug("Navigating to Login Page");
    
 
    }
}
