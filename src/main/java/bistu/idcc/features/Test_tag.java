package bistu.idcc.features;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 用于测试对语料标注的格式有没有错误
 * @author Joen
 *
 */
public class Test_tag {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("sourcefile/features/data/biaozhu.txt"), "UTF-8"));
		String temp = null;
		int count_1 = 0;
		int count_0 = 0;
		int linenum = 0;
		while((temp = br.readLine()) != null)
		{
			linenum++;
			String[] templist = temp.split(" ");
			if(templist[0].equals("0")){
				count_0++;
			}
			else if(templist[0].equals("1")){
				count_1++;
			}
			else{
				System.out.println("Error: " + linenum);
			}
		}
		br.close();
		System.out.println("sum_0: " + count_0 + "; sum_1: " + count_1);
	}

}
