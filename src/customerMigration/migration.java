package customerMigration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class migration {


	Date d = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("MMM-dd-yyyy");
	String date = formatter.format(d);
	String splitter[] = date.split("-");
	String month_year = splitter[0];
	String day = splitter[1]; 
	WebElement sub_menu;
	WebElement sub_menu1;
	public static Select selectCustomerType=null ;
	
		
	///////////TO L2 TYPES ///////////
	//////////////////////////////////
	
	
	public boolean migratingProfiletol2Types(WebDriver driver, String accNumber, String Name, String CNIC, String contactNumber, String residency
			, String address,String ActualOwner, String BenificiaryCNIC, String ExpextedMonthlyNoTransactionCR, String ExpextedMonthlyAmtTransactionDB,
			String ExpextedMonthlyAmtTransactionCR, String ExpextedMonthlyNoTransactionDB, String AvgYearlyIncome, String AvgYearlySales, String ntn,
			String TaxIdentificationNumber,String newcustomerType) throws InterruptedException, IOException
	{
		
		
		    driver.findElement(By.xpath("//*[@id='msisdn']")).sendKeys(accNumber);
		   // driver.findElement(By.id("displayName")).sendKeys("ajhabdc");
		    driver.findElement(By.id("id7")).click();
		    driver.findElement(By.xpath("/html/body/div/div[3]/div[4]/div/div[2]/div/div/form/div[5]/div/table/tbody/tr/td[1]/a/div")).click();

			try{
				migrationCommons.commons(newcustomerType);
			}

			catch(Exception e){
				migrationCommons.commons(newcustomerType);
			}
		

		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		if(selectCustomerType.getFirstSelectedOption().getText().equals("L2 Existing Customer")
				|| newcustomerType.equalsIgnoreCase("L2-Individual")
				|| newcustomerType.equalsIgnoreCase("L2-Individual-10000")
				|| newcustomerType.equalsIgnoreCase("L2-Individual-250000")
				|| newcustomerType.equalsIgnoreCase("L2-Individual-500000")
				|| newcustomerType.equalsIgnoreCase("L2-Individual-99999")
				|| newcustomerType.equalsIgnoreCase("L2 Individual Staff")
				|| newcustomerType.equalsIgnoreCase("L2 Individual Staff-100000")
				|| newcustomerType.equalsIgnoreCase("L2 Individual Staff-2500000")
				|| newcustomerType.equalsIgnoreCase("L2 Individual Staff-700000")
			){
			
	
			
			customerToL2Types(driver, accNumber, Name, CNIC, contactNumber, residency, 
					address, ActualOwner, BenificiaryCNIC, ExpextedMonthlyNoTransactionCR, ExpextedMonthlyAmtTransactionDB, 
					ExpextedMonthlyAmtTransactionCR, ExpextedMonthlyNoTransactionDB, 
					AvgYearlyIncome, AvgYearlySales, ntn, TaxIdentificationNumber, newcustomerType);
		
		} //END if condition

	 	    return true;
}
	
	public boolean customerToL2Types(WebDriver driver, String accNumber, String Name, String CNIC, String contactNumber, String residency
			, String address,String ActualOwner, String BenificiaryCNIC, String ExpextedMonthlyNoTransactionCR, String ExpextedMonthlyAmtTransactionDB,
			String ExpextedMonthlyAmtTransactionCR, String ExpextedMonthlyNoTransactionDB, String AvgYearlyIncome, String AvgYearlySales, String ntn,
			String TaxIdentificationNumber,String newcustomerType){

        System.out.println("HI ABSAR MUJADDIDI IS TESTING MIGRATION MODULE");
        
        /////////////////////////////
		////////KIN DETAILS//////////
		////////////////////////////
        
        Select PoliticalExposed = new Select(driver.findElement(By.name("kycDiv:politicallyExposedDiv:customer.kycDetailsBean.politicallyExposed.politicallyExposed")));
		PoliticalExposed.selectByIndex(1);
		
		Select accType = new Select (driver.findElement(By.name("generalInformationDiv:accountTypeDiv:customer.address.typeOfAccount")));
		accType.selectByVisibleText("Corporation");
		
		////////////////////////////////
		////////HARDCODED VALUES////////
		////////////////////////////////
		//driver.findElement(By.id("fatherHusbandName")).sendKeys("Test Automation");
		
	//	driver.findElement(By.id("permanentCity")).sendKeys("LAHORE");
		
		Select permanentProvince = new Select(driver.findElement(By.name("contactInfoDiv:addressDiv:permanentAddressDiv:customer.address.permanentState")));
		permanentProvince.selectByVisibleText("Punjab");
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		driver.findElement(By.id("nextOfKinName")).clear();
		driver.findElement(By.id("nextOfKinName")).sendKeys(Name);
		//	Assert.assertTrue(accountTitle.matches("^[a-zA-Z\\s]*$"));

		driver.findElement(By.id("nextOfKinCnic")).clear();
		driver.findElement(By.id("nextOfKinCnic")).sendKeys(CNIC);
		//Assert.assertTrue(CNIC.matches("^(?=.*[1-9])[0-9+]{5}(-){1}[0-9+]{7}(-){1}[0-9]{1}$"));

		Select nextofkinNationality = new Select(driver.findElement(By.name("kycDiv:nextOfKinDiv:nextOfKinNationalityDiv:customer.kycDetailsBean.nextOfKin.nextOfKinNationality")));
		nextofkinNationality.selectByVisibleText("PAKISTAN");

		driver.findElement(By.id("nextOfKinContactNo")).clear();
		driver.findElement(By.id("nextOfKinContactNo")).sendKeys(contactNumber);

		driver.findElement(By.id("nextOfKinResidency")).clear();
		driver.findElement(By.id("nextOfKinResidency")).sendKeys(residency);

		driver.findElement(By.id("nextOfKinAddress")).clear();
		driver.findElement(By.id("nextOfKinAddress")).sendKeys(address);
		//Assert.assertTrue(address.matches("^[0-9]*|^[-0-9a-zA-ZÀ-ÿ .']*$"));
	
		Select Relationship = new Select(driver.findElement(By.name("kycDiv:nextOfKinDiv:nextOfKinRelationshipDiv:customer.kycDetailsBean.nextOfKin.nextOfKinRelationship")));
		Relationship.selectByVisibleText("Aunt"); ////*[@id="ida3"]
		

		WebElement OccupationType = driver.findElement(By.name("kycDiv:occupationDetailsDiv:occupationTypeDiv:customer.kycDetailsBean.occupationDetail.occupationType"));
		Select occupationType = new Select(OccupationType);
		occupationType.selectByVisibleText("Retired");

		///html/body/div[1]/div[3]/div[4]/div/div[2]/div/form/div[3]/div[6]/div[3]/div[1]/div[1]/select
     for(int x = 0; x <=2 ; x++){
		try {
			Select purposeofACCOUNT = new Select(driver.findElement(By.name("kycDiv:occupationDetailsDiv:purposeOfAccountDiv:customer.kycDetailsBean.occupationDetail.purposeOfAccount")));
			purposeofACCOUNT.selectByVisibleText("Salary");
			}
			
			catch(Exception e){
				Select purposeofACCOUNT = new Select(driver.findElement(By.name("kycDiv:occupationDetailsDiv:purposeOfAccountDiv:customer.kycDetailsBean.occupationDetail.purposeOfAccount")));
				purposeofACCOUNT.selectByVisibleText("Salary");
			}
}

		Select beneficialAccount = new Select(driver.findElement(By.name("kycDiv:beneficialOwnerStatusDiv:isBeneficialAccountOwnerDiv:customer.kycDetailsBean.beneficialOwnerStatus.isBeneficialAccountOwner")));
		beneficialAccount.selectByVisibleText("Yes");
		
		Select TxnMode = new Select(driver.findElement(By.name("kycDiv:modeOfTransactionDiv:customer.kycDetailsBean.modeOfTransaction.modeOfTransaction")));
		TxnMode.selectByVisibleText("Cash");

		driver.findElement(By.id("expectedCredit")).clear();
		driver.findElement(By.id("expectedCredit")).sendKeys(ExpextedMonthlyNoTransactionCR);
		//Assert.assertTrue(ExpextedMonthlyNoTransactionCR.matches("^[0-9]{1,12}$"));

		driver.findElement(By.id("monthlyExpectedWithdrawal")).clear();
		driver.findElement(By.id("monthlyExpectedWithdrawal")).sendKeys(ExpextedMonthlyAmtTransactionCR);
		//Assert.assertTrue(ExpextedMonthlyNoTransactionCR.matches("^[0-9]{1,12}$"));

		driver.findElement(By.id("expectedDebit")).clear();
		driver.findElement(By.id("expectedDebit")).sendKeys(ExpextedMonthlyAmtTransactionDB);
		//Assert.assertTrue(ExpextedMonthlyNoTransactionCR.matches("^[0-9]{1,12}$"));

		driver.findElement(By.id("monthlyExpectedDeposit")).clear();
		driver.findElement(By.id("monthlyExpectedDeposit")).sendKeys(ExpextedMonthlyNoTransactionDB);
		//Assert.assertTrue(ExpextedMonthlyNoTransactionCR.matches("^[0-9]{1,12}$"));

		driver.findElement(By.id("avergaeYearlyIncome")).clear();
		driver.findElement(By.id("avergaeYearlyIncome")).sendKeys(AvgYearlyIncome);
		//Assert.assertTrue(ExpextedMonthlyNoTransactionCR.matches("^[0-9]{1,12}$"));

		driver.findElement(By.id("averageYearlySales")).clear();
		driver.findElement(By.id("averageYearlySales")).sendKeys(AvgYearlySales);
		//Assert.assertTrue(ExpextedMonthlyNoTransactionCR.matches("^[0-9]{1,12}$"));

		driver.findElement(By.id("ntn")).clear();
		driver.findElement(By.id("ntn")).sendKeys(ntn);

		Select TypeOfCustomer = new Select(driver.findElement(By.name("kycDiv:informationDiv:typeOfIncomingCustomerDiv:customer.kycDetailsBean.information.typeOfIncomingCustomer")));
		TypeOfCustomer.selectByVisibleText("Walk In");

		Select HearingMedium = new Select(driver.findElement(By.name("kycDiv:informationDiv:bankHearingMediumDiv:customer.kycDetailsBean.information.bankHearingMedium")));
		HearingMedium.selectByVisibleText("Others");

		//RISK TYPES
		Utility.riskTypes();

		//RESIDENCE STATUS//

		Select ResidenceStatus = new Select(driver.findElement(By.name("kycDiv:residenceStatusDiv:customer.kycDetailsBean.residenceStatus.residentOrNot")));
		ResidenceStatus.selectByVisibleText("Resident");

//		// CRS

		Select CountryOfBirth = new Select(driver.findElement(By.name("kycDiv:crsDiv:crsIndividual:countryOfBirthDiv:customer.kycDetailsBean.crsBean.countryOfBirth")));
		CountryOfBirth.selectByVisibleText("PAKISTAN");	

		Select TaxIsResident = new Select(driver.findElement(By.name("kycDiv:crsDiv:crsIndividual:customer.kycDetailsBean.crsBean.isTaxResident")));
		TaxIsResident.selectByVisibleText("Yes");	

		if(TaxIsResident.getFirstSelectedOption().getText().equals("No")){
			Select TaxResidenceCountry = new Select(driver.findElement(By.id("idae")));
			TaxResidenceCountry.selectByVisibleText("PAKISTAN");	

			driver.findElement(By.id("idaf")).sendKeys(TaxIdentificationNumber);
			
			/*Select Reason = new Select(driver.findElement(By.id("id94")));
			Reason.selectByVisibleText("Reason C");	*/

			System.out.print("\n \t HI PLEASE ATTEST THAT I AM HERE");
	
		} //END if condition
		
		else {

			WebElement UsPerson = driver.findElement(By.name("kycDiv:fatcaDiv:fatcaIndividual:customer.kycDetailsBean.fatca"));
			UsPerson.click();
		}
		
			driver.findElement(By.xpath("//*[@name='update']")).click(); 
			
			return true;

	}
	

	
	////////////////////////////////////////////////////
	////////////////MIGRATING GUEST TYPES //////////////
	///////////////////////////////////////////////////
	
	
	//------------------------------------------------------------------------------------------------------------------------------//
	
	
	public boolean toGuest(WebDriver driver, String accNumber, String newcustomerType) throws InterruptedException{
		
 			driver.findElement(By.xpath("//*[@id='msisdn']")).sendKeys(accNumber);
		   // driver.findElement(By.id("displayName")).sendKeys("ajhabdc");
		    driver.findElement(By.id("id7")).click();
		    driver.findElement(By.xpath("/html/body/div/div[3]/div[4]/div/div[2]/div/div/form/div[5]/div/table/tbody/tr/td[1]/a/div")).click();

		try{
			migrationCommons.commons(newcustomerType);
		}

		catch(Exception e){
			migrationCommons.commons(newcustomerType);
		}
		
				if(selectCustomerType.getFirstSelectedOption().getText().equals("Guest D/C Block User")){
					guestBlockTypes(driver, accNumber, newcustomerType);
				
				
			}
			
		return true;
	}
	
	
	public boolean guestBlockTypes(WebDriver driver, String accNumber, String newcustomerType) throws InterruptedException{
 
		
		try {
				
				
		  		System.out.println("HI ABSAR MUJADDIDI IS TESTING MIGRATION MODULE - GUEST TYPE MMIGRATION!!!!");
		  
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				System.out.println("waaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
				
				driver.findElement(By.id("motherName")).sendKeys("ASGHAR AFHAN");
				driver.findElement(By.id("permanentCity")).sendKeys("LAHORE"); 		
      			Select permanentProvince = new Select(driver.findElement(By.name("contactInfoDiv:addressDiv:permanentAddressDiv:customer.address.permanentState")));
      			permanentProvince.selectByVisibleText("Punjab");
      			
      			driver.findElement(By.xpath("//*[@name='update']")).click(); 
		}
		catch (Exception e){
			
			e.getMessage();
			
		}
		return true;
	}
	
	

        //---------------------------------------------------------------------------------------------------//
        //--------------------------CORPORATE TYPE MIGRATION-------------------------------------------------//
        
        public boolean corporatetypes(WebDriver driver, String accNumber, String accountTitle,
    			String sellerCODE, String nameCnic, String motherName,
    			String fatherName, String placeofBirth, String identityNumber ,String permanentAddress,
    			String permanentCity, String mailingAddress, String mailingCity, String email,
    			String accountHolderMN, String actualOwner, String beneficialCnic,
    			String expectedCredit, String monthlyExpectedWithdrawal,
    			String expectedDebit, String monthlyExpectedDeposit,
    			String avergaeYearlyIncome, String averageYearlySales,
    			String expectedMonthlyThroughPut, String expectedAvgBalance,
    			String expectedMonthlyCreditSales,
    			String expectedMaxAmountPerTransaction,
    			String expectedNoOfTransaction, String annualTurnover,
    			String natureOfBusiness, String noTinComments, String newcustomerType) throws InterruptedException{

   
        	
        	try{
   		
        	driver.findElement(By.xpath("//*[@id='msisdn']")).sendKeys(accNumber);
   		   // driver.findElement(By.id("displayName")).sendKeys("ajhabdc");
   		    driver.findElement(By.id("id7")).click();
   		    driver.findElement(By.xpath("/html/body/div/div[3]/div[4]/div/div[2]/div/div/form/div[5]/div/table/tbody/tr/td[1]/a/div")).click();
        	}
        	
        	catch(Exception e){
        		System.out.println("ex message: " + e.getMessage());
        		
        	}
   		 System.out.println("TESTING/MIGRATING CORPORATE TYPES");
   		    
      			try{
      				migrationCommons.commons(newcustomerType);
      			}

      			catch(Exception e){
      				migrationCommons.commons(newcustomerType);
      			}
   		
      	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      			
		if(selectCustomerType.getFirstSelectedOption().getText().equals("Corporate Account Master Wallet")) 
			
		{ //IF STARTED
				customertoCorporateTypes( driver,  accNumber,  accountTitle,
		    			 sellerCODE,  nameCnic,  motherName,
		    			 fatherName,  placeofBirth,  identityNumber , permanentAddress,
		    			 permanentCity,  mailingAddress,  mailingCity,  email,
		    			 accountHolderMN,  actualOwner,  beneficialCnic,
		    			 expectedCredit,  monthlyExpectedWithdrawal,
		    			 expectedDebit,  monthlyExpectedDeposit,
		    			 avergaeYearlyIncome,  averageYearlySales,
		    			 expectedMonthlyThroughPut,  expectedAvgBalance,
		    			 expectedMonthlyCreditSales,
		    			 expectedMaxAmountPerTransaction,
		    			 expectedNoOfTransaction,  annualTurnover,
		    			 natureOfBusiness,  noTinComments,  newcustomerType);
				} //END if condition
        	
		
		else if(selectCustomerType.getFirstSelectedOption().getText().equals("Corporate Sim Sim Account User")){

			customertoCorporateTypes( driver,  accNumber,  accountTitle,
	    			 sellerCODE,  nameCnic,  motherName,
	    			 fatherName,  placeofBirth,  identityNumber , permanentAddress,
	    			 permanentCity,  mailingAddress,  mailingCity,  email,
	    			 accountHolderMN,  actualOwner,  beneficialCnic,
	    			 expectedCredit,  monthlyExpectedWithdrawal,
	    			 expectedDebit,  monthlyExpectedDeposit,
	    			 avergaeYearlyIncome,  averageYearlySales,
	    			 expectedMonthlyThroughPut,  expectedAvgBalance,
	    			 expectedMonthlyCreditSales,
	    			 expectedMaxAmountPerTransaction,
	    			 expectedNoOfTransaction,  annualTurnover,
	    			 natureOfBusiness,  noTinComments,  newcustomerType);
			
		}
		else if(selectCustomerType.getFirstSelectedOption().getText().equals("Agent FINJA")){
		
			/// for different corporate groups	
			
			
			
		/*	customertoCorporateTypes( driver,  accNumber,  accountTitle,
	    			 sellerCODE,  nameCnic,  motherName,
	    			 fatherName,  placeofBirth,  identityNumber , permanentAddress,
	    			 permanentCity,  mailingAddress,  mailingCity,  email,
	    			 accountHolderMN,  actualOwner,  beneficialCnic,
	    			 expectedCredit,  monthlyExpectedWithdrawal,
	    			 expectedDebit,  monthlyExpectedDeposit,
	    			 avergaeYearlyIncome,  averageYearlySales,
	    			 expectedMonthlyThroughPut,  expectedAvgBalance,
	    			 expectedMonthlyCreditSales,
	    			 expectedMaxAmountPerTransaction,
	    			 expectedNoOfTransaction,  annualTurnover,
	    			 natureOfBusiness,  noTinComments,  newcustomerType);
			*/
			driver.findElement(By.id("permanentCity")).sendKeys("LAHORE"); 		
  			Select permanentProvince = new Select(driver.findElement(By.name("contactInfoDiv:addressDiv:permanentAddressDiv:customer.address.permanentState")));
  			permanentProvince.selectByVisibleText("Punjab");
			
			driver.findElement(By.id("workingWithOtherBank")).sendKeys("NO");
			driver.findElement(By.id("netWorth")).sendKeys("60000");
			driver.findElement(By.id("typeOfTransaction")).sendKeys("Business");
			driver.findElement(By.id("currentBusinessSince")).sendKeys("2000");
			Select premises = new Select(driver.findElement(By.id("premises")));
			premises.selectByVisibleText("OWNED");
			
			driver.findElement(By.name("update")).click();
			
			
			
		} 	else if(selectCustomerType.getFirstSelectedOption().getText().equals("Agent Finca")){
			
			customertoCorporateTypes( driver,  accNumber,  accountTitle,
	    			 sellerCODE,  nameCnic,  motherName,
	    			 fatherName,  placeofBirth,  identityNumber , permanentAddress,
	    			 permanentCity,  mailingAddress,  mailingCity,  email,
	    			 accountHolderMN,  actualOwner,  beneficialCnic,
	    			 expectedCredit,  monthlyExpectedWithdrawal,
	    			 expectedDebit,  monthlyExpectedDeposit,
	    			 avergaeYearlyIncome,  averageYearlySales,
	    			 expectedMonthlyThroughPut,  expectedAvgBalance,
	    			 expectedMonthlyCreditSales,
	    			 expectedMaxAmountPerTransaction,
	    			 expectedNoOfTransaction,  annualTurnover,
	    			 natureOfBusiness,  noTinComments,  newcustomerType);
		}
		
		
		
		
        	return true;
        }
        

        public boolean customertoCorporateTypes(WebDriver driver, String accNumber, String accountTitle,
    			String sellerCODE, String nameCnic, String motherName,
    			String fatherName, String placeofBirth, String identityNumber ,String permanentAddress,
    			String permanentCity, String mailingAddress, String mailingCity, String email,
    			String accountHolderMN, String actualOwner, String beneficialCnic,
    			String expectedCredit, String monthlyExpectedWithdrawal,
    			String expectedDebit, String monthlyExpectedDeposit,
    			String avergaeYearlyIncome, String averageYearlySales,
    			String expectedMonthlyThroughPut, String expectedAvgBalance,
    			String expectedMonthlyCreditSales,
    			String expectedMaxAmountPerTransaction,
    			String expectedNoOfTransaction, String annualTurnover,
    			String natureOfBusiness, String noTinComments, String newcustomerType) throws InterruptedException{
        
      
         //   System.out.println("HI ABSAR MUJADDIDI IS TESTING MIGRATION MODULE - CORPORAATTE ACCOUNT MASTER WALLET!!!");
            
      
            
          	System.out.println("Selected Customer Type : "+selectCustomerType.getFirstSelectedOption().getText());
        	
        	//driver.findElement(By.id("sellerCode")).sendKeys(sellerCODE);

    		Select typeAccount = new Select(driver.findElement(By.name("generalInformationDiv:accountTypeDiv:customer.address.typeOfAccount")));
    		typeAccount.selectByVisibleText("Corporation");

    		Select nationality = new Select(driver.findElement(By.name("personalDetailDiv:nationalityDiv:customer.kycDetailsBean.generalInfo.nationality")));
    		nationality.selectByVisibleText("AFGHANISTAN");

    	//	driver.findElement(By.id("id4")).sendKeys(accountTitle);
    	//	Assert.assertTrue(accountTitle.matches("^[a-zA-Z\\s]*$"));
    		
    		driver.findElement(By.id("nameAsPerCnic")).sendKeys(nameCnic);
    		//Assert.assertTrue(NameCnic.matches("^(?=.*[1-9])[0-9+]{5}(-){1}[0-9+]{7}(-){1}[0-9]{1}$"));
    		
    		driver.findElement(By.id("motherName")).sendKeys(motherName);
    		//Assert.assertTrue(motherName.matches("^[a-zA-Z\\s]*$"));
    		
    		driver.findElement(By.id("fatherHusbandName")).sendKeys(fatherName);
    		//Assert.assertTrue(FatherName.matches("^[a-zA-Z\\s]*$"));
    		
    		driver.findElement(By.id("placeOfBirth")).sendKeys(placeofBirth);
    		
    		driver.findElement(By.id("permanentCity")).sendKeys("LAHORE");
       		
       		Select permanentProvince = new Select(driver.findElement(By.name("contactInfoDiv:addressDiv:permanentAddressDiv:customer.address.permanentState")));
       		permanentProvince.selectByVisibleText("Punjab");
    		
    		driver.findElement(By.id("permanentAddress")).sendKeys(permanentAddress);
    		//driver..findElement(By.id("permanentCity")).sendKeys(PermanentCity);
    		
    		DesiredCapabilities dc = new DesiredCapabilities();
    		dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
    		
    		driver.findElement(By.id("mailingOrBusinessAddress")).sendKeys(mailingAddress);
    		dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
    		
    		driver.findElement(By.id("mailingOrBusinessCity")).sendKeys(mailingCity);
    		
    		//driver.findElement(By.id("email")).sendKeys(email);
    		
    		Select pOA = new Select(driver.findElement(By.name("kycDiv:occupationDetailsDiv:purposeOfAccountDiv:customer.kycDetailsBean.occupationDetail.purposeOfAccount")));
    		pOA.selectByVisibleText("Business");
    		
    		driver.findElement(By.id("accountHolderMsisdn")).sendKeys(accountHolderMN);
    		
    		Select province = new Select(driver.findElement(By.id("permanentState")));
    		province.selectByIndex(2);
    		
    		Select MailingProvince = new Select(driver.findElement(By.name("contactInfoDiv:addressDiv:mailingOrBusinessAddressDiv:customer.address.mailingOrBusinessState")));
    		MailingProvince.selectByIndex(5);


    		Select BeneficialAccOwner = new Select(driver.findElement(By.name("kycDiv:beneficialOwnerStatusDiv:isBeneficialAccountOwnerDiv:customer.kycDetailsBean.beneficialOwnerStatus.isBeneficialAccountOwner")));
    		BeneficialAccOwner.selectByVisibleText("Yes");

    		//driver.findElement(By.id("actualBeneficialOwner")).sendKeys(actualOwner);

    	/*	Select Relation = new Select(driver.findElement(By.name("kycDiv:beneficialOwnerStatusDiv:beneficialOwnerStatusDetailsDiv:relationWithApplicantDiv:customer.kycDetailsBean.beneficialOwnerStatus.relationWithApplicant")));
    		Relation.selectByVisibleText("Brother");*/

    		//driver.findElement(By.id("cnicBeneficial")).sendKeys(beneficialCnic);

    		Select TxnMode = new Select(driver.findElement(By.id("modeOfTransaction")));
    		TxnMode.selectByVisibleText("All");

    		driver.findElement(By.id("expectedCredit")).sendKeys(expectedCredit);
    		driver.findElement(By.id("monthlyExpectedWithdrawal")).sendKeys(monthlyExpectedWithdrawal);
    		driver.findElement(By.id("expectedDebit")).sendKeys(expectedDebit);				
    		driver.findElement(By.id("monthlyExpectedDeposit")).sendKeys(monthlyExpectedDeposit);									
    		driver.findElement(By.id("avergaeYearlyIncome")).sendKeys(avergaeYearlyIncome);
    		driver.findElement(By.id("averageYearlySales")).sendKeys(averageYearlySales);
    		driver.findElement(By.id("expectedMonthlyThroughPut")).sendKeys(expectedMonthlyThroughPut);
    		driver.findElement(By.id("expectedAvgBalance")).sendKeys(expectedAvgBalance);
    		driver.findElement(By.id("expectedMonthlyCreditSales")).sendKeys(expectedMonthlyCreditSales);
    		driver.findElement(By.id("expectedMaxAmountPerTransaction")).sendKeys(expectedMaxAmountPerTransaction);
    		driver.findElement(By.id("expectedNoOfTransaction")).sendKeys(expectedNoOfTransaction);
    		driver.findElement(By.id("annualTurnover")).sendKeys(annualTurnover);
    		driver.findElement(By.id("natureOfBusiness")).sendKeys(natureOfBusiness);
    		driver.findElement(By.id("sourcesOfIncomeOther")).sendKeys("Business");

    		Select businessType = new Select(driver.findElement(By.id("typeOfBusiness")));
    		businessType.selectByVisibleText("Service");

    		driver.findElement(By.id("noTinComments")).sendKeys(noTinComments);

    		Utility.riskTypes();

    		Select CountryOfBirth = new Select(driver.findElement(By.name("kycDiv:crsDiv:crsIndividual:countryOfBirthDiv:customer.kycDetailsBean.crsBean.countryOfBirth")));
    		CountryOfBirth.selectByVisibleText("PAKISTAN");	


    		Select TaxResidentYN = new Select(driver.findElement(By.name("kycDiv:crsDiv:crsIndividual:customer.kycDetailsBean.crsBean.isTaxResident")));
    		TaxResidentYN.selectByVisibleText("Yes");

    		WebElement UsPerson = driver.findElement(By.name("kycDiv:fatcaDiv:fatcaIndividual:customer.kycDetailsBean.fatca"));
    		UsPerson.click();
    		
    		//Utility.Submit(driver.);
    		
    		 
    		
    	    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    	    	///////////////////////////////////////
    	    	//////AUTOMATING DIRECTOR'S PAGE///////
    	    	//////////////////////////////////////
    	    
    	    driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[4]/div/div[2]/div/div/form/div[2]/div[2]/div[2]/div[2]/div/input")).sendKeys("Absar ahmed Mujaddidi");
    	  
    		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[4]/div/div[2]/div/div/form/div[2]/div[2]/div[2]/div[3]/div/input")).sendKeys("lahore rehman gardens");

    		Select ID = new Select(driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[4]/div/div[2]/div/div/form/div[2]/div[2]/div[2]/div[4]/div/select")));
    		ID.selectByIndex(2);
    		
    		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[4]/div/div[2]/div/div/form/div[2]/div[2]/div[2]/div[5]/div/input")).sendKeys("3001254");
    	
    		Select residentCountry = new Select(driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[4]/div/div[2]/div/div/form/div[2]/div[2]/div[2]/div[6]/div/select")));
    		residentCountry.selectByVisibleText("PAKISTAN");
    		
    		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[4]/div/div[2]/div/div/form/div[2]/div[2]/div[2]/div[7]/div/input")).sendKeys("Lahore");
    		
    			//////////////////////////////////////////
    			//SELECTING RANDOM DATE FOR DIRECTOR DOB//
    			//////////////////////////////////////////
    		
    	/*	try {
    			driver.findElement(By.id("directorDateofBirth")).click();
    			Thread.sleep(500);
    			Utility.selectDate(driver, month_year,day);

    			Thread.sleep(500);
    		}catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} */
    		
    		//for proceeding after adding director//
    		driver.findElement(By.id("idb8")).click();
    		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    		
    		Thread.sleep(250);
    		//CLICKING ON CONTINUE BUTTON ON DIRECTOR'S PAGE//
    		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[4]/div/div[2]/div/div/form/div[2]/div[3]/div/input[3]")).click();

    		Thread.sleep(300);
    		
    		//CLICKING ON CONTINUE BUTTON ON CORPORATE CUSTOMER ONBOARDING PAGE AFTER ADDING DIRECTOR//
    		driver.findElement(By.xpath("/html/body/div/div[3]/div[4]/div/div[2]/div/div/form/div[4]/div/input[1]")).click();
    		driver.findElement(By.xpath("//*[@name='update']")).click(); 
    		
    		return true;
     
     		
     		
          
            
        	
        	
         }
	
}