package bistu.idcc.features;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * 获取给定句子的url，用于之后向ltp服务器请求句法分析
 * @author Joen
 *
 */
public class Get_LTP_URL {

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
}
