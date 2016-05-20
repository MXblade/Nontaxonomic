package bistu.idcc.second_fea;

import java.io.IOException;

import bistu.idcc.ltp.LTP_DP;

public class LTP {

	public static void main(String[] args) throws IOException{
		LTP_DP ld = new LTP_DP();
		ld.ltp_dp("sourcefile/second/text_test.txt", "sourcefile/second/dp_test");
		ld.ltp_dp("sourcefile/second/text_train.txt", "sourcefile/second/dp_train");
	}
}
