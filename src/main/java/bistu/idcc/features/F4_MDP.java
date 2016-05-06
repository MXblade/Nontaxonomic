package bistu.idcc.features;

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

public class F4_MDP {

	String ltppath = "sourcefile/features/ltp";
	String outpath = "sourcefile/features/single/f4_mdp.txt";
	
	public F4_MDP() throws IOException{
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.cleartxt(outpath);
		//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath), "UTF-8"));

		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outpath), "UTF-8"));
		File ltp = new File(ltppath);
		File[] ltplist = ltp.listFiles();
		int count = 0;
		int count1 = 0;
		for(File f:ltplist){
			count++;
			String[][] wcp = readxml(f);
			int mdp = getmdp(wcp);
			if(mdp != 0){
				count1 ++;
			}
			//System.out.println(mdp + " " + count);
			bw.write(String.valueOf(mdp));
			bw.newLine();
		}
		bw.close();
		System.out.println(count1);
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
	           //System.out.println("共有" + nodes.getLength() + "个dog节点");  
	           for (int i = 0; i < nodes.getLength(); i++)  
	           {  
	               Node word = nodes.item(i);  
	               Element elem = (Element) word;
	               //中间ltp进行句法分析时把设的标签没有分离出来。
	               if(elem.getAttribute("cont").contains("【") || elem.getAttribute("cont").contains("{")){
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
	
	public int getmdp(String[][] wcp){
		int mdp = 0;
		if(wcp[0][1].equals(wcp[1][0]))
		{
			if(wcp[0][2].equals("SBV")){
				mdp = mdp + 1;
			}
			else if(wcp[0][2].equals("ATT")){
				mdp = mdp - 1;
			}
		}
		if(wcp[2][1].equals(wcp[1][0])){
			if(wcp[1][2].equals("VOB") || wcp[1][2].equals("POB")){
				mdp = mdp + 1;
			}
			else if(wcp[1][2].equals("ATT")){
				mdp = mdp - 1;
			}
		}
		
		return mdp;
	}
	
/*	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		F4_MDP f4 = new F4_MDP();
	}
*/
}
