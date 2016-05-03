package bistu.idcc.features;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import bistu.idcc.functions.ClearTxt_Path;

/**
 * 组合之前提取出来的各种特征，获得训练数据
 */
public class Features {

	String pasepath = "sourcefile/features/parse.txt";
	String f1path = "sourcefile/features/single/f1_nvnbase.txt";
	String f2path = "sourcefile/features/single/f2_distance.txt";
	String f3path = "sourcefile/features/single/f3_window.txt";
	String f4path = "sourcefile/features/single/f4_mdp.txt";
	
	/*
	 * 只包含词对和句子，用于标注非分类关系
	 */
	public void nvn() throws IOException{
		
		String inpath = pasepath;
		String outpath = "sourcefile/features/data/biaozhu.txt";

		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inpath), "UTF-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath), "UTF-8"));
		String temp = null;
		while((temp = br.readLine()) != null){
			String[] templist= temp.split(" ");
			for(int i = 0; i < templist.length; i++){
				if(templist[i].equals("【") || templist[i].equals("{")){
					bw.write(templist[i+1] + " ");
				}
			}
			temp = temp.replace(" ", "");
			bw.write(temp);
			bw.newLine();
		}
		br.close();
		bw.close();
	}
	
	
	/*
	 * 只包含词组的词。格式： 实体E1 关系词Ref 实体E2
	 */
	public void exam_1() throws IOException{
		String inpath = f1path;
		String outpath = "sourcefile/features/data/exam_1.txt";
		
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inpath), "UTF-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath), "UTF-8"));
		String temp = null;
		while((temp = br.readLine()) != null){
			String[] templist = temp.split(" ");
			bw.write(templist[3] + " " + templist[4] + " " + templist[5]);
			bw.newLine();
		}
		br.close();
		bw.close();
		
	}
	
	/*
	 * exam_2包含词以及词间距离，格式：E1 Ref E2 dis(E1,E2) dis(E1,ref) dis(ref,E2) 
	 */
	public void exam_2() throws IOException{
		String inpath1 = f1path;
		String inpath2 = f2path;
		String outpath = "sourcefile/features/data/exam_2.txt";
		
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(inpath1), "UTF-8"));
		BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(inpath2), "UTF-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath), "UTF-8"));
		String temp1 = null;
		String temp2 = null;
		while((temp1 = br1.readLine()) != null && ((temp2 = br2.readLine()) != null) )
		{
			String[] temp1list = temp1.split(" ");
			bw.write(temp1list[3] + " " + temp1list[4] + " " + temp1list[5] + " ");
			bw.write(temp2);
			bw.newLine();
		}
		bw.close();
		br1.close();
		br2.close();
	}

	/*
	 * exam_3包含词，词间距离，对于词组的窗口词 格式：E1 Ref E2 dis(e1,e2) dis(e1,ref) dis(ref,e2) before_e1 after_e1 before_ref after_ref before_e2 after_e2
	 */
	public void exam_3() throws IOException{
		String inpath1 = "sourcefile/features/data/exam_2.txt";
		String inpath2 = f3path;
		String outpath = "sourcefile/features/data/exam_3.txt";
		
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(inpath1), "UTF-8"));
		BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(inpath2), "UTF-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath), "UTF-8"));
		String temp1 = null;
		String temp2 = null;
		while(((temp1 = br1.readLine()) != null) && ((temp2 = br2.readLine()) != null)){
			bw.write(temp1);
			String[] temp2list = temp2.split(" ");
			for(int i = 0; i < 6; i++){
				bw.write(" " + temp2list[i]);
			}
			bw.newLine();
		}
		br1.close();
		br2.close();
		bw.close();
		
	}

	/*
	 * exam_4词，词距，窗口词，mdp
	 */
	public void exam_4() throws IOException{
		String inpath1 = "sourcefile/features/data/exam_3.txt";
		String inpath2 = f4path;
		String outpath = "sourcefile/features/data/exam_4.txt";
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(inpath1), "UTF-8"));
		BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(inpath2), "UTF-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath), "UTF-8"));
		String temp1 = null;
		String temp2 = null;
		while(((temp1 = br1.readLine()) != null) && ((temp2 = br2.readLine()) != null)){
			bw.write(temp1 + " " + temp2);
			bw.newLine();
		}
		bw.close();
		br1.close();
		br2.close();
	}
	
	/*
	 * exam_5 词，词距，窗口词， 词距中的词
	 */
	public void exam_5() throws IOException{
		String inpath1 = "sourcefile/features/data/exam_4.txt";
		String inpath2 = f3path;
		String outpath = "sourcefile/features/data/exam_5.txt";
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(inpath1), "UTF-8"));
		BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(inpath2), "UTF-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath), "UTF-8"));
		String temp1 = null;
		String temp2 = null;
		while(((temp1 = br1.readLine()) != null) && ((temp2 = br2.readLine()) != null)){
			String[] temp2list = temp2.split(" ");
			
			bw.write(temp1);
			for(int i = 6; i < temp2list.length; i++){
				bw.write(" " + temp2list[i]);
			}
			bw.newLine();
		}
		bw.close();
		br1.close();
		br2.close();
	}
	
	/*
	 * exam_6 词，词距，窗口词，mdp，词距中的词
	 */
	public void exam_6() throws IOException{
		String inpath1 = "sourcefile/features/data/exam_2.txt";
		String inpath2 = f3path;
		String outpath = "sourcefile/features/data/exam_6.txt";
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(inpath1), "UTF-8"));
		BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(inpath2), "UTF-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath), "UTF-8"));
		String temp1 = null;
		String temp2 = null;
		while(((temp1 = br1.readLine()) != null) && ((temp2 = br2.readLine()) != null)){
			bw.write(temp1 + " " + temp2);
			bw.newLine();
		}
		bw.close();
		br1.close();
		br2.close();

	}

	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Features fea = new Features();
		fea.nvn();
		fea.exam_1();
		fea.exam_2();
		fea.exam_3();
		fea.exam_4();
		fea.exam_5();
		fea.exam_6();
		
	}

}
