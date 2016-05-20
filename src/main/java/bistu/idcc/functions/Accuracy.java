package bistu.idcc.functions;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 对结果进行准确率和召回率计算，并写入文件
 * resultpath svm预测结果路径
 * stapath 标准答案路径
 * @author Joen
 *
 */
public class Accuracy {

	double TP = 0.0;//正类预测为正类数目
	double FN = 0.0;//正类预测为负类数目
	double FP = 0.0;//负类预测为正类数目
	double TN = 0.0;//负类预测为负类数目
	
	public Accuracy(String resultpath, String stapath) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(resultpath), "UTF-8"));
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(stapath), "UTF-8"));
		String temp = null;
		String temp1 = null;
		while((temp = br.readLine()) != null && (temp1 = br1.readLine())!= null){
			String ra = temp;
			String sa = temp1.split(" ")[0];
			if(ra.equals("1.0") && sa.equals("1"))
				TP++;
			else if(ra.equals("-1.0") && sa.equals("1"))
				FN++;
			else if(ra.equals("1.0") && sa.equals("-1"))
				FP++;
			else if(ra.equals("-1.0") && sa.equals("-1"))
				TN++;
		}
		br.close();
		br1.close();
		double P = (TP+TN)/(TP+TN+FN+FP)*100;
		double R = TP/(TP+FN)*100;
		double F = (2*P*R)/(R+P);
		System.out.println("TP: " + TP + "; FN: " + FN + "; FP: " + FP + "; TN: " + TN);
		System.out.println("P: " + P + "%; R: " + R + "%; F: " + F);
	
	}
	/*
	 * 0st:
	 * TP: 219.0; FN: 40.0; FP: 86.0; TN: 155.0
	 * P: 74.8%; R: 84.55598455598455%; F: 79.37935512632895
	 * 1st: 
	 * TP: 219.0; FN: 40.0; FP: 76.0; TN: 165.0
	 * P: 76.8%; R: 84.55598455598455%; F: 80.49158674553495
	 * 2st: svm: 77.2% (386/500)
	 * TP: 221.0; FN: 38.0; FP: 76.0; TN: 165.0
	 * P: 77.2%; R: 85.32818532818533%; F: 81.0608436196395
	 * 
	 */
}
