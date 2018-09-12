package com.zc.jsoup.write;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;
import com.zc.jsoup.model.User;

/**
 * ����ȡ����Ϣд�뱾��
 * @author zc
 * 2018��9��12��14:08:18
 */
public class JsoupWrite {

	/**
	 * ��һ���û�����Ϣд�뱾��
	 * @param �ļ���
	 * @param �û�����
	 */
	public static void wirteFile(String filePath,User user) {
		
		String name = user.getName();
		File file = new File(filePath+name);
		if(!file.exists()) {
			file.mkdirs();
		}
		File newFile = new File(file,name+".json");
		Writer w = null;
		BufferedWriter bw = null;
		try {
			w = new FileWriter(newFile,false);
			bw = new BufferedWriter(w);
	
			Gson gson = new Gson();
			// toJson ��bean����ת��Ϊjson�ַ���
			String jsonStr = gson.toJson(user, User.class);
			bw.write(jsonStr);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(bw!=null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(w!=null) {
				try {
					w.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
