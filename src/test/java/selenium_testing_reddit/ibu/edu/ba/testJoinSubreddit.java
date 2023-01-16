package selenium_testing_reddit.ibu.edu.ba;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.regex.Pattern;

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

class testJoinSubreddit {

	private static WebDriver webDriver;
	private static String baseURL;

	@BeforeAll
	static void setUP() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Amira\\Downloads\\chromedriver.exe");
		webDriver = new ChromeDriver();
		baseURL = "https://ibu.edu.ba";
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		
		String path = "C:\\Users\\Amira\\Downloads\\ReditProfile";
		options.addArguments("--user-data-dir="+path); 

		options.addArguments("--start-maximized");

		

		webDriver = new ChromeDriver(options);
		
		baseURL = "https://www.reddit.com/r/bugs/";
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div[1]/div/div[1]/div/div[2]/div/button"))
				).click();
		Thread.sleep(1500);
		webDriver.quit();
	}


	@Test
	void testJoin() throws InterruptedException {
		webDriver.get(baseURL);
		
		
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

		// i must find the user
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div[1]/div/div[1]/div/div[2]/div/button"))
				).click();
	
		String buttonText = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div[1]/div/div[1]/div/div[2]/div[1]"))
				).getText();
		 
		
		
		assertEquals(buttonText, "Leave");
		
	}
}
