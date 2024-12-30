package folkatech;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class technicalTest {

	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://lapor.folkatech.com/");
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("admin@example.com");
		driver.findElement(By.name("password")).sendKeys("password");
		driver.findElement(By.id("togglePassword")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(By.xpath("//h3[@class='mb-3']")).getText(), "Dashboard");
		driver.quit();
	}
	
	public static void handleAlert(WebDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
            System.out.println("Alert dismissed");
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present, continuing...");
        }
    }

}
