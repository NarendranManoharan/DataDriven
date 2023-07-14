package usingDataProvider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginusingDataProvider {
	WebDriver driver;
	String [][] data= {
			{"INNARMA","Naren@123"},
			{"INNARMAN","Naren@123"},
			{"INNARMA","Naren@12"}		
	};
	
	@DataProvider(name = "logindata")
	public String[][] dataprovider() {
		return data;
	}
	
		
	
    @Test(dataProvider = "logindata")
	public void LogintoLMS(String username,String password) throws InterruptedException{
    	driver =new ChromeDriver();
 		WebDriverManager.chromedriver().setup();
    	driver.get("http://lmstest.pilship.com/opuscntr/SignOn.do?serviceId=LMS");
		driver.findElement(By.id("j_username")).sendKeys(username);
		driver.findElement(By.id("j_password")).sendKeys(password);
		driver.findElement(By.xpath("//*[@type='button']")).click();
		driver.quit();
		
	}
}
