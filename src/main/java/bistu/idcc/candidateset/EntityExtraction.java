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
	//�ʵ��λ��
	public static String termpath;
	//�ִʺ������λ��
	public static String parserpath;
	//���дʵ�ƥ�����ȥ����ʵ��֮�������λ��
	public static String tagparpath;
	public static ArrayList<String> term;
	
	public EntityExtraction() throws IOException{
		
		term = new ArrayList<String>();
		//ʵ��ʵ��λ��
		termpath = "sourceFile/PatentTerm.txt";
		//�ִʺ�����ϵ�λ��
		parserpath = "sourceFile/after_parser.txt";
		//��ȡ����ʵ�������
		tagparpath = "sourceFile/candidate/TagParser.txt";
		readTerm(termpath);
		

		
		//��������ļ�����ÿ�仰ƥ���ʵ�������ExtractTerm.txt������Ǻ�ʵ������������TagParser.txt
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(tagparpath);
		
		
		//��ȡ�ִ�����
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
	 * ��PatentTerm.txt�еĴʶ��뵽�����У�����ÿ��ƥ�䶼���ж�ȡ����
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
	 * ƥ��words�Ƿ�Ϊ�ʵ�PatentTerm.txt�еĴ�
	 * @throws IOException
	 */
	public static void matchTerm(String sentence) throws IOException {
		
		//����ͳ��ÿ�仰�ĵ�ʵ����Ŀ,��̭���־�����ʵ����ĿС��2�ľ��ӣ�����ʵ��ľ�����ʵ������塣
		int entitynum = 0;
		
		//
		
		String ss = "";
		//�����Ʊ����\t����ÿ�仰�ָ��ÿ������String����洢
		String[] swlist = sentence.split("   ");
		for(int i = 0;i < swlist.length; i++)
		{
			//������sword������������/n�����ַָ�ɡ����������͡�n��
			String[] sword = swlist[i].split("/");
			for(int j=0;j<term.size();j++)
			{
				if(sword[0].equals(term.get(j)))
				{
					//System.out.println(sword[0]);
					entitynum++;
					sword[0] = "��"+sword[0]+"��";
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
		//boolean ss = ee.matchTerm("�������");
		//System.out.println(ss);
		System.out.println(new Date());
	}

}
