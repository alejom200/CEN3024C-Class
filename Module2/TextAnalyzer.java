
/*
Program: Text Analyzer
Assignment: Module 2

*/

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class TextAnalyzer {
    public static void main(String[] args) throws FileNotFoundException {
        /** Reading file line by line */
        File file = new File("/home/amarin/workspace/CEN3024C-Class/Module2/poemFile-1.txt");
        try (Scanner scan = new Scanner(file)) {

            // map to store key value pair, key : word and value: frequency of the word
            Map<String, Integer> map = new HashMap<String, Integer>();

            // Loop to reading line by line
            while (scan.hasNextLine()) {
                String val = scan.nextLine();
                // Condition: if the string is not inserted in the map yet then insert by setting and set the frequency as 1
                if (map.containsKey(val) == false) 
                    map.put(val, 1);
                else {
                    int count = (int) (map.get(val)); // locate the actual word frequency 
                    map.remove(val);
                    map.put(val, count + 1); // and increase frequency +1
                }
            }

            Set<Map.Entry<String, Integer>> set = map.entrySet(); // Get the map contents
            List<Map.Entry<String, Integer>> sortedList = new ArrayList<Map.Entry<String, Integer>>(set); // make an array list                                                                                                
            Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>() // sorting the array list
            {
                public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) // Compare the sorting of the array
                {
                    return (b.getValue()).compareTo(a.getValue()); // for descending order
                }
            });

            // Print statement
            for (Map.Entry<String, Integer> i : sortedList) {
                System.out.println(i.getKey() + " -> " + i.getValue());
            }
        }
    }
}