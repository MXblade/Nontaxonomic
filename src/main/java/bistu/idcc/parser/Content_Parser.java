package bistu.idcc.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import bistu.idcc.functions.ClearTxt_Path;

/**
 * ��ר���ļ����зִʲ��洢���ļ�
 * @parser(String path, String outpath)
 * ����ר���ļ���·��path������ר���ļ����зִʣ����ִʽ������һ���ļ��ڣ��ļ�·��outpath
 * @parser_files(String path, String outpath)
 * ����ר���ļ���·��path������ר���ļ����зִʣ����ִʽ��ȥ���԰��ļ�����һ���ļ��У��ļ���·��outpath
 * @author Joen
 *
 */
public class Content_Parser {

	/*
	 * ����ר���ļ���·��path������ר���ļ����зִʣ����ִʽ������һ���ļ��ڣ��ļ�·��outpath
	 */
	public void parser(String path, String outpath)throws IOException{
		//����path��ר���ļ�
		File folder = new File(path);
		File[] files = folder.listFiles();
		//��ղ���������ļ�·��
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		//�����
		BufferedWriter bw = new BufferedWriter(new FileWriter(outpath));
		
		for(File f:files){
			//������
			BufferedReader br = new BufferedReader(new FileReader(f));
			//��ȡ����������
			String temp = null;
			br.readLine();
			br.readLine();
			temp = br.readLine();
			br.close();
			//���������Ϊ��
			if(temp!=null)
			{
				//���˵����ÿո�
				temp = temp.replace(" ", "");
/*				if(temp.contains("��ѹ������Ƶ�·")){
					System.out.println(f.getName());
				}
*/				
				//�Ͼ�
				String[] templist = temp.split("��|��|,|��|;");
				//���зִʣ���д�뵽����ļ���
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
	 * ����ר���ļ���·��path������ר���ļ����зִʣ����ִʽ��ȥ���԰��ļ�����һ���ļ��У��ļ���·��outpath
	 */
	public void parser_files(String path, String outpath) throws IOException{
		//��մ�������ļ���
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.clearpath(outpath);
		//����ר���ļ�
		File folder = new File(path);
		File[] files = folder.listFiles();
		for(File f:files){
			//������
			BufferedReader br = new BufferedReader(new FileReader(f));
			//��������ļ�·��������մ����ļ�
			String fileoutpt = outpath + "/" + f.getName();
			clear.cleartxt(fileoutpt);
			//�����
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileoutpt));
			//��ȡ����������
			String temp = null;
			br.readLine();
			br.readLine();
			temp = br.readLine();
			br.close();
			//�жϵ����������Ƿ�Ϊ��
			if(temp!=null){
				//ȥ�����ÿո�
				temp = temp.replace(" ", "");
				//�Ͼ�
				String[] templist = temp.split("��|��|,|��|;");
				//�ִʣ���ȥ���Ժ�д��
				for(String s:templist){
	        		List<Term> segparser = HanLP.segment(s);
	        		for(Term k:segparser){
	        			//�ִʺ���Դ����ԣ����ڲ���Ҫ���ԣ��򽫴���ɾ��
	        			String st = k.toString();
	        			String[] stsplit = st.split("/");
	        			if(stsplit[0] != "��"){
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
