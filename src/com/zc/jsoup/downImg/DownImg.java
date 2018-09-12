package com.zc.jsoup.downImg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 下载用户的相片
 * @author zc
 * 2018年9月12日14:08:56
 */
public class DownImg implements Runnable{
	private String baseUrl="http://worlduc.com";
	private String folder = null;
	private String path=null;
	private String pathName=null;
	public DownImg(String folder,String path,String pathName) {
		this.folder = folder;
		this.path = path;
		this.pathName=pathName;
	}
	
	@Override
	public void run() {
		
		System.out.printf("%s正在下载：%s的相片\n",Thread.currentThread().getName(),pathName);
		//文件夹
		File newFolder = new File(folder+pathName);
		if(!newFolder.exists()) {
			newFolder.mkdirs();
		}
		//文件
		String file = path.substring(path.lastIndexOf("."));
		
		InputStream in = null;
		OutputStream out = null;
		try {
			URL url = new URL(baseUrl+path);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			in = con.getInputStream();
			
			out = new FileOutputStream(new File(newFolder,pathName+file),true);
			
			int len = 0;
			byte[] buf = new byte[1024];
			while((len = in.read(buf))!=-1) {
				out.write(buf, 0, len);
			}
			System.out.printf("%s已下载好%s的相片",Thread.currentThread().getName(),pathName);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(out!=null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
