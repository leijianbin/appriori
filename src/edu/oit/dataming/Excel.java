package edu.oit.dataming;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author yzhao
 * input:  transaction database given by teacher
 * output: an ArrayList<char[]> type object. 
 * each transaction should be transformed in a proper format.
 * for example, there is a transaction record T1: {I1,I3,I4}. T1 should be translated to ['1','0','1','1','0']
 *
 */
public class Excel {
    protected String inputFile;
    protected String outputFile;
    //protected ArrayList<String> items;
    protected HashSet <String> items;
    public Excel(){
    	inputFile = "/Users/yzhao/dataMining/TransactionDatabase.xlsx";
    	items =  new HashSet<String>();
    }
    
    public Excel(String input){
    	inputFile = input;
    	items = new HashSet<String>();
    }
    
    
    public void writeFile(){
    	
    }
    
    public void readFile() throws IOException{
    	try{
    		FileInputStream file = new FileInputStream(new File(this.inputFile));
	        XSSFWorkbook workbook = new XSSFWorkbook(file);
	        XSSFSheet sheet = workbook.getSheetAt(0);
	        Iterator <Row> rowIterator = sheet.iterator();
	        Double cellData_D;
	        String cellData_S;
	        while (rowIterator.hasNext()){
		    	Row row = rowIterator.next();
		    	Iterator<Cell> cellIterator = row.cellIterator();
		        while(cellIterator.hasNext()) {
		            Cell cell = cellIterator.next();
		            cellData_D = null;
		            cellData_S = null;
		            switch(cell.getCellType()) {
		                case Cell.CELL_TYPE_NUMERIC:
		                	//items.add(cell.getNumericCellValue());
		                	cellData_D = cell.getNumericCellValue() ;
		                	items.add(cellData_D.toString());
		                    //System.out.print(cell.getNumericCellValue() + "\t\t");
		                    break;
		                case Cell.CELL_TYPE_STRING:
		                	cellData_S = cell.getStringCellValue();
		                	String[] singleTransaction = cellData_S.split(",");
		                	for (String tmp: singleTransaction){
		                		this.items.add(tmp.trim());
		                	}
		                    //System.out.print(cell.getStringCellValue() + "\t\t");
		                    break;
		            }
		        }
		        System.out.println("");
		    }
	        System.out.println(items);
//	        for (String s: items){
//	        	System.out.println(s);
//	        }
    	}catch (Exception e){
    		e.printStackTrace();
    	}
    }
    
}

