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

class testAddSocialLinkRedirect {

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
		
		baseURL = "https://www.reddit.com/settings/profile";
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}


	@Test
	void testSocialLink() throws InterruptedException {
		webDriver.get(baseURL);
		
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
		
		WebElement addSocialLinkDiv = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div/div"))
				);
		
		addSocialLinkDiv.click();
		
		WebElement redditSocialLink = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[4]/div/div/section/div/div[2]"))
				);
		
		redditSocialLink.click();
		
		WebElement socialLinkInput = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[4]/div/div/section/div/input"))
				);
		
		socialLinkInput.sendKeys("r/Home");
		
		// /html/body/div[1]/div/div[2]/div[4]/div/div/section/div/div[2]
		Thread.sleep(3000);
		
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[4]/div/div/section/header/div/div[3]/button"))
				).click();
		
		webDriver.navigate().refresh();
		
		WebElement socialLink = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div[2]/div[1]/div[3]/div[2]/div/div[1]"))
				);
		
		assertEquals("r/Home",socialLink.getText());
		
		Thread.sleep(3000);
		
	}

}
