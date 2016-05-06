package bistu.idcc.candidateset;

import java.io.IOException;

public class Candidate_Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
/*		//将分词后的语料进行标记
		EntityExtraction e = new EntityExtraction("sourceFile/PatentTerm.txt","sourceFile/after_parser.txt","sourceFile/candidate/TagParser.txt");
		
		System.out.println("完成语料标记");
		System.out.println("……");
		
		//对标记好的专利句子进行无nvn词组过滤。
		Filter_NonNVN filter = new Filter_NonNVN();
		filter.filter_nonnvn("sourcefile/candidate/TagParser.txt", "sourcefile/candidate/after_filter.txt");
		
		System.out.println("完成无nvn过滤");
		System.out.println("……");
	
*/		
		//选取idf值最高的nvn词组，并写入文件。
		Candidate_Set set = new Candidate_Set();
		set.candset("sourcefile/candidate/after_filter.txt", "sourcefile/candidate/candidate.txt", "sourcefile/afparser");

		System.out.println("完成idf选取");
		System.out.println("……");
		
		//过滤掉nvn重复的句子
		Filter_repeat fr = new Filter_repeat();
		fr.flrepeat("sourcefile/candidate/candidate.txt", "sourcefile/candidate/candidate_set.txt");
		
		System.out.println("全部完成");
	}

}
