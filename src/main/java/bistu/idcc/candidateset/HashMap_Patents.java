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
 * ����ÿ��ר����HashSet����������ר��������һ��HashMap,����ͳ�ƴ��ڳ��ֵ��ĵ���
 * @HashMap_Patents(String path)
 * ���캯���������ļ���·����
 * @write(String path)
 * ��HashMap hm_pд��������ļ�path
 * @author Joen
 *
 */

public class HashMap_Patents {

	//����HashMap<String, HashSet<String>>
	HashMap<String, HashSet<String>> hm_p = new HashMap<String, HashSet<String>>();
	
	/*
	 * ����ר���ļ���·��folderpath������hashmap�Ĺ���
	 */
	public HashMap_Patents(String folderpath) throws IOException {
		//����folderpath�µ������ļ�
		File folder = new File(folderpath);
		File[] files = folder.listFiles();
		for(File f: files){
			//����������
			BufferedReader br = new BufferedReader(new FileReader(f));
			String filename = f.getName();
			//����ÿƬר����hashset
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
	 * ��������HashMapд���ļ�
	 */
	public void write(String outpath) throws IOException{
		//��մ������ļ�
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		//�����
		BufferedWriter bw = new BufferedWriter(new FileWriter(outpath));
		//����hashmap hm_p
		Iterator ite = hm_p.entrySet().iterator();
		while(ite.hasNext()){
			Map.Entry entry = (Map.Entry) ite.next();
			Object key = entry.getKey();
			HashSet value = (HashSet) entry.getValue();
			bw.write(key.toString());
			//��hashmap�е�hashset���б���
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
