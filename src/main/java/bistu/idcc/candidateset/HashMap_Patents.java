package bistu.idcc.candidateset;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import bistu.idcc.functions.ClearTxt_Path;

/**
 * 构建每个专利的HashSet，并将所有专利构建成一个HashMap,用于统计词在出现的文档数
 * @HashMap_Patents(String path)
 * 构造函数，给定文件夹路径。
 * @write(String path)
 * 将HashMap hm_p写入给定的文件path
 * @author Joen
 *
 */

public class HashMap_Patents {

	//构建HashMap<String, HashSet<String>>
	HashMap<String, HashSet<String>> hm_p = new HashMap<String, HashSet<String>>();
	
	/*
	 * 给定专利文件夹路径folderpath，进行hashmap的构建
	 */
	public HashMap_Patents(String folderpath) throws IOException {
		//遍历folderpath下的所有文件
		File folder = new File(folderpath);
		File[] files = folder.listFiles();
		for(File f: files){
			//构建输入流
			BufferedReader br = new BufferedReader(new FileReader(f));
			String filename = f.getName();
			//构建每片专利的hashset
			HashSet<String> fileset = new HashSet<String>();
			String temp = null;
			while((temp = br.readLine()) != null){
				if(!fileset.contains(temp)){
					fileset.add(temp);
				}
			}
			br.close();
			hm_p.put(filename, fileset);
		}
	}
	
	/*
	 * 将构建的HashMap写入文件
	 */
	public void write(String outpath) throws IOException{
		//清空创建新文件
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		//输出流
		BufferedWriter bw = new BufferedWriter(new FileWriter(outpath));
		//遍历hashmap hm_p
		Iterator ite = hm_p.entrySet().iterator();
		while(ite.hasNext()){
			Map.Entry entry = (Map.Entry) ite.next();
			Object key = entry.getKey();
			HashSet value = (HashSet) entry.getValue();
			bw.write(key.toString());
			//对hashmap中的hashset进行遍历
			Iterator setset = value.iterator();
			while(setset.hasNext()){
				String entry2 = (String) setset.next();
				bw.write("  " + entry2);
				//System.out.println("The words : " + entry2 );
			}
			bw.newLine();
		}
	}

/*	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		HashMap_Patents hmp = new HashMap_Patents("sourcefile/afparser");
		hmp.write("sourcefile/HashMap_Patents.txt");
	}
*/
}
