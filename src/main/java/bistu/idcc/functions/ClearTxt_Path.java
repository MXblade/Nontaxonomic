package bistu.idcc.functions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * ���ļ����ļ��н�����մ�������
 * clearpath��մ����ļ���
 * cleartxt��մ����ļ�
 * @author Joen
 *
 */
public class ClearTxt_Path {
	
	/**
	 * ���path·���µ����ļ�������path·�����ļ���
	 * @param path
	 * @throws IOException
	 */
	public void clearpath(String path) throws IOException{
		File filepath = new File(path);
		if(!filepath.exists()){
			filepath.mkdirs();
		}
		else{
			File[] filelist = filepath.listFiles();
			for(int i = 0; i < filelist.length; i++){
				filelist[i].delete();
			}
			System.out.println("Files Deleted");
		}
	}

	/**
	 * �ļ�����������ļ����ݣ��������򴴽����ļ�
	 * @param path
	 * @throws IOException 
	 */
	public void cleartxt(String path) throws IOException{
		File newfile = new File(path);
		if(!newfile.exists()){
				newfile.createNewFile();
		}
		
		//����ļ�����
	    FileWriter fileClear = new FileWriter(path);
	    BufferedWriter bufferClear = new BufferedWriter(fileClear);
	    bufferClear.write("");
	    bufferClear.close();
	}


}
