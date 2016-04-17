package bistu.idcc.candidateset;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.hankcs.hanlp.dependency.nnparser.util.math;

/**
 * 给定N-V-N组合，计算N-N在所有文档出现的次数，以及N-V-N出现的次数。
 * 通过公式给出NVN的文档频率 ((NVN)/(NN))
 * @f_nvn
 * nvn的文档频率
 * @num_nn
 * n-n文档出现的次数
 * @num_nvn
 * n-v-n文档出现的次数
 * @get_f_nvn(String n1, String v, String n2, HashMap<String, HashSet<String>> hm)
 * 计算n1-v-n2文档频率，需给专利文件夹的hashmap，返回double
 * @g_num_nn(String n1, String n2, HashMap<String, HashSet<String>> hm)
 * 计算n1-n2文档出现的次数，需给专利文件夹的hashmap，返回double
 * @g_num_nvn(String n1, String v, String n2, HashMap<String, HashSet<String>> hm)
 * 计算n1-v-n2文档出现的次数，需给专利文件夹的hashmap，返回double
 * @author Joen
 *
 */
public class IDF_NVN {
	
	//nvn的文档频率
	double f_nvn = 0.0;
	//nn的出现文档次数
	double num_nn = 0.0;
	//nvn的出现文档次数
	double num_nvn = 0.0;
	
	/*
	 * 计算f_nvn，返回double， 需给定n1,v,n2,HashMap
	 */
	public double get_f_nvn(String n1, String v, String n2, HashMap<String, HashSet<String>> hm){
		//统计nn出现文档次数
		num_nn = g_num_nn(n1,n2,hm);
		//统计nvn出现文档次数
		num_nvn = g_num_nvn(n1,v,n2,hm);
		//计算公式
		f_nvn = (num_nvn/num_nn);
		return f_nvn;
		
	}
	
	/*
	 * 统计n1，n2共同出现的文档次数。
	 */
	public double g_num_nn(String n1, String n2, HashMap<String, HashSet<String>> hm){
		double num = 0.0;
		//对给定的专利文件夹的hashmap进行遍历
		Iterator<Entry<String, HashSet<String>>> itera = hm.entrySet().iterator();
		while(itera.hasNext()){
			//遍历的对象是专利名+该专利所有词构成的Set
			Entry<String, HashSet<String>> entry =  itera.next();
			HashSet<String> value = entry.getValue();
			//对Set进行查询
			if(value.contains(n1)&&value.contains(n2)){
				num = num + 1.0;
			}
		}
		return num;
	}
	
	/*
	 * 统计n1，v,n2共同出现的文档次数。
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
		nvn.get_f_nvn("电动车", "混", "电池", hmp.hm_p);
		System.out.println(nvn.num_nn);
		System.out.println(nvn.num_nvn);
		System.out.println(nvn.f_nvn);
	}
*/
}
