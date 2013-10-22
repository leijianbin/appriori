package edu.oit.dataming;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.commons.codec.binary.StringUtils;

import edu.oit.dataming.model.ConfigParams;

public class Main {

	public static void main(String[] args) throws IOException  {
		ConfigParams params = new ConfigParams();
		int min_sup = params.getMinSupport();
		int min_conf = params.getMinConfidence();
		System.out.println("minimum support: " + params.getMinSupport());
		System.out.println("minimum confidence: "+ params.getMinConfidence());
		Excel excel = new Excel();
        Apriori test = new Apriori(excel.getMatrix(excel.readFile()),min_sup,min_conf);
        test.implementApriori();
        HashMap<char[], Integer> record = test.getRecord();
        System.out.println("record: ");
        test._printHashMap(record);
        
	}

}
