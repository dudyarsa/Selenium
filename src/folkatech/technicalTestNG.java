package folkatech;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class technicalTestNG {

    WebDriver driver;
    ExtentHtmlReporter htmlReporter;
    ExtentReports extent;

    @BeforeSuite
    public void setUp() {
        
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
    }
    
    @BeforeTest
    public void setUpTest() {
        
        String projectPath = System.getProperty("user.dir");
        System.getProperty("webdriver.chrome.driver", projectPath+"/driver");
        driver = new ChromeDriver();
    }

    @Test
    public void testValidLogin() throws Exception {
        // Create a test in the ExtentReports object
        ExtentTest test = extent.createTest("testValidLogin");

        
            driver.get("https://lapor.folkatech.com/");
            test.log(Status.INFO, "This step shows usage of log(status, details)");
            test.pass("Login with valid credentials.");
            driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("admin@example.com");
            driver.findElement(By.name("password")).sendKeys("password");
            driver.findElement(By.id("togglePassword")).click();
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            // Validate and log test result
            String dashboardText = driver.findElement(By.xpath("//h3[@class='mb-3']")).getText();
            Assert.assertEquals(dashboardText, "Dashboard");
            test.pass("Login was successful. Dashboard is displayed.");
            test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("/screenshots/Screenshot1.png").build());
            test.addScreenCaptureFromPath("Screenshoot.png");
        
    }

    @Test
    public void testInvalidLogin() throws Exception {
        // Create a test in the ExtentReports object
    	ExtentTest test = extent.createTest("testInvalidLogin");

   
            driver.get("https://lapor.folkatech.com/");
            test.log(Status.INFO, "This step shows usage of log(status, details)");
            test.pass("Login with invalid credentials.");
            driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("admin@example.com");
            driver.findElement(By.name("password")).sendKeys("passwordd");
            driver.findElement(By.id("togglePassword")).click();
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            // Validate and log test result
            String errorMessage = driver.findElement(By.xpath("//label[@role='alert']")).getText();
            Assert.assertEquals(errorMessage, "Login Gagal! Kata sandi salah.");
            test.pass("Error message displayed as expected: " + errorMessage);
            test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("/screenshots/Screenshot2.png").build());
            test.addScreenCaptureFromPath("Screenshoot.png");
       
    }

    @AfterTest
    public void tearDownTest() {
    	driver.close();
    	driver.quit();
    	System.out.println("Test Completed Successfully");
    }

    @AfterSuite
    public void tearDown() {
    	extent.flush();
    }
}