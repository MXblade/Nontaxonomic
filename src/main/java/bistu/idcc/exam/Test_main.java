package bistu.idcc.exam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import bistu.idcc.functions.ClearTxt_Path;

/**
 * 调用svm测试结果
 * @author Joen
 *
 */
public class Test_main {

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
	
	public static void equal(String path1, String path2) throws IOException{
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(path1), "UTF-8"));
		BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(path2), "UTF-8"));
		String temp1 = null;
		String temp2 = null;
		int count = 0;
		while(((temp1 = br1.readLine())!=null) && ((temp2 = br2.readLine())!=null))
		{
			count++;
			if(!temp1.equals(temp2))
				System.out.println("not equal: " + count);
			
		}
		
		br1.close();
		br2.close();
		
	}
	/*
	 * 测试单个特征的分类效果
	 */
	public static void test_single() throws IOException{
		for(int i = 1; i < 19; i++){
		String exampath = "sourcefile/testsvm/testsingle/singl_" + String.valueOf(i) + ".txt";
		Count_01_Svm_Test tst = new Count_01_Svm_Test();
//		tst.count(exampath);
//		tst.delete_0("sourcefile/testsvm/mx_2.txt", "sourcefile/testsvm/mx_3.txt");
//		tst.count("sourcefile/testsvm/mx_3.txt");

		
		Text_creative tt = new Text_creative();
		tt.cretxt(exampath, 5000);
		String trainpath = exampath.split(".txt")[0] + "_train.txt";
		String testpath = exampath.split(".txt")[0] + "_test.txt";
		String modelpath = exampath.split(".txt")[0] + ".model";
		String resultpath = exampath.split(".txt")[0] + "_result.txt";
//		Svm ss = new Svm();
//		ss.svm(trainpath, modelpath, testpath, resultpath);
		System.out.println(i + ": ");
		tst.count(resultpath);
		}
	}
	
	/*
	 * 转化特征表示
	 */
	public static void tran_f()throws IOException{
		
		String inpath = "sourcefile/svm/exam_4.txt";
		String outpath = "sourcefile/testsvm/tran_exam_4.txt";
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
				
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inpath), "UTF-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath), "UTF-8"));
		String temp = null;
		while((temp = br.readLine())!=null){
			String[] list = temp.split(" ");
			String line = list[0];
			for(int i = 1; i < 4; i++){
				line = line + " " + list[i].split(":")[1] + ":1";
			}
//			for(int i = 7; i < 19; i++){
//				line = line + " " + list[i].split(":")[1] + ":1";
//			}
//			for(int i = 4; i < 7; i++){
//				line = line + " " + (9736 + i) + ":" + list[i].split(":")[1];
//			}
			bw.write(line);
			bw.newLine();
		}
		bw.close();
		br.close();

	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
//		test_single();

//		Svm ss = new Svm();
//		ss.svm("sourcefile/testsvm/train.txt", "sourcefile/testsvm/hy.model", "sourcefile/testsvm/test.txt", "sourcefile/testsvm/hy_result.txt");
//		Count_01_Svm_Test ct = new Count_01_Svm_Test();
//		ct.count("sourcefile/testsvm/hy_result.txt");
		
		//tran_f();
//		Count_01_Svm_Test ct = new Count_01_Svm_Test();
//		ct.delete_0("sourcefile/testsvm/tran_exam_4.txt", "sourcefile/testsvm/0_tran_exam_4.txt");
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

		Count_01_Svm_Test tst = new Count_01_Svm_Test();
		tst.count(txtpath);
	
		Text_creative tt = new Text_creative();
		tt.cretxt(txtpath, 4500);
		
		Svm ss = new Svm(trainpath, modelpath, testpath, resultpath);
		
		tst.count(resultpath);

	}
}
