import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

class WordOccurences {

	public WordOccurences() { }

	public String readFile(String filename) {
		String txt = "";
		BufferedReader br = null;
		InputStreamReader isr = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(filename));
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				txt += line + " ";

			}
			return txt;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				if (br != null)
					br.close();
				if (isr != null)
					isr.close();
				if (fis != null)
					fis.close();
			} catch (Exception e) {

			}
		}
	}

	public Map<String, Integer> getOccurences(String txt, boolean fromFile) {
		if(fromFile) {
			txt = readFile(txt);
		}

		return getOccurences(txt);
	}

	public Map<String, Integer> getOccurences(String txt) {
		Map<String, Integer> words = new HashMap<String, Integer>();
		String tokens[] = txt.split(" ");
		for (String s : tokens) {
			s = s.trim();
			if (s.equals("")) {
				continue;// skip empty strings
			}
			if (words.containsKey(s)) {
				words.put(s, words.get(s) + 1);
			} else {
				words.put(s, 1);
			}
		}
		return words;
	}
}

public class App {

	public void loadWords(String filename) {
		WordOccurences wo = new WordOccurences();
		Map<String, Integer> map = wo.getOccurences(filename, true);
		// sort them
		Map<String, Integer> sortedMap = map.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		String txt = "";
		int count = 0;
		for (String s : sortedMap.keySet()) {
			count++;
			txt += s + ": " + map.get(s) + "\n";
			if (count == 20)// only 20 sorted values
			{
				break;
			}
		}
		System.out.println(txt);
	}

	public static void main(String[] args) {
		App app = new App();
		app.loadWords("input.txt");
	}

}
