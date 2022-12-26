package selenium_testing_reddit.ibu.edu.ba;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

class testLanguangeChange {

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
		webDriver.quit();
	}

	@Test
	void testDisplayLanuangeChange() throws InterruptedException {
		webDriver.get(baseURL);
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
		
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div[2]/div[1]/div[4]/div[3]/span/select"))
				);
		
		Select languange = new Select(webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div[2]/div[1]/div[4]/div[3]/span/select")));
		
		List<WebElement> allLanguanges = languange.getOptions();
		List<String> visibleTextLanguanges = new ArrayList<String>();
		for( int i = 0;i < allLanguanges.size();i++) {
			visibleTextLanguanges.add(allLanguanges.get(i).getText());
		}
		
		for( int i = 0;i < allLanguanges.size();i++) {
			System.out.println("Testing: " + visibleTextLanguanges.get(i));
			languange = new Select(webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div[2]/div[1]/div[4]/div[3]/span/select")));
			
			languange.selectByVisibleText(visibleTextLanguanges.get(i));
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"AppRouter-main-content\"]/div/div[2]/div[1]/h2"))
					);
			Thread.sleep(300);
			
			String accSettingsText = webDriver.findElement(By.xpath("//*[@id=\"AppRouter-main-content\"]/div/div[2]/div[1]/h2")).getText();
			
			if(visibleTextLanguanges.get(i).equals("Deutsch")) {
				assertEquals("Kontoeinstellungen", accSettingsText);
			}else if(visibleTextLanguanges.get(i).equals("English (US)")) {
				assertEquals("Account settings", accSettingsText);
			}else if(visibleTextLanguanges.get(i).equals("Español (ES)")) {
				assertEquals("Ajustes de cuenta", accSettingsText);
			}else if(visibleTextLanguanges.get(i).equals("Español (MX)")) {
				assertEquals("Ajustes de la cuenta", accSettingsText);
			}else if(visibleTextLanguanges.get(i).equals("Français")) {
				assertEquals("Paramètres du compte", accSettingsText);
			}else if(visibleTextLanguanges.get(i).equals("Italiano")) {
				assertEquals("Impostazioni account", accSettingsText);
			}else if(visibleTextLanguanges.get(i).equals("Português (BR)")) {
				assertEquals("Configurações da conta", accSettingsText);
			}else if(visibleTextLanguanges.get(i).equals("Português (PT)")) {
				assertEquals("Definições da conta", accSettingsText);
			}
		}
	}

}
