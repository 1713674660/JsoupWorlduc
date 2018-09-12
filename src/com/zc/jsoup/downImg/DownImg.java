package com.zc.jsoup.downImg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * �����û�����Ƭ
 * @author zc
 * 2018��9��12��14:08:56
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
		
		System.out.printf("%s�������أ�%s����Ƭ\n",Thread.currentThread().getName(),pathName);
		//�ļ���
		File newFolder = new File(folder+pathName);
		if(!newFolder.exists()) {
			newFolder.mkdirs();
		}
		//�ļ�
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
			System.out.printf("%s�����غ�%s����Ƭ",Thread.currentThread().getName(),pathName);
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
