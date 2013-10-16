package edu.oit.dataming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Apriori {
	protected int min_sup = 2;
    protected ArrayList<int[]> rawTransaction = new  ArrayList<int[]>();
    
    public Apriori(){
    	rawTransaction = this._genTestData();
    } 
    
    public Apriori(ArrayList<int[]> trans){
    	rawTransaction = trans;
    }
    
    public Apriori(ArrayList<int[]> trans, int min_sup){
    	rawTransaction = trans;
    	this.min_sup = min_sup;
    }
/**    
 * This function has the same function as find_frequent_1-itemsets on page 239
 * @return L1 type: HashMap containing the single itemset and it's support number.
 */
    public HashMap<int[], Integer> getItemFrequency(){
    	HashMap<int[], Integer> freq = new HashMap<int[], Integer>();
    	for (int i = 0 ; i < rawTransaction.get(0).length; i++ ){
    		int tmp[] = new int[rawTransaction.get(0).length];
    		int count = 0;
    		tmp[i] = 1;
    		for (int[] subset: rawTransaction){
    			if (subset[i] == 1) count++;
    		}
    		if (count >= this.min_sup)freq.put(tmp, count);
    	}
    	return freq;
    }
    
    public void implementApriori(){
    	HashMap<int[], Integer> output = this.getItemFrequency();
    	for (int i = 2 ; !output.isEmpty() ; i++){
    		
    	}
    }
    
    public HashMap<int[], Integer> filterUnvalid(HashMap<int[], Integer> input){
    	HashMap<int[], Integer> output = new HashMap<int[], Integer>();
    	for (int[] key: output.keySet()){
    		if (input.get(key) >= this.min_sup){
    			output.put(key, input.get(key));
    		}
    	}
    	return output;
    }
    
    private ArrayList<int[]> _genTestData(){
    	ArrayList<int[]> test = new ArrayList<int[]>();
    	int data[][] = {
    			        {1,1,0,0,1},
    			        {0,1,0,1,0},
    			        {0,1,1,0,0},
    			        {1,1,0,1,0},
    			        {1,0,1,0,0},
    			        {0,1,1,0,0},
    			        {1,0,1,0,0},
    			        {1,1,1,0,1},
    			        {1,1,1,0,0}
    			        };
        for (int i = 0; i < data.length; i++){
        	test.add(data[i]);
        }
    	return test;
    }
    
    private void _printHashMap(HashMap<int[], Integer> input){
    	for (int[] key: input.keySet()){
        	System.out.println("key = :"+ Arrays.toString(key));
        	System.out.println("value = :" + input.get(key));
        }
    }
    
}
