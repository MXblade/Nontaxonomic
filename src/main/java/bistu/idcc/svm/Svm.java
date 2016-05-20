package bistu.idcc.svm;
import java.io.IOException;

import libsvm.svm;
import bistu.idcc.svm.*;
import libsvm.svm_model;
/**
 * 调用svm
 * svm(训练语料，模型路径，测试语料，结果路径)
 * @author Joen
 *
 */

public class Svm {
	public Svm(String trainpath, String modelpath, String testpath, String resultpath) throws IOException{
		  
		svm_train svmt = new svm_train();
		svm_predict svmp = new svm_predict();

		String[] argvTrain = {trainpath,modelpath};
		String[] argvPredict = {testpath, modelpath, resultpath};

		try {
			svmt.main(argvTrain);
			svmp.main(argvPredict);
		}catch (IOException e) {
			e.printStackTrace();
		}

		double[] record = { -1, 12, 12, 78 };

		libsvm.svm_model model = svm.svm_load_model(modelpath);

		System.out.println("true");

	}
}
