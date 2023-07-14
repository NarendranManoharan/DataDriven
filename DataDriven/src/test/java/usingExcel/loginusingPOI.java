package usingExcel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class loginusingPOI {
    
	WebDriver driver;
	
	static List<String> username=new ArrayList<String>();
	static List<String> password=new ArrayList<String>();

	

	public static void readexcel() throws Exception {
		FileInputStream excel=new FileInputStream("D:\\testing\\Automation\\InputDetails.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(excel);
		XSSFSheet sheet=workbook.getSheetAt(1);
		
		Iterator<Row> rowsCount=sheet.rowIterator();
	  
		
			while(rowsCount.hasNext()) {
				Row row=rowsCount.next();

				Iterator<Cell> cellcount=row.iterator();
				int i=2;
				while(cellcount.hasNext()) {
					Cell column=cellcount.next();
					String sheetvalue=column.getStringCellValue();
					
					if(i%2==0) {
					
					    username.add(sheetvalue);
					}
					else {
						password.add(sheetvalue);
					}
					i++;	
				}
				
				
			}

	}
	
	public void login() throws InterruptedException {
		for (int i = 0; i <username.size() ; i++) {
			LogintoLMS(username.get(i), password.get(i));
		}
	}
	
	public static void main(String[] args) throws Exception {
		loginusingPOI loginusingPOI=new loginusingPOI();
		loginusingPOI.readexcel();
		loginusingPOI.login();
		
	}

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
