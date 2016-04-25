package bistu.idcc.features;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
/**
 * 词袋模型类
 * 给定分词后路径，用于构建词袋模型，bagwords<String,Integer>,分别为词，词的位置。
 * @author Joen
 *
 */
public class Bagwords {
	
	Map<String, Integer> bagwords = new HashMap<String, Integer>();
	
	public Bagwords(String path)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
		String temp = null;
		int i = 0;
		while((temp = br.readLine())!=null){
			temp = temp.replaceAll("\\{|\\}|【|】", "");
			String[] templist = temp.split(" ");
			for(String s: templist){
				if(!bagwords.containsKey(s)){
					i++;
					bagwords.put(s, i);
				}
			}
			
		}
		br.close();
	} 
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Bagwords bg = new Bagwords("sourcefile/features/parse.txt");
	}

}
