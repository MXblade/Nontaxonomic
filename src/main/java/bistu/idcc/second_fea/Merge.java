package bistu.idcc.second_fea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import bistu.idcc.functions.ClearTxt_Path;

public class Merge {

	public void merge(String inpath1, String inpath2, String outpath)throws IOException{
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(inpath1), "utf-8"));
		BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(inpath2), "utf-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath), "utf-8"));
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		String temp1 = null;
		String temp2 = null;
		while(((temp1 = br1.readLine())!=null) && ((temp2 = br2.readLine())!=null)){
			String[] tlist = temp2.split(" ");
			String temp = temp1;
			for(int i = 0; i < tlist.length; i++){
				temp = temp + " " + String.valueOf(i+3695) + ":" + tlist[i];
			}
			//String temp = temp1 + " 3695:" + temp2;
			bw.write(temp);
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
