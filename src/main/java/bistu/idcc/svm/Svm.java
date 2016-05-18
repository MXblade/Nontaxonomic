package bistu.idcc.svm;
import java.io.IOException;

import libsvm.svm;

import libsvm.svm_model;
/**
 * 调用svm
 * svm(训练语料，模型路径，测试语料，结果路径)
 * @author Joen
 *
 */

public class Svm {
	public void svm(String trainpath, String modelpath, String testpath, String resultpath) throws IOException{
		  svm_train svmt = new svm_train();
		  svm_predict svmp = new svm_predict();

		  String[] argvTrain = {trainpath,modelpath};
		  String[] argvPredict = {testpath, modelpath, resultpath};

		  try {
		   svmt.main(argvTrain);
		   svmp.main(argvPredict);
		  } catch (IOException e) {
		   e.printStackTrace();
		  }

		  double[] record = { -1, 12, 12, 78 };

		  libsvm.svm_model model = svm.svm_load_model(modelpath);

		  System.out.println("true");

	}
	public static void main(String[] args) throws IOException {
		  
/*		Svm ss = new Svm();
		ss.svm("sourcefile/svm/train_exam_4.txt", "sourcefile/svm/exam_4.model", "sourcefile/svm/test_exam_4.txt", "sourcefile/svm/result_exam_4.txt");
*/
		//		String[] argvTrain = {
//		  "C:\\libsvm-3.18\\windows\\test\\final_1.txt",// 训练文件
//		  "C:\\libsvm-3.18\\windows\\test\\final_1.model"// 模型文件
//		  };
//		  String[] argvPredict = {
//		    "C:\\libsvm-3.18\\windows\\test\\testFinal_1.txt",// 预测文件
//		    "C:\\libsvm-3.18\\windows\\test\\final_1.model", // 模型文件
//		    "C:\\libsvm-3.18\\windows\\test\\result_1.txt" // 预测结果文件
//		  };
	}
}
