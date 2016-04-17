package bistu.idcc.features;

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
 * ����LTP��api�����о䷨����
 * @author Joen
 *
 */
public class LTP_DP {

	public void ltp_dp(String path, String outpath)throws IOException{
		//���ڻ�ȡÿ�����ӵ�url
		Get_LTP_URL glu = new Get_LTP_URL();
		//��ղ������ļ���
		ClearTxt_Path clear = new ClearTxt_Path();
		clear.clearpath(outpath);
		//������
		BufferedReader br = new BufferedReader(new FileReader(path));
		String temp = null;
		int num = 0;
		
		while((temp = br.readLine()) != null){
			num++;
			System.out.println(num);
			System.out.println(temp);
			//��ȡÿ�仰��url�����ӷ���������ȡ������Ϣ�����ڷ��ص���utf-8��ʽ��������ע
			URL urltxt = glu.g_url(temp);
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
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		/**
		 * ÿ�ε���ʱ�����֮ǰ�����������ȫ����ա�����ÿ��ʹ��ʱ�������·������������ԭ�򣬿��ܻ��жϣ�
		 */
//		LTP_DP ltp = new LTP_DP();
//		ltp.ltp_dp("sourcefile/candidate/candidate_set.txt", "sourcefile/features/ltp");
	}

}
