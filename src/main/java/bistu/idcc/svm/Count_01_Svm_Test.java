package bistu.idcc.svm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.hankcs.hanlp.dependency.nnparser.util.math;

import bistu.idcc.functions.ClearTxt_Path;

public class Count_01_Svm_Test {

	public void count(String path)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
		String temp = null;
		int count_1 = 0;
		int count_0 = 0;
		while((temp = br.readLine()) !=null){
			if(temp.split(" ")[0].equals("0")){
				count_0++;
			}
			else if(temp.split(" ")[0].equals("1")){
				count_1++;
			}
		}
		br.close();
		System.out.println("sum_0: " + count_0 + "; sum_1: " + count_1);
	}
	
	public void delete_0(String inpath, String outpath) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inpath), "UTF-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath), "UTF-8"));
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		String temp = null;
		int count_0 = 0;
		int tag = 0;
		while((temp = br.readLine()) !=null){
			if(temp.split(" ")[0].equals("0")){
				tag = (int) (tag + Math.pow(-1, count_0));
				count_0++;
				if(tag == 0){
					bw.write(temp);
					bw.newLine();
				}
			}
			else if(temp.split(" ")[0].equals("1")){
				bw.write(temp);
				bw.newLine();
			}
		}
		bw.close();
		br.close();
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Count_01_Svm_Test tst = new Count_01_Svm_Test();
		tst.count("sourcefile/testsvm/mx_2.txt");
		tst.delete_0("sourcefile/testsvm/mx_2.txt", "sourcefile/testsvm/mx_3.txt");
		tst.count("sourcefile/testsvm/mx_3.txt");
		Text_creative tt = new Text_creative();
		tt.cretxt("sourcefile/testsvm/mx_3.txt", "sourcefile/testsvm/", 2000);
		
		Svm ss = new Svm();
		ss.svm("sourcefile/testsvm/train_mx_3.txt", "sourcefile/testsvm/mx_3.model", "sourcefile/testsvm/test_mx_3.txt", "sourcefile/testsvm/mx_3_result.txt");

	}

}
