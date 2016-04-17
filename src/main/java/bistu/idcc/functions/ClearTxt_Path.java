package bistu.idcc.functions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 对文件和文件夹进行清空创建操作
 * clearpath清空创建文件夹
 * cleartxt清空创建文件
 * @author Joen
 *
 */
public class ClearTxt_Path {
	
	/**
	 * 清空path路径下的有文件并创建path路径的文件夹
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
	 * 文件存在则清空文件内容，不存在则创建该文件
	 * @param path
	 * @throws IOException 
	 */
	public void cleartxt(String path) throws IOException{
		File newfile = new File(path);
		if(!newfile.exists()){
				newfile.createNewFile();
		}
		
		//清空文件内容
	    FileWriter fileClear = new FileWriter(path);
	    BufferedWriter bufferClear = new BufferedWriter(fileClear);
	    bufferClear.write("");
	    bufferClear.close();
	}


}
