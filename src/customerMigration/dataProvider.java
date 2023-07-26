package customerMigration;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.collections.Lists;

public class dataProvider {
	static Object data[][];
	
	@DataProvider(name = "registrationGroup")
	public static Object[][] gettestDataRegistrationGroup() throws IOException, InvalidFormatException{
		System.out.println("*** Into the gettestDataRegistrationGroup ***");
		String[] noOfSheets = {};
		noOfSheets = Utility.getSheetNames();
		
		for(int i=0;i<noOfSheets.length;i++){			
			if(i == 0){
				data = Utility.ReadSheet(noOfSheets[i]);
			}
			
			else{
			  List<Object[]> result = Lists.newArrayList();
			  result.addAll(Arrays.asList(data));
			  result.addAll(Arrays.asList(Utility.ReadSheet(noOfSheets[i])));
			  data = result.toArray(new Object[result.size()][]);
			}}
		return data;
		}
}
