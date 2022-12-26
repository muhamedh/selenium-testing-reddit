package selenium_testing_reddit.ibu.edu.ba;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class testOptingOutOfRedesign {

	private static WebDriver webDriver;
	private static String baseURL;
	
	@BeforeAll
	static void setUP() {
		System.setProperty("webdriver.chrome.driver", "/home/musaka/Desktop/Semester 5/svvt/chromedriver");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox",
				             "--disable-notifications",
				             "--disable-extensions");
		String path = "/home/musaka/Desktop/RedditProfile";
		options.addArguments("--user-data-dir="+path); 

		options.addArguments("--start-maximized");

		webDriver = new ChromeDriver(options);
		
		baseURL = "https://www.reddit.com/settings/";
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div/button")).click();
		Thread.sleep(3000);
		webDriver.quit();
	}

	@Test
	void testOptOut() throws InterruptedException {
		webDriver.get(baseURL);
		
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
		
		WebElement buttonSwitch = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div[2]/div[1]/div[11]/div[2]/div/button"))
				);
		buttonSwitch.click();
		
		
		
		// confirm opt out
		
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[4]/div/div/div/div/div/button[2]"))
				).click();
		
		// check if design changed
		
		// /html/body/div[2]/div[1]/div/button
		
		String msg = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[1]/div/button"))
				).getText();
		
		assertEquals("GET NEW REDDIT", msg);
		
		Thread.sleep(3000);
		
	}

}
