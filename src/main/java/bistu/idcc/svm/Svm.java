package bistu.idcc.svm;
import java.io.IOException;

import libsvm.svm;

import libsvm.svm_model;
public class Svm {
	public static void main(String[] args) throws IOException {
		  svm_train svmt = new svm_train();

		  svm_predict svmp = new svm_predict();

		  String[] argvTrain = {

		  "C:\\libsvm-3.18\\windows\\test\\final_1.txt",// 训练文件

		    "C:\\libsvm-3.18\\windows\\test\\final_1.model"// 模型文件
		                    
		  };

		  String[] argvPredict = {

		    "C:\\libsvm-3.18\\windows\\test\\testFinal_1.txt",// 预测文件

		    "C:\\libsvm-3.18\\windows\\test\\final_1.model", // 模型文件

		    "C:\\libsvm-3.18\\windows\\test\\result_1.txt" // 预测结果文件

		  };

		  try {

		   svmt.main(argvTrain);

		   svmp.main(argvPredict);

		  } catch (IOException e) {

		   e.printStackTrace();

		  }

		  double[] record = { -1, 12, 12, 78 };

		  libsvm.svm_model model = svm
		    .svm_load_model("C:\\libsvm-3.18\\windows\\test\\final_1.model");

		  System.out.println("true");
		 }
}
