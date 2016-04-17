package bistu.idcc.candidateset;

import java.io.IOException;

public class Candidate_Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		//对标记好的专利句子进行无nvn词组过滤。
		Filter_NonNVN filter = new Filter_NonNVN();
		filter.filter_nonnvn("sourcefile/candidate/TagParser.txt", "sourcefile/candidate/after_filter.txt");
		
		//选取idf值最高的nvn词组，并写入文件。
		Candidate_Set set = new Candidate_Set();
		set.candset("sourcefile/candidate/after_filter.txt", "sourcefile/candidate/candidate_set.txt", "sourcefile/afparser");
	}

}
