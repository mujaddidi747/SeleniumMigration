package customerMigration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;



import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;



public class Utility {
	
	static Workbook book;
	static Sheet sheet;
	public static String TestData_Sheet_Path = "D:/AutomationProject/SMP_3.1_Automation_Project/customerMigration/sample.xlsx";

	public static Actions getActionsObject_submenu_homepage(WebDriver driver,WebElement sub_menu,WebElement sub_menu1){
		
		///html/body/div/div[3]/div[3]/ul[1]/span/span[6]/li/span

		sub_menu = driver.findElement(By.xpath("//*[@id='pageWrap']/div[3]/ul[1]/span/span[6]/li/span"));
		sub_menu1 = driver.findElement(By.xpath("//*[@id='pageWrap']/div[3]/ul[1]/span/span[6]/li/ul/span[7]/li/a/span"));
		Actions act = new Actions(driver);
		act.moveToElement(sub_menu);
		act.click(sub_menu1);
		act.build().perform();

		return act;	
	}
	
public static String[] getSheetNames() throws InvalidFormatException{
		
		System.out.println("*** Into the Sheets Name ***");
        
    	FileInputStream file = null;

		try {
			file = new FileInputStream(TestData_Sheet_Path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Get No Of Sheets : "+book.getNumberOfSheets());
		
		String[] strName = new String [book.getNumberOfSheets()];  
		
		for (int i=0; i<book.getNumberOfSheets(); i++) { 
						
			strName[i] = book.getSheetAt(i).getSheetName();	
			System.out.println(strName[i]);  
		}  
		
		return strName;
	}

	public static Object[][] ReadSheet(String sheetName) throws IOException {
		
		System.out.println("*** Into the ReadFile ***");
    	
    	Object[][] data = null;
  	   
		sheet = book.getSheet(sheetName);
	  	
		data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
    	System.out.println("Sheet Total Records:"+sheet.getLastRowNum());
 
    	try{
    	
    		for (int j = 0; j<sheet.getLastRowNum();j++){
    			for (int k = 0; k < sheet.getRow(0).getLastCellNum();k++){  				
    				
    				if((data[j][k]) == null){
    				//we have to add check here that if the array value contains the null values delete the array
    					data[j][k] = sheet.getRow(j+1).getCell(k).toString();  
    				}
    			}
    	   }
    	 }catch (Exception e){
    		e.printStackTrace();
    	 }
		return data;
	}
	
	 public static void riskTypes(){
	    	
	    	Select AccountOrganizationType = new Select(migrationEntry.driver.findElement(By.name("kycDiv:riskProfileDiv:customer.kycDetailsBean.riskProfile.accRelegiousOrgOrMadrassa")));
			AccountOrganizationType.selectByVisibleText("Low Risk");

			Select PoliticallyExposedPerson = new Select(migrationEntry.driver.findElement(By.name("kycDiv:riskProfileDiv:customer.kycDetailsBean.riskProfile.politicalyExposed")));
			PoliticallyExposedPerson.selectByVisibleText("High Risk");

			Select CustomerOwnership = new Select(migrationEntry.driver.findElement(By.name("kycDiv:riskProfileDiv:customer.kycDetailsBean.riskProfile.landLord")));
			CustomerOwnership.selectByVisibleText("High Risk");

			Select CustomerRefuse = new Select(migrationEntry.driver.findElement(By.name("kycDiv:riskProfileDiv:customer.kycDetailsBean.riskProfile.refusedByOtherBank")));
			CustomerRefuse.selectByVisibleText("High Risk");

			Select CustomerDealing = new Select(migrationEntry.driver.findElement(By.name("kycDiv:riskProfileDiv:customer.kycDetailsBean.riskProfile.dealingWithHighLowRiskCountry")));
			CustomerDealing.selectByVisibleText("High Risk");

			Select IsResident = new Select(migrationEntry.driver.findElement(By.name("kycDiv:riskProfileDiv:customer.kycDetailsBean.riskProfile.residentOrNot")));
			IsResident.selectByVisibleText("Medium Risk");	

			Select IsActualOwner = new Select(migrationEntry.driver.findElement(By.name("kycDiv:riskProfileDiv:customer.kycDetailsBean.riskProfile.actualOwnerOfAccount")));
			IsActualOwner.selectByVisibleText("Low Risk");	

			Select IsCustomerDirector = new Select(migrationEntry.driver.findElement(By.name("kycDiv:riskProfileDiv:customer.kycDetailsBean.riskProfile.directorOrGovBody")));
			IsCustomerDirector.selectByVisibleText("Low Risk");	

			Select IsCustomerHouseWife = new Select(migrationEntry.driver.findElement(By.name("kycDiv:riskProfileDiv:customer.kycDetailsBean.riskProfile.houseWife")));
			IsCustomerHouseWife.selectByVisibleText("Low Risk");	

			Select IsMinorAccount = new Select(migrationEntry.driver.findElement(By.name("kycDiv:riskProfileDiv:customer.kycDetailsBean.riskProfile.minorAccount")));
			IsMinorAccount.selectByVisibleText("Medium Risk");	

			Select IsHighRiskBusiness = new Select(migrationEntry.driver.findElement(By.name("kycDiv:riskProfileDiv:customer.kycDetailsBean.riskProfile.profession")));
			IsHighRiskBusiness.selectByVisibleText("High Risk");	

			Select IsClientProfile = new Select(migrationEntry.driver.findElement(By.name("kycDiv:riskProfileDiv:customer.kycDetailsBean.riskProfile.productAndServiceMappingMatch")));
			IsClientProfile.selectByVisibleText("Medium Risk");	

			Select IsComplexStructure = new Select(migrationEntry.driver.findElement(By.name("kycDiv:riskProfileDiv:customer.kycDetailsBean.riskProfile.businessStructure")));
			IsComplexStructure.selectByVisibleText("Low Risk");			

			Select IsMailInstruction = new Select(migrationEntry.driver.findElement(By.name("kycDiv:riskProfileDiv:customer.kycDetailsBean.riskProfile.holdMail")));
			IsMailInstruction.selectByVisibleText("Medium Risk");			

			//RISK RATING
			Select RiskRating = new Select(migrationEntry.driver.findElement(By.name("kycDiv:riskRatingDiv:customer.kycDetailsBean.riskRating.riskRating")));
			RiskRating.selectByVisibleText("Low Risk");		
	    	
	    }
	 
	
}
