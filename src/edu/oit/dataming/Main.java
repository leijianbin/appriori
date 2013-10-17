package edu.oit.dataming;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.commons.codec.binary.StringUtils;

public class Main {

	public static void main(String[] args) throws IOException  {
        Apriori test = new Apriori();
        test.implementApriori();
        HashMap<char[], Integer> record = test.getRecord();
        System.out.println("record: ");
        test._printHashMap(record);
	}

}
