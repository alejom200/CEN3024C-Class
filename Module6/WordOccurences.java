package applicationwords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordOccurences {

	Map<String, Integer> map;
	public WordOccurences(String filename)
	{
		String txt = readFile(filename);
		map = this.getOccurences(txt);
	}
	public String readFile(String filename)
	{
		String txt="";
		BufferedReader br =null;
		InputStreamReader isr=null;
		FileInputStream fis =null;
		try {
			fis = new FileInputStream(new File(filename));
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String line;
			while((line = br.readLine()) != null)
			{
				txt += line+ " ";
				
			}
			return txt;
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		finally {
			try {
			if(br != null)br.close();
			if(isr != null)isr.close();
			if(fis != null)fis.close();
			}catch(Exception e)
			{
				
			}
		}
	}
	
	public Map<String, Integer> getOccurences(String txt)
	{
		Map<String, Integer> words = new HashMap<String, Integer>();
		String tokens[] = txt.split(" ");
		for(String s: tokens)
		{
			if(s.trim().equals(""))
			{
				continue;//skip empty strings
			}
			if(words.containsKey(s))
			{
				words.put(s, words.get(s)+1);
			}
			else {
				words.put(s, 1);
			}
		}
		return words;
	}
}
