package bistu.idcc.features;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import bistu.idcc.functions.ClearTxt_Path;

public class Parse {

	public void ps(String path, String outpath) throws IOException{
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath),"UTF-8"));
		String temp = null;
		while((temp = br.readLine()) !=null){
			List<Term> templist = HanLP.segment(temp);
			System.out.println(templist);
			for(Term i:templist){
				String[] ilist = i.toString().split("/");
				bw.write(ilist[0] + " ");
			}
			bw.newLine();
			
		}
		br.close();
		bw.close();
		
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Parse p = new Parse();
		p.ps("sourcefile/candidate/candidate_set.txt", "sourcefile/features/parse.txt");
	}

}
