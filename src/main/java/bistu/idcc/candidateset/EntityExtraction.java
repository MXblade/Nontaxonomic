package bistu.idcc.candidateset;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;

import bistu.idcc.functions.ClearTxt_Path;
import bistu.idcc.parser.*;

public class EntityExtraction {
	//词典的位置
	public static String termpath;
	//分词后的语料位置
	public static String parserpath;
	//进行词典匹配后抛去单个实体之后的语料位置
	public static String tagparpath;
	public static ArrayList<String> term;
	
	public EntityExtraction() throws IOException{
		
		term = new ArrayList<String>();
		//实体词典的位置
		termpath = "sourceFile/PatentTerm.txt";
		//分词后的语料的位置
		parserpath = "sourceFile/after_parser.txt";
		//抽取后标记实体的语料
		tagparpath = "sourceFile/candidate/TagParser.txt";
		readTerm(termpath);
		

		
		//创建输出文件，将每句话匹配的实体输出到ExtractTerm.txt，将标记好实体的语料输出到TagParser.txt
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(tagparpath);
		
		
		//读取分词语料
		File readparser = new File(parserpath);
		InputStreamReader readps = new InputStreamReader(new FileInputStream(readparser),"GBK");
		
		BufferedReader inparser = new BufferedReader(readps);
		String parser = null;
		int num  = 0;
		while((parser = inparser.readLine())!= null)
			{
				String sentence = parser;
				matchTerm(sentence);
				System.out.println(num++);
				/*String[] list = sentence.split("\t");
				for(int i = 0;i < list.length; i++)
				{
					//System.out.println(list[i]);
					String[] s = list[i].split("/");
					//System.out.println(s[0]);
					
				}*/
				
				
				
			}
		
	}
	
	/**
	 * 讲PatentTerm.txt中的词读入到数组中，避免每次匹配都进行读取操作
	 */
	public static void readTerm(String path) throws IOException{
		File readfile = new File(path);
		InputStreamReader read = new InputStreamReader(new FileInputStream(readfile),"UTF-8");
		BufferedReader in = new BufferedReader(read);
		String line = null;
		
		int i = 0;
		while((line = in.readLine())!= null)
		{
			term.add(line);
			//System.out.println(line);
			i++;
		 }
		in.close();
	}
	
	
	/**
	 * 匹配words是否为词典PatentTerm.txt中的词
	 * @throws IOException
	 */
	public static void matchTerm(String sentence) throws IOException {
		
		//用于统计每句话的的实体数目,淘汰部分句子中实体数目小于2的句子，单个实体的句子无实验的意义。
		int entitynum = 0;
		
		//
		
		String ss = "";
		//按照制表符“\t”把每句话分割成每个词用String数组存储
		String[] swlist = sentence.split("   ");
		for(int i = 0;i < swlist.length; i++)
		{
			//单个词sword，将“发动机/n”这种分割成“发动机”和“n”
			String[] sword = swlist[i].split("/");
			for(int j=0;j<term.size();j++)
			{
				if(sword[0].equals(term.get(j)))
				{
					//System.out.println(sword[0]);
					entitynum++;
					sword[0] = "【"+sword[0]+"】";
					//System.out.print(sword[0]);
					
				}
			}
			if(sword[1].equals("v")){
				sword[0] = "{" + sword[0] + "}";
			}
			
			ss = ss + sword[0];
			
		}
		
		if(entitynum > 1)
		{
			File out = new File(tagparpath);
			FileWriter outtag = new FileWriter(out,true);
			BufferedWriter bufout = new BufferedWriter(outtag);
			bufout.write(ss);
			bufout.newLine();
			bufout.close();
			//System.out.println(ss);
		}
		//System.out.println();
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityExtraction ee;
		try {
			ee = new EntityExtraction();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//boolean ss = ee.matchTerm("动力电池");
		//System.out.println(ss);
		System.out.println(new Date());
	}

}
