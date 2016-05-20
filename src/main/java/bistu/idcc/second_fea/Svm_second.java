package bistu.idcc.second_fea;

import java.io.IOException;

import bistu.idcc.exam.Accuracy;
import bistu.idcc.exam.Svm;

public class Svm_second {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		//包括基础特征
		test("sourcefile/second/svm/test_0.txt", "0");
		//包括mdp特征
		test("sourcefile/second/svm/test_1.txt", "1");
		//包括新添的特征
		test("sourcefile/second/svm/test_2.txt", "2");
//		//将三个新特征合并成一个
//		test("sourcefile/second/svm/test_3.txt", "3");
//		//将所有关系全部表示出来
//		test("sourcefile/second/svm/test_4.txt", "4");

	}
	
	
	
	public static void test(String path, String num) throws IOException{
		String testpath = path;
		String folderpath = "";
		String[] list = testpath.split("/");
		for(int i = 0; i < list.length-1; i++){
			folderpath = folderpath + list[i] + "/";
		}
		String trainpath = folderpath + "train_" + num + ".txt";
		String modelpath = folderpath + "exam_" + num + ".model";
		String resultpath = folderpath + "result_" + num + ".txt";
		Svm svm = new Svm(trainpath, modelpath, testpath, resultpath);
		Accuracy a =new Accuracy(resultpath,testpath);
	}

}
