package bistu.idcc.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import bistu.idcc.functions.ClearTxt_Path;

/**
 * 对专利文件进行分词并存储在文件
 * @parser(String path, String outpath)
 * 给定专利文件夹路径path，遍历专利文件进行分词，将分词结果存入一个文件内，文件路径outpath
 * @parser_files(String path, String outpath)
 * 给定专利文件夹路径path，遍历专利文件进行分词，将分词结果去词性按文件存入一个文件夹，文件夹路径outpath
 * @author Joen
 *
 */
public class Content_Parser {

	/*
	 * 给定专利文件夹路径path，遍历专利文件进行分词，将分词结果存入一个文件内，文件路径outpath
	 */
	public void parser(String path, String outpath)throws IOException{
		//遍历path下专利文件
		File folder = new File(path);
		File[] files = folder.listFiles();
		//清空并创建输出文件路径
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		//输出流
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath), "GBK"));
		
		for(File f:files){
			//输入流
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "GBK"));
			//读取第三行内容
			String temp = null;
			br.readLine();
			br.readLine();
			temp = br.readLine();
			br.close();
			//避免第三行为空
			if(temp!=null)
			{
				//过滤掉无用空格
				temp = temp.replace(" ", "");
/*				if(temp.contains("电压输出控制电路")){
					System.out.println(f.getName());
				}
*/				
				//断句
				String[] templist = temp.split("，|。|,|；|;");
				//进行分词，并写入到输出文件。
				for(String s:templist){
					if(!s.equals("")){
						List<Term> slist = HanLP.segment(s);
						for(Term sl:slist){
							bw.write(sl.toString() + "   ");
						}
						bw.newLine();
					
					}
				}
			}
		}
		bw.close();
	}
	
	/*
	 * 给定专利文件夹路径path，遍历专利文件进行分词，将分词结果去词性按文件存入一个文件夹，文件夹路径outpath
	 */
	public void parser_files(String path, String outpath) throws IOException{
		//清空创建输出文件夹
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.clearpath(outpath);
		//遍历专利文件
		File folder = new File(path);
		File[] files = folder.listFiles();
		for(File f:files){
			//输入流
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "GBK"));
			//构建输出文件路径，并清空创建文件
			String fileoutpt = outpath + "/" + f.getName();
			clear.cleartxt(fileoutpt);
			//输出流
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileoutpt), "GBK"));
			//读取第三行内容
			String temp = null;
			br.readLine();
			br.readLine();
			temp = br.readLine();
			br.close();
			//判断第三行内容是否为空
			if(temp!=null){
				//去除无用空格
				temp = temp.replace(" ", "");
				//断句
				String[] templist = temp.split("，|。|,|；|;");
				//分词，并去词性后写入
				for(String s:templist){
	        		List<Term> segparser = HanLP.segment(s);
	        		for(Term k:segparser){
	        			//分词后的自带词性，由于不需要词性，则将词性删除
	        			String st = k.toString();
	        			String[] stsplit = st.split("/");
	        			if(stsplit[0] != "、"){
	            			bw.write(stsplit[0]);
	            			bw.newLine();
	         			}
	        			//System.out.println(stsplit[0]);
	        		}

				}
			}
			bw.close();
		}
	}

/*	public static void main(String[] args)throws IOException{
		Content_Parser cp = new Content_Parser();
		cp.parser("sourcefile/patent", "sourcefile/after_parser.txt");
		System.out.println(new Date());
	}
*/	
}
