package edu.oit.dataming;

import java.util.ArrayList;
import java.util.HashMap;

public class AssociationRules {
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
	public void generate(ArrayList<char[]> rawData, HashMap<char[], Integer> entry){
		
	}
}
