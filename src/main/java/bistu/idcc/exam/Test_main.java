package bistu.idcc.exam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import bistu.idcc.functions.ClearTxt_Path;
import bistu.idcc.functions.Count_01;
import bistu.idcc.svm.Svm;
/**
 * 调用svm测试结果
 * @author Joen
 *
 */
public class Test_main {

	
	public static void main(String[] args) throws IOException{
		svmtest("sourcefile/svm/exam_1.txt");
		

	}
	
	/*
	 * 给定输入文件，对其进行分离训练语料，进行svm和统计次数
	 */
	public static void svmtest(String inpath) throws IOException{
		String txtpath = inpath;
		String trainpath = txtpath.split(".txt")[0] + "_train.txt";
		String testpath = txtpath.split(".txt")[0] + "_test.txt";
		String modelpath = txtpath.split(".txt")[0] + ".model";
		String resultpath = txtpath.split(".txt")[0] + "_result.txt";

		Count_01 tst = new Count_01(txtpath);

	
		Split_train tt = new Split_train();
		tt.cretxt(txtpath, 4500);
		
		Svm ss = new Svm(trainpath, modelpath, testpath, resultpath);
		
		Count_01 tst2 = new Count_01(txtpath);

	}
	
	
	/*
	 * 合并两个文件，与functions中的merge有些不一样，作用一样
	 */
	public static void merge(String path1, String path2, String outpath)throws IOException{
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(path1), "UTF-8"));
		BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(path2), "UTF-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath), "UTF-8"));
		String temp1 = null;
		String temp2 = null;
		while(((temp1 = br1.readLine())!=null) && ((temp2 = br2.readLine())!=null))
		{
			//String f2 = temp2.split(" ")[1];
			bw.write(temp1 + " " + temp2.split(" ")[1]);
			bw.newLine();
		}
		br1.close();
		br2.close();
		bw.close();
	}
	

}
