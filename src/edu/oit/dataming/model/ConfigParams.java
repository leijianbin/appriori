package edu.oit.dataming.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigParams {
	protected String inputFile;
    protected int itemsNum;
    protected int transactionNum;
    protected int min_sup;
    protected int min_conf;
	private BufferedReader br;
    
    public ConfigParams() throws IOException{
    	inputFile = "inputFiles/config.txt";
    	this._setParams();
    }
    
    public ConfigParams(String inputFile) throws IOException{
    	this.inputFile = inputFile;
    	this._setParams();
    }
    
    public int getItemsNum(){
    	return this.itemsNum;
    }
    public int getTransactionNum(){
    	return this.transactionNum;
    }
    public int getMinSupport(){
    	return this.min_sup;
    }
    public int getMinConfidence(){
    	return this.min_conf;
    }
    
    private void _setParams() throws IOException{
    	try{
    	    br = new BufferedReader(new FileReader(this.inputFile));
    	    String line;
    	    int lineNum = 0;
    	    while ((line = br.readLine())!= null){
    	    	int temp = Integer.parseInt(line);
    	    	switch (lineNum){
    	    	case 0 :
    	    		this.itemsNum = temp; break;
    	    	case 1:
    	    		this.transactionNum = temp;break;
    	    	case 2:
    	    		this.min_sup = temp; break;
    	    	case 3:
    	    		this.min_conf = temp; break;
    	    	default:
    	    		break;
    	    	}
    	    	lineNum++;
    	    }
    	    }
    	catch (FileNotFoundException e) {
     	    e.printStackTrace();
         } 
    	catch (IOException e) {
 	        e.printStackTrace();
 	     }
    }
}
