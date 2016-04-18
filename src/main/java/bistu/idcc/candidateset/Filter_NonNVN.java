package bistu.idcc.candidateset;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import bistu.idcc.functions.ClearTxt_Path;
/**
 * 过滤两个名词间没有动词的句子。
 * @filter_nonnvn(String path, String outpath)
 * path给定的分词后文件路径
 * outpath 过滤后的输出文件路径。
 * @author Joen
 *
 */

public class Filter_NonNVN {
	public void filter_nonnvn(String path, String outpath)throws IOException{
		//输入流，清空创建输出文件，输出流
		BufferedReader br = new BufferedReader(new FileReader(path));
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		BufferedWriter bw = new BufferedWriter(new FileWriter(outpath));
		
		//以“【”分离，去除分离后的首尾String的｛｝，并重新连接，判断整个句子若存在｛，则输出该句子
		String temp = null;
		while((temp = br.readLine()) != null){
			String[] templist = temp.split("【");
			templist[0] = templist[0].replace("{", "");
			templist[0] = templist[0].replace("}", "" );
			templist[templist.length-1] = templist[templist.length-1].replace("{", "");
			templist[templist.length-1] = templist[templist.length-1].replace("}", "");
			String utemplist = "";
			utemplist = templist[0];
			for(int i = 1; i < templist.length; i++){
				utemplist = utemplist +"【" + templist[i];
			}
			if(utemplist.contains("{"))
			{
				bw.write(utemplist);
				bw.newLine();
			}
		}
		bw.close();
		br.close();
	}
}
