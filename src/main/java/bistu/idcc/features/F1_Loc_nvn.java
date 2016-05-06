package bistu.idcc.features;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import bistu.idcc.functions.ClearTxt_Path;

public class F1_Loc_nvn {
	
	String parsepath = "sourcefile/features/parse.txt";
	String outpath = "sourcefile/features/single/f1_nvnbase.txt";
	Map<String, Integer> bagwords = new HashMap<String, Integer>();

	public F1_Loc_nvn() throws IOException{
		Bagwords bg = new Bagwords(parsepath);
		bagwords = bg.bagwords;
		HashSet<String> ss = new HashSet<String>();
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(parsepath), "UTF-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath), "UTF-8"));
		String temp = null;
		while((temp = br.readLine())!= null)
		{
			String nvn = gnvn(temp);
			ss.add(nvn);
			bw.write(nvn);
			bw.newLine();
		}
		bw.close();
	}
	
	public String gnvn(String line){
		String lin1 = line.split("\\{")[0];
		String lin2 = line.split("\\{")[1];
		String[] lin1list = lin1.split(" ");
		String[] lin2list = lin2.split(" ");
		String nvn = "";
		String loc = "";
		for(int i = 0; i < lin1list.length; i++){
			if(lin1list[i].equals("【")){
				nvn = nvn + lin1list[i+1];
//				System.out.println(lin1list[i+1]);
//				if(bagwords.containsKey(lin1list[i+1])){
//					System.out.println(true);
//				}
//				else
//					System.out.println(false);
				loc = bagwords.get(lin1list[i+1]).toString();
			}
		}
		nvn = nvn + " " + lin2list[1];
		loc = loc + " " + bagwords.get(lin2list[1]).toString();
		for(int i = 0; i < lin2list.length; i++){
			if(lin2list[i].equals("【")){
				nvn = nvn + " " + lin2list[i+1];
				loc = loc + " " + bagwords.get(lin2list[i+1]).toString();
			}
		}
		nvn = nvn + " " + loc;
		return nvn;
	}
	
/*	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		F1_Loc_nvn lnvn = new F1_Loc_nvn();
	}
*/
}
