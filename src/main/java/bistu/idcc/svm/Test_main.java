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
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
/*		for(int i = 1; i < 19; i++){
			String exampath = "sourcefile/testsvm/testsingle/singl_" + String.valueOf(i) + ".txt";
			Count_01_Svm_Test tst = new Count_01_Svm_Test();
			tst.count(exampath);
//			tst.delete_0("sourcefile/testsvm/mx_2.txt", "sourcefile/testsvm/mx_3.txt");
//			tst.count("sourcefile/testsvm/mx_3.txt");

			
			Text_creative tt = new Text_creative();
			tt.cretxt(exampath, 5000);
			String trainpath = exampath.split(".txt")[0] + "_train.txt";
			String testpath = exampath.split(".txt")[0] + "_test.txt";
			String modelpath = exampath.split(".txt")[0] + ".model";
			String resultpath = exampath.split(".txt")[0] + "_result.txt";
			Svm ss = new Svm();
			ss.svm(trainpath, modelpath, testpath, resultpath);

		}
*/ 
		Test_delete_3 d3 = new Test_delete_3();
		d3.delete("sourcefile/svm/exam_4.txt", "sourcefile/testsvm/delete_3.txt");
		//merge("sourcefile/testsvm/f124.txt", "sourcefile/testsvm/testsingle/singl_5.txt", "sourcefile/testsvm/f1245.txt");
		String txtpath = "sourcefile/testsvm/delete_3.txt";
		String trainpath = txtpath.split(".txt")[0] + "_train.txt";
		String testpath = txtpath.split(".txt")[0] + "_test.txt";
		String modelpath = txtpath.split(".txt")[0] + ".model";
		String resultpath = txtpath.split(".txt")[0] + "_result.txt";

		//equal("sourcefile/testsvm/f123.txt", "sourcefile/testsvm/exam_1.txt");
		
		Count_01_Svm_Test tst = new Count_01_Svm_Test();
		tst.count(txtpath);
	
		Text_creative tt = new Text_creative();
		tt.cretxt(txtpath, 5000);
		
		Svm ss = new Svm();
		ss.svm(trainpath, modelpath, testpath, resultpath);
		
		tst.count(resultpath);


/*		for(int i = 1; i < 19; i++){
			String countpath = "sourcefile/testsvm/testsingle/singl_" + String.valueOf(i) + "_result.txt";
			Count_01_Svm_Test tst = new Count_01_Svm_Test();
			System.out.println("result " + i);
			tst.count(countpath);
		}
*/
	}

}
