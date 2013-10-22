package edu.oit.dataming;

import java.util.HashMap;

public class AssociationRulesUnitTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AssociationRules ar = new AssociationRules(2, 25);
		HashMap<char[], Integer> rawData = new HashMap<char[], Integer>();
		HashMap<char[], Integer> entry = new HashMap<char[], Integer>();
		char[] c1 = {'1','0','0','0','0'};
		rawData.put(c1, 6);
		char[] c2 = {'0','1','0','0','0'};
		rawData.put(c2, 7);
		char[] c3 = {'0','0','1','0','0'};
		rawData.put(c3, 6);
		char[] c4 = {'0','0','0','1','0'};
		rawData.put(c4, 2);
		char[] c5 = {'0','0','0','0','1'};
		rawData.put(c5, 2);
		char[] c6 = {'1','1','0','0','0'};
		rawData.put(c6, 4);
		char[] c7 = {'1','0','1','0','0'};
		rawData.put(c7, 4);
		char[] c8 = {'1','0','0','0','1'};
		rawData.put(c8, 2);
		char[] c9 = {'0','1','1','0','0'};
		rawData.put(c9, 4);
		char[] c10 = {'0','1','0','1','0'};
		rawData.put(c10, 2);
		char[] c11 = {'0','1','0','0','1'};
		rawData.put(c11, 2);
		char[] c12 = {'1','1','1','0','0'};
		entry.put(c12, 2);
		char[] c13 = {'1','1','0','0','1'};
		entry.put(c13, 2);
		ar.generate(rawData, entry,"outputFile/rules.xls");
	}

}
