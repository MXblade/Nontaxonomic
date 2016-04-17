package bistu.idcc.candidateset;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import bistu.idcc.functions.ClearTxt_Path;

/**
 * �����ļ�(���ѹ��˵�������NVN��ʽ�ľ���)·��,����ļ�·��,�ִʺ�δ�����Ե�ר���ļ��С�
 * ��ÿһ���еĺ�ѡ����NVN���д�����ѡ��idfֵ��ߵ�һ��NVN��Ϊ�þ�ĺ�ѡ���顣
 * @candset(String path, String outpath, String content_folder_path)
 * �����ļ�����ÿһ��ѡ�����nvnд���ļ�
 * @ArrayList<String> phraseset(String line)
 * �������ӣ��������е�nvn��Ϊ��ѡ������arraylist��ʽ����
 * @String replace_nvn (String line, String phrase)
 * ����һ���ַ���������Ĵʶԣ����ַ������ôʶ������������Ų����������ش������ַ���
 * @author Joen
 *
 */
public class Candidate_Set {

	/*
	 * �Ը�����path�ļ�������nvn��ѡ����д��outpath��content_folder_path��������ר��hashmap
	 */
	public void candset(String path, String outpath, String content_folder_path)throws IOException{
		//��մ���outpath
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		//���������
		BufferedReader br = new BufferedReader(new FileReader(path));
		BufferedWriter bw = new BufferedWriter(new FileWriter(outpath));
		
		//����HashMap_Patents���󣬹���ר��hashmap
		HashMap_Patents hmp = new HashMap_Patents(content_folder_path);
		
		//����IDF_NVN���󣬼���idfֵ
		IDF_NVN idf = new IDF_NVN();
		
		//��ȡ�ļ�
		String temp = null;
		int num = 0;
		while((temp = br.readLine()) != null){
			System.out.println(num++);
			//��temp������phraseset���������е�nvn��
			ArrayList<String> tempset = phraseset(temp);
			//�����ж�idf�����ֵ
			double max = 0.0 ;
			String maxpharser = "";
			//�Եõ���nvn��ѡ�����б������õ�idf����nvn
			for(String s : tempset){
				String[] pharse = s.split(" ");
				double phidf = idf.get_f_nvn(pharse[0],pharse[1],pharse[2],hmp.hm_p);
				if(phidf >= max){
					max = phidf;
					maxpharser = s;
				}
			}
			//���õ���nvn��Ϊ�գ������replace_nvn�������������nvn֮�����е�������ţ���д��outpath
			//���þ����д���nvn������n1��n2����ͬ�ģ���õ���maxpharserΪ�ա�
			if(maxpharser != "")
			{
				//System.out.println(maxpharser);
				String result = replace_nvn(temp, maxpharser);
				bw.write(result);
				bw.newLine();
			
			}
		}
		bw.close();
		
	}
	
	/*
	 * ����һ���ַ������Ը��ַ�������ƥ��NVN����������ѷ���NVN��ʽ�����д����ԡ�N V N Nloc Vloc Nloc������arraylist
	 */
	public ArrayList<String> phraseset(String line){
		//����arraylist���ڴ洢����
		ArrayList<String> phset = new ArrayList<String>();
		//ͳ�ơ������ĸ���
		int booksym = 0;
		//ͳ�ơ�{���ĸ���
		int bracessym = 0;
		booksym = line.split("��").length - 1;
		bracessym = line.split("\\{").length - 1;
		//������ά����nterm��vterm���ڴ洢�õ������ʺͶ��ʣ��Լ���Ӧ��λ�á�
		String[][] nterm = new String[booksym][2];
		String[][] vterm = new String[bracessym][2];
		//bi��ei��ʾһ���ʵĿ�ʼλ�úͽ���λ�ã����ڻ�ȡ�����ַ��еĴʡ�
		int bi = 0;
		int ei = 0;
		//��ȡline�������Լ����ʵ�λ�ã����ʴ�nterm[i][0],λ�ô�nterm[i][1]
		for(int i = 0; i < booksym; i++){
			bi = line.indexOf("��",bi);
			ei = line.indexOf("��",ei);
			bi++;
			String term = line.substring(bi,ei);
			String loc = String.valueOf(bi);
			nterm[i][0] = term;
			nterm[i][1] = loc;
			ei++;
			
		}
		//��ȡline�ж����Լ����ʵ�λ�ã����ʴ�vterm[i][0],λ�ô�vterm[i][1]
		bi = ei = 0;
		for(int i = 0; i< bracessym; i++){
			bi = line.indexOf("{",bi);
			ei = line.indexOf("}",ei);
			bi++;
			String term = line.substring(bi,ei);
			String loc = String.valueOf(bi);
			vterm[i][0] = term;
			vterm[i][1] = loc;
			ei++;
			
		}
		
		//������������б�����������λ������������֮�䣬�������Լ�λ�ô���arraylist
		//��ʽn1 v n2 n1loc vloc n2loc
		for(int i = 0; i < nterm.length -1; i++){
			for(int j = i + 1; j < nterm.length; j++){
				for(int m = 0; m < vterm.length; m++){
					String n1 = nterm[i][0];
					String n2 = nterm[j][0];
					String v = vterm[m][0];
					int n1loc = Integer.parseInt(nterm[i][1]);
					int n2loc = Integer.parseInt(nterm[j][1]);
					int vloc = Integer.parseInt(vterm[m][1]);
					if((vloc > n1loc) && (vloc < n2loc) && (!n1.equals(n2))){
						phset.add(n1 + " " + v + " " + n2 + " " + n1loc + " " + vloc + " " + n2loc);
					}
				}
			}
		}
		
//		for(String s: phset){
//		System.out.println(s);
//		}
		return phset;
		
	}
	
	/*
	 * ����һ���ַ���������Ĵʶԣ����ַ������ôʶ������������Ų����������ش������ַ���
	 */
	public String replace_nvn (String line, String phrase){
		//�Ը����Ĵ�����зָ��õ������ַ���
		String[] phraselist = phrase.split(" ");
		String n1 = phraselist[0];
		String v = phraselist[1];
		String n2 = phraselist[2];
		int n1_index = Integer.parseInt(phraselist[3]);
		int v_index = Integer.parseInt(phraselist[4]);
		int n2_index = Integer.parseInt(phraselist[5]);
		
		//��line����n1��v��n2���зָ�õ�4���ַ�����
		String s1 = line.substring(0, n1_index);
		String s2 = line.substring(n1_index + n1.length(), v_index);
		String s3 = line.substring(v_index + v.length(), n2_index);
		String s4 = line.substring(n2_index + n2.length(), line.length());
		
		//��������������
		s1 = s1.replaceAll("\\{|\\}|��|��", "");
		s2 = s2.replaceAll("\\{|\\}|��|��", "");
		s3 = s3.replaceAll("\\{|\\}|��|��", "");
		s4 = s4.replaceAll("\\{|\\}|��|��", "");
		
		//���Ӳ�����
		String after_line = s1+"��" + n1 + "��" + s2 + "{" + v + "}" + s3 + "��" + n2 + "��" +s4;
		return after_line;
	}
	
/*	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Candidate_Set idf = new Candidate_Set();
		idf.candset("sourcefile/after_filter.txt", "sourcefile/candidate_set.txt");
		//idf.phraseset("����ء��¶�{����}ģ��ͨ������������{�ɼ�}����ء�ģ��ġ����塿�¶Ȳ�{���}�ź�{����}��ȴ�����ȡ���ˮ�á���Ż��򷧼�����������");
	}
*/
}
