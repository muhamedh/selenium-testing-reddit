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

class testPostDraft {

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
		
		baseURL = "https://www.reddit.com/submit";
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}

	@Test
	void testPostDraftFunctionality() throws InterruptedException {
		webDriver.get(baseURL);
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
		
		WebElement titleTextArea = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div[3]/div[1]/div[2]/div[3]/div[2]/div[1]/div/textarea"))
				);
		
		titleTextArea.sendKeys("Test Title");
		WebElement textTextArea = webDriver.findElement(By.xpath("//*[@id=\"AppRouter-main-content\"]/div/div/div[2]/div[3]/div[1]/div[2]/div[3]/div[2]/div[2]/div/div/div[3]/div/div[1]/div/div/div"));
		textTextArea.sendKeys("Test Text");
		
		WebElement saveDraftButton = webDriver.findElement(By.xpath("//*[@id=\"AppRouter-main-content\"]/div/div/div[2]/div[3]/div[1]/div[2]/div[3]/div[3]/div[2]/div/div/div[2]/button"));
		
		saveDraftButton.click();
		Thread.sleep(400);
		// go to draft list
		webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div[3]/div[1]/div[2]/div[1]/button")).click();
		
		// wait till draft list loads
		
	    Thread.sleep(2000);
		
		// check if draft is saved
		/*
		WebElement draftTitle = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\\\"SHORTCUT_FOCUSABLE_DIV\\\"]/div[4]/div/div/div/div[2]/div/div[2]/h2"))
				);*/
			WebElement draftTitle =	webDriver.findElement(By.xpath("//*[@id=\"SHORTCUT_FOCUSABLE_DIV\"]/div[4]/div/div/div/div[2]/div/div[2]/h2"));
		System.out.println(draftTitle.getText());
		assertEquals(draftTitle.getText(),"EDITING: Test Title");
		
		
	}

}
