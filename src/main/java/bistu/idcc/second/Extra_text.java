package bistu.idcc.second;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.nlpcn.commons.lang.occurrence.Count;

import bistu.idcc.functions.ClearTxt_Path;

/**
 * 抽取之前包括特征和语料中的语料部分。
 */
public class Extra_text {

	public void extra(String path, String outpath) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath), "utf-8"));
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		String temp = null;
		while((temp = br.readLine())!=null){
			String[] templist = temp.split("	");
			bw.write(templist[1]);
			bw.newLine();
		}
		bw.close();
		br.close();
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Extra_text ex = new Extra_text();
		ex.extra("sourcefile/second/Train.txt", "sourcefile/second/text_train.txt");
		ex.extra("sourcefile/second/Test.txt", "sourcefile/second/text_test.txt");
	}

}
