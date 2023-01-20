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

class testEmojiSend {

	private static WebDriver webDriver;
	private static String baseURL;
	public int countOfOccurrences(String str, String subStr) {
		  return (str.length() - str.replaceAll(Pattern.quote(subStr), "").length()) / subStr.length();
	}
	
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
		
		baseURL = "https://www.reddit.com/chat/";
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}


	@Test
	void testUserBlock() throws InterruptedException {
		webDriver.get(baseURL);
		Thread.sleep(3000);
		
		WebElement l= webDriver.findElement(By.tagName("body"));
		String p = l.getAttribute("innerHTML");
		int numOfIcons = this.countOfOccurrences(p,"https://www.redditstatic.com/desktop2x/img/snoomoji/sloth.png");
		
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

		// i must find the user
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/main/div[1]/div[1]/div[1]/div/button"))
				).click();
		
		// send search atttributes
		
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/main/div[1]/div[2]/div/div/form/div/div/div[2]/div[1]/span/input"))
				).sendKeys("muhasupa");
		// wait till search loads
		Thread.sleep(1000);
		// seelect user to send
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/main/div[1]/div[2]/div/div/form/div/div/div[2]/div[2]/div"))
				).click();
		// press start chat 
		
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/main/div[1]/div[2]/div/div/form/div/div[2]/div/button[2]"))
				).click();
		
		
		// press emoji button
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/main/div[1]/div[2]/div[1]/form/div/div/div/div[2]"))
				).click();
		
		//press sloth emoji
		// 
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/main/div[3]/div/div/div/div/div/div[34]"))
				).click();
		Thread.sleep(2000);
		 l= webDriver.findElement(By.tagName("body"));
		 p = l.getAttribute("innerHTML");
		
		int numOfIconsAfter = this.countOfOccurrences(p,"https://www.redditstatic.com/desktop2x/img/snoomoji/sloth.png");
		assertEquals(numOfIconsAfter, numOfIcons+1);
	
	
		
		
	}
}
