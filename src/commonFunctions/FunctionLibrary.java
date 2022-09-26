package commonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil {
	
	//method for Login
	public static boolean verifyLogin(String username, String password) throws Throwable {
		driver.findElement(By.xpath(config.getProperty("ObjUser"))).sendKeys(username);
		driver.findElement(By.xpath(config.getProperty("ObjPass"))).sendKeys(password);
		driver.findElement(By.xpath(config.getProperty("ObjLogin"))).click();
		String expected = "adminflow";
		String actual = driver.getCurrentUrl();
		if(actual.toLowerCase().contains(expected)) {
			Reporter.log("Login success  "+expected+"  "+actual);
			return true;
		}
		
		else {
			Reporter.log("Login Unsuccess" + expected+"  "+actual);
			return false;
		}
		
		
	}
	//method for New Branch creation
	public static void clickBranches() throws Throwable{
		driver.findElement(By.xpath(config.getProperty("ObjBranches"))).click();
	}
	public static boolean verifyNewBranch(String BranchName, String Address1,String Address2, String Address3, String Area, String Zipcode, 
			String country, String state, String city) throws Throwable {
		
		driver.findElement(By.xpath(config.getProperty("ObjNew"))).click();
		driver.findElement(By.xpath(config.getProperty("ObjBranch"))).sendKeys("Vijayawada");
		driver.findElement(By.xpath(config.getProperty("ObjAddress1"))).sendKeys("Chittinagar");
		driver.findElement(By.xpath(config.getProperty("ObjAddress2"))).sendKeys("junction");
		driver.findElement(By.xpath(config.getProperty("ObjAddress3"))).sendKeys("Eedgha Mahal");
		driver.findElement(By.xpath(config.getProperty("ObjArea"))).sendKeys("Kothapeta");
		driver.findElement(By.xpath(config.getProperty("ObjZipcode"))).sendKeys("520001");
		new Select(driver.findElement(By.xpath(config.getProperty("ObjCountry")))).selectByVisibleText(country);
		new Select(driver.findElement(By.xpath(config.getProperty("ObjState")))).selectByVisibleText(state);
		new Select(driver.findElement(By.xpath(config.getProperty("ObjCity")))).selectByVisibleText(city);
		driver.findElement(By.xpath(config.getProperty("ObjSubmit"))).click();
		//capture alert message
		String expected = driver.switchTo().alert().getText();
		String actual = "New Branch with";
		if(expected.toLowerCase().contains(actual)) {
			Reporter.log(expected,true);
			return true;
		}
			else {
				Reporter.log("Unable to create new branch", true);
		return false;
			
			}
	}
		//method for branch updation
	public static boolean verifyBranch(String BranchName, String Address, String Area, String Zip) {
		driver.findElement(By.xpath(config.getProperty("ObjEdit"))).click();
		driver.findElement(By.xpath(config.getProperty("ObjBranchName"))).sendKeys("Vinay");
		driver.findElement(By.xpath(config.getProperty("ObjAddress"))).sendKeys("dfdg");
		driver.findElement(By.xpath(config.getProperty("ObjArea1"))).sendKeys("dfgdrhr");
		driver.findElement(By.xpath(config.getProperty("ObjZip"))).sendKeys("520002");
		//capture alet message
		String expected = driver.switchTo().alert().getText();
		String actual ="Branch updated";
		if(expected.toLowerCase().contains(actual)) {
			Reporter.log(expected,true);
			return true;
			
		}
		else {
			Reporter.log("Unable to update",true);
			return false;
		}
	}
		//method for Logout
		public static boolean verifyLogout() throws Throwable {
			driver.findElement(By.xpath(config.getProperty("ObjLogout"))).click();
			if(driver.findElement(By.xpath(config.getProperty("ObjLogin"))).isDisplayed()) 
			{
				Reporter.log("Logout Success",true);
				return true;
				
			}
			else {
				Reporter.log("Fail to Logout",true);
				return false;
			}
			
		}
		//method for Role creation
		public static boolean verifyNewRole(String RoleName, String Description, String RoleType) throws Throwable{
			driver.findElement(By.xpath(config.getProperty("ObjReset"))).click();
			driver.findElement(By.xpath(config.getProperty("ObjNew1"))).click();
			driver.findElement(By.xpath(config.getProperty("ObjRoleName"))).sendKeys("VD");
			driver.findElement(By.xpath(config.getProperty("ObjRoleDesc"))).sendKeys("jdehsdhfsdh");
			new Select(driver.findElement(By.xpath(config.getProperty("ObjRoleType")))).selectByVisibleText(RoleType);
			driver.findElement(By.xpath(config.getProperty("ObjSubmit1"))).click();
			//capture alert message
			String expected = driver.switchTo().alert().getText();
			String actual = "New Role with";
			if(expected.toLowerCase().contains(actual)) {
				Reporter.log(expected,true);
				return true;
			}
				else {
					Reporter.log("Unable to create new branch", true);
			return false;
				
				}
			}
		}
		
		
		
		
		
		
	
		
		
	

