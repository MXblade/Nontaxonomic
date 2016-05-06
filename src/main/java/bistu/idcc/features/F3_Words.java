package bistu.idcc.features;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import bistu.idcc.functions.ClearTxt_Path;

public class F3_Words {

	String parsepath = "sourcefile/features/parse.txt";
	String outpath = "sourcefile/features/single/f3_window.txt";
	int window = 2;
	Map<String, Integer> bagwords = new HashMap<String, Integer>();

	
	public F3_Words() throws IOException{
		
		Bagwords bg = new Bagwords(parsepath);
		bagwords = bg.bagwords;
		
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(parsepath), "UTF-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath), "UTF-8"));
		
		String temp = null;
		while((temp = br.readLine())!= null)
		{
			Map<Integer, ArrayList<Integer>> words = nl_nvn(temp);
			for(int i = 1; i < 7; i++){
				ArrayList<Integer> wds = words.get(i);
				for(int j : wds){
					bw.write(j + " ");
				}
			}
			ArrayList<Integer> snv = words.get(7);
			//若在该文件中需要显示距离，则取消两行注释
			//bw.write(snv.size() + " ");
			for(int i:snv){
				bw.write(i + " ");
			}
			ArrayList<Integer> svn = words.get(8);
			//bw.write(svn.size() + " ");
			for(int i:svn){
				bw.write(i + " ");
			}
			
			bw.newLine();

		}
		bw.close();
		br.close();
	}

	
	public Map<Integer, ArrayList<Integer>> nl_nvn(String line){
		int window = 2;
		ArrayList<Integer> s1 = new ArrayList<Integer>();
		ArrayList<Integer> s2 = new ArrayList<Integer>();
		ArrayList<Integer> s3 = new ArrayList<Integer>();
		ArrayList<Integer> s4 = new ArrayList<Integer>();
		ArrayList<Integer> s5 = new ArrayList<Integer>();
		ArrayList<Integer> s6 = new ArrayList<Integer>();
		ArrayList<Integer> snv = new ArrayList<Integer>();
		ArrayList<Integer> svn = new ArrayList<Integer>();
		Map<Integer, ArrayList<Integer>> locn = new HashMap<Integer, ArrayList<Integer>>();
		
		String l1 = line.split("\\{")[0];
		String l2 = line.split("\\}")[1];
		String l1p1 = l1.split("【")[0];
		String l1p2 = l1.split("】")[1];
		String l2p1 = l2.split("【")[0];
		String l2p2 = l2.split("【")[0];
		if(l1p1 != ""){
			ArrayList<Integer> l11loc = n_loc(l1p1);
			if(l11loc.size() < window){
				for(int i = 0; i < window - l11loc.size(); i++)
				{
					s1.add(0);
				}
				for(int i =0; i< l11loc.size(); i++){
					s1.add(l11loc.get(i));
				}
			}
			else{
				for(int i = window; i > 0 ;i --)
				{
					s1.add(l11loc.get(l11loc.size()-i));
					
				}
			}
		}
		if(l1p2!= ""){
			ArrayList<Integer> l12loc = n_loc(l1p2);
			if(l12loc.size() < window){
				s2 = l12loc;
				for(int i = 0; i < window - l12loc.size(); i++)
				{
					s2.add(0);
					s3.add(0);
				}
				for(int i =0; i< l12loc.size(); i++){
					s3.add(l12loc.get(i));
				}
			}
			else{
				
				for(int i = 0; i < window ;i ++)
				{
					s2.add(l12loc.get(i));
					s3.add(l12loc.get(l12loc.size()-(window - i)));
				}
			}
			snv = l12loc;
		}
		if(l2p1 != ""){
			ArrayList<Integer> l21loc = n_loc(l2p1);
			if(l21loc.size() < window){
				s4 = l21loc;
				for(int i = 0; i < window - l21loc.size(); i++)
				{
					s4.add(0);
					s5.add(0);
				}
				for(int i =0; i< l21loc.size(); i++){
					s5.add(l21loc.get(i));
				}
			}
			else{
				
				for(int i = 0; i < window ;i ++)
				{
					s4.add(l21loc.get(i));
					s5.add(l21loc.get(l21loc.size()-(window - i)));
				}
			}
			svn = l21loc;
		}
		if(l2p2 != ""){
			ArrayList<Integer> l22loc = n_loc(l2p2);
			if(l22loc.size() < window){
				for(int i =0; i< l22loc.size(); i++){
					s6.add(l22loc.get(i));
				}
				for(int i = 0; i < window - l22loc.size(); i++)
				{
					s6.add(0);
				}
				
			}
			else{
				
				for(int i = 0; i < window ;i ++)
				{
					s6.add(l22loc.get(i));
				}
			}
		}

		locn.put(1, s1);
		locn.put(2, s2);
		locn.put(3, s3);
		locn.put(4, s4);
		locn.put(5, s5);
		locn.put(6, s6);
		locn.put(7, snv);
		locn.put(8, svn);
		return locn;
		
	} 

	/*
	 * 根据句子返回每个词的位置int[]
	 */
	public ArrayList<Integer> n_loc(String line){
		String[] terms = line.split(" ");
		ArrayList<Integer> termloc = new ArrayList<Integer>();
		for(int i = 0; i < terms.length; i++){
			termloc.add(bagwords.get(terms[i]));
		}
		return termloc;
	}

	
/*	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		F3_Words f3 = new F3_Words();
	}
*/
}
