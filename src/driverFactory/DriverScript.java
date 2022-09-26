package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil {
	String inputpath = "D:\\SeleniumPrograms\\HybridFrameWork\\TestInput\\DataEngine.xlsx";
	String outputpath = "D:\\SeleniumPrograms\\HybridFrameWork\\TestOutput\\HybridFrameWorkResults.xlsx";
	String Tcsheet = "Test Cases";
	String Tssheet = "Test Steps";
	@Test
	public void startTest()throws Throwable{
		boolean res = false;
		String tcres="";
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//count rows in both sheets 
		int TCCount = xl.rowCount(Tcsheet);
		int TSCount = xl.rowCount(Tssheet);
		Reporter.log(TCCount+"    "+TSCount,true);
		//iterate all rows TCsheet
		for(int i=1; i<=TCCount; i++) {
			//read execution Mode cell
			String ExecutionMode = xl.getCellData(Tcsheet, i, 2);
			if(ExecutionMode.equalsIgnoreCase("Y")) {
				//read tcid cell
				String tcid = xl.getCellData(Tcsheet, i, 0);
				//iterate all rows in Tssheet
				for(int j=1;j<=TSCount;j++) {
					//read tcid cell
					String  tsid = xl.getCellData(Tssheet, j, 0);
					if(tcid.equalsIgnoreCase(tsid)){
						//read Keyword cell
						String KeyWord = xl.getCellData(Tssheet, j, 3);
					
						if(KeyWord.equalsIgnoreCase("AdminLogin")) {
							String para1=xl.getCellData(Tssheet, j, 5);
							String para2=xl.getCellData(Tssheet, j, 6);
							res = FunctionLibrary.verifyLogin(para1, para2);
						}
							else if (KeyWord.equalsIgnoreCase("NewBranch")) {
								String para1 =xl.getCellData(Tssheet, j, 5);
								String para2 =xl.getCellData(Tssheet, j, 6);
								String para3 =xl.getCellData(Tssheet, j, 7);
								String para4 =xl.getCellData(Tssheet, j, 8);
								String para5 =xl.getCellData(Tssheet, j, 9);
								String para6 =xl.getCellData(Tssheet, j, 10);
								String para7 =xl.getCellData(Tssheet, j, 11);
								String para8 =xl.getCellData(Tssheet, j, 12);
								String para9 =xl.getCellData(Tssheet, j, 13);
								res = FunctionLibrary.verifyNewBranch(para1, para2, para3, para4, para5, para6, para7, para8, para9);
								}
							else if (KeyWord.equalsIgnoreCase("UpdateBranch")) {
								String para1 =xl.getCellData(Tssheet, j, 5);
								String para2 =xl.getCellData(Tssheet, j, 6);
								String para3 =xl.getCellData(Tssheet, j, 7);
								String para4 =xl.getCellData(Tssheet, j, 10);
								res = FunctionLibrary.verifyBranch(para1, para2, para3, para4);
								
								
							}
							else if (KeyWord.equalsIgnoreCase("NewRole")) {
								String para1= xl.getCellData(Tssheet, j, 5);
								String para2= xl.getCellData(Tssheet, j, 6);
								String para3= xl.getCellData(Tssheet, j, 7);
								res= FunctionLibrary.verifyNewRole(para1, para2, para3);
							}
							else if (KeyWord.equalsIgnoreCase("AdminLogout"))
							{
								res=FunctionLibrary.verifyLogout();
							}
						String tsres="";
						if(res) {
							
							//if res is true write pass
							tsres = "Pass";
							xl.setCelldata(Tssheet, j, 4, tsres, outputpath);
						}
						else {
							
							tsres = "Fail";
							xl.setCelldata(Tssheet, j, 4, tsres, outputpath);
							}
						tsres=tcres;
					}
						}
				//write tcres into TCSheet
				xl.setCelldata(Tcsheet, i, 3, tcres, outputpath);
			}
			else
			{
				//write as blocked into status cell in TCSheet for flag N
				xl.setCelldata(Tcsheet, i, 3, "Blocked", outputpath);
			}
						
						
					
					}
					
				}
				
			}

			

	
 

