package bistu.idcc.features;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;
import com.hankcs.hanlp.dependency.CRFDependencyParser;

public class ParseDependency {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "根据 发动机 、 扭矩 传感器 、 电机 、  前 、 后 、 左 、 右 【 支架 】 构成 台架";
		System.out.println(HanLP.segment("把市场经济奉行的等价盘式制动单元交换原则引入党的生活和国家机关政务活动中"));
		System.out.println(CRFDependencyParser.compute("电池温度控制模块通过传感器采集电池模块的【本体】温度并输出信号{控制}冷却风扇、水泵、电磁换向阀及【加热器】"));
	}

}
