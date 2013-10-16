package edu.oit.dataming;

import java.util.ArrayList;
import java.util.HashMap;

public class AssociationRules {

/**
 * used for generating association rules and write the result into a excel file.
 * 
 * @param  rawData ,entry
 * rawData is the data converted from the origin transactions data. 
 *      each transaction  is translated into a integer array. 
 *      for example: [1,0,1,0,1] indicates {I1,I3,I5}
 * HashMap key: 
 *       type: int[], [I1,I2,...,Ii]
 *       example: [1,0,0,0,1] stands for {I1,I5}.
 * HashMap value:
 *       type: integer
 *       meaning: indicate the itemset's(Hash key) absolute support numbers.
 */
	public void generate(ArrayList<int[]> rawData, HashMap<int[], Integer> entry){
		
	}
}
