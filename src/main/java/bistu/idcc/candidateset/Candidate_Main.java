package bistu.idcc.candidateset;

import java.io.IOException;

public class Candidate_Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		//�Ա�Ǻõ�ר�����ӽ�����nvn������ˡ�
		Filter_NonNVN filter = new Filter_NonNVN();
		filter.filter_nonnvn("sourcefile/candidate/TagParser.txt", "sourcefile/candidate/after_filter.txt");
		
		//ѡȡidfֵ��ߵ�nvn���飬��д���ļ���
		Candidate_Set set = new Candidate_Set();
		set.candset("sourcefile/candidate/after_filter.txt", "sourcefile/candidate/candidate_set.txt", "sourcefile/afparser");
	}

}
