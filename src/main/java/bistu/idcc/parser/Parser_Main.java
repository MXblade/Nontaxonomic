package bistu.idcc.parser;

import java.io.IOException;
import java.util.Date;

public class Parser_Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		Content_Parser cp = new Content_Parser();
		//cp.parser("sourcefile/patent", "sourcefile/after_parser.txt");
		cp.parser_files("sourcefile/patent", "sourcefile/afparser");
		System.out.println(new Date());

		
/*		Filter_NonNVN filnn = new Filter_NonNVN();
		filnn.filter_nonnvn("sourcefile/TagParser.txt", "sourcefile/after_filter.txt");
*/	}

}
