package com.zc.jsoup.write;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;
import com.zc.jsoup.model.User;

/**
 * 将爬取的信息写入本地
 * @author zc
 * 2018年9月12日14:08:18
 */
public class JsoupWrite {

	/**
	 * 将一个用户的信息写入本地
	 * @param 文件夹
	 * @param 用户对象
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
			// toJson 将bean对象转换为json字符串
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
