package edu.oit.dataming;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class AssociationRules {
	
	private boolean test = false;
	
	private class Rule implements Comparable<Rule> {
		private String antecedent;
		private String consequent;
		private Integer support;
		private Integer confidence;

		public void set_antecedent(String value) {
			this.antecedent = value;
		}

		public String get_antecedent() {
			return this.antecedent;
		}

		public void set_consequent(String value) {
			this.consequent = value;
		}

		public String get_consequent() {
			return this.consequent;
		}

		public void set_support(Integer value) {
			this.support = value;
		}

		public Integer get_support() {
			return this.support;
		}

		public void set_confidence(Integer value) {
			this.confidence = value;
		}

		public Integer get_confidence() {
			return this.confidence;
		}

		public Integer get_rank() {
			return this.support * this.confidence;
		}
		
		@Override
		public int compareTo(Rule r)
		{
		    return(this.get_rank() - r.get_rank());
		}
	}
	
	private int min_sup;
	private int min_conf;
	
	public AssociationRules(int min_sup, int min_conf){
		this.min_sup = min_sup;
		this.min_conf = min_conf;
	}
/**
 * used for generating association rules and write the result into a excel file.
 * 
 * @param  rawData ,entry
 * rawData is the data converted from the origin transactions data. 
 *      each transaction  is translated into a integer array. 
 *      for example: ['1','0','1','0','1'] indicates {I1,I3,I5}
 * HashMap key: 
 *       type: char[], [I1,I2,...,Ii]
 *       example: ['1','0','0','0','1'] stands for {I1,I5}.
 *       note: in order to user hashMap's own function to avoid duplication, you 
 *             may need to convert key to a string.Two arrays having the same value 
 *             in HashMap may be treated as different keys.  
 * HashMap value:
 *       type: integer
 *       meaning: indicate the itemset's(Hash key) absolute support numbers.
 */
	public void generate(HashMap<char[], Integer> rawData, HashMap<char[], Integer> entry, String file){
		List<Rule> ruleList = new ArrayList<Rule>();
		HashMap<Integer, Integer> convertedRawData;
		HashMap<Integer, Integer> convertedEntry;
		convertedRawData = this.convertmap(rawData);
		convertedEntry = this.convertmap(entry);
		Iterator<Map.Entry<Integer, Integer>> it = convertedEntry.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Integer> pairs = (Map.Entry<Integer, Integer>)it.next();
			Integer itemset = (Integer)pairs.getKey();
			Integer support_count = (Integer)pairs.getValue();
			for (int i=1;i<itemset;i++) {
				if (this.is_subset(i, itemset)) {
					Rule rule = new Rule();
					if (this.test) {System.out.println("with subset: "+Integer.toString(i));}
					Integer conf = 100*support_count/convertedRawData.get(i);
					if (conf < this.min_conf) {continue;}
					rule.set_antecedent(this.getitemstring(i));
					rule.set_consequent(this.getitemstring(itemset&~i));
					rule.set_support(convertedRawData.get(i));
					rule.set_confidence(conf);
					ruleList.add(rule);
				}
			}
		}
		Collections.sort(ruleList, Collections.reverseOrder());
		this.writeexcelfile(ruleList,file);
	}

	private HashMap<Integer, Integer> convertmap(HashMap<char[], Integer> inputmap) {
		HashMap<Integer, Integer> resultmap = new HashMap<Integer, Integer>();
		Iterator<Map.Entry<char[], Integer>> it = inputmap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<char[], Integer> pairs = (Map.Entry<char[], Integer>)it.next();
			char[] ikey = pairs.getKey();
			Integer okey = 0;
			int i = 1;
			for (char c : ikey) {
				if (c == '1') {
					okey += i;
				}
				i*=2;
			}
			if (this.test) {System.out.println("convert: "+Integer.toString(okey));}
			resultmap.put(okey, pairs.getValue());
		}
		return resultmap;
	}
	
	private void writeexcelfile(List<Rule> ruleList, String file) {
		Iterator<Rule> iterator = ruleList.iterator();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Association Rules");
		List<Object[]> data = new ArrayList<Object[]>();
		data.add(new Object[] {"Antecedent", "Consequent", "Support", "Confidence", "Support x Confidence"});
		while (iterator.hasNext()) {
			Rule rule = iterator.next();
			data.add(new Object[] {rule.get_antecedent(), rule.get_consequent(), rule.get_support(), rule.get_confidence(), rule.get_rank()});
		}
		int rownum = 0;
		Iterator<Object[]> diterator = data.iterator();
		while (diterator.hasNext()) {
			Object [] objArr = diterator.next();
		    Row row = sheet.createRow(rownum++);
		    int cellnum = 0;
		    for (Object obj : objArr) {
		        Cell cell = row.createCell(cellnum++);
		        if(obj instanceof Date) 
		            cell.setCellValue((Date)obj);
		        else if(obj instanceof Boolean)
		            cell.setCellValue((Boolean)obj);
		        else if(obj instanceof String)
		            cell.setCellValue((String)obj);
		        else if(obj instanceof Double)
		            cell.setCellValue((Double)obj);
		        else if(obj instanceof Integer)
		            cell.setCellValue((Integer)obj);
		    }
		}
		try {
		    FileOutputStream out =
		            new FileOutputStream(new File(file));
		    workbook.write(out);
		    out.close();
		    System.out.println("Association Rules Excel written successfully..");
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	private String getitemstring(Integer itemset) {
		String buff = "";
		boolean first_flag = true;
		int maxitem = (int)(Math.log(itemset)/Math.log(2))+1;
		for (int i=1;i<=maxitem;i++) {
			Integer bit = (int) Math.pow(2, i-1);
			if ((bit&itemset)==0) {
				continue;
			}
			if (first_flag) {
				first_flag = false;
				buff += "I"+Integer.toString(i);
				continue;
			}
			buff += ",I"+Integer.toString(i);
		}
		return buff;
	}
	
	private boolean is_subset(Integer cand, Integer itemset) {
		if ((cand &~ itemset) == 0) {return true;}
		return false;
	}

}

