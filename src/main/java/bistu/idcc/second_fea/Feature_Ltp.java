package bistu.idcc.second_fea;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bistu.idcc.functions.ClearTxt_Path;
/**
 * 计算语料的句法特征，包括n_v的关系特征，v的关系特征，v_n的关系特征
 * 需给定ltp依存句法分析分好的文件夹路径，和输出结果路径
 */
public class Feature_Ltp {

	
	public Feature_Ltp(String ltppath, String outpath) throws IOException{
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath), "UTF-8"));
		File ltp = new File(ltppath);
		File[] ltplist = ltp.listFiles();
		for(File f:ltplist){
			String[][] wcp = readxml(f);
			int[] mdp = getmdp(wcp);
			String f_dp = "";
			for(int i = 0; i < mdp.length; i++){
				f_dp = f_dp + String.valueOf(3695+i) + ":" + String.valueOf(mdp[i]) + " ";
			}
			bw.write(f_dp);
			//bw.write(String.valueOf(mdp));
			bw.newLine();
		}
		bw.close();
	}
	
	public String[][] readxml(File f){
		String[][] wcp = new String[3][3];
		int pl = 0;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
	       try  
	       {  
	           DocumentBuilder db = dbf.newDocumentBuilder();  
	           Document doc = db.parse(f);  
	           NodeList nodes = doc.getElementsByTagName("word");
	           int count = 0;
	           //System.out.println("共有" + nodes.getLength() + "个dog节点");  
	           for (int i = 0; i < nodes.getLength(); i++)  
	           {  
	               Node word = nodes.item(i);  
	               Element elem = (Element) word;
	               //中间ltp进行句法分析时把设的标签没有分离出来。
	               if((elem.getAttribute("cont").contains("【") || elem.getAttribute("cont").contains("#")) && (count < 3)){
	            	   count++;
	            	   Node noun = nodes.item(i+1);
	            	   Element nelem = (Element) noun;
	            	   wcp[pl][0] = nelem.getAttribute("id");
	            	   wcp[pl][1] = nelem.getAttribute("parent");
	            	   wcp[pl][2] = nelem.getAttribute("relate");
	            	   //System.out.println(elem.getAttribute("cont") + " " + wcp[pl][0] + " " + wcp[pl][1] + " " + wcp[pl][2]);
	            	   pl++;
	               }
	           }  
	       }  
	       catch (Exception e)  
	       {  
	           e.printStackTrace();  
	       }  
	       return wcp;
	}
	
	public int[] getmdp(String[][] wcp){
		int[] mdp = new int[3];
		if(wcp[0][1].equals(wcp[1][0]))
		{
			if(wcp[0][2].equals("SBV"))
				mdp[0] = 2;
//			else if(wcp[0][2].equals("ATT"))
//				mdp[0] = -1;
		}
		if(wcp[1][1].equals("-1")){
			if(wcp[1][2].equals("HED"))
				mdp[1] = 5;
		}
		if(wcp[2][1].equals(wcp[1][0])){
			if(wcp[2][2].equals("IOB") || wcp[2][2].equals("POB"))
				mdp[2] = 1;
			else if(wcp[2][2].equals("VOB"))
				mdp[2] = 2;
//			else if(wcp[1][2].equals("ATT"))
//				mdp[2] = -1;
		}
		return mdp;
	}
	
	
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Feature_Ltp ftest = new Feature_Ltp("sourcefile/second/dp_test", "sourcefile/second/f_dp_test_4.txt");
		Feature_Ltp ftrain = new Feature_Ltp("sourcefile/second/dp_train", "sourcefile/second/f_dp_train_4.txt");
		
	}
	
/*	public int pipei(String s){
		int num = 0;
		if(s.equals("IS"))
			num = 1;
		else if(s.equals("COO"))
			num = 2;
		else if(s.equals("ATT"))
			num = 3;
		else if(s.equals("POB"))
			num = 4;
		else if(s.equals("HED"))
			num = 5;
		else if(s.equals("FOB"))
			num = 6;
		else if(s.equals("IOB"))
			num = 7;
		else if(s.equals("VOB"))
			num = 8;
		else if(s.equals("SBV"))
			num = 9;

		return num;
	}
*/

}
