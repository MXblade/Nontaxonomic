package bistu.idcc.candidateset;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import bistu.idcc.functions.ClearTxt_Path;
/**
 * �����������ʼ�û�ж��ʵľ��ӡ�
 * @filter_nonnvn(String path, String outpath)
 * path�����ķִʺ��ļ�·��
 * outpath ���˺������ļ�·����
 * @author Joen
 *
 */

public class Filter_NonNVN {
	public void filter_nonnvn(String path, String outpath)throws IOException{
		//����������մ�������ļ��������
		BufferedReader br = new BufferedReader(new FileReader(path));
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		BufferedWriter bw = new BufferedWriter(new FileWriter(outpath));
		
		//�ԡ��������룬ȥ����������βString�ģ��������������ӣ��ж��������������ڣ���������þ���
		String temp = null;
		while((temp = br.readLine()) != null){
			String[] templist = temp.split("��");
			templist[0] = templist[0].replace("{", "");
			templist[0] = templist[0].replace("}", "" );
			templist[templist.length-1] = templist[templist.length-1].replace("{", "");
			templist[templist.length-1] = templist[templist.length-1].replace("}", "");
			String utemplist = "";
			utemplist = templist[0];
			for(int i = 1; i < templist.length; i++){
				utemplist = utemplist +"��" + templist[i];
			}
			if(utemplist.contains("{"))
			{
				bw.write(utemplist);
				bw.newLine();
			}
		}
		bw.close();
		br.close();
	}
}
