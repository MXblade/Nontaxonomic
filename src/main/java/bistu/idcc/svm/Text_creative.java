package bistu.idcc.svm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import bistu.idcc.functions.ClearTxt_Path;

/**
 * 将数据分为训练语料和测试语料
 * @author Joen
 *
 */
public class Text_creative {

	
	public void cretxt(String inpath, String ofpath, int num) throws IOException{
		File f = new File(inpath);
		String outpath_train = ofpath + "train_" + f.getName();
		String outpath_test = ofpath + "test_" + f.getName();
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath_train);
		clear.cleartxt(outpath_test);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inpath), "UTF-8"));
		BufferedWriter bw_train = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath_train), "UTF-8"));
		BufferedWriter bw_test = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath_test), "UTF-8"));
		String temp = null;
		int count = 0;
		while((temp = br.readLine()) != null){
			count++;
			if(count <= num){
				bw_train.write(temp);
				bw_train.newLine();
			}
			else{
				bw_test.write(temp);
				bw_test.newLine();
			}
		}
		br.close();
		bw_test.close();
		bw_train.close();
		
		
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Text_creative ct = new Text_creative();
		ct.cretxt("sourcefile/svm/exam_1.txt", "sourcefile/svm/", 5000);
		ct.cretxt("sourcefile/svm/exam_2.txt", "sourcefile/svm/", 5000);
		ct.cretxt("sourcefile/svm/exam_3.txt", "sourcefile/svm/", 5000);
		ct.cretxt("sourcefile/svm/exam_4.txt", "sourcefile/svm/", 5000);
		ct.cretxt("sourcefile/svm/exam_5.txt", "sourcefile/svm/", 5000);
		ct.cretxt("sourcefile/svm/exam_6.txt", "sourcefile/svm/", 5000);
	}

}
