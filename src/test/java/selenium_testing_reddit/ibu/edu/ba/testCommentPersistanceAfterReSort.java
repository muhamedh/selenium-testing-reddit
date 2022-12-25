package selenium_testing_reddit.ibu.edu.ba;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class testCommentPersistanceAfterReSort {

	private static WebDriver webDriver;
	private static String baseURL;
	
	@BeforeAll
	static void setUP() {
		System.setProperty("webdriver.chrome.driver", "/home/musaka/Desktop/Semester 5/svvt/chromedriver");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox","--disable-notifications");
		webDriver = new ChromeDriver(options);
		
		baseURL = "https://www.reddit.com/";
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}

	@Test
	void testCommentPersistance() throws InterruptedException {
		baseURL = "https://www.reddit.com/r/AskReddit/comments/zuckzx/what_does_europe_have_that_north_america_does_not/";
		webDriver.get(baseURL);
		webDriver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
		
		WebElement commentForm = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"AppRouter-main-content\"]/div/div/div[2]/div[3]/div[1]/div[2]/div[3]/div/div/div/div[2]/div/div[1]/div/div/div/div/div/div/div"))
				);
		JavascriptExecutor jse2 = (JavascriptExecutor) webDriver;
		jse2.executeScript("arguments[0].scrollIntoView", commentForm);
		commentForm.click();
		
		commentForm.sendKeys("Hello");
		
		webDriver.findElement(By.id("CommentSort--SortPicker")).click();
		
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.linkText("Top"))
		).click();
		
		webDriver.switchTo().alert().accept();
		
		String commentFormText = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div[3]/div[1]/div[2]/div[3]/div/div/div/div[2]/div/div[1]/div/div/div/div/div/div/div/span/span")).getText();

		assertNotEquals(commentFormText,"Hello");
	}

}
