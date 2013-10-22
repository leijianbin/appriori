package edu.oit.dataming;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

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
    //protected HashSet<String> items;
    public Excel(){
    	inputFile = "inputFiles/TransactionDatabase.xlsx";
    }
    
    public Excel(String input){
    	inputFile = input;
    }
    
    
    public void writeFile(Map map){
    	
    }

    /**@author zhenqing
     * read everyline, put each line in a key-value pair
     * using TreeMap so that the key will be sorted
     * @return
     * @throws IOException
     * data.put("T1","I1,I2,I5");
		 data.put("T2", "I2,I4");
		 data.put("T3", "I2, I3");
		 data.put("T4", "I1, I2, I4");
		 data.put("T5", "I1, I3");
     */
    public TreeMap<String,String> readFile() throws IOException{
    	TreeMap<String,String> data = new TreeMap<String,String>();
    	try{
    		FileInputStream file = new FileInputStream(new File(this.inputFile));
    		XSSFWorkbook workbook = new XSSFWorkbook(file);
    		XSSFSheet sheet = workbook.getSheetAt(0);
    		Iterator <Row> rowIterator = sheet.iterator();
    		
 	        String tid;
 	        String itemlist;
 	        rowIterator.next();
 	        while (rowIterator.hasNext()){
 		    	Row row = rowIterator.next();
 		    	tid = row.getCell(0).getStringCellValue();
 		    	itemlist = row.getCell(1).getStringCellValue();
 		    	data.put(tid, itemlist);	
 		    }
     	}catch (FileNotFoundException e) {
     	    e.printStackTrace();
     	} catch (IOException e) {
     	    e.printStackTrace();
     	}
    	return data;
    }
    /**
     * @author zhenqing
     * get the distinct items
     * using TreeSet so the values are distinct and sorted
     * I1,I2,I3,I4,I5
     * @param data
     * @return
     */
    public TreeSet<String> getItems(TreeMap data){
    	TreeSet<String> items = new TreeSet<String>();
    	Collection<String> values = data.values();
    	
		 for(String value:values){
			 value = value.replace(" ", "");
			 String[] tmp = value.split(",");
			 for(int i=0;i<tmp.length;i++){
				 items.add(tmp[i]);
			 }
		 }
		 return items;
    }
    /**
     * @author zhenqing
     * return the matrix 0 1 0 1 for each candidate
     * the format is {[0,1,1,0,0],[1,0,1,1,0]...}
     * @param data
     * @return
     */
    public ArrayList<char[]> getMatrix(TreeMap data){
    	ArrayList<char[]> matrix = new ArrayList<char[]>();
		 TreeSet<String> items = getItems(data);
		 System.out.println(items.toString());
		 
		 Iterator iterator = data.entrySet().iterator();
		 while(iterator.hasNext()){
			 Map.Entry entry=(Map.Entry)iterator.next();
			 String key = (String)entry.getKey();
			 String value =(String) entry.getValue();
			 ArrayList t = new ArrayList();
			char[] tt = new char[items.size()];
			Iterator i = items.iterator();
			int m=0;
			while(i.hasNext()){
				if(value.indexOf((String)i.next())==-1){
					tt[m]= '0';
				}else{
					tt[m]= '1';
				}
				m++;
			}
			 matrix.add(tt);
		 }
		 return matrix;
    }
    

    
}

