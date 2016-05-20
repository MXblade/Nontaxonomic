package bistu.idcc.functions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import bistu.idcc.functions.ClearTxt_Path;
/**
 * 给定inpath1，inpath2，outpath
 * 将inpath2对应的文件的内容 merge到inpath1文件，并输出到outpath
 * 格式：inpath1内容 inpath2内容
 */
public class Merge {

	public void merge(String inpath1, String inpath2, String outpath)throws IOException{
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(inpath1), "utf-8"));
		BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(inpath2), "utf-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath), "utf-8"));
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		String temp1 = null;
		String temp2 = null;
		while(((temp1 = br1.readLine())!=null) && ((temp2 = br2.readLine())!=null))
		{
			bw.write(temp1 + " " + temp2);
			bw.newLine();
		}
		bw.close();
		br1.close();
		br2.close();
		
		
	}
	public static void main(String[] args) throws IOException{
		Merge m = new Merge();
		m.merge("sourcefile/second/svm/train_1.txt", "sourcefile/second/f_dp_train_4.txt", "sourcefile/second/svm/train_4.txt");
		m.merge("sourcefile/second/svm/test_1.txt", "sourcefile/second/f_dp_test_4.txt", "sourcefile/second/svm/test_4.txt");

	}
}
