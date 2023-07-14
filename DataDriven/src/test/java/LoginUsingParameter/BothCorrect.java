package LoginUsingParameter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BothCorrect {
	@Test
	@Parameters({"username","password"})
	public void LoginwithBothCorrect(String username,String password) throws InterruptedException{

		WebDriver driver =new ChromeDriver();
		WebDriverManager.chromedriver().setup();
		driver.get("http://lmstest.pilship.com/opuscntr/SignOn.do?serviceId=LMS");
		driver.findElement(By.id("j_username")).sendKeys(username);
		driver.findElement(By.id("j_password")).sendKeys(password);
		driver.findElement(By.xpath("//*[@type='button']")).click();
		driver.quit();
	}
}
