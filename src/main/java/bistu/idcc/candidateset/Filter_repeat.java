package bistu.idcc.candidateset;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

import bistu.idcc.functions.ClearTxt_Path;

public class Filter_repeat {

	public void flrepeat(String inpath, String outpath)throws IOException{
		HashSet<String> tst = new HashSet<String>(); 
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inpath), "UTF-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath), "UTF-8"));
		
		String temp = null;
		while((temp = br.readLine()) != null){
			String nvn = gnvn(temp);
			if(!tst.contains(nvn)){
				tst.add(nvn);
				bw.write(temp);
				bw.newLine();
			}
		}
		bw.close();
		br.close();
		
	}
	
	public String gnvn(String line) {
		String nvn = "";
		String lin1 = line.split("\\{")[0];
		String lin2 = line.split("\\{")[1];
		String n1 = lin1.substring(lin1.indexOf("【") + 1, lin1.indexOf("】"));
		String v = lin2.substring(0, lin2.indexOf("}"));
		String n2 = lin2.substring(lin2.indexOf("【") + 1 , lin2.indexOf("】"));
		nvn = n1 + " " + v + " " + n2;
		//System.out.println(nvn);
		return nvn;
	}
	
/*	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Filter_repeat fr = new Filter_repeat();
		fr.flrepeat("sourcefile/candidate/candidate_set.txt", "sourcefile/candidate/candidate_filter.txt");
	}
*/
}
