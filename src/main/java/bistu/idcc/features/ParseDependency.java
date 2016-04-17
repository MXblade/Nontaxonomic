package bistu.idcc.features;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;
import com.hankcs.hanlp.dependency.CRFDependencyParser;

public class ParseDependency {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(HanLP.segment("���г����÷��еĵȼ���ʽ�ƶ���Ԫ����ԭ�����뵳������͹��һ���������"));
		System.out.println(CRFDependencyParser.compute("����¶ȿ���ģ��ͨ���������ɼ����ģ��ġ����塿�¶Ȳ�����ź�{����}��ȴ���ȡ�ˮ�á���Ż��򷧼�����������"));
	}

}
