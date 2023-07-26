package customerMigration;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class migrationCommons {

	public static void commons (String newcustomerType) throws InterruptedException{
		
		try
		{
		WebElement dropdown_customerRegistration = migrationEntry.driver.findElement(By.name("generalInformationDiv:customerTypeDiv:customer.customerTypeId"));		
		////*[@id="idc"]
		dropdown_customerRegistration.click();
		migration.selectCustomerType = new Select(dropdown_customerRegistration);
		migration.selectCustomerType.selectByVisibleText(newcustomerType) ;
		System.out.println("Selected Customer Type : "+ migration.selectCustomerType.getFirstSelectedOption().getText());
		Thread.sleep(2500);
		migrationEntry.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		
		catch(Exception e){
			
			System.out.println("EXCEPTION: "+ e.getMessage());
			
			
		}
	}
	
}
