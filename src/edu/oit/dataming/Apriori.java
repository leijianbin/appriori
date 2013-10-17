package edu.oit.dataming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Apriori {
	protected int min_sup = 2;
    protected ArrayList<char[]> rawTransaction = new  ArrayList<char[]>();
    protected HashMap<char[], Integer> record = new HashMap<char[], Integer>();
    
    public Apriori(){
    	rawTransaction = this._genTestData();
    } 
    
    public Apriori(ArrayList<char[]> trans){
    	rawTransaction = trans;
    }
    
    public Apriori(ArrayList<char[]> trans, int min_sup){
    	rawTransaction = trans;
    	this.min_sup = min_sup;
    }
/**    
 * This function has the same function as find_frequent_1-itemsets on page 239
 * @return L1 type: HashMap containing the single itemset and it's support number.
 */
    public HashMap<char[], Integer> getItemFrequency(){
    	HashMap<char[], Integer> freq = new HashMap<char[], Integer>();
    	for (int i = 0 ; i < rawTransaction.get(0).length; i++ ){
    		char tmp[] = new char[rawTransaction.get(0).length];
    		Arrays.fill(tmp, '0');
    		int count = 0;
    		tmp[i] = '1';
    		for (char[] subset: rawTransaction){
    			if (subset[i] == '1') count++;
    		}
    		if (count >= this.min_sup){
    			freq.put(tmp, count);
    			record.put(tmp, count);
    			}
    	}
    	return freq;
    }
    
    public void implementApriori(){
    	HashMap<char[], Integer> output = this.getItemFrequency();
    	for (int i = 2 ; !output.isEmpty() ; i++){
    		output = this.aprioriGenerator(output.keySet(), i);
    	    System.out.println("L"+(i-1)+":");
    	    this._printHashMap(output);
    	}
    }
    
    public HashMap<char[], Integer> getRecord(){
    	return this.record;
    }
    
    
    private HashMap<char[], Integer> aprioriGenerator(Set<char[]> inputSet, int k){
    	HashSet<String> output = new HashSet<String>();
    	HashMap<char[], Integer> Ls = new HashMap<char[], Integer>();
    	HashMap<char[], Integer> Cs = new HashMap<char[], Integer>();
    	int itemNum = 0;
    	int i = 0;
    	int support = 0;
    	int inputSize = inputSet.size();
    	String[] itemSet = new String[inputSize];
    	for (char[] one: inputSet){
    		if (itemNum == 0)itemNum = one.length;
    		itemSet[i] = String.valueOf(one);
    		i++;
    	}
    	for (int m = 0 ; m < inputSize; m++){
    		for (int n = m+1 ; n < inputSize; n++){
    			String result = this._joinTwoSet(itemSet[m], itemSet[n], k-2);
    			if (result!=""){
    				support = this._calculateSupport(result);
    				Cs.put(result.toCharArray(), support);
    				if (support >= this.min_sup && !output.contains(result)){
    					output.add(result);
    					//System.out.println(result);
    					Ls.put(result.toCharArray(), support);
    					record.put(result.toCharArray(), support);
    				}
    			}
    		}
    	}
    	return Ls;
    }
    
    public int _calculateSupport(String itemSet){
    	int count = 0;
    	int s_toi;
    	int itemSet_toi = Integer.parseInt(itemSet, 2);
    	String temp;
    	for (char[] set: this.rawTransaction){
    		temp = String.valueOf(set);
    		s_toi = Integer.parseInt(temp,2);
    		if ((s_toi & itemSet_toi) == itemSet_toi)count++;
    	}
    	return count;
    }
    
    public String _joinTwoSet(String a, String b, int common){
    	int a_toi =  Integer.parseInt(a, 2);
    	int b_toi =  Integer.parseInt(b, 2);
    	int length = a.trim().length();
    	int afterAnd = a_toi&b_toi;
    	int afterOr;
    	String mid = Integer.toBinaryString(afterAnd);
    	int count = mid.replaceAll("[^1]", "").length();
    	if (count == common){
    		afterOr = a_toi | b_toi;
    		String result = Integer.toBinaryString((int)Math.pow(2,length) | afterOr).substring(1);
    		return result;
    	}
    	else return "";
    }
    
    public HashMap<char[], Integer> filterUnvalid(HashMap<char[], Integer> input){
    	HashMap<char[], Integer> output = new HashMap<char[], Integer>();
    	for (char[] key: output.keySet()){
    		if (input.get(key) >= this.min_sup){
    			output.put(key, input.get(key));
    		}
    	}
    	return output;
    }
    
    private ArrayList<char[]> _genTestData(){
    	ArrayList<char[]> test = new ArrayList<char[]>();
    	char data[][] = {
    			        {'1','1','0','0','1'},
    			        {'0','1','0','1','0'},
    			        {'0','1','1','0','0'},
    			        {'1','1','0','1','0'},
    			        {'1','0','1','0','0'},
    			        {'0','1','1','0','0'},
    			        {'1','0','1','0','0'},
    			        {'1','1','1','0','1'},
    			        {'1','1','1','0','0'}
    			        };
        for (int i = 0; i < data.length; i++){
        	test.add(data[i]);
        }
    	return test;
    }
    
    public void _printHashMap(HashMap<char[], Integer> input){
    	for (char[] key: input.keySet()){
        	System.out.print("key = :"+ Arrays.toString(key));
        	System.out.println("value = :" + input.get(key));
        }
    }
    
}
