package bistu.idcc.candidateset;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.hankcs.hanlp.dependency.nnparser.util.math;

/**
 * ����N-V-N��ϣ�����N-N�������ĵ����ֵĴ������Լ�N-V-N���ֵĴ�����
 * ͨ����ʽ����NVN���ĵ�Ƶ�� ((NVN)/(NN))
 * @f_nvn
 * nvn���ĵ�Ƶ��
 * @num_nn
 * n-n�ĵ����ֵĴ���
 * @num_nvn
 * n-v-n�ĵ����ֵĴ���
 * @get_f_nvn(String n1, String v, String n2, HashMap<String, HashSet<String>> hm)
 * ����n1-v-n2�ĵ�Ƶ�ʣ����ר���ļ��е�hashmap������double
 * @g_num_nn(String n1, String n2, HashMap<String, HashSet<String>> hm)
 * ����n1-n2�ĵ����ֵĴ��������ר���ļ��е�hashmap������double
 * @g_num_nvn(String n1, String v, String n2, HashMap<String, HashSet<String>> hm)
 * ����n1-v-n2�ĵ����ֵĴ��������ר���ļ��е�hashmap������double
 * @author Joen
 *
 */
public class IDF_NVN {
	
	//nvn���ĵ�Ƶ��
	double f_nvn = 0.0;
	//nn�ĳ����ĵ�����
	double num_nn = 0.0;
	//nvn�ĳ����ĵ�����
	double num_nvn = 0.0;
	
	/*
	 * ����f_nvn������double�� �����n1,v,n2,HashMap
	 */
	public double get_f_nvn(String n1, String v, String n2, HashMap<String, HashSet<String>> hm){
		//ͳ��nn�����ĵ�����
		num_nn = g_num_nn(n1,n2,hm);
		//ͳ��nvn�����ĵ�����
		num_nvn = g_num_nvn(n1,v,n2,hm);
		//���㹫ʽ
		f_nvn = (num_nvn/num_nn);
		return f_nvn;
		
	}
	
	/*
	 * ͳ��n1��n2��ͬ���ֵ��ĵ�������
	 */
	public double g_num_nn(String n1, String n2, HashMap<String, HashSet<String>> hm){
		double num = 0.0;
		//�Ը�����ר���ļ��е�hashmap���б���
		Iterator<Entry<String, HashSet<String>>> itera = hm.entrySet().iterator();
		while(itera.hasNext()){
			//�����Ķ�����ר����+��ר�����дʹ��ɵ�Set
			Entry<String, HashSet<String>> entry =  itera.next();
			HashSet<String> value = entry.getValue();
			//��Set���в�ѯ
			if(value.contains(n1)&&value.contains(n2)){
				num = num + 1.0;
			}
		}
		return num;
	}
	
	/*
	 * ͳ��n1��v,n2��ͬ���ֵ��ĵ�������
	 */
	public double g_num_nvn(String n1, String v, String n2, HashMap<String, HashSet<String>> hm){
		double num = 0.0;
		Iterator<Entry<String, HashSet<String>>> itera = hm.entrySet().iterator();
		while(itera.hasNext()){
			Entry<String, HashSet<String>> entry =  itera.next();
			HashSet<String> value = entry.getValue();
			if(value.contains(n1) && value.contains(n2) && value.contains(v)){
				num = num + 1.0;
			}
		}
		return num;
	}
	
/*	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		HashMap_Patents hmp = new HashMap_Patents("sourcefile/afparser");
		IDF_NvN nvn = new IDF_NvN();
		nvn.get_f_nvn("�綯��", "��", "���", hmp.hm_p);
		System.out.println(nvn.num_nn);
		System.out.println(nvn.num_nvn);
		System.out.println(nvn.f_nvn);
	}
*/
}
