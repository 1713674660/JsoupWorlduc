package com.zc.jsoup.runnable;

import com.zc.jsoup.JsoupFromWorlduc;

/**
 * 多线程下载
 * @author zc
 * 2018年9月12日14:09:46
 */
public class DownRunnable implements Runnable {

	String folder = null;
	private int uid=0;
	public DownRunnable(String folder,int uid) {
		this.folder = folder;
		this.uid = uid;
	}
	
	@Override
	public void run() {
		
		JsoupFromWorlduc jfw = JsoupFromWorlduc.getJsoup();
		jfw.jsoupInfo(folder,uid);
	}

}
