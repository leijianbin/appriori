package edu.oit.dataming;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws IOException  {
//		int value1 = Integer.parseInt("0101011", 2);
//		int value2 = Integer.parseInt("011",2);
//		int value3 = value1&value2;
//		System.out.println("value1: "+value1);
//		System.out.println("value2: "+value2);
//		System.out.println("value1 & value2 :" + value3);
		
		//Excel fOperat = new Excel();
		//fOperat.readFile();
//		int test[] = {0,1,2,2,2,8};
//		System.out.println(Arrays.toString(test));
		Map<int[], Integer> entry = new HashMap<int[], Integer>();
		int a1[] = {0,1,0,1,0};
		int a2[] = {1,1,0,0,1};
		entry.put(a1, 3);
		entry.put(a2, 4);
		for (Map.Entry ent : entry.entrySet()) {
			int key[] = (int[])ent.getKey();
            System.out.println("Key = " + key[0] + ", Value = " +
                               ent.getValue());
        }
		
	}

}
