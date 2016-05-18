package bistu.idcc.svm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import bistu.idcc.functions.ClearTxt_Path;

/**
 * 对多个特征的文件进行单特征拆分，需给定输入文件路径和输出文件夹路径
 * @author Joen
 *
 */
public class Split_feature {

	/*
	 * 拆分单个特征，分别保存
	 */
	public void split(String inpath, String outpath) throws IOException{
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.clearpath(outpath);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inpath), "UTF-8"));
		String line = br.readLine();
		br.close();
		int num_f = line.split(" ").length - 1;
		for(int i = 1; i <= num_f; i++){
			String fwpath = outpath + "/singl_" + String.valueOf(i) + ".txt";
			clear.cleartxt(fwpath);
			write(inpath, fwpath, i);
		}
		
		
	}
	
	public void write(String inpath, String outpath, int i)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inpath), "UTF-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath), "UTF-8"));
		
		String temp = null;
		while((temp = br.readLine()) != null){
			String[] templist = temp.split(" ");
			bw.write(templist[0] + " " + templist[i]);
			bw.newLine();
		}
		br.close();
		bw.close();
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Split_feature sf = new Split_feature();
		sf.split("sourcefile/testsvm/exam_4.txt", "sourcefile/testsvm/testsingle");
	}

}
