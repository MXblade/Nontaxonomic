package bistu.idcc.features;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import bistu.idcc.functions.ClearTxt_Path;

public class F2_Distance_nvn {
	String parsepath = "sourcefile/features/parse.txt";
	String outpath = "sourcefile/features/single/f2_distance.txt";

	public F2_Distance_nvn() throws IOException{
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(parsepath), "UTF-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath), "UTF-8"));
		String temp = null;
		int[] length = new int[2];
		while((temp = br.readLine()) != null){
			String temp1 = temp.split("\\{")[0];
			String[] p1list = temp1.split(" ");
			for(int i = 0; i < p1list.length; i++){
				if(p1list[i].equals("】")){
					length[0] = p1list.length - i - 1;
				}
			}
			
			String temp2 = temp.split("\\}")[1];
			String[] p2list = temp2.split(" ");
			for(int i = 0; i < p2list.length; i++){
				if(p2list[i].equals("【")){
					length[1] = i;
				}
			}
			bw.write((length[0]+length[1] + 1) +" " + length[0] + " " + length[1] );
			bw.newLine();
		}
		bw.close();

	}
/*	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		F2_Distance_nvn dis = new F2_Distance_nvn();
	}
*/
}
