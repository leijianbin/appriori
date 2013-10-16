package edu.oit.dataming.model;

public class ConfigParams {
	protected String inputFile;
    protected int itemsNum;
    protected int transactionNum;
    protected int min_sup;
    protected int min_conf;
    
    public ConfigParams(){
    	itemsNum = transactionNum = min_sup = min_conf = 0;
    	inputFile = "/Users/yzhao/dataMining/config.txt";
    }
    
    public ConfigParams(String inputFile){
    	inputFile = inputFile;
    }
}
