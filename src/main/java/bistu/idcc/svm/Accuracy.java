package bistu.idcc.svm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Accuracy {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:/libsvm-3.18/windows/test/result.txt"), "UTF-8"));
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("C:/libsvm-3.18/windows/test/result_1.txt"), "UTF-8"));
		String temp = null;
		String temp1 = null;
		while((temp = br.readLine()) != null && (temp1 = br1.readLine())!= null){
			if(temp.equals(temp1)){
				
			}
			else
				System.out.println(false);
		}
		br.close();
		br1.close();
	}
}