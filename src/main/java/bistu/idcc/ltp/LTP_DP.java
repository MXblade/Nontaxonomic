package bistu.idcc.ltp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


import bistu.idcc.functions.ClearTxt_Path;
/**
 * 调用LTP的api，进行句法分析
 * @author Joen
 *
 */
public class LTP_DP {

	public void ltp_dp(String path, String outpath)throws IOException{
		//清空并创建文件夹
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.clearpath(outpath);
		//输入流
		BufferedReader br = new BufferedReader(new FileReader(path));
		String temp = null;
		int num = 0;
		
		while((temp = br.readLine()) != null){
			num++;
			System.out.println(num);
			System.out.println(temp);
			//获取每句话的url，连接服务器并获取返回信息，由于返回的是utf-8格式，需额外标注
			URL urltxt = g_url(temp);
	        URLConnection conn = urltxt.openConnection();
	        conn.connect();
	        BufferedReader innet = new BufferedReader(new InputStreamReader( conn.getInputStream(), "UTF-8") );
	        String line;
	        String ofilepath = outpath + "/" + String.valueOf(num) + ".xml";
	        clear.cleartxt(ofilepath);
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ofilepath), "UTF-8"));
	        while ((line = innet.readLine())!= null) {
	        	bw.write(line);
	        	bw.newLine();
	        }
	        bw.close();
	        innet.close();

		}
		
	}
	
	/**
	 * 给定待处理文本text，返回URL
	 * @param text
	 * @return
	 * @throws IOException
	 */
	public URL g_url(String text)throws IOException{
		//LTP的api_key
		String api_key = "v6D3S052lxaSTnrBmiGyJSSrXv6qQxxyXIZMngTY";
        //pattern选择的分析模式，ws(分词)，pos(词性标注)，ner(命名实体识别)，dp(依存句法分析)，sdp(语义依存分析)，srl(语义角色标注),all(全部任务)
		String pattern = "dp";
		//format指定结果格式类型，xml(XML格式)，json(JSON格式)，conll(CONLL格式)，plain(简洁文本格式)
        String format  = "xml";
        //将文本转化为utf-8格式，ltp处理只能处理该格式文本
        text = URLEncoder.encode(text, "UTF-8");
        
        URL url     = new URL("http://api.ltp-cloud.com/analysis/?"
                + "api_key=" + api_key + "&"
                + "text="    + text    + "&"
                + "format="  + format  + "&"
                + "pattern=" + pattern);

		
		return url;
	}

	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		/**
		 * 每次调用时，会把之前输出过的数据全部清空。尽量每次使用时更换输出路径，由于网络原因，可能会中断，
		 */
		LTP_DP ltp = new LTP_DP();
		ltp.ltp_dp("sourcefile/candidate/candidate_set.txt", "sourcefile/features/ltp");
	}

}
