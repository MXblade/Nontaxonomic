package bistu.idcc.functions;

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
 * @author Joen
 *
 */

public class Count_01 {

	/*
	 * 统计语料中标记为0和1的数目或语料为0.0和1.0的数目
	 */
	public Count_01(String path)throws IOException{
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
}
