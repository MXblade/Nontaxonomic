package bistu.idcc.features;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * ��ȡ�������ӵ�url������֮����ltp����������䷨����
 * @author Joen
 *
 */
public class Get_LTP_URL {

	public URL g_url(String text)throws IOException{
		//LTP��api_key
		String api_key = "v6D3S052lxaSTnrBmiGyJSSrXv6qQxxyXIZMngTY";
        //patternѡ��ķ���ģʽ��ws(�ִ�)��pos(���Ա�ע)��ner(����ʵ��ʶ��)��dp(����䷨����)��sdp(�����������)��srl(�����ɫ��ע),all(ȫ������)
		String pattern = "dp";
		//formatָ�������ʽ���ͣ�xml(XML��ʽ)��json(JSON��ʽ)��conll(CONLL��ʽ)��plain(����ı���ʽ)
        String format  = "xml";
        //���ı�ת��Ϊutf-8��ʽ��ltp����ֻ�ܴ���ø�ʽ�ı�
        text = URLEncoder.encode(text, "UTF-8");
        
        URL url     = new URL("http://api.ltp-cloud.com/analysis/?"
                + "api_key=" + api_key + "&"
                + "text="    + text    + "&"
                + "format="  + format  + "&"
                + "pattern=" + pattern);

		
		return url;
	}
}
