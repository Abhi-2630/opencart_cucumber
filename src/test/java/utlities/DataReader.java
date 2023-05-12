package utlities;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.devtools.v111.webaudio.model.AudioListenerWillBeDestroyed;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Table.Cell;

public class DataReader 
{
  public static HashMap<String, String> storeValues = new HashMap();
  
  public static List<HashMap<String, String>> data(String filepath, String sheetName)
  {
	  List<HashMap<String, String>> mydata = new ArrayList<>();
	  
	  try
	  {
		  FileInputStream fs=new FileInputStream(filepath);
		  XSSFWorkbook workbook = new XSSFWorkbook(fs);
		  XSSFSheet sheet = workbook.getSheet(sheetName);
		  
		  Row HeaderRow =sheet.getRow(0);
		  
		  for(int i=1; i<sheet.getPhysicalNumberOfRows(); i++)
		  {
			  Row currentRow = sheet.getRow(i);
			  HashMap<String, String> currentHash = new HashMap<String, String>();
			  
			  for(int j=0; j<currentRow.getPhysicalNumberOfCells(); j++)
			  {
				  Cell currentCell = (Cell) currentRow.getCell(j);
				  switch (((org.apache.poi.ss.usermodel.Cell) currentCell).getCellType())
				  {
					  case STRING:
						  currentHash.put(getCell(j).getStringCellValue(), ((org.apache.poi.ss.usermodel.Cell) currentCell).getStringCellValue());
					  break;
				  }
			  }
			  
			  mydata.add(currentHash);
		  }
		  
		  fs.close();
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  
	return mydata;
	  
  }

private static org.apache.poi.ss.usermodel.Cell getCell(int j) {
	// TODO Auto-generated method stub
	return null;
}

}

