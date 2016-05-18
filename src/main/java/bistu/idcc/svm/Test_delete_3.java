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
 * 测试去除3特征之后
 * @author Joen
 *
 */
public class Test_delete_3 {

	public void delete(String path, String outpath) throws IOException{
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath), "utf-8"));
		String temp = null;
		while((temp = br.readLine())!=null){
			String[] list = temp.split(" ");
			String r = list[0] + " " + list[1] + " " + list[2];
			for(int i= 4; i< list.length;i++){
				r = r + " " + list[i];
			}
			bw.write(r);
			bw.newLine();
		}
		bw.close();
		br.close();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
