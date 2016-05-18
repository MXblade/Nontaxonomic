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
/**
 * 用于统计语料中标记为0和1的数目 或 结果为0.0和1.0的数目
 * count 给定输入文件路径
 * 用于删除一般标记为0的语料
 * delete_0 给定输入文件路径和输出文件路径
 * @author Joen
 *
 */

public class Count_01_Svm_Test {

	/*
	 * 统计语料中标记为0和1的数目或语料为0.0和1.0的数目
	 */
	public void count(String path)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
		String temp = null;
		int count_1 = 0;
		int count_0 = 0;
		while((temp = br.readLine()) !=null){
			if(temp.split(" ")[0].equals("0") || temp.equals("0.0")){
				count_0++;
			}
			else if(temp.split(" ")[0].equals("1") || temp.equals("1.0")){
				count_1++;
			}
		}
		br.close();
		System.out.println("sum_0: " + count_0 + "; sum_1: " + count_1);
	}
	/*
	 * 删除一半标为0的语料
	 */
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
}
