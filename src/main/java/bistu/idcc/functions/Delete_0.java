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
 * 删除语料中一半标记为0的语料
 * inpath,outpath 输入文件路径，输出文件路径
 * @author Joen
 *
 */
public class Delete_0 {
	/*
	 * 删除一半标为0的语料
	 */
	public Delete_0(String inpath, String outpath) throws IOException{
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
