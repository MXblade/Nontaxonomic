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
 * 给定文件(需已过滤掉不包含NVN格式的句子)路径,输出文件路径,分词后未带词性的专利文件夹。
 * 对每一句中的候选词组NVN进行处理，挑选出idf值最高的一组NVN作为该句的候选词组。
 * @candset(String path, String outpath, String content_folder_path)
 * 遍历文件，对每一句选出最佳nvn写入文件
 * @ArrayList<String> phraseset(String line)
 * 给定句子，遍历所有的nvn作为候选集，以arraylist形式返回
 * @String replace_nvn (String line, String phrase)
 * 给定一个字符串和算出的词对，对字符串除该词对外进行清除符号操作，并返回处理后的字符串
 * @author Joen
 *
 */
public class Candidate_Set {

	/*
	 * 对给定的path文件，进行nvn挑选，并写入outpath，content_folder_path用来构建专利hashmap
	 */
	public void candset(String path, String outpath, String content_folder_path)throws IOException{
		//清空创建outpath
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		//输入输出流
		BufferedReader br = new BufferedReader(new FileReader(path));
		BufferedWriter bw = new BufferedWriter(new FileWriter(outpath));
		
		//创建HashMap_Patents对象，构建专利hashmap
		HashMap_Patents hmp = new HashMap_Patents(content_folder_path);
		
		//创建IDF_NVN对象，计算idf值
		IDF_NVN idf = new IDF_NVN();
		
		//读取文件
		String temp = null;
		int num = 0;
		while((temp = br.readLine()) != null){
			System.out.println(num++);
			//对temp，调用phraseset，遍历所有的nvn。
			ArrayList<String> tempset = phraseset(temp);
			//用于判断idf的最大值
			double max = 0.0 ;
			String maxpharser = "";
			//对得到的nvn候选集进行遍历，得到idf最大的nvn
			for(String s : tempset){
				String[] pharse = s.split(" ");
				double phidf = idf.get_f_nvn(pharse[0],pharse[1],pharse[2],hmp.hm_p);
				if(phidf >= max){
					max = phidf;
					maxpharser = s;
				}
			}
			//若得到的nvn不为空，则调用replace_nvn，进行清除除了nvn之外所有的特殊符号，并写入outpath
			//若该句子中存在nvn，但是n1和n2是相同的，则得到的maxpharser为空。
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
	 * 给定一个字符串，对该字符串进行匹配NVN词组操作，把符合NVN格式的所有词组以“N V N Nloc Vloc Nloc”存入arraylist
	 */
	public ArrayList<String> phraseset(String line){
		//构建arraylist用于存储词组
		ArrayList<String> phset = new ArrayList<String>();
		//统计“【”的个数
		int booksym = 0;
		//统计“{”的个数
		int bracessym = 0;
		booksym = line.split("【").length - 1;
		bracessym = line.split("\\{").length - 1;
		//构建二维数组nterm和vterm用于存储得到的名词和动词，以及对应的位置。
		String[][] nterm = new String[booksym][2];
		String[][] vterm = new String[bracessym][2];
		//bi和ei表示一个词的开始位置和结束位置，用于获取特殊字符中的词。
		int bi = 0;
		int ei = 0;
		//获取line中名词以及名词的位置，名词存nterm[i][0],位置存nterm[i][1]
		for(int i = 0; i < booksym; i++){
			bi = line.indexOf("【",bi);
			ei = line.indexOf("】",ei);
			bi++;
			String term = line.substring(bi,ei);
			String loc = String.valueOf(bi);
			nterm[i][0] = term;
			nterm[i][1] = loc;
			ei++;
			
		}
		//获取line中动词以及动词的位置，动词存vterm[i][0],位置存vterm[i][1]
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
		
		//对两个数组进行遍历，若动词位置在两个名词之间，则将三者以及位置存入arraylist
		//格式n1 v n2 n1loc vloc n2loc
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
	 * 给定一个字符串和算出的词对，对字符串除该词对外进行清除符号操作，并返回处理后的字符串
	 */
	public String replace_nvn (String line, String phrase){
		//对给定的词组进行分割，会得到六个字符串
		String[] phraselist = phrase.split(" ");
		String n1 = phraselist[0];
		String v = phraselist[1];
		String n2 = phraselist[2];
		int n1_index = Integer.parseInt(phraselist[3]);
		int v_index = Integer.parseInt(phraselist[4]);
		int n2_index = Integer.parseInt(phraselist[5]);
		
		//将line按照n1，v，n2进行分割得到4个字符串，
		String s1 = line.substring(0, n1_index);
		String s2 = line.substring(n1_index + n1.length(), v_index);
		String s3 = line.substring(v_index + v.length(), n2_index);
		String s4 = line.substring(n2_index + n2.length(), line.length());
		
		//进行特殊符号清除
		s1 = s1.replaceAll("\\{|\\}|【|】", "");
		s2 = s2.replaceAll("\\{|\\}|【|】", "");
		s3 = s3.replaceAll("\\{|\\}|【|】", "");
		s4 = s4.replaceAll("\\{|\\}|【|】", "");
		
		//连接并返回
		String after_line = s1+"【" + n1 + "】" + s2 + "{" + v + "}" + s3 + "【" + n2 + "】" +s4;
		return after_line;
	}
	
/*	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Candidate_Set idf = new Candidate_Set();
		idf.candset("sourcefile/after_filter.txt", "sourcefile/candidate_set.txt");
		//idf.phraseset("【电池】温度{控制}模块通过【传感器】{采集}【电池】模块的【本体】温度并{输出}信号{控制}冷却【风扇】、水泵、电磁换向阀及【加热器】");
	}
*/
}
